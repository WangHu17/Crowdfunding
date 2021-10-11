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

    int deleteAllAssignedRolesByAdminId(Integer adminId);

    int insertRoles(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

}
