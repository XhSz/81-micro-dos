// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransExecutorCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class TransExecutorCompImpl
{
    public static abstract class TransExecutorRemote
        implements BaseComp.TransExecutor
    {

        public String getAppcAbstId()
        {
            return appcAbstId;
        }

        public void setAppcAbstId(String appcAbstId)
        {
            this.appcAbstId = appcAbstId;
        }

        public String getPkgAbstId()
        {
            return pkgAbstId;
        }

        public void setPkgAbstId(String pkgAbstId)
        {
            this.pkgAbstId = pkgAbstId;
        }

        private String appcAbstId;
        private String pkgAbstId;

        public TransExecutorRemote()
        {
        }
    }

    public static abstract class TransExecutorLocal
        implements BaseComp.TransExecutor
    {

        public TransExecutorLocal()
        {
        }
    }


    public TransExecutorCompImpl()
    {
    }
}
