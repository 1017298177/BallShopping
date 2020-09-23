package com.lz.ballshopping.account.controller;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.service.OrderInfoService;
import com.lz.ballshopping.commons.entity.OrderInfo;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;

    /*
     * 所有订单
     * 127.0.0.1/api/orders
     * {"currentPage":"1","pageSize":"5"}
     * */
    @PostMapping(value = "/orders",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json")
    PageInfo<OrderInfo> getAllOrderInfo(@RequestBody SearchVo searchVo) {
        return orderInfoService.getAllOrderInfo(searchVo);
    }
    @PostMapping(value = "/orders/backMoney",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json")
    PageInfo<OrderInfo> getBackMoneyOrders(@RequestBody SearchVo searchVo) {
        return orderInfoService.getBackMoneyOrders(searchVo);
    }

    @PostMapping(value = "/orders/statistics")
    public List<Object> getOrderStatistics(@RequestParam String year){
        return orderInfoService.getOrderStatistics(year);
    }

    /*根据订单id查询订单
     * 127.0.0.1/api/order/1
     * */
    @DeleteMapping(value = "/order/{orderId}")
    public Result deleteOrderInfoByOrderId(@PathVariable int orderId) {
        return orderInfoService.deleteOrderInfoByOrderId(orderId);
    }

    @GetMapping(value = "/order/year")
    public List<String> getOrderYear(){
        return orderInfoService.getOrderYear();
    }

    @PostMapping(value = "/order/province")
    public List<HashMap<String,Object>> getOrderCountByProvince(){
        return orderInfoService.getOrderCountByProvince();
    }

    @GetMapping(value = "/order/percent")
    public List<HashMap<String,String>> getOrderPercentByProductType(){
        return orderInfoService.getOrderPercentByProductType();
    }
    @PutMapping(value = "/order/{orderId}")
    public Result<String> sendProduct(@PathVariable int orderId){
        return orderInfoService.sendProduct(orderId);
    }
    @PutMapping(value = "/order/backMoney/{orderId}")
    public Result<String> backMoney(@PathVariable int orderId){
        return orderInfoService.backMoney(orderId);
    }
    @GetMapping(value = "/orders/{orderNumber}")
    public List<OrderInfo> getOrdersByOrderNumber(@PathVariable String orderNumber){
       return orderInfoService.getOrdersByOrderNumber(orderNumber);
    }

    @PostMapping(value = "/waitPaymentOrder",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json" )
    public PageInfo<OrderInfo> getWaitPaymentOrder(@RequestBody SearchVo searchVo) {
        return orderInfoService.getWaitPaymentOrder(searchVo);
    }

    @PostMapping(value = "/waitSendOrder",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json" )
    public PageInfo<OrderInfo> getWaitSendOrder(@RequestBody SearchVo searchVo) {
        return orderInfoService.getWaitSendOrder(searchVo);
    }

    @PostMapping(value = "/waitConfirmOrder",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json" )
    public PageInfo<OrderInfo> getWaitConfirmOrder(@RequestBody SearchVo searchVo) {
        return orderInfoService.getWaitConfirmOrder(searchVo);
    }

    @PostMapping(value = "/waitCommentOrder",consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json" )
    public PageInfo<OrderInfo> getWaitCommentOrder(@RequestBody SearchVo searchVo) {
        return orderInfoService.getWaitCommentOrder(searchVo);
    }



}
