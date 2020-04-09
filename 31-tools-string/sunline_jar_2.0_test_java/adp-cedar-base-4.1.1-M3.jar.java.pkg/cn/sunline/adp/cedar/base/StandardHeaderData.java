// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StandardHeaderData.java

package cn.sunline.adp.cedar.base;

import cn.sunline.adp.cedar.base.engine.HeaderDataConstants;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import java.util.Map;

public class StandardHeaderData
{
    public static class Response extends MapListDataContext
    {

        public String getBusiSeqNo()
        {
            return getString(HeaderDataConstants.BUSI_SEQ_NO);
        }

        public void setBusiSeqNo(String busiSeqNo)
        {
            setString(HeaderDataConstants.BUSI_SEQ_NO, busiSeqNo);
        }

        public String getCallSeqNo()
        {
            return getString(HeaderDataConstants.CALL_SEQ_NO);
        }

        public void setCallSeqNo(String callSeqNo)
        {
            setString(HeaderDataConstants.CALL_SEQ_NO, callSeqNo);
        }

        public String getResponseCode()
        {
            return getString(HeaderDataConstants.RESPONSE_CODE);
        }

        public void setResponseCode(String responseCode)
        {
            setString(HeaderDataConstants.RESPONSE_CODE, responseCode);
        }

        public String getResponseMsg()
        {
            return getString(HeaderDataConstants.RESPONSE_MSG);
        }

        public void setResponseMsg(String responseMsg)
        {
            setString(HeaderDataConstants.RESPONSE_MSG, responseMsg);
        }

        public String getRetStatus()
        {
            return getString(HeaderDataConstants.RET_Status);
        }

        public void setRetStatus(String retStatus)
        {
            setString(HeaderDataConstants.RET_Status, retStatus);
        }

        public String getResponseType()
        {
            return getString(HeaderDataConstants.RESPONSE_TYPE);
        }

        public void setResponseType(String responseType)
        {
            setString(HeaderDataConstants.RESPONSE_TYPE, responseType);
        }

        public String getProviderDcn()
        {
            return getString(HeaderDataConstants.PROVIDER_DCN);
        }

        public void setProviderDcn(String providerDcn)
        {
            setString(HeaderDataConstants.PROVIDER_DCN, providerDcn);
        }

        public String getGlobalTranSeqNo()
        {
            return getString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO);
        }

        public void setGlobalTranSeqNo(String globalTranSeqNo)
        {
            setString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO, globalTranSeqNo);
        }

        public String getDTXStatus()
        {
            return getString(HeaderDataConstants.DTX_STATUS);
        }

        public void setDTXStatus(String dtxTranStatus)
        {
            setString(HeaderDataConstants.DTX_STATUS, dtxTranStatus);
        }

        public String getDTXErrortx()
        {
            return getString(HeaderDataConstants.DTX_ERRORTX);
        }

        public void setDTXErrortx(String dtxErrortx)
        {
            setString(HeaderDataConstants.DTX_ERRORTX, dtxErrortx);
        }

        public String getDTXErrorcd()
        {
            return getString(HeaderDataConstants.DTX_ERRORCD);
        }

        public void setDTXErrorcd(String dtxErrorcd)
        {
            setString(HeaderDataConstants.DTX_ERRORCD, dtxErrorcd);
        }

        public Response()
        {
        }

        public Response(Map data)
        {
            super(data);
        }

        public Response(MapListDataContext dataContext)
        {
            super(dataContext);
        }
    }

    public static class Request extends MapListDataContext
    {

        public String getServiceCode()
        {
            return getString(HeaderDataConstants.SERVICE_CODE);
        }

        public void setServiceCode(String serviceCode)
        {
            setString(HeaderDataConstants.SERVICE_CODE, serviceCode);
        }

        public String getBusiSeqNo()
        {
            return getString(HeaderDataConstants.BUSI_SEQ_NO);
        }

        public void setBusiSeqNo(String busiSeqNo)
        {
            setString(HeaderDataConstants.BUSI_SEQ_NO, busiSeqNo);
        }

        public String getCallSeqNo()
        {
            return getString(HeaderDataConstants.CALL_SEQ_NO);
        }

        public void setCallSeqNo(String callSeqNo)
        {
            setString(HeaderDataConstants.CALL_SEQ_NO, callSeqNo);
        }

        public String getOrigSysId()
        {
            return getString(HeaderDataConstants.ORIG_SYS_ID);
        }

        public void setOrigSysId(String origSysId)
        {
            setString(HeaderDataConstants.ORIG_SYS_ID, origSysId);
        }

        public String getOrigSrvId()
        {
            return getString(HeaderDataConstants.ORIG_SRV_ID);
        }

        public void setOrigSrvId(String origSrvId)
        {
            setString(HeaderDataConstants.ORIG_SRV_ID, origSrvId);
        }

        public String getConsumerCallSeqNo()
        {
            return getString(HeaderDataConstants.CONSUMER_CALL_SEQ_NO);
        }

        public void setConsumerCallSeqNo(String consumerCallSeqNo)
        {
            setString(HeaderDataConstants.CONSUMER_CALL_SEQ_NO, consumerCallSeqNo);
        }

        public String getConsumerDcn()
        {
            return getString(HeaderDataConstants.CONSUMER_DCN);
        }

        public void setConsumerDcn(String consumerDcn)
        {
            setString(HeaderDataConstants.CONSUMER_DCN, consumerDcn);
        }

        public String getConsumerSysId()
        {
            return getString(HeaderDataConstants.CONSUMER_SYS_ID);
        }

        public void setConsumerSysId(String consumerSysId)
        {
            setString(HeaderDataConstants.CONSUMER_SYS_ID, consumerSysId);
        }

        public String getConsumerSysVersion()
        {
            return getString(HeaderDataConstants.CONSUMER_SYS_VERSION);
        }

        public void setConsumerSysVersion(String consumerSysVersion)
        {
            setString(HeaderDataConstants.CONSUMER_SYS_VERSION, consumerSysVersion);
        }

        public String getConsumerSrvId()
        {
            return getString(HeaderDataConstants.CONSUMER_SRV_ID);
        }

        public void setConsumerSrvId(String consumerSrvId)
        {
            setString(HeaderDataConstants.CONSUMER_SRV_ID, consumerSrvId);
        }

        public String getTranTimestamp()
        {
            return getString(HeaderDataConstants.TRAN_TIMESTAMP);
        }

        public void setTranTimestamp(String tranTimestamp)
        {
            setString(HeaderDataConstants.TRAN_TIMESTAMP, tranTimestamp);
        }

        public String getUserLang()
        {
            return getString(HeaderDataConstants.USER_LANG);
        }

        public void setUserLang(String userLang)
        {
            setString(HeaderDataConstants.USER_LANG, userLang);
        }

        public String getReversalSeqNo()
        {
            return getString(HeaderDataConstants.REVERSAL_SEQ_NO);
        }

        public void setReversalSeqNo(String reversalSeqNo)
        {
            setString(HeaderDataConstants.REVERSAL_SEQ_NO, reversalSeqNo);
        }

        public String getGlobalTranSeqNo()
        {
            return getString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO);
        }

        public void setGlobalTranSeqNo(String globalTranSeqNo)
        {
            setString(HeaderDataConstants.GLOBAL_TRAN_SEQ_NO, globalTranSeqNo);
        }

        public boolean getInServiceMark()
        {
            String retStr = getString(HeaderDataConstants.IN_SERVICE_MARK);
            return Boolean.valueOf(retStr).booleanValue();
        }

        public void setInServiceMark(String inServiceMark)
        {
            setString(HeaderDataConstants.IN_SERVICE_MARK, inServiceMark);
        }

        public Request()
        {
        }

        public Request(Map data)
        {
            super(data);
        }

        public Request(MapListDataContext dataContext)
        {
            super(dataContext);
        }
    }


    public StandardHeaderData()
    {
    }
}
