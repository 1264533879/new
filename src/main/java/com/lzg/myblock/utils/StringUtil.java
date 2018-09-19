package com.lzg.myblock.utils;

import java.security.MessageDigest;

/**
 * 此方法用来生存唯一的hash值
 */
public class StringUtil {
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");//创建算加密算法对象
            byte[] hash = digest.digest(input.getBytes("UTF-8"));//加密数据
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i <hash.length ; i++) {
                String hex=Integer.toHexString(0xff & hash[i]);
                if (hex.length()==1)hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
