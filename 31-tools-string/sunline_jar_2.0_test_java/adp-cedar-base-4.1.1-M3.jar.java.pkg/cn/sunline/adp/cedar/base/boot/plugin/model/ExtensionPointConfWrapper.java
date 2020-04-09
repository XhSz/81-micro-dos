// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionPointConfWrapper.java

package cn.sunline.adp.cedar.base.boot.plugin.model;

import cn.sunline.adp.cedar.base.boot.plugin.util.PluginUtil;
import cn.sunline.adp.cedar.base.constant.CorePluginConstantDef;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.boot.plugin.model:
//            ExtensionConf, PluginWrapper, ExtensionPointConf

public class ExtensionPointConfWrapper
{

    public ExtensionPointConfWrapper(String pointId, PluginWrapper pluginConf, ExtensionPointConf point)
    {
        this.pointId = pointId;
        owner = pluginConf;
        this.point = point;
        try
        {
            pointClazz = PluginUtil.classForName(point.getClazz());
        }
        catch(Exception ex)
        {
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C062(), ex, new Object[] {
                pluginConf.getPluginConfFileName(), pointId, point.getClazz()
            });
        }
    }

    public void addExtensionConf(PluginWrapper owner, ExtensionConf extension)
    {
        Class extensionClzzz = null;
        try
        {
            extensionClzzz = PluginUtil.classForName(extension.getClazzImpl());
        }
        catch(Exception ex)
        {
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C058(), ex, new Object[] {
                owner.getPluginConfFileName(), extension.getId(), extension.getClazzImpl()
            });
        }
        if(!pointClazz.isAssignableFrom(extensionClzzz))
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C059(), new Object[] {
                extension.getId(), extension.getClazzImpl(), point.getClazz()
            });
        if(PluginUtil.isEmpty(extension.getId()))
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C060(), new Object[] {
                owner.getPluginConfFileName(), extension.getClazzImpl()
            });
        if(index_extension.get(extension.getId()) != null)
        {
            ExtensionConf duplicateExtensionConf = (ExtensionConf)index_extension.get(extension.getId());
            String duplicatePluginConfFile = ((PluginWrapper)index_extensionBelongPlugin.get(duplicateExtensionConf)).getPluginConfFileName();
            throw PluginUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CorePluginConstantDef.SPC_CO.C061(), new Object[] {
                extension.getId(), owner.getPluginConfFileName(), duplicatePluginConfFile
            });
        } else
        {
            index_extension.put(extension.getId(), extension);
            extension_coll.add(extension);
            index_extensionBelongPlugin.put(extension, owner);
            return;
        }
    }

    public ExtensionConf getExtensionConf(String extensionId)
    {
        return (ExtensionConf)index_extension.get(extensionId);
    }

    public ExtensionPointConf getPoint()
    {
        return point;
    }

    public PluginWrapper getOwner()
    {
        return owner;
    }

    public String getPointId()
    {
        return pointId;
    }

    public Class getPointClazz()
    {
        return pointClazz;
    }

    public List getExtensions()
    {
        return extension_coll;
    }

    private final String pointId;
    private final PluginWrapper owner;
    private final ExtensionPointConf point;
    private final Class pointClazz;
    private final List extension_coll = new ArrayList();
    private final Map index_extension = new LinkedHashMap();
    private static Map index_extensionBelongPlugin = new HashMap();

}
