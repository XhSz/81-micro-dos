// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentManager.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.cedar.base.bean.ApplicationBeanResources;
import cn.sunline.adp.cedar.base.constant.TspEngineConstantDef;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.adp.metadata.loader.util.ModelFactoryUtil;
import cn.sunline.adp.metadata.model.ModelFactory;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.adp.metadata.model.component.AbstractComponentInstance;
import cn.sunline.adp.metadata.model.component.ComponentType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.component:
//            ComponentInstanceController, ComponentInstance

public class ComponentManager
{

    public ComponentManager()
    {
        initialized = false;
        typeMap = new ConcurrentHashMap();
        abstMap = new ConcurrentHashMap();
        abstToInst = new ConcurrentHashMap();
    }

    public static ComponentManager get()
    {
        return instance;
    }

    public void init()
    {
        if(initialized)
            return;
        List cts = ModelFactoryUtil.getModelFactory().getModels(cn/sunline/adp/metadata/model/component/ComponentType);
        ComponentType ct;
        for(Iterator iterator = cts.iterator(); iterator.hasNext(); typeMap.put(ct.getFullId(), ct))
            ct = (ComponentType)iterator.next();

        List acis = ModelFactoryUtil.getModelFactory().getModels(cn/sunline/adp/metadata/model/component/AbstractComponentInstance);
        AbstractComponentInstance aci;
        for(Iterator iterator1 = acis.iterator(); iterator1.hasNext(); abstMap.put(aci.getFullId(), aci))
            aci = (AbstractComponentInstance)iterator1.next();

        controller = (ComponentInstanceController)ApplicationBeanResources.getInstance().getBean(cn/sunline/adp/cedar/base/component/ComponentInstanceController);
        initialized = true;
    }

    private static String getFullIdByAnnotation(Class clazz)
    {
        ConfigType ct = (ConfigType)clazz.getAnnotation(cn/sunline/adp/metadata/model/annotation/ConfigType);
        if(ct == null)
            throw new RuntimeException(String.format(cn.sunline.adp.cedar.base.constant.TspEngineConstantDef.TSPEngineConst.C015(), new Object[] {
                clazz
            }));
        else
            return ct.value();
    }

    public ComponentType getComponentType(Class clazz)
    {
        if(clazz == null)
        {
            return null;
        } else
        {
            String fullId = getFullIdByAnnotation(clazz);
            return (ComponentType)typeMap.get(fullId);
        }
    }

    public ComponentInstance getComponentInstanceByAbst(Class clazz)
    {
        if(clazz == null)
        {
            return null;
        } else
        {
            String fullId = getFullIdByAnnotation(clazz);
            return getComponentInstanceByAbst(fullId);
        }
    }

    public ComponentInstance getComponentInstanceByAbst(String abstSchemaId)
    {
        ComponentInstance ret = null;
        if(!ProfileSwitcher.get().getCompinstFromCache)
        {
            ret = controller.getComponentInstance(abstSchemaId);
        } else
        {
            ret = (ComponentInstance)abstToInst.get(abstSchemaId);
            if(ret == null)
            {
                ret = controller.getComponentInstance(abstSchemaId);
                if(ret != null)
                    abstToInst.put(abstSchemaId, ret);
            }
        }
        return ret;
    }

    public AbstractComponentInstance getAbstractComponentInstance(String abstSchemaId)
    {
        return (AbstractComponentInstance)abstMap.get(abstSchemaId);
    }

    private static ComponentManager instance = new ComponentManager();
    private boolean initialized;
    private Map typeMap;
    private Map abstMap;
    private Map abstToInst;
    private ComponentInstanceController controller;

}
