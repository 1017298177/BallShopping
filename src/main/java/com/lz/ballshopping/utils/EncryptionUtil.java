package com.lz.ballshopping.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class EncryptionUtil {

    public static SimpleHash encryption(String obj,String password){
        //加密方式
        String hashAlgorithName ="MD5";
        //加密次数
        int hashIterations = 1024;
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(obj);
        //加密后的密码
        SimpleHash simpleHash = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        return  simpleHash;
    }


//    public static void main(String[] args) {
//        String hashAlgorithName ="MD5";
//        //加密次数
//        int hashIterations = 1024;
//        //盐值
//        ByteSource credentialsSalt = ByteSource.Util.bytes("zsn");
//        //加密后的密码
//       SimpleHash simpleHash = new SimpleHash(hashAlgorithName, "123456", credentialsSalt, hashIterations);
//        System.out.println(simpleHash);
//    }


}
