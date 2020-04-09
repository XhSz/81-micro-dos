// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StringUtil.java

package cn.sunline.adp.cedar.base.boot.plugin.util;

import java.util.*;

public class StringUtil
{

    public StringUtil()
    {
    }

    public static final String replace(String input, String matchString, String newString)
    {
        int i = 0;
        if((i = input.indexOf(matchString, i)) >= 0)
        {
            char line2[] = input.toCharArray();
            char newString2[] = newString.toCharArray();
            int oLength = matchString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j;
            for(j = i; (i = input.indexOf(matchString, i)) > 0; j = i)
            {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
            }

            buf.append(line2, j, line2.length - j);
            return buf.toString();
        } else
        {
            return input;
        }
    }

    public static String capitalFirst(String s)
    {
        return (new StringBuilder()).append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1)).toString();
    }

    public static String[] tokenizeToStringArray(String str, String delimiters)
    {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
    {
        if(str == null)
            return null;
        StringTokenizer st = new StringTokenizer(str, delimiters);
        List tokens = new ArrayList();
        do
        {
            if(!st.hasMoreTokens())
                break;
            String token = st.nextToken();
            if(trimTokens)
                token = token.trim();
            if(!ignoreEmptyTokens || token.length() > 0)
                tokens.add(token);
        } while(true);
        return toStringArray(tokens);
    }

    public static String[] toStringArray(Collection collection)
    {
        if(collection == null)
            return null;
        else
            return (String[])collection.toArray(new String[collection.size()]);
    }

    public static boolean hasText(String str)
    {
        if(!hasLength(str))
            return false;
        int strLen = str.length();
        for(int i = 0; i < strLen; i++)
            if(!Character.isWhitespace(str.charAt(i)))
                return true;

        return false;
    }

    public static boolean hasLength(String str)
    {
        return str != null && str.length() > 0;
    }

    public static int countOccurrencesOf(String str, String sub)
    {
        if(str == null || sub == null || str.length() == 0 || sub.length() == 0)
            return 0;
        int count = 0;
        int idx;
        for(int pos = 0; (idx = str.indexOf(sub, pos)) != -1; pos = idx + sub.length())
            count++;

        return count;
    }

    public static String cleanPath(String path)
    {
        if(path == null)
            return null;
        String pathToUse = replace(path, "\\", "/");
        int prefixIndex = pathToUse.indexOf(":");
        String prefix = "";
        if(prefixIndex != -1)
        {
            prefix = pathToUse.substring(0, prefixIndex + 1);
            pathToUse = pathToUse.substring(prefixIndex + 1);
        }
        if(pathToUse.startsWith("/"))
        {
            prefix = (new StringBuilder()).append(prefix).append("/").toString();
            pathToUse = pathToUse.substring(1);
        }
        String pathArray[] = delimitedListToStringArray(pathToUse, "/");
        List pathElements = new LinkedList();
        int tops = 0;
        for(int i = pathArray.length - 1; i >= 0; i--)
        {
            String element = pathArray[i];
            if(".".equals(element))
                continue;
            if("..".equals(element))
            {
                tops++;
                continue;
            }
            if(tops > 0)
                tops--;
            else
                pathElements.add(0, element);
        }

        for(int i = 0; i < tops; i++)
            pathElements.add(0, "..");

        return (new StringBuilder()).append(prefix).append(collectionToDelimitedString(pathElements, "/")).toString();
    }

    public static String[] delimitedListToStringArray(String str, String delimiter)
    {
        return delimitedListToStringArray(str, delimiter, null);
    }

    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete)
    {
        if(str == null)
            return new String[0];
        if(delimiter == null)
            return (new String[] {
                str
            });
        List result = new ArrayList();
        if("".equals(delimiter))
        {
            for(int i = 0; i < str.length(); i++)
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));

        } else
        {
            int pos;
            int delPos;
            for(pos = 0; (delPos = str.indexOf(delimiter, pos)) != -1; pos = delPos + delimiter.length())
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));

            if(str.length() > 0 && pos <= str.length())
                result.add(deleteAny(str.substring(pos), charsToDelete));
        }
        return toStringArray(result);
    }

    public static String deleteAny(String inString, String charsToDelete)
    {
        if(!hasLength(inString) || !hasLength(charsToDelete))
            return inString;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < inString.length(); i++)
        {
            char c = inString.charAt(i);
            if(charsToDelete.indexOf(c) == -1)
                sb.append(c);
        }

        return sb.toString();
    }

    public static String applyRelativePath(String path, String relativePath)
    {
        int separatorIndex = path.lastIndexOf("/");
        if(separatorIndex != -1)
        {
            String newPath = path.substring(0, separatorIndex);
            if(!relativePath.startsWith("/"))
                newPath = (new StringBuilder()).append(newPath).append("/").toString();
            return (new StringBuilder()).append(newPath).append(relativePath).toString();
        } else
        {
            return relativePath;
        }
    }

    public static String collectionToDelimitedString(Collection coll, String delim)
    {
        return collectionToDelimitedString(coll, delim, "", "");
    }

    public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix)
    {
        if(coll == null || coll.isEmpty())
            return "";
        StringBuilder sb = new StringBuilder();
        Iterator it = coll.iterator();
        do
        {
            if(!it.hasNext())
                break;
            sb.append(prefix).append(it.next()).append(suffix);
            if(it.hasNext())
                sb.append(delim);
        } while(true);
        return sb.toString();
    }

    static final char LIST_ESC_CHAR = 92;
    public static final String FOLDER_SEPARATOR = "/";
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    public static final String CURRENT_PATH = ".";
    public static final String TOP_PATH = "..";
}
