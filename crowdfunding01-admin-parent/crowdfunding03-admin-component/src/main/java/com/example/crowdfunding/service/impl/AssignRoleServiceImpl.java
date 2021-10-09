package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.mapper.AssignRoleMapper;
import com.example.crowdfunding.service.api.AssignRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:16
 */
@Service
public class AssignRoleServiceImpl implements AssignRoleService {

    @Autowired
    private AssignRoleMapper assignRoleMapper;

    @Override
    public List<Role> getUnAssignedRoles(Integer adminId) {
        return assignRoleMapper.getUnAssignedRoles(adminId);
    }

    @Override
    public List<Role> getAssignedRoles(Integer adminId) {
        return assignRoleMapper.getAssignedRoles(adminId);
    }

    @Override
    public int assignRoles(Integer adminId, List<Integer> roles) {
        //先删除所有已分配的角色
        assignRoleMapper.deleteAllAssignedRolesByAdminId(adminId);
        //再插入新的角色
        if (roles != null && roles.size() > 0) {
            return assignRoleMapper.insertRoles(adminId, roles);
        }
        return -1;
    }
}
