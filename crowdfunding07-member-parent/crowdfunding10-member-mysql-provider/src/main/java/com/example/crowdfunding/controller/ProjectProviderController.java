package com.example.crowdfunding.controller;

import com.example.crowdfunding.bean.vo.PortalTypeVO;
import com.example.crowdfunding.bean.vo.ProjectDetailVO;
import com.example.crowdfunding.bean.vo.ProjectVO;
import com.example.crowdfunding.service.api.ProjectService;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-11-09 18:55
 */
@RestController
public class ProjectProviderController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/get/project/detail/{id}")
    ResultEntity<ProjectDetailVO> getProjectDetailVORemote(@PathVariable("id")Integer projectId) {
        try {
            ProjectDetailVO projectDetailVO = projectService.getProjectDetail(projectId);
            return ResultEntity.successWithData(projectDetailVO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/get/portal/type/project/remote")
    ResultEntity<List<PortalTypeVO>> getPortalTypeProjectRemote() {
        try {
            List<PortalTypeVO> portalTypeList = projectService.getPortalTypeList();
            return ResultEntity.successWithData(portalTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    @RequestMapping("/save/projectvo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId")Integer memberId) {
        try {
            projectService.saveProjectVO(projectVO, memberId);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

}
