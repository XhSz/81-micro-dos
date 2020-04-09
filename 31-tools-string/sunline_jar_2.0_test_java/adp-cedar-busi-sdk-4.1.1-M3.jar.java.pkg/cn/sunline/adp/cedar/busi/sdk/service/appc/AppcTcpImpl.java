// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcTcpImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.io.IOException;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.service.appc:
//            LengthPrefixTcpClient

public class AppcTcpImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcTcp
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.Appc
{

    public AppcTcpImpl()
    {
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        LengthPrefixTcpClient sc;
        sc = new LengthPrefixTcpClient(getEncoding(), getLeftPadding().booleanValue(), getLengthPrefixLength().intValue(), getPaddingChar());
        try
        {
            sc.setConnectTimeout(getConnectTimeoutInMs().intValue());
            sc.connect(getIp(), getPort().intValue());
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E015(e);
        }
        String s = sc.call(sendBuffer);
        try
        {
            sc.disconnect();
        }
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C012());
        }
        return s;
        Exception exception;
        exception;
        try
        {
            sc.disconnect();
        }
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C012());
        }
        throw exception;
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        return call(sendBuffer, null);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/service/appc/AppcTcpImpl);

}
