package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-15 15:49
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortalTypeVO {

    private Integer id;
    private String name;
    private String remark;

    private List<PortalProjectVO> PortalProjectVOList;

}
