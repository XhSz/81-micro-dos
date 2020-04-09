// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EchoUtil.java

package cn.sunline.adp.cedar.busi.sdk.com.suncard.security.util;

import java.util.*;

public class EchoUtil
{

    public EchoUtil()
    {
    }

    public static boolean isnotNullOrEmpty(String str)
    {
        return !"".equals(str) && str != null;
    }

    public static byte[] bcd2hex(String input)
    {
        if(input == null || input.length() == 0)
            return new byte[0];
        byte bs[] = new byte[input.length() / 2];
        for(int i = 0; i < input.length(); i++)
        {
            int x = Integer.parseInt(input.substring(i, i + 2), 16);
            bs[i / 2] = (byte)x;
            i++;
        }

        return bs;
    }

    public static String hex2bcd(byte input[])
    {
        StringBuffer builder = new StringBuffer();
        if(input != null && input.length != 0)
        {
            for(int i = 0; i < input.length; i++)
            {
                int v = input[i] & 0xff;
                String hv = Integer.toHexString(v);
                if(hv.length() < 2)
                    builder.append(0);
                builder.append(hv.toUpperCase());
            }

        }
        return builder.toString();
    }

    public static String withHeader(String input)
    {
        if(input == null || input.length() == 0)
        {
            return "0000";
        } else
        {
            char c1 = (char)(input.length() / 256);
            char c2 = (char)(input.length() % 256);
            return (new StringBuilder()).append(num2hex(c1)).append(num2hex(c2)).append(input).toString();
        }
    }

    public static String headerLength(String input)
    {
        if(input == null || input.length() == 0)
        {
            return "0000";
        } else
        {
            char c1 = (char)(input.length() / 256);
            char c2 = (char)(input.length() % 256);
            return (new StringBuilder()).append(num2hex(c1)).append(num2hex(c2)).toString();
        }
    }

    public static String lpad(String str, String pad, int maxlength)
    {
        if(str == null)
            return "";
        if(str.length() < maxlength)
        {
            StringBuffer b = new StringBuffer();
            for(int i = 0; i < maxlength - str.length(); i++)
                b.append(pad);

            return (new StringBuilder()).append(b.toString()).append(str).toString();
        } else
        {
            return str;
        }
    }

    public static boolean v_EqLength(String src, int expectLength)
    {
        return src.length() == expectLength;
    }

    public static boolean v_LqLength(String src, int maxLength)
    {
        return src.length() <= maxLength;
    }

    public static boolean v_LengthAndNotNull(String src, boolean bool, int length)
    {
        if(isnotNullOrEmpty(src))
            if(bool)
            {
                if(v_LqLength(src, length))
                    return true;
            } else
            if(v_EqLength(src, length))
                return true;
        return false;
    }

    public static String randStr(int len)
    {
        Random random = new Random();
        int r = 97 + random.nextInt(25);
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < len; i++)
        {
            sb.append((char)r);
            r = 97 + random.nextInt(25);
        }

        return sb.toString();
    }

    public static String num2hex(int value)
    {
        String s = Integer.toHexString(value).toUpperCase();
        return s.length() % 2 != 0 ? (new StringBuilder()).append("0").append(s).toString() : s;
    }

    protected static int parseRespPrefixLength(byte bs[])
    {
        return Integer.valueOf(hex2bcd(bs), 16).intValue();
    }

    public static String keyouFormat(String tag, String val)
    {
        StringBuffer builder = new StringBuffer();
        builder.append(tag).append(lpad((new StringBuilder()).append(val.length()).append("").toString(), "0", 4)).append(val);
        return builder.toString();
    }

    public static Map keyouExtract(byte bt[])
    {
        String output = hex2bcd(bt).substring(20);
        Map map = new HashMap();
        if(output == null || output.length() == 0)
            return map;
        int cnt = Integer.valueOf(new String(bcd2hex(output.substring(0, 6)))).intValue();
        int index = 6;
        for(int i = 0; i < cnt; i++)
        {
            String key = new String(bcd2hex(output.substring(index, index + 6)));
            index += 6;
            int c = Integer.valueOf(new String(bcd2hex(output.substring(index, index + 8)))).intValue() * 2;
            index += 8;
            String val = output.substring(index, index + c);
            index += c;
            map.put(key, val);
        }

        return map;
    }

    public static String formatPan(String pan, String panseq)
    {
        if(panseq == null || panseq.length() == 0)
            panseq = "00";
        if(panseq.length() == 1)
            panseq = (new StringBuilder()).append("0").append(panseq).toString();
        if(panseq.length() > 2)
            panseq = panseq.substring(panseq.length() - 2, panseq.length());
        String tmp = (new StringBuilder()).append("00000000000000000000").append(pan).append(panseq).toString();
        return tmp.substring(tmp.length() - 16, tmp.length());
    }

    public static String str2hex(String value)
        throws Throwable
    {
        byte bs[] = value.getBytes("GB18030");
        StringBuffer stringBuilder = new StringBuffer();
        for(int i = 0; i < bs.length; i++)
        {
            int v = bs[i] & 0xff;
            String hv = Integer.toHexString(v);
            if(hv.length() < 2)
                stringBuilder.append(0);
            stringBuilder.append(hv);
        }

        return stringBuilder.toString().toUpperCase();
    }
}
