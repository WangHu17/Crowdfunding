package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-18 15:58
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String AdminLogin(Admin admin, HttpSession session){
        Admin checkAdmin = adminService.checkAdmin(admin);
        session.setAttribute(CrowdConstant.ADMIN_LOGIN_ACCOUNT, checkAdmin);
        return "redirect:/admin/to/main.html";
    }

    @RequestMapping("/admin/do/logout.html")
    public String AdminLogout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/to/login.html";
    }

}
