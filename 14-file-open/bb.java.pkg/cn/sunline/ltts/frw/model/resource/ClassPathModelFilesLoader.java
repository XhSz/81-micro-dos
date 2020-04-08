// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClassPathModelFilesLoader.java

package cn.sunline.ltts.frw.model.resource;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.frw.model.util.InputStreamModelFile;
import cn.sunline.ltts.frw.model.util.ModelFilesLoader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassPathModelFilesLoader
    implements ModelFilesLoader
{

    public transient ClassPathModelFilesLoader(String filename[])
    {
        maxIndex = -1;
        this.filename = filename;
        suffix = "";
    }

    public ClassPathModelFilesLoader(int maxIndex, String filename, String suffix)
    {
        this.maxIndex = maxIndex;
        this.filename = (new String[] {
            filename
        });
        this.suffix = suffix;
    }

    public ModelFile[] load()
    {
        if(maxIndex > 0)
            return loadWithMaxIndex();
        else
            return load2();
    }

    public ModelFile[] loadWithMaxIndex()
    {
        List ret = new ArrayList();
        for(int i = 0; i < 100; i++)
        {
            String file = StringUtil.isEmpty(suffix) ? filename[0] : (new StringBuilder()).append(filename[0]).append(i).append(suffix).toString();
            InputStream is = cn/sunline/ltts/frw/model/resource/ClassPathModelFilesLoader.getResourceAsStream(file);
            if(is != null)
                ret.add(new InputStreamModelFile(is, file));
        }

        return (ModelFile[])ret.toArray(new ModelFile[0]);
    }

    public ModelFile[] load2()
    {
        List ret = new ArrayList();
        String arr$[] = filename;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String file = arr$[i$];
            InputStream is = cn/sunline/ltts/frw/model/resource/ClassPathModelFilesLoader.getResourceAsStream(file);
            if(is != null)
                ret.add(new InputStreamModelFile(is, file));
        }

        return (ModelFile[])ret.toArray(new ModelFile[0]);
    }

    private final int maxIndex;
    private final String filename[];
    private final String suffix;
}
