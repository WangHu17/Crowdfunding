package com.example.crowdfunding.service.impl;

import com.example.crowdfunding.bean.Admin;
import com.example.crowdfunding.bean.AdminExample;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.exception.LoginAcctAlreadyInUseException;
import com.example.crowdfunding.exception.LoginAcctAlreadyInUseForUpdateException;
import com.example.crowdfunding.exception.LoginFailedException;
import com.example.crowdfunding.mapper.AdminMapper;
import com.example.crowdfunding.service.api.AdminService;
import com.example.crowdfunding.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private AdminMapper adminMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveAdmin(Admin admin) {
        String userPswd = admin.getUserPswd();
//        String md5 = CrowdUtil.md5(userPswd);
        String encodePswd = passwordEncoder.encode(userPswd); // 使用带盐值的加密算法
        admin.setUserPswd(encodePswd);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);
        admin.setCreateTime(createTime);
        try {
            adminMapper.insert(admin);
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_ACCOUNT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    //登录检查输入的账号密码
    @Override
    public Admin checkAdmin(Admin admin) {

        // 1、根据输入的用户名密码从数据库获取数据
        AdminExample example = new AdminExample();
        example.createCriteria().andLoginAcctEqualTo(admin.getLoginAcct());
        List<Admin> list = adminMapper.selectByExample(example);

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

    @Override
    public PageInfo<Admin> getAdminByKeyWord(String keyword, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.selectByKeyWord(keyword);
        return new PageInfo<>(admins);

    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdminById(Admin admin) {
        try{
            adminMapper.updateByPrimaryKeySelective(admin);
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_ACCOUNT_ALREADY_IN_USE);
            }
        }
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct) {
        AdminExample example = new AdminExample();
        example.createCriteria().andLoginAcctEqualTo(loginAcct);
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins.get(0);
    }

}
