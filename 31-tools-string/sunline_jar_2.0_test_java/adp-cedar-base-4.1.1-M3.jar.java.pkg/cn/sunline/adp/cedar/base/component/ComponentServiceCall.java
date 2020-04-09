// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ComponentServiceCall.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.adp.cedar.base.EngineConst;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ComponentServiceCall
{

    public ComponentServiceCall(Class componentType, String abstSchemaId, Method method, Object args[])
    {
        this.componentType = componentType;
        this.abstSchemaId = abstSchemaId;
        this.method = method;
        this.args = Arrays.copyOf(args, args.length);
        runTimes = 0;
    }

    public Class getComponentType()
    {
        return componentType;
    }

    public String getAbstSchemaId()
    {
        return abstSchemaId;
    }

    public Method getMethod()
    {
        return method;
    }

    public Object[] getArgs()
    {
        return args;
    }

    public int getRunTimes()
    {
        return runTimes;
    }

    public void setRunTimes(int runTimes)
    {
        this.runTimes = runTimes;
    }

    public void incRunTimes()
    {
        runTimes++;
    }

    public String toString()
    {
        return String.format(EngineConst.ComponentServiceCall01, new Object[] {
            componentType, abstSchemaId, method.getName(), Arrays.toString(args), Integer.valueOf(runTimes)
        });
    }

    private Class componentType;
    private String abstSchemaId;
    private Method method;
    private Object args[];
    private int runTimes;
}
