package com.example.crowdfunding.util;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.example.crowdfunding.constant.CrowdConstant;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-16 17:16
 */
public class CrowdUtil {

    /**
     * 专门负责上传文件到 OSS 服务器的工具方法
     *
     * @param endpoint        OSS 参数
     * @param accessKeyId     OSS 参数
     * @param accessKeySecret OSS 参数
     * @param inputStream     要上传的文件的输入流
     * @param bucketName      OSS 参数
     * @param bucketDomain    OSS 参数
     * @param originalName    要上传的文件的原始文件名
     * @return 包含上传结果以及上传的文件在 OSS 上的访问路径
     */
    public static ResultEntity<String> uploadFileToOss(
            String endpoint,
            String accessKeyId,
            String accessKeySecret,
            InputStream inputStream,
            String bucketName,
            String bucketDomain,
            String originalName) {

        // 创建 OSSClient 实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // 生成上传文件在 OSS 服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用 UUID 生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
        String extensionName = originalName.substring(originalName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;

        try {
            // 调用 OSS 客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, objectName, inputStream);

            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if (responseMessage == null) {

                // 拼接访问刚刚上传的文件的路径
                String ossFileAccessPath = bucketDomain + "/" + objectName;

                // 当前方法返回成功
                return ResultEntity.successWithData(ossFileAccessPath);

            } else {

                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();

                // 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();

                // 当前方法返回失败
                return ResultEntity.failed(" 当 前 响 应 状 态 码 =" + statusCode + " 错 误 消 息 =" + errorMessage);
            }
        } catch (Exception e) {

            e.printStackTrace();

            // 当前方法返回失败
            return ResultEntity.failed(e.getMessage());

        } finally {

            if (ossClient != null) {

                // 关闭 OSSClient。
                ossClient.shutdown();

            }
        }
    }

    /**
     * 向第三方接口发送请求给指定手机号发送短信
     *
     * @param host        短信接口调用的URL地址
     * @param path        具体发送短信功能的地址
     * @param method      请求方式
     * @param appcode     用来调用第三方短信API的AppCode
     * @param phoneNumber 接收短信的手机号
     * @param sign        签名编号
     * @param skin        模板编号
     * @return 成功返回验证码，失败返回失败消息
     */
    public static Msg sendShortMessage(String host, String path, String method, String appcode, String
            phoneNumber, String sign, String skin) {

        Map<String, String> headers = new HashMap<>();
        // 最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        // 封装其他参数
        Map<String, String> querys = new HashMap<>();
        // 短信内容（包括签名和验证码）
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 10);
            builder.append(num);
        }
        String verificationCode = builder.toString();
        // 自定义短信内容需要联系客服进行模板报备（【巨头公司】欢迎阁下加入1424巨头股份有限公司，阁下的验证码是：1424，3分钟有效！希望你早日获得巨头名号，称霸9A！）
        querys.put("content", "【创信】你的验证码是：5873，3分钟内有效！");

        // 要发送验证码的手机号
        querys.put("mobile", phoneNumber);
        Map<String, String> bodys = new HashMap<>();

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            String reasonPhrase = statusLine.getReasonPhrase();
            if (statusCode == 200) {
                return Msg.success().add("verificationCode", verificationCode);
            }
            return Msg.failWithMsg(reasonPhrase);
        } catch (Exception e) {
            e.printStackTrace();
            return Msg.failWithMsg(e.getMessage());
        }
    }

    /**
     * 对明文字符串进行 MD5 加密
     *
     * @param source 传入的明文字符串
     * @return 加密结果
     */
    public static String md5(String source) {
        // 1.判断 source 是否有效
        if (source == null || source.length() == 0) {
            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }
        try {
            // 3.获取 MessageDigest 对象
            String algorithm = "md5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();
            // 5.执行加密
            byte[] output = messageDigest.digest(input);
            // 6.创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);
            // 7.按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断当前请求是否为 Ajax 请求
     *
     * @param request
     * @return true代表是
     * false代表不是
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1.获取请求消息头信息
        String acceptInformation = request.getHeader("Accept");
        String xRequestInformation = request.getHeader("X-Requested-With");
        // 2.检查并返回
        return (
                acceptInformation != null &&
                        acceptInformation.length() > 0 &&
                        acceptInformation.contains("application/json")
        )
                ||
                (
                        xRequestInformation != null &&
                                xRequestInformation.length() > 0 &&
                                xRequestInformation.equals("XMLHttpRequest")
                );
    }
}
