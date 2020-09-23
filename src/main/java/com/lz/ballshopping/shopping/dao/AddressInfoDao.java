package com.lz.ballshopping.shopping.dao;

import com.lz.ballshopping.shopping.entity.AddressInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressInfoDao {

    @Insert("insert into address_info values(#{addressId},#{address},#{userName}," +
            "#{status},#{telPhone},#{receiverName},#{province},#{city})")
    void insertAddressInfo(AddressInfo addressInfo);

    @Select("select * from address_info where user_name = #{userName}")
    List<AddressInfo> getAddressInfos(String userName);

    @Select("select * from address_info where address_id = #{addressId}")
    AddressInfo getAddressInfo(String addressId);

    @Update("update address_info set  address = #{address},status = #{status}," +
            "tel_phone = #{telPhone},receiver_name = #{receiverName},province = #{province}," +
            "city = #{city} where address_id = #{addressId}")
    void updateAddressInfo(AddressInfo addressInfo);

    @Delete("delete from address_info where address_id = #{addressId}")
    void deleteAddressInfo(String addressId);

    @Update("update address_info set  status = 0" +
            " where status = 1")
    void updateBeforeStatus();

    @Update("update address_info set  status = 1" +
            " where address_id = #{addressId}")
    void updateAddressInfoStatus(String addressId);


}
