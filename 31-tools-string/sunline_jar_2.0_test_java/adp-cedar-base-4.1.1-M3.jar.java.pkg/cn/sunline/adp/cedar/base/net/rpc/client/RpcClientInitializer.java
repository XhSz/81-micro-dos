// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RpcClientInitializer.java

package cn.sunline.adp.cedar.base.net.rpc.client;

import cn.sunline.adp.cedar.base.net.util.NetConstants;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import java.nio.charset.Charset;

// Referenced classes of package cn.sunline.adp.cedar.base.net.rpc.client:
//            RpcClientNettyHandler

public class RpcClientInitializer extends ChannelInitializer
{

    public RpcClientInitializer()
    {
    }

    protected void initChannel(SocketChannel socketChannel)
        throws Exception
    {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(0x7fffffff, 0, 8, 0, 8, false));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(8));
        Charset c = Charset.forName(NetConstants.encoding);
        pipeline.addLast("decoder", new StringDecoder(c));
        pipeline.addLast("encoder", new StringEncoder(c));
        pipeline.addLast(new ChannelHandler[] {
            new RpcClientNettyHandler((NioSocketChannel)socketChannel)
        });
    }

    protected volatile void initChannel(Channel channel)
        throws Exception
    {
        initChannel((SocketChannel)channel);
    }
}
