package com.lz.ballshopping.commons.controller;


import com.lz.ballshopping.commons.vo.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {


    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Result<String> errorPage403(){
        return new Result<String>("",Result.ResultStatus.FIALD.status,"/account/403");
    }


}
