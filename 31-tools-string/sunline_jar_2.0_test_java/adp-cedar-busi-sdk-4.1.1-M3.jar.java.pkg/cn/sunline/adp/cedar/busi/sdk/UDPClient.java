// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UDPClient.java

package cn.sunline.adp.cedar.busi.sdk;

import cn.sunline.adp.cedar.base.BaseConst;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Arrays;

public class UDPClient
{

    public UDPClient(String host, int port)
    {
        recvBufferSize = 4096;
        timeout = 30000;
        this.host = host;
        this.port = port;
    }

    public void init()
    {
        if(server == null)
            try
            {
                server = new DatagramSocket();
                server.connect(InetAddress.getByName(host), port);
                recvBuffer = new byte[recvBufferSize];
            }
            catch(Exception e)
            {
                throw new RuntimeException(BaseConst.UDPClient01, e);
            }
    }

    public String recvString(String encoding)
        throws SocketTimeoutException
    {
        try
        {
            byte ret[] = recvBytes();
            return new String(ret, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(BaseConst.UDPClient02, e);
        }
    }

    public byte[] recvBytes()
        throws SocketTimeoutException
    {
        init();
        try
        {
            DatagramPacket incoming = new DatagramPacket(recvBuffer, recvBuffer.length);
            server.setSoTimeout(timeout);
            server.receive(incoming);
            return Arrays.copyOf(recvBuffer, incoming.getLength());
        }
        catch(Exception e)
        {
            throw new RuntimeException(BaseConst.UDPClient03, e);
        }
    }

    public void sendString(String buffer)
    {
        try
        {
            sendString(buffer, "GB2312");
        }
        catch(SocketTimeoutException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sendString(String buffer, String encoding)
        throws SocketTimeoutException
    {
        try
        {
            byte bytes[] = buffer.getBytes(encoding);
            sendBytes(bytes);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new IllegalArgumentException(BaseConst.UDPClient04, e);
        }
    }

    public void sendBytes(byte bytes[])
        throws SocketTimeoutException
    {
        init();
        try
        {
            server.setSoTimeout(timeout);
            DatagramPacket outgoing = new DatagramPacket(bytes, bytes.length, server.getInetAddress(), server.getPort());
            server.send(outgoing);
        }
        catch(Exception e)
        {
            throw new RuntimeException(BaseConst.UDPClient05, e);
        }
    }

    public void close()
    {
        if(server != null)
            server.close();
    }

    public int getRecvBufferSize()
    {
        return recvBufferSize;
    }

    public void setRecvBufferSize(int recvBufferSize)
    {
        this.recvBufferSize = recvBufferSize;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_TIME_OUT = 30000;
    private int recvBufferSize;
    private int timeout;
    private byte recvBuffer[];
    private String host;
    private int port;
    private DatagramSocket server;
}
