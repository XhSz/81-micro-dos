// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnvDataHelper.java

package cn.sunline.adp.cedar.base.engine;

import java.util.Map;

public class EnvDataHelper
{

    public EnvDataHelper(Object runEnvMap)
    {
        mdc_ = (Map)runEnvMap;
    }

    public Map getData()
    {
        return mdc_;
    }

    private Map mdc_;
}
