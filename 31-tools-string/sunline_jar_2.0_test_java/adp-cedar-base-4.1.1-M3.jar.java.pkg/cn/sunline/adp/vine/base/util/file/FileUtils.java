// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileUtils.java

package cn.sunline.adp.vine.base.util.file;

import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class FileUtils
{

    public FileUtils()
    {
    }

    public static File createFile(File file)
        throws IOException
    {
        if(file.isDirectory() || file.exists())
            return file;
        File dir = file.getParentFile();
        if(!dir.exists())
            dir.mkdirs();
        boolean flag = file.createNewFile();
        if(!flag)
            throw new RuntimeException((new StringBuilder()).append("create file failure:").append(file.getPath()).toString());
        else
            return file;
    }

    public static int[][] findLineNo(File file, String tokens[])
    {
        int lineNos[][];
        List tokenLineNoList;
        lineNos = (int[][])null;
        if(tokens == null || tokens.length == 0)
            return lineNos;
        tokenLineNoList = new ArrayList(tokens.length);
        for(int i = 0; i < tokens.length; i++)
            tokenLineNoList.add(new ArrayList());

        FileReader fileReader;
        Throwable throwable;
        fileReader = new FileReader(file);
        throwable = null;
        try
        {
            List list = IOUtils.readLines(fileReader);
            for(int i = 0; i < list.size(); i++)
            {
                String lineContent = (String)list.get(i);
                for(int t = 0; t < tokens.length; t++)
                    if(lineContent.contains(tokens[t]))
                    {
                        List lineNoList = (List)tokenLineNoList.get(t);
                        lineNoList.add(Integer.valueOf(i + 1));
                    }

            }

            lineNos = new int[tokens.length][];
            for(int i = 0; i < tokens.length; i++)
            {
                List lineNoList = (List)tokenLineNoList.get(i);
                lineNos[i] = new int[lineNoList.size()];
                for(int j = 0; j < lineNos[i].length; j++)
                    lineNos[i][j] = ((Integer)lineNoList.get(j)).intValue();

            }

            fileReader.close();
        }
        catch(Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        if(fileReader != null)
            if(throwable != null)
                try
                {
                    fileReader.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                fileReader.close();
        break MISSING_BLOCK_LABEL_390;
        Exception exception;
        exception;
        if(fileReader != null)
            if(throwable != null)
                try
                {
                    fileReader.close();
                }
                catch(Throwable throwable3)
                {
                    throwable.addSuppressed(throwable3);
                }
            else
                fileReader.close();
        throw exception;
        FileNotFoundException e;
        e;
        logger.error("file not found", e, new Object[0]);
        break MISSING_BLOCK_LABEL_390;
        e;
        logger.error("IO exception", e, new Object[0]);
        return lineNos;
    }

    public static String getContent(Reader r)
        throws IOException
    {
        String s;
        StringBuffer sb = new StringBuffer();
        char buf[] = new char[1024];
        int c = 0;
        do
        {
            c = r.read(buf);
            if(c != -1)
                sb.append(buf, 0, c);
        } while(c != -1);
        s = sb.toString();
        try
        {
            r.close();
        }
        catch(Exception exception) { }
        return s;
        Exception exception1;
        exception1;
        try
        {
            r.close();
        }
        catch(Exception exception2) { }
        throw exception1;
    }

    private static final SysLog logger = SysLogUtil.getSysLog(cn/sunline/adp/vine/base/util/file/FileUtils);

}
