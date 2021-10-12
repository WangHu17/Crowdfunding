package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Auth;
import com.example.crowdfunding.bean.AuthExample;
import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.mapper.AssignMapper;
import com.example.crowdfunding.mapper.AuthMapper;
import com.example.crowdfunding.service.api.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:16
 */
@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignMapper assignMapper;

    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Role> getUnAssignedRoles(Integer adminId) {
        return assignMapper.getUnAssignedRoles(adminId);
    }

    @Override
    public List<Role> getAssignedRoles(Integer adminId) {
        return assignMapper.getAssignedRoles(adminId);
    }

    @Override
    public int assignRoles(Integer adminId, List<Integer> roles) {
        //先删除所有已分配的角色
        assignMapper.deleteAllAssignedRolesByAdminId(adminId);
        //再插入新的角色
        if (roles != null && roles.size() > 0) {
            return assignMapper.insertRoles(adminId, roles);
        }
        return -1;
    }

    @Override
    public List<Auth> getAllAuthList() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthsByRoleId(Integer roleId) {
        return assignMapper.getAssignedAuthsByRoleId(roleId);
    }

    @Override
    public int saveRoleIdAndAuths(Map<String, List<Integer>> map) {
        // 获取角色id
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 先删除该角色已分配的全部权限
        assignMapper.deleteAllAssignedAuthByRoleId(roleId);
        // 获取权限集合
        List<Integer> authIds = map.get("authIds");
        // 保存权限
        return assignMapper.insertAuths(roleId, authIds);
    }
}
