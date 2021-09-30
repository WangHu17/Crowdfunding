package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Menu;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-29 17:18
 */
public interface MenuService {

    public List<Menu> getAllMenu();

    public int addMenu(Menu menu);

}
