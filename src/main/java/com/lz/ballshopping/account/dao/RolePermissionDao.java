package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.account.entity.RolePermission;
import com.lz.ballshopping.commons.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RolePermissionDao {

    @Insert("insert into role_permission(role_id,permission_id) values(#{roleId},#{permissionId})")
    void insertRolePermissionByRolePermission(RolePermission rolePermission);
    @Delete("delete from role_permission where permission_id = #{permissionId}")
    void deleteRolePermissionByPermissionId(int permissionId);
    @Select("SELECT\n" +
            "role.role_id,\n" +
            "role.role_name\n" +
            "FROM\n" +
            "role\n" +
            "INNER JOIN role_permission ON role_permission.role_id = role.role_id\n" +
            "WHERE role_permission.permission_id = #{permissionId}")
    List<Role> getRolesByPermissionId(int permissionId);
}
