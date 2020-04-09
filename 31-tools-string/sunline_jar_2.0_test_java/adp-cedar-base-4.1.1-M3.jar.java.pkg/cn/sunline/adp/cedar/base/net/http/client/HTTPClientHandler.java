// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTTPClientHandler.java

package cn.sunline.adp.cedar.base.net.http.client;

import cn.sunline.adp.cedar.base.net.http.HTTPCallback;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.Attribute;

public class HTTPClientHandler extends ChannelInboundHandlerAdapter
{

    public HTTPClientHandler()
    {
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception
    {
        HTTPCallback callback = (HTTPCallback)ctx.channel().attr(HTTPCallback.KEY_CALLBACK).get();
        if(msg instanceof FullHttpResponse)
            callback.onMessage(ctx, (FullHttpResponse)msg);
    }

    public void channelReadComplete(ChannelHandlerContext ctx)
        throws Exception
    {
        ctx.flush();
        ctx.close();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception
    {
        HTTPCallback callback = (HTTPCallback)ctx.channel().attr(HTTPCallback.KEY_CALLBACK).get();
        callback.onException(ctx, cause);
        ctx.close();
    }
}
