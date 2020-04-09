// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceMessage.java

package cn.sunline.adp.cedar.base.bean;


public class SequenceMessage
{

    public SequenceMessage(String resetType, String nextValue)
    {
        this.resetType = resetType;
        this.nextValue = nextValue;
    }

    public String getResetType()
    {
        return resetType;
    }

    public void setResetType(String resetType)
    {
        this.resetType = resetType;
    }

    public String getNextValue()
    {
        return nextValue;
    }

    public void setNextValue(String nextValue)
    {
        this.nextValue = nextValue;
    }

    private String resetType;
    private String nextValue;
}
