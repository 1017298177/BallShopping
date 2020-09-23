package com.lz.ballshopping.shopping.dao;

import com.lz.ballshopping.account.entity.ProductImage;
import com.lz.ballshopping.commons.entity.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BallDao {

     @Select("select * from product where product_type = '篮球' and product_status = 1 limit 8 ")
     List<Product> getBasketBall();

     @Select("select * from product where product_id = #{productId}")
     @Results(id = "productResults", value = {
             @Result(column = "product_id", property = "productId"),
             @Result(column = "product_id", property = "productImages",
                     javaType = List.class,
                     many = @Many(select = "com.lz.ballshopping.commons.dao.ProductImgDao.getProductImgByProductId"))
     })
     Product getBallInfoByProductId(String productId);

     @Select("select * from product where product_type = '网球' and product_status = 1 limit 8 ")
     List<Product> getTennis();
     @Select("select * from product where product_type = '足球' and product_status = 1 limit 8 ")
     List<Product> getFootBall();
     @Select("select * from product where product_type = '排球' and product_status = 1 limit 8 ")
     List<Product> getVolleyBall();
}
