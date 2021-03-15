// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestHeaderData.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.StandardHeaderData;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine:
//            HeaderDataConstants, MapListDataContext

public class RequestHeaderData extends cn.sunline.adp.cedar.base.StandardHeaderData.Request
{

    public RequestHeaderData()
    {
    }

    public RequestHeaderData(Map data)
    {
        super(data);
    }

    public RequestHeaderData(MapListDataContext data)
    {
        super(data);
    }

    public Map getSystemProfile()
    {
        return getMap("profile");
    }

    public boolean enableErrorStatck()
    {
        return "1".equals(getString(HeaderDataConstants.RET_ERROR_STACK));
    }

    public boolean enableDBRollback()
    {
        return "1".equals(getString(HeaderDataConstants.DB_ROLLBACK));
    }
}
