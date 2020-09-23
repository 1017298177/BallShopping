package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    PageInfo<Product> getProductBySearchVo( SearchVo searchVo);

    Result<String> uploadProductImg(MultipartFile file);

    List<List<String>> getProductBrandAndType();

    Result<String> insertProduct(Product product);

    Result<String> productDown(String productId);

    Result<String> productUp(String productId);

    Result<String> deleteProductByProductId(String productId);

    Result<String> updateProduct(Product product);

    Product getProductByProductId(String productId);
}
