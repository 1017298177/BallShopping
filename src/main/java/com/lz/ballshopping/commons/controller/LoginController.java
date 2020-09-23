package com.lz.ballshopping.commons.controller;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.service.LoginService;
import com.lz.ballshopping.commons.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/userInfo",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Result<UserInfo> checkUserInfoByUserNameAndPassWord(@RequestBody UserInfo userInfo){
        return loginService.checkUserInfoByUserNameAndPassWord(userInfo);
    }

    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public Result<String> registerUser(@RequestBody UserInfo userInfo){
        return loginService.registerUser(userInfo);
    }

    @PostMapping(value = "/checkUserName" )
    @ResponseBody
    public Result<String> checkUserName(@RequestParam String userName){
        return loginService.checkUserName(userName);
    }

    @GetMapping("/logout")
    public String logout(@RequestParam int flag){
        loginService.logout();
        return  flag==0?"account/login":"shopping/login";
    }




}
