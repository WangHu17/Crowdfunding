package com.example.crowdfunding.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wanghu
 * @discrption： 用来管理发送短信方法中的参数
 * @create 2021-11-02 15:50
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@ConfigurationProperties(prefix = "short.message")
public class ShortMessageProperties {

    private String host;
    private String path;
    private String method;
    private String appcode;
    private String sign;
    private String skin;

}
