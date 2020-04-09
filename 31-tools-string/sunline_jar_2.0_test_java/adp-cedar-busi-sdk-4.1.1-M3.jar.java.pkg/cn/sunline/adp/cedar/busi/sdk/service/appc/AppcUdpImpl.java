// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcUdpImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.UDPClient;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.net.SocketTimeoutException;
import java.util.Map;

public class AppcUdpImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcUdp
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc
{

    public AppcUdpImpl()
    {
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        String result;
        UDPClient udp;
        result = null;
        udp = new UDPClient(getIp(), getPort().intValue());
        udp.setTimeout(60000);
        udp.setRecvBufferSize(4096);
        if(log.isDebugEnabled())
            log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C018(), new Object[] {
                sendBuffer, getEncoding()
            });
        try
        {
            udp.sendString(sendBuffer, getEncoding());
        }
        catch(SocketTimeoutException e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E020(e);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E021(e);
        }
        if(getNeedReturn().booleanValue())
            try
            {
                result = udp.recvString(getEncoding());
                if(log.isDebugEnabled())
                    log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C019(), new Object[] {
                        result
                    });
            }
            catch(SocketTimeoutException e)
            {
                throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E022(e);
            }
            catch(Exception e)
            {
                throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E023(e);
            }
        udp.close();
        break MISSING_BLOCK_LABEL_190;
        Exception exception;
        exception;
        udp.close();
        throw exception;
        return result;
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        return call(sendBuffer, null);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/component/BaseComp$Appc);

}
