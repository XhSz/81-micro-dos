// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoadAllWebPageModelFactory.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.common.util.StringUtil;
import cn.sunline.ltts.base.util.LogUtil;
import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.*;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.internal.WebComponent;
import cu.sunline.ltts.ModelRtConst;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            AbstractModelFactory, XmlConfigManager, ModelFilesLoader

public class LoadAllWebPageModelFactory extends AbstractModelFactory
    implements ModelFactory
{

    public transient LoadAllWebPageModelFactory(List base, ModelFilesLoader loaders[])
    {
        Class classes[] = getClasses(base);
        XmlConfigManager.isSchemaValidate = false;
        xmlConfigManager = new XmlConfigManager(classes);
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
            log.info(ModelRtConst.LoadAllAnnotationModelFactory01, new Object[] {
                Double.valueOf((double)loadtime / 1000D), Integer.valueOf(modelsByTypeAndId.values().size())
            });
        }
    }

    private Class[] getClasses(List factories)
    {
        List classes = new ArrayList();
        for(Iterator i$ = factories.iterator(); i$.hasNext();)
        {
            ModelFactory factory = (ModelFactory)i$.next();
            List components = factory.getModels(cn/sunline/ltts/frw/model/dm/internal/WebComponent);
            Iterator i$ = components.iterator();
            while(i$.hasNext()) 
            {
                WebComponent com = (WebComponent)i$.next();
                classes.add(com.getJavaClass());
            }
        }

        return (Class[])classes.toArray(new Class[0]);
    }

    public ModelObject parseModelFile(ModelFile r)
    {
        InputStream is = r.getInputStream();
        Object obj;
        ModelObjectEx m = (ModelObjectEx)xmlConfigManager.load(r, is);
        if(!(m instanceof ModelObjectEx))
            throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllWebPageModelFactory03, new Object[] {
                m.getClass().getName()
            }));
        m.setId(getModelId(r.getFileName()));
        if(m instanceof ModelFileAware)
            ((ModelFileAware)m).setModelFile(r);
        obj = m;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllWebPageModelFactory04, new Object[] {
                    r.getFullPath()
                }));
            }
        return ((ModelObject) (obj));
        Exception e;
        e;
        LogUtil.log.error(e);
        LogUtil.log.error((new StringBuilder()).append(r.getFullPath()).append(ModelRtConst.LoadAllWebPageModelFactory02).append(e.getMessage()).toString());
        obj = null;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllWebPageModelFactory04, new Object[] {
                    r.getFullPath()
                }));
            }
        return ((ModelObject) (obj));
        Exception exception;
        exception;
        if(is != null)
            try
            {
                is.close();
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException(String.format(ModelRtConst.LoadAllWebPageModelFactory04, new Object[] {
                    r.getFullPath()
                }));
            }
        throw exception;
    }

    private String getModelId(String fileName)
    {
        if(StringUtil.isEmpty(fileName))
            return null;
        if(fileName.indexOf(".page.xml") != -1)
            return fileName.substring(0, fileName.lastIndexOf(".page.xml"));
        else
            return null;
    }

    public XmlConfigManager getXmlConfigManager()
    {
        return xmlConfigManager;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/LoadAllWebPageModelFactory);
    private XmlConfigManager xmlConfigManager;

}
