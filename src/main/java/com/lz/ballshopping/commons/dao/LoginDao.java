package com.lz.ballshopping.commons.dao;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginDao {


    @Select("select * from user_info where user_name  = #{userName}")
    UserInfo getUserInfoByUserName(String userName);

    @Insert("insert into user_info(user_name,real_name,create_time,tel_phone,email,password,sex,user_image)" +
            "values(#{userName},#{realName},#{createTime},#{telPhone},#{email},#{password},#{sex},#{userImage})")
    void registerUser(UserInfo userInfo);
}
