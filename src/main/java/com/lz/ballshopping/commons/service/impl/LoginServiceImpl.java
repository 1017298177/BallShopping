package com.lz.ballshopping.commons.service.impl;

import com.lz.ballshopping.account.dao.RoleDao;
import com.lz.ballshopping.account.dao.UserRoleDao;
import com.lz.ballshopping.account.entity.UserRole;
import com.lz.ballshopping.utils.EncryptionUtil;
import com.lz.ballshopping.commons.dao.LoginDao;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.service.LoginService;
import com.lz.ballshopping.commons.vo.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        subject.getSession().setAttribute("userInfo",null);
    }

    @Override
    public Result<String> checkUserName(String userName) {
        return loginDao.getUserInfoByUserName(userName)==null?new Result<String>("",Result.ResultStatus.SUCCESS.status):
                new Result<String>("验证码错误",Result.ResultStatus.FIALD.status);
    }

    @Override
    @Transactional
    public Result<String> registerUser(UserInfo userInfo) {
        String password = userInfo.getPassword();
        SimpleHash encryption = EncryptionUtil.encryption(userInfo.getUserName(), password);
        userInfo.setPassword(encryption.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setUserImage("/upload/user.jpg");
        loginDao.registerUser(userInfo);
        UserInfo user = loginDao.getUserInfoByUserName(userInfo.getUserName());
        UserRole userRole = new UserRole();
        Role roleId = roleDao.getRoleId();
        userRole.setRoleId(roleId.getRoleId());
        userRole.setUserId(user.getUserId());
        userRoleDao.insertUserRole(userRole);
        return new Result<String>("注册成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return loginDao.getUserInfoByUserName(userName);
    }

    @Override
    public Result<UserInfo> checkUserInfoByUserNameAndPassWord(UserInfo userInfo) {
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUserName(),userInfo.getPassword());
        token.setRememberMe(userInfo.getRememberMe());
        try {
            SecurityUtils.getSubject().login(token);
            SecurityUtils.getSubject().checkRoles();
            return new Result<>("登录成功",Result.ResultStatus.SUCCESS.status);
        }
        catch (Exception e) {
            return new Result<>("用户名或密码错误",Result.ResultStatus.FIALD.status);
        }
    }






}
