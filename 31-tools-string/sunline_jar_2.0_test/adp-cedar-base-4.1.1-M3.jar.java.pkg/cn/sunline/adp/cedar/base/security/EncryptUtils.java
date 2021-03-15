// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptUtils.java

package cn.sunline.adp.cedar.base.security;

import cn.sunline.adp.cedar.base.constant.ApiConst;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

public class EncryptUtils
{

    public EncryptUtils()
    {
    }

    public static final String decrypt(String data)
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        return new String(decrypt(hex2byte(data.getBytes()), "cindaportal".getBytes()));
    }

    public static final String encrypt(String password)
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        return byte2hex(encrypt(password.getBytes(), "cindaportal".getBytes()));
    }

    public static byte[] encrypt(byte src[], byte key[])
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        javax.crypto.SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte src[], byte key[])
        throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
    {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        javax.crypto.SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(src);
    }

    public static String byte2hex(byte b[])
    {
        String hs = "";
        String stmp = "";
        for(int n = 0; n < b.length; n++)
        {
            stmp = Integer.toHexString(b[n] & 0xff);
            if(stmp.length() == 1)
                hs = (new StringBuilder()).append(hs).append("0").append(stmp).toString();
            else
                hs = (new StringBuilder()).append(hs).append(stmp).toString();
        }

        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte b[])
    {
        if(b.length % 2 != 0)
            throw new IllegalArgumentException(cn.sunline.adp.cedar.base.constant.ApiConst.AConst.EncryptUtils01());
        byte b2[] = new byte[b.length / 2];
        for(int n = 0; n < b.length; n += 2)
        {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte)Integer.parseInt(item, 16);
        }

        return b2;
    }

    private static final String CIPHER_CRYPT_KEY = "cindaportal";
    private static final String DES = "DES";
}
