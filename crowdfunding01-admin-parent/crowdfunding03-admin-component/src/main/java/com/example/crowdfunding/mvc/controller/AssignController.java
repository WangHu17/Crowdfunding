package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Auth;
import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.service.api.AssignService;
import com.example.crowdfunding.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:06
 */
@Controller
public class AssignController {

    @Autowired
    private AssignService assignService;

    // 去到角色分配页面，带上已分配和未分配的角色信息
    @RequestMapping("/admin/to/assignRole.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap) {
        List<Role> unAssignedRoles = assignService.getUnAssignedRoles(adminId);
        List<Role> assignedRoles = assignService.getAssignedRoles(adminId);
        modelMap.addAttribute("unAssignedRoles", unAssignedRoles);
        modelMap.addAttribute("assignedRoles", assignedRoles);
        return "admin-assignRole";
    }

    // 响应角色分配请求
    @RequestMapping("/admin/do/assignRole.html")
    public String doAssignRole(@RequestParam("adminId") Integer adminId,
                               @RequestParam("pageNum") Integer pageNum,
                               @RequestParam("keyword") String keyword,
                               @RequestParam(value = "roleIdList", required = false) List<Integer> roles) {
        assignService.assignRoles(adminId, roles);
        return "redirect:/admin/get/user.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    // 返回全部权限信息
    @ResponseBody
    @RequestMapping("/admin/get/all/auth.json")
    public Msg getAllAuthList() {
        List<Auth> authList = assignService.getAllAuthList();
        return Msg.success().add("authList", authList);
    }

    // 根据角色id返回已分配权限
    @ResponseBody
    @RequestMapping("/admin/get/assigned/auth.json")
    public Msg getAssignedAuthsByRoleId(@RequestParam("roleId") Integer roleId) {
        List<Integer> assignedAuths = assignService.getAssignedAuthsByRoleId(roleId);
        return Msg.success().add("assignedAuths", assignedAuths);
    }

    // 响应权限分配请求
    @ResponseBody
    @RequestMapping("/admin/do/assign/auth.json")
    public Msg assignAuths(@RequestBody Map<String, List<Integer>> map) {
        int i = assignService.saveRoleIdAndAuths(map);
        if (i != 0) return Msg.success();
        return Msg.fail();
    }

}
