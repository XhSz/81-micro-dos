// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsPkgUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.edsp.base.constant.DateConstant;
import cn.sunline.edsp.base.util.collection.ArrayUtil;
import cn.sunline.edsp.base.util.date.DateUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.sql.Timestamp;
import java.util.*;

public class LttsPkgUtil
{

    public LttsPkgUtil()
    {
    }

    public static Map parse(String s)
    {
        return parse(s, DEFAULT_ENCODING);
    }

    public static Map parse(String s, String encoding)
    {
        return parse(s, encoding, true);
    }

    public static Map parse(String s, String encoding, boolean escapeChinese)
    {
        return parse(toBytes(s, encoding, escapeChinese), encoding);
    }

    public static Map parse(byte bytes[], String encoding)
    {
        Map ret = new LinkedHashMap();
        int level = 0;
        String recordsKey = null;
        Map currentMap = ret;
        int tokenIdx = 0;
        byte tokenBytes[] = new byte[bytes.length];
        char lastMetaChar = '\0';
        boolean isInEscape = false;
        String name = null;
        String value = null;
        for(int i = 0; i < bytes.length; i++)
        {
            byte ch = bytes[i];
            if(isInEscape)
            {
                tokenBytes[tokenIdx++] = ch;
                lastMetaChar = '\0';
                isInEscape = false;
                continue;
            }
            switch(ch)
            {
            case 92: // '\\'
                tokenBytes[tokenIdx++] = ch;
                isInEscape = true;
                break;

            case 124: // '|'
                if(lastMetaChar == '|')
                    throw new IllegalArgumentException("Message format error: unexcepted meta char '|' after '|', excepte for [':', '='].");
                if(level == 1)
                {
                    currentMap = null;
                    name = null;
                    tokenIdx = 0;
                } else
                {
                    if(level == 0)
                        recordsKey = name;
                    value = unescape(tokenBytes, tokenIdx, encoding);
                    if(name != null && name.equals(DEFAULT_WNDWDA_KEY))
                    {
                        Map wndwdaMap = parse(toBytes(value, encoding, false), encoding);
                        currentMap.putAll(wndwdaMap);
                    }
                    currentMap.put(name, value);
                    name = null;
                    tokenIdx = 0;
                }
                lastMetaChar = '|';
                break;

            case 61: // '='
                if(lastMetaChar == '=')
                    throw new IllegalArgumentException("Message format error: unexcepted meta char '=' after '=', excepte for ['|', ':'].");
                name = unescape(tokenBytes, tokenIdx, encoding);
                tokenIdx = 0;
                lastMetaChar = '=';
                break;

            case 58: // ':'
                if(lastMetaChar == ':')
                    throw new IllegalArgumentException("Message format error: unexcepted meta char ':' after ':', excepte for ['='].");
                if(lastMetaChar == '=')
                    throw new IllegalArgumentException("Message format error: unexcepted meta char ':' after '=', excepte for ['|'].");
                value = unescape(tokenBytes, tokenIdx, encoding);
                if(ArrayUtil.notIn(value, "0,1,2"))
                    throw new IllegalArgumentException((new StringBuilder()).append("Message format error: '").append(value).append(":'").toString());
                int newLevel = Integer.parseInt(value);
                if(level > 0 && level == 1 && newLevel == 0)
                    throw new IllegalArgumentException((new StringBuilder()).append("Message format error: '0:' must not be appear after '").append(level).append(":'").toString());
                if(level == 0 && newLevel == 2)
                    throw new IllegalArgumentException("Message format error: not found '1:' before '2:'.");
                if(newLevel == 2)
                {
                    if(currentMap == null)
                    {
                        Object recordsObject = ret.get(recordsKey);
                        List records = null;
                        if((recordsObject instanceof String) || recordsObject == null)
                            ret.put(recordsKey, records = new ArrayList());
                        else
                            records = (List)recordsObject;
                        records.add(currentMap = new MapListDataContext());
                    }
                } else
                if(newLevel == 0)
                    currentMap = ret;
                level = newLevel;
                tokenIdx = 0;
                lastMetaChar = ':';
                break;

            default:
                tokenBytes[tokenIdx++] = ch;
                lastMetaChar = '\0';
                break;
            }
        }

        if(currentMap != null && StringUtil.isNotEmpty(name))
        {
            value = unescape(tokenBytes, tokenIdx, encoding);
            if(name != null && name.equals(DEFAULT_WNDWDA_KEY))
            {
                Map wndwdaMap = parse(tokenBytes, encoding);
                currentMap.putAll(wndwdaMap);
            }
            currentMap.put(name, value);
        }
        return ret;
    }

    public static byte[] format(Map map)
    {
        return format(map, DEFAULT_ENCODING);
    }

    public static byte[] format(Map map, String encoding)
    {
        return toBytes(formatMap(map, true, encoding, true), encoding, false);
    }

    public static String formatToString(Map map, String enconding, boolean escapeChinese)
    {
        return formatMap(map, true, enconding, escapeChinese);
    }

    public static String formatToString(Map map, String encoding)
    {
        return formatToString(map, encoding, false);
    }

    public static String formatToString(Map map)
    {
        return formatToString(map, DEFAULT_ENCODING);
    }

    private static String formatMap(Map map, boolean processList, String encoding, boolean escapeChinese)
    {
        StringBuffer ret = new StringBuffer();
        String list = null;
        boolean needLevelPrefix = false;
        Iterator iterator = map.entrySet().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            java.util.Map.Entry en = (java.util.Map.Entry)iterator.next();
            if(en.getKey() != null)
            {
                String name = en.getKey().toString();
                if(en.getValue() instanceof List)
                {
                    if(!processList)
                        throw new IllegalArgumentException("Unsupported data structure!");
                    List recordsList = (List)en.getValue();
                    list = formatList(recordsList, encoding, escapeChinese);
                    if(StringUtil.isNotEmpty(list))
                    {
                        if(needLevelPrefix)
                        {
                            ret.append("0:");
                            needLevelPrefix = false;
                        }
                        String escapedName = escape(name, encoding, escapeChinese);
                        ret.append(escapedName).append("=").append(recordsList.size()).append("|");
                        ret.append(list);
                        needLevelPrefix = true;
                    }
                } else
                {
                    if(needLevelPrefix)
                    {
                        ret.append("0:");
                        needLevelPrefix = false;
                    }
                    String escapedName = escape(name, encoding, escapeChinese);
                    String escapedValue = null;
                    if(en.getValue() != null && (en.getValue() instanceof Map))
                    {
                        escapedValue = formatMap((Map)(Map)en.getValue(), processList, encoding, escapeChinese);
                        escapedValue = escape(escapedValue != null ? escapedValue : "", encoding, escapeChinese);
                    } else
                    {
                        escapedValue = escape(en.getValue() != null ? formatValue(en.getValue()) : "", encoding, escapeChinese);
                    }
                    ret.append(escapedName).append("=").append(escapedValue).append("|");
                }
            }
        } while(true);
        return ret.toString();
    }

    private static String formatValue(Object value)
    {
        if(value instanceof Timestamp)
            return DateUtil.formatDate((Timestamp)value, "yyyyMMdd HH:mm:ss");
        if(value instanceof Date)
            return DateUtil.formatDate((Date)value, "yyyyMMdd");
        else
            return value.toString();
    }

    protected static String formatList(List list, String encoding, boolean escapeChinese)
    {
        StringBuffer ret = new StringBuffer();
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Map map = (Map)iterator.next();
            ret.append("1:");
            ret.append("=|2:").append(formatMap(map, false, encoding, escapeChinese));
            if(map.isEmpty())
                ret.append("|");
        } while(true);
        return ret.toString();
    }

    private static String escape(String s, String encoding, boolean escapeChinese)
    {
        byte temp[] = new byte[s.length() * 3];
        int j = 0;
label0:
        for(int i = 0; i < s.length(); i++)
            try
            {
                byte b[] = s.substring(i, i + 1).getBytes(encoding);
                if(b.length == 1)
                {
                    if(b[0] == 92 || b[0] == 124 || b[0] == 61 || b[0] == 58)
                        temp[j++] = 92;
                    temp[j++] = b[0];
                    continue;
                }
                byte abyte0[] = b;
                int k = abyte0.length;
                int l = 0;
                do
                {
                    if(l >= k)
                        continue label0;
                    byte bb = abyte0[l];
                    if(escapeChinese && (bb == 92 || bb == 61 || bb == 58 || bb == 124))
                        temp[j++] = 92;
                    temp[j++] = bb;
                    l++;
                } while(true);
            }
            catch(Exception e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Message process error: convert [").append(s).append("] to encoding [").append(encoding).append("] fail").toString(), e);
            }

        byte ret[] = new byte[j];
        System.arraycopy(temp, 0, ret, 0, j);
        try
        {
            return new String(ret, encoding);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Message format error: unexcepted encoding message", e);
        }
    }

    protected static String unescape(byte bytes[], int len, String encoding)
    {
        try
        {
            byte tmp[] = new byte[len];
            int j = 0;
            for(int i = 0; i < len; i++)
                if(bytes[i] == 92)
                {
                    if(i + 1 < len)
                        tmp[j++] = bytes[++i];
                } else
                {
                    tmp[j++] = bytes[i];
                }

            byte ret[] = new byte[j];
            System.arraycopy(tmp, 0, ret, 0, j);
            return (new String(ret, encoding)).trim();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Message parse error: unexcepted encoding exception", e);
        }
    }

    private static byte[] toBytes(String val, String encoding, boolean escapeChinese)
    {
        if(val == null)
            return null;
        byte temp[];
        int j;
        int i;
        byte ret[];
        byte b[];
        byte abyte0[];
        int k;
        int l;
        byte bb;
        try
        {
            if(!escapeChinese)
                return val.getBytes(encoding);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Message process error: convert [").append(val).append("] to encoding [").append(encoding).append("] fail").toString(), e);
        }
        temp = new byte[val.length() * 3];
        j = 0;
label0:
        for(i = 0; i < val.length(); i++)
        {
            b = val.substring(i, i + 1).getBytes(encoding);
            if(b.length == 1)
            {
                temp[j++] = b[0];
                continue;
            }
            abyte0 = b;
            k = abyte0.length;
            l = 0;
            do
            {
                if(l >= k)
                    continue label0;
                bb = abyte0[l];
                if(bb == 92 || bb == 61 || bb == 58 || bb == 124)
                    temp[j++] = 92;
                temp[j++] = bb;
                l++;
            } while(true);
        }

        ret = new byte[j];
        System.arraycopy(temp, 0, ret, 0, j);
        return ret;
    }

    private static String DEFAULT_WNDWDA_KEY = "wndwda";
    private static String DEFAULT_ENCODING = "GB18030";

}
