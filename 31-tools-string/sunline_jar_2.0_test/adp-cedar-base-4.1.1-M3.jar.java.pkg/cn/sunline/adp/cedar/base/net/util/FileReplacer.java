// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileReplacer.java

package cn.sunline.adp.cedar.base.net.util;

import java.io.*;
import java.util.Map;

public class FileReplacer
{

    public FileReplacer()
    {
    }

    public static String read(String path, String encoding)
    {
        String content;
        File file;
        content = "";
        file = new File(path);
        BufferedReader reader;
        Throwable throwable;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        throwable = null;
        try
        {
            String line = null;
            do
            {
                if((line = reader.readLine()) == null)
                    break;
                if(!line.startsWith("#"))
                    content = (new StringBuilder()).append(content).append(line).append("\n").toString();
            } while(true);
        }
        catch(Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        if(reader != null)
            if(throwable != null)
                try
                {
                    reader.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                reader.close();
        break MISSING_BLOCK_LABEL_197;
        Exception exception;
        exception;
        if(reader != null)
            if(throwable != null)
                try
                {
                    reader.close();
                }
                catch(Throwable throwable3)
                {
                    throwable.addSuppressed(throwable3);
                }
            else
                reader.close();
        throw exception;
        Exception e;
        e;
        throw new RuntimeException(e);
        return content;
    }

    private static String unescape(byte bytes[], int len, String encoding)
    {
        try
        {
            byte ret[] = new byte[len];
            System.arraycopy(bytes, 0, ret, 0, len);
            return (new String(ret, encoding)).trim();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException("Message parse error: unexcepted encoding exception", e);
        }
    }

    public static String readAndReplaceVariables(String path, String encoding, Map vars)
    {
        String origin = read(path, encoding);
        StringBuffer sb = new StringBuffer();
        try
        {
            byte originBytes[] = origin.getBytes(encoding);
            byte tokenBytes[] = new byte[originBytes.length];
            boolean flag1 = false;
            boolean flag2 = false;
            int tokenIndex = 0;
            byte abyte0[] = originBytes;
            int i = abyte0.length;
            for(int j = 0; j < i; j++)
            {
                byte b = abyte0[j];
                switch(b)
                {
                case 36: // '$'
                    if(!flag1)
                    {
                        if(tokenIndex > 0)
                        {
                            sb.append(unescape(tokenBytes, tokenIndex, encoding));
                            tokenIndex = 0;
                        }
                        flag1 = true;
                    } else
                    {
                        tokenBytes[tokenIndex++] = b;
                    }
                    break;

                case 123: // '{'
                    if(flag1)
                        flag2 = true;
                    else
                        tokenBytes[tokenIndex++] = b;
                    break;

                case 125: // '}'
                    if(flag2)
                    {
                        String varName = unescape(tokenBytes, tokenIndex, encoding);
                        tokenIndex = 0;
                        sb.append((String)vars.get(varName));
                        flag1 = flag2 = false;
                    } else
                    {
                        tokenBytes[tokenIndex++] = b;
                    }
                    flag1 = flag2 = false;
                    break;

                default:
                    tokenBytes[tokenIndex++] = b;
                    break;
                }
            }

            if(tokenIndex > 0)
            {
                sb.append(unescape(tokenBytes, tokenIndex, encoding));
                tokenIndex = 0;
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
