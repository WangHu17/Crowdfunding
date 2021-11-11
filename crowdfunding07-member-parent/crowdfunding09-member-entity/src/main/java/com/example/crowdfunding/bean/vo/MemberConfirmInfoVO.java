package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 19:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberConfirmInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // 易付宝账号
    private String payNum;

    // 法人身份证号
    private String cardNum;

}
