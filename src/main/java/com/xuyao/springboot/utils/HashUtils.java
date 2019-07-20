package com.xuyao.springboot.utils;


import org.apache.shiro.crypto.hash.SimpleHash;

public class HashUtils {

    public static String hashAlgorithmName = "MD5";

    public static int hashIterations = 6;

    public static String getHashVal(String hashAlgorithmName, Object credentials, Object salt, int hashIterations){
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toHex();
    }

}
