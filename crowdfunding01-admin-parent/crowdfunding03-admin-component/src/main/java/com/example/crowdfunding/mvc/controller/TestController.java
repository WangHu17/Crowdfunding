package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.bean.TestAjax;
import com.example.crowdfunding.service.api.SaveAdminService;
import com.example.crowdfunding.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-15 16:24
 */
@Controller
public class TestController {

    @Autowired
    private SaveAdminService adminService;

    //接收复杂对象

    @ResponseBody
    @RequestMapping("/send/complicateObject.json")
    public ResultEntity<TestAjax> testAjax1(@RequestBody TestAjax testAjax){
        System.out.println(testAjax);
        return ResultEntity.successWithData(testAjax);
    }

    @ResponseBody
    @RequestMapping("/send/array.html")
    public String testAjax(@RequestBody List<Integer> array){
        for (Integer num:array){
            System.out.println(num);
        }
        return "success";
    }

    @RequestMapping("/test/ssm.html")
    public String testSSM(ModelMap modelMap){
        List<Admin> admins = adminService.getAll();
        modelMap.addAttribute("admins",admins);
        return "testSuccess";
    }

}
