package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.UserInfoDao;
import com.lz.ballshopping.account.entity.UserRole;
import com.lz.ballshopping.commons.entity.ProductSaleNumber;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    PageInfo<UserInfo> getAllUser(SearchVo searchVo);


    Result insertUser(UserInfo userInfo);

    UserInfo getUserInfoByUserId(int userId);

    Result<UserRole> updateUserInfoRole(UserRole userRole);

    Result<UserInfo> deleteUserByUserId(int userId);


    Result<String> checkPassword(String oldPassword);

    Result<String> updatePassword(String newPassword);

    Result<String> updateUserInfo(UserInfo userInfo);

    UserInfo getUserInfo();
}
