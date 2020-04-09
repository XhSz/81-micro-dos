// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcNettyHandler.java

package cn.sunline.adp.cedar.base.net.rpc.server;

import cn.sunline.adp.cedar.base.constant.CommonNetConstantDef;
import cn.sunline.adp.cedar.base.net.rpc.model.*;
import cn.sunline.adp.cedar.base.net.util.JsonUtil;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.adp.metadata.base.event.IMonitorMetricsManager;
import cn.sunline.adp.metadata.base.event.MonitorMetricConstant;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import com.google.common.collect.Maps;
import io.netty.channel.*;
import java.util.Map;
import java.util.concurrent.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.server:
//            RpcServer, RpcHandler

public class RpcNettyHandler extends ChannelInboundHandlerAdapter
{

    public RpcNettyHandler(RpcServer server)
    {
        this.server = server;
    }

    public void channelRead(final ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        if(StringUtils.isEmpty((String)msg))
            return;
        try
        {
            String reqJson = (String)msg;
            final RpcRequest reqObj = (RpcRequest)JsonUtil.toModel(reqJson, cn/sunline/adp/cedar/base/net/rpc/model/RpcRequest);
            logger.info((new StringBuilder()).append("SREQ[").append(reqJson).append("]").toString());
            server.getHandlePool().submit(new Runnable() {

                public void run()
                {
                    String resJson = "";
                    RpcResponse resObj = server.getNetHandler().handle(reqObj);
                    resJson = JsonUtil.toJson(resObj);
                    RpcNettyHandler.logger.info((new StringBuilder()).append("SRES[").append(resJson).append("]").toString());
                    ctx.writeAndFlush(resJson);
                    break MISSING_BLOCK_LABEL_216;
                    Exception e;
                    e;
                    RpcNettyHandler.logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C032(), e);
                    RpcResponse resObj = RpcSysError.serverUnknowException.toReponse(reqObj);
                    resObj.setContent(String.format(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C031(), new Object[] {
                        e.getMessage()
                    }));
                    resJson = JsonUtil.toJson(resObj);
                    RpcNettyHandler.logger.info((new StringBuilder()).append("SRES[").append(resJson).append("]").toString());
                    ctx.writeAndFlush(resJson);
                    break MISSING_BLOCK_LABEL_216;
                    Exception exception;
                    exception;
                    RpcNettyHandler.logger.info((new StringBuilder()).append("SRES[").append(resJson).append("]").toString());
                    ctx.writeAndFlush(resJson);
                    throw exception;
                }

                final RpcRequest val$reqObj;
                final ChannelHandlerContext val$ctx;
                final RpcNettyHandler this$0;

            
            {
                this.this$0 = RpcNettyHandler.this;
                reqObj = rpcrequest;
                ctx = channelhandlercontext;
                super();
            }
            }
);
            postAccessThreadEvent();
            RpcResponseStatus status = new RpcResponseStatus(reqObj.getSysHeader().getReqId(), ResponseStatus.ok);
            String resJson = (new StringBuilder()).append("#").append(JsonUtil.toJson(status)).toString();
            logger.info((new StringBuilder()).append("SRES-Status[").append(resJson).append("]").toString());
            ctx.writeAndFlush(resJson);
        }
        catch(RejectedExecutionException e)
        {
            String reqJson = (String)msg;
            RpcRequest reqObj = (RpcRequest)JsonUtil.toModel(reqJson, cn/sunline/adp/cedar/base/net/rpc/model/RpcRequest);
            RpcResponseStatus status = new RpcResponseStatus(reqObj.getSysHeader().getReqId(), ResponseStatus.busy);
            String resJson = (new StringBuilder()).append("#").append(JsonUtil.toJson(status)).toString();
            logger.info((new StringBuilder()).append("SRES-Status[").append(resJson).append("]").toString());
            ctx.writeAndFlush(resJson);
        }
        return;
    }

    private void postAccessThreadEvent()
    {
        if(!ProfileSwitcher.enableMonitorEvent)
        {
            return;
        } else
        {
            EdspCoreBeanUtil.getMetricsManager().pushMetrics("TSP_ACCESS_POOL_STATE", new cn.sunline.adp.metadata.base.event.IMonitorMetricsManager.FillMetricsData() {

                protected Map _getMetricsData()
                {
                    int queueCount = server.getHandlePool().getQueue().size() - server.getHandlePool().getQueue().remainingCapacity();
                    Map metrics = Maps.newHashMap();
                    metrics.put("poolName", server.getThreadName());
                    metrics.put("totalCount", String.valueOf(server.getHandlePool().getActiveCount()));
                    metrics.put("coreCount", String.valueOf(server.getHandlePool().getCorePoolSize()));
                    metrics.put("maxCount", String.valueOf(server.getHandlePool().getMaximumPoolSize()));
                    metrics.put("queueCount", String.valueOf(server.getHandlePool().getQueue().size()));
                    metrics.put("queueSize", String.valueOf(queueCount));
                    return metrics;
                }

                final RpcNettyHandler this$0;

            
            {
                this.this$0 = RpcNettyHandler.this;
                super();
            }
            }
);
            return;
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        logger.error(cn.sunline.adp.cedar.base.constant.CommonNetConstantDef.CommNetConst.C033(), ctx.channel().remoteAddress(), cause);
        ctx.close();
    }

    private static final Logger logger = LogManager.getLogger(cn/sunline/adp/cedar/base/net/rpc/server/RpcNettyHandler);
    private RpcServer server;



}
