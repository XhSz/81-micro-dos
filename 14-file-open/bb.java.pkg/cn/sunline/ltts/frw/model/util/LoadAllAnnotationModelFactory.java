// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadAllAnnotationModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.util.ClassUtils;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelObject;
import cn.sunline.ltts.core.api.model.dm.ElementType;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.*;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            AbstractModelFactory, AnnotationConfigManager, ModelFactoryUtil

/**
 * @deprecated Class LoadAllAnnotationModelFactory is deprecated
 */

public class LoadAllAnnotationModelFactory extends AbstractModelFactory
    implements ModelFactory
{

    private Schema getSchema(Class clss)
    {
        String schemaId = AnnotationConfigManager.get().getSchemaId(clss);
        Schema schema = null;
        if(schemaMap.containsKey(schemaId))
        {
            schema = (Schema)schemaMap.get(schemaId);
        } else
        {
            schema = AnnotationConfigManager.get().getSchema(clss);
            schemaMap.put(schemaId, schema);
        }
        return schema;
    }

    private void load(Class clss)
    {
        ModelObject model = AnnotationConfigManager.get().load(clss);
        if(model instanceof ElementType)
        {
            Schema schema = getSchema(clss);
            if(schema != null)
            {
                ((ElementType)model).setOwner(schema);
                schema.getTypes().add((ElementType)model);
            }
        } else
        if(model instanceof Schema)
        {
            Schema schema = (Schema)model;
            if(schemaMap.containsKey(schema.getId()))
                return;
            schemaMap.put(schema.getId(), schema);
        }
    }

    public LoadAllAnnotationModelFactory(List base, String classes[])
    {
        schemaMap = new HashMap();
        ModelFactoryUtil.setModelFactory(this);
        getFactories().add(ClassModelFactory.INSTANCE);
        getFactories().add(SimpleTypeModelFactory.INSTANCE);
        if(base != null)
            getFactories().addAll(base);
        if(classes != null)
        {
            long start = System.currentTimeMillis();
            try
            {
                String arr$[] = classes;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    String className = arr$[i$];
                    if(log.isDebugEnabled())
                        log.debug((new StringBuilder()).append("load file:").append(className).toString());
                    try
                    {
                        Class clss = ClassUtils.classForName(className);
                        load(clss);
                        Class innerClasses[] = clss.getDeclaredClasses();
                        if(innerClasses == null || innerClasses.length == 0)
                            continue;
                        Class arr$[] = innerClasses;
                        int len$ = arr$.length;
                        for(int i$ = 0; i$ < len$; i$++)
                        {
                            Class innerClass = arr$[i$];
                            load(innerClass);
                        }

                    }
                    catch(Exception e)
                    {
                        throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllAnnotationModelFactory02, new Object[] {
                            className, e.getMessage()
                        }), e);
                    }
                }

                Schema s;
                for(Iterator i$ = schemaMap.values().iterator(); i$.hasNext(); putModel(s))
                {
                    s = (Schema)i$.next();
                    String keys[] = getModelIds(s);
                    if(modelsByTypeAndId.containsKey(keys[0]))
                    {
                        Schema s1 = (Schema)modelsByTypeAndId.get(keys[0]);
                        throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllAnnotationModelFactory03, new Object[] {
                            s.getId(), s.getPackage(), s1.getId(), s1.getPackage()
                        }));
                    }
                }

            }
            catch(Exception e)
            {
                throw new IllegalArgumentException(e);
            }
            long loadtime = System.currentTimeMillis() - start;
            start = System.currentTimeMillis();
            for(Iterator i$ = modelsByTypeAndId.values().iterator(); i$.hasNext();)
            {
                Object m = i$.next();
                try
                {
                    ModelFactoryUtil.resolveModelRelation(m, new ModelFactory[0]);
                }
                catch(Exception e)
                {
                    log.error("LoadAllAnnotationModelFactory error!", e, new Object[0]);
                }
            }

            long resolvetime = System.currentTimeMillis() - start;
            log.info(ModelRtConst.LoadAllAnnotationModelFactory01, new Object[] {
                Double.valueOf((double)loadtime / 1000D), Double.valueOf((double)resolvetime / 1000D), Integer.valueOf(modelsByTypeAndId.values().size())
            });
        }
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/LoadAllAnnotationModelFactory);
    private Map schemaMap;

}
