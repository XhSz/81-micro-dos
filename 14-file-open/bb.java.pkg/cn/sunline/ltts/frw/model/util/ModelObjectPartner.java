// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModelObjectPartner.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.base.frw.model.util.ModelPropertyDescriptor;
import java.util.Map;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelObjectLocator

public interface ModelObjectPartner
{

    public abstract Object getSource();

    public abstract void setSource(Object obj);

    public abstract ModelObjectLocator getLocator();

    public abstract Object getRelation(ModelPropertyDescriptor modelpropertydescriptor);

    public abstract Map getData();
}
