package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.account.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
@Repository
public interface UserRoleDao {

    @Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
    void insertUserRole(UserRole userRole);

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteByUserId(int userId);

}
