package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.RoleDao;
import com.lz.ballshopping.account.dao.UserInfoDao;
import com.lz.ballshopping.account.dao.UserRoleDao;
import com.lz.ballshopping.account.entity.UserRole;
import com.lz.ballshopping.account.service.UserInfoService;
import com.lz.ballshopping.utils.EncryptionUtil;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<UserInfo> getAllUser(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
         return  new PageInfo<UserInfo>(Optional.ofNullable(userInfoDao.getAllUser(searchVo)).orElse(Collections.emptyList()));
    }


    @Override
    public Result insertUser(UserInfo userInfo) {
//        判断用户是否存在
        UserInfo userInfoByUserName = userInfoDao.getUserInfoByUserName(userInfo.getUserName());
        if (userInfoByUserName != null) {
            return new Result("对象已存在",
                    Result.ResultStatus.FIALD.status);
        }
        //插入用户
        SimpleHash encryption = EncryptionUtil.encryption(userInfo.getUserName(), userInfo.getPassword());
        userInfo.setPassword(encryption.toString());
        userInfo.setCreateTime(new Date());
        userInfoDao.insertUser(userInfo);
        return new Result("插入成功！",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public UserInfo getUserInfoByUserId(int userId) {
        return userInfoDao.getUserInfoByUserId(userId);
    }

    @Override
    public Result<UserRole> updateUserInfoRole(UserRole userRole) {
        userRoleDao.deleteByUserId(userRole.getUserId());
        userRoleDao.insertUserRole(userRole);
        return new Result<>("修改成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<UserInfo> deleteUserByUserId(int userId) {
        userRoleDao.deleteByUserId(userId);
        userInfoDao.deleteUserInfoByuserId(userId);
        return new Result<>("删除成功",Result.ResultStatus.SUCCESS.status);
    }


    @Override
    public Result<String> checkPassword(String oldPassword) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        SimpleHash encryption = EncryptionUtil.encryption(userInfo.getUserName(), oldPassword);
        if(encryption.toString().equals(userInfo.getPassword())){
            return new Result<String>("原密码正确",Result.ResultStatus.SUCCESS.status);
        }
        return new Result<String>("原密码不正确",Result.ResultStatus.FIALD.status);
    }

    @Override
    public Result<String> updatePassword(String newPassword) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        SimpleHash encryption = EncryptionUtil.encryption(userInfo.getUserName(), newPassword);
        userInfo.setPassword(encryption.toString());
        userInfoDao.updatePassword(userInfo);
        return new Result<String>("修改成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> updateUserInfo(UserInfo userInfo) {
        userInfoDao.updateUserInfo(userInfo);
        SecurityUtils.getSubject().getSession().setAttribute("userInfo",userInfoDao.getUserInfo(userInfo.getUserId()));
        return new Result<String>("修改成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public UserInfo getUserInfo() {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return userInfoDao.getUserInfo(userInfo.getUserId());
    }


}
