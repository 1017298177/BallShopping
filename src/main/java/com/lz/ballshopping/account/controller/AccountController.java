package com.lz.ballshopping.account.controller;

import com.lz.ballshopping.account.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/account")
public class AccountController {

      @GetMapping("/index")
      public String index(){
            return "account/index";
      }
      @GetMapping("/home")
      public String welcome(){
            return "account/home";
      }
      @GetMapping("/login")
      public String login(){
            return "account/login";
      }
      @GetMapping("/users/userList")
      public String userList(){
            return "account/users/userList";
      }
      @GetMapping("/users/userInfo")
      public String userInfo(){
            return "account/users/userInfo";
      }
      @GetMapping("/roles/roleList")
      public String roleList(){return "account/roles/roleList";}
      @GetMapping("/permission/permissionList")
      public String permissionList(){return "account/permission/permissionList";}
      @GetMapping("/product/productList")
      public String productList(){
            return "account/product/productList";
      }
      @GetMapping("/product/productTypeList")
      public String productTypeList(){
            return "account/product/productTypeList";
      }
      @GetMapping("/product/brandList")
      public String brandList(){
            return "account/product/brandList";
      }
      @GetMapping("/product/productAdd")
      public String productAdd(){
            return "account/product/productAdd";
      }
      @GetMapping("/product/saleTotal")
      public String saleTotal(){
            return "account/product/saleTotal";
      }
      @GetMapping("/order/orderList")
      public String orderList(){
            return "account/order/orderList";
      }
      @GetMapping("/order/orderBackMoney")
      public String orderBackMoneyList(){
            return "account/order/orderBackMoney";
      }
      @GetMapping("/order/orderInfo")
      public String orderInfo(){return "account/order/orderInfo";}
      @GetMapping("/order/orderInfoProvince")
      public String orderInfoProvince(){return "account/order/orderInfoProvince";}
      @GetMapping(value = "/order/orderDetail")
      public String orderDetail(@RequestParam String orderNumber, ModelMap modelMap){
            modelMap.addAttribute("orderNumber",orderNumber);
            return "account/order/orderDetail";
      }

      @GetMapping("/403")
      public String page403(){
            return "account/commons/403";
      }


}
