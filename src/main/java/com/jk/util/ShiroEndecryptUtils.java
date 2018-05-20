package com.jk.util;

import com.jk.model.AdminUsersEntity;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * shiro加密工具类
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-11-05 14:43
 */
public final class ShiroEndecryptUtils {

    //base64进制加密
    public static String encrytBase64(String password) {
        byte[] bytes = password.getBytes();
        return Base64.encodeToString(bytes);
    }
    //对后台用户加密并返回用户对像
    public static AdminUsersEntity md5Password(AdminUsersEntity adminUsersEntity){
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt= secureRandomNumberGenerator.nextBytes().toHex();
        //组合username,两次迭代，对密码进行加密
        String newpassword= new Md5Hash(
                adminUsersEntity.getPassword(),
                adminUsersEntity.getUsername()+salt,
                2).toBase64();
        adminUsersEntity.setPassword(newpassword);
        adminUsersEntity.setPasswordSalt(salt);
        adminUsersEntity.setUsername(adminUsersEntity.getUsername());
        return adminUsersEntity;
    }

}