// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlobalAttachment.java

package cn.sunline.adp.cedar.base.engine.transaction.distributed.bean;

import cn.sunline.adp.cedar.base.engine.HeaderDataConstants;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import java.util.Map;

public class GlobalAttachment extends MapListDataContext
{

    public GlobalAttachment()
    {
    }

    public GlobalAttachment(Map data)
    {
        super(data);
    }

    public String getBusiSeqNum()
    {
        return getString(HeaderDataConstants.BUSI_SEQ_NO);
    }

    public void setBusiSeqNum(String busiSeqNum)
    {
        setString(HeaderDataConstants.BUSI_SEQ_NO, busiSeqNum);
    }

    public String getCallSeqNum()
    {
        return getString(HeaderDataConstants.CALL_SEQ_NO);
    }

    public void setCallSeqNum(String callSeqNum)
    {
        setString(HeaderDataConstants.CALL_SEQ_NO, callSeqNum);
    }

    public String getConsumerCallSeqNum()
    {
        return getString(HeaderDataConstants.CONSUMER_CALL_SEQ_NO);
    }

    public void setConsumerCallSeqNum(String consumerCallSeqNum)
    {
        setString(HeaderDataConstants.CONSUMER_CALL_SEQ_NO, consumerCallSeqNum);
    }

    public String getLogLevel()
    {
        return getString("__logLevelKey__");
    }

    public void setLogLevel(String logLevel)
    {
        setString("__logLevelKey__", logLevel);
    }

    public Map getLogKeyValue()
    {
        return getMap("__logKeyValuekey__");
    }

    public void setLogKeyValue(Map logKeyValue)
    {
        put("__logKeyValuekey__", logKeyValue);
    }

    public Map getCommreq()
    {
        return getMap("__commReq__");
    }

    public void setCommreq(Map commreq)
    {
        put("__commReq__", commreq);
    }

    public String getTranCode()
    {
        return getString(HeaderDataConstants.TXN_CD);
    }

    public void setTranCode(String tranCode)
    {
        setString(HeaderDataConstants.TXN_CD, tranCode);
    }

    public String getGlobalTranSeqNo()
    {
        return getString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO);
    }

    public void setGlobalTranSeqNo(String globalTranSeqNo)
    {
        setString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO, globalTranSeqNo);
    }

    private final String LOG_LEVEL_KEY = "__logLevelKey__";
    private final String LOG_KEY_VALUE_KEY = "__logKeyValuekey__";
    private final String COMM_REQ_KEY = "__commReq__";
}
