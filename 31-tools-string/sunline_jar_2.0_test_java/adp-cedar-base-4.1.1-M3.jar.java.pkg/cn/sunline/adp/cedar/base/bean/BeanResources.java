// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BeanResources.java

package cn.sunline.adp.cedar.base.bean;


// Referenced classes of package cn.sunline.adp.cedar.base.bean:
//            ApplicationBeanResources

public class BeanResources
{

    public BeanResources()
    {
    }

    public static Object getBean(Class intfClass)
    {
        return ApplicationBeanResources.getInstance().getBean(intfClass);
    }
}
