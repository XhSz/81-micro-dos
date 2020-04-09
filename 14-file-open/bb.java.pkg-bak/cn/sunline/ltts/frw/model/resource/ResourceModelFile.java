// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResourceModelFile.java

package cn.sunline.ltts.frw.model.resource;

import cn.sunline.ltts.core.api.model.ModelFile;
import java.io.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

public class ResourceModelFile
    implements ModelFile
{

    public ResourceModelFile(Resource resource)
    {
        this.resource = resource;
    }

    public String getFullPath()
    {
        return resource.getDescription();
    }

    public InputStream getInputStream()
    {
        try
        {
            return resource.getInputStream();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public OutputStream getOutputStream()
    {
        File file;
        Exception e;
        try
        {
            if(resource instanceof WritableResource)
                return ((WritableResource)resource).getOutputStream();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        file = resource.getFile();
        return new FileOutputStream(file);
        e;
        return null;
    }

    public boolean isReadonly()
    {
        if(resource instanceof WritableResource)
            return false;
        try
        {
            File file = resource.getFile();
            if(file.exists())
                return false;
        }
        catch(Exception e)
        {
            return true;
        }
        return true;
    }

    public String toString()
    {
        return getFullPath();
    }

    public String getFileName()
    {
        return resource.getFilename();
    }

    public long lastModified()
    {
        try
        {
            return resource.lastModified();
        }
        catch(IOException e)
        {
            return 0L;
        }
    }

    private final Resource resource;
}
