// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TxnExecutor.java

package cn.sunline.adp.boot.cedar.launch.online;

import cn.sunline.adp.boot.cedar.launch.IDERunner;
import cn.sunline.adp.boot.cedar.launch.IOnlineIDERunner;
import cn.sunline.edsp.base.factories.FactoriesLoader;

public class TxnExecutor
    implements IDERunner
{

    public TxnExecutor()
    {
    }

    public void process(String args[])
    {
        IOnlineIDERunner runner = (IOnlineIDERunner)FactoriesLoader.getNewestFactory(cn/sunline/adp/boot/cedar/launch/IOnlineIDERunner);
        runner._run(args);
    }
}
