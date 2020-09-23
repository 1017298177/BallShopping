package com.lz.ballshopping.account.dao;


import com.lz.ballshopping.commons.entity.Brand;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BrandDao {

    @Select("select * from brand")
    List<Brand> getBrand();



    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "brand\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (brand_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<Brand> getBrandBySearchVo(SearchVo searchVo);

    @Select("select * from brand where brand_id = #{brandId}")
    Brand getBrandByBrandId(int brandId);
    @Insert("insert into brand(brand_name) values(#{brandName})")
    void insertBrand(Brand brand);
    @Update("update brand set brand_name = #{brandName} where brand_id = #{brandId}")
    void updateBrand(Brand brand);
    @Delete("delete from brand where brand_id = #{brandId}")
    void deleteBrand(int brandId);
}
