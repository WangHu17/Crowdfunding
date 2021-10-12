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

    List<Role> getUnAssignedRoles(Integer adminId);

    List<Role> getAssignedRoles(Integer adminId);

    void deleteAllAssignedRolesByAdminId(Integer adminId);

    int insertRoles(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

    List<Integer> getAssignedAuthsByRoleId(Integer roleId);

    void deleteAllAssignedAuthByRoleId(Integer roleId);

    int insertAuths(@Param("roleId") Integer roleId,@Param("authIds") List<Integer> authIds);
}
