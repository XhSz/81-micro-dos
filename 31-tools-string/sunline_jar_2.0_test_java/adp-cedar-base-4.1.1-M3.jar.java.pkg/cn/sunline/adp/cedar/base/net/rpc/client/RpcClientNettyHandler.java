// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcClientNettyHandler.java

package cn.sunline.adp.cedar.base.net.rpc.client;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.rpc.model.*;
import cn.sunline.adp.cedar.base.net.util.JsonUtil;
import cn.sunline.adp.cedar.base.net.util.NetExceptionUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.client:
//            RpcRet, RpcStatusFuture, RpcFuture

public class RpcClientNettyHandler extends ChannelInboundHandlerAdapter
{

    public RpcClientNettyHandler(NioSocketChannel channel)
    {
        pendingRPC = new ConcurrentHashMap();
        logger.info("create RpcClientHandler");
        this.channel = channel;
    }

    public NioSocketChannel getChannel()
    {
        return channel;
    }

    public SocketAddress getRemotePeer()
    {
        return remotePeer;
    }

    public void channelActive(ChannelHandlerContext ctx)
        throws Exception
    {
        super.channelActive(ctx);
        remotePeer = channel.remoteAddress();
    }

    public void channelRegistered(ChannelHandlerContext ctx)
        throws Exception
    {
        super.channelRegistered(ctx);
        channel = (NioSocketChannel)ctx.channel();
    }

    public void channelRead(ChannelHandlerContext ctx, Object response)
        throws Exception
    {
        String retJons = (String)response;
        if(retJons.startsWith("#"))
        {
            RpcResponseStatus resStatus = (RpcResponseStatus)JsonUtil.toModel(retJons.substring(1), cn/sunline/adp/cedar/base/net/rpc/model/RpcResponseStatus);
            RpcRet rpcFuture = (RpcRet)pendingRPC.get(resStatus.getRqId());
            if(rpcFuture != null)
                rpcFuture.getStatusRet().done(resStatus);
            return;
        }
        RpcResponse res = (RpcResponse)JsonUtil.toModel(retJons, cn/sunline/adp/cedar/base/net/rpc/model/RpcResponse);
        String requestId = res.getRqId();
        if(requestId == null)
            throw NetExceptionUtil.wrapThrow(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C022(), new String[0]);
        RpcRet rpcFuture = (RpcRet)pendingRPC.get(requestId);
        if(rpcFuture != null)
        {
            pendingRPC.remove(requestId);
            rpcFuture.getRealRet().done(res);
        }
    }

    public void close()
    {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public RpcRet sendRequest(RpcRequest request)
        throws InterruptedException, ClosedChannelException
    {
        RpcRet ret = new RpcRet(request.getSysHeader().getReqId());
        pendingRPC.put(request.getSysHeader().getReqId(), ret);
        String reqJson = JsonUtil.toJson(request);
        channel.writeAndFlush(reqJson).addListener(new GenericFutureListener() {

            public void operationComplete(Future future)
                throws Exception
            {
                if(future.isSuccess())
                    RpcClientNettyHandler.logger.debug(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C023());
                else
                    RpcClientNettyHandler.logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C024(), future.cause());
            }

            final RpcClientNettyHandler this$0;

            
            {
                this.this$0 = RpcClientNettyHandler.this;
                super();
            }
        }
).sync();
        return ret;
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C025(), cause);
        ctx.close();
        RpcRet waitingRet;
        for(Iterator iterator = pendingRPC.values().iterator(); iterator.hasNext(); waitingRet.cancel())
            waitingRet = (RpcRet)iterator.next();

    }

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/rpc/client/RpcClientNettyHandler);
    private ConcurrentHashMap pendingRPC;
    private volatile NioSocketChannel channel;
    private SocketAddress remotePeer;


}
