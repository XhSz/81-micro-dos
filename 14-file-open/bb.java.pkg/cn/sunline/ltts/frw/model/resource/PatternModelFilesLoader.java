// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatternModelFilesLoader.java

package cn.sunline.ltts.frw.model.resource;

import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.frw.model.util.ModelFilesLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

// Referenced classes of package cn.sunline.ltts.frw.model.resource:
//            ResourceModelFile

public class PatternModelFilesLoader
    implements ModelFilesLoader
{

    public transient PatternModelFilesLoader(String patterns[])
    {
        this.patterns = patterns;
    }

    public ModelFile[] load()
    {
        List ret = new ArrayList();
        PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver(new DefaultResourceLoader());
        String arr$[] = patterns;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String pattern = arr$[i$];
            try
            {
                Resource arr$[] = rr.getResources(pattern);
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    Resource r = arr$[i$];
                    if(r.exists())
                        ret.add(new ResourceModelFile(r));
                }

            }
            catch(IOException e)
            {
                throw new IllegalArgumentException(e);
            }
        }

        return (ModelFile[])ret.toArray(new ModelFile[0]);
    }

    private String patterns[];
}
