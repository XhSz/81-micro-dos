// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RemoteCommandExecuter.java

package cn.sunline.adp.cedar.busi.sdk.service;

import ch.ethz.ssh2.*;
import java.io.*;

public class RemoteCommandExecuter
{

    public RemoteCommandExecuter(String hostName, String userName, String password, String keyFilePath)
    {
        port = 22;
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
        keyFile = new File(keyFilePath);
    }

    public RemoteCommandExecuter(String hostName, String userName, File keyFile)
    {
        port = 22;
        this.hostName = hostName;
        this.userName = userName;
        this.keyFile = keyFile;
    }

    public String execWithPKAuthentication(String cmd)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            Connection conn = new Connection(hostName, port);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPublicKey(userName, keyFile, password);
            if(!isAuthenticated)
                throw new IOException("Authentication failed.");
            Session sess = conn.openSession();
            sess.execCommand(cmd);
            InputStream stdout = new StreamGobbler(sess.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            do
            {
                String line = br.readLine();
                if(line == null)
                    break;
                ret.append(line).append("\n");
            } while(true);
            br.close();
            sess.close();
            conn.close();
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e);
        }
        return ret.toString();
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public void setKeyFilePass(String keyFilePass)
    {
        password = keyFilePass;
    }

    private String hostName;
    private int port;
    private String userName;
    private String password;
    private File keyFile;
}
