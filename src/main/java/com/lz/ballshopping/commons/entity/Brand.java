package com.lz.ballshopping.commons.entity;

import java.io.Serializable;

/**
 * (Brand)实体类
 *
 * @author makejava
 * @since 2020-08-25 22:15:36
 */
public class Brand implements Serializable {
    private static final long serialVersionUID = -12854655423340215L;
    
    private Integer brandId;
    
    private String brandName;


    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

}