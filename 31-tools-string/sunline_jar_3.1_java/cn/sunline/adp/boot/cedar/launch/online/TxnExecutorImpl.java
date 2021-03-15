// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TxnExecutorImpl.java

package cn.sunline.adp.boot.cedar.launch.online;

import cn.sunline.adp.boot.cedar.launch.IDERunUtil;
import cn.sunline.adp.boot.cedar.launch.IOnlineIDERunner;
import cn.sunline.adp.cedar.base.engine.*;
import cn.sunline.adp.cedar.base.engine.data.DataArea;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.pkg.*;
import cn.sunline.adp.cedar.base.util.CommUtil;
import cn.sunline.adp.cedar.engine.online.InServiceController;
import cn.sunline.adp.cedar.engine.online.OnlineEngineTemplate;
import cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef;
import cn.sunline.adp.core.GlobalContext;
import cn.sunline.adp.core.profile.ProfileSwitcher;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.dao.base.conn.DBConnectionManager;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class TxnExecutorImpl extends IOnlineIDERunner
{

    public TxnExecutorImpl()
    {
    }

    public void run(String args[])
    {
        if(args.length < 2)
        {
            System.err.println("Usage: TxnExecutor RequestData");
            return;
        }
        if(System.getProperty("ltts.home") == null)
            System.setProperty("ltts.home", System.getProperty("user.dir"));
        IDERunUtil.init(args);
        try
        {
            handle(args);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.wrapThrow(e);
        }
        try
        {
            Thread.sleep(2000L);
            System.exit(0);
        }
        catch(Exception ex)
        {
            log.error("TxnExecutorImpl error!", ex, new Object[0]);
            System.exit(0);
        }
    }

    public String[] handle(String args[])
    {
        List ret;
        if(args.length < 2)
        {
            System.err.println("Usage: TxnExecutor RequestData");
            return null;
        }
        ret = new ArrayList();
        ExceptionUtil.println(StringUtil.format(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C035(), new Object[] {
            GlobalContext.get().getStatus()
        }));
        int i = 1;
_L2:
        if(i >= args.length)
            break; /* Loop/switch isn't completed */
        if(i != 1 && StringUtil.isEmpty(args[i]))
        {
            log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C037());
            break; /* Loop/switch isn't completed */
        }
        log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C036());
        ret.add(handle(args[i]));
        Exception e;
        Exception exception;
        log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C037());
        i++;
        continue; /* Loop/switch isn't completed */
        e;
        log.error(e.getMessage(), e, new Object[0]);
        log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C037());
        break; /* Loop/switch isn't completed */
        exception;
        log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C037());
        throw exception;
        if(true) goto _L2; else goto _L1
_L1:
        try
        {
            EdspCoreBeanUtil.getDBConnectionManager().close();
        }
        catch(Exception e)
        {
            log.error("close db connection fail", e, new Object[0]);
        }
        break MISSING_BLOCK_LABEL_242;
        Exception exception1;
        exception1;
        try
        {
            EdspCoreBeanUtil.getDBConnectionManager().close();
        }
        catch(Exception e)
        {
            log.error("close db connection fail", e, new Object[0]);
        }
        throw exception1;
        return (String[])ret.toArray(new String[0]);
    }

    private String handle(String data)
        throws IOException
    {
        if(data == null || data.length() == 0)
            throw new IOException("Request data is null.");
        Date start = new Date();
        if(log.isDebugEnabled())
            log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C038(), new Object[] {
                data
            });
        String pkgType = System.getProperty("pkgType");
        PkgParser parser = PkgFactory.get().getPkgParser();
        if(CommUtil.isNotNull(System.getProperty("pkgType")))
            parser = PkgFactory.get().getPkgParser(pkgType);
        DataArea requestDataArea = parser.parse(data, null, PkgMode.request);
        ProfileSwitcher.get().setSystemOptions(requestDataArea.getSystemProfile());
        ProfileSwitcher.get().useDebugListener = true;
        ProfileUtil.cleanup(true);
        requestDataArea.getSystem().remove(HeaderDataConstants.NAME_DEBUG);
        RequestData request = new RequestData(data, requestDataArea);
        InServiceController serviceController;
        if("s".equals(request.getRequestHeader().get("ServiceCategory")))
            serviceController = new InServiceController(request.getRequestHeader().getServiceCode(), null, cn.sunline.adp.cedar.engine.online.InServiceController.ServiceCategory.S);
        else
        if("f".equals(request.getRequestHeader().get("ServiceCategory")))
        {
            serviceController = new InServiceController(request.getRequestHeader().getServiceCode(), null, cn.sunline.adp.cedar.engine.online.InServiceController.ServiceCategory.F);
        } else
        {
            String flowTranId = request.getRequestHeader().getServiceCode();
            if(StringUtil.isBlank(flowTranId))
                flowTranId = (String)requestDataArea.getSystem().get(HeaderDataConstants.NAME_PRCSCD);
            serviceController = new InServiceController(flowTranId);
        }
        OnlineEngineTemplate engineTemplate = new OnlineEngineTemplate();
        ResponseData response = engineTemplate.process(start, request, serviceController);
        String output = PkgFactory.get().getPkgWrapper().format(response.getBody(), null, PkgMode.response);
        log.debug(cn.sunline.adp.cedar.engine.online.constant.OnlineEngineConstantDef.OnlineEngineConstant.C039(), new Object[] {
            output
        });
        if(PROFILE_LOGGER.isInfoEnabled())
        {
            cn.sunline.adp.core.profile.PerformanceData pd = ProfileUtil.getPerformanceData();
            PROFILE_LOGGER.info(ProfileUtil.getProfileMessage(System.currentTimeMillis() - start.getTime()));
        }
        ProfileUtil.cleanup(false);
        String tranStatus = (String)response.getBody().getSystem().get(HeaderDataConstants.NAME_RET_STATUS);
        if(StringUtil.isNotEmpty(tranStatus) && "F".equals(tranStatus))
            throw new IllegalArgumentException((new StringBuilder()).append("Transaction is error! ").append(response.getBody().getSystem().get(HeaderDataConstants.NAME_ERORTX)).toString());
        else
            return output;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/boot/cedar/launch/online/TxnExecutorImpl);
    private static final SysLog PROFILE_LOGGER = SysLogUtil.getProfileLogger();
    private static final String PKGTYPE = "pkgType";

}
