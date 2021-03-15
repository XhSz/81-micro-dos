// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcClient.java

package cn.sunline.adp.cedar.base.net.rpc.client;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.rpc.exception.RpcBusyException;
import cn.sunline.adp.cedar.base.net.rpc.exception.RpcConnectionException;
import cn.sunline.adp.cedar.base.net.rpc.model.*;
import cn.sunline.adp.cedar.base.net.util.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import java.net.ConnectException;
import java.nio.channels.ClosedChannelException;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.client:
//            RpcClientInitializer, RpcClientNettyHandler, RpcRet, RpcFuture, 
//            RpcStatusFuture

public class RpcClient
{

    public RpcClient()
    {
    }

    public void connection(final String ip, final int port)
    {
        ChannelFuture channelFuture;
        long start;
        this.ip = ip;
        this.port = port;
        Bootstrap bootstrap = new Bootstrap();
        ((Bootstrap)((Bootstrap)((Bootstrap)((Bootstrap)bootstrap.group(bossGroupForClient)).channel(io/netty/channel/socket/nio/NioSocketChannel)).handler(new RpcClientInitializer())).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(500))).option(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
        channelFuture = bootstrap.connect(ip, port);
        start = System.currentTimeMillis();
        long end;
        try
        {
            channelFuture.addListener(new ChannelFutureListener() {

                public void operationComplete(ChannelFuture channelFuture)
                    throws Exception
                {
                    if(channelFuture.isSuccess())
                    {
                        RpcClient.logger.debug(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C009(), ip, Integer.valueOf(port));
                    } else
                    {
                        RpcClient.logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C010(), channelFuture.cause());
                        if(channelFuture.cause() instanceof ConnectException)
                            throw new RpcConnectionException(channelFuture.cause());
                        else
                            throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C010(), channelFuture.cause());
                    }
                }

                public volatile void operationComplete(Future future)
                    throws Exception
                {
                    operationComplete((ChannelFuture)future);
                }

                final String val$ip;
                final int val$port;
                final RpcClient this$0;

            
            {
                this.this$0 = RpcClient.this;
                ip = s;
                port = i;
                super();
            }
            }
).sync();
        }
        catch(Exception e)
        {
            throw new RpcConnectionException(channelFuture.cause());
        }
        end = System.currentTimeMillis();
        logger.info(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C011(), ip, Integer.valueOf(port), Long.valueOf(end - start));
        break MISSING_BLOCK_LABEL_194;
        Exception exception;
        exception;
        long end = System.currentTimeMillis();
        logger.info(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C011(), ip, Integer.valueOf(port), Long.valueOf(end - start));
        throw exception;
        nettyClientHandler = (RpcClientNettyHandler)channelFuture.channel().pipeline().get(cn/sunline/adp/cedar/base/net/rpc/client/RpcClientNettyHandler);
        logger.debug(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C012());
        return;
    }

    private void reconnection()
    {
        connection(ip, port);
    }

    public RpcResponse sendRequest(RpcRequest request)
    {
        if(logger.isInfoEnabled())
            logger.info((new StringBuilder()).append("CREQ[").append(JsonUtil.toJson(request)).append("]").toString());
        try
        {
            RpcResponse ret = null;
            if(request.getTimeout() != null && request.getTimeout().longValue() > 0L)
                ret = (RpcResponse)sendAsynRequest(request).get(request.getTimeout().longValue(), TimeUnit.MILLISECONDS);
            else
                ret = (RpcResponse)sendAsynRequest(request).get();
            if(logger.isInfoEnabled())
                logger.info((new StringBuilder()).append("CRES[").append(JsonUtil.toJson(ret)).append("]").toString());
            return ret;
        }
        catch(NetException de)
        {
            logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C014(), de);
            throw de;
        }
        catch(Exception e)
        {
            throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C013(), e);
        }
    }

    public RpcFuture sendAsynRequest(RpcRequest request)
    {
        String reqId = BaseUtil.createGUID();
        request.getSysHeader().setReqId(reqId);
        request.getSysHeader().setClientId(NetConstants.vmId);
        do
            try
            {
                if(nettyClientHandler == null)
                    throw NetExceptionUtil.wrapThrow(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C015(), new Object[] {
                        ip, Integer.valueOf(port)
                    }), new String[0]);
                RpcRet rpcRet = nettyClientHandler.sendRequest(request);
                RpcResponseStatus rpcResponseStatus;
                if(request.getTimeout() != null && request.getTimeout().longValue() > 0L)
                    rpcResponseStatus = (RpcResponseStatus)rpcRet.getStatusRet().get(request.getTimeout().longValue(), TimeUnit.MILLISECONDS);
                else
                    rpcResponseStatus = (RpcResponseStatus)rpcRet.getStatusRet().get();
                if(rpcResponseStatus.getStatus() == ResponseStatus.busy)
                    throw new RpcBusyException(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C016(), new Object[] {
                        ip, Integer.valueOf(port)
                    }));
                if(rpcResponseStatus.getStatus() != ResponseStatus.ok)
                    throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C017(), new String[] {
                        rpcResponseStatus.getStatus().name(), rpcResponseStatus.getStatus().getMsg()
                    });
                else
                    return rpcRet.getRealRet();
            }
            catch(ClosedChannelException e1)
            {
                reconnection();
            }
            catch(Exception e)
            {
                throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C019(), e);
            }
        while(true);
    }

    public void close()
    {
        logger.debug(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C020(), ip, Integer.valueOf(port));
        if(nettyClientHandler != null)
            nettyClientHandler.close();
        if(bossGroupForClient == null);
    }

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/rpc/client/RpcClient);
    RpcClientNettyHandler nettyClientHandler;
    static EventLoopGroup bossGroupForClient = new NioEventLoopGroup();
    String ip;
    int port;


}
