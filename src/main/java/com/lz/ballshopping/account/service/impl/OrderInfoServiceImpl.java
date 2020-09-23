package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.OrderInfoDao;
import com.lz.ballshopping.account.dao.ProductDao;
import com.lz.ballshopping.account.service.OrderInfoService;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.utils.ChinaJson;
import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    OrderInfoDao orderInfoDao;
    @Autowired
    ProductDao productDao;

    @Override
    public PageInfo<OrderInfo> getAllOrderInfo(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getOrderInfoBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public List<OrderInfo> getOrdersByOrderNumber(String orderNumber) {
        return orderInfoDao.getOrdersByOrderNumber(orderNumber);
    }

    @Override
    public Result deleteOrderInfoByOrderId(int orderId) {
        orderInfoDao.deleteOrderInfoByOrderId(orderId);
        return new Result("删除成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public List<Object> getOrderStatistics(String year) {
        List<Object> lists = new ArrayList<>();
        //已付订单统计
        List<Integer> paymentCount = new ArrayList<>();
        paymentCount.add(orderInfoDao.getOneMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getTwoMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getThreeMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getFourMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getFiveMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getSixMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getSevenMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getEightMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getNineMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getTenMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getElevenMonthPaymentOrderCount(year));
        paymentCount.add(orderInfoDao.getTwelveMonthPaymentOrderCount(year));
        lists.add(paymentCount);
        //已发货订单统计
        List<Integer> sendCount = new ArrayList<>();
        sendCount.add(orderInfoDao.getOneMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getTwoMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getThreeMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getFourMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getFiveMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getSixMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getSevenMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getEightMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getNineMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getTenMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getElevenMonthSendOrderCount(year));
        sendCount.add(orderInfoDao.getTwelveMonthSendOrderCount(year));
        lists.add(sendCount);
        //今年订单总数
         lists.add(orderInfoDao.getOrderCountByYear(year));
        //今年交易成功
        lists.add(orderInfoDao.getSuccessOrderCountByYear(year));
        //今年交易失败
        lists.add(orderInfoDao.getFailOrderCountByYear(year));
        return lists;
    }

    @Override
    public List<String> getOrderYear() {
        return orderInfoDao.getOrderYear();
    }

    @Override
    public List<HashMap<String,Object>> getOrderCountByProvince() {
        List<HashMap<String,Object>> mapList  = new ArrayList<>();
        for (String province : ChinaJson.provinces) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("name",province);
            hashMap.put("value",orderInfoDao.getOrderCountByProvince(province));
            mapList.add(hashMap);
        }
        return mapList;
    }

    @Override
    public List<HashMap<String,String>> getOrderPercentByProductType() {
        List<HashMap<String,String>> list= new ArrayList<>();
        int orderCount = orderInfoDao.getOrderCount();
        DecimalFormat df = new DecimalFormat("0");//格式化小数
        List<String> productType = orderInfoDao.getOrderProductType();
        for (String type: productType) {
          HashMap<String,String> hashMap = new HashMap<>();
          int typeCount = orderInfoDao.getBallOrderCount(type);
          double ballPercent = ((float)typeCount/(float)orderCount)*100;
          hashMap.put("name",type);
          hashMap.put("value",df.format(ballPercent));
          list.add(hashMap);
        }
        return list;
    }

    @Override
    public Result<String> sendProduct(int orderId) {
        orderInfoDao.sendProduct(orderId);
        return new Result<>("发货成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public PageInfo<OrderInfo> getBackMoneyOrders(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getBackMoneyOrders(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<String> backMoney(int orderId) {
        orderInfoDao.backMoney(orderId);
        return new Result<>("退款成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public PageInfo<OrderInfo> getWaitPaymentOrder(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getWaitPaymentOrder(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<OrderInfo> getWaitSendOrder(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getWaitSendOrder(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<OrderInfo> getWaitConfirmOrder(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getWaitConfirmOrder(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<OrderInfo> getWaitCommentOrder(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(orderInfoDao.getWaitCommentOrder(searchVo)).orElse(Collections.emptyList()));
    }


}
