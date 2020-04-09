// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FlowSensitiveException.java

package cn.sunline.adp.vine.base.exception;

import java.util.Map;

public interface FlowSensitiveException
{

    public abstract boolean isFlowInfoInited();

    public abstract void setFlowName(String s);

    public abstract void setFlowNodeInfo(String s, String s1);

    public abstract void setExceptionSource(String s);

    public abstract void setFlowNodeContext(Map map);
}
