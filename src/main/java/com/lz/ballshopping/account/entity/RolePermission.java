package com.lz.ballshopping.account.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author makejava
 * @since 2020-08-29 14:21:07
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 197368789790527798L;
    
    private Integer rolePermissionId;
    
    private Integer roleId;
    
    private Integer permissionId;


    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}