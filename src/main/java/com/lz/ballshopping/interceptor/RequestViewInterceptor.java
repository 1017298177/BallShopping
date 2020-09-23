package com.lz.ballshopping.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestViewInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         //不返回页面，或者是重定向页面就不做操作
        if(modelAndView==null || modelAndView.getViewName().startsWith("redirect")){
              return;
         }
        String path = request.getServletPath();
        //先获取template值，如果为空再给template设置值
        String  template = (String) modelAndView.getModelMap().get("template");
        if(StringUtils.isBlank(template)){
            if(path.startsWith("/")){
                //将/取消，application.properties 已经配置过 ——>classpath:/templates/
                path = path.substring(1);
            }
            //给碎片组装器里的 内容部分设置组装的 页面(页面路径)
            modelAndView.getModelMap().addAttribute("template",path.toLowerCase());
        }
        HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.preHandle(request,response,handler);
    }
}
