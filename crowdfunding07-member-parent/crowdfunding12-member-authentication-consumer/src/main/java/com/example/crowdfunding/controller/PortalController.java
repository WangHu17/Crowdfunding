package com.example.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-29 17:00
 */
@Controller
public class PortalController {

    @RequestMapping("/")
    public String showPortalPage() {
        return "portal";
    }

}
