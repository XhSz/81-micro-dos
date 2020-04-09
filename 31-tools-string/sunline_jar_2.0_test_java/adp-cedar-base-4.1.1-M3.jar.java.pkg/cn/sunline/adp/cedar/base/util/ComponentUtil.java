// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.component.ComponentCacher;

public class ComponentUtil
{

    public ComponentUtil()
    {
    }

    public static Object getInstance(Class componentType, String abstSchemaId)
    {
        return ComponentCacher.getInstance(componentType, abstSchemaId, false);
    }
}
