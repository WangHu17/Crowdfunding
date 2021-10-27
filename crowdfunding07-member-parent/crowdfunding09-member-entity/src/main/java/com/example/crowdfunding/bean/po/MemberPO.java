package com.example.crowdfunding.bean.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberPO {
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private Integer authstatus;

    private Integer usertype;

    private String realname;

    private String cardnum;

    private Integer accttype;

}