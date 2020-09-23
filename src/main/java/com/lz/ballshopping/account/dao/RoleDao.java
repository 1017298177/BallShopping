package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {


    @Select("select * from role")
    List<Role> getRoles();

    @Select("SELECT\n" +
            "role.role_id,\n" +
            "role.role_name\n" +
            "FROM\n" +
            "user_info\n" +
            "INNER JOIN user_role ON user_info.user_id = user_role.user_id\n" +
            "INNER JOIN role ON user_role.role_id = role.role_id\n" +
            "where user_info.user_id = #{userId}")
    Role getRoleByUserId(int userId);

    @Select("select * from role where role_name = #{role}")
    Role getRoleByRoleName(String role);
    @Delete("delete from role where role_id  = #{roleId}")
    void deleteRoleByRoleId(int roleId);
    @Insert("insert into role(role_name) values(#{roleName})")
    void insertRole(Role role);

    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "role\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (role_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<Role> getRolesBySearchVo(SearchVo searchVo);

    @Select("select * from role where role_id = #{roleId}")
    Role getRoleByRoleId(int roleId);
    @Update("update role set role_name = #{roleName} where role_id = #{roleId}")
    void updateRole(Role role);
    @Select("select role_id from role where role_name = 'user' ")
    Role getRoleId();

}
