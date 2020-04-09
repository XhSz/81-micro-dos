// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class PackCompImpl
{
    public static abstract class PackXml
        implements BaseComp.Pack
    {

        public PackXml()
        {
        }
    }

    public static abstract class PackJson
        implements BaseComp.Pack
    {

        public PackJson()
        {
        }
    }

    public static abstract class PackLtts
        implements BaseComp.Pack
    {

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        private String encoding;

        public PackLtts()
        {
        }
    }


    public PackCompImpl()
    {
    }
}
