// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KsDict.java

package cn.sunline.adp.cedar.base.dict;

import cn.sunline.adp.core.lang.IString;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.edsp.base.lang.Symbol;

public interface KsDict
{
    public static final class DbDict extends Enum
        implements Symbol
    {

        public static DbDict[] values()
        {
            return (DbDict[])$VALUES.clone();
        }

        public static DbDict valueOf(String name)
        {
            return (DbDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$DbDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final DbDict sql_timeout;
        public static final DbDict lock_wait_time;
        public static final DbDict sql_retry_freq;
        public static final DbDict sql_retry_interval;
        public static final DbDict is_param_table;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final DbDict $VALUES[];

        static 
        {
            sql_timeout = new DbDict("sql_timeout", 0, "sql_timeout", "sql\u8D85\u65F6\u65F6\u95F4", "", 19, 0);
            lock_wait_time = new DbDict("lock_wait_time", 1, "lock_wait_time", "\u9501\u7B49\u5F85\u65F6\u95F4", "", 19, 0);
            sql_retry_freq = new DbDict("sql_retry_freq", 2, "sql_retry_freq", "sql\u91CD\u8BD5\u6B21\u6570", "", 19, 0);
            sql_retry_interval = new DbDict("sql_retry_interval", 3, "sql_retry_interval", "sql\u91CD\u8BD5\u95F4\u9694", "", 19, 0);
            is_param_table = new DbDict("is_param_table", 4, "is_param_table", "\u662F\u5426\u53C2\u6570\u8868", "", 1, 0);
            $VALUES = (new DbDict[] {
                sql_timeout, lock_wait_time, sql_retry_freq, sql_retry_interval, is_param_table
            });
        }

        private DbDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.DbDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class SqDict extends Enum
        implements Symbol
    {

        public static SqDict[] values()
        {
            return (SqDict[])$VALUES.clone();
        }

        public static SqDict valueOf(String name)
        {
            return (SqDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$SqDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final SqDict serial_code;
        public static final SqDict serial_name;
        public static final SqDict cache_size;
        public static final SqDict step_value;
        public static final SqDict max_serial_value;
        public static final SqDict min_serial_value;
        public static final SqDict current_serial_value;
        public static final SqDict serial_register_status;
        public static final SqDict serial_register_tablenum;
        public static final SqDict reset_mode;
        public static final SqDict update_time;
        public static final SqDict serial_type;
        public static final SqDict instance_code;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final SqDict $VALUES[];

        static 
        {
            serial_code = new SqDict("serial_code", 0, "serial_code", "\u6D41\u6C34\u7F16\u7801", "", 30, 0);
            serial_name = new SqDict("serial_name", 1, "serial_name", "\u6D41\u6C34\u540D\u79F0", "", 200, 0);
            cache_size = new SqDict("cache_size", 2, "cache_size", "\u7F13\u5B58\u5927\u5C0F", "", 8, 0);
            step_value = new SqDict("step_value", 3, "step_value", "\u6B65\u957F", "", 8, 0);
            max_serial_value = new SqDict("max_serial_value", 4, "max_serial_value", "\u6D41\u6C34\u6700\u5927\u503C", "", 19, 0);
            min_serial_value = new SqDict("min_serial_value", 5, "min_serial_value", "\u6D41\u6C34\u6700\u5C0F\u503C", "", 19, 0);
            current_serial_value = new SqDict("current_serial_value", 6, "current_serial_value", "\u6D41\u6C34\u5F53\u524D\u503C", "", 19, 0);
            serial_register_status = new SqDict("serial_register_status", 7, "serial_register_status", "\u6D41\u6C34\u6CE8\u518C\u72B6\u6001", "", 10, 0);
            serial_register_tablenum = new SqDict("serial_register_tablenum", 8, "serial_register_tablenum", "\u6D41\u6C34\u6CE8\u518C\u8868\u5E8F\u53F7", "", 6, 0);
            reset_mode = new SqDict("reset_mode", 9, "reset_mode", "\u6D41\u6C34\u590D\u4F4D\u7C7B\u578B", "", 30, 0);
            update_time = new SqDict("update_time", 10, "update_time", "\u66F4\u65B0\u65F6\u95F4", "", 0, 0);
            serial_type = new SqDict("serial_type", 11, "serial_type", "\u6D41\u6C34\u7C7B\u578B", "", 30, 0);
            instance_code = new SqDict("instance_code", 12, "instance_code", "\u5E94\u7528\u5B9E\u4F8B\u6807\u8BC6", "", 60, 0);
            $VALUES = (new SqDict[] {
                serial_code, serial_name, cache_size, step_value, max_serial_value, min_serial_value, current_serial_value, serial_register_status, serial_register_tablenum, reset_mode, 
                update_time, serial_type, instance_code
            });
        }

        private SqDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.SqDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class BiDict extends Enum
        implements Symbol
    {

        public static BiDict[] values()
        {
            return (BiDict[])$VALUES.clone();
        }

        public static BiDict valueOf(String name)
        {
            return (BiDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$BiDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final BiDict qudaohao;
        public static final BiDict jiaoyirq;
        public static final BiDict xckjriqi;
        public static final BiDict xcjioyrq;
        public static final BiDict sckjriqi;
        public static final BiDict jiaoyijg;
        public static final BiDict jiaoyigy;
        public static final BiDict jiaoyima;
        public static final BiDict waibclma;
        public static final BiDict guiydaih;
        public static final BiDict guiymima;
        public static final BiDict guiyxinm;
        public static final BiDict guiyshux;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final BiDict $VALUES[];

        static 
        {
            qudaohao = new BiDict("qudaohao", 0, "qudaohao", "\u6E20\u9053", "", 3, 0);
            jiaoyirq = new BiDict("jiaoyirq", 1, "jiaoyirq", "\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            xckjriqi = new BiDict("xckjriqi", 2, "xckjriqi", "\u4E0B\u6B21\u4F1A\u8BA1\u65E5\u671F", "", 8, 0);
            xcjioyrq = new BiDict("xcjioyrq", 3, "xcjioyrq", "\u4E0B\u6B21\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            sckjriqi = new BiDict("sckjriqi", 4, "sckjriqi", "\u4E0A\u6B21\u4F1A\u8BA1\u65E5\u671F", "", 8, 0);
            jiaoyijg = new BiDict("jiaoyijg", 5, "jiaoyijg", "\u4EA4\u6613\u673A\u6784", "", 10, 0);
            jiaoyigy = new BiDict("jiaoyigy", 6, "jiaoyigy", "\u4EA4\u6613\u67DC\u5458", "", 8, 0);
            jiaoyima = new BiDict("jiaoyima", 7, "jiaoyima", "\u4EA4\u6613\u7801", "", 10, 0);
            waibclma = new BiDict("waibclma", 8, "waibclma", "\u7B2C\u4E09\u65B9\u4EA4\u6613\u7801", "", 10, 0);
            guiydaih = new BiDict("guiydaih", 9, "guiydaih", "\u67DC\u5458\u4EE3\u53F7", "", 8, 0);
            guiymima = new BiDict("guiymima", 10, "guiymima", "\u5BC6\u7801", "", 16, 0);
            guiyxinm = new BiDict("guiyxinm", 11, "guiyxinm", "\u67DC\u5458\u59D3\u540D", "", 200, 0);
            guiyshux = new BiDict("guiyshux", 12, "guiyshux", "\u67DC\u5458\u5C5E\u6027", "", 1, 0);
            $VALUES = (new BiDict[] {
                qudaohao, jiaoyirq, xckjriqi, xcjioyrq, sckjriqi, jiaoyijg, jiaoyigy, jiaoyima, waibclma, guiydaih, 
                guiymima, guiyxinm, guiyshux
            });
        }

        private BiDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.BiDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class CpDict extends Enum
        implements Symbol
    {

        public static CpDict[] values()
        {
            return (CpDict[])$VALUES.clone();
        }

        public static CpDict valueOf(String name)
        {
            return (CpDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$CpDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final CpDict abstract_component_schema;
        public static final CpDict component_type;
        public static final CpDict component_impl_schema;
        public static final CpDict is_singleton;
        public static final CpDict is_sync_call;
        public static final CpDict description;
        public static final CpDict attribute;
        public static final CpDict attribute_value;
        public static final CpDict chinese_name;
        public static final CpDict abstract_component_instance;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final CpDict $VALUES[];

        static 
        {
            abstract_component_schema = new CpDict("abstract_component_schema", 0, "abstract_component_schema", "\u62BD\u8C61\u7EC4\u4EF6Schema\u8DEF\u5F84ID", "", 80, 0);
            component_type = new CpDict("component_type", 1, "component_type", "\u7EC4\u4EF6\u7C7B\u578B", "", 30, 0);
            component_impl_schema = new CpDict("component_impl_schema", 2, "component_impl_schema", "\u7EC4\u4EF6\u5B9E\u73B0Schema\u8DEF\u5F84ID", "", 60, 0);
            is_singleton = new CpDict("is_singleton", 3, "is_singleton", "\u662F\u5426\u5355\u4F8B 0-\u975E\u5355\u4F8B 1-\u5355\u4F8B", "", 1, 0);
            is_sync_call = new CpDict("is_sync_call", 4, "is_sync_call", "\u662F\u5426\u540C\u6B65\u8C03\u7528 0-\u5F02\u6B65\u8C03\u7528 1-\u540C\u6B65\u8C03\u7528 ", "", 1, 0);
            description = new CpDict("description", 5, "description", "\u63CF\u8FF0\u4FE1\u606F", "", 400, 0);
            attribute = new CpDict("attribute", 6, "attribute", "\u5C5E\u6027\u540D\u79F0", "", 32, 0);
            attribute_value = new CpDict("attribute_value", 7, "attribute_value", "\u5C5E\u6027\u503C", "", 100, 0);
            chinese_name = new CpDict("chinese_name", 8, "chinese_name", "\u4E2D\u6587\u540D\u79F0", "", 200, 0);
            abstract_component_instance = new CpDict("abstract_component_instance", 9, "abstract_component_instance", "\u62BD\u8C61\u7EC4\u4EF6\u5B9E\u4F8B", "", 80, 0);
            $VALUES = (new CpDict[] {
                abstract_component_schema, component_type, component_impl_schema, is_singleton, is_sync_call, description, attribute, attribute_value, chinese_name, abstract_component_instance
            });
        }

        private CpDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.CpDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class SoDict extends Enum
        implements Symbol
    {

        public static SoDict[] values()
        {
            return (SoDict[])$VALUES.clone();
        }

        public static SoDict valueOf(String name)
        {
            return (SoDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$SoDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final SoDict is_runable_tran;
        public static final SoDict register_mode;
        public static final SoDict timeout;
        public static final SoDict log_level;
        public static final SoDict table_name;
        public static final SoDict table_comment;
        public static final SoDict cache_level;
        public static final SoDict is_executed_preprocess;
        public static final SoDict is_registered_changed_log;
        public static final SoDict is_check_param;
        public static final SoDict is_process_param;
        public static final SoDict process_result;
        public static final SoDict param_code;
        public static final SoDict response_msg;
        public static final SoDict param_version;
        public static final SoDict timestamp;
        public static final SoDict main_version;
        public static final SoDict is_forced_refresh;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final SoDict $VALUES[];

        static 
        {
            is_runable_tran = new SoDict("is_runable_tran", 0, "is_runable_tran", "\u4EA4\u6613\u53EF\u8FD0\u884C\u6807\u8BC6", "", 1, 0);
            register_mode = new SoDict("register_mode", 1, "register_mode", "\u767B\u8BB0\u62A5\u6587\u6D41\u6C34\u6807\u8BC6 1-\u767B\u8BB0 0-\u4E0D\u767B\u8BB0", "", 1, 0);
            timeout = new SoDict("timeout", 2, "timeout", "\u8D85\u65F6\u65F6\u95F4 (\u5355\u4F4D:\u6BEB\u79D2) 0-\u4E0D\u63A7\u5236", "", 19, 0);
            log_level = new SoDict("log_level", 3, "log_level", "\u65E5\u5FD7\u7EA7\u522B", "", 8, 0);
            table_name = new SoDict("table_name", 4, "table_name", "\u8868\u540D", "", 30, 0);
            table_comment = new SoDict("table_comment", 5, "table_comment", "\u8868\u4E2D\u6587\u540D\u79F0", "", 200, 0);
            cache_level = new SoDict("cache_level", 6, "cache_level", "\u7F13\u5B58\u7EA7\u522B", "", 6, 0);
            is_executed_preprocess = new SoDict("is_executed_preprocess", 7, "is_executed_preprocess", "\u6267\u884C\u524D\u5904\u7406\u6807\u5FD7", "", 1, 0);
            is_registered_changed_log = new SoDict("is_registered_changed_log", 8, "is_registered_changed_log", "\u767B\u8BB0\u53D8\u66F4\u65E5\u5FD7\u6807\u5FD7", "", 1, 0);
            is_check_param = new SoDict("is_check_param", 9, "is_check_param", "\u68C0\u67E5\u53C2\u6570\u6807\u5FD7", "", 1, 0);
            is_process_param = new SoDict("is_process_param", 10, "is_process_param", "\u5904\u7406\u53C2\u6570\u6807\u5FD7", "", 1, 0);
            process_result = new SoDict("process_result", 11, "process_result", "\u5904\u7406\u7ED3\u679C\u6807\u5FD7", "", 1, 0);
            param_code = new SoDict("param_code", 12, "param_code", "\u53C2\u6570\u4EE3\u7801", "", 60, 0);
            response_msg = new SoDict("response_msg", 13, "response_msg", "\u8FD4\u56DE\u4FE1\u606F", "", 1000, 0);
            param_version = new SoDict("param_version", 14, "param_version", "\u53C2\u6570\u7248\u672C", "", 8, 0);
            timestamp = new SoDict("timestamp", 15, "timestamp", "\u65F6\u95F4\u6233", "", 0, 0);
            main_version = new SoDict("main_version", 16, "main_version", "\u603B\u7248\u672C", "", 8, 0);
            is_forced_refresh = new SoDict("is_forced_refresh", 17, "is_forced_refresh", "\u5F3A\u5236\u5237\u65B0", "", 1, 0);
            $VALUES = (new SoDict[] {
                is_runable_tran, register_mode, timeout, log_level, table_name, table_comment, cache_level, is_executed_preprocess, is_registered_changed_log, is_check_param, 
                is_process_param, process_result, param_code, response_msg, param_version, timestamp, main_version, is_forced_refresh
            });
        }

        private SoDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.SoDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class BtDict extends Enum
        implements Symbol
    {

        public static BtDict[] values()
        {
            return (BtDict[])$VALUES.clone();
        }

        public static BtDict valueOf(String name)
        {
            return (BtDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$BtDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final BtDict flow_step_name;
        public static final BtDict task_exe_mode;
        public static final BtDict task_commit_date;
        public static final BtDict task_exe_date;
        public static final BtDict transaction_date;
        public static final BtDict job_execution_mode;
        public static final BtDict task_run_mode;
        public static final BtDict task_num;
        public static final BtDict task_exe_num;
        public static final BtDict tran_exe_num;
        public static final BtDict task_priority;
        public static final BtDict task_commit_time;
        public static final BtDict tran_group_id;
        public static final BtDict tran_group_exe_time;
        public static final BtDict job_no;
        public static final BtDict tran_code;
        public static final BtDict tran_chinese_name;
        public static final BtDict tran_flow_id;
        public static final BtDict task_interrupt_flag;
        public static final BtDict task_run_conditions;
        public static final BtDict tran_run_conditions;
        public static final BtDict task_run_conditions_code;
        public static final BtDict task_run_condi_service_code;
        public static final BtDict task_run_conditions_param;
        public static final BtDict task_run_callback_service;
        public static final BtDict execution_code;
        public static final BtDict fail_interrupt_code;
        public static final BtDict data_split_mode;
        public static final BtDict rely_tran_goup_id;
        public static final BtDict rely_tran_list;
        public static final BtDict tran_run_time;
        public static final BtDict tran_start_time;
        public static final BtDict job_distribute_start_time;
        public static final BtDict tran_end_time;
        public static final BtDict tran_start_timestamp;
        public static final BtDict tran_end_timestamp;
        public static final BtDict data_area;
        public static final BtDict request_data_area;
        public static final BtDict response_data_area;
        public static final BtDict tran_state;
        public static final BtDict error_message;
        public static final BtDict message;
        public static final BtDict error_stack;
        public static final BtDict error_step;
        public static final BtDict tran_execution_domain_code;
        public static final BtDict concurrency_num;
        public static final BtDict mini_concurrency_num;
        public static final BtDict max_concurrency_num;
        public static final BtDict max_step_concurrency_num;
        public static final BtDict mini_step_concurrency_num;
        public static final BtDict mini_job_concurrency_num;
        public static final BtDict max_job_concurrency_num;
        public static final BtDict job_server_cluster;
        public static final BtDict exe_tran_flow_list;
        public static final BtDict exe_tran_group_list;
        public static final BtDict exe_domain_state;
        public static final BtDict desc_message;
        public static final BtDict package_serial;
        public static final BtDict schedule_name;
        public static final BtDict schedule_date;
        public static final BtDict schedule_time;
        public static final BtDict exe_class_name;
        public static final BtDict schedule_param;
        public static final BtDict schedule_start_date;
        public static final BtDict schedule_end_date;
        public static final BtDict schedule_year;
        public static final BtDict schedule_week;
        public static final BtDict schedule_month;
        public static final BtDict day_of_month;
        public static final BtDict schedule_hour;
        public static final BtDict schedule_minutes;
        public static final BtDict schedule_seconds;
        public static final BtDict automatic_delete_code;
        public static final BtDict is_register_log;
        public static final BtDict scheduling_status;
        public static final BtDict total_cost;
        public static final BtDict success_group_count;
        public static final BtDict failure_group_count;
        public static final BtDict success_count;
        public static final BtDict failure_count;
        public static final BtDict ignore_count;
        public static final BtDict job_excution_status;
        public static final BtDict tran_id;
        public static final BtDict step_id;
        public static final BtDict current_step;
        public static final BtDict current_flow_step_num;
        public static final BtDict current_tran_group_id;
        public static final BtDict current_execution_num;
        public static final BtDict start_step_num;
        public static final BtDict start_flow_step_num;
        public static final BtDict start_tran_group_id;
        public static final BtDict start_execution_no;
        public static final BtDict batch_data_num;
        public static final BtDict vm_id;
        public static final BtDict server_host_name;
        public static final BtDict ip_address;
        public static final BtDict thread_name;
        public static final BtDict wrong_data;
        public static final BtDict tran_group_desc;
        public static final BtDict reconnection_num;
        public static final BtDict transactions_submit_num;
        public static final BtDict data_split_key;
        public static final BtDict single_query_num;
        public static final BtDict job_split_condition;
        public static final BtDict log_level;
        public static final BtDict execution_no;
        public static final BtDict system_tran_date;
        public static final BtDict tran_date;
        public static final BtDict system_date;
        public static final BtDict chinese_name;
        public static final BtDict flow_step_num;
        public static final BtDict is_execution;
        public static final BtDict log_num;
        public static final BtDict run_info;
        public static final BtDict process_node;
        public static final BtDict query_date;
        public static final BtDict dayend_manage_date;
        public static final BtDict last_date;
        public static final BtDict before_last_date;
        public static final BtDict next_date;
        public static final BtDict file_info_key;
        public static final BtDict file_info_val;
        public static final BtDict batch_file_status;
        public static final BtDict busi_code;
        public static final BtDict busi_param_key;
        public static final BtDict busi_param_val;
        public static final BtDict dcn_num;
        public static final BtDict first_submit_sign;
        public static final BtDict job_server_code;
        public static final BtDict control_code;
        public static final BtDict control_value;
        public static final BtDict service_code;
        public static final BtDict tran_type;
        public static final BtDict is_skip;
        public static final BtDict group_num;
        public static final BtDict start_line;
        public static final BtDict end_line;
        public static final BtDict total_lines;
        public static final BtDict is_batch_file;
        public static final BtDict target_batch_group;
        public static final BtDict is_ignore;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final BtDict $VALUES[];

        static 
        {
            flow_step_name = new BtDict("flow_step_name", 0, "flow_step_name", "\u6D41\u7A0B\u6B65\u9AA4\u540D\u79F0", "", 200, 0);
            task_exe_mode = new BtDict("task_exe_mode", 1, "task_exe_mode", "\u6279\u91CF\u4EFB\u52A1\u6267\u884C\u6A21\u5F0F", "", 1, 0);
            task_commit_date = new BtDict("task_commit_date", 2, "task_commit_date", "\u6279\u91CF\u4EFB\u52A1\u63D0\u4EA4\u65E5\u671F", "", 23, 0);
            task_exe_date = new BtDict("task_exe_date", 3, "task_exe_date", "\u6279\u91CF\u4EFB\u52A1\u6267\u884C\u65E5\u671F", "", 23, 0);
            transaction_date = new BtDict("transaction_date", 4, "transaction_date", "\u5F53\u524D\u4EA4\u6613\u65E5\u671F", "", 23, 0);
            job_execution_mode = new BtDict("job_execution_mode", 5, "job_execution_mode", "\u6279\u91CF\u4F5C\u4E1A\u6267\u884C\u6A21\u5F0F", "", 1, 0);
            task_run_mode = new BtDict("task_run_mode", 6, "task_run_mode", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u6A21\u5F0F", "", 1, 0);
            task_num = new BtDict("task_num", 7, "task_num", "\u6279\u91CF\u4EFB\u52A1\u6279\u6B21\u53F7", "", 100, 0);
            task_exe_num = new BtDict("task_exe_num", 8, "task_exe_num", "\u6279\u91CF\u4EFB\u52A1\u6267\u884C\u6279\u6B21\u53F7", "", 100, 0);
            tran_exe_num = new BtDict("tran_exe_num", 9, "tran_exe_num", "\u6279\u91CF\u4EA4\u6613\u6267\u884C\u6279\u6B21\u53F7", "", 100, 0);
            task_priority = new BtDict("task_priority", 10, "task_priority", "\u6279\u91CF\u4EFB\u52A1\u4F18\u5148\u7EA7", "", 8, 0);
            task_commit_time = new BtDict("task_commit_time", 11, "task_commit_time", "\u6279\u91CF\u4EFB\u52A1\u63D0\u4EA4\u65F6\u95F4", "", 23, 0);
            tran_group_id = new BtDict("tran_group_id", 12, "tran_group_id", "\u6279\u91CF\u4EA4\u6613\u7EC4ID", "", 10, 0);
            tran_group_exe_time = new BtDict("tran_group_exe_time", 13, "tran_group_exe_time", "\u6279\u91CF\u4EA4\u6613\u7EC4\u6267\u884C\u8017\u65F6", "", 23, 0);
            job_no = new BtDict("job_no", 14, "job_no", "\u6279\u91CF\u4F5C\u4E1A\u7F16\u53F7", "", 100, 0);
            tran_code = new BtDict("tran_code", 15, "tran_code", "\u6279\u91CF\u4EA4\u6613\u7801", "", 10, 0);
            tran_chinese_name = new BtDict("tran_chinese_name", 16, "tran_chinese_name", "\u6279\u91CF\u4EA4\u6613\u4E2D\u6587\u540D\u79F0", "", 200, 0);
            tran_flow_id = new BtDict("tran_flow_id", 17, "tran_flow_id", "\u6279\u91CF\u4EA4\u6613\u6D41\u7A0BID", "", 30, 0);
            task_interrupt_flag = new BtDict("task_interrupt_flag", 18, "task_interrupt_flag", "\u6279\u91CF\u4EFB\u52A1\u4E2D\u65AD\u6807\u5FD7", "", 1, 0);
            task_run_conditions = new BtDict("task_run_conditions", 19, "task_run_conditions", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u8BB8\u53EF\u6761\u4EF6", "", 32, 0);
            tran_run_conditions = new BtDict("tran_run_conditions", 20, "tran_run_conditions", "\u6279\u91CF\u4EA4\u6613\u8FD0\u884C\u8BB8\u53EF\u6761\u4EF6", "", 32, 0);
            task_run_conditions_code = new BtDict("task_run_conditions_code", 21, "task_run_conditions_code", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u8BB8\u53EF\u6761\u4EF6\u6807\u8BC6", "", 32, 0);
            task_run_condi_service_code = new BtDict("task_run_condi_service_code", 22, "task_run_condi_service_code", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u8BB8\u53EF\u670D\u52A1\u5B9E\u73B0\u6807\u8BC6", "", 40, 0);
            task_run_conditions_param = new BtDict("task_run_conditions_param", 23, "task_run_conditions_param", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u8BB8\u53EF\u53C2\u6570", "", 1000, 0);
            task_run_callback_service = new BtDict("task_run_callback_service", 24, "task_run_callback_service", "\u6279\u91CF\u4EFB\u52A1\u8FD0\u884C\u56DE\u8C03\u670D\u52A1", "", 60, 0);
            execution_code = new BtDict("execution_code", 25, "execution_code", "\u6267\u884C\u6807\u8BC6", "", 1, 0);
            fail_interrupt_code = new BtDict("fail_interrupt_code", 26, "fail_interrupt_code", "\u5931\u8D25\u4E2D\u65AD\u6807\u8BC6", "", 1, 0);
            data_split_mode = new BtDict("data_split_mode", 27, "data_split_mode", "\u6570\u636E\u62C6\u5206\u6A21\u5F0F", "", 1, 0);
            rely_tran_goup_id = new BtDict("rely_tran_goup_id", 28, "rely_tran_goup_id", "\u4F9D\u8D56\u6279\u91CF\u4EA4\u6613\u7EC4ID", "", 10, 0);
            rely_tran_list = new BtDict("rely_tran_list", 29, "rely_tran_list", "\u4F9D\u8D56\u6279\u91CF\u4EA4\u6613\u5217\u8868", "", 80, 0);
            tran_run_time = new BtDict("tran_run_time", 30, "tran_run_time", "\u4EA4\u6613\u8FD0\u884C\u65F6\u95F4", "", 23, 0);
            tran_start_time = new BtDict("tran_start_time", 31, "tran_start_time", "\u4EA4\u6613\u5F00\u59CB\u65F6\u95F4", "", 23, 0);
            job_distribute_start_time = new BtDict("job_distribute_start_time", 32, "job_distribute_start_time", "\u4F5C\u4E1A\u5206\u53D1\u5F00\u59CB\u65F6\u95F4", "", 23, 0);
            tran_end_time = new BtDict("tran_end_time", 33, "tran_end_time", "\u4EA4\u6613\u7ED3\u675F\u65F6\u95F4", "", 23, 0);
            tran_start_timestamp = new BtDict("tran_start_timestamp", 34, "tran_start_timestamp", "\u4EA4\u6613\u5F00\u59CB\u65F6\u95F4\u6233", "", 19, 0);
            tran_end_timestamp = new BtDict("tran_end_timestamp", 35, "tran_end_timestamp", "\u4EA4\u6613\u7ED3\u675F\u65F6\u95F4\u6233", "", 19, 0);
            data_area = new BtDict("data_area", 36, "data_area", "\u6570\u636E\u533A", "", 2000, 0);
            request_data_area = new BtDict("request_data_area", 37, "request_data_area", "\u8BF7\u6C42\u6570\u636E\u533A", "", 2000, 0);
            response_data_area = new BtDict("response_data_area", 38, "response_data_area", "\u54CD\u5E94\u6570\u636E\u533A", "", 2000, 0);
            tran_state = new BtDict("tran_state", 39, "tran_state", "\u4EA4\u6613\u72B6\u6001", "", 15, 0);
            error_message = new BtDict("error_message", 40, "error_message", "\u9519\u8BEF\u4FE1\u606F", "", 1000, 0);
            message = new BtDict("message", 41, "message", "\u4FE1\u606F", "", 1000, 0);
            error_stack = new BtDict("error_stack", 42, "error_stack", "\u9519\u8BEF\u5806\u6808", "", 3000, 0);
            error_step = new BtDict("error_step", 43, "error_step", "\u9519\u8BEF\u6B65\u9AA4", "", 8, 0);
            tran_execution_domain_code = new BtDict("tran_execution_domain_code", 44, "tran_execution_domain_code", "\u6279\u91CF\u4EA4\u6613\u6267\u884C\u57DF\u6807\u8BC6", "", 20, 0);
            concurrency_num = new BtDict("concurrency_num", 45, "concurrency_num", "\u5E76\u53D1\u6570", "", 8, 0);
            mini_concurrency_num = new BtDict("mini_concurrency_num", 46, "mini_concurrency_num", "\u6700\u5C0F\u5E76\u53D1\u6570", "", 8, 0);
            max_concurrency_num = new BtDict("max_concurrency_num", 47, "max_concurrency_num", "\u6700\u5927\u5E76\u53D1\u6570", "", 8, 0);
            max_step_concurrency_num = new BtDict("max_step_concurrency_num", 48, "max_step_concurrency_num", "\u6700\u5927\u6B65\u9AA4\u5E76\u53D1\u6570", "", 8, 0);
            mini_step_concurrency_num = new BtDict("mini_step_concurrency_num", 49, "mini_step_concurrency_num", "\u6700\u5C0F\u6B65\u9AA4\u5E76\u53D1\u6570", "", 8, 0);
            mini_job_concurrency_num = new BtDict("mini_job_concurrency_num", 50, "mini_job_concurrency_num", "\u6700\u5C0F\u4F5C\u4E1A\u5E76\u53D1\u6570", "", 8, 0);
            max_job_concurrency_num = new BtDict("max_job_concurrency_num", 51, "max_job_concurrency_num", "\u6700\u5927\u4F5C\u4E1A\u5E76\u53D1\u6570", "", 8, 0);
            job_server_cluster = new BtDict("job_server_cluster", 52, "job_server_cluster", "\u5206\u5E03\u5F0F\u73AF\u5883\u4E0B,worker\u96C6\u7FA4\u5217\u8868,\u683C\u5F0Fip1:port1;ip2:port2", "", 500, 0);
            exe_tran_flow_list = new BtDict("exe_tran_flow_list", 53, "exe_tran_flow_list", "\u53EF\u6267\u884C\u6279\u91CF\u4EA4\u6613\u6D41\u7A0B\u5217\u8868", "", 500, 0);
            exe_tran_group_list = new BtDict("exe_tran_group_list", 54, "exe_tran_group_list", "\u53EF\u6267\u884C\u6279\u91CF\u4EA4\u6613\u7EC4\u5217\u8868", "", 500, 0);
            exe_domain_state = new BtDict("exe_domain_state", 55, "exe_domain_state", "\u6267\u884C\u57DF\u72B6\u6001", "", 10, 0);
            desc_message = new BtDict("desc_message", 56, "desc_message", "\u63CF\u8FF0\u4FE1\u606F", "", 1000, 0);
            package_serial = new BtDict("package_serial", 57, "package_serial", "\u5305\u6D41\u6C34", "", 30, 0);
            schedule_name = new BtDict("schedule_name", 58, "schedule_name", "\u8C03\u5EA6\u540D\u79F0", "", 200, 0);
            schedule_date = new BtDict("schedule_date", 59, "schedule_date", "\u8C03\u5EA6\u65E5\u671F", "", 8, 0);
            schedule_time = new BtDict("schedule_time", 60, "schedule_time", "\u8C03\u5EA6\u65F6\u95F4", "", 23, 0);
            exe_class_name = new BtDict("exe_class_name", 61, "exe_class_name", "\u6267\u884C\u7C7B\u540D", "", 80, 0);
            schedule_param = new BtDict("schedule_param", 62, "schedule_param", "\u8C03\u5EA6\u53C2\u6570", "", 1000, 0);
            schedule_start_date = new BtDict("schedule_start_date", 63, "schedule_start_date", "\u8C03\u5EA6\u5F00\u59CB\u65E5\u671F", "", 8, 0);
            schedule_end_date = new BtDict("schedule_end_date", 64, "schedule_end_date", "\u8C03\u5EA6\u7ED3\u675F\u65E5\u671F", "", 8, 0);
            schedule_year = new BtDict("schedule_year", 65, "schedule_year", "\u8C03\u5EA6\u5E74", "", 8, 0);
            schedule_week = new BtDict("schedule_week", 66, "schedule_week", "\u8C03\u5EA6\u661F\u671F\u51E0\uFF080-6\uFF09", "", 3, 0);
            schedule_month = new BtDict("schedule_month", 67, "schedule_month", "\u6708\u4EFD", "", 4, 0);
            day_of_month = new BtDict("day_of_month", 68, "day_of_month", "\u6BCF\u6708\u7B2C\u51E0\u5929", "", 10, 0);
            schedule_hour = new BtDict("schedule_hour", 69, "schedule_hour", "\u5C0F\u65F6", "", 10, 0);
            schedule_minutes = new BtDict("schedule_minutes", 70, "schedule_minutes", "\u5206\u949F", "", 10, 0);
            schedule_seconds = new BtDict("schedule_seconds", 71, "schedule_seconds", "\u79D2", "", 10, 0);
            automatic_delete_code = new BtDict("automatic_delete_code", 72, "automatic_delete_code", "\u81EA\u52A8\u5220\u9664\u6807\u8BC6", "", 1, 0);
            is_register_log = new BtDict("is_register_log", 73, "is_register_log", "\u662F\u5426\u767B\u8BB0\u65E5\u5FD7", "", 1, 0);
            scheduling_status = new BtDict("scheduling_status", 74, "scheduling_status", "\u8C03\u5EA6\u72B6\u6001", "", 20, 0);
            total_cost = new BtDict("total_cost", 75, "total_cost", "\u7D2F\u8BA1\u8017\u65F6", "", 19, 0);
            success_group_count = new BtDict("success_group_count", 76, "success_group_count", "\u6210\u529F\u5206\u7EC4\u8BB0\u5F55\u6570", "", 19, 0);
            failure_group_count = new BtDict("failure_group_count", 77, "failure_group_count", "\u5931\u8D25\u5206\u7EC4\u8BB0\u5F55\u6570", "", 19, 0);
            success_count = new BtDict("success_count", 78, "success_count", "\u6210\u529F\u8BB0\u5F55\u6570", "", 19, 0);
            failure_count = new BtDict("failure_count", 79, "failure_count", "\u5931\u8D25\u8BB0\u5F55\u6570", "", 19, 0);
            ignore_count = new BtDict("ignore_count", 80, "ignore_count", "\u5FFD\u7565\u7684\u8BB0\u5F55\u6570", "", 19, 0);
            job_excution_status = new BtDict("job_excution_status", 81, "job_excution_status", "\u4F5C\u4E1A\u6267\u884C\u72B6\u6001", "", 15, 0);
            tran_id = new BtDict("tran_id", 82, "tran_id", "\u6279\u91CF\u4EA4\u6613ID", "", 10, 0);
            step_id = new BtDict("step_id", 83, "step_id", "\u6B65\u9AA4\u53F7", "", 8, 0);
            current_step = new BtDict("current_step", 84, "current_step", "\u5F53\u524D\u6B65\u9AA4", "", 8, 0);
            current_flow_step_num = new BtDict("current_flow_step_num", 85, "current_flow_step_num", "\u5F53\u524D\u6D41\u7A0B\u6B65\u9AA4\u53F7", "", 8, 0);
            current_tran_group_id = new BtDict("current_tran_group_id", 86, "current_tran_group_id", "\u5F53\u524D\u6279\u91CF\u4EA4\u6613\u7EC4ID", "", 10, 0);
            current_execution_num = new BtDict("current_execution_num", 87, "current_execution_num", "\u5F53\u524D\u6267\u884C\u5E8F\u53F7", "", 8, 0);
            start_step_num = new BtDict("start_step_num", 88, "start_step_num", "\u8D77\u59CB\u6B65\u9AA4", "", 8, 0);
            start_flow_step_num = new BtDict("start_flow_step_num", 89, "start_flow_step_num", "\u8D77\u59CB\u6D41\u7A0B\u6B65\u9AA4\u53F7", "", 8, 0);
            start_tran_group_id = new BtDict("start_tran_group_id", 90, "start_tran_group_id", "\u8D77\u59CB\u6279\u91CF\u4EA4\u6613\u7EC4ID", "", 10, 0);
            start_execution_no = new BtDict("start_execution_no", 91, "start_execution_no", "\u8D77\u59CB\u6267\u884C\u5E8F\u53F7", "", 8, 0);
            batch_data_num = new BtDict("batch_data_num", 92, "batch_data_num", "\u6279\u6B21\u6570\u636E\u6570\u91CF", "", 19, 0);
            vm_id = new BtDict("vm_id", 93, "vm_id", "\u865A\u62DF\u673AID", "", 32, 0);
            server_host_name = new BtDict("server_host_name", 94, "server_host_name", "\u670D\u52A1\u5668\u4E3B\u673A\u540D\u79F0", "", 200, 0);
            ip_address = new BtDict("ip_address", 95, "ip_address", "IP\u5730\u5740", "", 20, 0);
            thread_name = new BtDict("thread_name", 96, "thread_name", "\u7EBF\u7A0B\u540D\u79F0", "", 80, 0);
            wrong_data = new BtDict("wrong_data", 97, "wrong_data", "\u9519\u8BEF\u7684\u6570\u636E", "", 2000, 0);
            tran_group_desc = new BtDict("tran_group_desc", 98, "tran_group_desc", "\u4EA4\u6613\u7EC4\u522B\u4E2D\u6587\u63CF\u8FF0", "", 200, 0);
            reconnection_num = new BtDict("reconnection_num", 99, "reconnection_num", "\u91CD\u8FDE\u6B21\u6570", "", 8, 0);
            transactions_submit_num = new BtDict("transactions_submit_num", 100, "transactions_submit_num", "\u4E8B\u52A1\u63D0\u4EA4\u95F4\u9694\uFF08\u5355\u4F4D\uFF1A\u7B14\u6570\uFF09", "", 8, 0);
            data_split_key = new BtDict("data_split_key", 101, "data_split_key", "\u6570\u636E\u62C6\u5206\u4E3B\u952E", "", 1000, 0);
            single_query_num = new BtDict("single_query_num", 102, "single_query_num", "\u5355\u6B21\u67E5\u8BE2\u7B14\u6570", "", 8, 0);
            job_split_condition = new BtDict("job_split_condition", 103, "job_split_condition", "\u4F5C\u4E1A\u62C6\u5206\u6761\u4EF6\uFF08\u533A\u95F4\uFF09", "", 400, 0);
            log_level = new BtDict("log_level", 104, "log_level", "\u65E5\u5FD7\u7EA7\u522B", "", 8, 0);
            execution_no = new BtDict("execution_no", 105, "execution_no", "\u6267\u884C\u5E8F\u53F7", "", 8, 0);
            system_tran_date = new BtDict("system_tran_date", 106, "system_tran_date", "\u7CFB\u7EDF\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            tran_date = new BtDict("tran_date", 107, "tran_date", "\u4EA4\u6613\u65E5\u671F", "", 0, 0);
            system_date = new BtDict("system_date", 108, "system_date", "\u7CFB\u7EDF\u65E5\u671F", "", 8, 0);
            chinese_name = new BtDict("chinese_name", 109, "chinese_name", "\u4E2D\u6587\u540D\u79F0", "", 200, 0);
            flow_step_num = new BtDict("flow_step_num", 110, "flow_step_num", "\u6D41\u7A0B\u6B65\u9AA4\u53F7", "", 8, 0);
            is_execution = new BtDict("is_execution", 111, "is_execution", "\u662F\u5426\u6267\u884C", "", 1, 0);
            log_num = new BtDict("log_num", 112, "log_num", "\u65E5\u5FD7\u5E8F\u53F7", "", 8, 0);
            run_info = new BtDict("run_info", 113, "run_info", "\u8FD0\u884C\u4FE1\u606F", "", 400, 0);
            process_node = new BtDict("process_node", 114, "process_node", "\u5F53\u524D\u6D41\u7A0B\u56FE\u7ED3\u70B9ID", "", 30, 0);
            query_date = new BtDict("query_date", 115, "query_date", "\u67E5\u8BE2\u65E5\u671F", "", 8, 0);
            dayend_manage_date = new BtDict("dayend_manage_date", 116, "dayend_manage_date", "\u65E5\u7EC8\u7BA1\u7406\u65E5\u671F", "", 8, 0);
            last_date = new BtDict("last_date", 117, "last_date", "\u4E0A\u6B21\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            before_last_date = new BtDict("before_last_date", 118, "before_last_date", "\u4E0A\u4E0A\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            next_date = new BtDict("next_date", 119, "next_date", "\u4E0B\u6B21\u4EA4\u6613\u65E5\u671F", "", 8, 0);
            file_info_key = new BtDict("file_info_key", 120, "file_info_key", "\u6587\u4EF6\u4FE1\u606F\u540D\u79F0", "", 10, 0);
            file_info_val = new BtDict("file_info_val", 121, "file_info_val", "\u6587\u4EF6\u4FE1\u606F\u503C", "", 10, 0);
            batch_file_status = new BtDict("batch_file_status", 122, "batch_file_status", "\u6587\u4EF6\u6279\u91CF\u72B6\u6001", "", 1, 0);
            busi_code = new BtDict("busi_code", 123, "busi_code", "\u4E1A\u52A1\u5173\u952E\u5B57", "", 10, 0);
            busi_param_key = new BtDict("busi_param_key", 124, "busi_param_key", "\u4E1A\u52A1\u53C2\u6570\u540D\u79F0", "", 10, 0);
            busi_param_val = new BtDict("busi_param_val", 125, "busi_param_val", "\u4E1A\u52A1\u53C2\u6570\u503C", "", 10, 0);
            dcn_num = new BtDict("dcn_num", 126, "dcn_num", "DCN\u7F16\u53F7", "", 10, 0);
            first_submit_sign = new BtDict("first_submit_sign", 127, "first_submit_sign", "\u4E00\u6B21\u63D0\u4EA4\u6307\u793A\u6807\u5FD7", "", 1, 0);
            job_server_code = new BtDict("job_server_code", 128, "job_server_code", "\u4F5C\u4E1A\u670D\u52A1\u5668\u6807\u8BC6", "", 100, 0);
            control_code = new BtDict("control_code", 129, "control_code", "\u6279\u91CF\u63A7\u5236\u6807\u8BC6", "", 100, 0);
            control_value = new BtDict("control_value", 130, "control_value", "\u6279\u91CF\u63A7\u5236\u503C1-\u5141\u8BB8 0-\u4E0D\u4E0D\u5141\u8BB8", "", 1, 0);
            service_code = new BtDict("service_code", 131, "service_code", "\u670D\u52A1\u6807\u8BC6", "", 60, 0);
            tran_type = new BtDict("tran_type", 132, "tran_type", "\u4EA4\u6613\u7C7B\u578B", "", 1, 0);
            is_skip = new BtDict("is_skip", 133, "is_skip", "\u80FD\u5426\u8DF3\u8FC7", "", 1, 0);
            group_num = new BtDict("group_num", 134, "group_num", "\u5206\u7EC4\u53F7", "", 20, 0);
            start_line = new BtDict("start_line", 135, "start_line", "\u8D77\u59CB\u884C\u53F7", "", 8, 0);
            end_line = new BtDict("end_line", 136, "end_line", "\u7EC8\u6B62\u884C\u53F7", "", 8, 0);
            total_lines = new BtDict("total_lines", 137, "total_lines", "\u603B\u884C\u53F7", "", 8, 0);
            is_batch_file = new BtDict("is_batch_file", 138, "is_batch_file", "\u662F\u5426\u6587\u4EF6\u6279\u91CF", "", 1, 0);
            target_batch_group = new BtDict("target_batch_group", 139, "target_batch_group", "\u76EE\u6807\u6279\u91CF\u4EA4\u6613\u7EC4", "", 10, 0);
            is_ignore = new BtDict("is_ignore", 140, "is_ignore", "\u662F\u5426\u5FFD\u7565", "", 1, 0);
            $VALUES = (new BtDict[] {
                flow_step_name, task_exe_mode, task_commit_date, task_exe_date, transaction_date, job_execution_mode, task_run_mode, task_num, task_exe_num, tran_exe_num, 
                task_priority, task_commit_time, tran_group_id, tran_group_exe_time, job_no, tran_code, tran_chinese_name, tran_flow_id, task_interrupt_flag, task_run_conditions, 
                tran_run_conditions, task_run_conditions_code, task_run_condi_service_code, task_run_conditions_param, task_run_callback_service, execution_code, fail_interrupt_code, data_split_mode, rely_tran_goup_id, rely_tran_list, 
                tran_run_time, tran_start_time, job_distribute_start_time, tran_end_time, tran_start_timestamp, tran_end_timestamp, data_area, request_data_area, response_data_area, tran_state, 
                error_message, message, error_stack, error_step, tran_execution_domain_code, concurrency_num, mini_concurrency_num, max_concurrency_num, max_step_concurrency_num, mini_step_concurrency_num, 
                mini_job_concurrency_num, max_job_concurrency_num, job_server_cluster, exe_tran_flow_list, exe_tran_group_list, exe_domain_state, desc_message, package_serial, schedule_name, schedule_date, 
                schedule_time, exe_class_name, schedule_param, schedule_start_date, schedule_end_date, schedule_year, schedule_week, schedule_month, day_of_month, schedule_hour, 
                schedule_minutes, schedule_seconds, automatic_delete_code, is_register_log, scheduling_status, total_cost, success_group_count, failure_group_count, success_count, failure_count, 
                ignore_count, job_excution_status, tran_id, step_id, current_step, current_flow_step_num, current_tran_group_id, current_execution_num, start_step_num, start_flow_step_num, 
                start_tran_group_id, start_execution_no, batch_data_num, vm_id, server_host_name, ip_address, thread_name, wrong_data, tran_group_desc, reconnection_num, 
                transactions_submit_num, data_split_key, single_query_num, job_split_condition, log_level, execution_no, system_tran_date, tran_date, system_date, chinese_name, 
                flow_step_num, is_execution, log_num, run_info, process_node, query_date, dayend_manage_date, last_date, before_last_date, next_date, 
                file_info_key, file_info_val, batch_file_status, busi_code, busi_param_key, busi_param_val, dcn_num, first_submit_sign, job_server_code, control_code, 
                control_value, service_code, tran_type, is_skip, group_num, start_line, end_line, total_lines, is_batch_file, target_batch_group, 
                is_ignore
            });
        }

        private BtDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.BtDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class CommDict extends Enum
        implements Symbol
    {

        public static CommDict[] values()
        {
            return (CommDict[])$VALUES.clone();
        }

        public static CommDict valueOf(String name)
        {
            return (CommDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/KsDict$CommDict, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getDescription()
        {
            return __description__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longname__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longname__)).getString();
        }

        public int getLength()
        {
            return __length__;
        }

        public int getFractionDigits()
        {
            return __fractionDigits__;
        }

        public static final CommDict corporate_code;
        public static final CommDict system_code;
        public static final CommDict sub_system_code;
        public static final CommDict target_system_code;
        public static final CommDict maintenance_date;
        public static final CommDict maintenance_teller;
        public static final CommDict maintenance_mechanism;
        public static final CommDict timestamp;
        public static final CommDict record_state;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final CommDict $VALUES[];

        static 
        {
            corporate_code = new CommDict("corporate_code", 0, "corporate_code", "\u6CD5\u4EBA\u4EE3\u7801", "\u6CD5\u4EBA\u4EE3\u7801", 4, 0);
            system_code = new CommDict("system_code", 1, "system_code", "\u7CFB\u7EDF\u6807\u8BC6\u53F7", "", 10, 0);
            sub_system_code = new CommDict("sub_system_code", 2, "sub_system_code", "\u5B50\u7CFB\u7EDF\u7F16\u53F7", "", 10, 0);
            target_system_code = new CommDict("target_system_code", 3, "target_system_code", "\u76EE\u6807\u7CFB\u7EDF\u6807\u8BC6", "", 10, 0);
            maintenance_date = new CommDict("maintenance_date", 4, "maintenance_date", "\u7EF4\u62A4\u65E5\u671F", "\u7EF4\u62A4\u65E5\u671F", 8, 0);
            maintenance_teller = new CommDict("maintenance_teller", 5, "maintenance_teller", "\u7EF4\u62A4\u67DC\u5458", "\u7EF4\u62A4\u67DC\u5458", 8, 0);
            maintenance_mechanism = new CommDict("maintenance_mechanism", 6, "maintenance_mechanism", "\u7EF4\u62A4\u673A\u6784", "\u7EF4\u62A4\u673A\u6784", 10, 0);
            timestamp = new CommDict("timestamp", 7, "timestamp", "\u65F6\u95F4\u6233", "\u65F6\u95F4\u6233", 0, 0);
            record_state = new CommDict("record_state", 8, "record_state", "\u8BB0\u5F55\u72B6\u6001", "\u8BB0\u5F55\u72B6\u6001", 1, 0);
            $VALUES = (new CommDict[] {
                corporate_code, system_code, sub_system_code, target_system_code, maintenance_date, maintenance_teller, maintenance_mechanism, timestamp, record_state
            });
        }

        private CommDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("KsDict.CommDict.").append(__id__).append(".longname").toString();
        }
    }

}
