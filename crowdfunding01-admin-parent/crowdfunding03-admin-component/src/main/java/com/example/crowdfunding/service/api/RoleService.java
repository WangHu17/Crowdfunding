package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Role;
import com.github.pagehelper.PageInfo;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-26 15:06
 */
public interface RoleService {

    public PageInfo<Role> getRolesByKeyword(String keyword, Integer pageNum, Integer pageSize);

}
