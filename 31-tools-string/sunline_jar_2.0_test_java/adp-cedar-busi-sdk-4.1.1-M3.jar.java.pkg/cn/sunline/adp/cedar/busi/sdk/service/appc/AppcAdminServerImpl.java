// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcAdminServerImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.cedar.net.tcp.socket.util.SocketUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.service.appc:
//            LengthPrefixTcpClient

public class AppcAdminServerImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcAdminServer
{

    public AppcAdminServerImpl()
    {
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        LengthPrefixTcpClient adminClient;
        DataArea req;
        DataArea res;
        adminClient = new LengthPrefixTcpClient(getEncoding(), getLeftPadding().booleanValue(), getLengthPrefixLength().intValue(), getPaddingChar());
        try
        {
            adminClient.setConnectTimeout(getConnectTimeoutInMs().intValue());
            adminClient.connect(getIp(), getPort().intValue());
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E015(e);
        }
        req = DataArea.buildWithEmpty();
        MapListDataContext linkRequest = req.getLinkReq();
        linkRequest.add("trancd", "TM002");
        linkRequest.add("scname", getScname());
        linkRequest.add("scidxx", getScid());
        res = null;
        res = adminClient.call(req);
        IOException e;
        try
        {
            adminClient.disconnect();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C008(), e, new Object[0]);
        }
        break MISSING_BLOCK_LABEL_231;
        e;
        log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C007(), e, new Object[0]);
        try
        {
            adminClient.disconnect();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C008(), e, new Object[0]);
        }
        break MISSING_BLOCK_LABEL_231;
        Exception exception;
        exception;
        try
        {
            adminClient.disconnect();
        }
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C008(), e, new Object[0]);
        }
        throw exception;
        LengthPrefixTcpClient appClient;
        if(res == null)
            throw new IllegalArgumentException("Received message is null.");
        log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C009(), new Object[] {
            res
        });
        MapListDataContext linkResponse = res.getLinkRes();
        try
        {
            if(linkResponse.get("exception") != null)
                throw (IOException)linkResponse.get("exception");
            if(!"1".equals(linkResponse.get("result")))
                throw new IOException((String)linkResponse.get("error"));
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E015(e);
        }
        String servip = linkResponse.getString("servip");
        if(servip == null)
            servip = adminClient.getLocalAddress().getHostAddress();
        appClient = new LengthPrefixTcpClient(getEncoding(), getLeftPadding().booleanValue(), getLengthPrefixLength().intValue(), getPaddingChar());
        try
        {
            appClient.setConnectTimeout(getConnectTimeoutInMs().intValue());
            appClient.connect(SocketUtil.normalizeIp(servip), linkResponse.getInt("servpt", 0));
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E015(e);
        }
        log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C010());
        String s = appClient.call(sendBuffer);
        try
        {
            appClient.disconnect();
        }
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C011(), e, new Object[0]);
        }
        return s;
        Exception exception1;
        exception1;
        try
        {
            appClient.disconnect();
        }
        catch(IOException e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C011(), e, new Object[0]);
        }
        throw exception1;
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        return call(sendBuffer);
    }

    private final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/service/appc/AppcAdminServerImpl);
}
