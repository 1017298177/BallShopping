package com.lz.ballshopping.commons.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.entity.UserRole;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

public interface LoginService {



    UserInfo getUserInfoByUserName(String userName);

   Result<UserInfo> checkUserInfoByUserNameAndPassWord(UserInfo userInfo);

    void logout();


    Result<String> checkUserName(String userName);

    Result<String> registerUser(UserInfo userInfo);
}
