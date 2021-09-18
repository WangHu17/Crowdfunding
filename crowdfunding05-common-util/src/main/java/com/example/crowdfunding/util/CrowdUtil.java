package com.example.crowdfunding.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-16 17:16
 */
public class CrowdUtil {
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
        return  (
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
