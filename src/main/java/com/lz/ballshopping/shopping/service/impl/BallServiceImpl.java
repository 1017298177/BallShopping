package com.lz.ballshopping.shopping.service.impl;

import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.shopping.dao.BallDao;
import com.lz.ballshopping.shopping.service.BallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallServiceImpl implements BallService {

    @Autowired
    private BallDao basketBallDao;

    @Override
    public List<Product> getBasketBall() {
        return basketBallDao.getBasketBall();
    }

    @Override
    public Product getBallInfoByProductId(String productId) {
        return   basketBallDao.getBallInfoByProductId(productId);
    }

    @Override
    public List<Product> getTennis() {
        return basketBallDao.getTennis();
    }

    @Override
    public List<Product> getFootBall() {
        return basketBallDao.getFootBall();
    }

    @Override
    public List<Product> getVolleyBall() {
        return basketBallDao.getVolleyBall();
    }
}
