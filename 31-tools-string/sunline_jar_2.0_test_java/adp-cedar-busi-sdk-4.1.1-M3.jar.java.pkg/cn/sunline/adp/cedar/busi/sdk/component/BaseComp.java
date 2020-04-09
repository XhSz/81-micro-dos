// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseComp.java

package cn.sunline.adp.cedar.busi.sdk.component;

import cn.sunline.adp.cedar.base.exception.EdspServiceException;
import cn.sunline.adp.cedar.busi.sdk.type.CompTypes;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import java.util.Map;

public class BaseComp
{
    public static interface FPI
    {

        public abstract Integer getFeatureByImg(String s)
            throws EdspServiceException;

        public abstract Integer getTemplate(String s, String s1, String s2)
            throws EdspServiceException;

        public abstract Integer match(String s, String s1, Integer integer)
            throws EdspServiceException;

        public abstract String getPsErrMsg()
            throws EdspServiceException;

        public abstract Integer getPnVerLen()
            throws EdspServiceException;

        public abstract String getPsRegBuf()
            throws EdspServiceException;

        public abstract Integer getPnRegLen()
            throws EdspServiceException;

        public abstract String getPsVerBuf()
            throws EdspServiceException;
    }

    public static interface IcSecurity
    {

        public abstract String arqc(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
                String s7, String s8, String s9, String s10, String s11, String s12, String s13)
            throws EdspServiceException;

        public abstract String arpc(String s, String s1, String s2, String s3, String s4)
            throws EdspServiceException;

        public abstract String isscmac(String s, String s1, String s2, String s3, String s4, String s5)
            throws EdspServiceException;

        public abstract String isscenc(String s, String s1, String s2, String s3, String s4, String s5)
            throws EdspServiceException;

        public abstract String cvvauth(String s, String s1, String s2, String s3, String s4, String s5)
            throws EdspServiceException;
    }

    public static interface TransExecutor
    {

        public abstract Map call(Map map)
            throws EdspServiceException;
    }

    public static interface FileSecurity
    {

        public abstract Boolean encrypt(String s, String s1)
            throws EdspServiceException;

        public abstract Boolean decrypt(String s, String s1)
            throws EdspServiceException;
    }

    public static interface PkgHeader
    {

        public abstract String processRequest(Map map)
            throws EdspServiceException;

        public abstract String processResponse(String s)
            throws EdspServiceException;
    }

    public static interface Pack
    {

        public abstract String format(Map map)
            throws EdspServiceException;

        public abstract Map parse(String s)
            throws EdspServiceException;
    }

    public static interface Appc
    {

        public abstract String call(String s)
            throws EdspServiceException;

        public abstract String call(String s, Map map)
            throws EdspServiceException;
    }

    public static interface FileTransfer
    {

        public abstract Integer download(String s, String s1)
            throws EdspServiceException;

        public abstract Integer download(String s, String s1, String s2, String s3)
            throws EdspServiceException;

        public abstract Integer upload(String s, String s1, String s2)
            throws EdspServiceException;

        public abstract Integer upload(String s, String s1)
            throws EdspServiceException;

        public abstract String remoteDirectory(String s)
            throws EdspServiceException;

        public abstract cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult download(String s, Map map)
            throws EdspServiceException;

        public abstract cn.sunline.adp.cedar.busi.sdk.type.CompTypes.FileTransferResult upload(String s, Map map)
            throws EdspServiceException;

        public abstract String workDirectory()
            throws EdspServiceException;
    }

    public static interface Security
    {

        public abstract Boolean macCheck(String s, String s1, String s2)
            throws EdspServiceException;

        public abstract String encryptPin(String s, String s1)
            throws EdspServiceException;

        public abstract String decryptPin(String s, String s1)
            throws EdspServiceException;

        public abstract String translatePin(String s, String s1, String s2)
            throws EdspServiceException;

        public abstract String translatePin(String s, String s1, String s2, String s3, String s4)
            throws EdspServiceException;

        public abstract String cvvBuild(String s, String s1, String s2)
            throws EdspServiceException;

        public abstract Boolean cvvCheck(String s, String s1, String s2, String s3)
            throws EdspServiceException;
    }


    public BaseComp()
    {
    }
}
