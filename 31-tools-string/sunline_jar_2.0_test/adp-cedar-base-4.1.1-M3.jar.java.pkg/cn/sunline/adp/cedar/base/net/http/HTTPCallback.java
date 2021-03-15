// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTTPCallback.java

package cn.sunline.adp.cedar.base.net.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.util.AttributeKey;

public interface HTTPCallback
{

    public abstract void onMessage(ChannelHandlerContext channelhandlercontext, FullHttpMessage fullhttpmessage);

    public abstract void onException(ChannelHandlerContext channelhandlercontext, Throwable throwable);

    public static final AttributeKey KEY_CALLBACK = AttributeKey.valueOf("key_callback");

}
