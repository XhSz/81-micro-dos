// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RandomGUID.java

package cn.sunline.adp.cedar.base.net.util;

import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.*;
import java.util.Random;

public class RandomGUID
{

    public RandomGUID()
    {
        valueBeforeMD5 = "";
        valueAfterMD5 = "";
        getRandomGUID(false);
    }

    public RandomGUID(boolean secure)
    {
        valueBeforeMD5 = "";
        valueAfterMD5 = "";
        getRandomGUID(secure);
    }

    private void getRandomGUID(boolean secure)
    {
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer(128);
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception) { }
        try
        {
            long time = System.currentTimeMillis();
            long rand = 0L;
            if(secure)
                rand = mySecureRand.nextLong();
            else
                rand = myRand.nextLong();
            sbValueBeforeMD5.append(s_id);
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(time));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));
            valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(valueBeforeMD5.getBytes());
            byte array[] = md5.digest();
            StringBuffer sb = new StringBuffer(32);
            for(int j = 0; j < array.length; j++)
            {
                int b = array[j] & 0xff;
                if(b < 16)
                    sb.append('0');
                sb.append(Integer.toHexString(b));
            }

            valueAfterMD5 = sb.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String getQuoteString(boolean secure)
    {
        RandomGUID myGUID = new RandomGUID(secure);
        String ret = (new StringBuilder()).append("{").append(myGUID.toString()).append("}").toString();
        return ret;
    }

    public String toString()
    {
        String raw = valueAfterMD5.toUpperCase();
        StringBuffer sb = new StringBuffer(64);
        sb.append(raw.substring(0, 8));
        sb.append("-");
        sb.append(raw.substring(8, 12));
        sb.append("-");
        sb.append(raw.substring(12, 16));
        sb.append("-");
        sb.append(raw.substring(16, 20));
        sb.append("-");
        sb.append(raw.substring(20));
        return sb.toString();
    }

    public static void main(String args[])
    {
        for(int i = 0; i < 10; i++)
        {
            RandomGUID myGUID = new RandomGUID();
            System.out.println((new StringBuilder()).append("Seeding String=").append(myGUID.valueBeforeMD5).toString());
            System.out.println((new StringBuilder()).append("rawGUID=").append(myGUID.valueAfterMD5).toString());
            System.out.println((new StringBuilder()).append("RandomGUID=").append(myGUID.toString()).toString());
        }

    }

    public final String getValueBeforeMD5()
    {
        return valueBeforeMD5;
    }

    public final String getValueAfterMD5()
    {
        return valueAfterMD5;
    }

    private String valueBeforeMD5;
    private String valueAfterMD5;
    private static Random myRand;
    private static SecureRandom mySecureRand;
    private static String s_id;
    private static final int PAD_BELOW = 16;
    private static final int TWO_BYTES = 255;

    static 
    {
        mySecureRand = new SecureRandom();
        long secureInitializer = mySecureRand.nextLong();
        myRand = new Random(secureInitializer);
        try
        {
            s_id = InetAddress.getLocalHost().toString();
        }
        catch(UnknownHostException unknownhostexception) { }
    }
}
