package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.ProductTypeDao;
import com.lz.ballshopping.account.service.ProductTypeService;
import com.lz.ballshopping.commons.entity.ProductType;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ProductTypeServiceImpl implements ProductTypeService
{

    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public PageInfo<ProductType> getProductTypeBySearchVo(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(productTypeDao.getProductTypeBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }
//   return new Result<>("增加商品类型成功",Result.ResultStatus.SUCCESS.status);
    @Override
    public ProductType getProductTypeByproductTypeId(int productTypeId) {
        return  productTypeDao.getProductTypeByproductTypeId(productTypeId);
    }

    @Override
    public Result<String> insertProductType(ProductType productType) {
        productTypeDao.insertProductType(productType);
        return new Result<>("插入商品类型成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> updateProductType(ProductType productType) {
        productTypeDao.updateProductType(productType);
        return new Result<>("修改商品类型成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> deleteProductType(int productTypeId) {
        productTypeDao.deleteProductType(productTypeId);
        return new Result<>("删除商品类型成功",Result.ResultStatus.SUCCESS.status);
    }
}
