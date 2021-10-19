package com.example.crowdfunding.mvc.config;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.bean.Role;
import com.example.crowdfunding.service.api.AdminService;
import com.example.crowdfunding.service.api.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-18 15:56
 */
@Component
public class CrowdUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AssignService assignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据账号查询admin
        Admin admin = adminService.getAdminByLoginAcct(username);
        Integer adminId = admin.getId();
        // 根据 adminId 查询角色信息
        List<Role> assignedRoles = assignService.getAssignedRoles(adminId);
        // 根据 adminId 查询权限信息
        List<String> assignedAuthNames = assignService.getAuthNameByAdminId(adminId);
        // 创建集合对象用来存储 GrantedAuthority
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 遍历 assignedRoles 存入角色信息
        for (Role role:assignedRoles){
            // 注意：要加前缀！
            String roleName = "ROLE_" + role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        // 遍历 assignedAuthNames 存入权限信息
        for (String authName:assignedAuthNames){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }

        return new SecurityAdmin(admin,authorities);
    }

}
