// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WorkerHealthCheck.java

package cn.sunline.adp.cedar.base.healthcheck;

import cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor;
import cn.sunline.adp.dao.base.DaoContext;
import java.io.IOException;
import java.net.Socket;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.base.healthcheck:
//            HealthCheck, DefaultWorkerChannel, WorkerChannelBoard

public class WorkerHealthCheck extends HealthCheck
{

    public WorkerHealthCheck(WorkerChannelBoard board)
    {
        closeCriticalResourceWhenTimeout = true;
        this.board = board;
        maxTimeOut = Long.valueOf(0L);
        closeCriticalResourceWhenTimeout = true;
    }

    public WorkerHealthCheck(WorkerChannelBoard board, Long maxTimeOut, boolean closeCriticalResourceWhenTimeout)
    {
        this.closeCriticalResourceWhenTimeout = true;
        this.board = board;
        this.maxTimeOut = maxTimeOut;
        this.closeCriticalResourceWhenTimeout = closeCriticalResourceWhenTimeout;
    }

    protected HealthCheck.Result check()
        throws Exception
    {
        Iterator iterator;
        List channels = board.getActiveWorkerChannels();
        iterator = channels.iterator();
_L2:
        DefaultWorkerChannel chl;
        if(!iterator.hasNext())
            break; /* Loop/switch isn't completed */
        chl = (DefaultWorkerChannel)iterator.next();
        if(!chl.getThread().isAlive())
        {
            syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C001(), new Object[] {
                chl.getThread().getName(), chl.getId()
            });
            board.removeWorkerChannel(chl);
            continue; /* Loop/switch isn't completed */
        }
        long timeout;
        Long timeoutInSecond = DaoContext.get(chl.getThread()).getTimeoutInSeconds();
        timeout = timeoutInSecond != null ? timeoutInSecond.longValue() * 1000L : 0L;
        if(timeout <= 0L)
            continue; /* Loop/switch isn't completed */
        DefaultWorkerChannel.TransData trans = chl.getProcessingTransData();
        if(!chl.getThread().isAlive() || trans == null || System.currentTimeMillis() - trans.getBeginProcessTime() <= timeout || chl.getThread().getName().indexOf("-[Stuck") != -1 && !DaoContext.get(chl.getThread()).isAfterExceptionProcess())
            continue; /* Loop/switch isn't completed */
        chl.getThread().setName((new StringBuilder()).append(chl.getThread().getName()).append("-[Stuck ").append(new Timestamp(trans.getBeginProcessTime())).append("]").toString());
        try
        {
            StringBuffer sb = new StringBuffer();
            StackTraceElement traces[] = chl.getThread().getStackTrace();
            StackTraceElement astacktraceelement[] = traces;
            int i = astacktraceelement.length;
            for(int j = 0; j < i; j++)
            {
                StackTraceElement trace = astacktraceelement[j];
                sb.append((new StringBuilder()).append("\tat ").append(trace).append("\r\n").toString());
            }

            syslog.error(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C002(), new Object[] {
                chl.getThread().getName(), sb
            });
            chl.setTransactionTimeout(true);
            chl.getThread().interrupt();
            DaoContext daoContext = DaoContext.get(chl.getThread());
            if(daoContext != null)
            {
                daoContext.setTransactionTimeout(true);
                Statement statement = daoContext.getCurrentStatement();
                if(statement != null && !statement.isClosed())
                {
                    if(syslog.isInfoEnabled())
                        syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C003());
                    try
                    {
                        statement.cancel();
                    }
                    catch(Throwable t)
                    {
                        syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C004(), t, new Object[0]);
                    }
                }
            }
            if(closeCriticalResourceWhenTimeout)
            {
                if(syslog.isInfoEnabled())
                    syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C005(), new Object[] {
                        Integer.valueOf(chl.getCriticalResources().size())
                    });
                Iterator iterator1 = chl.getCriticalResources().iterator();
                do
                {
                    if(!iterator1.hasNext())
                        break;
                    cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource resource = (cn.sunline.adp.cedar.base.srv.ThreadCriticalResourceAccessor.CriticalResource)iterator1.next();
                    if(syslog.isInfoEnabled())
                        syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C006(), new Object[] {
                            resource.getType(), resource
                        });
                    try
                    {
                        if(resource != null)
                            resource.close();
                    }
                    catch(Exception e)
                    {
                        syslog.error(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C007(), e, new Object[] {
                            resource
                        });
                    }
                } while(true);
                if(chl.getSocket() != null)
                {
                    if(syslog.isInfoEnabled())
                        syslog.info(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C008(), new Object[] {
                            chl.getSocket()
                        });
                    try
                    {
                        chl.getSocket().close();
                    }
                    catch(IOException e)
                    {
                        syslog.error(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C009(), e, new Object[] {
                            e.getMessage()
                        });
                    }
                }
            }
            if(chl.getThread().isAlive() || !chl.getThread().isInterrupted())
                chl.getThread().interrupt();
        }
        catch(Exception e)
        {
            syslog.warn(cn.sunline.adp.cedar.base.constant.EdspBaseHealthCheckConstantDef.HealthCheckConstant.C010(), e, new Object[0]);
        }
        if(true) goto _L2; else goto _L1
_L1:
        return HealthCheck.Result.healthy();
    }

    private static SysLog syslog = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/healthcheck/WorkerHealthCheck);
    private boolean closeCriticalResourceWhenTimeout;
    private Long maxTimeOut;
    private WorkerChannelBoard board;

}
