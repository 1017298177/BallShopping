package com.lz.ballshopping.account.entity;

import com.lz.ballshopping.commons.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-08-29 14:21:07
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 231909279863136738L;
    
    private Integer permissionId;
    
    private String permissionName;
    
    private String permissionUrl;

    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

}