package com.example.crowdfunding.mapper;

import com.example.crowdfunding.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:17
 */
public interface AssignMapper {

    //根据账号id查询未分配的角色
    List<Role> selectUnAssignedRoles(Integer adminId);

    //根据账号id查询已分配的角色
    List<Role> selectAssignedRoles(Integer adminId);

    //根据账号id删除所有已分配的角色
    void deleteAllAssignedRolesByAdminId(Integer adminId);

    //插入新的角色
    int insertRoles(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

    //根据角色id查询已分配的权限
    List<Integer> selectAssignedAuthsByRoleId(Integer roleId);

    //根据角色id删除所有已分配的权限
    void deleteAllAssignedAuthByRoleId(Integer roleId);

    //插入新的权限
    int insertAuths(@Param("roleId") Integer roleId,@Param("authIds") List<Integer> authIds);

    //根据账号id查询已分配的权限名字
    List<String> selectAuthNameByAdminId(Integer adminId);

}
