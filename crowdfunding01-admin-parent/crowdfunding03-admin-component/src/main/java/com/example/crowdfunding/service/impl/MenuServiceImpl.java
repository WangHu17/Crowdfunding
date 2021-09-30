package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Menu;
import com.example.crowdfunding.bean.MenuExample;
import com.example.crowdfunding.mapper.MenuMapper;
import com.example.crowdfunding.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-29 17:18
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getAllMenu() {
        return menuMapper.selectByExample(new MenuExample());
    }
}
