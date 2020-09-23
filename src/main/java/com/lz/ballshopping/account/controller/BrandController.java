package com.lz.ballshopping.account.controller;


import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.service.BrandService;
import com.lz.ballshopping.commons.entity.Brand;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BrandController {

    @Autowired
    private BrandService brandService ;

    @PostMapping(value = "/brand",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Brand> getBrandBySearchVo(@RequestBody SearchVo searchVo){
        return brandService.getBrandBySearchVo(searchVo);
    }

    @GetMapping(value = "/brand/{brandId}")
    public Brand getBrandByBrandId(@PathVariable int brandId){
        return brandService.getBrandByBrandId(brandId);
    }

    @PostMapping(value = "/brandInsert",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Result<String> insertBrand(@RequestBody Brand brand){
        return brandService.insertBrand(brand);
    }
    @PutMapping(value = "/brand",consumes =  MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateBrand(@RequestBody Brand brand){
        return brandService.updateBrand(brand);
    }

    @DeleteMapping(value = "/brand/{brandId}")
    public Result<String> deleteBrand(@PathVariable int brandId){
        return brandService.deleteBrand(brandId);
    }
    
}
