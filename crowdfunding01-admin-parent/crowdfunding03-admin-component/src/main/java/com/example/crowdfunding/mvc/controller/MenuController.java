package com.example.crowdfunding.mvc.controller;

import com.example.crowdfunding.bean.Menu;
import com.example.crowdfunding.service.api.MenuService;
import com.example.crowdfunding.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-29 17:19
 */
@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    //获取菜单
    @RequestMapping("/admin/get/menu.json")
    public Msg getAllMenu(){
        List<Menu> menus = menuService.getAllMenu();
        Menu root = null;

        //先将菜单的id对应存入map中，用于下面查找
        HashMap<Integer, Menu> map = new HashMap<>();
        for (Menu menu:menus){
            Integer id = menu.getId();
            map.put(id,menu);
        }

        //然后遍历菜单集合，找出根节点，及所有子节点
        for (Menu menu:menus){
            Integer pid = menu.getPid();
            //如果pid是null，那就是根节点
            if (pid == null){
                root = menu;
                continue;
            }
            //如果不是根节点，就找它的父节点
            Menu parent = map.get(pid);
            //把当前节点存入父节点的孩子集合中
            parent.getChildren().add(menu);
        }

        //最后，返回根节点即可
        return Msg.success().add("root",root);
    }

    //添加菜单节点
    @RequestMapping("/admin/add/menu.json")
    public Msg addMenu(Menu menu) {
        int i = menuService.addMenu(menu);
        if (i == 1) return Msg.success();
        return Msg.fail();
    }

}
