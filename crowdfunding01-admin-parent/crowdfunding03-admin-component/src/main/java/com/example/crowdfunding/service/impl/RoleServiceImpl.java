package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.bean.RoleExample;
import com.example.crowdfunding.mapper.RoleMapper;
import com.example.crowdfunding.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-26 15:12
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getRolesByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> roles = roleMapper.selectByKeyword(keyword);
        return new PageInfo<>(roles);
    }

    @Override
    public int addRole(String roleName) {
        return roleMapper.insert(new Role(null,roleName));
    }

    @Override
    public int updateRoleById(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int deleteRolesByIds(List<Integer> ids) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(ids);
        return roleMapper.deleteByExample(example);
    }

}
