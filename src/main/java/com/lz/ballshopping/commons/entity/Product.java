package com.lz.ballshopping.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.ballshopping.account.entity.ProductImage;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2020-08-24 18:46:10
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -86107148670768973L;
    /**
    * 商品id
    */
    private String productId;
    /**
    * 商品名称
    */
    private String productName;
    /**
    * 商品副标题
    */
    private String productTitle;
    /**
    * 产品主图名称
    */
    private String productMainimage;
    /**
    * 商品详情
    */
    private String productDetail;
    /**
    * 商品品牌
    */
    private String productBrand;
    /**
    * 现有价格,单位-元保留两位小数
    */
    private Double productPrice;
    /**
    * 库存数量
    */
    private Integer productStock;
    /**
    * 商品状态.1-在售 0-下架 
    */
    private Integer productStatus;
    /**
    * 上架时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date productCreatetime;
    
    private String productType;
    /**
    * 市场价格
    */
    private Double productMarketPrice;

    private String productImageMany;

    private String productImageColor;

    List<ProductImage> productImages;

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public String getProductImageMany() {
        return productImageMany;
    }

    public void setProductImageMany(String productImageMany) {
        this.productImageMany = productImageMany;
    }

    public String getProductImageColor() {
        return productImageColor;
    }

    public void setProductImageColor(String productImageColor) {
        this.productImageColor = productImageColor;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductMainimage() {
        return productMainimage;
    }

    public void setProductMainimage(String productMainimage) {
        this.productMainimage = productMainimage;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Date getProductCreatetime() {
        return productCreatetime;
    }

    public void setProductCreatetime(Date productCreatetime) {
        this.productCreatetime = productCreatetime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getProductMarketPrice() {
        return productMarketPrice;
    }

    public void setProductMarketPrice(Double productMarketPrice) {
        this.productMarketPrice = productMarketPrice;
    }

}