package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.commons.entity.ProductType;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

public interface ProductTypeService {
    PageInfo<ProductType> getProductTypeBySearchVo(SearchVo searchVo);

    ProductType getProductTypeByproductTypeId(int productTypeId);

    Result<String> insertProductType(ProductType productType);

    Result<String> updateProductType(ProductType productType);

    Result<String> deleteProductType(int productTypeId);
}
