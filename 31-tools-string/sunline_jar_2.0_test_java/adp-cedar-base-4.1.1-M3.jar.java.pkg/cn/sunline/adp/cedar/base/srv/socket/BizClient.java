// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BizClient.java

package cn.sunline.adp.cedar.base.srv.socket;

import cn.sunline.adp.cedar.base.engine.data.DataArea;

public interface BizClient
{

    public abstract DataArea call(DataArea dataarea);
}
