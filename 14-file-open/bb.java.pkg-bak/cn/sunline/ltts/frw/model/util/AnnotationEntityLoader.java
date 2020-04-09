// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnnotationEntityLoader.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.odb.impl.NoChangeChangeChecker;
import cn.sunline.ltts.frw.model.annotation.Annotations;
import cn.sunline.ltts.frw.model.dm.Schema;
import cn.sunline.ltts.frw.model.resource.ClassFilesLoader;
import cu.sunline.ltts.ModelRtConst;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            AnnotationEntityOdbEntityDao, ModelConfigUtil, AnnotationConfigManager, ModelConfig

public class AnnotationEntityLoader extends ModelConfig.TypedModelLoader
{

    public AnnotationEntityLoader()
    {
    }

    protected ModelConfigUtil.LoaderHelper createHelper()
    {
        ModelConfigUtil.LoaderHelper ret = new ModelConfigUtil.LoaderHelper();
        String classes[] = (new ClassFilesLoader(path, getAnnotations())).load();
        AnnotationEntityOdbEntityDao loader = new AnnotationEntityOdbEntityDao(getType(), classes);
        ret.loader = loader;
        ret.dao = loader;
        ret.changeChecker = NoChangeChangeChecker.INSTANCE;
        return ret;
    }

    private Class[] getAnnotations()
    {
        if(cn/sunline/ltts/frw/model/dm/Schema == getType())
            return Annotations.ALL;
        if("webTransaction".equals(getModelType()))
            return AnnotationConfigManager.get().getAnnotationClasses(getModelType());
        else
            throw new IllegalArgumentException(String.format(ModelRtConst.AnnotationEntityLoader01, new Object[] {
                getType()
            }));
    }

    public String[] getPath()
    {
        return path;
    }

    public void setPath(String path[])
    {
        this.path = path;
    }

    private String path[];
}
