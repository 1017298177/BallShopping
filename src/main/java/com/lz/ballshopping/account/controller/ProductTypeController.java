package com.lz.ballshopping.account.controller;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.service.ProductTypeService;
import com.lz.ballshopping.commons.entity.ProductType;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @PostMapping(value = "/productType",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<ProductType> getProductTypeBySearchVo(@RequestBody SearchVo searchVo){
      return productTypeService.getProductTypeBySearchVo(searchVo);
    }

    @GetMapping(value = "/productType/{productTypeId}")
    public ProductType getProductTypeByproductTypeId(@PathVariable int productTypeId){
        return productTypeService.getProductTypeByproductTypeId(productTypeId);
    }

    @PostMapping(value = "/productTypeInsert",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Result<String> insertProductType(@RequestBody ProductType productType){
        return productTypeService.insertProductType(productType);
    }
    @PutMapping(value = "/productType",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateProductType(@RequestBody ProductType productType){
        return productTypeService.updateProductType(productType);
    }

    @DeleteMapping(value = "/productType/{productTypeId}")
    public Result<String> deleteProductType(@PathVariable int productTypeId){
        return productTypeService.deleteProductType(productTypeId);
    }

}
