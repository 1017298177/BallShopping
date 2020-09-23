package com.lz.ballshopping.account.entity;

import java.io.Serializable;

/**
 * (ProductImage)实体类
 *
 * @author makejava
 * @since 2020-09-06 15:04:00
 */
public class ProductImage implements Serializable {
    private static final long serialVersionUID = 738839798261460449L;
    
    private Integer productImageId;
    
    private String productId;
    /**
    * 0-->主图的各面  1-->同类商品的不同颜色
    */
    private Integer productImageType;
    
    private String productImageUrl;


    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductImageType() {
        return productImageType;
    }

    public void setProductImageType(Integer productImageType) {
        this.productImageType = productImageType;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

}