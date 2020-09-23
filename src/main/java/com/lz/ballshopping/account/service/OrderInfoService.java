package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

import java.util.HashMap;
import java.util.List;

public interface OrderInfoService {

    PageInfo<OrderInfo> getAllOrderInfo(SearchVo searchVo);

    List<OrderInfo> getOrdersByOrderNumber(String orderNumber);

    Result deleteOrderInfoByOrderId(int orderId);

    List<Object> getOrderStatistics(String year);

    List<String> getOrderYear();

    List<HashMap<String,Object>> getOrderCountByProvince();

    List<HashMap<String,String>> getOrderPercentByProductType();

    Result<String> sendProduct(int orderId);

    PageInfo<OrderInfo> getBackMoneyOrders(SearchVo searchVo);

    Result<String> backMoney(int orderId);

    PageInfo<OrderInfo> getWaitPaymentOrder(SearchVo searchVo);
    PageInfo<OrderInfo> getWaitSendOrder(SearchVo searchVo);
    PageInfo<OrderInfo> getWaitConfirmOrder(SearchVo searchVo);
    PageInfo<OrderInfo> getWaitCommentOrder(SearchVo searchVo);
}
