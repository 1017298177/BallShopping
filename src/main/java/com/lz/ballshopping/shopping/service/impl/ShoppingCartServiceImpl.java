package com.lz.ballshopping.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.lz.ballshopping.account.dao.OrderInfoDao;
import com.lz.ballshopping.account.dao.ProductDao;
import com.lz.ballshopping.account.dao.ProductSaleNumberDao;
import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.entity.ProductSaleNumber;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.dao.CardInfoDao;
import com.lz.ballshopping.shopping.dao.ShoppingCartDao;
import com.lz.ballshopping.shopping.entity.CardInfo;
import com.lz.ballshopping.shopping.entity.Cart;
import com.lz.ballshopping.shopping.entity.ShoppingCart;
import com.lz.ballshopping.shopping.service.ShoppingCartService;
import com.lz.ballshopping.utils.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.HTMLDocument;
import java.util.*;


@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private ProductSaleNumberDao productSaleNumberDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CardInfoDao cardInfoDao;

    @Override
    public Result<ShoppingCart> insertShoppingCart(ShoppingCart shoppingCart) {
        String shoppingId = UUID.randomUUID().toString().substring(0, 6).replace("-", "");
        if (shoppingCart.getProductId() == "" || shoppingCart.getProductId() == null) {
            String productId = UUID.randomUUID().toString().substring(0, 8).replace("-", "");
            shoppingCart.setProductId(productId);
        }
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        shoppingCart.setShoppingId(shoppingId);
        shoppingCart.setUserName(user.getUserName());
        shoppingCart.setProductTotalPrice(shoppingCart.getProductPrice() * shoppingCart.getProductNumber());
        String cart = "user:" + user.getUserName();
        String item = "item:" + shoppingCart.getProductId();
        if (!redisUtil.hHasKey(cart, item)) {
            redisUtil.hset(cart, item, shoppingCart);
        } else {
//            反序列化JSON
            ShoppingCart shoppingCart1 = JSON.parseObject(redisUtil.hget(cart, item).toString(), ShoppingCart.class);
            shoppingCart1.setProductNumber(shoppingCart.getProductNumber());
            shoppingCart1.setProductTotalPrice(shoppingCart.getProductNumber() * shoppingCart1.getProductPrice());
            redisUtil.hset(cart, item, shoppingCart1);
        }
        return new Result<>("加入购物车成功", Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public List<ShoppingCart> getShoppingCarts() {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        String cart = "user:" + user.getUserName();
        if (!redisUtil.hasKey(cart)) {
            List<ShoppingCart> shoppingCarts = shoppingCartDao.getShoppingCart("lcm");
            if (shoppingCarts != null || shoppingCarts.size() > 0) {
                for (ShoppingCart shoppingCart : shoppingCarts) {
                    String item = "item:" + shoppingCart.getProductId();
                    if (!redisUtil.hHasKey(cart, item)) {
                        redisUtil.hset(cart, item, shoppingCart);
                    }
                }
            }
        }
        Map<Object, Object> hmget = redisUtil.hmget(cart);
        List<ShoppingCart> lists = new ArrayList<>();
        for (Object key : hmget.keySet()) {
            lists.add(JSON.parseObject(hmget.get(key).toString(), ShoppingCart.class));
        }
        return lists;
    }

    @Override
    public Result<String> deleteProductItem(String productId) {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        String cart = "user:" + user.getUserName();
        String item = "item:" + productId;
        redisUtil.hdel(cart, item);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserName(user.getUserName());
        shoppingCart.setProductId(productId);
        shoppingCartDao.deleteProductItem(shoppingCart);
        return new Result<String>("删除成功", Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<Object> shoppingCartSubmit(Cart cart) {
        String orderNumber = UUID.randomUUID().toString().substring(0, 8).replace("-", "");
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        List<ShoppingCart> items = cart.getItems();
        String cartName = "user:" + user.getUserName();
        Date dt = new Date();
        String year = String.format("%tY", dt);
        String mon = String.format("%tm", dt);
        String day = String.format("%td", dt);
        for (ShoppingCart shoppingCart : items) {
            String item = "item:" + shoppingCart.getProductId();
            ShoppingCart hget = JSON.parseObject(redisUtil.hget(cartName, item).toString(), ShoppingCart.class);
            hget.setProductNumber(shoppingCart.getProductNumber());
            hget.setProductTotalPrice(shoppingCart.getProductTotalPrice());
            redisUtil.hset(cartName, item, hget);
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderProductId(shoppingCart.getProductId());
            orderInfo.setOrderNumber(orderNumber);
            orderInfo.setOrderProductImage(hget.getProductMainimage());
            orderInfo.setOrderUserName(user.getRealName());
            orderInfo.setOrderProductType(hget.getProductType());
            orderInfo.setOrderProductPrice(hget.getProductPrice());
            orderInfo.setOrderProductTotalPrice(shoppingCart.getProductTotalPrice());
            orderInfo.setOrderProductNumbers(shoppingCart.getProductNumber());
            orderInfo.setOrderProductName(hget.getProductTitle());
            orderInfo.setOrderTradingTimeYear(year);
            orderInfo.setOrderPhoneNumber(user.getTelPhone());
            orderInfo.setOrderTradingTimeDetail(mon + "-" + day);
            orderInfoDao.insertNotPaymentOrder(orderInfo);
        }
        return new Result<Object>("下单成功", Result.ResultStatus.SUCCESS.status, orderNumber);
    }

    @Override
    public List<OrderInfo> shoppingOrders(String orderNumber) {
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNumber(orderNumber);
        orderInfo.setOrderUserName(userInfo.getRealName());
        return Optional.ofNullable(orderInfoDao.getShoppingOrders(orderInfo)).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Result<String> shoppingOrderSubmit(OrderInfo orderInfo) {
        if("内蒙古自治区".equals(orderInfo.getOrderProvince())||"黑龙江省".equals(orderInfo.getOrderProvince())){
           orderInfo.setOrderProvince(orderInfo.getOrderProvince().substring(0,3));
        }else {
           orderInfo.setOrderProvince(orderInfo.getOrderProvince().substring(0,2));
        }
        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        String key = "user:" + userInfo.getUserName();
        if (orderInfo.getProductStr().contains(",")) {
            String[] split = orderInfo.getProductStr().split(",");
            String mainId = null;
            int number = 0;
            double totalPrice = 0.0;
            for (String productId : split) {
                String item = "item:" + productId;
                ShoppingCart shoppingCart = JSON.parseObject(redisUtil.hget(key, item).toString(), ShoppingCart.class);
                orderInfo.setOrderProductId(shoppingCart.getProductMainId());
                mainId = shoppingCart.getProductMainId();
                number += shoppingCart.getProductNumber();
                totalPrice +=shoppingCart.getProductTotalPrice();
                ProductSaleNumber productSaleNumber = new ProductSaleNumber();
                if(productSaleNumberDao.getProductSaleNumberByProductId(shoppingCart.getProductId())==null){
                    productSaleNumber.setSaleCount(shoppingCart.getProductNumber());
                    productSaleNumber.setSaleProductTotalPrice(shoppingCart.getProductTotalPrice());
                    productSaleNumber.setSaleProductId(shoppingCart.getProductId());
                    productSaleNumber.setSaleProductType(shoppingCart.getProductType());
                    productSaleNumberDao.insertProductSaleNumber(productSaleNumber);
                }else {
                  ProductSaleNumber productSaleNumber1 = productSaleNumberDao.getProductSaleNumberByProductId(shoppingCart.getProductId());
                  productSaleNumber1.setSaleProductTotalPrice(productSaleNumber1.getSaleProductTotalPrice()+shoppingCart.getProductTotalPrice());
                  productSaleNumber1.setSaleCount(productSaleNumber1.getSaleCount()+shoppingCart.getProductNumber());
                  productSaleNumberDao.updateProductSaleNumber(productSaleNumber1);
                }
                redisUtil.hdel(key, item);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("productId", mainId);
            map.put("number", number);
            //修改库存
            productDao.updateProductStockByProductId(map);
            //修改订单状态，地址
            orderInfoDao.updateOrderInfoByOrderNumber(orderInfo);
            //减去金额
            CardInfo cardInfo = orderInfo.getCardInfo();
            cardInfo.setUserName(userInfo.getUserName());
            cardInfo.setCardMoney(cardInfo.getCardMoney()-totalPrice);
            cardInfoDao.updateMoney(cardInfo);
            return new Result<String>("结算成功",Result.ResultStatus.SUCCESS.status);
        }else {
            String item = "item:" + orderInfo.getProductStr();
            ShoppingCart shoppingCart = JSON.parseObject(redisUtil.hget(key, item).toString(), ShoppingCart.class);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("productId", shoppingCart.getProductMainId());
            map.put("number", shoppingCart.getProductNumber());
            productDao.updateProductStockByProductId(map);
            //修改订单状态，地址
            orderInfoDao.updateOrderInfoByOrderNumber(orderInfo);
            //改变销量统计
            ProductSaleNumber productSaleNumber = new ProductSaleNumber();
            if(productSaleNumberDao.getProductSaleNumberByProductId(shoppingCart.getProductId())==null){
                productSaleNumber.setSaleCount(shoppingCart.getProductNumber());
                productSaleNumber.setSaleProductTotalPrice(shoppingCart.getProductTotalPrice());
                productSaleNumber.setSaleProductId(shoppingCart.getProductId());
                productSaleNumber.setSaleProductType(shoppingCart.getProductType());
                productSaleNumberDao.insertProductSaleNumber(productSaleNumber);
            }else {
                ProductSaleNumber productSaleNumber1 = productSaleNumberDao.getProductSaleNumberByProductId(shoppingCart.getProductId());
                productSaleNumber1.setSaleProductTotalPrice(productSaleNumber1.getSaleProductTotalPrice()+shoppingCart.getProductTotalPrice());
                productSaleNumber1.setSaleCount(productSaleNumber1.getSaleCount()+shoppingCart.getProductNumber());
                productSaleNumberDao.updateProductSaleNumber(productSaleNumber1);
            }
            //清空购物车中的商品
            redisUtil.hdel(key, item);
            //减去金额
            CardInfo cardInfo = orderInfo.getCardInfo();
            cardInfo.setUserName(userInfo.getUserName());
            cardInfo.setCardMoney(cardInfo.getCardMoney()-shoppingCart.getProductTotalPrice());
            cardInfoDao.updateMoney(cardInfo);
            return new Result<String>("结算成功",Result.ResultStatus.SUCCESS.status);
        }
    }
}
