// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringUtils.java

package cn.sunline.adp.vine.base.util.lang;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{

    public StringUtils()
    {
    }

    public static int parseInt(String str, int defVal)
    {
        try
        {
            if(isBlank(str))
                return defVal;
        }
        catch(NumberFormatException e)
        {
            return defVal;
        }
        return Integer.parseInt(str);
    }

    public static boolean isBlank(String str)
    {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }

    public static String conc(String array[], String seperator)
    {
        String rst = "";
        for(int i = 0; i < array.length; i++)
        {
            if(i > 0)
                rst = (new StringBuilder()).append(rst).append(seperator).toString();
            rst = (new StringBuilder()).append(rst).append(array[i]).toString();
        }

        return rst;
    }

    public static String getValue(String value, String defValue)
    {
        if(value == null || "".equals(value))
            return defValue;
        else
            return value;
    }

    public static String fillChar(String rs, char ch, int num, boolean left)
    {
        int rsLen = rs.getBytes().length;
        StringBuilder sb = new StringBuilder();
        if(left)
        {
            if(num >= rsLen)
            {
                for(int i = 0; i < num - rsLen; i++)
                    sb.append(ch);

                sb.append(rs);
            } else
            {
                sb.append(rs.substring(0, num));
            }
        } else
        if(num >= rsLen)
        {
            sb.append(rs);
            for(int i = 0; i < num - rsLen; i++)
                sb.append(ch);

        } else
        {
            sb.append(rs.substring(0, num));
        }
        return sb.toString();
    }

    public static String removeFillChar(String rs, char ch, boolean left)
    {
        if(left)
            return removeFillCharStep1(rs, ch);
        else
            return removeFillCharStep2(rs, ch);
    }

    public static String removeFillCharStep2(String rs, char ch)
    {
        if(rs.charAt(rs.length() - 1) != ch)
            return rs;
        int idx = -1;
        int i = rs.length() - 1;
        do
        {
            if(i < 0)
                break;
            if(rs.charAt(i) != ch)
            {
                idx = i;
                break;
            }
            i--;
        } while(true);
        return rs.substring(0, idx + 1);
    }

    public static String removeFillCharStep1(String rs, char ch)
    {
        if(rs.charAt(0) != ch)
            return rs;
        int idx = rs.length();
        int i = 0;
        do
        {
            if(i >= rs.length())
                break;
            if(rs.charAt(i) != ch)
            {
                idx = i;
                break;
            }
            i++;
        } while(true);
        return rs.substring(idx);
    }

    public static String ltrim(String s)
    {
        int len = s.length();
        int st = 0;
        for(char val[] = s.toCharArray(); st < len && val[st] <= ' '; st++);
        return st <= 0 ? s : s.substring(st, len);
    }

    public static String rtrim(String s)
    {
        int len = s.length();
        int st = 0;
        for(char val[] = s.toCharArray(); st < len && val[len - 1] <= ' '; len--);
        return len >= s.length() ? s : s.substring(st, len);
    }

    public static boolean patternMatches(String regex, String str)
    {
        if(str == null)
        {
            return false;
        } else
        {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }

    public static boolean isValidVariableName(String name)
    {
        return isEmpty(name) ? false : patternMatches("^[a-zA-Z_]+\\w*$", name);
    }

    public static boolean isValidLeftOperator(String name)
    {
        return isEmpty(name) ? false : patternMatches("^[a-zA-Z_]+(\\.?\\w+(\\[\\d+\\])*)*$", name);
    }

    public static boolean startsWith(String str, String regexp)
    {
        return Pattern.compile(regexp).matcher(str).find();
    }

    public static String repeat(String str, int times)
    {
        String r = "";
        for(int i = 0; i < times; i++)
            r = (new StringBuilder()).append(r).append(str).toString();

        return r;
    }

    public static String asciiPaddingR(String str, int length, String padding)
    {
        String rst = asciiTrimR(str, length);
        int alen = asciiLength(rst);
        if(alen == length)
            return rst;
        int div = length - alen;
        int dived = asciiLength(padding);
        if(dived != 0)
        {
            int num = div / dived;
            rst = (new StringBuilder()).append(rst).append(repeat(padding, num)).toString();
        }
        return rst;
    }

    public static String asciiTrimR(String str, int length)
    {
        int alen = asciiLength(str);
        if(alen <= length)
            return str;
        String result = "";
        alen = 0;
        int i = 0;
        do
        {
            if(i >= length)
                break;
            char c = str.charAt(i);
            alen += c <= '\177' ? 1 : 2;
            if(alen > length)
                break;
            result = (new StringBuilder()).append(result).append(c).toString();
            i++;
        } while(true);
        return result;
    }

    public static int asciiLength(String str)
    {
        int length = 0;
        for(int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            length += c <= '\177' ? 1 : 2;
        }

        return length;
    }

    public static int asciiIdx2StrIdx(String str, int asciiIdx)
    {
        int length = 0;
        for(int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            length += c <= '\177' ? 1 : 2;
            if(length >= asciiIdx)
                return i;
        }

        return -1;
    }

    public static String asciiFill(String str, int startIdx, String fillStr)
    {
        String charset = Charset.defaultCharset().name();
        try
        {
            byte bs[] = str.getBytes(charset);
            byte fb[] = fillStr.getBytes(charset);
            int capacity = bs.length;
            if(startIdx + fb.length > bs.length)
                capacity = startIdx + fb.length;
            ByteBuffer bb = ByteBuffer.allocate(capacity);
            bb.put(bs);
            if(startIdx > bs.length)
            {
                for(int i = startIdx - bs.length; i > 0; i--)
                    bb.put((byte)32);

            }
            asciiFill(bb, fb, startIdx, bs);
            bb.position(startIdx);
            bb.put(fb);
            return new String(bb.array(), charset);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void asciiFill(ByteBuffer bb, byte fb[], int startIdx, byte bs[])
    {
        boolean isChiChar = false;
        boolean isChiCharBegin = false;
        int fillLastIdx = startIdx + fb.length;
        int i = 0;
        do
        {
            if(i >= bs.length || fillLastIdx <= 0)
                break;
            if(bb.get(i) >= 0)
            {
                isChiChar = false;
                isChiCharBegin = false;
            } else
            {
                isChiChar = true;
                isChiCharBegin = i != 0 ? !isChiCharBegin : true;
            }
            int num = asciiFillIf(i, startIdx, isChiChar, isChiCharBegin, bb, fillLastIdx);
            if(num == 1)
                break;
            i++;
        } while(true);
    }

    public static int asciiFillIf(int i, int startIdx, boolean isChiChar, boolean isChiCharBegin, ByteBuffer bb, int fillLastIdx)
    {
        int num = 0;
        if(i == startIdx)
        {
            if(isChiChar && !isChiCharBegin)
            {
                bb.put(i - 1, (byte)32);
                bb.put(i, (byte)32);
            }
        } else
        if(i == fillLastIdx)
        {
            if(isChiChar && !isChiCharBegin)
            {
                bb.put(i - 1, (byte)32);
                bb.put(i, (byte)32);
            }
            num = 1;
        }
        return num;
    }

    public static String asciiFill2(String str, int startIdx, String fillStr)
    {
        int idx = asciiIdx2StrIdx(str, startIdx);
        String ret = "";
        if(idx == -1)
        {
            ret = asciiPaddingR(str, startIdx, " ");
            ret = (new StringBuilder()).append(ret).append(fillStr).toString();
        } else
        {
            ret = (new StringBuilder()).append(str.substring(0, idx)).append(fillStr).toString();
            if(ret.length() < str.length())
                ret = (new StringBuilder()).append(ret).append(str.substring(ret.length())).toString();
        }
        return ret;
    }

    public static String hexChars2Str(String delimChar)
    {
        if(delimChar.matches("(0[x,X](-){0,1}[0-9,a-f,A-F]+)+"))
        {
            String ss[] = delimChar.split("0[x,X]");
            byte bs[] = new byte[ss.length - 1];
            for(int i = 1; i < ss.length; i++)
            {
                byte b = (byte)Integer.parseInt(ss[i], 16);
                bs[i - 1] = b;
            }

            return new String(bs);
        } else
        {
            return delimChar;
        }
    }

    public static String stationsReplace(String srcStr, Map stationsMap)
    {
        if(stationsMap == null || stationsMap.isEmpty())
            return srcStr;
        String regex = "\\$\\{([^\\}]+)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(srcStr);
        StringBuffer sb = new StringBuffer();
        Object replaceStr = "";
        for(; m.find(); m.appendReplacement(sb, replaceStr != null ? replaceStr.toString() : ""))
        {
            String key = m.group(1);
            replaceStr = stationsMap.get(key);
        }

        if(sb.length() > 0)
        {
            m.appendTail(sb);
            return sb.toString();
        } else
        {
            return srcStr;
        }
    }
}
