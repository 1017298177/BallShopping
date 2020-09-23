package com.lz.ballshopping.account.controller;

import com.lz.ballshopping.account.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StatisticsController {
  @Autowired
  private StatisticsService statisticsService;

  @PostMapping(value = "/statistics",produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String,Object> getStatisticsInfo(){
    return statisticsService.getStatisticsInfo();
  }

  @PostMapping(value = "/statistics/productSale")
  public List<Object> getProductSaleInfo(){
    return statisticsService.getProductSaleInfo();
  }



}
