// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlEntityLoader.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.lang.Symbol;
import cn.sunline.ltts.frw.model.ModelFactory;
import cn.sunline.ltts.frw.model.dm.internal.WebComponent;
import cn.sunline.ltts.frw.model.resource.PatternModelFilesLoader;
import java.util.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            XmlEntityOdbEntityDao, XmlConfigManager, ModelConfigUtil, ModelFactoryUtil, 
//            ModelConfig

public class XmlEntityLoader extends ModelConfig.TypedModelLoader
{
    public static final class PartnerKey extends Enum
        implements Symbol
    {

        public static PartnerKey[] values()
        {
            return (PartnerKey[])$VALUES.clone();
        }

        public static PartnerKey valueOf(String name)
        {
            return (PartnerKey)Enum.valueOf(cn/sunline/ltts/frw/model/util/XmlEntityLoader$PartnerKey, name);
        }

        public static final PartnerKey LastModified;
        public static final PartnerKey ModelFile;
        private static final PartnerKey $VALUES[];

        static 
        {
            LastModified = new PartnerKey("LastModified", 0);
            ModelFile = new PartnerKey("ModelFile", 1);
            $VALUES = (new PartnerKey[] {
                LastModified, ModelFile
            });
        }

        private PartnerKey(String s, int i)
        {
            super(s, i);
        }
    }


    public XmlEntityLoader()
    {
    }

    public XmlEntityLoader(String id, String longname, String path[], String modelType, ModelConfig.Group owner)
    {
        this(id, longname, path, modelType);
        setOwner(owner);
    }

    public XmlEntityLoader(String id, String longname, String path[], String modelType)
    {
        setId(id);
        setLongname(longname);
        this.path = path;
        setModelType(modelType);
    }

    protected ModelConfigUtil.LoaderHelper createHelper()
    {
        ModelConfigUtil.LoaderHelper ret = new ModelConfigUtil.LoaderHelper();
        ModelFilesLoader loader = new PatternModelFilesLoader(path);
        XmlConfigManager xmlConfigManager = initXmlConfigManager();
        XmlEntityOdbEntityDao dao = new XmlEntityOdbEntityDao(getType(), xmlConfigManager, loader, isExitOnError());
        ret.loader = dao;
        ret.dao = dao;
        ret.changeChecker = dao;
        return ret;
    }

    public String[] getPath()
    {
        return path;
    }

    public void setPath(String path[])
    {
        this.path = path;
    }

    public Boolean getWebpage()
    {
        return webpage;
    }

    public void setWebpage(Boolean webpage)
    {
        this.webpage = webpage;
    }

    private XmlConfigManager initXmlConfigManager()
    {
        if(webpage != null && webpage.booleanValue())
        {
            List classes = new ArrayList();
            List components = ModelFactoryUtil.getModelFactory().getModels(cn/sunline/ltts/frw/model/dm/internal/WebComponent);
            WebComponent com;
            for(Iterator i$ = components.iterator(); i$.hasNext(); classes.add(com.getJavaClass()))
                com = (WebComponent)i$.next();

            return new XmlConfigManager((Class[])classes.toArray(new Class[classes.size()]));
        } else
        {
            return ModelFactoryUtil.xcm;
        }
    }

    private String path[];
    private Boolean webpage;
}
