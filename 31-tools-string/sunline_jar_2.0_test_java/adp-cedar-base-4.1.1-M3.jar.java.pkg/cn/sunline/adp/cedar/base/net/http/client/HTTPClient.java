// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTTPClient.java

package cn.sunline.adp.cedar.base.net.http.client;

import cn.sunline.adp.cedar.base.net.http.HTTPCallback;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.Attribute;

// Referenced classes of package cn.sunline.adp.cedar.base.net.http.client:
//            HTTPClientHandler

public class HTTPClient
{

    private HTTPClient()
    {
        boot = new Bootstrap();
        worker = new NioEventLoopGroup();
    }

    public static HTTPClient create()
    {
        return new HTTPClient();
    }

    public void connect(String host, int port)
    {
        try
        {
            ((Bootstrap)((Bootstrap)((Bootstrap)boot.group(worker)).channel(io/netty/channel/socket/nio/NioSocketChannel)).option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))).handler(new ChannelInitializer() {

                protected void initChannel(SocketChannel ch)
                    throws Exception
                {
                    ch.pipeline().addLast("encoder", new HttpRequestEncoder());
                    ch.pipeline().addLast("decoder", new HttpResponseDecoder());
                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(0x7fffffff));
                    ch.pipeline().addLast("handler", new HTTPClientHandler());
                }

                protected volatile void initChannel(Channel channel)
                    throws Exception
                {
                    initChannel((SocketChannel)channel);
                }

                final HTTPClient this$0;

            
            {
                this.this$0 = HTTPClient.this;
                super();
            }
            }
);
            future = boot.connect(host, port).sync();
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void disconnect()
    {
        future.channel().closeFuture().sync();
        worker.shutdownGracefully();
        break MISSING_BLOCK_LABEL_66;
        InterruptedException e;
        e;
        Thread.currentThread().interrupt();
        worker.shutdownGracefully();
        break MISSING_BLOCK_LABEL_66;
        Exception exception;
        exception;
        worker.shutdownGracefully();
        throw exception;
    }

    public void send(HttpRequest request, HTTPCallback callback)
    {
        future.channel().attr(HTTPCallback.KEY_CALLBACK).set(callback);
        future.channel().writeAndFlush(request);
    }

    private Bootstrap boot;
    private EventLoopGroup worker;
    private ChannelFuture future;
}
