package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Admin;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-14 19:17
 */
public interface SaveAdminService {
    public void saveAdmin(Admin admin);

    public List<Admin> getAll();
}
