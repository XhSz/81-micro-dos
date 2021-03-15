// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JDKComponentServiceProxy.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.cedar.base.EngineConst;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKComponentServiceProxy
    implements InvocationHandler
{

    public JDKComponentServiceProxy(Class componentType, String abstSchemaId)
    {
        this.componentType = componentType;
        this.abstSchemaId = abstSchemaId;
    }

    public Object invoke(Object proxy, Method method, Object args[])
        throws Throwable
    {
        throw ExceptionUtil.wrapThrow(EngineConst.JDKComponentServiceProxy01, new String[0]);
    }

    private Class componentType;
    private String abstSchemaId;
}
