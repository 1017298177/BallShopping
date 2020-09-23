package com.lz.ballshopping.shopping.service;

import com.lz.ballshopping.commons.entity.Product;

import java.util.List;
import java.util.Map;

public interface BallService {

    List<Product> getBasketBall();

    Product getBallInfoByProductId(String productId);

    List<Product> getTennis();

    List<Product> getFootBall();

    List<Product> getVolleyBall();
}
