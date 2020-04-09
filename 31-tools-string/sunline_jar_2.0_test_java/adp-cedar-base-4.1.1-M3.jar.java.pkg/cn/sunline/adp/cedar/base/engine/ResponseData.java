// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseData.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine:
//            ResponseHeaderData, OnlineConstant

public class ResponseData
{

    public ResponseData()
    {
        body = DataArea.buildWithEmpty();
        headerData = new ResponseHeaderData();
    }

    public ResponseData(String rspCode, Map body)
    {
        this.body = DataArea.buildWithData(body);
        headerData = new ResponseHeaderData();
        this.body.getSystem();
        this.body.getCommRes();
        this.body.getOutput();
        this.body.getPrinter();
    }

    public ResponseData(DataArea body)
    {
        this.body = body;
        headerData = new ResponseHeaderData(body.getSystem());
    }

    public ResponseData(ResponseHeaderData headerData, DataArea body)
    {
        this.headerData = headerData;
        this.body = body;
    }

    public ResponseHeaderData getHeaderData()
    {
        return headerData;
    }

    public DataArea getBody()
    {
        return body;
    }

    public DataArea getData()
    {
        DataArea ret = DataArea.buildWithEmpty();
        ret.setSystem(headerData);
        ret.setCommRes(body.getCommRes());
        ret.setOutput(body.getOutput());
        ret.setPrinter(body.getPrinter());
        return ret;
    }

    public Map getDataEx()
    {
        Map data = new HashMap();
        data.put("head", headerData.getMap());
        data.put("body", body.getData());
        return data;
    }

    private final ResponseHeaderData headerData;
    private final DataArea body;
}
