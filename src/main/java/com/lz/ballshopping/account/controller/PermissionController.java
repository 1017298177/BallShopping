package com.lz.ballshopping.account.controller;

import com.github.pagehelper.PageInfo;
import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.account.service.PermissionService;
import com.lz.ballshopping.commons.vo.Result;
import com.lz.ballshopping.commons.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/permissions",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<Permission> getPermissionsBySearchVo(@RequestBody SearchVo searchVo){
        return permissionService.getPermissionsBySearchVo(searchVo);
    }
    @GetMapping(value = "/permission/{permissionId}")
    Permission getPermissionByPermissionId(@PathVariable int permissionId){
        return permissionService.getPermissionByPermissionId(permissionId);
    }
    @DeleteMapping(value = "/permission/{permissionId}")
    Result<String> deletePermissionByPermissionId(@PathVariable int permissionId){
        return permissionService.deletePermissionByPermissionId(permissionId);
    }
    @PostMapping(value = "/permission",consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String>  insertPermission(@RequestBody Permission permission){
        return permissionService.insertPermission(permission);
    }
    @PutMapping(value = "/permission",consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String>  updatePermissionByPermissionId(@RequestBody Permission permission){
        return permissionService.updatePermissionByPermissionId(permission);
    }



}
