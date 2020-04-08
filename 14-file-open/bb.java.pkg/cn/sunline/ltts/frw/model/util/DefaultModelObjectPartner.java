// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultModelObjectPartner.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelObjectLocator, ModelObjectPartner, ModelFactoryUtil

public class DefaultModelObjectPartner
    implements ModelObjectPartner
{

    public DefaultModelObjectPartner()
    {
        locator = new ModelObjectLocator();
        data = new ConcurrentHashMap();
    }

    public Object getSource()
    {
        return source;
    }

    public void setSource(Object o)
    {
        source = o;
    }

    public ModelObjectLocator getLocator()
    {
        return locator;
    }

    public Object getRelation(ModelPropertyDescriptor mpd)
    {
        return ModelFactoryUtil.resolveModelRelation(source, mpd.getField().getDeclaringClass(), mpd);
    }

    public Map getData()
    {
        return data;
    }

    private Object source;
    private ModelObjectLocator locator;
    private Map data;
}
