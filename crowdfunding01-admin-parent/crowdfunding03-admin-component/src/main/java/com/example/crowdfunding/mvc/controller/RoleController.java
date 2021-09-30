package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.service.api.RoleService;
import com.example.crowdfunding.util.Msg;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/admin/add/role.json")
    public Msg addRole(@RequestParam("roleName")String roleName){
        int i = roleService.addRole(roleName);
        if (i == 1)return Msg.success();
        return Msg.fail();
    }

    @RequestMapping("/admin/update/role.json")
    public Msg updateRoleById(Role role){
        int i = roleService.updateRoleById(role);
        if (i == 1)return Msg.success();
        return Msg.fail();
    }

    @RequestMapping("/admin/delete/role.json")
    public Msg deleteRolesByIds(@RequestBody List<Integer> ids){
        int i = roleService.deleteRolesByIds(ids);
        if (i != 0) return Msg.success();
        return Msg.fail();
    }

}
