package com.lz.ballshopping.shopping.controller;

import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.AddressInfo;
import com.lz.ballshopping.shopping.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressInfoController {

    @Autowired
    private AddressInfoService addressInfoService;

    @PostMapping(value = "/addressInfo",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> insertAddressInfo(@RequestBody AddressInfo addressInfo){
        return addressInfoService.insertAddressInfo(addressInfo);
    }

    @GetMapping(value = "/addressInfos")
    public List<AddressInfo> getAddressInfos(){
        return addressInfoService.getAddressInfos();
    }

    @GetMapping(value = "/addressInfo/{addressId}")
    public AddressInfo getAddressInfo(@PathVariable String addressId){
        return addressInfoService.getAddressInfo(addressId);
    }

    @PutMapping(value = "/addressInfo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<String> updateAddressInfo(@RequestBody AddressInfo addressInfo) {
        return addressInfoService.updateAddressInfo(addressInfo);
    }

    @PutMapping(value = "/addressInfo/{addressId}")
    public Result<String> updateAddressInfoStatus(@PathVariable String addressId) {
        return addressInfoService.updateAddressInfoStatus(addressId);
    }

    @DeleteMapping(value = "/addressInfo/{addressId}")
    public Result<String>  deleteAddressInfo(@PathVariable String addressId){
        return addressInfoService.deleteAddressInfo(addressId);
    }

}
