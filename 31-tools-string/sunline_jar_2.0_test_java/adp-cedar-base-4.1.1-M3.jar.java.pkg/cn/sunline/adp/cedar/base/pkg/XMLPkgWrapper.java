// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLPkgWrapper.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.util.XmlUtil;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;
import java.io.UnsupportedEncodingException;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgWrapper, PkgMode

public class XMLPkgWrapper
    implements PkgWrapper
{

    public XMLPkgWrapper()
    {
    }

    public static XMLPkgWrapper get()
    {
        return instance;
    }

    public String format(DataArea response, DataInterface dataInterface, PkgMode mode)
    {
        return XmlUtil.format(response.getData());
    }

    public byte[] format(DataArea response, String encoding, DataInterface dataInterface, PkgMode mode)
    {
        String data = format(response, dataInterface, mode);
        try
        {
            return data.getBytes(encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(BaseConst.XMLPkgWrapper01, e);
        }
    }

    private static final XMLPkgWrapper instance = new XMLPkgWrapper();

}
