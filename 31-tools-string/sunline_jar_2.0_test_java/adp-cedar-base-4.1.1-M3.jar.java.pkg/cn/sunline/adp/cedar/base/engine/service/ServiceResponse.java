// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceResponse.java

package cn.sunline.adp.cedar.base.engine.service;

import cn.sunline.adp.cedar.base.engine.OnlineConstant;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.service:
//            ServiceResponseHeader

public class ServiceResponse
{

    public ServiceResponse(DataArea responseData)
    {
        this.responseData = responseData;
    }

    public DataArea getResponseData()
    {
        return responseData;
    }

    public String getRespStr()
    {
        return respStr;
    }

    public void setRespStr(String respStr)
    {
        this.respStr = respStr;
    }

    public ServiceResponseHeader getResponseHeader()
    {
        return responseHeader;
    }

    public void setResponseData(DataArea responseData)
    {
        this.responseData = responseData;
    }

    public Map getData()
    {
        Map data = new HashMap();
        data.put("head", responseHeader.getMap());
        data.put("body", responseData.getData());
        return data;
    }

    private String respStr;
    private DataArea responseData;
    private final ServiceResponseHeader responseHeader = new ServiceResponseHeader();
}
