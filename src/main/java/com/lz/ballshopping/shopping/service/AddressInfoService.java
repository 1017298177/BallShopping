package com.lz.ballshopping.shopping.service;

import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.entity.AddressInfo;

import java.util.List;

public interface AddressInfoService {

    Result<String> insertAddressInfo(AddressInfo addressInfo);

    List<AddressInfo> getAddressInfos();

    AddressInfo getAddressInfo(String addressId);

    Result<String> updateAddressInfo(AddressInfo addressInfo);

    Result<String>  deleteAddressInfo(String addressId);

    Result<String> updateAddressInfoStatus(String addressId);
}
