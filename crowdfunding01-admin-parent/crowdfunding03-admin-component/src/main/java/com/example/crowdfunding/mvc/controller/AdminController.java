package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    //登录
    @RequestMapping("/admin/do/login.html")
    public String AdminLogin(Admin admin, HttpSession session) {
        Admin checkAdmin = adminService.checkAdmin(admin);
        session.setAttribute(CrowdConstant.ADMIN_LOGIN_ACCOUNT, checkAdmin);
        return "redirect:/admin/to/main.html";
    }

    //注销
    @RequestMapping("/admin/do/logout.html")
    public String AdminLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/to/login.html";
    }

    //获取用户列表
    @RequestMapping("/admin/get/user.html")
    public String getAdminByKeyWord(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.getAdminByKeyWord(keyword, pageNum, 13);
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-user";
    }

    //删除用户
    @RequestMapping("/admin/del/{id}/{pageNum}/{keyword}.html")
    public String deleteAdminById(@PathVariable("id") Integer id,
                                  @PathVariable("pageNum") Integer pageNum,
                                  @PathVariable("keyword") String keyword) {
        adminService.deleteAdminById(id);
        return "redirect:/admin/get/user.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    //添加用户
    @RequestMapping("/admin/do/add.html")
    public String addAdmin(Admin admin) {
        adminService.saveAdmin(admin);
        return "redirect:/admin/get/user.html?pageNum=" + Integer.MAX_VALUE;
    }

    //跳转到用户信息编辑页面，带上该用户对象
    @RequestMapping("/admin/to/edit.html")
    public String getAdminById(@RequestParam("id")Integer id, ModelMap modelMap) {
        Admin admin = adminService.getAdminById(id);
        modelMap.addAttribute("admin",admin);
        return "admin-user-edit";
    }

    //更新用户信息并跳转到之前的那一页
    @RequestMapping("/admin/do/update.html")
    public String updateAdminById(Admin admin, @RequestParam("pageNum")Integer pageNum, @RequestParam("keyword")String keyword){
        adminService.updateAdminById(admin);
        return "redirect:/admin/get/user.html?pageNum=" + pageNum + "&keyword=" + keyword;
    }

}
