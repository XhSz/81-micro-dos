// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SPEnumType.java

package cn.sunline.adp.cedar.base.type;

import cn.sunline.adp.core.lang.IString;
import cn.sunline.edsp.base.lang.DefaultEnum;
import cn.sunline.edsp.base.util.convert.EnumUtils;

public interface SPEnumType
{
    public static final class E_ASYNC_PROCESS_MODE extends Enum
        implements DefaultEnum
    {

        public static E_ASYNC_PROCESS_MODE[] values()
        {
            return (E_ASYNC_PROCESS_MODE[])$VALUES.clone();
        }

        public static E_ASYNC_PROCESS_MODE valueOf(String name)
        {
            return (E_ASYNC_PROCESS_MODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_ASYNC_PROCESS_MODE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public Integer getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_ASYNC_PROCESS_MODE, value);
        }

        public static E_ASYNC_PROCESS_MODE get(Object value)
        {
            return (E_ASYNC_PROCESS_MODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_ASYNC_PROCESS_MODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_ASYNC_PROCESS_MODE NOW;
        public static final E_ASYNC_PROCESS_MODE CONFIRM;
        private String __id__;
        private Integer __value__;
        private String __longName__;
        private final String KEY;
        private static final E_ASYNC_PROCESS_MODE $VALUES[];

        static 
        {
            NOW = new E_ASYNC_PROCESS_MODE("NOW", 0, "NOW", Integer.valueOf(1), "\u5373\u65F6");
            CONFIRM = new E_ASYNC_PROCESS_MODE("CONFIRM", 1, "CONFIRM", Integer.valueOf(0), "\u786E\u8BA4");
            $VALUES = (new E_ASYNC_PROCESS_MODE[] {
                NOW, CONFIRM
            });
        }

        private E_ASYNC_PROCESS_MODE(String s, int i, String __id__, Integer __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_ASYNC_PROCESS_MODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_REGISTER_LOG_MODE extends Enum
        implements DefaultEnum
    {

        public static E_REGISTER_LOG_MODE[] values()
        {
            return (E_REGISTER_LOG_MODE[])$VALUES.clone();
        }

        public static E_REGISTER_LOG_MODE valueOf(String name)
        {
            return (E_REGISTER_LOG_MODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_REGISTER_LOG_MODE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_REGISTER_LOG_MODE, value);
        }

        public static E_REGISTER_LOG_MODE get(Object value)
        {
            return (E_REGISTER_LOG_MODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_REGISTER_LOG_MODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_REGISTER_LOG_MODE NoRegister;
        public static final E_REGISTER_LOG_MODE RegisterNoCheck;
        public static final E_REGISTER_LOG_MODE RepetitionPrevention;
        public static final E_REGISTER_LOG_MODE Idempotent;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_REGISTER_LOG_MODE $VALUES[];

        static 
        {
            NoRegister = new E_REGISTER_LOG_MODE("NoRegister", 0, "NoRegister", "0", "\u4E0D\u767B\u8BB0");
            RegisterNoCheck = new E_REGISTER_LOG_MODE("RegisterNoCheck", 1, "RegisterNoCheck", "1", "\u767B\u8BB0\u4E0D\u68C0\u67E5");
            RepetitionPrevention = new E_REGISTER_LOG_MODE("RepetitionPrevention", 2, "RepetitionPrevention", "2", "\u767B\u8BB0\u5E76\u9632\u91CD\u5904\u7406");
            Idempotent = new E_REGISTER_LOG_MODE("Idempotent", 3, "Idempotent", "3", "\u767B\u8BB0\u5E76\u5E42\u7B49\u5904\u7406");
            $VALUES = (new E_REGISTER_LOG_MODE[] {
                NoRegister, RegisterNoCheck, RepetitionPrevention, Idempotent
            });
        }

        private E_REGISTER_LOG_MODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_REGISTER_LOG_MODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SVCEXETYPE extends Enum
        implements DefaultEnum
    {

        public static E_SVCEXETYPE[] values()
        {
            return (E_SVCEXETYPE[])$VALUES.clone();
        }

        public static E_SVCEXETYPE valueOf(String name)
        {
            return (E_SVCEXETYPE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCEXETYPE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCEXETYPE, value);
        }

        public static E_SVCEXETYPE get(Object value)
        {
            return (E_SVCEXETYPE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCEXETYPE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SVCEXETYPE L;
        public static final E_SVCEXETYPE R;
        public static final E_SVCEXETYPE A;
        public static final E_SVCEXETYPE F;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SVCEXETYPE $VALUES[];

        static 
        {
            L = new E_SVCEXETYPE("L", 0, "L", "L", "\u672C\u5730\u6267\u884C");
            R = new E_SVCEXETYPE("R", 1, "R", "R", "\u8FDC\u7A0B\u6267\u884C");
            A = new E_SVCEXETYPE("A", 2, "A", "A", "\u5F02\u6B65\u6267\u884C");
            F = new E_SVCEXETYPE("F", 3, "F", "F", "\u6D41\u7A0B\u6267\u884C");
            $VALUES = (new E_SVCEXETYPE[] {
                L, R, A, F
            });
        }

        private E_SVCEXETYPE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_SVCEXETYPE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SERVICE_TRANS_MODE extends Enum
        implements DefaultEnum
    {

        public static E_SERVICE_TRANS_MODE[] values()
        {
            return (E_SERVICE_TRANS_MODE[])$VALUES.clone();
        }

        public static E_SERVICE_TRANS_MODE valueOf(String name)
        {
            return (E_SERVICE_TRANS_MODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_SERVICE_TRANS_MODE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SERVICE_TRANS_MODE, value);
        }

        public static E_SERVICE_TRANS_MODE get(Object value)
        {
            return (E_SERVICE_TRANS_MODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SERVICE_TRANS_MODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SERVICE_TRANS_MODE NotSupported;
        public static final E_SERVICE_TRANS_MODE Supports;
        public static final E_SERVICE_TRANS_MODE Required;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SERVICE_TRANS_MODE $VALUES[];

        static 
        {
            NotSupported = new E_SERVICE_TRANS_MODE("NotSupported", 0, "NotSupported", "NotSupported", "\u4E0D\u542F\u4E8B\u52A1");
            Supports = new E_SERVICE_TRANS_MODE("Supports", 1, "Supports", "Supports", "\u652F\u6301\u4E8B\u52A1");
            Required = new E_SERVICE_TRANS_MODE("Required", 2, "Required", "Required", "\u652F\u6301\u4E8B\u52A1\uFF0C\u5E76\u4E14\u6267\u884C\u4E8C\u6B21\u63D0\u4EA4");
            $VALUES = (new E_SERVICE_TRANS_MODE[] {
                NotSupported, Supports, Required
            });
        }

        private E_SERVICE_TRANS_MODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_SERVICE_TRANS_MODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_TRANSACTION_MODE extends Enum
        implements DefaultEnum
    {

        public static E_TRANSACTION_MODE[] values()
        {
            return (E_TRANSACTION_MODE[])$VALUES.clone();
        }

        public static E_TRANSACTION_MODE valueOf(String name)
        {
            return (E_TRANSACTION_MODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_TRANSACTION_MODE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_TRANSACTION_MODE, value);
        }

        public static E_TRANSACTION_MODE get(Object value)
        {
            return (E_TRANSACTION_MODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_TRANSACTION_MODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_TRANSACTION_MODE R;
        public static final E_TRANSACTION_MODE A;
        public static final E_TRANSACTION_MODE C;
        public static final E_TRANSACTION_MODE D;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_TRANSACTION_MODE $VALUES[];

        static 
        {
            R = new E_TRANSACTION_MODE("R", 0, "R", "R", "\u53EA\u8BFB\u4E8B\u52A1\u6A21\u5F0F");
            A = new E_TRANSACTION_MODE("A", 1, "A", "A", "\u539F\u5B50\u4E8B\u52A1\u6A21\u5F0F");
            C = new E_TRANSACTION_MODE("C", 2, "C", "C", "\u4E8B\u52A1\u5757\u6A21\u5F0F");
            D = new E_TRANSACTION_MODE("D", 3, "D", "D", "\u5206\u5E03\u5F0F\u4E8B\u52A1\u6A21\u5F0F");
            $VALUES = (new E_TRANSACTION_MODE[] {
                R, A, C, D
            });
        }

        private E_TRANSACTION_MODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_TRANSACTION_MODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SVCMODE extends Enum
        implements DefaultEnum
    {

        public static E_SVCMODE[] values()
        {
            return (E_SVCMODE[])$VALUES.clone();
        }

        public static E_SVCMODE valueOf(String name)
        {
            return (E_SVCMODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCMODE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCMODE, value);
        }

        public static E_SVCMODE get(Object value)
        {
            return (E_SVCMODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCMODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SVCMODE S;
        public static final E_SVCMODE A;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SVCMODE $VALUES[];

        static 
        {
            S = new E_SVCMODE("S", 0, "S", "S", "\u540C\u6B65");
            A = new E_SVCMODE("A", 1, "A", "A", "\u5F02\u6B65");
            $VALUES = (new E_SVCMODE[] {
                S, A
            });
        }

        private E_SVCMODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_SVCMODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_PROCSTATUS extends Enum
        implements DefaultEnum
    {

        public static E_PROCSTATUS[] values()
        {
            return (E_PROCSTATUS[])$VALUES.clone();
        }

        public static E_PROCSTATUS valueOf(String name)
        {
            return (E_PROCSTATUS)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_PROCSTATUS, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_PROCSTATUS, value);
        }

        public static E_PROCSTATUS get(Object value)
        {
            return (E_PROCSTATUS)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_PROCSTATUS, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_PROCSTATUS W;
        public static final E_PROCSTATUS P;
        public static final E_PROCSTATUS S;
        public static final E_PROCSTATUS F;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_PROCSTATUS $VALUES[];

        static 
        {
            W = new E_PROCSTATUS("W", 0, "W", "W", "\u5F85\u5904\u7406");
            P = new E_PROCSTATUS("P", 1, "P", "P", "\u5904\u7406\u4E2D");
            S = new E_PROCSTATUS("S", 2, "S", "S", "\u5904\u7406\u6210\u529F");
            F = new E_PROCSTATUS("F", 3, "F", "F", "\u5904\u7406\u5931\u8D25");
            $VALUES = (new E_PROCSTATUS[] {
                W, P, S, F
            });
        }

        private E_PROCSTATUS(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_PROCSTATUS.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SVCTYPE extends Enum
        implements DefaultEnum
    {

        public static E_SVCTYPE[] values()
        {
            return (E_SVCTYPE[])$VALUES.clone();
        }

        public static E_SVCTYPE valueOf(String name)
        {
            return (E_SVCTYPE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCTYPE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCTYPE, value);
        }

        public static E_SVCTYPE get(Object value)
        {
            return (E_SVCTYPE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCTYPE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SVCTYPE CHECK;
        public static final E_SVCTYPE TRY;
        public static final E_SVCTYPE NOTIFY;
        public static final E_SVCTYPE CANCEL;
        public static final E_SVCTYPE CONFIRM;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SVCTYPE $VALUES[];

        static 
        {
            CHECK = new E_SVCTYPE("CHECK", 0, "CHECK", "check", "\u68C0\u67E5\u7C7B\u4E1A\u52A1\u670D\u52A1");
            TRY = new E_SVCTYPE("TRY", 1, "TRY", "try", "\u4E00\u6B21\u63D0\u4EA4\u4E1A\u52A1\u670D\u52A1");
            NOTIFY = new E_SVCTYPE("NOTIFY", 2, "NOTIFY", "notify", "\u901A\u77E5\u7C7B\u4E1A\u52A1\u670D\u52A1");
            CANCEL = new E_SVCTYPE("CANCEL", 3, "CANCEL", "cancel", "\u51B2\u6B63\u7C7B\u4E1A\u52A1\u670D\u52A1");
            CONFIRM = new E_SVCTYPE("CONFIRM", 4, "CONFIRM", "confirm", "\u4E8C\u6B21\u63D0\u4EA4\u7C7B\u4E1A\u52A1\u670D\u52A1");
            $VALUES = (new E_SVCTYPE[] {
                CHECK, TRY, NOTIFY, CANCEL, CONFIRM
            });
        }

        private E_SVCTYPE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_SVCTYPE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SVCCATE extends Enum
        implements DefaultEnum
    {

        public static E_SVCCATE[] values()
        {
            return (E_SVCCATE[])$VALUES.clone();
        }

        public static E_SVCCATE valueOf(String name)
        {
            return (E_SVCCATE)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCCATE, name);
        }

        public String getId()
        {
            return __id__;
        }

        public String getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCCATE, value);
        }

        public static E_SVCCATE get(Object value)
        {
            return (E_SVCCATE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_SVCCATE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SVCCATE T;
        public static final E_SVCCATE S;
        public static final E_SVCCATE F;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SVCCATE $VALUES[];

        static 
        {
            T = new E_SVCCATE("T", 0, "T", "T", "\u6D41\u7A0B\u4EA4\u6613");
            S = new E_SVCCATE("S", 1, "S", "S", "\u4E1A\u52A1\u670D\u52A1");
            F = new E_SVCCATE("F", 2, "F", "F", "\u6D41\u7A0B\u7F16\u6392");
            $VALUES = (new E_SVCCATE[] {
                T, S, F
            });
        }

        private E_SVCCATE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_SVCCATE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_YESORNO extends Enum
        implements DefaultEnum
    {

        public static E_YESORNO[] values()
        {
            return (E_YESORNO[])$VALUES.clone();
        }

        public static E_YESORNO valueOf(String name)
        {
            return (E_YESORNO)Enum.valueOf(cn/sunline/adp/cedar/base/type/SPEnumType$E_YESORNO, name);
        }

        public String getId()
        {
            return __id__;
        }

        public Integer getValue()
        {
            return __value__;
        }

        public String getLocalLongName()
        {
            return (new IString(KEY, __longName__)).getLocalString();
        }

        public String getLongName()
        {
            return (new IString(KEY, __longName__)).getString();
        }

        public String toString()
        {
            return String.valueOf(__value__);
        }

        public static boolean isIn(Object value)
        {
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_YESORNO, value);
        }

        public static E_YESORNO get(Object value)
        {
            return (E_YESORNO)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/SPEnumType$E_YESORNO, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_YESORNO YES;
        public static final E_YESORNO NO;
        private String __id__;
        private Integer __value__;
        private String __longName__;
        private final String KEY;
        private static final E_YESORNO $VALUES[];

        static 
        {
            YES = new E_YESORNO("YES", 0, "YES", Integer.valueOf(1), "\u662F");
            NO = new E_YESORNO("NO", 1, "NO", Integer.valueOf(0), "\u5426");
            $VALUES = (new E_YESORNO[] {
                YES, NO
            });
        }

        private E_YESORNO(String s, int i, String __id__, Integer __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("SPEnumType.E_YESORNO.").append(__id__).append(".longname").toString();
        }
    }

}
