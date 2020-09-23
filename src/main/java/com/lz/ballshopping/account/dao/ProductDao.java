package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.Brand;
import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.commons.entity.ProductType;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductDao {


    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "product\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (product_name like '%${keyWord}%' or product_brand like '%${keyWord}%'  or product_type like '%${keyWord}%'  ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<Product> getProductBySearchVo(SearchVo searchVo);

    @Select("select count(*) from product")
    int getProductCount();

    @Select("select count(*) from product where product_status = 1")
    int getProductOnCount();

    @Select("select count(*) from product where product_status = 0")
    int getProductDownCount();

    @Select("select brand_name from brand")
    List<String> getProductBrand();

    @Select("select product_type_name from product_type")
    List<String> getProductType();

    @Insert("insert into product values (#{productId},#{productName}, #{productTitle}, " +
            "#{productMainimage}, #{productDetail}, " +
            "#{productBrand}, #{productMarketPrice}, " +
            "#{productPrice}, #{productStock}, #{productStatus}, " +
            "#{productCreatetime}, #{productType})")
    void insertProduct(Product product);

    @Update("update product set product_status = #{productStatus} where product_id = #{productId}")
    void updateProductByproductId(String productId, int productStatus);

    @Delete("delete from product where product_id = #{productId}")
    void deleteProductByProductId(String productId);

    @Update("<script>update product\n" +
            "        <set>\n" +
            "            <if test=\"productName != null and productName != ''\">\n" +
            "                product_name = #{productName},\n" +
            "            </if>\n" +
            "            <if test=\"productTitle != null and productTitle != ''\">\n" +
            "                product_title = #{productTitle},\n" +
            "            </if>\n" +
            "            <if test=\"productMainimage != null and productMainimage != ''\">\n" +
            "                product_mainimage = #{productMainimage},\n" +
            "            </if>\n" +
            "            <if test=\"productDetail != null and productDetail != ''\">\n" +
            "                product_detail = #{productDetail},\n" +
            "            </if>\n" +
            "            <if test=\"productBrand != null and productBrand != ''\">\n" +
            "                product_brand = #{productBrand},\n" +
            "            </if>\n" +
            "            <if test=\"productMarketPrice != null\">\n" +
            "                product_market_price = #{productMarketPrice},\n" +
            "            </if>\n" +
            "            <if test=\"productPrice != null\">\n" +
            "                product_price = #{productPrice},\n" +
            "            </if>\n" +
            "            <if test=\"productStock != null\">\n" +
            "                product_stock = #{productStock},\n" +
            "            </if>\n" +
            "            <if test=\"productStatus != null\">\n" +
            "                product_status = #{productStatus},\n" +
            "            </if>\n" +
            "            <if test=\"productCreatetime != null\">\n" +
            "                product_createtime = #{productCreatetime},\n" +
            "            </if>\n" +
            "            <if test=\"productType != null and productType != ''\">\n" +
            "                product_type = #{productType},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where product_id = #{productId}</script>")
    void updateProduct(Product product);

    @Select("select * from product where product_id = #{productId}")
    Product getProductByProductId(String productId);
    @Update("update product set product_stock = product_stock-#{number} where product_id =#{productId}")
    void updateProductStockByProductId(Map map);
}
