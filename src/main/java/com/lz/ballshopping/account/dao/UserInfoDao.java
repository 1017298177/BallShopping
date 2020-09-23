package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInfoDao {
    @Select("<script>" +
            "select " +
            "user_info.*\n" +
            "FROM\n" +
            "user_info\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%'  or address like '%${keyWord}%' or email like '%${keyWord}%'or tel_phone like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "</script>")
    List<UserInfo> getAllUser(SearchVo searchVo);

    @Delete("delete from user_info where user_id=#{userId}")
    void deleteUserInfoByuserId(int userId);

    @Insert("insert into user_info(user_name,create_time,tel_phone,email,password) values(#{userName},#{createTime},#{telPhone},#{email},#{password})")
    void insertUser(UserInfo userInfo);

    @Select("select * from user_info where user_name  = #{userName}")
    UserInfo getUserInfoByUserName(String userName);

    @Select("select * from user_info where user_id = #{userId}")
    @Results(id = "userResults", value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_id", property = "roles",
                    javaType = List.class,
                    many = @Many(select = "com.lz.ballshopping.account.dao.RoleDao.getRoleByUserId"))
    }
    )
    UserInfo getUserInfoByUserId(int userId);

    @Select("select * from user_info where user_id = #{userId}")
    UserInfo getUserInfo(int userId);

    @Select("select count(*) from user_info ")
    int getUserCount();
    @Update("update user_info set password = #{password} where user_id = #{userId}")
    void updatePassword(UserInfo userInfo);
    @Select("select user_name from user_info ")
    List<String> getUserName();

    @Update("update user_info set user_name = #{userName},tel_phone = #{telPhone}," +
            "email = #{email},sex = #{sex},user_image = #{userImage} where user_id = #{userId}")
    void updateUserInfo(UserInfo userInfo);
}
