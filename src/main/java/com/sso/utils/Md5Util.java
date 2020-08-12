package com.sso.utils;

import java.security.MessageDigest;

/**
 * @author chenhaowen
 * @Description: md5工具类
 * @date 2020/8/12 7:55 PM
 */
public class Md5Util {

    private static final String charsetName = "UTF-8";

    /**
     * MD5加密
     * @param message 需要加密的信息，例：123456
     * @return 返回MD5加密后的32位大写字符串，例：E10ADC3949BA59ABBE56E057F20F883E
     */
    public static String encode(String message) {
        String md5 = "";
        // 创建一个md5算法对象
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] messageByte = message.getBytes(charsetName);
            // 获得MD5字节数组,16*8=128位
            byte[] md5Byte = md.digest(messageByte);
            // 转换为16进制字符串
            md5 = bytesToHex(md5Byte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5;
    }

    /***
     * 将字节数组转换成16进制字符串
     * @param bytes 需要转换的字节数组，例：[-31,10,-36,57,73,-70,89,-85,-66,86,-32,87,-14,15,-120,62]
     * @return 返回转换后的大写字符串，例：E10ADC3949BA59ABBE56E057F20F883E
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder();
        int num;
        for (byte aByte : bytes) {
            num = aByte;
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}
