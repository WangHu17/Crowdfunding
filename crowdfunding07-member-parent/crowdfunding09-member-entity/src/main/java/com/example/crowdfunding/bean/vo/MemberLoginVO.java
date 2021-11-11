package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wanghu
 * @discrption： 用于存放用户登录信息的
 * @create 2021-11-04 15:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberLoginVO implements Serializable {

    private static final long serialVersionUID = -590295444506779334L;

    private Integer id;

    private String username;

    private String email;

}
