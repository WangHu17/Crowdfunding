package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-16 16:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnDetailVO {

    private Integer returnId;
    private Integer supportMoney;
    private String  content;
    private Integer signalPurchase;
    private Integer purchase;
    private Integer freight;
    private Integer returnDate;
    private Integer supportCount;

}
