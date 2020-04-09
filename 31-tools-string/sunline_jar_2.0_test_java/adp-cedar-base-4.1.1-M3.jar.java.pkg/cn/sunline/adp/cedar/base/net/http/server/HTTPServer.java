// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HTTPServer.java

package cn.sunline.adp.cedar.base.net.http.server;

import cn.sunline.adp.cedar.base.net.http.HTTPCallback;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;

// Referenced classes of package cn.sunline.adp.cedar.base.net.http.server:
//            HTTPServerHandler

public class HTTPServer
{

    private HTTPServer()
    {
        boot = new ServerBootstrap();
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
    }

    public static HTTPServer create()
    {
        return new HTTPServer();
    }

    public void start(int port, final HTTPCallback callback)
    {
        try
        {
            ((ServerBootstrap)((ServerBootstrap)boot.group(boss, worker).channel(io/netty/channel/socket/nio/NioServerSocketChannel)).option(ChannelOption.SO_KEEPALIVE, Boolean.valueOf(true))).childHandler(new ChannelInitializer() {

                protected void initChannel(SocketChannel ch)
                    throws Exception
                {
                    ch.pipeline().addLast("decoder", new HttpRequestDecoder());
                    ch.pipeline().addLast("encoder", new HttpResponseEncoder());
                    ch.pipeline().addLast("aggregator", new HttpObjectAggregator(0x7fffffff));
                    ch.pipeline().addLast("handler", new HTTPServerHandler(callback));
                }

                protected volatile void initChannel(Channel channel)
                    throws Exception
                {
                    initChannel((SocketChannel)channel);
                }

                final HTTPCallback val$callback;
                final HTTPServer this$0;

            
            {
                this.this$0 = HTTPServer.this;
                callback = httpcallback;
                super();
            }
            }
);
            future = boot.bind(port).sync();
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown()
    {
        if(future != null)
            future.channel().closeFuture().sync();
        worker.shutdownGracefully();
        boss.shutdownGracefully();
        break MISSING_BLOCK_LABEL_103;
        InterruptedException e;
        e;
        Thread.currentThread().interrupt();
        worker.shutdownGracefully();
        boss.shutdownGracefully();
        break MISSING_BLOCK_LABEL_103;
        Exception exception;
        exception;
        worker.shutdownGracefully();
        boss.shutdownGracefully();
        throw exception;
    }

    private ServerBootstrap boot;
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private ChannelFuture future;
}
