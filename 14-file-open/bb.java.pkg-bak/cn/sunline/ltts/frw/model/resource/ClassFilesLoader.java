// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClassFilesLoader.java

package cn.sunline.ltts.frw.model.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.*;

public class ClassFilesLoader
{

    public transient ClassFilesLoader(String patterns[], Class validAnnotations[])
    {
        resourcePatternResolver = new PathMatchingResourcePatternResolver();
        metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        this.validAnnotations = validAnnotations;
        this.patterns = patterns;
    }

    public String[] load()
    {
        List ret = new ArrayList();
        String arr$[] = patterns;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String pattern = arr$[i$];
            ret.addAll(__load(pattern));
        }

        return (String[])ret.toArray(new String[0]);
    }

    private List __load(String pattern)
    {
        List ret = new ArrayList();
        try
        {
            Resource resources[] = resourcePatternResolver.getResources(pattern);
            Resource arr$[] = resources;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Resource resource = arr$[i$];
                if(resource.isReadable())
                    try
                    {
                        MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                        AnnotationMetadata amd = metadataReader.getAnnotationMetadata();
                        Class arr$[] = validAnnotations;
                        int len$ = arr$.length;
                        for(int i$ = 0; i$ < len$; i$++)
                        {
                            Class a = arr$[i$];
                            if(amd.hasAnnotation(a.getName()))
                                ret.add(metadataReader.getClassMetadata().getClassName());
                        }

                    }
                    catch(Throwable ex)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Failed to read candidate component class: ").append(resource).toString(), ex);
                    }
            }

        }
        catch(IOException ex)
        {
            throw new IllegalArgumentException("I/O failure during classpath scanning", ex);
        }
        return ret;
    }

    private ResourcePatternResolver resourcePatternResolver;
    private MetadataReaderFactory metadataReaderFactory;
    private Class validAnnotations[];
    private String patterns[];
}
