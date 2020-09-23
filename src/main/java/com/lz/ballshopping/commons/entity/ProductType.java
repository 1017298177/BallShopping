package com.lz.ballshopping.commons.entity;

import java.io.Serializable;

/**
 * (ProductType)实体类
 *
 * @author makejava
 * @since 2020-08-25 22:14:24
 */
public class ProductType implements Serializable {
    private static final long serialVersionUID = 732608888347881103L;
    
    private Integer productTypeId;
    
    private String productTypeName;


    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

}