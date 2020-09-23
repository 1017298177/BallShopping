package com.lz.ballshopping.account.service;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();
    Role getRoleByUserId(int userId);

    Result<Role> deleteRoleByRoleId(int roleId);

    Result<Role> insertRole(Role role);

    PageInfo<Role> getRolesBySearchVo(SearchVo searchVo);

    Result<String> updateRole(Role role);

    Role getRoleByRoleId(int roleId);
}
