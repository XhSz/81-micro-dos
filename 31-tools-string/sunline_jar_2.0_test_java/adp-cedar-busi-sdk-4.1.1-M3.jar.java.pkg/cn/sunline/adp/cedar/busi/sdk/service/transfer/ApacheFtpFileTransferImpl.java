// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ApacheFtpFileTransferImpl.java

package cn.sunline.adp.cedar.busi.sdk.service.transfer;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.busi.sdk.component.BaseComp;
import cn.sunline.adp.cedar.busi.sdk.component.FileTransferCompImpl;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.cedar.busi.sdk.service.SimpleFTPClient;
import cn.sunline.adp.cedar.busi.sdk.type.CompTypes;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import cn.sunline.edsp.base.util.file.FileUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.io.File;
import java.util.Map;

public class ApacheFtpFileTransferImpl extends cn.sunline.adp.cedar.busi.sdk.component.FileTransferCompImpl.ApacheFtpFileTransfer
    implements cn.sunline.adp.cedar.busi.sdk.component.BaseComp.FileTransfer
{

    public ApacheFtpFileTransferImpl()
    {
    }

    private SimpleFTPClient getFTPClient()
    {
        SimpleFTPClient ret = (new SimpleFTPClient()).connectTimeoutInMs(getConnectTimeoutInMs()).ip(getIp()).port(getPort()).user(getUser()).password(getPassword()).binaryMode(getBinaryMode());
        if(getRetryTime() != null)
            ret.setRetryTime(getRetryTime().intValue());
        if(getRetryInterval() != null)
            ret.setRetryInterval(getRetryInterval().intValue());
        return ret;
    }

    private String getFullPathName(String workDir, String fileName)
    {
        if(StringUtil.isEmpty(workDir))
            return fileName;
        String file = workDir;
        if(!isFileSeparator(file.charAt(file.length() - 1)))
            file = (new StringBuilder()).append(file).append("/").toString();
        if(StringUtil.isNotEmpty(fileName) && isFileSeparator(fileName.charAt(0)))
            file = (new StringBuilder()).append(file).append(fileName.substring(1)).toString();
        else
            file = (new StringBuilder()).append(file).append(fileName).toString();
        return file;
    }

    public cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult download(String localFileName, Map properties)
        throws EdspServiceException
    {
        cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult ret = (cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult)EdspCoreBeanUtil.getModelObjectCreator().create(cn/sunline/adp/cedar/busi/sdk/type/CompTypes$FileTransferResult);
        ret.setCode(download(localFileName, (String)properties.get("remoteFileName")));
        ret.setProperties(properties);
        return ret;
    }

    public cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult upload(String localFileName, Map properties)
        throws EdspServiceException
    {
        cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult ret = (cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult)EdspCoreBeanUtil.getModelObjectCreator().create(cn/sunline/adp/cedar/busi/sdk/type/CompTypes$FileTransferResult);
        ret.setCode(upload(localFileName, (String)properties.get("remoteFileName")));
        ret.setProperties(properties);
        return ret;
    }

    public String workDirectory()
        throws EdspServiceException
    {
        String file = getLocalWorkDir();
        if(StringUtil.isNotEmpty(file))
        {
            file = file.replace('/', File.separatorChar);
            file = file.replace('\\', File.separatorChar);
            if(!isFileSeparator(file.charAt(file.length() - 1)))
                file = (new StringBuilder()).append(file).append(File.separatorChar).toString();
        }
        return file;
    }

    private boolean isFileSeparator(char cha)
    {
        return cha == '/' || cha == '\\';
    }

    public Integer download(String localFileName, String remoteFileName)
        throws EdspServiceException
    {
        localFileName = getFullPathName(workDirectory(), localFileName);
        remoteFileName = getFullPathName(getRemoteWorkDir(), remoteFileName);
        SimpleFTPClient ftp = getFTPClient();
        try
        {
            ftp.download(localFileName, remoteFileName);
            log.info(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C021(), new Object[] {
                getIp(), getUser(), remoteFileName, localFileName
            });
        }
        catch(Exception e)
        {
            log.error(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C022(), e, new Object[] {
                getIp(), getUser(), remoteFileName, localFileName
            });
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E030(e);
        }
        return Integer.valueOf(0);
    }

    public Integer download(String sysId, String trandate, String localFileName, String remoteFileName)
        throws EdspServiceException
    {
        return download(localFileName, remoteFileName);
    }

    public Integer upload(String trandate, String localFileName, String remoteFileName)
        throws EdspServiceException
    {
        return upload(localFileName, remoteFileName);
    }

    public Integer upload(String localFileName, String remoteFileName)
        throws EdspServiceException
    {
        localFileName = getFullPathName(workDirectory(), localFileName);
        remoteFileName = getFullPathName(getRemoteWorkDir(), remoteFileName);
        SimpleFTPClient ftp = getFTPClient();
        try
        {
            ftp.upload(localFileName, remoteFileName);
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E031(e);
        }
        return Integer.valueOf(0);
    }

    public String remoteDirectory(String directory)
        throws EdspServiceException
    {
        String file = "";
        if(StringUtil.isNotEmpty(directory))
        {
            file = directory;
            setRemoteWorkDir(file);
        } else
        {
            file = getRemoteWorkDir();
        }
        file = file.replace('/', File.separatorChar);
        file = file.replace('\\', File.separatorChar);
        if(!isFileSeparator(file.charAt(file.length() - 1)))
            file = (new StringBuilder()).append(file).append(File.separatorChar).toString();
        return file;
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/service/transfer/ApacheFtpFileTransferImpl);

}
