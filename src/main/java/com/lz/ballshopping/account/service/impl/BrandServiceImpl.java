package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.BrandDao;
import com.lz.ballshopping.account.service.BrandService;
import com.lz.ballshopping.commons.entity.Brand;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;


    @Override
    public PageInfo<Brand> getBrandBySearchVo(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(brandDao.getBrandBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Brand getBrandByBrandId(int brandId) {
        return brandDao.getBrandByBrandId(brandId);
    }

    @Override
    public Result<String> insertBrand(Brand brand) {
        brandDao.insertBrand(brand);
        return new Result<>("增加品牌成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> updateBrand(Brand brand) {
        brandDao.updateBrand(brand);
        return new Result<>("修改品牌成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> deleteBrand(int brandId) {
        brandDao.deleteBrand(brandId);
        return new Result<>("删除商品类型成功",Result.ResultStatus.SUCCESS.status);
    }
}
