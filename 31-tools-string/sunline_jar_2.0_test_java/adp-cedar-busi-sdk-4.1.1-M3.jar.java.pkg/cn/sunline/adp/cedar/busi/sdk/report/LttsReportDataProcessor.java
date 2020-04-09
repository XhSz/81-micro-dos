// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsReportDataProcessor.java

package cn.sunline.adp.cedar.busi.sdk.report;


// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.report:
//            LttsReportQueryExecuter

public interface LttsReportDataProcessor
{

    public abstract boolean processSingleData(int i, Object obj);

    public abstract LttsReportQueryExecuter getMainDataQueryExecuter(Object obj);

    public abstract Object getAssistentData(Object obj);
}
