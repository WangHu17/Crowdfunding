package com.example.crowdfunding.mvc.config;

import com.example.crowdfunding.bean.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-18 16:14
 */
public class SecurityAdmin extends User {

    private static final long serialVersionUID = 1L;

    private Admin originalAdmin;

    public SecurityAdmin(Admin admin, List<GrantedAuthority> authorities) {
        super(admin.getLoginAcct(), admin.getUserPswd(), authorities);
        this.originalAdmin = admin;
        this.originalAdmin.setUserPswd(null); // 擦除密码
    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
