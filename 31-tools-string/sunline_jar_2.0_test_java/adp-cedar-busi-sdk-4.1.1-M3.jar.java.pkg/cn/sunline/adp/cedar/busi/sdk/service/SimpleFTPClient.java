// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimpleFTPClient.java

package cn.sunline.adp.cedar.busi.sdk.service;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.*;
import java.net.SocketException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.net.ftp.*;

public class SimpleFTPClient
{

    public SimpleFTPClient()
    {
        port = DEFAULT_FTP_PORT;
        connectTimeoutInMs = DEFAULT_CONNECT_TIMEOUT_IN_MS;
        dataTimeoutInMs = DEFAULT_DATA_TIMEOUT_IN_MS;
        binaryMode = Boolean.valueOf(true);
        localEncoding = "GB18030";
        remoteEncoding = "ISO8859-1";
        retryTime = Integer.valueOf(2);
        retryInterval = Integer.valueOf(2000);
    }

    public SimpleFTPClient ip(String ip)
    {
        this.ip = ip;
        return this;
    }

    public SimpleFTPClient port(Integer port)
    {
        if(port != null)
            this.port = port;
        else
            this.port = DEFAULT_FTP_PORT;
        return this;
    }

    public SimpleFTPClient user(String user)
    {
        this.user = user;
        return this;
    }

    public SimpleFTPClient password(String password)
    {
        this.password = password;
        return this;
    }

    public SimpleFTPClient workingDirctory(String workingDirctory)
    {
        this.workingDirctory = workingDirctory;
        return this;
    }

    public SimpleFTPClient connectTimeoutInMs(Integer connectTimeoutInMs)
    {
        if(connectTimeoutInMs != null)
            this.connectTimeoutInMs = connectTimeoutInMs;
        else
            this.connectTimeoutInMs = DEFAULT_CONNECT_TIMEOUT_IN_MS;
        return this;
    }

    public SimpleFTPClient dataTimeoutInMs(Integer dataTimeoutInMs)
    {
        if(dataTimeoutInMs != null)
            this.dataTimeoutInMs = dataTimeoutInMs;
        else
            this.dataTimeoutInMs = DEFAULT_DATA_TIMEOUT_IN_MS;
        return this;
    }

    public SimpleFTPClient binaryMode(Boolean binaryMode)
    {
        if(binaryMode != null)
            this.binaryMode = binaryMode;
        else
            this.binaryMode = Boolean.valueOf(true);
        return this;
    }

    public void setRetryTime(int retryTime)
    {
        this.retryTime = Integer.valueOf(retryTime);
    }

    public void setRetryInterval(int retryInterval)
    {
        this.retryInterval = Integer.valueOf(retryInterval);
    }

    private String getFileName(String name)
    {
        try
        {
            return new String(name.getBytes(localEncoding), remoteEncoding);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new RuntimeException(String.format(BaseConst.SimpleFTPClient06, new Object[] {
                name
            }), e);
        }
    }

    private FTPClient login()
    {
        FTPClient ftp = new FTPClient();
        try
        {
            connect(ftp, retryTime.intValue());
            ftp.setSoTimeout(dataTimeoutInMs.intValue());
            ftp.setControlEncoding(remoteEncoding);
            if(!ftp.login(user, password))
                throw new RuntimeException(String.format(BaseConst.SimpleFTPClient07, new Object[] {
                    user, password
                }));
            if(StringUtil.isNotEmpty(workingDirctory) && !ftp.changeWorkingDirectory(workingDirctory))
                throw new RuntimeException(String.format(BaseConst.SimpleFTPClient08, new Object[] {
                    workingDirctory
                }));
            if(binaryMode != null && !binaryMode.booleanValue())
            {
                if(!ftp.setFileType(0))
                    throw new RuntimeException(String.format(BaseConst.SimpleFTPClient09, new Object[] {
                        Integer.valueOf(0)
                    }));
            } else
            if(!ftp.setFileType(2))
                throw new RuntimeException(String.format(BaseConst.SimpleFTPClient09, new Object[] {
                    Integer.valueOf(2)
                }));
            ftp.setBufferSize(0x100000);
        }
        catch(SocketException e)
        {
            throw new RuntimeException(BaseConst.SimpleFTPClient10, e);
        }
        catch(IOException e)
        {
            throw new RuntimeException(BaseConst.SimpleFTPClient10, e);
        }
        return ftp;
    }

    private void connect(FTPClient ftp, int retryTime)
    {
        int retryCount = 0;
        do
            try
            {
                ftp.setDefaultTimeout(connectTimeoutInMs.intValue());
                ftp.setDataTimeout(dataTimeoutInMs.intValue());
                ftp.setConnectTimeout(connectTimeoutInMs.intValue());
                ftp.connect(ip, port.intValue());
                int reply = ftp.getReplyCode();
                if(!FTPReply.isPositiveCompletion(reply))
                    throw new RuntimeException((new StringBuilder()).append("Can't Connect to :").append(ip).toString());
                else
                    return;
            }
            catch(Exception e)
            {
                if(retryCount < retryTime)
                {
                    try
                    {
                        Thread.sleep(retryInterval.intValue());
                    }
                    catch(InterruptedException e1)
                    {
                        Thread.currentThread().interrupt();
                    }
                    retryCount++;
                } else
                {
                    throw new RuntimeException(e);
                }
            }
        while(true);
    }

    private void logoff(FTPClient ftp)
    {
        if(ftp.isConnected())
        {
            try
            {
                ftp.logout();
            }
            catch(Exception e)
            {
                log.error("logout fail", e, new Object[0]);
            }
            try
            {
                ftp.disconnect();
            }
            catch(Exception e)
            {
                log.error(BaseConst.SimpleFTPClient01);
            }
        }
    }

    public void download(String localFileName, String remoteFileName)
    {
        long start;
        FTPClient ftp;
        start = System.currentTimeMillis();
        ftp = login();
        localPrepare(localFileName);
        FileOutputStream fos;
        Throwable throwable;
        fos = new FileOutputStream(localFileName);
        throwable = null;
        BufferedOutputStream bos;
        Throwable throwable3;
        bos = new BufferedOutputStream(fos);
        throwable3 = null;
        try
        {
            remoteFileName = getFileName(remoteFileName);
            if(!ftp.retrieveFile(remoteFileName, bos))
                throw new RuntimeException(String.format(BaseConst.SimpleFTPClient11, new Object[] {
                    ftp.getReplyString()
                }));
            log.info(BaseConst.SimpleFTPClient02, new Object[] {
                ip, user, remoteFileName, localFileName, Long.valueOf(System.currentTimeMillis() - start)
            });
        }
        catch(Throwable throwable5)
        {
            throwable3 = throwable5;
            throw throwable5;
        }
        if(bos != null)
            if(throwable3 != null)
                try
                {
                    bos.close();
                }
                catch(Throwable throwable4)
                {
                    throwable3.addSuppressed(throwable4);
                }
            else
                bos.close();
        break MISSING_BLOCK_LABEL_219;
        Exception exception;
        exception;
        if(bos != null)
            if(throwable3 != null)
                try
                {
                    bos.close();
                }
                catch(Throwable throwable6)
                {
                    throwable3.addSuppressed(throwable6);
                }
            else
                bos.close();
        throw exception;
        if(fos != null)
            if(throwable != null)
                try
                {
                    fos.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                fos.close();
        break MISSING_BLOCK_LABEL_358;
        Throwable throwable2;
        throwable2;
        throwable = throwable2;
        throw throwable2;
        Exception exception1;
        exception1;
        if(fos != null)
            if(throwable != null)
                try
                {
                    fos.close();
                }
                catch(Throwable throwable7)
                {
                    throwable.addSuppressed(throwable7);
                }
            else
                fos.close();
        throw exception1;
        FileNotFoundException e;
        e;
        throw new RuntimeException(String.format(BaseConst.SimpleFTPClient12, new Object[] {
            localFileName, e.getMessage()
        }), e);
        e;
        throw new RuntimeException(BaseConst.SimpleFTPClient13, e);
        logoff(ftp);
        break MISSING_BLOCK_LABEL_378;
        Exception exception2;
        exception2;
        logoff(ftp);
        throw exception2;
    }

    public void upload(String localFileName, String remoteFileName)
    {
        long start;
        FTPClient ftp;
        start = System.currentTimeMillis();
        ftp = login();
        remotePrepare(ftp, remoteFileName);
        BufferedInputStream in;
        Throwable throwable;
        in = new BufferedInputStream(new FileInputStream(localFileName));
        throwable = null;
        try
        {
            if(!ftp.storeFile(getFileName(remoteFileName), in))
                throw new RuntimeException(String.format(BaseConst.SimpleFTPClient14, new Object[] {
                    ftp.getReplyString()
                }));
            log.info(BaseConst.SimpleFTPClient04, new Object[] {
                ip, user, remoteFileName, localFileName, Long.valueOf(System.currentTimeMillis() - start)
            });
        }
        catch(Throwable throwable2)
        {
            throwable = throwable2;
            throw throwable2;
        }
        if(in != null)
            if(throwable != null)
                try
                {
                    in.close();
                }
                catch(Throwable throwable1)
                {
                    throwable.addSuppressed(throwable1);
                }
            else
                in.close();
        break MISSING_BLOCK_LABEL_280;
        Exception exception;
        exception;
        if(in != null)
            if(throwable != null)
                try
                {
                    in.close();
                }
                catch(Throwable throwable3)
                {
                    throwable.addSuppressed(throwable3);
                }
            else
                in.close();
        throw exception;
        FileNotFoundException e;
        e;
        throw new RuntimeException(String.format(BaseConst.SimpleFTPClient15, new Object[] {
            localFileName, e.getMessage()
        }), e);
        e;
        throw new RuntimeException(String.format(BaseConst.SimpleFTPClient16, new Object[] {
            e.getMessage()
        }), e);
        logoff(ftp);
        break MISSING_BLOCK_LABEL_300;
        Exception exception1;
        exception1;
        logoff(ftp);
        throw exception1;
    }

    public static List getDirectories(String fileName)
    {
        List ret = null;
        boolean abs = false;
        int idx = fileName.indexOf('/');
        if(idx != -1)
        {
            if(idx == 0)
            {
                fileName = fileName.substring(idx + 1);
                abs = true;
            }
            idx = fileName.lastIndexOf('/');
            if(idx != -1)
            {
                fileName = fileName.substring(0, idx);
                ret = StringUtil.split(fileName, '/');
                ret.set(0, abs ? ((Object) ((new StringBuilder()).append("/").append((String)ret.get(0)).toString())) : ((Object) ((String)ret.get(0))));
            }
        }
        return ret;
    }

    public static void localPrepare(String localFileName)
    {
        List dirs = getDirectories(localFileName);
        if(dirs != null && dirs.size() > 0)
        {
            StringBuilder sb = new StringBuilder();
            String dir;
            for(Iterator iterator = dirs.iterator(); iterator.hasNext(); sb.append(dir).append("/"))
                dir = (String)iterator.next();

            (new File(sb.toString())).mkdirs();
        }
    }

    public void remotePrepare(FTPClient client, String remoteFileName)
    {
        try
        {
            client.deleteFile(getFileName(remoteFileName));
        }
        catch(IOException e)
        {
            throw new RuntimeException(String.format(BaseConst.SimpleFTPClient17, new Object[] {
                remoteFileName
            }), e);
        }
        List dirs = getDirectories(remoteFileName);
        if(dirs != null && dirs.size() > 0)
        {
            StringBuilder sb = new StringBuilder();
            for(Iterator iterator = dirs.iterator(); iterator.hasNext();)
            {
                String dir = (String)iterator.next();
                sb.append(dir).append("/");
                try
                {
                    client.makeDirectory(getFileName(sb.toString()));
                }
                catch(IOException e)
                {
                    throw new RuntimeException(String.format(BaseConst.SimpleFTPClient18, new Object[] {
                        sb.toString()
                    }), e);
                }
            }

        }
    }

    public static void main(String args[])
    {
        if(args.length < 6)
            throw new IllegalArgumentException("Usage: FTP RequestData");
        String ip = args[0];
        int port = Integer.parseInt(args[1]);
        String user = args[2];
        String passwd = args[3];
        SimpleFTPClient ftp = (new SimpleFTPClient()).connectTimeoutInMs(Integer.valueOf(5000)).ip(ip).port(Integer.valueOf(port)).user(user).password(passwd).binaryMode(Boolean.valueOf(true));
        String localFileName = args[4];
        String remoteFileName = args[5];
        if(args.length == 8)
        {
            ftp.localEncoding = args[6];
            ftp.remoteEncoding = args[7];
        }
        try
        {
            ftp.download(localFileName, remoteFileName);
        }
        catch(Exception e)
        {
            log.error("", e, new Object[0]);
        }
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/service/SimpleFTPClient);
    private static final Integer DEFAULT_CONNECT_TIMEOUT_IN_MS = Integer.valueOf(5000);
    private static final Integer DEFAULT_DATA_TIMEOUT_IN_MS = Integer.valueOf(60000);
    private static final Integer DEFAULT_FTP_PORT = Integer.valueOf(21);
    private String ip;
    private Integer port;
    private String user;
    private String password;
    private String workingDirctory;
    private Integer connectTimeoutInMs;
    private Integer dataTimeoutInMs;
    private Boolean binaryMode;
    protected String localEncoding;
    protected String remoteEncoding;
    private Integer retryTime;
    private Integer retryInterval;

}
