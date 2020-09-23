package com.lz.ballshopping.shopping.service;

import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.Cart;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


public interface ShoppingCartService {

    Result<ShoppingCart> insertShoppingCart(ShoppingCart shoppingCart);

    List<ShoppingCart> getShoppingCarts();

    Result<String> deleteProductItem(String productId);

    Result<Object> shoppingCartSubmit(Cart cart);

    List<OrderInfo> shoppingOrders(String orderNumber);

    Result<String> shoppingOrderSubmit(OrderInfo orderInfo);
}
