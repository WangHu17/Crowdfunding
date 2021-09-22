package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Admin;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-14 19:17
 */
public interface AdminService {
    public void saveAdmin(Admin admin);

    public List<Admin> getAll();

    public Admin checkAdmin(Admin admin);
}
