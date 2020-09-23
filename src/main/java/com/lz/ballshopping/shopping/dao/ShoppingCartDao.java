package com.lz.ballshopping.shopping.dao;

import com.lz.ballshopping.shopping.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCartDao {

    @Insert("insert into shopping_cart values(#{shoppingId},#{userName}," +
            "#{productNumber},#{productPrice},#{productTotalPrice},#{productMainimage},#{productId},#{productTitle},#{productType},#{productMainId})")
    void insertShoppingCart(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_name = #{userName}")
    List<ShoppingCart> getShoppingCart(String userName);

    @Delete("delete from shopping_cart where user_name = #{userName} and product_id = #{productId}")
    void deleteProductItem(ShoppingCart shoppingCart);
}
