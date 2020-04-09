// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LengthPrefixTcpClient.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.pkg.*;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.cedar.net.tcp.socket.protocol.LengthPrefixProtocolHandler;
import cn.sunline.adp.cedar.net.tcp.socket.util.SocketUtil;
import java.io.IOException;
import java.net.*;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import org.apache.commons.net.SocketClient;

public class LengthPrefixTcpClient extends SocketClient
{

    public LengthPrefixTcpClient(String encoding, boolean leftPadding, int lengthPrefixLength, String paddingChar)
    {
        protocolHandler = new LengthPrefixProtocolHandler();
        protocolHandler.setEncoding(encoding);
        protocolHandler.setId("0");
        protocolHandler.setLeftPadding(Boolean.valueOf(leftPadding));
        protocolHandler.setLengthPrefixLength(lengthPrefixLength);
        protocolHandler.setPaddingChar(paddingChar);
    }

    protected void _connectAction_()
        throws IOException
    {
        super._connectAction_();
    }

    public void connect(InetAddress host, int port)
        throws SocketException, IOException
    {
        super.connect(host, port);
    }

    public void connect(String hostname, int port)
        throws SocketException, IOException
    {
        super.connect(hostname, port);
    }

    public void connect(InetAddress host, int port, InetAddress localAddr, int localPort)
        throws SocketException, IOException
    {
        super.connect(host, port, localAddr, localPort);
    }

    public void connect(String hostname, int port, InetAddress localAddr, int localPort)
        throws SocketException, IOException
    {
        super.connect(hostname, port, localAddr, localPort);
    }

    public void connect(InetAddress host)
        throws SocketException, IOException
    {
        super.connect(host);
    }

    public void connect(String hostname)
        throws SocketException, IOException
    {
        super.connect(hostname);
    }

    public void disconnect()
        throws IOException
    {
        super.disconnect();
    }

    public boolean isConnected()
    {
        return super.isConnected();
    }

    public void setDefaultPort(int port)
    {
        super.setDefaultPort(port);
    }

    public int getDefaultPort()
    {
        return super.getDefaultPort();
    }

    public void setDefaultTimeout(int timeout)
    {
        super.setDefaultTimeout(timeout);
    }

    public int getDefaultTimeout()
    {
        return super.getDefaultTimeout();
    }

    public void setSoTimeout(int timeout)
        throws SocketException
    {
        super.setSoTimeout(timeout);
    }

    public void setSendBufferSize(int size)
        throws SocketException
    {
        super.setSendBufferSize(size);
    }

    public void setReceiveBufferSize(int size)
        throws SocketException
    {
        super.setReceiveBufferSize(size);
    }

    public int getSoTimeout()
        throws SocketException
    {
        return super.getSoTimeout();
    }

    public void setTcpNoDelay(boolean on)
        throws SocketException
    {
        super.setTcpNoDelay(on);
    }

    public boolean getTcpNoDelay()
        throws SocketException
    {
        return super.getTcpNoDelay();
    }

    public void setKeepAlive(boolean keepAlive)
        throws SocketException
    {
        super.setKeepAlive(keepAlive);
    }

    public boolean getKeepAlive()
        throws SocketException
    {
        return super.getKeepAlive();
    }

    public void setSoLinger(boolean on, int val)
        throws SocketException
    {
        super.setSoLinger(on, val);
    }

    public int getSoLinger()
        throws SocketException
    {
        return super.getSoLinger();
    }

    public int getLocalPort()
    {
        return super.getLocalPort();
    }

    public InetAddress getLocalAddress()
    {
        return super.getLocalAddress();
    }

    public int getRemotePort()
    {
        return super.getRemotePort();
    }

    public InetAddress getRemoteAddress()
    {
        return super.getRemoteAddress();
    }

    public boolean verifyRemote(Socket socket)
    {
        return super.verifyRemote(socket);
    }

    public void setSocketFactory(SocketFactory factory)
    {
        super.setSocketFactory(factory);
    }

    public void setServerSocketFactory(ServerSocketFactory factory)
    {
        super.setServerSocketFactory(factory);
    }

    public void setConnectTimeout(int connectTimeout)
    {
        super.setConnectTimeout(connectTimeout);
    }

    public int getConnectTimeout()
    {
        return super.getConnectTimeout();
    }

    public ServerSocketFactory getServerSocketFactory()
    {
        return super.getServerSocketFactory();
    }

    public String call(String sendBuffer)
    {
        String result = null;
        try
        {
            protocolHandler.writeMessage(_output_, sendBuffer);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E024(e);
        }
        try
        {
            result = protocolHandler.readMessage(_input_, _output_);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E025(e);
        }
        if(result == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E026();
        else
            return result;
    }

    public DataArea call(DataArea req)
        throws IOException
    {
        String encoding = protocolHandler.getEncoding();
        byte inputBytes[] = SocketUtil.wrapper.format(req, encoding, null, PkgMode.request);
        String inputs = new String(inputBytes, encoding);
        String outputs = call(inputs);
        if(outputs == null)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E027();
        else
            return SocketUtil.parser.parse(outputs.getBytes(encoding), encoding, null, PkgMode.response);
    }

    private LengthPrefixProtocolHandler protocolHandler;
}
