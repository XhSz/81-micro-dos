// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AppcActiveMQImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.appc;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import java.util.*;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class AppcActiveMQImpl extends cn.sunline.adp.cedar.busi.sdk.component.AppcCompImpl.AppcActiveMQ
{

    public AppcActiveMQImpl()
    {
        start = false;
    }

    public String call(String sendBuffer)
        throws EdspServiceException
    {
        return call(sendBuffer, null);
    }

    public String call(String sendBuffer, Map properties)
        throws EdspServiceException
    {
        try
        {
            sendMessage(getSubject(), getTopic().booleanValue(), properties, sendBuffer);
            log.info(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C005(), new Object[] {
                properties, sendBuffer
            });
        }
        catch(Exception e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C006(), e, new Object[] {
                properties, sendBuffer
            });
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E014(e);
        }
        close();
        break MISSING_BLOCK_LABEL_86;
        Exception exception;
        exception;
        close();
        throw exception;
        return null;
    }

    public void sendMessage(String subject, boolean topic, Map msgProps, String message)
    {
        try
        {
            Session s = getSession(true);
            Destination dest = getDestination(s, subject, topic);
            TextMessage msg = s.createTextMessage(message);
            if(msgProps != null)
            {
                java.util.Map.Entry e;
                for(Iterator iterator = msgProps.entrySet().iterator(); iterator.hasNext(); msg.setObjectProperty((String)e.getKey(), e.getValue()))
                    e = (java.util.Map.Entry)iterator.next();

            }
            getProducer(subject, topic).send(dest, msg);
        }
        catch(JMSException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected Session getSession(boolean isSync)
        throws JMSException
    {
        Session s = isSync ? session : asyncSession;
        if(s == null)
        {
            s = getConnection().createSession(false, 1);
            if(isSync)
                session = s;
            else
                asyncSession = s;
        }
        return s;
    }

    protected Destination getDestination(Session s, String subject, boolean topic)
        throws JMSException
    {
        if(topic)
            return s.createTopic(subject);
        else
            return s.createQueue(subject);
    }

    private Connection getConnection()
        throws JMSException
    {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(getUrl());
        conn = connectionFactory.createConnection();
        conn.setExceptionListener(new ExceptionListener() {

            public void onException(JMSException e)
            {
                AppcActiveMQImpl.log.error((new StringBuilder()).append("jms:").append(e.getMessage()).toString(), e, new Object[0]);
                if(exceptionListener != null)
                    exceptionListener.onException(e);
            }

            final AppcActiveMQImpl this$0;

            
            {
                this.this$0 = AppcActiveMQImpl.this;
                super();
            }
        }
);
        return conn;
    }

    protected MessageProducer getProducer(String subject, boolean topic)
        throws JMSException
    {
        if(producer == null)
        {
            Session s = getSession(true);
            producer = s.createProducer(null);
            start();
        }
        return producer;
    }

    public void start()
    {
        if(!start)
            try
            {
                getConnection().start();
                start = true;
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.getMessage(), e);
            }
    }

    public void close()
    {
        start = false;
        if(producer != null)
            producer.close();
        if(session != null)
            session.close();
        if(asyncSession != null)
            asyncSession.close();
        if(conn != null)
            conn.close();
        producer = null;
        session = null;
        asyncSession = null;
        conn = null;
        break MISSING_BLOCK_LABEL_156;
        JMSException e;
        e;
        log.error(e.getMessage(), e, new Object[0]);
        producer = null;
        session = null;
        asyncSession = null;
        conn = null;
        break MISSING_BLOCK_LABEL_156;
        Exception exception;
        exception;
        producer = null;
        session = null;
        asyncSession = null;
        conn = null;
        throw exception;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/component/AppcCompImpl$AppcActiveMQ);
    private Connection conn;
    private Session session;
    private Session asyncSession;
    private MessageProducer producer;
    private boolean start;
    private ExceptionListener exceptionListener;



}
