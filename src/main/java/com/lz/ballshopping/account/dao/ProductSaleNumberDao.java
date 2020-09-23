package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.ProductSaleNumber;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductSaleNumberDao {

    @Select("select sum(sale_product_total_price) from product_sale_number")
    double getProductTotalPrice();
    @Select("select distinct(sale_product_type) from  product_sale_number")
    List<String> getProductType();
    @Select("select sum(sale_count) from  product_sale_number where sale_product_type = #{productType}")
    int getProductCountByProductType(String productType);
    @Select("select * from product_sale_number where sale_product_id = #{productId}")
    ProductSaleNumber getProductSaleNumberByProductId(String productId);
    @Insert("insert into product_sale_number(sale_count,sale_product_id,sale_product_type," +
            "sale_product_total_price) values(#{saleCount},#{saleProductId}," +
            "#{saleProductType},#{saleProductTotalPrice})")
    void insertProductSaleNumber(ProductSaleNumber productSaleNumber);
    @Update("update product_sale_number set sale_count = #{saleCount}," +
            "sale_product_total_price = #{saleProductTotalPrice} where sale_product_id = #{saleProductId}")
    void updateProductSaleNumber(ProductSaleNumber productSaleNumber1);
}
