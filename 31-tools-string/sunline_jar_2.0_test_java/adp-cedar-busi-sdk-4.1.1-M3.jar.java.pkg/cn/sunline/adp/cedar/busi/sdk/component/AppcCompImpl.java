// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcCompImpl.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.metadata.model.annotation.ConfigType;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.component:
//            BaseComp

public class AppcCompImpl
{
    public static abstract class AppcActiveMQ
        implements BaseComp.Appc
    {

        public String getSubject()
        {
            return subject;
        }

        public void setSubject(String subject)
        {
            this.subject = subject;
        }

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public Integer getTimeout()
        {
            return timeout;
        }

        public void setTimeout(Integer timeout)
        {
            this.timeout = timeout;
        }

        public Boolean getTopic()
        {
            return topic;
        }

        public void setTopic(Boolean topic)
        {
            this.topic = topic;
        }

        private String subject;
        private String url;
        private Integer timeout;
        private Boolean topic;

        public AppcActiveMQ()
        {
        }
    }

    public static abstract class AppcTuxedoLTTS
        implements BaseComp.Appc
    {

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        private String address;
        private String encoding;

        public AppcTuxedoLTTS()
        {
        }
    }

    public static abstract class AppcTuxedo
        implements BaseComp.Appc
    {

        public String getAddress()
        {
            return address;
        }

        public void setAddress(String address)
        {
            this.address = address;
        }

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        public Integer getPkgLength()
        {
            return pkgLength;
        }

        public void setPkgLength(Integer pkgLength)
        {
            this.pkgLength = pkgLength;
        }

        private String address;
        private String encoding;
        private Integer pkgLength;

        public AppcTuxedo()
        {
        }
    }

    public static abstract class AppcUdp
        implements BaseComp.Appc
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

        public Boolean getNeedReturn()
        {
            return needReturn;
        }

        public void setNeedReturn(Boolean needReturn)
        {
            this.needReturn = needReturn;
        }

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        private String ip;
        private Integer port;
        private Boolean needReturn;
        private String encoding;

        public AppcUdp()
        {
        }
    }

    public static abstract class AppcAdminServer
        implements BaseComp.Appc
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

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        public String getScid()
        {
            return scid;
        }

        public void setScid(String scid)
        {
            this.scid = scid;
        }

        public String getScname()
        {
            return scname;
        }

        public void setScname(String scname)
        {
            this.scname = scname;
        }

        public Integer getConnectTimeoutInMs()
        {
            return connectTimeoutInMs;
        }

        public void setConnectTimeoutInMs(Integer connectTimeoutInMs)
        {
            this.connectTimeoutInMs = connectTimeoutInMs;
        }

        public Integer getSendTimeoutInMs()
        {
            return sendTimeoutInMs;
        }

        public void setSendTimeoutInMs(Integer sendTimeoutInMs)
        {
            this.sendTimeoutInMs = sendTimeoutInMs;
        }

        public Integer getRecvTimeoutInMs()
        {
            return recvTimeoutInMs;
        }

        public void setRecvTimeoutInMs(Integer recvTimeoutInMs)
        {
            this.recvTimeoutInMs = recvTimeoutInMs;
        }

        public Integer getLengthPrefixLength()
        {
            return lengthPrefixLength;
        }

        public void setLengthPrefixLength(Integer lengthPrefixLength)
        {
            this.lengthPrefixLength = lengthPrefixLength;
        }

        public String getPaddingChar()
        {
            return paddingChar;
        }

        public void setPaddingChar(String paddingChar)
        {
            this.paddingChar = paddingChar;
        }

        public Boolean getLeftPadding()
        {
            return leftPadding;
        }

        public void setLeftPadding(Boolean leftPadding)
        {
            this.leftPadding = leftPadding;
        }

        private String ip;
        private Integer port;
        private String encoding;
        private String scid;
        private String scname;
        private Integer connectTimeoutInMs;
        private Integer sendTimeoutInMs;
        private Integer recvTimeoutInMs;
        private Integer lengthPrefixLength;
        private String paddingChar;
        private Boolean leftPadding;

        public AppcAdminServer()
        {
        }
    }

    public static abstract class AppcInterfaceMain
        implements BaseComp.Appc
    {

        public String getUrl()
        {
            return url;
        }

        public void setUrl(String url)
        {
            this.url = url;
        }

        public String getDriverClass()
        {
            return driverClass;
        }

        public void setDriverClass(String driverClass)
        {
            this.driverClass = driverClass;
        }

        public String getDatasourceId()
        {
            return datasourceId;
        }

        public void setDatasourceId(String datasourceId)
        {
            this.datasourceId = datasourceId;
        }

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPasswd()
        {
            return passwd;
        }

        public void setPasswd(String passwd)
        {
            this.passwd = passwd;
        }

        private String url;
        private String driverClass;
        private String datasourceId;
        private String username;
        private String passwd;

        public AppcInterfaceMain()
        {
        }
    }

    public static abstract class AppcTcp
        implements BaseComp.Appc
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

        public Integer getConnectTimeoutInMs()
        {
            return connectTimeoutInMs;
        }

        public void setConnectTimeoutInMs(Integer connectTimeoutInMs)
        {
            this.connectTimeoutInMs = connectTimeoutInMs;
        }

        public Integer getSendTimeoutInMs()
        {
            return sendTimeoutInMs;
        }

        public void setSendTimeoutInMs(Integer sendTimeoutInMs)
        {
            this.sendTimeoutInMs = sendTimeoutInMs;
        }

        public Integer getRecvTimeoutInMs()
        {
            return recvTimeoutInMs;
        }

        public void setRecvTimeoutInMs(Integer recvTimeoutInMs)
        {
            this.recvTimeoutInMs = recvTimeoutInMs;
        }

        public Integer getLengthPrefixLength()
        {
            return lengthPrefixLength;
        }

        public void setLengthPrefixLength(Integer lengthPrefixLength)
        {
            this.lengthPrefixLength = lengthPrefixLength;
        }

        public String getEncoding()
        {
            return encoding;
        }

        public void setEncoding(String encoding)
        {
            this.encoding = encoding;
        }

        public String getPaddingChar()
        {
            return paddingChar;
        }

        public void setPaddingChar(String paddingChar)
        {
            this.paddingChar = paddingChar;
        }

        public Boolean getLeftPadding()
        {
            return leftPadding;
        }

        public void setLeftPadding(Boolean leftPadding)
        {
            this.leftPadding = leftPadding;
        }

        private String ip;
        private Integer port;
        private Integer connectTimeoutInMs;
        private Integer sendTimeoutInMs;
        private Integer recvTimeoutInMs;
        private Integer lengthPrefixLength;
        private String encoding;
        private String paddingChar;
        private Boolean leftPadding;

        public AppcTcp()
        {
        }
    }


    public AppcCompImpl()
    {
    }
}
