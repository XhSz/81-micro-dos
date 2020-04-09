// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.edsp.base.file.FileDataExecutor;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;

public class FileUtil
{

    public FileUtil()
    {
    }

    public static int getTotalLines(String fileName)
    {
        try
        {
            return cn.sunline.edsp.base.util.file.FileUtil.getTotalLines(fileName);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E002(fileName, e);
        }
    }

    public static int readFile(String fileName, FileDataExecutor executor)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.readFile(fileName, executor);
    }

    public static int readFile(String fileName, FileDataExecutor executor, String encoding)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.readFile(fileName, executor, encoding);
    }

    public static String getFullPath(String workDir, String fileName)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.getFullPath(workDir, fileName);
    }

    public static String getFileName(String fullPath)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.getFileName(fullPath);
    }

    public static String getFileDir(String fullPath)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.getFileDir(fullPath);
    }

    public static File createDir(String dir)
    {
        File file;
        for(file = new File(dir); !file.exists(); file.mkdirs());
        return file;
    }

    public static File createFile(String workDir, String fileName, boolean deleteWhenExists)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.createFile(workDir, fileName, deleteWhenExists);
    }

    /**
     * @deprecated Method ReadFile is deprecated
     */

    public static List ReadFile(String fileName, long startLine, long recNum)
    {
        try
        {
            List lines = FileUtils.readLines(new File(fileName));
            return lines.subList((int)startLine, (int)(startLine + recNum));
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E002(fileName);
        }
    }

    /**
     * @deprecated Method writeToFile is deprecated
     */

    public static File writeToFile(String filePathAndName, String fileContent, String encoding, boolean deleteWhenExists)
    {
        return cn.sunline.edsp.base.util.file.FileUtil.writeToFile(filePathAndName, fileContent, encoding, deleteWhenExists);
    }

    public static void copyFile(String src, String dest)
    {
        cn.sunline.edsp.base.util.file.FileUtil.copyFile(src, dest);
    }

    public static final String Remote_File_Name = "remoteFileName";
    public static final String FILE_ID = "fileId";
    public static final String MD5 = "md5";
}
