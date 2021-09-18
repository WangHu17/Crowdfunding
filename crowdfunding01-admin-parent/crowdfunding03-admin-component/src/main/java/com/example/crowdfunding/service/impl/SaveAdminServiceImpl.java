package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.bean.AdminExample;
import com.example.crowdfunding.mapper.AdminMapper;
import com.example.crowdfunding.service.api.SaveAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-14 19:17
 */
@Service
public class SaveAdminServiceImpl implements SaveAdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public void saveAdmin(Admin admin) {
        mapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return mapper.selectByExample(new AdminExample());
    }
}
