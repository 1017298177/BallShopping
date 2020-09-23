package com.lz.ballshopping.account.service.impl;

import com.lz.ballshopping.account.dao.OrderInfoDao;
import com.lz.ballshopping.account.dao.ProductDao;
import com.lz.ballshopping.account.dao.ProductSaleNumberDao;
import com.lz.ballshopping.account.dao.UserInfoDao;
import com.lz.ballshopping.account.service.StatisticsService;
import com.lz.ballshopping.commons.entity.ProductSaleNumber;
import com.lz.ballshopping.commons.entity.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {


    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductSaleNumberDao productSaleNumberDao;

    @Override
    public Map<String, Object> getStatisticsInfo() {
       Map<String, Object> mapList = new HashMap<>();
       int userCount = userInfoDao.getUserCount();
       mapList.put("userCount",userCount);
       int orderCount = orderInfoDao.getOrderCount();
       mapList.put("orderCount",orderCount);
       int productCount = productDao.getProductCount();
       mapList.put("productCount",productCount);
       int productOnCount = productDao.getProductOnCount();
       mapList.put("productOnCount",productOnCount);
       int productDownCount = productDao.getProductDownCount();
       mapList.put("productDownCount",productDownCount);
       //商品成交额，通过商品销售记录表
       double productTotalPrice  = productSaleNumberDao.getProductTotalPrice();
       mapList.put("productTotalPrice",productTotalPrice);
       int orderWaitSendCount = orderInfoDao.getOrderWaitSendCount();
       mapList.put("orderWaitSendCount",orderWaitSendCount);
       int orderWaitCommentCount = orderInfoDao.getOrderWaitCommentCount();
       mapList.put("orderWaitCommentCount",orderWaitCommentCount);
        int orderWaitPaymentCount = orderInfoDao.getOrderWaitPaymentCount();
        mapList.put("orderWaitPaymentCount",orderWaitPaymentCount);
        int orderWaitConfirmCount = orderInfoDao.getOrderWaitConfirmCount();
        mapList.put("orderWaitConfirmCount",orderWaitConfirmCount);
        return mapList;
    }

    @Override
    public List<Object> getProductSaleInfo() {
        List<Object> lists = new ArrayList<>();
        List<String> productTypes =productDao.getProductType();
        lists.add(productTypes);
        List<Object> productSaleInfos = new ArrayList<>();
        //查询每一个类型的销量
        for (String productType: productTypes) {
          HashMap<String,Object> map = new HashMap<>();
          int productSaleNumber = productSaleNumberDao.getProductCountByProductType(productType);
          map.put("value",productSaleNumber);
          map.put("name",productType);
          productSaleInfos.add(map);
        }
        lists.add(productSaleInfos);
        return lists;
    }
}
