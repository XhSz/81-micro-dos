// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionUtil.java

package cn.sunline.adp.cedar.base.boot.plugin.util;

import cn.sunline.adp.cedar.base.boot.plugin.*;
import cn.sunline.adp.cedar.base.boot.plugin.impl.PluginConfManager;
import cn.sunline.adp.cedar.base.boot.plugin.model.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.util:
//            PluginUtil, PluginConfUtil

public class ExtensionUtil
{

    public ExtensionUtil()
    {
    }

    public static IReplaceExtension getExtensionPointImpl(String pointId)
    {
        List extensions = PluginConfManager.getExtensionConfs(pointId);
        if(extensions == null || extensions.size() == 0)
            throw PluginUtil.wrapThrow("{} extension point[{}] has no any implementations", new Object[] {
                ExtensionPointType.replace.toString(), pointId
            });
        ExtensionConf lastExtensionConf = (ExtensionConf)extensions.get(extensions.size() - 1);
        IExtension extensionObj = PluginConfUtil.getExtensionObj(lastExtensionConf);
        if(!(extensionObj instanceof IReplaceExtension))
            throw PluginUtil.wrapThrow("{} extension point[{}]'s implementation {} must be implemented interface[{}]", new Object[] {
                ExtensionPointType.replace.toString(), pointId, extensionObj.getClass().toString(), cn/sunline/adp/cedar/base/boot/plugin/IReplaceExtension.toString()
            });
        else
            return (IReplaceExtension)extensionObj;
    }

    public static IReplaceExtension getExtensionPointImpl(String pointId, String extensionId)
    {
        ExtensionConf extensionConf = PluginConfManager.getExtensionConf(pointId, extensionId);
        if(null == extensionConf)
            throw PluginUtil.wrapThrow("{} extension point[{}] has no the implementation {}", new Object[] {
                ExtensionPointType.replace.toString(), pointId, extensionId
            });
        else
            return (IReplaceExtension)PluginConfUtil.getExtensionObj(extensionConf);
    }

    public static void executeExtensionPoint(String pointId, IProcess p, String extensionIds[])
    {
        if(extensionIds == null || extensionIds.length == 0)
            return;
        List extensions = new ArrayList();
        String as[] = extensionIds;
        int i = as.length;
        for(int j = 0; j < i; j++)
        {
            String extensionId = as[j];
            ExtensionConf extensionConf = PluginConfManager.getExtensionConf(pointId, extensionId);
            extensions.add(extensionConf);
        }

        _executeExtensionPoint(extensions, pointId, p);
    }

    public static List getAdditionalExtensionImpl(String pointId)
    {
        List ret = new ArrayList();
        List extensions = PluginConfManager.getExtensionConfs(pointId);
        if(extensions == null || extensions.size() == 0)
            return Collections.EMPTY_LIST;
        IAdditionalExtension extensionObj;
        for(Iterator iterator = extensions.iterator(); iterator.hasNext(); ret.add(extensionObj))
        {
            ExtensionConf extension = (ExtensionConf)iterator.next();
            IExtension _extensionObj = PluginConfUtil.getExtensionObj(extension);
            if(!(_extensionObj instanceof IAdditionalExtension))
                throw PluginUtil.wrapThrow("{} extension point[{}]'s implementation {} must be implemented interface[{}]", new Object[] {
                    ExtensionPointType.additional.toString(), pointId, _extensionObj.getClass().toString(), cn/sunline/adp/cedar/base/boot/plugin/IAdditionalExtension.toString()
                });
            extensionObj = (IAdditionalExtension)PluginConfUtil.getExtensionObj(extension);
        }

        return ret;
    }

    public static void executeExtensionPoint(String pointId, IProcess p)
    {
        ExtensionPointConf extensionPointConf = PluginConfManager.getExtensionPointConf(pointId);
        if(extensionPointConf == null)
            throw PluginUtil.wrapThrow("extension point[{}] not exist", new Object[] {
                pointId
            });
        if(extensionPointConf.getPointType() != ExtensionPointType.additional)
            throw PluginUtil.wrapThrow("extension point[{}]'s type must be the {}", new Object[] {
                pointId, "additional"
            });
        List extensions = PluginConfManager.getExtensionConfs(pointId);
        if(extensions == null)
        {
            return;
        } else
        {
            _executeExtensionPoint(extensions, pointId, p);
            return;
        }
    }

    private static void _executeExtensionPoint(List extensions, String pointId, IProcess p)
    {
        Iterator iterator = extensions.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            ExtensionConf extensionConf = (ExtensionConf)iterator.next();
            IExtension _extensionObj = PluginConfUtil.getExtensionObj(extensionConf);
            if(!(_extensionObj instanceof IAdditionalExtension))
                throw PluginUtil.wrapThrow("{} extension point[{}]'s implementation {} must be implemented interface[{}]", new Object[] {
                    ExtensionPointType.additional.toString(), pointId, _extensionObj.getClass().toString(), cn/sunline/adp/cedar/base/boot/plugin/IAdditionalExtension.toString()
                });
            IAdditionalExtension extensionObj = (IAdditionalExtension)PluginConfUtil.getExtensionObj(extensionConf);
            try
            {
                p.run(extensionObj);
            }
            catch(Exception e)
            {
                LOGGERPLUGIN.error("extension implementation {} process failed", extensionConf.getClazzImpl(), e);
                throw PluginUtil.wrapThrow((new StringBuilder()).append("extension implementation {} process failed: ").append(e.getMessage()).toString(), e, new Object[] {
                    extensionConf.getClazzImpl()
                });
            }
        } while(true);
    }

    private static final Logger LOGGERPLUGIN = LoggerFactory.getLogger(cn/sunline/adp/cedar/base/boot/plugin/util/ExtensionUtil);

}
