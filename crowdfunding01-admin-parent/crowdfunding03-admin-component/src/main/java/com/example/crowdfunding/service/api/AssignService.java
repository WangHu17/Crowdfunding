package com.example.crowdfunding.service.api;


import com.example.crowdfunding.bean.Auth;
import com.example.crowdfunding.bean.Role;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:15
 */
public interface AssignService {

    List<Role> getUnAssignedRoles(Integer adminId);

    List<Role> getAssignedRoles(Integer adminId);

    int assignRoles(Integer adminId, List<Integer> roles);

    List<Auth> getAllAuthList();
}
