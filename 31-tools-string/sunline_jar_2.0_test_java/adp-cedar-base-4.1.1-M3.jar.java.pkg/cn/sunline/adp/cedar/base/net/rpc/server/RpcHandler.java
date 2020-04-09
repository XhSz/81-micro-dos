// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcHandler.java

package cn.sunline.adp.cedar.base.net.rpc.server;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.rpc.model.*;
import cn.sunline.adp.cedar.base.net.util.BaseUtil;
import cn.sunline.adp.cedar.base.net.util.NetConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class RpcHandler
{

    public RpcHandler()
    {
    }

    public final RpcResponse handle(RpcRequest request)
    {
        long start = System.currentTimeMillis();
        RpcResponse ret;
        try
        {
            ret = doHandle(request);
        }
        catch(Exception e)
        {
            logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C030(), e);
            ret = RpcSysError.serverUnknowException.toReponse(request);
            ret.setContent(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C031(), new Object[] {
                e.getMessage()
            }));
        }
        long end = System.currentTimeMillis();
        ret.setInner(BaseUtil.format("serverId=%s|cost=%s", new Object[] {
            NetConstants.version, Long.valueOf(end - start)
        }));
        ret.setRqId(request.getSysHeader().getReqId());
        return ret;
    }

    protected abstract RpcResponse doHandle(RpcRequest rpcrequest);

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/rpc/server/RpcHandler);
    private static final String innerTpl = "serverId=%s|cost=%s";

}
