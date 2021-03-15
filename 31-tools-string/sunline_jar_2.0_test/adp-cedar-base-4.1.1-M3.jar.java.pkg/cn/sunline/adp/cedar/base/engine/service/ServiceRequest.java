// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServiceRequest.java

package cn.sunline.adp.cedar.base.engine.service;

import cn.sunline.adp.cedar.base.engine.OnlineConstant;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.service:
//            ServiceRequestHeader

public class ServiceRequest
{

    public ServiceRequest(Map input)
    {
        requestHeader = new ServiceRequestHeader();
        requestBody = DataArea.buildWithEmpty();
        requestBody.setInput(input);
        requestBody.setCommReq(new HashMap());
        requestBody.setSystem(new HashMap());
    }

    public ServiceRequest(DataArea requestBody)
    {
        requestHeader = new ServiceRequestHeader();
        this.requestBody = DataArea.buildWithEmpty();
        this.requestBody = requestBody;
    }

    public DataArea getRequestBody()
    {
        return requestBody;
    }

    public ServiceRequestHeader getRequestHeader()
    {
        return requestHeader;
    }

    public Map getData()
    {
        Map data = new HashMap();
        data.put("head", requestHeader.getMap());
        data.put("body", requestBody.getData());
        return data;
    }

    private final ServiceRequestHeader requestHeader;
    private DataArea requestBody;
}
