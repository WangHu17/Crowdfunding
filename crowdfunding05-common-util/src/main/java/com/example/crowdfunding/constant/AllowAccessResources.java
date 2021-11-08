package com.example.crowdfunding.constant;

import java.util.HashSet;

/**
 * @author wanghu
 * @discrption： 放行的请求地址和静态资源
 * @create 2021-11-05 15:59
 */
public class AllowAccessResources {

    // 放行的请求地址
    public static final HashSet<String> PASS_URL_SET = new HashSet<>();

    static {
        PASS_URL_SET.add("/");
        PASS_URL_SET.add("/auth/member/to/register/page.html");
        PASS_URL_SET.add("/auth/member/to/login/page.html");
        PASS_URL_SET.add("/auth/member/do/register");
        PASS_URL_SET.add("/auth/member/do/login");
        PASS_URL_SET.add("/auth/member/send/short/message");
        PASS_URL_SET.add("/auth/member/logout");
    }

    // 静态资源
    public static final HashSet<String> STATIC_RES_SET = new HashSet<>();

    static {
        STATIC_RES_SET.add("bootstrap");
        STATIC_RES_SET.add("css");
        STATIC_RES_SET.add("fonts");
        STATIC_RES_SET.add("img");
        STATIC_RES_SET.add("jquery");
        STATIC_RES_SET.add("layer");
        STATIC_RES_SET.add("script");
        STATIC_RES_SET.add("ztree");
    }

    // 判断请求地址是否是静态资源
    public static boolean judgeUrlWeatherStaticResource(String url){

        if (url == null || url.length() == 0)
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);

        String[] split = url.split("/");
        return STATIC_RES_SET.contains(split[1]);
    }

}
