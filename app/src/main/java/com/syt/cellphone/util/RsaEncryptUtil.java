package com.syt.cellphone.util;

import com.syt.cellphone.base.Config;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author shenyutian
 * @data 2020/5/14 5:30 PM
 * 功能 客户端加解密工具
 */
public class RsaEncryptUtil {
    /**
     *      rsa 算法，公钥
     */
    private static final String KEY_PUBLIC = Config.KEY_PUBLIC;

    /**
     * 加密工具类
     */
    private static RSA rsa = new RSA(KEY_PUBLIC, null);

    /**
     * 加密方法
     * @param content 未加密数据
     * @return 加密结果
     */
    public static String encrypt(String content) {
        return rsa.encryptHex(content, KeyType.PublicKey);
    }

    /**
     * 解密方法
     * @param content 加密的数据
     * @return 解密结果
     */
    public static String decrypt(String content) {
        return rsa.decryptStr(content, KeyType.PublicKey);
    }
}
