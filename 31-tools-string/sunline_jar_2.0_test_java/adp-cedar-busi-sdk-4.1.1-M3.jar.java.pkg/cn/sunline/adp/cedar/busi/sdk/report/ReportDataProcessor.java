// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportDataProcessor.java

package cn.sunline.adp.cedar.busi.sdk.report;

import java.util.List;

public interface ReportDataProcessor
{

    public abstract List getMainData(Object obj);

    public abstract Object getAssistentData(Object obj);
}
