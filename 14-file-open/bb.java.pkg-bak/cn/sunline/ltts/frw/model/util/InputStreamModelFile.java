// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InputStreamModelFile.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.model.ModelFile;
import java.io.InputStream;
import java.io.OutputStream;

public class InputStreamModelFile
    implements ModelFile
{

    public InputStreamModelFile(InputStream is, String filename)
    {
        this.is = is;
        this.filename = filename;
    }

    public String getFileName()
    {
        return filename;
    }

    public String getFullPath()
    {
        return filename;
    }

    public InputStream getInputStream()
    {
        return is;
    }

    public boolean isReadonly()
    {
        return true;
    }

    public OutputStream getOutputStream()
    {
        return null;
    }

    public long lastModified()
    {
        return 0L;
    }

    private InputStream is;
    private String filename;
}
