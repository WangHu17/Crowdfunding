package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanghu
 * @discrption： 与前端对接的 member实体类
 * @create 2021-11-03 16:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberVO {

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phoneNumber;

    private String verificationCode;

}
