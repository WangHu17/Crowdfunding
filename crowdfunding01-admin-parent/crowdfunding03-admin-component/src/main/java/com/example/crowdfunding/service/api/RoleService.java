package com.example.crowdfunding.service.api;

import com.example.crowdfunding.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-26 15:06
 */
public interface RoleService {

    public PageInfo<Role> getRolesByKeyword(String keyword, Integer pageNum, Integer pageSize);

    public int addRole(String roleName);

    public int updateRoleById(Role role);

    public int deleteRolesByIds(List<Integer> ids);
}
