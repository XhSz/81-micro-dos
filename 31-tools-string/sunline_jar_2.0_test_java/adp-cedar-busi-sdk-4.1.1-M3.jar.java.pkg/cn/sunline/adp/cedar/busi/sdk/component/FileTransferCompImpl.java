// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileTransferCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class FileTransferCompImpl
{
    public static abstract class ApacheFtpFileTransfer
        implements BaseComp.FileTransfer
    {

        public String getIp()
        {
            return ip;
        }

        public void setIp(String ip)
        {
            this.ip = ip;
        }

        public Integer getPort()
        {
            return port;
        }

        public void setPort(Integer port)
        {
            this.port = port;
        }

        public String getUser()
        {
            return user;
        }

        public void setUser(String user)
        {
            this.user = user;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public Integer getConnectTimeoutInMs()
        {
            return connectTimeoutInMs;
        }

        public void setConnectTimeoutInMs(Integer connectTimeoutInMs)
        {
            this.connectTimeoutInMs = connectTimeoutInMs;
        }

        public Boolean getBinaryMode()
        {
            return binaryMode;
        }

        public void setBinaryMode(Boolean binaryMode)
        {
            this.binaryMode = binaryMode;
        }

        public String getLocalWorkDir()
        {
            return localWorkDir;
        }

        public void setLocalWorkDir(String localWorkDir)
        {
            this.localWorkDir = localWorkDir;
        }

        public String getRemoteWorkDir()
        {
            return remoteWorkDir;
        }

        public void setRemoteWorkDir(String remoteWorkDir)
        {
            this.remoteWorkDir = remoteWorkDir;
        }

        public Integer getRetryInterval()
        {
            return retryInterval;
        }

        public void setRetryInterval(Integer retryInterval)
        {
            this.retryInterval = retryInterval;
        }

        public Integer getRetryTime()
        {
            return retryTime;
        }

        public void setRetryTime(Integer retryTime)
        {
            this.retryTime = retryTime;
        }

        private String ip;
        private Integer port;
        private String user;
        private String password;
        private Integer connectTimeoutInMs;
        private Boolean binaryMode;
        private String localWorkDir;
        private String remoteWorkDir;
        private Integer retryInterval;
        private Integer retryTime;

        public ApacheFtpFileTransfer()
        {
        }
    }


    public FileTransferCompImpl()
    {
    }
}
