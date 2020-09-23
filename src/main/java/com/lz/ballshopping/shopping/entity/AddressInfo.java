package com.lz.ballshopping.shopping.entity;

import java.io.Serializable;

/**
 * (AddressInfo)实体类
 *
 * @author makejava
 * @since 2020-09-13 15:43:45
 */
public class AddressInfo implements Serializable {
    private static final long serialVersionUID = 106489696325967506L;
    /**
    * 地址的id用来区分地址
    */
    private String addressId;
    /**
    * 详细地址
    */
    private String address;
    
    private String userName;
    /**
    * 是否为默认地址,1为默认地址,0为非默认地址
    */
    private Integer status;
    /**
    * 联系电话
    */
    private String telPhone;
    /**
    * 接受者姓名
    */
    private String receiverName;
    /**
    * 省份
    */
    private String province;
    
    private String city;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}