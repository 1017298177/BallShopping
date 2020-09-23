package com.lz.ballshopping.config;

import com.alibaba.fastjson.JSON;
import com.lz.ballshopping.account.dao.UserInfoDao;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.shopping.dao.ShoppingCartDao;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import com.lz.ballshopping.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StaticSchedule {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserInfoDao userInfoDao;

    //*/5 * * * * ? 每隔5s执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void insertShoppingCart() {
        List<String> userNames = userInfoDao.getUserName();
        for (String userName : userNames) {
            String cart = "user:" + userName;
            Map<Object, Object> hmget = redisUtil.hmget(cart);
            List<ShoppingCart> lists = new ArrayList<>();
            for (Object key : hmget.keySet()) {
                lists.add(JSON.parseObject(hmget.get(key).toString(), ShoppingCart.class));
            }
            for (ShoppingCart list : lists) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setProductId(list.getProductId());
                shoppingCart.setUserName(list.getUserName());
                shoppingCartDao.deleteProductItem(shoppingCart);
                shoppingCartDao.insertShoppingCart(list);
                redisUtil.hdel(cart, "item:" + list.getProductId());
            }
        }

    }


}
