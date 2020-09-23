package com.lz.ballshopping.shopping.controller;

import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.Cart;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import com.lz.ballshopping.shopping.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value = "/shoppingCart",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<ShoppingCart> insertShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return shoppingCartService.insertShoppingCart(shoppingCart);
    }

    @GetMapping(value = "/shoppingCarts")
    public List<ShoppingCart> getShoppingCarts(){
       return shoppingCartService.getShoppingCarts();
    }

    @DeleteMapping(value = "/shoppingCart/{productId}")
    public Result<String> deleteProductItem(@PathVariable String productId){
        return shoppingCartService.deleteProductItem(productId);
    }
    @PostMapping(value = "/shoppingCartSubmit",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Object> shoppingCartSubmit(@RequestBody Cart cart){
        return shoppingCartService.shoppingCartSubmit(cart);
    }

    @PostMapping(value = "/shoppingOrder/{orderNumber}")
    public List<OrderInfo> shoppingOrders(@PathVariable String orderNumber){
       return shoppingCartService.shoppingOrders(orderNumber);
    }

    @PostMapping(value = "/shoppingOrderSubmit",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> shoppingOrderSubmit(@RequestBody OrderInfo orderInfo){
        return shoppingCartService.shoppingOrderSubmit(orderInfo);
    }

}
