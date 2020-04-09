// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceManagerServiceConfig.java

package cn.sunline.adp.cedar.base.engine.sequence;


// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence:
//            SequenceCallBackImpl

public class SequenceManagerServiceConfig
{

    public SequenceManagerServiceConfig()
    {
        callbackClass = DEFAULT_SEQUENCE_CALLBACK_CLASS;
    }

    public String getCallbackClass()
    {
        return callbackClass;
    }

    public void setCallbackClass(String callbackClass)
    {
        this.callbackClass = callbackClass;
    }

    public static final String DEFAULT_SEQUENCE_CALLBACK_CLASS = cn/sunline/adp/cedar/base/engine/sequence/SequenceCallBackImpl.getName();
    private String callbackClass;

}
