// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportProcessor.java

package cn.sunline.adp.cedar.busi.sdk.report;

import java.util.Map;

public interface ReportProcessor
{

    public abstract String process(String s, Map map, String s1, String s2, String s3, String s4);
}
