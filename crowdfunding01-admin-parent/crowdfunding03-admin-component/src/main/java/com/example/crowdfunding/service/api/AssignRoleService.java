package com.example.crowdfunding.service.api;


import com.example.crowdfunding.bean.Role;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:15
 */
public interface AssignRoleService {

    public List<Role> getUnAssignedRoles(Integer adminId);

    public List<Role> getAssignedRoles(Integer adminId);

    public int assignRoles(Integer adminId, List<Integer> roles);
}
