// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SPDict.java

package cn.sunline.adp.cedar.base.dict;

import cn.sunline.adp.core.lang.IString;
import cn.sunline.adp.metadata.model.annotation.ConfigType;
import cn.sunline.edsp.base.lang.Symbol;

public interface SPDict
{
    public static final class AsyncDict extends Enum
        implements Symbol
    {

        public static AsyncDict[] values()
        {
            return (AsyncDict[])$VALUES.clone();
        }

        public static AsyncDict valueOf(String name)
        {
            return (AsyncDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/SPDict$AsyncDict, name);
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

        public static final AsyncDict corpo_code;
        public static final AsyncDict priority_level;
        public static final AsyncDict max_thread;
        public static final AsyncDict postposition_task;
        public static final AsyncDict retrigger_time;
        public static final AsyncDict depend_task;
        public static final AsyncDict process_mode;
        public static final AsyncDict redo_count;
        public static final AsyncDict queue_length;
        public static final AsyncDict create_time;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final AsyncDict $VALUES[];

        static 
        {
            corpo_code = new AsyncDict("corpo_code", 0, "corpo_code", "\u6CD5\u4EBA\u4EE3\u7801", "", 20, 0);
            priority_level = new AsyncDict("priority_level", 1, "priority_level", "\u4F18\u5148\u7EA7\u522B", "", 3, 0);
            max_thread = new AsyncDict("max_thread", 2, "max_thread", "\u6700\u5927\u5E76\u53D1\u6570", "", 3, 0);
            postposition_task = new AsyncDict("postposition_task", 3, "postposition_task", "\u540E\u7F6E\u4EFB\u52A1", "", 50, 0);
            retrigger_time = new AsyncDict("retrigger_time", 4, "retrigger_time", "\u518D\u6B21\u89E6\u53D1\u65F6\u95F4", "", 19, 0);
            depend_task = new AsyncDict("depend_task", 5, "depend_task", "\u4F9D\u8D56\u5217\u8868", "", 400, 0);
            process_mode = new AsyncDict("process_mode", 6, "process_mode", "\u5904\u7406\u6A21\u5F0F", "", 1, 0);
            redo_count = new AsyncDict("redo_count", 7, "redo_count", "\u91CD\u505A\u6B21\u6570", "", 3, 0);
            queue_length = new AsyncDict("queue_length", 8, "queue_length", "\u961F\u5217\u957F\u5EA6", "", 8, 0);
            create_time = new AsyncDict("create_time", 9, "create_time", "\u521B\u5EFA\u65F6\u95F4", "", 0, 0);
            $VALUES = (new AsyncDict[] {
                corpo_code, priority_level, max_thread, postposition_task, retrigger_time, depend_task, process_mode, redo_count, queue_length, create_time
            });
        }

        private AsyncDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("SPDict.AsyncDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class BatchDict extends Enum
        implements Symbol
    {

        public static BatchDict[] values()
        {
            return (BatchDict[])$VALUES.clone();
        }

        public static BatchDict valueOf(String name)
        {
            return (BatchDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/SPDict$BatchDict, name);
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

        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final BatchDict $VALUES[] = new BatchDict[0];


        private BatchDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("SPDict.BatchDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class OnlineDict extends Enum
        implements Symbol
    {

        public static OnlineDict[] values()
        {
            return (OnlineDict[])$VALUES.clone();
        }

        public static OnlineDict valueOf(String name)
        {
            return (OnlineDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/SPDict$OnlineDict, name);
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

        public static final OnlineDict out_service_app;
        public static final OnlineDict out_service_code;
        public static final OnlineDict out_service_group;
        public static final OnlineDict out_service_version;
        public static final OnlineDict out_service_dcnno;
        public static final OnlineDict inner_service_code;
        public static final OnlineDict inner_service_impl_code;
        public static final OnlineDict service_category;
        public static final OnlineDict protocol_id;
        public static final OnlineDict transaction_mode;
        public static final OnlineDict service_type;
        public static final OnlineDict timeout;
        public static final OnlineDict alias_mapping;
        public static final OnlineDict regist_protocol_log;
        public static final OnlineDict force_unused_odb_cache;
        public static final OnlineDict service_invoke_id;
        public static final OnlineDict timeout_redo_counts;
        public static final OnlineDict timeout_confirm;
        public static final OnlineDict regist_call_log;
        public static final OnlineDict busi_seq_num;
        public static final OnlineDict call_seq_num;
        public static final OnlineDict orig_call_seq_num;
        public static final OnlineDict input_head;
        public static final OnlineDict input_body;
        public static final OnlineDict attachment;
        public static final OnlineDict input_msg;
        public static final OnlineDict output_msg;
        public static final OnlineDict resp_code;
        public static final OnlineDict resp_text;
        public static final OnlineDict error_text;
        public static final OnlineDict process_status;
        public static final OnlineDict client_inst_id;
        public static final OnlineDict server_inst_id;
        public static final OnlineDict service_call_seq_num;
        public static final OnlineDict retry_count;
        public static final OnlineDict service_mode;
        public static final OnlineDict initator_id;
        public static final OnlineDict processor_id;
        public static final OnlineDict route_keys;
        public static final OnlineDict cancel_service;
        public static final OnlineDict confirm_service;
        public static final OnlineDict service_transaction_mode;
        public static final OnlineDict service_executor_type;
        public static final OnlineDict target_dcn;
        public static final OnlineDict register_mode;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final OnlineDict $VALUES[];

        static 
        {
            out_service_app = new OnlineDict("out_service_app", 0, "out_service_app", "\u5916\u90E8\u670D\u52A1\u5E94\u7528\u6807\u8BC6", "", 25, 0);
            out_service_code = new OnlineDict("out_service_code", 1, "out_service_code", "\u5916\u90E8\u670D\u52A1\u7801", "", 50, 0);
            out_service_group = new OnlineDict("out_service_group", 2, "out_service_group", "\u5916\u90E8\u670D\u52A1\u5206\u7EC4\u53F7", "", 25, 0);
            out_service_version = new OnlineDict("out_service_version", 3, "out_service_version", "\u5916\u90E8\u670D\u52A1\u7248\u672C\u53F7", "", 25, 0);
            out_service_dcnno = new OnlineDict("out_service_dcnno", 4, "out_service_dcnno", "\u5916\u90E8\u670D\u52A1\u8282\u70B9\u7F16\u53F7", "", 10, 0);
            inner_service_code = new OnlineDict("inner_service_code", 5, "inner_service_code", "\u5185\u90E8\u670D\u52A1\u7801", "", 100, 0);
            inner_service_impl_code = new OnlineDict("inner_service_impl_code", 6, "inner_service_impl_code", "\u5185\u90E8\u670D\u52A1\u5B9E\u73B0\u6807\u8BC6", "", 100, 0);
            service_category = new OnlineDict("service_category", 7, "service_category", "\u670D\u52A1\u7C7B\u522B", "", 1, 0);
            protocol_id = new OnlineDict("protocol_id", 8, "protocol_id", "\u901A\u8BAF\u534F\u8BAE\u6807\u8BC6", "", 25, 0);
            transaction_mode = new OnlineDict("transaction_mode", 9, "transaction_mode", "\u4E8B\u52A1\u6A21\u5F0F", "", 1, 0);
            service_type = new OnlineDict("service_type", 10, "service_type", "\u4E1A\u52A1\u670D\u52A1\u7C7B\u578B", "", 10, 0);
            timeout = new OnlineDict("timeout", 11, "timeout", "\u8D85\u65F6\u65F6\u95F4", "", 8, 0);
            alias_mapping = new OnlineDict("alias_mapping", 12, "alias_mapping", "\u662F\u5426\u522B\u540D\u6620\u5C04", "", 1, 0);
            regist_protocol_log = new OnlineDict("regist_protocol_log", 13, "regist_protocol_log", "\u662F\u5426\u767B\u8BB0\u901A\u8BAF\u63A5\u5165\u65E5\u5FD7", "", 1, 0);
            force_unused_odb_cache = new OnlineDict("force_unused_odb_cache", 14, "force_unused_odb_cache", "\u662F\u5426\u5F3A\u5236\u4E0D\u4F7F\u7528ODB\u7F13\u5B58", "", 1, 0);
            service_invoke_id = new OnlineDict("service_invoke_id", 15, "service_invoke_id", "\u4E1A\u52A1\u670D\u52A1\u8C03\u7528\u6807\u8BC6", "", 50, 0);
            timeout_redo_counts = new OnlineDict("timeout_redo_counts", 16, "timeout_redo_counts", "\u8D85\u65F6\u91CD\u8BD5\u6B21\u6570", "", 8, 0);
            timeout_confirm = new OnlineDict("timeout_confirm", 17, "timeout_confirm", "\u8D85\u65F6\u786E\u8BA4\u6807\u5FD7", "", 1, 0);
            regist_call_log = new OnlineDict("regist_call_log", 18, "regist_call_log", "\u662F\u5426\u767B\u8BB0\u901A\u8BAF\u63A5\u51FA\u65E5\u5FD7", "", 1, 0);
            busi_seq_num = new OnlineDict("busi_seq_num", 19, "busi_seq_num", "\u4E1A\u52A1\u6D41\u6C34\u53F7", "", 64, 0);
            call_seq_num = new OnlineDict("call_seq_num", 20, "call_seq_num", "\u7CFB\u7EDF\u8C03\u7528\u6D41\u6C34\u53F7", "", 64, 0);
            orig_call_seq_num = new OnlineDict("orig_call_seq_num", 21, "orig_call_seq_num", "\u53D1\u8D77\u65B9\u7CFB\u7EDF\u8C03\u7528\u6D41\u6C34", "", 64, 0);
            input_head = new OnlineDict("input_head", 22, "input_head", "\u8BF7\u6C42Head\u6570\u636E", "", 1000, 0);
            input_body = new OnlineDict("input_body", 23, "input_body", "\u8BF7\u6C42Body\u6570\u636E", "", 50000, 0);
            attachment = new OnlineDict("attachment", 24, "attachment", "\u9644\u52A0\u6570\u636E", "", 1000, 0);
            input_msg = new OnlineDict("input_msg", 25, "input_msg", "\u8BF7\u6C42\u6570\u636E", "", 4001, 0);
            output_msg = new OnlineDict("output_msg", 26, "output_msg", "\u54CD\u5E94\u6570\u636E", "", 4001, 0);
            resp_code = new OnlineDict("resp_code", 27, "resp_code", "\u54CD\u5E94\u7801", "", 25, 0);
            resp_text = new OnlineDict("resp_text", 28, "resp_text", "\u54CD\u5E94\u4FE1\u606F", "", 400, 0);
            error_text = new OnlineDict("error_text", 29, "error_text", "\u5F02\u5E38\u5806\u6808", "", 4000, 0);
            process_status = new OnlineDict("process_status", 30, "process_status", "\u6267\u884C\u72B6\u6001", "", 1, 0);
            client_inst_id = new OnlineDict("client_inst_id", 31, "client_inst_id", "\u5BA2\u6237\u7AEF\u6807\u8BC6", "", 50, 0);
            server_inst_id = new OnlineDict("server_inst_id", 32, "server_inst_id", "\u670D\u52A1\u7AEF\u6807\u8BC6", "", 50, 0);
            service_call_seq_num = new OnlineDict("service_call_seq_num", 33, "service_call_seq_num", "\u670D\u52A1\u8C03\u7528\u6D41\u6C34\u53F7", "", 64, 0);
            retry_count = new OnlineDict("retry_count", 34, "retry_count", "\u91CD\u8BD5\u6B21\u6570", "", 3, 0);
            service_mode = new OnlineDict("service_mode", 35, "service_mode", "\u670D\u52A1\u6A21\u5F0F", "", 1, 0);
            initator_id = new OnlineDict("initator_id", 36, "initator_id", "\u53D1\u8D77\u65B9\u6807\u8BC6", "", 50, 0);
            processor_id = new OnlineDict("processor_id", 37, "processor_id", "\u5904\u7406\u65B9\u6807\u8BC6", "", 50, 0);
            route_keys = new OnlineDict("route_keys", 38, "route_keys", "\u8DEF\u7531\u5173\u952E\u5B57", "", 100, 0);
            cancel_service = new OnlineDict("cancel_service", 39, "cancel_service", "\u51B2\u6B63\u670D\u52A1\u6807\u8BC6", "", 50, 0);
            confirm_service = new OnlineDict("confirm_service", 40, "confirm_service", "\u4E8C\u6B21\u63D0\u4EA4\u670D\u52A1\u6807\u8BC6", "", 50, 0);
            service_transaction_mode = new OnlineDict("service_transaction_mode", 41, "service_transaction_mode", "\u670D\u52A1\u4E8B\u52A1\u6A21\u5F0F", "", 20, 0);
            service_executor_type = new OnlineDict("service_executor_type", 42, "service_executor_type", "\u670D\u52A1\u6267\u884C\u7C7B\u578B", "", 1, 0);
            target_dcn = new OnlineDict("target_dcn", 43, "target_dcn", "\u76EE\u6807DCN", "", 25, 0);
            register_mode = new OnlineDict("register_mode", 44, "register_mode", "\u767B\u8BB0\u901A\u8BAF\u65E5\u5FD7\u6A21\u5F0F", "", 1, 0);
            $VALUES = (new OnlineDict[] {
                out_service_app, out_service_code, out_service_group, out_service_version, out_service_dcnno, inner_service_code, inner_service_impl_code, service_category, protocol_id, transaction_mode, 
                service_type, timeout, alias_mapping, regist_protocol_log, force_unused_odb_cache, service_invoke_id, timeout_redo_counts, timeout_confirm, regist_call_log, busi_seq_num, 
                call_seq_num, orig_call_seq_num, input_head, input_body, attachment, input_msg, output_msg, resp_code, resp_text, error_text, 
                process_status, client_inst_id, server_inst_id, service_call_seq_num, retry_count, service_mode, initator_id, processor_id, route_keys, cancel_service, 
                confirm_service, service_transaction_mode, service_executor_type, target_dcn, register_mode
            });
        }

        private OnlineDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("SPDict.OnlineDict.").append(__id__).append(".longname").toString();
        }
    }

    public static final class CommonDict extends Enum
        implements Symbol
    {

        public static CommonDict[] values()
        {
            return (CommonDict[])$VALUES.clone();
        }

        public static CommonDict valueOf(String name)
        {
            return (CommonDict)Enum.valueOf(cn/sunline/adp/cedar/base/dict/SPDict$CommonDict, name);
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

        public static final CommonDict system_code;
        public static final CommonDict sub_system_code;
        public static final CommonDict description;
        public static final CommonDict is_enable;
        public static final CommonDict log_level;
        public static final CommonDict begin_time;
        public static final CommonDict end_time;
        public static final CommonDict use_time;
        private String __id__;
        private String __longname__;
        private String __description__;
        private int __length__;
        private int __fractionDigits__;
        private final String KEY;
        private static final CommonDict $VALUES[];

        static 
        {
            system_code = new CommonDict("system_code", 0, "system_code", "\u7CFB\u7EDF\u7F16\u53F7", "", 20, 0);
            sub_system_code = new CommonDict("sub_system_code", 1, "sub_system_code", "\u5B50\u7CFB\u7EDF\u7F16\u53F7", "", 20, 0);
            description = new CommonDict("description", 2, "description", "\u63CF\u8FF0\u4FE1\u606F", "", 200, 0);
            is_enable = new CommonDict("is_enable", 3, "is_enable", "\u662F\u5426\u542F\u7528", "", 1, 0);
            log_level = new CommonDict("log_level", 4, "log_level", "\u65E5\u5FD7\u7EA7\u522B", "", 10, 0);
            begin_time = new CommonDict("begin_time", 5, "begin_time", "\u5F00\u59CB\u65F6\u95F4", "", 0, 0);
            end_time = new CommonDict("end_time", 6, "end_time", "\u7ED3\u675F\u65F6\u95F4", "", 0, 0);
            use_time = new CommonDict("use_time", 7, "use_time", "\u8017\u65F6\uFF08\u6BEB\u79D2\uFF09", "", 19, 0);
            $VALUES = (new CommonDict[] {
                system_code, sub_system_code, description, is_enable, log_level, begin_time, end_time, use_time
            });
        }

        private CommonDict(String s, int i, String __id__, String __longname__, String __description__, int __length__, int __fractionDigits__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__longname__ = __longname__;
            this.__description__ = __description__;
            this.__length__ = __length__;
            this.__fractionDigits__ = __fractionDigits__;
            KEY = (new StringBuilder()).append("SPDict.CommonDict.").append(__id__).append(".longname").toString();
        }
    }

}
