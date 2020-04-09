// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Facade.java

package cn.sunline.adp.cedar.base.engine;


// Referenced classes of package cn.sunline.adp.cedar.base.engine:
//            RequestData, ResponseData

public interface Facade
{

    public abstract ResponseData process(RequestData requestdata);
}
