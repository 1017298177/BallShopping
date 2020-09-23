package com.lz.ballshopping.account.controller;


import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.entity.UserRole;
import com.lz.ballshopping.account.service.UserInfoService;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping(value = "/userInfos",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    public PageInfo<UserInfo> getAllUser(@RequestBody SearchVo searchVo){
        return userInfoService.getAllUser(searchVo);
    }


    @PostMapping(value = "/userInfo/insert",consumes =MediaType.APPLICATION_JSON_VALUE )
    public  Result insertUser(@RequestBody UserInfo userInfo){
        return userInfoService.insertUser(userInfo);
    }

    @GetMapping(value = "/userInfo/{userId}")
    public UserInfo getUserInfoByUserId(@PathVariable int userId){
        return userInfoService.getUserInfoByUserId(userId);
    }

    @GetMapping(value = "/userInfo")
    public UserInfo getUserInfo(){
        return userInfoService.getUserInfo();
    }

    @PutMapping(value = "/userInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserRole> updateUserInfoRole(@RequestBody UserRole userRole){
        return userInfoService.updateUserInfoRole(userRole);
    }

    @DeleteMapping(value = "/userInfo/{userId}")
    @RequiresPermissions(value = "/api/userInfo")
    public Result<UserInfo> deleteUserByUserId(@PathVariable int userId){
        return userInfoService.deleteUserByUserId(userId);
    }

    @GetMapping(value = "/checkPassword")
    public Result<String> checkPassword(@RequestParam String oldPassword){
        return userInfoService.checkPassword(oldPassword);
    }

    @GetMapping(value = "/updatePassword")
    public Result<String> updatePassword(@RequestParam String newPassword){
        return userInfoService.updatePassword(newPassword);
    }

    @PostMapping(value = "/updateUserInfo",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.updateUserInfo(userInfo);
    }
    @GetMapping(value = "/user")
    public UserInfo getUser(){
       UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return userInfo;
    }

}
