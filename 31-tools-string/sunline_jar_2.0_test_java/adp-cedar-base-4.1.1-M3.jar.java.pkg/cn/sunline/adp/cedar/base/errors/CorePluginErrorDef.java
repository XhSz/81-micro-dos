// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CorePluginErrorDef.java

package cn.sunline.adp.cedar.base.errors;

import cn.sunline.adp.core.exception.AdpBusinessException;
import cn.sunline.adp.core.exception.ErrorType;
import cn.sunline.adp.core.lang.IString;
import cn.sunline.edsp.base.lang.Params;

public class CorePluginErrorDef
{
    public static class SP_CP
    {

        public static String E001_ERROR(Integer count)
        {
            Params context = (new Params()).add("count", count);
            String message = (new IString(E001, context)).getString();
            return message;
        }

        public static AdpBusinessException E001(Integer count)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("count", count);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E001", new IString(E001, context));
        }

        public static AdpBusinessException E001(Integer count, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("count", count);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E001", new IString(E001, context), t);
        }

        public static String E022_ERROR()
        {
            return E022.getString();
        }

        public static AdpBusinessException E022()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E022", new IString(E022, null));
        }

        public static AdpBusinessException E022(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E022", new IString(E022, null), t);
        }

        public static String E023_ERROR(String sequenceId)
        {
            Params context = (new Params()).add("sequenceId", sequenceId);
            String message = (new IString(E023, context)).getString();
            return message;
        }

        public static AdpBusinessException E023(String sequenceId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("sequenceId", sequenceId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E023", new IString(E023, context));
        }

        public static AdpBusinessException E023(String sequenceId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("sequenceId", sequenceId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E023", new IString(E023, context), t);
        }

        public static String E024_ERROR()
        {
            return E024.getString();
        }

        public static AdpBusinessException E024()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E024", new IString(E024, null));
        }

        public static AdpBusinessException E024(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E024", new IString(E024, null), t);
        }

        public static String E025_ERROR()
        {
            return E025.getString();
        }

        public static AdpBusinessException E025()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E025", new IString(E025, null));
        }

        public static AdpBusinessException E025(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E025", new IString(E025, null), t);
        }

        public static String E026_ERROR(String name)
        {
            Params context = (new Params()).add("name", name);
            String message = (new IString(E026, context)).getString();
            return message;
        }

        public static AdpBusinessException E026(String name)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("name", name);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E026", new IString(E026, context));
        }

        public static AdpBusinessException E026(String name, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("name", name);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E026", new IString(E026, context), t);
        }

        public static String E027_ERROR(String errorMessage, String errmessage)
        {
            Params context = (new Params()).add("errorMessage", errorMessage).add("errmessage", errmessage);
            String message = (new IString(E027, context)).getString();
            return message;
        }

        public static AdpBusinessException E027(String errorMessage, String errmessage)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("errorMessage", errorMessage).add("errmessage", errmessage);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E027", new IString(E027, context));
        }

        public static AdpBusinessException E027(String errorMessage, String errmessage, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("errorMessage", errorMessage).add("errmessage", errmessage);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E027", new IString(E027, context), t);
        }

        public static String E028_ERROR(String bindid, String Fwlxid)
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxid", Fwlxid);
            String message = (new IString(E028, context)).getString();
            return message;
        }

        public static AdpBusinessException E028(String bindid, String Fwlxid)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxid", Fwlxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E028", new IString(E028, context));
        }

        public static AdpBusinessException E028(String bindid, String Fwlxid, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxid", Fwlxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E028", new IString(E028, context), t);
        }

        public static String E029_ERROR(String bindid, String Fwlxsxid)
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            String message = (new IString(E029, context)).getString();
            return message;
        }

        public static AdpBusinessException E029(String bindid, String Fwlxsxid)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E029", new IString(E029, context));
        }

        public static AdpBusinessException E029(String bindid, String Fwlxsxid, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E029", new IString(E029, context), t);
        }

        public static String E030_ERROR(String bindid, String Fwlxsxid)
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            String message = (new IString(E030, context)).getString();
            return message;
        }

        public static AdpBusinessException E030(String bindid, String Fwlxsxid)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E030", new IString(E030, context));
        }

        public static AdpBusinessException E030(String bindid, String Fwlxsxid, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("Fwlxsxid", Fwlxsxid);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E030", new IString(E030, context), t);
        }

        public static String E031_ERROR()
        {
            return E031.getString();
        }

        public static AdpBusinessException E031()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E031", new IString(E031, null));
        }

        public static AdpBusinessException E031(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E031", new IString(E031, null), t);
        }

        public static String E032_ERROR(String serverIp, Integer port)
        {
            Params context = (new Params()).add("serverIp", serverIp).add("port", port);
            String message = (new IString(E032, context)).getString();
            return message;
        }

        public static AdpBusinessException E032(String serverIp, Integer port)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("serverIp", serverIp).add("port", port);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E032", new IString(E032, context));
        }

        public static AdpBusinessException E032(String serverIp, Integer port, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("serverIp", serverIp).add("port", port);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E032", new IString(E032, context), t);
        }

        public static String E033_ERROR(String svcFullId, String svcClazz)
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcClazz", svcClazz);
            String message = (new IString(E033, context)).getString();
            return message;
        }

        public static AdpBusinessException E033(String svcFullId, String svcClazz)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcClazz", svcClazz);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E033", new IString(E033, context));
        }

        public static AdpBusinessException E033(String svcFullId, String svcClazz, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcClazz", svcClazz);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E033", new IString(E033, context), t);
        }

        public static String E034_ERROR(String svcFullId, String svcOwnerId)
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcOwnerId", svcOwnerId);
            String message = (new IString(E034, context)).getString();
            return message;
        }

        public static AdpBusinessException E034(String svcFullId, String svcOwnerId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcOwnerId", svcOwnerId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E034", new IString(E034, context));
        }

        public static AdpBusinessException E034(String svcFullId, String svcOwnerId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId).add("svcOwnerId", svcOwnerId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E034", new IString(E034, context), t);
        }

        public static String E035_ERROR(String svcFullId)
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            String message = (new IString(E035, context)).getString();
            return message;
        }

        public static AdpBusinessException E035(String svcFullId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E035", new IString(E035, context));
        }

        public static AdpBusinessException E035(String svcFullId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E035", new IString(E035, context), t);
        }

        public static String E036_ERROR(String simpleName)
        {
            Params context = (new Params()).add("simpleName", simpleName);
            String message = (new IString(E036, context)).getString();
            return message;
        }

        public static AdpBusinessException E036(String simpleName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E036", new IString(E036, context));
        }

        public static AdpBusinessException E036(String simpleName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E036", new IString(E036, context), t);
        }

        public static String E037_ERROR(String simpleName)
        {
            Params context = (new Params()).add("simpleName", simpleName);
            String message = (new IString(E037, context)).getString();
            return message;
        }

        public static AdpBusinessException E037(String simpleName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E037", new IString(E037, context));
        }

        public static AdpBusinessException E037(String simpleName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E037", new IString(E037, context), t);
        }

        public static String E038_ERROR()
        {
            return E038.getString();
        }

        public static AdpBusinessException E038()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E038", new IString(E038, null));
        }

        public static AdpBusinessException E038(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E038", new IString(E038, null), t);
        }

        public static String E039_ERROR(String svcFullId)
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            String message = (new IString(E039, context)).getString();
            return message;
        }

        public static AdpBusinessException E039(String svcFullId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E039", new IString(E039, context));
        }

        public static AdpBusinessException E039(String svcFullId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E039", new IString(E039, context), t);
        }

        public static String E040_ERROR(String svcFullId)
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            String message = (new IString(E040, context)).getString();
            return message;
        }

        public static AdpBusinessException E040(String svcFullId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E040", new IString(E040, context));
        }

        public static AdpBusinessException E040(String svcFullId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E040", new IString(E040, context), t);
        }

        public static String E041_ERROR(String bindid, String servicetypeid, String svcid, String svcOwnerId)
        {
            Params context = (new Params()).add("bindid", bindid).add("servicetypeid", servicetypeid).add("svcid", svcid).add("svcOwnerId", svcOwnerId);
            String message = (new IString(E041, context)).getString();
            return message;
        }

        public static AdpBusinessException E041(String bindid, String servicetypeid, String svcid, String svcOwnerId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("servicetypeid", servicetypeid).add("svcid", svcid).add("svcOwnerId", svcOwnerId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E041", new IString(E041, context));
        }

        public static AdpBusinessException E041(String bindid, String servicetypeid, String svcid, String svcOwnerId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("bindid", bindid).add("servicetypeid", servicetypeid).add("svcid", svcid).add("svcOwnerId", svcOwnerId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E041", new IString(E041, context), t);
        }

        public static String E042_ERROR(String simpleName, String BizServiceController)
        {
            Params context = (new Params()).add("simpleName", simpleName).add("BizServiceController", BizServiceController);
            String message = (new IString(E042, context)).getString();
            return message;
        }

        public static AdpBusinessException E042(String simpleName, String BizServiceController)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName).add("BizServiceController", BizServiceController);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E042", new IString(E042, context));
        }

        public static AdpBusinessException E042(String simpleName, String BizServiceController, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("simpleName", simpleName).add("BizServiceController", BizServiceController);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E042", new IString(E042, context), t);
        }

        public static String E043_ERROR(String CanonicalName)
        {
            Params context = (new Params()).add("CanonicalName", CanonicalName);
            String message = (new IString(E043, context)).getString();
            return message;
        }

        public static AdpBusinessException E043(String CanonicalName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("CanonicalName", CanonicalName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E043", new IString(E043, context));
        }

        public static AdpBusinessException E043(String CanonicalName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("CanonicalName", CanonicalName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E043", new IString(E043, context), t);
        }

        public static String E044_ERROR(String svcImplClassStr)
        {
            Params context = (new Params()).add("svcImplClassStr", svcImplClassStr);
            String message = (new IString(E044, context)).getString();
            return message;
        }

        public static AdpBusinessException E044(String svcImplClassStr)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcImplClassStr", svcImplClassStr);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E044", new IString(E044, context));
        }

        public static AdpBusinessException E044(String svcImplClassStr, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcImplClassStr", svcImplClassStr);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E044", new IString(E044, context), t);
        }

        public static String E045_ERROR(String executorId)
        {
            Params context = (new Params()).add("executorId", executorId);
            String message = (new IString(E045, context)).getString();
            return message;
        }

        public static AdpBusinessException E045(String executorId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("executorId", executorId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E045", new IString(E045, context));
        }

        public static AdpBusinessException E045(String executorId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("executorId", executorId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E045", new IString(E045, context), t);
        }

        public static String E046_ERROR(String LocalServiceTypeExecutor)
        {
            Params context = (new Params()).add("LocalServiceTypeExecutor", LocalServiceTypeExecutor);
            String message = (new IString(E046, context)).getString();
            return message;
        }

        public static AdpBusinessException E046(String LocalServiceTypeExecutor)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("LocalServiceTypeExecutor", LocalServiceTypeExecutor);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E046", new IString(E046, context));
        }

        public static AdpBusinessException E046(String LocalServiceTypeExecutor, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("LocalServiceTypeExecutor", LocalServiceTypeExecutor);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E046", new IString(E046, context), t);
        }

        public static String E047_ERROR(String implId, String ServiceTypeimpl)
        {
            Params context = (new Params()).add("implId", implId).add("ServiceTypeimpl", ServiceTypeimpl);
            String message = (new IString(E047, context)).getString();
            return message;
        }

        public static AdpBusinessException E047(String implId, String ServiceTypeimpl)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("implId", implId).add("ServiceTypeimpl", ServiceTypeimpl);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E047", new IString(E047, context));
        }

        public static AdpBusinessException E047(String implId, String ServiceTypeimpl, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("implId", implId).add("ServiceTypeimpl", ServiceTypeimpl);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E047", new IString(E047, context), t);
        }

        public static String E048_ERROR()
        {
            return E048.getString();
        }

        public static AdpBusinessException E048()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E048", new IString(E048, null));
        }

        public static AdpBusinessException E048(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E048", new IString(E048, null), t);
        }

        public static String E049_ERROR(String svcTypeClass)
        {
            Params context = (new Params()).add("svcTypeClass", svcTypeClass);
            String message = (new IString(E049, context)).getString();
            return message;
        }

        public static AdpBusinessException E049(String svcTypeClass)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcTypeClass", svcTypeClass);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E049", new IString(E049, context));
        }

        public static AdpBusinessException E049(String svcTypeClass, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcTypeClass", svcTypeClass);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E049", new IString(E049, context), t);
        }

        public static String E050_ERROR(String svcFullId)
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            String message = (new IString(E050, context)).getString();
            return message;
        }

        public static AdpBusinessException E050(String svcFullId)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E050", new IString(E050, context));
        }

        public static AdpBusinessException E050(String svcFullId, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("svcFullId", svcFullId);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E050", new IString(E050, context), t);
        }

        public static String E051_ERROR()
        {
            return E051.getString();
        }

        public static AdpBusinessException E051()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E051", new IString(E051, null));
        }

        public static AdpBusinessException E051(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E051", new IString(E051, null), t);
        }

        public static String E052_ERROR()
        {
            return E052.getString();
        }

        public static AdpBusinessException E052()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E052", new IString(E052, null));
        }

        public static AdpBusinessException E052(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E052", new IString(E052, null), t);
        }

        public static String E053_ERROR()
        {
            return E053.getString();
        }

        public static AdpBusinessException E053()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E053", new IString(E053, null));
        }

        public static AdpBusinessException E053(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E053", new IString(E053, null), t);
        }

        public static String E054_ERROR(String callback)
        {
            Params context = (new Params()).add("callback", callback);
            String message = (new IString(E054, context)).getString();
            return message;
        }

        public static AdpBusinessException E054(String callback)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("callback", callback);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E054", new IString(E054, context));
        }

        public static AdpBusinessException E054(String callback, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("callback", callback);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E054", new IString(E054, context), t);
        }

        public static String E055_ERROR(String tableName)
        {
            Params context = (new Params()).add("tableName", tableName);
            String message = (new IString(E055, context)).getString();
            return message;
        }

        public static AdpBusinessException E055(String tableName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E055", new IString(E055, context));
        }

        public static AdpBusinessException E055(String tableName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E055", new IString(E055, context), t);
        }

        public static String E056_ERROR(String tableName)
        {
            Params context = (new Params()).add("tableName", tableName);
            String message = (new IString(E056, context)).getString();
            return message;
        }

        public static AdpBusinessException E056(String tableName)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E056", new IString(E056, context));
        }

        public static AdpBusinessException E056(String tableName, Throwable t)
            throws AdpBusinessException
        {
            Params context = (new Params()).add("tableName", tableName);
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E056", new IString(E056, context), t);
        }

        public static String E057_ERROR()
        {
            return E057.getString();
        }

        public static AdpBusinessException E057()
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E057", new IString(E057, null));
        }

        public static AdpBusinessException E057(Throwable t)
            throws AdpBusinessException
        {
            throw new AdpBusinessException(ErrorType.ERROR, "SP_CP.E057", new IString(E057, null), t);
        }

        public static final String F_E001 = "SP_CP.E001";
        private static final IString E001 = new IString("CorePluginErrorDef.SP_CP.E001", "\u53C2\u6570\u9519\u8BEF,count\u5FC5\u987B\u5927\u4E8E0, pageSize=${count}");
        public static final String F_E022 = "SP_CP.E022";
        private static final IString E022 = new IString("CorePluginErrorDef.SP_CP.E022", "\u521B\u5EFA\u9ED8\u8BA4\u7684\u6D41\u6C34\u5B9A\u4E49\u5931\u8D25");
        public static final String F_E023 = "SP_CP.E023";
        private static final IString E023 = new IString("CorePluginErrorDef.SP_CP.E023", "\u66F4\u65B0\u6D41\u6C34[${sequenceId}]\u5F53\u524D\u503C\u5931\u8D25\u3002 \u66F4\u65B0\u5230\u7684\u8BB0\u5F55\u6570\u4E3A0");
        public static final String F_E024 = "SP_CP.E024";
        private static final IString E024 = new IString("CorePluginErrorDef.SP_CP.E024", "\u672B\u627E\u5230\u914D\u7F6E\uFF0C\u65E5\u5FD7\u914D\u7F6E\u7BA1\u7406\u5668\u5C06\u4E0D\u4F1A\u542F\u52A8");
        public static final String F_E025 = "SP_CP.E025";
        private static final IString E025 = new IString("CorePluginErrorDef.SP_CP.E025", "\u521D\u59CB\u5316\u65E5\u5FD7\u914D\u7F6E\u7BA1\u7406\u5668\u5931\u8D25!");
        public static final String F_E026 = "SP_CP.E026";
        private static final IString E026 = new IString("CorePluginErrorDef.SP_CP.E026", "\u6EE1\u8DB3\u67E5\u8BE2\u6761\u4EF6\u7684\u8BB0\u5F55\u4E0D\u6B62\u4E00\u6761:${name}");
        public static final String F_E027 = "SP_CP.E027";
        private static final IString E027 = new IString("CorePluginErrorDef.SP_CP.E027", "\u89E3\u6790\u9519\u8BEF\u6D88\u606F[${errorMessage}]\u53C2\u6570\u9519\u8BEF, cause by: ${errmessage}");
        public static final String F_E028 = "SP_CP.E028";
        private static final IString E028 = new IString("CorePluginErrorDef.SP_CP.E028", "ksys_fwbdxx[${bindid}]\u914D\u7F6E\u7684\u670D\u52A1\u7C7B\u578B[${Fwlxid}]\u4E0D\u5B58\u5728\uFF01");
        public static final String F_E029 = "SP_CP.E029";
        private static final IString E029 = new IString("CorePluginErrorDef.SP_CP.E029", "ksys_fwbdxx[${bindid}]\u914D\u7F6E\u7684\u670D\u52A1\u7C7B\u578B\u5B9E\u73B0[${Fwlxsxid}]\u4E0D\u5B58\u5728\uFF01");
        public static final String F_E030 = "SP_CP.E030";
        private static final IString E030 = new IString("CorePluginErrorDef.SP_CP.E030", "ksys_fwbdxx[${bindid}]\u914D\u7F6E\u7684\u6267\u884C\u5668[${Fwlxsxid}]\u4E0D\u5B58\u5728\uFF01");
        public static final String F_E031 = "SP_CP.E031";
        private static final IString E031 = new IString("CorePluginErrorDef.SP_CP.E031", "\u521B\u5EFAMBean\u8FDE\u63A5\u5931\u8D25\uFF01");
        public static final String F_E032 = "SP_CP.E032";
        private static final IString E032 = new IString("CorePluginErrorDef.SP_CP.E032", "\u542F\u52A8JMX\u6D88\u606F\u63A5\u6536\u5668[${serverIp}:${port}]\u5931\u8D25");
        public static final String F_E033 = "SP_CP.E033";
        private static final IString E033 = new IString("CorePluginErrorDef.SP_CP.E033", "\u670D\u52A1[${svcFullId}]\u6570\u636E\u63A5\u53E3JavaBean\u7C7B${svcClazz}\u672A\u5B9E\u73B0");
        public static final String F_E034 = "SP_CP.E034";
        private static final IString E034 = new IString("CorePluginErrorDef.SP_CP.E034", "\u670D\u52A1[${svcFullId}]\u6240\u5C5E\u670D\u52A1\u7C7B\u578B[${svcOwnerId}]\u4E0D\u5B58\u5728");
        public static final String F_E035 = "SP_CP.E035";
        private static final IString E035 = new IString("CorePluginErrorDef.SP_CP.E035", "\u670D\u52A1[${svcFullId}]\u5BF9\u5E94\u5B9E\u73B0\u7C7B\u672A\u5B9A\u4E49");
        public static final String F_E036 = "SP_CP.E036";
        private static final IString E036 = new IString("CorePluginErrorDef.SP_CP.E036", "\u670D\u52A1\u7C7B\u578B\u6267\u884C\u5668[${simpleName}]\u521D\u59CB\u5316\u670D\u52A1\u53C2\u6570\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E037 = "SP_CP.E037";
        private static final IString E037 = new IString("CorePluginErrorDef.SP_CP.E037", "\u670D\u52A1\u7C7B\u578B\u6267\u884C\u5668[${simpleName}]\u521D\u59CB\u5316\u7ED1\u5B9A\u53C2\u6570\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E038 = "SP_CP.E038";
        private static final IString E038 = new IString("CorePluginErrorDef.SP_CP.E038", "\u670D\u52A1\u7C7B\u578B\u6267\u884C\u5668\u4E0E\u670D\u52A1\u7ED1\u5B9A\u5FC5\u987B\u662F\u4E00\u5BF9\u4E00\u5173\u7CFB");
        public static final String F_E039 = "SP_CP.E039";
        private static final IString E039 = new IString("CorePluginErrorDef.SP_CP.E039", "\u670D\u52A1[${svcFullId}]\u6CA1\u6709\u5BF9\u5E94\u7684\u5B9E\u73B0");
        public static final String F_E040 = "SP_CP.E040";
        private static final IString E040 = new IString("CorePluginErrorDef.SP_CP.E040", "\u670D\u52A1[${svcFullId}]\u5BF9\u5E94\u7684\u5B9E\u73B0\u6709\u591A\u4E2A");
        public static final String F_E041 = "SP_CP.E041";
        private static final IString E041 = new IString("CorePluginErrorDef.SP_CP.E041", "\u7ED1\u5B9A[${bindid}]\u4E2D\u7684\u670D\u52A1\u7C7B\u578B[${servicetypeid}]\u4E0E\u670D\u52A1[${svcid}]\u6240\u5C5E\u7684\u670D\u52A1\u7C7B\u578B[${svcOwnerId}]\u4E0D\u4E00\u81F4");
        public static final String F_E042 = "SP_CP.E042";
        private static final IString E042 = new IString("CorePluginErrorDef.SP_CP.E042", "\u670D\u52A1\u7C7B\u578B\u6267\u884C\u5668[${simpleName}]\u7684\u670D\u52A1\u63A7\u5236\u5668[${BizServiceController}]\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E043 = "SP_CP.E043";
        private static final IString E043 = new IString("CorePluginErrorDef.SP_CP.E043", "\u670D\u52A1\u7C7B\u578B\u5B9E\u73B0\u5BF9\u8C61\u7C7B\u578B\u4E0D\u4E00\u81F4[${CanonicalName}]");
        public static final String F_E044 = "SP_CP.E044";
        private static final IString E044 = new IString("CorePluginErrorDef.SP_CP.E044", "\u670D\u52A1\u7C7B\u578B\u5B9E\u73B0\u7C7B[${svcImplClassStr}]\u4E0D\u5B58\u5728");
        public static final String F_E045 = "SP_CP.E045";
        private static final IString E045 = new IString("CorePluginErrorDef.SP_CP.E045", "\u6267\u884C\u5668\u6269\u5C55[${executorId}]\u4E0D\u5B58\u5728\uFF01");
        public static final String F_E046 = "SP_CP.E046";
        private static final IString E046 = new IString("CorePluginErrorDef.SP_CP.E046", "\u672C\u5730\u6267\u884C\u5668\u6269\u5C55[${LocalServiceTypeExecutor}]\u4E0D\u5B58\u5728\uFF01");
        public static final String F_E047 = "SP_CP.E047";
        private static final IString E047 = new IString("CorePluginErrorDef.SP_CP.E047", "\u670D\u52A1\u7C7B\u578B\u5B9E\u73B0[${implId}]\uFF0C\u5BF9\u5E94\u670D\u52A1\u7C7B\u578B[${ServiceTypeimpl}]\u4E0D\u5B58\u5728");
        public static final String F_E048 = "SP_CP.E048";
        private static final IString E048 = new IString("CorePluginErrorDef.SP_CP.E048", "\u670D\u52A1\u5BF9\u8C61\u4E0D\u80FD\u4E3A\u7A7A");
        public static final String F_E049 = "SP_CP.E049";
        private static final IString E049 = new IString("CorePluginErrorDef.SP_CP.E049", "\u63A5\u53E3[${svcTypeClass}]\u65E0\u5BF9\u5E94\u7684\u670D\u52A1\u7C7B\u578B\u5B9A\u4E49");
        public static final String F_E050 = "SP_CP.E050";
        private static final IString E050 = new IString("CorePluginErrorDef.SP_CP.E050", "\u670D\u52A1[${svcFullId}]\u8F93\u5165\u53C2\u6570\u4E2A\u6570\u4E0E\u63A5\u53E3\u5B9A\u4E49\u4E0D\u4E00\u81F4");
        public static final String F_E051 = "SP_CP.E051";
        private static final IString E051 = new IString("CorePluginErrorDef.SP_CP.E051", "\u83B7\u53D6\u670D\u52A1\u8FD4\u56DE\u7C7B\u578B\u5931\u8D25");
        public static final String F_E052 = "SP_CP.E052";
        private static final IString E052 = new IString("CorePluginErrorDef.SP_CP.E052", "\u8F93\u51FA\u63A5\u53E3\u4F5C\u4E3A\u53C2\u6570\u4E14\u914D\u7F6E\u4E0D\u751F\u6210\u7C7B\u65F6\uFF0C\u5143\u7D20\u5FC5\u987B\u4E3A\u590D\u5408\u7C7B\u578B!");
        public static final String F_E053 = "SP_CP.E053";
        private static final IString E053 = new IString("CorePluginErrorDef.SP_CP.E053", "\u672A\u5728settings\u6587\u4EF6\u4E2D\u914D\u7F6E\u7CFB\u7EDF\u53C2\u6570,\u65E0\u6CD5\u83B7\u53D6\u83B7\u53D6\u5BF9\u5E94\u7684\u7CFB\u7EDF\u53C2\u6570!");
        public static final String F_E054 = "SP_CP.E054";
        private static final IString E054 = new IString("CorePluginErrorDef.SP_CP.E054", "NamedServiceDiscovery\u5B9E\u73B0\u7C7B[${callback}]\u65E0\u6CD5\u52A0\u8F7D\uFF0C\u8BF7\u68C0\u67E5\u7C7B\u8DEF\u5F84\u6216\u8BBF\u95EE\u6743\u9650\u662F\u5426\u914D\u7F6E\u6B63\u786E\u3002");
        public static final String F_E055 = "SP_CP.E055";
        private static final IString E055 = new IString("CorePluginErrorDef.SP_CP.E055", "\u7C7B[${tableName}]\u4E0D\u662F\u8868\u6A21\u578B\u5BF9\u5E94\u7684JavaBean");
        public static final String F_E056 = "SP_CP.E056";
        private static final IString E056 = new IString("CorePluginErrorDef.SP_CP.E056", "\u83B7\u53D6\u7C7B[${tableName}]\u7684BeanInfo\u5931\u8D25\uFF0C\u4E0D\u652F\u6301\u7684\u7C7B\u578B");
        public static final String F_E057 = "SP_CP.E057";
        private static final IString E057 = new IString("CorePluginErrorDef.SP_CP.E057", "\u63D2\u4EF6[{}]\u7684activator\u5FC5\u987B\u662F[{}]\u7684\u5B50\u7C7B");


        public SP_CP()
        {
        }
    }


    public CorePluginErrorDef()
    {
    }
}
