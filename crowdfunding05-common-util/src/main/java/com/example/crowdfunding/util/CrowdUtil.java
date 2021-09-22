package com.example.crowdfunding.util;

import com.example.crowdfunding.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-16 17:16
 */
public class CrowdUtil {

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
