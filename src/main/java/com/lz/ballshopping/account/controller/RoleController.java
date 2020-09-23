package com.lz.ballshopping.account.controller;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.service.RoleService;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    @PostMapping(value = "/roles",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> getRolesBySearchVo(@RequestBody SearchVo searchVo){
        return roleService.getRolesBySearchVo(searchVo);
    }

    @DeleteMapping("/role/{roleId}")
    @RequiresPermissions("/api/role")
    public Result<Role> deleteRoleByRoleId(@PathVariable int roleId){
        return roleService.deleteRoleByRoleId(roleId);
    }


    @PostMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions("/api/role")
    public Result<Role> insertRole(@RequestBody Role role){
        return roleService.insertRole(role);
    }

    @PutMapping(value = "/role",consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequiresPermissions("/api/role")
    public Result<String> updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }
    @GetMapping(value = "/role/{roleId}")
    public Role getRoleByRoleId(@PathVariable int roleId){
        return roleService.getRoleByRoleId(roleId);
    }
}
