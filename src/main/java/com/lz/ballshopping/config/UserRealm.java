package com.lz.ballshopping.config;

import com.lz.ballshopping.account.dao.PermissionDao;
import com.lz.ballshopping.account.entity.Permission;
import com.lz.ballshopping.account.service.RoleService;
import com.lz.ballshopping.account.service.UserInfoService;
import com.lz.ballshopping.commons.entity.Role;
import com.lz.ballshopping.commons.entity.UserInfo;
import com.lz.ballshopping.commons.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        Role role = roleService.getRoleByUserId(userInfo.getUserId());
        info.addRole(role.getRoleName());
        List<Permission> permissions = permissionDao.getPermissionByRoleId(role.getRoleId());
        for (Permission permission : permissions) {
            info.addStringPermission(permission.getPermissionUrl());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取存在token 中的信息来进行判断
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        UserInfo userInfo = loginService.getUserInfoByUserName(username);
        if(userInfo ==null){
            return null;
        }
        SecurityUtils.getSubject().getSession().setAttribute("userInfo", userInfo);
        //shiro 自动进行密码验证
        //principal:user 前端可以通过principal获取实体对象，在通过property取出实体的属性
        //盐值
        ByteSource salt = ByteSource.Util.bytes(userInfo.getUserName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo,//安全数据
                userInfo.getPassword(),//密码
                salt,
                getName()
        );
        return authenticationInfo;
    }
}
