package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.commons.entity.Brand;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

public interface BrandService {
    PageInfo<Brand> getBrandBySearchVo(SearchVo searchVo);

    Brand getBrandByBrandId(int brandId);

    Result<String> insertBrand(Brand brand);

    Result<String> updateBrand(Brand brand);

    Result<String> deleteBrand(int brandId);
}
