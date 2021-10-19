package com.example.crowdfunding.service.api;


import com.example.crowdfunding.bean.Auth;
import com.example.crowdfunding.bean.Role;

import java.util.List;
import java.util.Map;

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

    List<Integer> getAssignedAuthsByRoleId(Integer roleId);

    int saveRoleIdAndAuths(Map<String, List<Integer>> map);

    List<String> getAuthNameByAdminId(Integer adminId);
}
