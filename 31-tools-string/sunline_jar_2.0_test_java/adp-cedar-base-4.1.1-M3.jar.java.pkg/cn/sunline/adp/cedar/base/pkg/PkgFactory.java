// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PkgFactory.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.edsp.base.factories.FactoriesLoader;
import cn.sunline.edsp.base.util.lang.StringUtil;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgParserProxy, PkgParser, PkgWrapperProxy, PkgWrapper

public class PkgFactory
{
    public static class PkgType
    {

        public static final String ltts = "ltts";
        public static final String json = "json";
        public static final String xml = "xml";
        public static final String tlxml = "tlxml";
        public static final String dlxml = "dlxml";

        public PkgType()
        {
        }
    }


    public PkgFactory()
    {
    }

    public static PkgFactory get()
    {
        return instance;
    }

    public PkgParser getPkgParser()
    {
        return getPkgParser("json");
    }

    public PkgParser getPkgParser(String pkgType)
    {
        if(pkgType == null)
            pkgType = "json";
        return new PkgParserProxy((PkgParser)FactoriesLoader.getFactoryById(cn/sunline/adp/cedar/base/pkg/PkgParser, pkgType));
    }

    public PkgWrapper getPkgWrapper()
    {
        return getPkgWrapper("json");
    }

    public PkgWrapper getPkgWrapper(String pkgType)
    {
        if(pkgType == null)
            pkgType = "json";
        return new PkgWrapperProxy((PkgWrapper)FactoriesLoader.getFactoryById(cn/sunline/adp/cedar/base/pkg/PkgWrapper, pkgType));
    }

    public String getPkgencoding(String encoding)
    {
        if(StringUtil.isEmpty(encoding))
            return "UTF-8";
        else
            return encoding;
    }

    public String getDefaultPkgencoding()
    {
        return "UTF-8";
    }

    private static final String defaultPkgType = "json";
    private static final String defaultPkgEncoding = "UTF-8";
    private static final PkgFactory instance = new PkgFactory();

}
