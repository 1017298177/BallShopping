package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.PermissionDao;
import com.lz.ballshopping.account.dao.RolePermissionDao;
import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.account.entity.RolePermission;
import com.lz.ballshopping.account.service.PermissionService;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

   @Autowired
   private PermissionDao permissionDao;
   @Autowired
   private RolePermissionDao rolePermissionDao;


    @Override
    @Transactional
    public Result<String> deletePermissionByPermissionId(int permissionId) {
        permissionDao.deletePermissionByPermissionId(permissionId);
        rolePermissionDao.deleteRolePermissionByPermissionId(permissionId);
        return new Result<>("删除成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    @Transactional
    public Result<String>  insertPermission(Permission permission) {
        Permission permissionByPermissionUrl = permissionDao.getPermissionByPermissionUrl(permission.getPermissionUrl());
        if(permissionByPermissionUrl !=null){
            return new Result<>("此权限已存在",Result.ResultStatus.FIALD.status);
        }
        permissionDao.insertPermission(permission);
        return new Result<>("增加成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    @Transactional
    public Result<String>  updatePermissionByPermissionId(Permission permission) {
        rolePermissionDao.deleteRolePermissionByPermissionId(permission.getPermissionId());
        permissionDao.updatePermissionByPermissionId(permission);
        List<Role> roles = permission.getRoles();
        for (Role role : roles) {
             RolePermission rolePermission = new RolePermission()   ;
             rolePermission.setRoleId(role.getRoleId());
             rolePermission.setPermissionId(permission.getPermissionId());
            rolePermissionDao.insertRolePermissionByRolePermission(rolePermission);
        }
        return new Result<>("分配成功",Result.ResultStatus.SUCCESS.status);
    }

    @Override
    public Permission getPermissionByPermissionId(int permissionId) {
        return permissionDao.getPermissionByPermissionId(permissionId);
    }

    @Override
    public PageInfo<Permission> getPermissionsBySearchVo(SearchVo searchVo) {
        return new PageInfo<>(Optional.ofNullable(permissionDao.getPermissionsBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }


}
