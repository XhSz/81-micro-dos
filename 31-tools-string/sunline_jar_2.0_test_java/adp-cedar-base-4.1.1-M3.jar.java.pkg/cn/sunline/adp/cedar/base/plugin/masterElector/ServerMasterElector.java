// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerMasterElector.java

package cn.sunline.adp.cedar.base.plugin.masterElector;


public interface ServerMasterElector
{

    public abstract void init();

    public abstract boolean becomeMaster();
}
