//package com.jk.shiro;
//
//import com.jk.model.AdminUsersEntity;
//import com.jk.service.UserService;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by hukai on 2017-11-05.
// */
//public class MyAuthorRealm extends AuthorizingRealm {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
//
//        String username = (String) token.getPrincipal();
//        AdminUsersEntity adminUsersEntity = new AdminUsersEntity();
//        adminUsersEntity.setUsername(username);
//        adminUsersEntity = userService.getEntity(adminUsersEntity);
//        if(null==adminUsersEntity){
//            throw new UnknownAccountException("用户不存在");
//        }
//        if (!"0".equals(adminUsersEntity.getLocked())) {
//            throw new LockedAccountException("用户被锁定");
//        }
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                adminUsersEntity,
//                adminUsersEntity.getPassword(),
//                ByteSource.Util.bytes(adminUsersEntity.getPasswordSalt()),
//                this.getName());
//        return authenticationInfo;
//
//    }
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//}
