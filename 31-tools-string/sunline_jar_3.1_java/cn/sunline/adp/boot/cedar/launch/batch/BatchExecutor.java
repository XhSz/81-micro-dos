// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BatchExecutor.java

package cn.sunline.adp.boot.cedar.launch.batch;

import cn.sunline.adp.boot.cedar.launch.*;
import cn.sunline.edsp.base.factories.FactoriesLoader;

public class BatchExecutor
    implements IDERunner
{

    public BatchExecutor()
    {
    }

    public void process(String args[])
    {
        IDERunUtil.init(args);
        IBatchIDERunner runner = (IBatchIDERunner)FactoriesLoader.getNewestFactory(cn/sunline/adp/boot/cedar/launch/IBatchIDERunner);
        runner.run(args);
    }
}
