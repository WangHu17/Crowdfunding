package com.example.crowdfunding.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.crowdfunding.api.MysqlRemoteService;
import com.example.crowdfunding.api.RedisRemoteService;
import com.example.crowdfunding.bean.po.MemberPO;
import com.example.crowdfunding.bean.vo.MemberLoginVO;
import com.example.crowdfunding.bean.vo.MemberVO;
import com.example.crowdfunding.config.ShortMessageProperties;
import com.example.crowdfunding.constant.CrowdConstant;
import com.example.crowdfunding.util.CrowdUtil;
import com.example.crowdfunding.util.Msg;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-10-29 17:00
 */
@Controller
public class PortalController {

    @Autowired
    private ShortMessageProperties shortMessageProperties;

    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    // 跳转首页
    @RequestMapping("/")
    public String showPortalPage() {
        return "portal";
    }

    // 发送短信验证码
    @ResponseBody
    @RequestMapping("/auth/member/send/short/message")
    public Msg sendShortMessage(@RequestParam("phoneNum") String phoneNum) {
//        Msg msg = CrowdUtil.sendShortMessage(shortMessageProperties.getHost(),
//                                             shortMessageProperties.getPath(),
//                                             shortMessageProperties.getMethod(),
//                                             shortMessageProperties.getAppcode(),
//                                             phoneNum,
//                                             shortMessageProperties.getSign(),
//                                             shortMessageProperties.getSkin());
        // 发送验证码成功
//        if (msg.getCode() == 100){
        // 发送的验证码
//            String verificationCode = (String) msg.getData().get("verificationCode");
        String verificationCode = "5517";
        // 拼接一个用于在Redis中存储数据的key
        String key = CrowdConstant.RRDIS_CODE_PREFIX + phoneNum;
        // 存入redis中
        Msg msg1 = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key, verificationCode, 15, TimeUnit.MINUTES);
        // 存入redis成功
        if (msg1.getCode() == 100) {
            return Msg.successWithMsg("发送成功");
        }
        // 存入redis失败
        else {
            return msg1;
        }
//        }
        // 发送验证码失败
//        else {
//            return msg;
//        }
    }

    // 用户注册
    @ResponseBody
    @RequestMapping("/auth/member/do/register")
    public Msg registerMember(MemberVO memberVO) {
        // 1、检查redis中是否有手机号对应的验证码
        String phoneNumber = memberVO.getPhoneNumber();
        String key = CrowdConstant.RRDIS_CODE_PREFIX + phoneNumber;
        Msg redisValueMsg = redisRemoteService.getRedisStringValueByKeyRemote(key);
        // 如果redis中没有该手机号的验证码
        if (redisValueMsg.getData().get(key) == null) {
            return Msg.failWithMsg("验证码已过期，请检查手机号是否正确或重新发送");
        }

        // 2、检查redis中的验证码是否和表单中填写的一致
        String verificationCode = memberVO.getVerificationCode();
        // 如果验证码不一致
        if (!redisValueMsg.getData().get(key).equals(verificationCode)) {
            return Msg.failWithMsg("验证码错误，请重新输入");
        }

        // 3、向数据库保存该用户信息
        // 加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userpswdBeforeEncode = memberVO.getUserpswd();
        String userpswdAfterEncode = encoder.encode(userpswdBeforeEncode);
        memberVO.setUserpswd(userpswdAfterEncode);
        MemberPO memberPO = new MemberPO();
        // 拷贝表单数据到memberPO对象中
        BeanUtils.copyProperties(memberVO, memberPO);
        // 向数据库保存memberPO对象
        Msg saveMemberPOMsg = mysqlRemoteService.saveMemberPO(memberPO);
        if (saveMemberPOMsg.getCode() == 100) {
            // 如果注册成功，删除redis中保存的验证码
            redisRemoteService.removeRedisKeyRemote(key);
        }
        return saveMemberPOMsg;
    }

    // 用户登录
    @ResponseBody
    @RequestMapping("/auth/member/do/login")
    public Msg loginMember(@RequestParam("loginacct") String loginacct,
                           @RequestParam("userpswd") String userpswd,
                           HttpSession session) {

        // 通过用户名向数据库查询
        Msg memberPOMsg = mysqlRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        Object memberPOObject = memberPOMsg.getData().get("memberPO");
        // 如果没有查询到该用户名的记录
        if (memberPOObject == null) {
            return Msg.failWithMsg(CrowdConstant.MESSAGE_NO_ADMIN_ACCOUNT);
        }
        // 将存入map中的 memberPO 转换回来
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(memberPOObject));
        MemberPO memberPO = jsonObject.toJavaObject(MemberPO.class);
        // 该用户名在数据库中的密码
        String userpswdFromDB = memberPO.getUserpswd();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 比较两个密码是否一致（必须使用matches方法进行比较，不能将前端传来的密码加密后再比较）
        boolean matches = encoder.matches(userpswd, userpswdFromDB);
        // 如果不一致
        if (!matches) {
            return Msg.failWithMsg(CrowdConstant.MESSAGE_ACCT_OR_PASSWORD_WRONG);
        }
        // 将信息封装到 MemberLoginVO 中
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());
        // 将 MemberLoginVO 保存到 session 中
        session.setAttribute(CrowdConstant.MEMBER_LOGIN_ACCOUNT, memberLoginVO);
        return Msg.success();
    }

    // 用户注销
    @RequestMapping("/auth/member/logout")
    public String logoutMember(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
