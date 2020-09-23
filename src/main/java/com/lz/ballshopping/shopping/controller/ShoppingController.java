package com.lz.ballshopping.shopping.controller;

import com.lz.ballshopping.commons.entity.OrderInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {



    @GetMapping("/login")
    public String login(){
        return "shopping/login";
    }
    @GetMapping("/index")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("userInfo", SecurityUtils.getSubject().getSession().getAttribute("userInfo"));
        return "shopping/index";
    }
    @GetMapping("/register")
    public String register(){
        return "shopping/register";
    }
    @GetMapping("/show/basketballShow")
    public String basketballShow(){
        return "shopping/show/basketball_show";
    }
    @GetMapping("/show/footballShow")
    public String footballShow(){
        return "shopping/show/football_show";
    }
    @GetMapping("/show/tennisShow")
    public String tennisShow(){
        return "shopping/show/tennis_show";
    }
    @GetMapping("/show/VolleyballShow")
    public String VolleyballShow(){
        return "shopping/show/Volleyball_show";
    }
    @GetMapping("/productSale")
    public String productSale(){
        return "shopping/productSale";
    }
    @GetMapping("/shoppingCart")
    public String shoppingCart(){
        return "shopping/shoppingCart";
    }
    @GetMapping("/productPay")
    public String productPay(String orderNumber, ModelMap modelMap){
        modelMap.addAttribute("orderNumber",orderNumber);
        return "shopping/productPay";
    }





    @GetMapping("/profile/profileWelcome")
    public String profileWelcome(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileMoney")
    public String profileMoney(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileOrder")
    public String profileOrder(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileAddress")
    public String profileAddress(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileAddressAdd")
    public String profileAddressAdd(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileAddressEdit")
    public String profileAddressEdit(@RequestParam String addressId,ModelMap modelMap){
        modelMap.addAttribute("addressId",addressId);
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileCollection")
    public String profileCollection(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileRefund")
    public String profileRefund(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileInfo")
    public String profileInfo(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileUpdatePassWordSet1")
    public String profileUpdatePassWordSet1(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileUpdatePassWordSet2")
    public String profileUpdatePassWordSet2(){
        return "shopping/profile/index";
    }
    @GetMapping("/profile/profileUpdatePassWordSet3")
    public String profileUpdatePassWordSet3(){
        return "shopping/profile/index";
    }





}
