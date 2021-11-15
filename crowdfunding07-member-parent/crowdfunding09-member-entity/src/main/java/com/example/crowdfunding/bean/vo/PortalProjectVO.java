package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-15 15:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PortalProjectVO {

    private Integer projectId;
    private String projectName;
    private String headerPicturePath;
    private Integer money;
    private String deployDate;
    private Integer percentage;
    private Integer supporter;

}
