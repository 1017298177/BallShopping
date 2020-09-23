package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

import java.util.List;

public interface PermissionService {

    Result<String> deletePermissionByPermissionId(int permissionId);
    Result<String>  insertPermission(Permission permission);
    Result<String>  updatePermissionByPermissionId(Permission permission);
    Permission getPermissionByPermissionId(int permissionId);
    PageInfo<Permission> getPermissionsBySearchVo(SearchVo searchVo);
}
