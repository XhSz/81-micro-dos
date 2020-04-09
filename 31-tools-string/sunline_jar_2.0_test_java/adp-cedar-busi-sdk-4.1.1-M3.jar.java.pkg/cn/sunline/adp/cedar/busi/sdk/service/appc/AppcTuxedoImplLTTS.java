// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcTuxedoImplLTTS.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.com.bank.TuxCall;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.util.Map;

public class AppcTuxedoImplLTTS extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcTuxedo
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc
{

    public AppcTuxedoImplLTTS()
    {
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        try
        {
            byte addr[] = getAddress().getBytes(getEncoding());
            byte sendBytes[] = sendBuffer.getBytes(getEncoding());
            if(log.isDebugEnabled())
                log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C016(), new Object[] {
                    getAddress(), sendBuffer
                });
            byte recvBytes[] = TuxCall.SendRecvEx(addr, sendBytes);
            String result = new String(recvBytes, getEncoding());
            if(log.isDebugEnabled())
                log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C017(), new Object[] {
                    result
                });
            return result;
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E018(e);
        }
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        return call(sendBuffer, null);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/component/BaseComp$Appc);

}
