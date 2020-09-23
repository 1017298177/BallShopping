package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.ProductDao;
import com.lz.ballshopping.commons.dao.ProductImgDao;
import com.lz.ballshopping.account.entity.ProductImage;
import com.lz.ballshopping.account.service.ProductService;
import com.lz.ballshopping.commons.entity.Product;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import com.lz.ballshopping.config.ResourceConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;
    @Autowired
    private ProductImgDao productImgDao;
    @Override
    public PageInfo<Product> getProductBySearchVo(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Product>(Optional.ofNullable(productDao.getProductBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    public Result<String> uploadProductImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    "请选择图片！", Result.ResultStatus.FIALD.status);
        }
        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                     "上传失败", Result.ResultStatus.FIALD.status);
        }
        return new Result<String>(
                "上传成功", Result.ResultStatus.SUCCESS.status,relativePath);
    }

    @Override
    @Transactional
    public List<List<String>> getProductBrandAndType() {
        List<List<String>> lists = new ArrayList<>();
        List<String> brand = productDao.getProductBrand();
        lists.add(brand);
        List<String> type = productDao.getProductType();
        lists.add(type);
        return lists;
    }

    @Override
    @Transactional
    public Result<String> insertProduct(Product product) {
        //插入商品
        String productId = UUID.randomUUID().toString().substring(0,8).replace("-","");
        product.setProductId(productId);
        product.setProductCreatetime(new Date());
        product.setProductStatus(1);
        productDao.insertProduct(product);
        //插入商品附属图片
        String productImageMany = product.getProductImageMany();
        String productImageColor = product.getProductImageColor();
        String[] splitMany = productImageMany.split(",");
        String[] splitColor = productImageColor.split(",");
        for (String url : splitMany) {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setProductImageType(0);
            productImage.setProductImageUrl(url);
            productImgDao.insertProductImg(productImage);
        }
        for (String url: splitColor) {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setProductImageType(1);
            productImage.setProductImageUrl(url);
            productImgDao.insertProductImg(productImage);
        }
        return new Result<>("增加商品成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> productDown(String productId) {
        productDao.updateProductByproductId(productId,0);
        return new Result<String>("下架成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> productUp(String productId) {
        productDao.updateProductByproductId(productId,1);
        return new Result<String>("上架成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> deleteProductByProductId(String productId) {
        productImgDao.deleteProductImgByProductId(productId);
        productDao.deleteProductByProductId(productId);
        return new Result<String>("删除成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> updateProduct(Product product) {
        productDao.updateProduct(product);
        return new Result<String>("修改成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Product getProductByProductId(String productId){
        return productDao.getProductByProductId(productId);
    }
}
