package com.lz.ballshopping.account.dao;

import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PermissionDao {

    @Select("<script>" +
            "select " +
            "*\n" +
            "FROM\n" +
            "permission\n"
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (permission_name like '%${keyWord}%' or permission_url like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "</script>")
    List<Permission> getPermissionsBySearchVo(SearchVo searchVo);
    @Select("select * from permission where permission_id  = #{permissionId}")
    @Results(id = "rolesResult",value={
            @Result(column="permission_id", property="permissionId"),
            @Result(column="permission_id",property="roles",
                    javaType=List.class,
                    many=@Many(select="com.lz.ballshopping.account.dao.RolePermissionDao.getRolesByPermissionId"))
    })
    Permission getPermissionByPermissionId(int permissionId);
    @Select("select * from permission  where permission_url  = #{permissionUrl}")
    Permission getPermissionByPermissionUrl(String permissionUrl);

    @Update("<script>update permission\n" +
            "        <set>\n" +
            "            <if test=\"permissionName != null and permissionName != ''\">\n" +
            "                permission_name = #{permissionName},\n" +
            "            </if>\n" +
            "            <if test=\"permissionUrl != null and permissionUrl != ''\">\n" +
            "                permission_url = #{permissionUrl},\n" +
            "            </if>\n"+
            "        </set>\n" +
            "        where permission_id = #{permissionId}</script>")
    void updatePermissionByPermissionId(Permission permission);

    @Insert("insert into permission(permission_name,permission_url) values(#{permissionName},#{permissionUrl})")
    void insertPermission(Permission permission);

    @Delete("delete from permission where permission_id = #{permissionId}")
    void deletePermissionByPermissionId(int permissionId);

    @Select("SELECT\n" +
            "permission.*\n" +
            "FROM\n" +
            "role\n" +
            "INNER JOIN role_permission ON role_permission.role_id = role.role_id\n" +
            "INNER JOIN permission ON role_permission.permission_id = permission.permission_id\n" +
            "WHERE role.role_id = #{roleId}")
    List<Permission> getPermissionByRoleId(Integer roleId);
}
