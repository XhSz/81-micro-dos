// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcTuxedoImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.com.bank.TuxCall;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.cedar.net.tcp.socket.protocol.pkgheader.PkgHeaderUtil;
import java.util.Map;

public class AppcTuxedoImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcTuxedo
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc
{

    public AppcTuxedoImpl()
    {
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        Integer pkgLength = super.getPkgLength();
        if(pkgLength == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E016();
        int headerLen = pkgLength.intValue();
        int prefixLen = 8;
        try
        {
            int len = headerLen + sendBuffer.getBytes(super.getEncoding()).length;
            String msg = pad(sendBuffer, " ", len, true);
            byte addr[] = getAddress().getBytes(super.getEncoding());
            byte sendBytes[] = msg.getBytes(super.getEncoding());
            msg = (new StringBuilder()).append(pad((new StringBuilder()).append(len).append("").toString(), "0", prefixLen, true)).append(msg).toString();
            sendBytes = msg.getBytes(super.getEncoding());
            if(log.isDebugEnabled())
                log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C014(), new Object[] {
                    getAddress(), msg
                });
            byte recvBytes[] = TuxCall.SendRecvEx(addr, sendBytes);
            if(log.isDebugEnabled())
                log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C015(), new Object[] {
                    new String(recvBytes, super.getEncoding())
                });
            if(recvBytes.length < headerLen + prefixLen)
            {
                throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E017();
            } else
            {
                int pkgBodySize = recvBytes.length - headerLen - prefixLen;
                byte pkgBodyBytes[] = new byte[pkgBodySize];
                PkgHeaderUtil.byteArrayCopy(recvBytes, headerLen + prefixLen, pkgBodyBytes, 0, pkgBodySize);
                return new String(pkgBodyBytes, super.getEncoding());
            }
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

    private String pad(String s, String pad, int len, boolean left)
    {
        if(s.length() > len)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E019(Integer.valueOf(len), Integer.valueOf(s.length()));
        StringBuffer ret = new StringBuffer();
        if(!left)
            ret.append(s);
        for(int i = 0; i < len - s.length(); i++)
            ret.append(pad);

        if(left)
        {
            ret.append(s);
        } else
        {
            for(int i = 0; i < len; i++)
                ret.append(pad);

        }
        return ret.toString();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/component/BaseComp$Appc);

}
