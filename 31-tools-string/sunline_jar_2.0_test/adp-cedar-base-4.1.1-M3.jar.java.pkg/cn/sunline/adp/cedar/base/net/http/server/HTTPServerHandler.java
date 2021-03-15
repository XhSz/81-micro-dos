// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTTPServerHandler.java

package cn.sunline.adp.cedar.base.net.http.server;

import cn.sunline.adp.cedar.base.net.http.HTTPCallback;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

public class HTTPServerHandler extends ChannelInboundHandlerAdapter
{

    public HTTPServerHandler(HTTPCallback callback)
    {
        this.callback = callback;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        if(msg instanceof FullHttpRequest)
            callback.onMessage(ctx, (FullHttpRequest)msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx)
        throws Exception
    {
        ctx.flush();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        callback.onException(ctx, cause);
        ctx.close();
    }

    private HTTPCallback callback;
}
