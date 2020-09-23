package com.lz.ballshopping.account.service;


import java.util.List;
import java.util.Map;

public interface StatisticsService {
    Map<String, Object> getStatisticsInfo();

    List<Object> getProductSaleInfo();
}
