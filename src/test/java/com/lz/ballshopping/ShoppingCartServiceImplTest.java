package com.lz.ballshopping;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.shopping.dao.ShoppingCartDao;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import com.lz.ballshopping.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ShoppingCartServiceImplTest {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Test
    public void getShoppingCarts() {
//        for (ShoppingCart shoppingCart : shoppingCarts) {
//            System.out.println(shoppingCart);
//        }

    }
}