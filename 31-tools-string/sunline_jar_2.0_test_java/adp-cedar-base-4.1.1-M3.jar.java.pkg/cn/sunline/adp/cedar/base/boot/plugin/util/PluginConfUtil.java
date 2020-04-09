// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PluginConfUtil.java

package cn.sunline.adp.cedar.base.boot.plugin.util;

import cn.sunline.adp.cedar.base.boot.plugin.IExtension;
import cn.sunline.adp.cedar.base.boot.plugin.model.ExtensionConf;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.util:
//            PluginUtil

public class PluginConfUtil
{

    public PluginConfUtil()
    {
    }

    public static IExtension getExtensionObj(ExtensionConf extensionConf)
    {
        if(!extensionConf.isSingleton())
            return _getExtensionObj(extensionConf);
        IExtension ret = (IExtension)index_extensionObj.get(extensionConf);
        if(ret == null)
            index_extensionObj.put(extensionConf, ret = _getExtensionObj(extensionConf));
        return ret;
    }

    private static IExtension _getExtensionObj(ExtensionConf extensionConf)
    {
        try
        {
            return (IExtension)PluginUtil.classForName(extensionConf.getClazzImpl()).newInstance();
        }
        catch(Exception e)
        {
            throw PluginUtil.wrapThrow("extension implementation [{}] creation failed", e, new Object[] {
                extensionConf.getClazzImpl()
            });
        }
    }

    private static Map index_extensionObj = new ConcurrentHashMap();

}
