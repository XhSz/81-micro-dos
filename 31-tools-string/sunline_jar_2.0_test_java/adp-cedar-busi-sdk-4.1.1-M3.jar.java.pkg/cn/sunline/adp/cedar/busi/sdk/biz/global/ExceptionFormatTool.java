// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExceptionFormatTool.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;


public class ExceptionFormatTool
{

    public ExceptionFormatTool()
    {
    }

    public static transient String format(String errSrc, String strs[])
    {
        String rstr = null;
        if(errSrc.indexOf("[%") == -1 && errSrc.indexOf("]") == -1 || strs.length == 0 || null == strs)
            return errSrc;
        int index = 0;
        String as[] = strs;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String s = as[j];
            index++;
            if(rstr == null)
                rstr = errSrc.replace((new StringBuilder()).append("[%").append(index).append("]").toString(), s);
            else
                rstr = rstr.replace((new StringBuilder()).append("[%").append(index).append("]").toString(), s);
        }

        return rstr;
    }
}
