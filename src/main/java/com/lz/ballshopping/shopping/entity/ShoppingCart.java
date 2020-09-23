package com.lz.ballshopping.shopping.entity;

import java.io.Serializable;

/**
 * (ShoppingCart)实体类
 *
 * @author makejava
 * @since 2020-09-10 17:15:28
 */
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 336711153447025651L;
    /**
    * 购物车商品项的id
    */
    private String shoppingId;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 商品数量
    */
    private Integer productNumber;
    
    private Double productPrice;
    /**
    * 商品的总价
    */
    private Double productTotalPrice;
    /**
    * 商品图片名
    */
    private String productMainimage;
    
    private String productId;
    
    private String productTitle;

    private String productType;

    private String productMainId;


    public String getProductMainId() {
        return productMainId;
    }

    public void setProductMainId(String productMainId) {
        this.productMainId = productMainId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId) {
        this.shoppingId = shoppingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(Double productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getProductMainimage() {
        return productMainimage;
    }

    public void setProductMainimage(String productMainimage) {
        this.productMainimage = productMainimage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

}