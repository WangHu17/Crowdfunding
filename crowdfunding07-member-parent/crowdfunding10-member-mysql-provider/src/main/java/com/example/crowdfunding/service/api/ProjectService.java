package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.vo.PortalTypeVO;
import com.example.crowdfunding.bean.vo.ProjectDetailVO;
import com.example.crowdfunding.bean.vo.ProjectVO;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 18:55
 */
public interface ProjectService {

    void saveProjectVO(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeList();

    ProjectDetailVO getProjectDetail(Integer id);

}
