package com.lz.ballshopping.commons.dao;

import com.lz.ballshopping.account.entity.ProductImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductImgDao {


    @Insert("insert into product_image(product_id,product_image_type,product_image_url)" +
            "values(#{productId},#{productImageType},#{productImageUrl})")
    void insertProductImg(ProductImage productImage);

    @Delete("delete from product_image where  product_id = #{productId}")
    void deleteProductImgByProductId(String productId);

    @Select("select * from product_image where  product_id = #{productId}")
    List<ProductImage> getProductImgByProductId(String productId);
}
