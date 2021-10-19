package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Admin;
import com.github.pagehelper.PageInfo;

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

    public PageInfo<Admin> getAdminByKeyWord(String keyword, Integer pageNum, Integer pageSize);

    public void deleteAdminById(Integer id);

    public Admin getAdminById(Integer id);

    public void updateAdminById(Admin admin);

    //根据账号查询admin
    public Admin getAdminByLoginAcct(String loginAcct);

}
