// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceBase.java

package cn.sunline.adp.cedar.base.engine.sequence.loader;


public class SequenceBase
{

    public SequenceBase()
    {
        currentSequenceValue = 0L;
    }

    public String getSequenceType()
    {
        return sequenceType;
    }

    public void setSequenceType(String sequenceType)
    {
        this.sequenceType = sequenceType;
    }

    public long getMaxSequenceValue()
    {
        return maxSequenceValue;
    }

    public void setMaxSequenceValue(long maxSequenceValue)
    {
        this.maxSequenceValue = maxSequenceValue;
    }

    public long getMinSequenceValue()
    {
        return minSequenceValue;
    }

    public void setMinSequenceValue(long minSequenceValue)
    {
        this.minSequenceValue = minSequenceValue;
    }

    public long getCurrentSequenceValue()
    {
        return currentSequenceValue;
    }

    public void setCurrentSequenceValue(long currentSequenceValue)
    {
        this.currentSequenceValue = currentSequenceValue;
    }

    public int getCacheSize()
    {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize)
    {
        this.cacheSize = cacheSize;
    }

    public int getStepValue()
    {
        return stepValue;
    }

    public void setStepValue(int stepValue)
    {
        this.stepValue = stepValue;
    }

    private String sequenceType;
    private long maxSequenceValue;
    private long minSequenceValue;
    private long currentSequenceValue;
    private int cacheSize;
    private int stepValue;
}
