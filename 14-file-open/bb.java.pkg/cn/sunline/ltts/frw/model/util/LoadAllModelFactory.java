// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadAllModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.ClassModelFactory;
import cn.sunline.ltts.frw.model.dm.SimpleTypeModelFactory;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            AbstractModelFactory, ModelFilesLoader, ModelFactoryUtil

public class LoadAllModelFactory extends AbstractModelFactory
    implements ModelFactory
{

    public transient LoadAllModelFactory(List base, ModelFilesLoader loaders[])
    {
        ModelFactoryUtil.setModelFactory(this);
        getFactories().add(ClassModelFactory.INSTANCE);
        getFactories().add(SimpleTypeModelFactory.INSTANCE);
        if(base != null)
            getFactories().addAll(base);
        if(loaders != null)
        {
            long start = System.currentTimeMillis();
            try
            {
                ModelFilesLoader arr$[] = loaders;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    ModelFilesLoader loader = arr$[i$];
                    ModelFile arr$[] = loader.load();
                    int len$ = arr$.length;
                    for(int i$ = 0; i$ < len$; i$++)
                    {
                        ModelFile r = arr$[i$];
                        if(log.isDebugEnabled())
                            log.debug((new StringBuilder()).append("load file:").append(r).toString());
                        putModel(parseModelFile(r));
                    }

                }

            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(e);
            }
            long loadtime = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            Object m;
            for(Iterator i$ = modelsByTypeAndId.values().iterator(); i$.hasNext(); ModelFactoryUtil.resolveModelRelation(m, new ModelFactory[0]))
                m = i$.next();

            if(base != null)
                getFactories().removeAll(base);
            long resolvetime = System.currentTimeMillis() - start;
            log.info(ModelRtConst.LoadAllModelFactory01, new Object[] {
                Double.valueOf((double)loadtime / 1000D), Double.valueOf((double)resolvetime / 1000D), Integer.valueOf(modelsByTypeAndId.values().size())
            });
        }
    }

    public transient LoadAllModelFactory(ModelFilesLoader loaders[])
    {
        this(null, loaders);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/LoadAllModelFactory);

}
