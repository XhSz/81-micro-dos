// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequestData.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine:
//            RequestHeaderData, OnlineConstant, MapListDataContext

public class RequestData
{

    public RequestData(String message, DataArea requestDataArea)
    {
        this(message, new RequestHeaderData(requestDataArea.getSystem()), requestDataArea);
    }

    public RequestData(String message, RequestHeaderData requestHeader, DataArea body)
    {
        this.message = message;
        this.requestHeader = requestHeader;
        this.body = body;
    }

    public String getMessage()
    {
        return message;
    }

    public String getInputData(String name)
    {
        return body.getInput().getString(name);
    }

    public RequestHeaderData getRequestHeader()
    {
        return requestHeader;
    }

    public MapListDataContext getCommReq()
    {
        return body.getCommReq();
    }

    public MapListDataContext getInput()
    {
        return body.getInput();
    }

    public DataArea getBody()
    {
        return body;
    }

    public Map getData()
    {
        Map data = new HashMap();
        data.put("head", requestHeader.getMap());
        data.put("body", body.getData());
        return data;
    }

    private final String message;
    private final RequestHeaderData requestHeader;
    private final DataArea body;
}
