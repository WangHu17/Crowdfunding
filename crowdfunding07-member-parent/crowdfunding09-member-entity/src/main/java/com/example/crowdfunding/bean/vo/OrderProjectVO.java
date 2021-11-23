package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-18 17:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProjectVO implements Serializable {

    private static final long serialVersionUID = 4983917628008380255L;
    private Integer id;
    private String projectName;
    private String launchName;
    private String returnContent;
    private Integer returnCount;
    private Integer supportPrice;
    private Integer freight;
    private Integer orderId;
    private Integer signalPurchase;
    private Integer purchase;

}
