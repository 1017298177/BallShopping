package com.lz.ballshopping.account.controller;


import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.service.ProductService;
import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*
      127.0.0.1:8080/api/products
      {"currentPage":1,"pageSize":5}
     */
    @PostMapping(value = "/products",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Product> getProductBySearchVo(@RequestBody SearchVo searchVo){
        return productService.getProductBySearchVo(searchVo);
    }

    @PostMapping(value = "/productImg", consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file) {
        return productService.uploadProductImg(file);
    }

    @GetMapping(value = "/product/typeBrand")
    public List<List<String>> getProductBrandAndType(){
        return productService.getProductBrandAndType();
    }

    @PostMapping(value = "/product/productAndImg",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> insertProductAndImg(@RequestBody Product product){
         return  productService.insertProduct(product);
    }

    @PutMapping(value = "/product/productDown/{productId}")
    public Result<String> productDown(@PathVariable String productId){
        return productService.productDown(productId);
    }
    @PutMapping(value = "/product/productUp/{productId}")
    public Result<String> productUp(@PathVariable String productId){
        return productService.productUp(productId);
    }

    @DeleteMapping(value = "/product/{productId}")
    public Result<String> deleteProductByProductId(@PathVariable String productId){
        return productService.deleteProductByProductId(productId);
    }

    @PutMapping(value = "/product",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @GetMapping(value = "/product/{productId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductByProductId(@PathVariable String productId){
        return productService.getProductByProductId(productId);
    }


}
