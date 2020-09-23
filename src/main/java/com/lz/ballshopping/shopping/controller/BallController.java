package com.lz.ballshopping.shopping.controller;

import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.shopping.service.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BallController {

    @Autowired
    private BallService basketBallService;

    @GetMapping(value = "/product/basketBall")
    @ResponseBody
    public List<Product> getBasketBall(){
        return basketBallService.getBasketBall();
    }


    @GetMapping(value = "/product/tennis")
    @ResponseBody
    public List<Product> getTennis(){
        return basketBallService.getTennis();
    }

    @GetMapping(value = "/product/footBall")
    @ResponseBody
    public List<Product> getFootBall(){
        return basketBallService.getFootBall();
    }

    @GetMapping(value = "/product/volleyBall")
    @ResponseBody
    public List<Product> getVolleyBall(){
        return basketBallService.getVolleyBall();
    }

    @GetMapping(value = "/product/ballAndImg/{productId}")
    @ResponseBody
    public Product getBallInfoByProductId(@PathVariable String productId){
        return basketBallService.getBallInfoByProductId(productId);
    }


    @GetMapping(value = "/ball/{productId}")
    public String productSalePage(@PathVariable String productId, ModelMap modelMap){
        modelMap.addAttribute("productId",productId);
        return "shopping/productSale";
    }




}
