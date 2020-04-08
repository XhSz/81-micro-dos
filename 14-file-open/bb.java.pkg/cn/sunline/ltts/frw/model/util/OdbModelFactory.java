// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OdbModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.odb.OdbFactory;
import cn.sunline.ltts.base.odb.OdbManager;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.ClassModelFactory;
import cn.sunline.ltts.frw.model.dm.SimpleTypeModelFactory;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            LazyloadableModelFactory, ModelFilesLoader, ModelFactoryUtil

public class OdbModelFactory
    implements ModelFactory, LazyloadableModelFactory
{

    public transient OdbModelFactory(List base, ModelFilesLoader loaders[])
    {
        if(loaders != null && loaders.length > 0)
        {
            long start = System.currentTimeMillis();
            List models = new ArrayList();
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
                            log.debug((new StringBuilder()).append("load file:").append(r.getFileName()).toString());
                        ModelObject o = ModelFactoryUtil.parse(r);
                        models.add(o);
                        putModel(o.getClass(), o);
                    }

                }

            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(e);
            }
            long loadtime = System.currentTimeMillis() - start;
            List factories = new ArrayList();
            factories.add(this);
            if(base != null)
                factories.addAll(base);
            factories.add(ClassModelFactory.INSTANCE);
            factories.add(SimpleTypeModelFactory.INSTANCE);
            ModelFactoryUtil.setModelFactory((ModelFactory[])factories.toArray(new ModelFactory[factories.size()]));
            start = System.currentTimeMillis();
            Object m;
            for(Iterator i$ = models.iterator(); i$.hasNext(); ModelFactoryUtil.resolveModelRelation(m, new ModelFactory[0]))
                m = i$.next();

            long resolvetime = System.currentTimeMillis() - start;
            log.info(ModelRtConst.OdbModelFactory01, new Object[] {
                Double.valueOf((double)loadtime / 1000D), Double.valueOf((double)resolvetime / 1000D), Integer.valueOf(models.size())
            });
        }
    }

    public void putModel(Class type, Object m)
    {
        getOdbManager(type).insert(m);
    }

    public Object getModel(Class type, Object id)
    {
        return getOdbManager(type).selectByKey(new Object[] {
            id
        });
    }

    public List getModels(Class type)
    {
        return getOdbManager(type).selectAll();
    }

    public ModelObject parseModelFile(ModelFile file)
    {
        return ModelFactoryUtil.parse(file);
    }

    public void putModel(Class type, ModelObject m)
    {
        getOdbManager(type).insert(m);
    }

    private OdbManager getOdbManager(Class type)
    {
        OdbManager ret = OdbFactory.get().getOdbManager(type);
        if(ret == null)
            throw new IllegalArgumentException(String.format(ModelRtConst.OdbModelFactory02, new Object[] {
                type.getName()
            }));
        else
            return ret;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/OdbModelFactory);

}
