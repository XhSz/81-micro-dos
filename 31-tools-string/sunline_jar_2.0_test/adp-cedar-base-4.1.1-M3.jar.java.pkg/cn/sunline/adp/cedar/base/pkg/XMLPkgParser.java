// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLPkgParser.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.util.XmlUtil;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;
import java.io.UnsupportedEncodingException;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgParser, PkgMode

public class XMLPkgParser
    implements PkgParser
{

    public XMLPkgParser()
    {
    }

    public static XMLPkgParser get()
    {
        return instance;
    }

    public DataArea parse(String data, DataInterface dataInterface, PkgMode mode)
    {
        return DataArea.buildWithData(XmlUtil.parse(data));
    }

    public DataArea parse(byte bytes[], String encoding, DataInterface dataInterface, PkgMode mode)
    {
        String data = "";
        try
        {
            data = new String(bytes, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(BaseConst.XMLPkgParser01, e);
        }
        return parse(data, dataInterface, mode);
    }

    private static final XMLPkgParser instance = new XMLPkgParser();

}
