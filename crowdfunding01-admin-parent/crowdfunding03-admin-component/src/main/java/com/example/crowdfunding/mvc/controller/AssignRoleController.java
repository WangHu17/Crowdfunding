package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.service.api.AssignRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:06
 */
@Controller
public class AssignRoleController {

    @Autowired
    private AssignRoleService assignRoleService;

    @RequestMapping("/admin/to/assignRole.html")
    public String toAssignRolePage(@RequestParam("adminId")Integer adminId, ModelMap modelMap){
        List<Role> unAssignedRoles = assignRoleService.getUnAssignedRoles(adminId);
        List<Role> assignedRoles = assignRoleService.getAssignedRoles(adminId);
        modelMap.addAttribute("unAssignedRoles",unAssignedRoles);
        modelMap.addAttribute("assignedRoles",assignedRoles);
        return "admin-assignRole";
    }

    @RequestMapping("/admin/do/assignRole.html")
    public String doAssignRole(@RequestParam("adminId")Integer adminId,
                               @RequestParam("pageNum")Integer pageNum,
                               @RequestParam("keyword")String keyword,
                               @RequestParam(value = "roleIdList",required = false)List<Integer> roles){
        assignRoleService.assignRoles(adminId, roles);
        return "redirect:/admin/get/user.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
