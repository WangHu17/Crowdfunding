package com.example.crowdfunding.bean.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-16 16:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectDetailVO {

    private Integer id;
    private String projectName;
    private String projectDescription;
    private Integer money;
    private Integer day;
    private Integer status;
    private String statusText;
    private String deployDate;
    private Integer lastDays;
    private Integer supportMoney;
    private Integer percentage;
    private Integer supporterCount;
    private Integer followerCount;
    private String headerPicturePath;
    private List<String> detailPicturePathList;
    private List<ReturnDetailVO> ReturnDetailVOList;

}
