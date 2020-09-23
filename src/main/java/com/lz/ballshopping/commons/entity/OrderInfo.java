package com.lz.ballshopping.commons.entity;

import com.lz.ballshopping.shopping.entity.CardInfo;

import java.io.Serializable;

/**
 * (OrderInfo)实体类
 *
 * @author makejava
 * @since 2020-08-29 10:37:30
 */
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -81258795002899257L;
    /**
    * 订单id
    */
    private Integer orderId;
    /**
    * 商品数目:买了几个商品
    */
    private Integer orderProductNumbers;
    /**
    * 产品名称
    */
    private String orderProductName;
    /**
    * 此订单成交额
    */
    private Double orderProductTotalPrice;
    /**
    * 快递公司
    */
    private String expressDeliveryCompany;
    /**
    * 收货地址
    */
    private String orderAddress;
    /**
    * 买家的用户名
    */
    private String orderUserName;
    /**
    * 交易时间
    */
    private String orderTradingTimeDetail;
    
    private String orderNumber;
    
    private String orderProductId;
    
    private String orderProductImage;
    /**
    * 商品类型
    */
    private String orderProductType;
    /**
    * 产品单价
    */
    private Double orderProductPrice;
    /**
    * 订单的支付情况：0-->未支付  1-->已支付
    */
    private String orderPaymentStatus;
    /**
    * 0-->未发货  1-->已发货
    */
    private Integer orderSendStatus;
    /**
    * 0 -->未收货  1-->已收货
    */
    private Integer orderConfirmStatus;
    /**
    * 0 -->未评论 1-->已评论
    */
    private Integer orderCommentStatus;
    /**
    * 订单所属省份
    */
    private String orderProvince;
    
    private String orderTradingTimeYear;
    /**
    * 0--->未退款 1--->已退款
    */
    private Integer orderStatus;

    /**
     * 电话号码
     */
    private String orderPhoneNumber;

    /**
     * 收货人
     */
    private String orderReceiverName;

    public String getOrderReceiverName() {
        return orderReceiverName;
    }

    public void setOrderReceiverName(String orderReceiverName) {
        this.orderReceiverName = orderReceiverName;
    }

    /**
    * 0-->未发起退款请求  1-->发起退款请求
    */
    private Integer orderBackMoneyStatus;

    private String productStr;

    private CardInfo cardInfo;

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getProductStr() {
        return productStr;
    }

    public void setProductStr(String productStr) {
        this.productStr = productStr;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderProductNumbers() {
        return orderProductNumbers;
    }

    public void setOrderProductNumbers(Integer orderProductNumbers) {
        this.orderProductNumbers = orderProductNumbers;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public Double getOrderProductTotalPrice() {
        return orderProductTotalPrice;
    }

    public void setOrderProductTotalPrice(Double orderProductTotalPrice) {
        this.orderProductTotalPrice = orderProductTotalPrice;
    }

    public String getExpressDeliveryCompany() {
        return expressDeliveryCompany;
    }

    public void setExpressDeliveryCompany(String expressDeliveryCompany) {
        this.expressDeliveryCompany = expressDeliveryCompany;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    public String getOrderTradingTimeDetail() {
        return orderTradingTimeDetail;
    }

    public void setOrderTradingTimeDetail(String orderTradingTimeDetail) {
        this.orderTradingTimeDetail = orderTradingTimeDetail;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getOrderProductImage() {
        return orderProductImage;
    }

    public void setOrderProductImage(String orderProductImage) {
        this.orderProductImage = orderProductImage;
    }

    public String getOrderProductType() {
        return orderProductType;
    }

    public void setOrderProductType(String orderProductType) {
        this.orderProductType = orderProductType;
    }

    public Double getOrderProductPrice() {
        return orderProductPrice;
    }

    public void setOrderProductPrice(Double orderProductPrice) {
        this.orderProductPrice = orderProductPrice;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public Integer getOrderSendStatus() {
        return orderSendStatus;
    }

    public void setOrderSendStatus(Integer orderSendStatus) {
        this.orderSendStatus = orderSendStatus;
    }

    public Integer getOrderConfirmStatus() {
        return orderConfirmStatus;
    }

    public void setOrderConfirmStatus(Integer orderConfirmStatus) {
        this.orderConfirmStatus = orderConfirmStatus;
    }

    public Integer getOrderCommentStatus() {
        return orderCommentStatus;
    }

    public void setOrderCommentStatus(Integer orderCommentStatus) {
        this.orderCommentStatus = orderCommentStatus;
    }

    public String getOrderProvince() {
        return orderProvince;
    }

    public void setOrderProvince(String orderProvince) {
        this.orderProvince = orderProvince;
    }

    public String getOrderTradingTimeYear() {
        return orderTradingTimeYear;
    }

    public void setOrderTradingTimeYear(String orderTradingTimeYear) {
        this.orderTradingTimeYear = orderTradingTimeYear;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderBackMoneyStatus() {
        return orderBackMoneyStatus;
    }

    public void setOrderBackMoneyStatus(Integer orderBackMoneyStatus) {
        this.orderBackMoneyStatus = orderBackMoneyStatus;
    }

}