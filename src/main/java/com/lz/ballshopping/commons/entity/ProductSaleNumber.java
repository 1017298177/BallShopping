package com.lz.ballshopping.commons.entity;

import java.io.Serializable;

/**
 * (ProductSaleNumber)实体类
 *
 * @author makejava
 * @since 2020-08-22 22:47:18
 */
public class ProductSaleNumber implements Serializable {
    private static final long serialVersionUID = 473781819038989750L;
    
    private Integer saleId;
    
    private Integer saleCount;
    
    private String saleProductId;
    
    private String saleProductType;
    
    private Double saleProductTotalPrice;


    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getSaleProductId() {
        return saleProductId;
    }

    public void setSaleProductId(String saleProductId) {
        this.saleProductId = saleProductId;
    }

    public String getSaleProductType() {
        return saleProductType;
    }

    public void setSaleProductType(String saleProductType) {
        this.saleProductType = saleProductType;
    }

    public Double getSaleProductTotalPrice() {
        return saleProductTotalPrice;
    }

    public void setSaleProductTotalPrice(Double saleProductTotalPrice) {
        this.saleProductTotalPrice = saleProductTotalPrice;
    }

}