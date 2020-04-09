// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlComponentInstanceController.java

package cn.sunline.adp.cedar.base.component.impl;

import cn.sunline.adp.cedar.base.component.ComponentInstance;
import cn.sunline.adp.cedar.base.component.ComponentInstanceController;
import cn.sunline.adp.metadata.base.odb.OdbFactory;
import cn.sunline.adp.metadata.base.odb.OdbManager;
import cn.sunline.adp.metadata.model.component.ComponentInstanceConf;

// Referenced classes of package cn.sunline.adp.cedar.base.component.impl:
//            XmlComponentInstance

public class XmlComponentInstanceController
    implements ComponentInstanceController
{

    public XmlComponentInstanceController()
    {
    }

    public ComponentInstance getComponentInstance(String abstId)
    {
        ComponentInstanceConf config = (ComponentInstanceConf)OdbFactory.get().getOdbManager(cn/sunline/adp/metadata/model/component/ComponentInstanceConf).selectByIndex(cn/sunline/adp/metadata/model/component/ComponentInstanceConf$Abstr_Index, new Object[] {
            abstId
        });
        if(config == null)
            return null;
        else
            return new XmlComponentInstance(config);
    }
}
