package com.example.crowdfunding.mapper;

import com.example.crowdfunding.bean.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-09 16:17
 */
public interface AssignRoleMapper {

    public List<Role> getUnAssignedRoles(Integer adminId);

    public List<Role> getAssignedRoles(Integer adminId);

    public int deleteAllAssignedRolesByAdminId(Integer adminId);

    public int insertRoles(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);

}
