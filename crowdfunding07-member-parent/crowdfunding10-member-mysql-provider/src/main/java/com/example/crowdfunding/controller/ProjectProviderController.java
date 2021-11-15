package com.example.crowdfunding.controller;

import com.example.crowdfunding.bean.vo.PortalTypeVO;
import com.example.crowdfunding.bean.vo.ProjectVO;
import com.example.crowdfunding.service.api.ProjectService;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
