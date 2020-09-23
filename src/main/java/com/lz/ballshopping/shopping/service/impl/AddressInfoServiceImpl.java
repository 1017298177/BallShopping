package com.lz.ballshopping.shopping.service.impl;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.shopping.dao.AddressInfoDao;
import com.lz.ballshopping.shopping.entity.AddressInfo;
import com.lz.ballshopping.shopping.service.AddressInfoService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressInfoServiceImpl implements AddressInfoService {

    @Autowired
    private AddressInfoDao addressInfoDao;

    @Override
    public Result<String> insertAddressInfo(AddressInfo addressInfo) {
        String addressId = UUID.randomUUID().toString().substring(0, 6).replace("-", "");
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        addressInfo.setUserName(user.getUserName());
        addressInfo.setAddressId(addressId);
        addressInfoDao.insertAddressInfo(addressInfo);
        return new Result<String>("增加成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public List<AddressInfo> getAddressInfos() {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return Optional.ofNullable(addressInfoDao.getAddressInfos(user.getUserName())).orElse(Collections.emptyList());
    }

    @Override
    public AddressInfo getAddressInfo(String addressId) {
        return  addressInfoDao.getAddressInfo(addressId);
    }

    @Override
    public Result<String> updateAddressInfo(AddressInfo addressInfo) {
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        addressInfo.setUserName(user.getUserName());
        addressInfoDao.updateAddressInfo(addressInfo);
        return new Result<String>("修改成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Result<String> deleteAddressInfo(String addressId) {
        addressInfoDao.deleteAddressInfo(addressId);
        return new Result<String>("删除成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    @Transactional
    public Result<String> updateAddressInfoStatus(String addressId) {
        addressInfoDao.updateBeforeStatus();
        addressInfoDao.updateAddressInfoStatus(addressId);
        return new Result<String>("设置成功",Result.ResultStatus.SUCCESS.status);
    }
}
