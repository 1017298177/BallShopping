package com.lz.ballshopping.commons.entity;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-08-19 09:53:10
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 841385691336243179L;
    
    private Integer roleId;
    
    private String roleName;
    

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}