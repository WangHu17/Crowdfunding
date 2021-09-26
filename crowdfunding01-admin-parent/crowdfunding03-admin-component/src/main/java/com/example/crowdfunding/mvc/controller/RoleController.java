package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.service.api.RoleService;
import com.example.crowdfunding.util.Msg;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-26 15:14
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/admin/get/role.json")
    public Msg getRolesByKeyword(@RequestParam(value = "keyword", defaultValue = "")String keyword,
                                 @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum){
        PageInfo<Role> rolePageInfo = roleService.getRolesByKeyword(keyword, pageNum, 7);
        return Msg.success().add("rolePageInfo",rolePageInfo);
    }

}
