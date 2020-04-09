// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BootProcessPointExtension.java

package cn.sunline.adp.cedar.base.boot.spi;

import cn.sunline.adp.cedar.base.boot.plugin.IAdditionalExtension;

public interface BootProcessPointExtension
    extends IAdditionalExtension
{

    public abstract void serverStartBefore();

    public abstract void serverStartAfter();

    public static final String POINT = "edsp-micro-core.process.boot";
}
