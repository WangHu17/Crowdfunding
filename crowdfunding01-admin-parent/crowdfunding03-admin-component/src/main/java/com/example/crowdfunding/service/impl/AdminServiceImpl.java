package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.bean.AdminExample;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.exception.LoginFailedException;
import com.example.crowdfunding.mapper.AdminMapper;
import com.example.crowdfunding.service.api.AdminService;
import com.example.crowdfunding.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-14 19:17
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;

    @Override
    public void saveAdmin(Admin admin) {
        mapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return mapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin checkAdmin(Admin admin) {

        // 1、根据输入的用户名密码从数据库获取数据
        AdminExample example = new AdminExample();
        example.createCriteria().andLoginAcctEqualTo(admin.getLoginAcct());
        List<Admin> list = mapper.selectByExample(example);

        // 2、判断是否有此账号
        if (list == null || list.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_NO_ADMIN_ACCOUNT);
        }

        // 3、判断是否有多个此账号
        if (list.size() > 1){
            throw new RuntimeException(CrowdConstant.MESSAGE_ADMIN_ACCOUNT_IS_NOT_UNIQUE);
        }

        Admin adminFromDB = list.get(0);

        if (adminFromDB == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_NO_ADMIN_ACCOUNT);
        }

        // 数据库中的密码
        String userPswdDB = adminFromDB.getUserPswd();

        // 输入的密码经过md5加密后的字符串
        String userPswd = CrowdUtil.md5(admin.getUserPswd());

        // 比较密码是否正确
        if (!Objects.equals(userPswd,userPswdDB)){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        return adminFromDB;
    }
}
