// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileModelFile.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;
import java.io.*;

public class FileModelFile
    implements ModelFile
{

    public FileModelFile(File file)
    {
        this.file = file;
    }

    public String getFullPath()
    {
        return file.getAbsolutePath();
    }

    public InputStream getInputStream()
    {
        try
        {
            return new FileInputStream(file);
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can't find file: '").append(getFullPath()).append("'.").toString(), e);
        }
    }

    public OutputStream getOutputStream()
    {
        try
        {
            return new FileOutputStream(file);
        }
        catch(FileNotFoundException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Can't find file: '").append(getFullPath()).append("'.").toString(), e);
        }
    }

    public boolean isReadonly()
    {
        return false;
    }

    public String getFileName()
    {
        return file.getName();
    }

    public long lastModified()
    {
        return file.lastModified();
    }

    private final File file;
}
