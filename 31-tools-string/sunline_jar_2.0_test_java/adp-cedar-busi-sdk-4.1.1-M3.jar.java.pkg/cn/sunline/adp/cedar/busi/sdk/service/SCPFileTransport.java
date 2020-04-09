// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SCPFileTransport.java

package cn.sunline.adp.cedar.busi.sdk.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.constant.TspBaseConstantDef;
import java.io.File;
import java.io.IOException;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.service:
//            RemoteCommandExecuter

public class SCPFileTransport
{

    public SCPFileTransport(String hostname, String keyPath)
    {
        this.hostname = hostname;
        port = 22;
        this.keyPath = keyPath;
        keyFile = new File(keyPath);
        getConnection();
    }

    public SCPFileTransport(String hostname, int port, String keyPath)
    {
        this.hostname = hostname;
        this.port = port;
        this.keyPath = keyPath;
        keyFile = new File(keyPath);
        getConnection();
    }

    private void getConnection()
    {
        try
        {
            conn = new Connection(hostname, port);
            conn.connect();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(String.format(BaseConst.SCPFileTransport01, new Object[] {
                hostname, Integer.valueOf(port)
            }));
        }
    }

    public void login(String user, String password)
    {
        try
        {
            boolean isAutherized = conn.authenticateWithPublicKey(user, keyFile, password);
            if(isAutherized)
            {
                this.user = user;
                this.password = password;
                client = conn.createSCPClient();
            } else
            {
                throw new IllegalArgumentException(cn.sunline.adp.cedar.base.constant.TspBaseConstantDef.TBConst.C014());
            }
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean downloadFromRemote(String remoteFile, String localDir)
    {
        setDirectory(localDir);
        boolean downed;
        try
        {
            client.get(remoteFile, localDir);
            downed = true;
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException(e);
        }
        conn.close();
        break MISSING_BLOCK_LABEL_50;
        Exception exception;
        exception;
        conn.close();
        throw exception;
        return downed;
    }

    public boolean uploadToRemote(String localFile, String remoteDir)
    {
        setDirectory(remoteDir);
        boolean sended;
        try
        {
            client.put(localFile, remoteDir, "0755");
            sended = true;
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException(e);
        }
        conn.close();
        break MISSING_BLOCK_LABEL_52;
        Exception exception;
        exception;
        conn.close();
        throw exception;
        return sended;
    }

    public void uploadToRemote(byte b[], String remoteFile, String remoteDir)
    {
        setDirectory(remoteDir);
        try
        {
            client.put(b, remoteFile, remoteDir);
        }
        catch(Exception exception) { }
    }

    private void setDirectory(String dir)
    {
        if(dir.startsWith("/"))
        {
            RemoteCommandExecuter rce = new RemoteCommandExecuter(hostname, user, password, keyPath);
            rce.execWithPKAuthentication((new StringBuilder()).append("mkdir -p ").append(dir).toString());
        }
        File f = new File(dir);
        if(!f.exists())
        {
            f.mkdir();
            f.setWritable(true);
            f.setReadable(true);
        }
    }

    private String hostname;
    private int port;
    private String user;
    private String password;
    private Connection conn;
    private SCPClient client;
    private final int DEFAULT_PORT = 22;
    private File keyFile;
    private String keyPath;
}
