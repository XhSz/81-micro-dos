// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JSONParser.java

package cn.sunline.adp.cedar.base.pkg;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.core.util.JsonUtil;
import cn.sunline.adp.metadata.model.datainterface.DataInterface;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.pkg:
//            PkgParser, PkgMode

public class JSONParser
    implements PkgParser
{

    public JSONParser()
    {
    }

    public static JSONParser get()
    {
        return instance;
    }

    public DataArea parse(String data, DataInterface dataInterface, PkgMode mode)
    {
        Map ret = new HashMap();
        ret = StringUtil.isBlank(data) ? ret : JsonUtil.parse(data);
        return DataArea.buildWithData(ret);
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
            throw new RuntimeException(BaseConst.JSONParser01, e);
        }
        return parse(data, dataInterface, mode);
    }

    private static final JSONParser instance = new JSONParser();

}
