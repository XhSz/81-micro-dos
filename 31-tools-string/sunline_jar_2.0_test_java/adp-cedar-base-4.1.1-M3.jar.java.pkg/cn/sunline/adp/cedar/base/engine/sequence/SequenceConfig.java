// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceConfig.java

package cn.sunline.adp.cedar.base.engine.sequence;


public class SequenceConfig
{

    public SequenceConfig()
    {
        callbackClass = "cn.sunline.ltts.engine.sequence.SequenceCallbackDefault";
    }

    public String getCallbackClass()
    {
        return callbackClass;
    }

    public void setCallbackClass(String callbackClass)
    {
        this.callbackClass = callbackClass;
    }

    public static final String DEFAULT_SEQUENCE_CALLBACK_CLASS = "cn.sunline.ltts.engine.sequence.SequenceCallbackDefault";
    private String callbackClass;
}
