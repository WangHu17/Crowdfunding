package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-22 15:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderVO {

    private Integer id;

    private String orderNum;

    private String payOrderNum;

    private Double orderAmount;

    private Integer invoice;

    private String invoiceTitle;

    private String orderRemark;

    private String addressId;

    private OrderProjectVO orderProjectVO;

}
