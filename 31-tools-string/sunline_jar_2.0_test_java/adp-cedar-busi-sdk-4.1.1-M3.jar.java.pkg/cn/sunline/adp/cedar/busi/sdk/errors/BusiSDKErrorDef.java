// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BusiSDKErrorDef.java

package cn.sunline.adp.cedar.busi.sdk.errors;

import cn.sunline.adp.core.exception.AdpBusinessException;
import cn.sunline.adp.core.exception.ErrorType;
import cn.sunline.adp.core.lang.IString;
import cn.sunline.edsp.base.lang.Params;

public class BusiSDKErrorDef
{
    public static class SP_BS
    {

        public static String E001_ERROR(String tableName)
        {
            Params context = (new Params()).add("tableName", tableName);
            String message = (new IString(E001, context)).getString();
            return message;
        }

        public static AdpBusinessException E001(String tableName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E001", new IString(E001, context));
        }

        public static AdpBusinessException E001(String tableName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E001", new IString(E001, context), t);
        }

        public static String E002_ERROR(String fileName)
        {
            Params context = (new Params()).add("fileName", fileName);
            String message = (new IString(E002, context)).getString();
            return message;
        }

        public static AdpBusinessException E002(String fileName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("fileName", fileName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E002", new IString(E002, context));
        }

        public static AdpBusinessException E002(String fileName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("fileName", fileName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E002", new IString(E002, context), t);
        }

        public static String E003_ERROR(String repProcessor)
        {
            Params context = (new Params()).add("repProcessor", repProcessor);
            String message = (new IString(E003, context)).getString();
            return message;
        }

        public static AdpBusinessException E003(String repProcessor)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("repProcessor", repProcessor);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E003", new IString(E003, context));
        }

        public static AdpBusinessException E003(String repProcessor, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("repProcessor", repProcessor);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E003", new IString(E003, context), t);
        }

        public static String E004_ERROR(String reportId, String errMsg)
        {
            Params context = (new Params()).add("reportId", reportId).add("errMsg", errMsg);
            String message = (new IString(E004, context)).getString();
            return message;
        }

        public static AdpBusinessException E004(String reportId, String errMsg)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("errMsg", errMsg);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E004", new IString(E004, context));
        }

        public static AdpBusinessException E004(String reportId, String errMsg, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("errMsg", errMsg);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E004", new IString(E004, context), t);
        }

        public static String E005_ERROR(String reportId, String filePath)
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            String message = (new IString(E005, context)).getString();
            return message;
        }

        public static AdpBusinessException E005(String reportId, String filePath)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E005", new IString(E005, context));
        }

        public static AdpBusinessException E005(String reportId, String filePath, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E005", new IString(E005, context), t);
        }

        public static String E006_ERROR(String reportId, String filePath)
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            String message = (new IString(E006, context)).getString();
            return message;
        }

        public static AdpBusinessException E006(String reportId, String filePath)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E006", new IString(E006, context));
        }

        public static AdpBusinessException E006(String reportId, String filePath, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("filePath", filePath);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E006", new IString(E006, context), t);
        }

        public static String E007_ERROR()
        {
            return E007.getString();
        }

        public static AdpBusinessException E007()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E007", new IString(E007, null));
        }

        public static AdpBusinessException E007(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E007", new IString(E007, null), t);
        }

        public static String E008_ERROR()
        {
            return E008.getString();
        }

        public static AdpBusinessException E008()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E008", new IString(E008, null));
        }

        public static AdpBusinessException E008(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E008", new IString(E008, null), t);
        }

        public static String E009_ERROR(String prcscd)
        {
            Params context = (new Params()).add("prcscd", prcscd);
            String message = (new IString(E009, context)).getString();
            return message;
        }

        public static AdpBusinessException E009(String prcscd)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("prcscd", prcscd);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E009", new IString(E009, context));
        }

        public static AdpBusinessException E009(String prcscd, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("prcscd", prcscd);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E009", new IString(E009, context), t);
        }

        public static String E010_ERROR()
        {
            return E010.getString();
        }

        public static AdpBusinessException E010()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E010", new IString(E010, null));
        }

        public static AdpBusinessException E010(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E010", new IString(E010, null), t);
        }

        public static String E011_ERROR(String tableName)
        {
            Params context = (new Params()).add("tableName", tableName);
            String message = (new IString(E011, context)).getString();
            return message;
        }

        public static AdpBusinessException E011(String tableName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E011", new IString(E011, context));
        }

        public static AdpBusinessException E011(String tableName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E011", new IString(E011, context), t);
        }

        public static String E012_ERROR()
        {
            return E012.getString();
        }

        public static AdpBusinessException E012()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E012", new IString(E012, null));
        }

        public static AdpBusinessException E012(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E012", new IString(E012, null), t);
        }

        public static String E013_ERROR(String className, String impName)
        {
            Params context = (new Params()).add("className", className).add("impName", impName);
            String message = (new IString(E013, context)).getString();
            return message;
        }

        public static AdpBusinessException E013(String className, String impName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("className", className).add("impName", impName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E013", new IString(E013, context));
        }

        public static AdpBusinessException E013(String className, String impName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("className", className).add("impName", impName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E013", new IString(E013, context), t);
        }

        public static String E014_ERROR()
        {
            return E014.getString();
        }

        public static AdpBusinessException E014()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E014", new IString(E014, null));
        }

        public static AdpBusinessException E014(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E014", new IString(E014, null), t);
        }

        public static String E015_ERROR()
        {
            return E015.getString();
        }

        public static AdpBusinessException E015()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E015", new IString(E015, null));
        }

        public static AdpBusinessException E015(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E015", new IString(E015, null), t);
        }

        public static String E016_ERROR()
        {
            return E016.getString();
        }

        public static AdpBusinessException E016()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E016", new IString(E016, null));
        }

        public static AdpBusinessException E016(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E016", new IString(E016, null), t);
        }

        public static String E017_ERROR()
        {
            return E017.getString();
        }

        public static AdpBusinessException E017()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E017", new IString(E017, null));
        }

        public static AdpBusinessException E017(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E017", new IString(E017, null), t);
        }

        public static String E018_ERROR()
        {
            return E018.getString();
        }

        public static AdpBusinessException E018()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E018", new IString(E018, null));
        }

        public static AdpBusinessException E018(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E018", new IString(E018, null), t);
        }

        public static String E019_ERROR(Integer expMsgLen, Integer curMsgLen)
        {
            Params context = (new Params()).add("expMsgLen", expMsgLen).add("curMsgLen", curMsgLen);
            String message = (new IString(E019, context)).getString();
            return message;
        }

        public static AdpBusinessException E019(Integer expMsgLen, Integer curMsgLen)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("expMsgLen", expMsgLen).add("curMsgLen", curMsgLen);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E019", new IString(E019, context));
        }

        public static AdpBusinessException E019(Integer expMsgLen, Integer curMsgLen, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("expMsgLen", expMsgLen).add("curMsgLen", curMsgLen);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E019", new IString(E019, context), t);
        }

        public static String E020_ERROR()
        {
            return E020.getString();
        }

        public static AdpBusinessException E020()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E020", new IString(E020, null));
        }

        public static AdpBusinessException E020(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E020", new IString(E020, null), t);
        }

        public static String E021_ERROR()
        {
            return E021.getString();
        }

        public static AdpBusinessException E021()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E021", new IString(E021, null));
        }

        public static AdpBusinessException E021(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E021", new IString(E021, null), t);
        }

        public static String E022_ERROR()
        {
            return E022.getString();
        }

        public static AdpBusinessException E022()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E022", new IString(E022, null));
        }

        public static AdpBusinessException E022(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E022", new IString(E022, null), t);
        }

        public static String E023_ERROR()
        {
            return E023.getString();
        }

        public static AdpBusinessException E023()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E023", new IString(E023, null));
        }

        public static AdpBusinessException E023(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E023", new IString(E023, null), t);
        }

        public static String E024_ERROR()
        {
            return E024.getString();
        }

        public static AdpBusinessException E024()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E024", new IString(E024, null));
        }

        public static AdpBusinessException E024(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E024", new IString(E024, null), t);
        }

        public static String E025_ERROR()
        {
            return E025.getString();
        }

        public static AdpBusinessException E025()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E025", new IString(E025, null));
        }

        public static AdpBusinessException E025(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E025", new IString(E025, null), t);
        }

        public static String E026_ERROR()
        {
            return E026.getString();
        }

        public static AdpBusinessException E026()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E026", new IString(E026, null));
        }

        public static AdpBusinessException E026(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E026", new IString(E026, null), t);
        }

        public static String E027_ERROR()
        {
            return E027.getString();
        }

        public static AdpBusinessException E027()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E027", new IString(E027, null));
        }

        public static AdpBusinessException E027(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E027", new IString(E027, null), t);
        }

        public static String E028_ERROR(String pkgAbstId)
        {
            Params context = (new Params()).add("pkgAbstId", pkgAbstId);
            String message = (new IString(E028, context)).getString();
            return message;
        }

        public static AdpBusinessException E028(String pkgAbstId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("pkgAbstId", pkgAbstId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E028", new IString(E028, context));
        }

        public static AdpBusinessException E028(String pkgAbstId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("pkgAbstId", pkgAbstId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E028", new IString(E028, context), t);
        }

        public static String E029_ERROR()
        {
            return E029.getString();
        }

        public static AdpBusinessException E029()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E029", new IString(E029, null));
        }

        public static AdpBusinessException E029(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E029", new IString(E029, null), t);
        }

        public static String E030_ERROR()
        {
            return E030.getString();
        }

        public static AdpBusinessException E030()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E030", new IString(E030, null));
        }

        public static AdpBusinessException E030(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E030", new IString(E030, null), t);
        }

        public static String E031_ERROR()
        {
            return E031.getString();
        }

        public static AdpBusinessException E031()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E031", new IString(E031, null));
        }

        public static AdpBusinessException E031(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E031", new IString(E031, null), t);
        }

        public static String E032_ERROR(String reportid, String longname, String className)
        {
            Params context = (new Params()).add("reportid", reportid).add("longname", longname).add("className", className);
            String message = (new IString(E032, context)).getString();
            return message;
        }

        public static AdpBusinessException E032(String reportid, String longname, String className)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportid", reportid).add("longname", longname).add("className", className);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E032", new IString(E032, context));
        }

        public static AdpBusinessException E032(String reportid, String longname, String className, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportid", reportid).add("longname", longname).add("className", className);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E032", new IString(E032, context), t);
        }

        public static String E033_ERROR()
        {
            return E033.getString();
        }

        public static AdpBusinessException E033()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E033", new IString(E033, null));
        }

        public static AdpBusinessException E033(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E033", new IString(E033, null), t);
        }

        public static String E034_ERROR(String reportId)
        {
            Params context = (new Params()).add("reportId", reportId);
            String message = (new IString(E034, context)).getString();
            return message;
        }

        public static AdpBusinessException E034(String reportId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E034", new IString(E034, context));
        }

        public static AdpBusinessException E034(String reportId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E034", new IString(E034, context), t);
        }

        public static String E035_ERROR(String reportId, String Jaspertemplate)
        {
            Params context = (new Params()).add("reportId", reportId).add("Jaspertemplate", Jaspertemplate);
            String message = (new IString(E035, context)).getString();
            return message;
        }

        public static AdpBusinessException E035(String reportId, String Jaspertemplate)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("Jaspertemplate", Jaspertemplate);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E035", new IString(E035, context));
        }

        public static AdpBusinessException E035(String reportId, String Jaspertemplate, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId).add("Jaspertemplate", Jaspertemplate);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E035", new IString(E035, context), t);
        }

        public static String E036_ERROR(String reportId)
        {
            Params context = (new Params()).add("reportId", reportId);
            String message = (new IString(E036, context)).getString();
            return message;
        }

        public static AdpBusinessException E036(String reportId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E036", new IString(E036, context));
        }

        public static AdpBusinessException E036(String reportId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("reportId", reportId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E036", new IString(E036, context), t);
        }

        public static String E037_ERROR(String sourceId)
        {
            Params context = (new Params()).add("sourceId", sourceId);
            String message = (new IString(E037, context)).getString();
            return message;
        }

        public static AdpBusinessException E037(String sourceId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("sourceId", sourceId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E037", new IString(E037, context));
        }

        public static AdpBusinessException E037(String sourceId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("sourceId", sourceId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_BS.E037", new IString(E037, context), t);
        }

        public static final String F_E001 = "SP_BS.E001";
        private static final IString E001 = new IString("BusiSDKErrorDef.SP_BS.E001", "\u8868[${tableName}]\u672A\u5B9A\u4E49");
        public static final String F_E002 = "SP_BS.E002";
        private static final IString E002 = new IString("BusiSDKErrorDef.SP_BS.E002", "\u8BFB\u53D6\u6587\u4EF6\uFF1A${fileName} \u51FA\u9519!");
        public static final String F_E003 = "SP_BS.E003";
        private static final IString E003 = new IString("BusiSDKErrorDef.SP_BS.E003", "\u83B7\u53D6\u62A5\u8868\u5904\u7406\u5668[${repProcessor}]\u5931\u8D25");
        public static final String F_E004 = "SP_BS.E004";
        private static final IString E004 = new IString("BusiSDKErrorDef.SP_BS.E004", "\u586B\u5145\u62A5\u8868[${reportId}]\u6570\u636E\u5931\u8D25\uFF01${errMsg}");
        public static final String F_E005 = "SP_BS.E005";
        private static final IString E005 = new IString("BusiSDKErrorDef.SP_BS.E005", "\u62A5\u8868[${reportId}]\u6E32\u67D3[${filePath}]\u5931\u8D25!");
        public static final String F_E006 = "SP_BS.E006";
        private static final IString E006 = new IString("BusiSDKErrorDef.SP_BS.E006", "\u62A5\u8868[${reportId}]\u5185\u5BB9\u5199\u5165\u6587\u4EF6[${filePath}]\u5931\u8D25!");
        public static final String F_E007 = "SP_BS.E007";
        private static final IString E007 = new IString("BusiSDKErrorDef.SP_BS.E007", "\u5360\u4E0D\u652F\u6301\u8FDC\u7A0B\u65B9\u5F0F\u8C03\u7528");
        public static final String F_E008 = "SP_BS.E008";
        private static final IString E008 = new IString("BusiSDKErrorDef.SP_BS.E008", "\u8C03\u7528\u4EA4\u6613\u670D\u52A1\u5931\u8D25\uFF0C\u4EA4\u6613\u7801\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E009 = "SP_BS.E009";
        private static final IString E009 = new IString("BusiSDKErrorDef.SP_BS.E009", "\u8C03\u7528\u4EA4\u6613\u670D\u52A1[${prcscd}]\u5931\u8D25\uFF0C\u4EA4\u6613\u8F93\u5165\u6570\u636E\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E010 = "SP_BS.E010";
        private static final IString E010 = new IString("BusiSDKErrorDef.SP_BS.E010", "\u7CFB\u7EDF\u9519\u8BEF\uFF1A\u4EA4\u6613\u6269\u5C55\u70B9\u670D\u52A1\u672A\u542F\u52A8\uFF0C\u65E0\u6CD5\u83B7\u53D6\u6269\u5C55\u70B9\u914D\u7F6E\u4FE1\u606F\uFF01");
        public static final String F_E011 = "SP_BS.E011";
        private static final IString E011 = new IString("BusiSDKErrorDef.SP_BS.E011", "\u53C2\u6570\u8868[${tableName}]\u672A\u627E\u5230\u5BF9\u5E94\u7684\u914D\u7F6E\u4FE1\u606F!");
        public static final String F_E012 = "SP_BS.E012";
        private static final IString E012 = new IString("BusiSDKErrorDef.SP_BS.E012", "\u83B7\u53D6\u53C2\u6570\u5904\u7406\u5668\u7C7B\u8DEF\u5F84\u5931\u8D25");
        public static final String F_E013 = "SP_BS.E013";
        private static final IString E013 = new IString("BusiSDKErrorDef.SP_BS.E013", "'${className}' must implement interface: '${impName}'");
        public static final String F_E014 = "SP_BS.E014";
        private static final IString E014 = new IString("BusiSDKErrorDef.SP_BS.E014", "MQ\u6D88\u606F\u53D1\u9001\u5931\u8D25");
        public static final String F_E015 = "SP_BS.E015";
        private static final IString E015 = new IString("BusiSDKErrorDef.SP_BS.E015", "\u8FDE\u63A5\u5931\u8D25");
        public static final String F_E016 = "SP_BS.E016";
        private static final IString E016 = new IString("BusiSDKErrorDef.SP_BS.E016", "\u62A5\u6587\u5934\u957F\u5EA6\u672A\u914D\u7F6E");
        public static final String F_E017 = "SP_BS.E017";
        private static final IString E017 = new IString("BusiSDKErrorDef.SP_BS.E017", "\u8FD4\u56DE\u62A5\u6587\u957F\u5EA6\u4E0D\u6B63\u786E");
        public static final String F_E018 = "SP_BS.E018";
        private static final IString E018 = new IString("BusiSDKErrorDef.SP_BS.E018", "Tuxedo\u670D\u52A1\u8C03\u7528\u5931\u8D25");
        public static final String F_E019 = "SP_BS.E019";
        private static final IString E019 = new IString("BusiSDKErrorDef.SP_BS.E019", "Exceed to max message size:${expMsgLen} -> ${curMsgLen}");
        public static final String F_E020 = "SP_BS.E020";
        private static final IString E020 = new IString("BusiSDKErrorDef.SP_BS.E020", "udp\u53D1\u9001\u8D85\u65F6");
        public static final String F_E021 = "SP_BS.E021";
        private static final IString E021 = new IString("BusiSDKErrorDef.SP_BS.E021", "udp\u53D1\u9001\u5931\u8D25");
        public static final String F_E022 = "SP_BS.E022";
        private static final IString E022 = new IString("BusiSDKErrorDef.SP_BS.E022", "udp\u63A5\u6536\u8D85\u65F6");
        public static final String F_E023 = "SP_BS.E023";
        private static final IString E023 = new IString("BusiSDKErrorDef.SP_BS.E023", "udp\u63A5\u6536\u5931\u8D25");
        public static final String F_E024 = "SP_BS.E024";
        private static final IString E024 = new IString("BusiSDKErrorDef.SP_BS.E024", "\u53D1\u9001\u5931\u8D25");
        public static final String F_E025 = "SP_BS.E025";
        private static final IString E025 = new IString("BusiSDKErrorDef.SP_BS.E025", "\u53D1\u9001\u6210\u529F\u4F46\u63A5\u6536\u5931\u8D25, \u8BF7\u68C0\u67E5\u4EA4\u6613\u662F\u5426\u6210\u529F\uFF1F");
        public static final String F_E026 = "SP_BS.E026";
        private static final IString E026 = new IString("BusiSDKErrorDef.SP_BS.E026", "\u53D1\u9001\u6210\u529F\u4F46\u63A5\u6536\u5931\u8D25, \u5BF9\u65B9\u5DF2\u5173\u95ED\u8FDE\u63A5, \u8BF7\u68C0\u67E5\u4EA4\u6613\u662F\u5426\u6210\u529F\uFF1F");
        public static final String F_E027 = "SP_BS.E027";
        private static final IString E027 = new IString("BusiSDKErrorDef.SP_BS.E027", "Received message is null.");
        public static final String F_E028 = "SP_BS.E028";
        private static final IString E028 = new IString("BusiSDKErrorDef.SP_BS.E028", "\u83B7\u53D6\u62BD\u8C61\u7EC4\u4EF6ID\u4E3A[${pkgAbstId}]\u5BF9\u5E94\u7684\u7EC4\u4EF6\u5B9E\u4F8B\u5931\u8D25!");
        public static final String F_E029 = "SP_BS.E029";
        private static final IString E029 = new IString("BusiSDKErrorDef.SP_BS.E029", "\u7EC4\u5305\u5931\u8D25");
        public static final String F_E030 = "SP_BS.E030";
        private static final IString E030 = new IString("BusiSDKErrorDef.SP_BS.E030", "\u6587\u4EF6\u4E0B\u8F7D\u5931\u8D25");
        public static final String F_E031 = "SP_BS.E031";
        private static final IString E031 = new IString("BusiSDKErrorDef.SP_BS.E031", "\u6587\u4EF6\u4E0A\u4F20\u5931\u8D25");
        public static final String F_E032 = "SP_BS.E032";
        private static final IString E032 = new IString("BusiSDKErrorDef.SP_BS.E032", "\u672A\u627E\u5230\u62A5\u8868[${reportid}][${longname}]\u5BF9\u5E94\u7684\u62A5\u8868\u6570\u636E\u5904\u7406\u5668[${className}]");
        public static final String F_E033 = "SP_BS.E033";
        private static final IString E033 = new IString("BusiSDKErrorDef.SP_BS.E033", "\u62A5\u8868ID\u4E3A\u7A7A\uFF0C\u8BF7\u68C0\u67E5\u62A5\u6587\u4E2Dsys\u533A\u57DF\u4E2DreportId\u5B57\u6BB5\u4E0D\u4E3A\u7A7A\uFF01");
        public static final String F_E034 = "SP_BS.E034";
        private static final IString E034 = new IString("BusiSDKErrorDef.SP_BS.E034", "\u83B7\u53D6\u62A5\u8868[${reportId}]\u5B9A\u4E49\u6587\u4EF6\u5931\u8D25");
        public static final String F_E035 = "SP_BS.E035";
        private static final IString E035 = new IString("BusiSDKErrorDef.SP_BS.E035", "\u52A0\u8F7D\u62A5\u8868\u6A21\u677F\u5931\u8D25\uFF01\u62A5\u8868[${reportId}]\u5BF9\u5E94\u7684\u6A21\u677F[${Jaspertemplate}]\u672A\u627E\u5230\uFF01");
        public static final String F_E036 = "SP_BS.E036";
        private static final IString E036 = new IString("BusiSDKErrorDef.SP_BS.E036", "\u7F16\u8BD1\u62A5\u8868\u6A21\u677F[${reportId}.jrxml]\u5931\u8D25\uFF01\u8BF7\u68C0\u67E5\u6A21\u677F\u4E2D\u63A7\u4EF6\u503C\u662F\u5426\u6B63\u786E\uFF01");
        public static final String F_E037 = "SP_BS.E037";
        private static final IString E037 = new IString("BusiSDKErrorDef.SP_BS.E037", "\u6570\u636E\u6E90[${sourceId}]\u4E0D\u5B58\u5728,\u8BF7\u68C0\u67E5\u6570\u636E\u6E90\u914D\u7F6E\u6587\u4EF6");


        public SP_BS()
        {
        }
    }


    public BusiSDKErrorDef()
    {
    }
}
