package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.ProductType;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductTypeDao {

    @Select("select * from product_type")
    List<ProductType> getProductType();


    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "product_type\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (product_type_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<ProductType> getProductTypeBySearchVo(SearchVo searchVo);

    @Select("select * from product_type where product_type_id = #{productTypeId}")
    ProductType getProductTypeByproductTypeId(int productTypeId);
    @Insert("insert into product_type(product_type_name) values(#{productTypeName})")
    void insertProductType(ProductType productType);
    @Update("update product_type set product_type_name = #{productTypeName} where product_type_id = #{productTypeId}")
    void updateProductType(ProductType productType);
    @Delete("delete from product_type where product_type_id = #{productTypeId}")
    void deleteProductType(int productTypeId);
}
