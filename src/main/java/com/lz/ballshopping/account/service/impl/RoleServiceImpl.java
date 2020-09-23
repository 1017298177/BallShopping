package com.lz.ballshopping.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.dao.RoleDao;
import com.lz.ballshopping.account.service.RoleService;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService
{
  @Autowired
  private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return Optional.ofNullable(roleDao.getRoles()).orElse(Collections.emptyList());
    }

  @Override
  public Role getRoleByUserId(int userId) {
    return roleDao.getRoleByUserId(userId);
  }

  @Override
  public Result<Role> deleteRoleByRoleId(int roleId) {
      roleDao.deleteRoleByRoleId(roleId);
    return new Result<>("删除成功",Result.ResultStatus.SUCCESS.status);
  }

  @Override
  public Result<Role> insertRole(Role role) {
    roleDao.insertRole(role);
    return new Result<>("增加成功",Result.ResultStatus.SUCCESS.status);
  }

  @Override
  public PageInfo<Role> getRolesBySearchVo(SearchVo searchVo) {
    PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
    return new PageInfo<Role>(Optional.ofNullable(roleDao.getRolesBySearchVo(searchVo)).orElse(Collections.emptyList()));
  }

  @Override
  public Result<String> updateRole(Role role) {
      roleDao.updateRole(role);
      return new Result<>("修改成功",Result.ResultStatus.SUCCESS.status);
  }

  @Override
  public Role getRoleByRoleId(int roleId) {

    return roleDao.getRoleByRoleId(roleId);
  }
}
