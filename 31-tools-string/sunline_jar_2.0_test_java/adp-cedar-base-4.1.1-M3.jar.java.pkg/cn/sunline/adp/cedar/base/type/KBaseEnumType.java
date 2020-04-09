// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KBaseEnumType.java

package cn.sunline.adp.cedar.base.type;

import cn.sunline.adp.core.lang.IString;
import cn.sunline.edsp.base.lang.DefaultEnum;
import cn.sunline.edsp.base.util.convert.EnumUtils;

public interface KBaseEnumType
{
    public static final class E_RESET_MODE extends Enum
        implements DefaultEnum
    {

        public static E_RESET_MODE[] values()
        {
            return (E_RESET_MODE[])$VALUES.clone();
        }

        public static E_RESET_MODE valueOf(String name)
        {
            return (E_RESET_MODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RESET_MODE, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RESET_MODE, value);
        }

        public static E_RESET_MODE get(Object value)
        {
            return (E_RESET_MODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RESET_MODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_RESET_MODE None;
        public static final E_RESET_MODE Daily;
        public static final E_RESET_MODE Monthly;
        public static final E_RESET_MODE Yearly;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_RESET_MODE $VALUES[];

        static 
        {
            None = new E_RESET_MODE("None", 0, "None", "N", "\u4E0D\u590D\u4F4D");
            Daily = new E_RESET_MODE("Daily", 1, "Daily", "D", "\u6309\u65E5\u590D\u4F4D");
            Monthly = new E_RESET_MODE("Monthly", 2, "Monthly", "M", "\u6309\u6708\u590D\u4F4D");
            Yearly = new E_RESET_MODE("Yearly", 3, "Yearly", "Y", "\u6309\u5E74\u590D\u4F4D");
            $VALUES = (new E_RESET_MODE[] {
                None, Daily, Monthly, Yearly
            });
        }

        private E_RESET_MODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_RESET_MODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_BATCHTRANRUNMODE extends Enum
        implements DefaultEnum
    {

        public static E_BATCHTRANRUNMODE[] values()
        {
            return (E_BATCHTRANRUNMODE[])$VALUES.clone();
        }

        public static E_BATCHTRANRUNMODE valueOf(String name)
        {
            return (E_BATCHTRANRUNMODE)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_BATCHTRANRUNMODE, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_BATCHTRANRUNMODE, value);
        }

        public static E_BATCHTRANRUNMODE get(Object value)
        {
            return (E_BATCHTRANRUNMODE)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_BATCHTRANRUNMODE, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_BATCHTRANRUNMODE two_process;
        public static final E_BATCHTRANRUNMODE default_;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_BATCHTRANRUNMODE $VALUES[];

        static 
        {
            two_process = new E_BATCHTRANRUNMODE("two_process", 0, "two_process", "1", "\u4E8C\u6B21\u5904\u7406\u65B9\u5F0F");
            default_ = new E_BATCHTRANRUNMODE("default_", 1, "default_", "0", "\u7F3A\u7701\u6A21\u5F0F");
            $VALUES = (new E_BATCHTRANRUNMODE[] {
                two_process, default_
            });
        }

        private E_BATCHTRANRUNMODE(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_BATCHTRANRUNMODE.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_LIUSZCZT extends Enum
        implements DefaultEnum
    {

        public static E_LIUSZCZT[] values()
        {
            return (E_LIUSZCZT[])$VALUES.clone();
        }

        public static E_LIUSZCZT valueOf(String name)
        {
            return (E_LIUSZCZT)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_LIUSZCZT, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_LIUSZCZT, value);
        }

        public static E_LIUSZCZT get(Object value)
        {
            return (E_LIUSZCZT)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_LIUSZCZT, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_LIUSZCZT using;
        public static final E_LIUSZCZT hang;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_LIUSZCZT $VALUES[];

        static 
        {
            using = new E_LIUSZCZT("using", 0, "using", "using", "\u5728\u4F7F\u7528");
            hang = new E_LIUSZCZT("hang", 1, "hang", "hang", "\u6302\u8D77");
            $VALUES = (new E_LIUSZCZT[] {
                using, hang
            });
        }

        private E_LIUSZCZT(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_LIUSZCZT.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_WENJPLZT extends Enum
        implements DefaultEnum
    {

        public static E_WENJPLZT[] values()
        {
            return (E_WENJPLZT[])$VALUES.clone();
        }

        public static E_WENJPLZT valueOf(String name)
        {
            return (E_WENJPLZT)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_WENJPLZT, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_WENJPLZT, value);
        }

        public static E_WENJPLZT get(Object value)
        {
            return (E_WENJPLZT)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_WENJPLZT, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_WENJPLZT onprocess;
        public static final E_WENJPLZT processing;
        public static final E_WENJPLZT failure;
        public static final E_WENJPLZT success;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_WENJPLZT $VALUES[];

        static 
        {
            onprocess = new E_WENJPLZT("onprocess", 0, "onprocess", "0", "\u6392\u961F\u4E2D");
            processing = new E_WENJPLZT("processing", 1, "processing", "0", "\u6B63\u5728\u6267\u884C");
            failure = new E_WENJPLZT("failure", 2, "failure", "0", "\u5931\u8D25");
            success = new E_WENJPLZT("success", 3, "success", "0", "\u6210\u529F");
            $VALUES = (new E_WENJPLZT[] {
                onprocess, processing, failure, success
            });
        }

        private E_WENJPLZT(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_WENJPLZT.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_JIAOYILX extends Enum
        implements DefaultEnum
    {

        public static E_JIAOYILX[] values()
        {
            return (E_JIAOYILX[])$VALUES.clone();
        }

        public static E_JIAOYILX valueOf(String name)
        {
            return (E_JIAOYILX)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIAOYILX, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIAOYILX, value);
        }

        public static E_JIAOYILX get(Object value)
        {
            return (E_JIAOYILX)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIAOYILX, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_JIAOYILX CXJY;
        public static final E_JIAOYILX JRJY;
        public static final E_JIAOYILX WHJY;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_JIAOYILX $VALUES[];

        static 
        {
            CXJY = new E_JIAOYILX("CXJY", 0, "CXJY", "0", "\u67E5\u8BE2\u7C7B\u4EA4\u6613");
            JRJY = new E_JIAOYILX("JRJY", 1, "JRJY", "1", "\u91D1\u878D\u7C7B\u4EA4\u6613");
            WHJY = new E_JIAOYILX("WHJY", 2, "WHJY", "2", "\u7EF4\u62A4\u7C7B\u4EA4\u6613");
            $VALUES = (new E_JIAOYILX[] {
                CXJY, JRJY, WHJY
            });
        }

        private E_JIAOYILX(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_JIAOYILX.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_GUIYSHUX extends Enum
        implements DefaultEnum
    {

        public static E_GUIYSHUX[] values()
        {
            return (E_GUIYSHUX[])$VALUES.clone();
        }

        public static E_GUIYSHUX valueOf(String name)
        {
            return (E_GUIYSHUX)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_GUIYSHUX, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_GUIYSHUX, value);
        }

        public static E_GUIYSHUX get(Object value)
        {
            return (E_GUIYSHUX)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_GUIYSHUX, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_GUIYSHUX GM;
        public static final E_GUIYSHUX ATM;
        public static final E_GUIYSHUX JKATM;
        public static final E_GUIYSHUX POS;
        public static final E_GUIYSHUX JKPOA;
        public static final E_GUIYSHUX DH;
        public static final E_GUIYSHUX WY;
        public static final E_GUIYSHUX ZJYW;
        public static final E_GUIYSHUX QT;
        public static final E_GUIYSHUX PCL;
        public static final E_GUIYSHUX XE;
        public static final E_GUIYSHUX DE;
        public static final E_GUIYSHUX LJ;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_GUIYSHUX $VALUES[];

        static 
        {
            GM = new E_GUIYSHUX("GM", 0, "GM", "0", "\u67DC\u9762\u67DC\u5458");
            ATM = new E_GUIYSHUX("ATM", 1, "ATM", "1", "ATM\u865A\u62DF\u67DC\u5458");
            JKATM = new E_GUIYSHUX("JKATM", 2, "JKATM", "2", "\u91D1\u5361ATM\u865A\u62DF\u67DC\u5458");
            POS = new E_GUIYSHUX("POS", 3, "POS", "3", "POS\u865A\u62DF\u67DC\u5458");
            JKPOA = new E_GUIYSHUX("JKPOA", 4, "JKPOA", "4", "\u91D1\u5361POS\u865A\u62DF\u67DC\u5458");
            DH = new E_GUIYSHUX("DH", 5, "DH", "5", "\u7535\u8BDD\u94F6\u884C\u865A\u62DF\u67DC\u5458");
            WY = new E_GUIYSHUX("WY", 6, "WY", "6", "\u7F51\u94F6\u865A\u62DF\u67DC\u5458");
            ZJYW = new E_GUIYSHUX("ZJYW", 7, "ZJYW", "7", "\u4E2D\u95F4\u4E1A\u52A1\u865A\u62DF\u67DC\u5458");
            QT = new E_GUIYSHUX("QT", 8, "QT", "9", "\u5176\u5B83\u865A\u62DF\u67DC\u5458");
            PCL = new E_GUIYSHUX("PCL", 9, "PCL", "a", "\u6279\u5904\u7406\u67DC\u5458");
            XE = new E_GUIYSHUX("XE", 10, "XE", "b", "\u5C0F\u989D\u865A\u62DF\u67DC\u5458");
            DE = new E_GUIYSHUX("DE", 11, "DE", "c", "\u5927\u989D\u6765\u8D26\u8BB0\u8D26\u67DC\u5458");
            LJ = new E_GUIYSHUX("LJ", 12, "LJ", "d", "\u8054\u673A\u6279\u91CF\u67DC\u5458");
            $VALUES = (new E_GUIYSHUX[] {
                GM, ATM, JKATM, POS, JKPOA, DH, WY, ZJYW, QT, PCL, 
                XE, DE, LJ
            });
        }

        private E_GUIYSHUX(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_GUIYSHUX.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_XITONGBS extends Enum
        implements DefaultEnum
    {

        public static E_XITONGBS[] values()
        {
            return (E_XITONGBS[])$VALUES.clone();
        }

        public static E_XITONGBS valueOf(String name)
        {
            return (E_XITONGBS)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGBS, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGBS, value);
        }

        public static E_XITONGBS get(Object value)
        {
            return (E_XITONGBS)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGBS, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_XITONGBS GM;
        public static final E_XITONGBS DE;
        public static final E_XITONGBS XE;
        public static final E_XITONGBS LC;
        public static final E_XITONGBS GZ;
        public static final E_XITONGBS CAIWU;
        public static final E_XITONGBS TP;
        public static final E_XITONGBS YL;
        public static final E_XITONGBS ATM;
        public static final E_XITONGBS WB;
        public static final E_XITONGBS SW;
        public static final E_XITONGBS HB;
        public static final E_XITONGBS KJ;
        public static final E_XITONGBS XGUAN;
        public static final E_XITONGBS DH;
        public static final E_XITONGBS DX;
        public static final E_XITONGBS WY;
        public static final E_XITONGBS MM;
        public static final E_XITONGBS JJ;
        public static final E_XITONGBS XY;
        public static final E_XITONGBS POS;
        public static final E_XITONGBS QZ;
        public static final E_XITONGBS GJ;
        public static final E_XITONGBS YY;
        public static final E_XITONGBS YLQZ;
        public static final E_XITONGBS ZJYW;
        public static final E_XITONGBS PJXT;
        public static final E_XITONGBS SFHC;
        public static final E_XITONGBS SJYH;
        public static final E_XITONGBS WXYH;
        public static final E_XITONGBS JZSQ;
        public static final E_XITONGBS HEXIN;
        public static final E_XITONGBS JYPL;
        public static final E_XITONGBS KJPL;
        public static final E_XITONGBS ZONGZH;
        public static final E_XITONGBS FGM;
        public static final E_XITONGBS QB;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_XITONGBS $VALUES[];

        static 
        {
            GM = new E_XITONGBS("GM", 0, "GM", "000", "\u67DC\u9762");
            DE = new E_XITONGBS("DE", 1, "DE", "001", "\u4E8C\u4EE3\u652F\u4ED8");
            XE = new E_XITONGBS("XE", 2, "XE", "002", "\u519C\u4FE1\u94F6");
            LC = new E_XITONGBS("LC", 3, "LC", "003", "\u7406\u8D22");
            GZ = new E_XITONGBS("GZ", 4, "GZ", "004", "\u7535\u5B50\u56FD\u503A");
            CAIWU = new E_XITONGBS("CAIWU", 5, "CAIWU", "005", "\u8D22\u52A1\u7CFB\u7EDF");
            TP = new E_XITONGBS("TP", 6, "TP", "006", "tips");
            YL = new E_XITONGBS("YL", 7, "YL", "007", "\u94F6\u8054\u603B\u4E2D\u5FC3");
            ATM = new E_XITONGBS("ATM", 8, "ATM", "008", "\u672C\u884CATM");
            WB = new E_XITONGBS("WB", 9, "WB", "009", "\u5883\u5185\u5916\u5E01\u652F\u4ED8");
            SW = new E_XITONGBS("SW", 10, "SW", "010", "swift");
            HB = new E_XITONGBS("HB", 11, "HB", "011", "\u6CB3\u5317\u94F6\u8054");
            KJ = new E_XITONGBS("KJ", 12, "KJ", "012", "\u4F1A\u8BA1\u7CFB\u7EDF");
            XGUAN = new E_XITONGBS("XGUAN", 13, "XGUAN", "013", "\u4FE1\u8D37\u7CFB\u7EDF");
            DH = new E_XITONGBS("DH", 14, "DH", "015", "\u7535\u8BDD\u94F6\u884C");
            DX = new E_XITONGBS("DX", 15, "DX", "016", "\u77ED\u4FE1\u5E73\u53F0");
            WY = new E_XITONGBS("WY", 16, "WY", "017", "\u7F51\u4E0A\u94F6\u884C");
            MM = new E_XITONGBS("MM", 17, "MM", "018", "\u652F\u4ED8\u5BC6\u7801\u6838\u9A8C\u7CFB\u7EDF");
            JJ = new E_XITONGBS("JJ", 18, "JJ", "019", "\u57FA\u91D1");
            XY = new E_XITONGBS("XY", 19, "XY", "020", "\u6821\u56ED\u5361");
            POS = new E_XITONGBS("POS", 20, "POS", "022", "\u672C\u884CPOS");
            QZ = new E_XITONGBS("QZ", 21, "QZ", "023", "\u5927\u524D\u7F6E");
            GJ = new E_XITONGBS("GJ", 22, "GJ", "024", "\u56FD\u9645\u7ED3\u7B97");
            YY = new E_XITONGBS("YY", 23, "YY", "025", "\u9A8C\u5370\u7CFB\u7EDF");
            YLQZ = new E_XITONGBS("YLQZ", 24, "YLQZ", "026", "\u94F6\u8054\u524D\u7F6E");
            ZJYW = new E_XITONGBS("ZJYW", 25, "ZJYW", "027", "\u4E2D\u95F4\u4E1A\u52A1");
            PJXT = new E_XITONGBS("PJXT", 26, "PJXT", "028", "\u7968\u636E\u7CFB\u7EDF");
            SFHC = new E_XITONGBS("SFHC", 27, "SFHC", "029", "\u8EAB\u4EFD\u6838\u67E5");
            SJYH = new E_XITONGBS("SJYH", 28, "SJYH", "030", "\u624B\u673A\u94F6\u884C");
            WXYH = new E_XITONGBS("WXYH", 29, "WXYH", "031", "\u5FAE\u4FE1\u94F6\u884C");
            JZSQ = new E_XITONGBS("JZSQ", 30, "JZSQ", "032", "\u96C6\u4E2D\u6388\u6743");
            HEXIN = new E_XITONGBS("HEXIN", 31, "HEXIN", "800", "\u6838\u5FC3\u7CFB\u7EDF");
            JYPL = new E_XITONGBS("JYPL", 32, "JYPL", "990", "\u4EA4\u6613\u6279\u91CF");
            KJPL = new E_XITONGBS("KJPL", 33, "KJPL", "992", "\u4F1A\u8BA1\u6279\u91CF");
            ZONGZH = new E_XITONGBS("ZONGZH", 34, "ZONGZH", "997", "\u603B\u8D26");
            FGM = new E_XITONGBS("FGM", 35, "FGM", "998", "\u975E\u67DC\u9762\u6E20\u9053");
            QB = new E_XITONGBS("QB", 36, "QB", "999", "\u5168\u90E8");
            $VALUES = (new E_XITONGBS[] {
                GM, DE, XE, LC, GZ, CAIWU, TP, YL, ATM, WB, 
                SW, HB, KJ, XGUAN, DH, DX, WY, MM, JJ, XY, 
                POS, QZ, GJ, YY, YLQZ, ZJYW, PJXT, SFHC, SJYH, WXYH, 
                JZSQ, HEXIN, JYPL, KJPL, ZONGZH, FGM, QB
            });
        }

        private E_XITONGBS(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_XITONGBS.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SYSTEM extends Enum
        implements DefaultEnum
    {

        public static E_SYSTEM[] values()
        {
            return (E_SYSTEM[])$VALUES.clone();
        }

        public static E_SYSTEM valueOf(String name)
        {
            return (E_SYSTEM)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SYSTEM, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SYSTEM, value);
        }

        public static E_SYSTEM get(Object value)
        {
            return (E_SYSTEM)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SYSTEM, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SYSTEM hx_dayend;
        public static final E_SYSTEM gl_dayend;
        public static final E_SYSTEM hx_trans_chk;
        public static final E_SYSTEM hx_trans_pro;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SYSTEM $VALUES[];

        static 
        {
            hx_dayend = new E_SYSTEM("hx_dayend", 0, "hx_dayend", "hx_dayend", "\u6838\u5FC3\u7CFB\u7EDF");
            gl_dayend = new E_SYSTEM("gl_dayend", 1, "gl_dayend", "gl_dayend", "\u4F1A\u8BA1\u7CFB\u7EDF");
            hx_trans_chk = new E_SYSTEM("hx_trans_chk", 2, "hx_trans_chk", "hx_trans_chk", "\u6570\u636E\u79FB\u690D\u68C0\u67E5\u6D41\u7A0B");
            hx_trans_pro = new E_SYSTEM("hx_trans_pro", 3, "hx_trans_pro", "hx_trans_pro", "\u6570\u636E\u79FB\u690D\u5904\u7406\u6D41\u7A0B");
            $VALUES = (new E_SYSTEM[] {
                hx_dayend, gl_dayend, hx_trans_chk, hx_trans_pro
            });
        }

        private E_SYSTEM(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_SYSTEM.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_JGRJIEBZ extends Enum
        implements DefaultEnum
    {

        public static E_JGRJIEBZ[] values()
        {
            return (E_JGRJIEBZ[])$VALUES.clone();
        }

        public static E_JGRJIEBZ valueOf(String name)
        {
            return (E_JGRJIEBZ)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JGRJIEBZ, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JGRJIEBZ, value);
        }

        public static E_JGRJIEBZ get(Object value)
        {
            return (E_JGRJIEBZ)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JGRJIEBZ, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_JGRJIEBZ RJ;
        public static final E_JGRJIEBZ YY;
        public static final E_JGRJIEBZ QZ;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_JGRJIEBZ $VALUES[];

        static 
        {
            RJ = new E_JGRJIEBZ("RJ", 0, "RJ", "0", "\u65E5\u7ED3");
            YY = new E_JGRJIEBZ("YY", 1, "YY", "1", "\u672A\u65E5\u7ED3");
            QZ = new E_JGRJIEBZ("QZ", 2, "QZ", "2", "\u5F3A\u5236\u65E5\u7ED3");
            $VALUES = (new E_JGRJIEBZ[] {
                RJ, YY, QZ
            });
        }

        private E_JGRJIEBZ(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_JGRJIEBZ.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_JIGKJBZH extends Enum
        implements DefaultEnum
    {

        public static E_JIGKJBZH[] values()
        {
            return (E_JIGKJBZH[])$VALUES.clone();
        }

        public static E_JIGKJBZH valueOf(String name)
        {
            return (E_JIGKJBZH)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIGKJBZH, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIGKJBZH, value);
        }

        public static E_JIGKJBZH get(Object value)
        {
            return (E_JIGKJBZH)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JIGKJBZH, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_JIGKJBZH ZB;
        public static final E_JIGKJBZH YK;
        public static final E_JIGKJBZH GJ;
        public static final E_JIGKJBZH SQKJ;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_JIGKJBZH $VALUES[];

        static 
        {
            ZB = new E_JIGKJBZH("ZB", 0, "ZB", "0", "\u65E5\u521D\u51C6\u5907");
            YK = new E_JIGKJBZH("YK", 1, "YK", "1", "\u5DF2\u5F00\u673A");
            GJ = new E_JIGKJBZH("GJ", 2, "GJ", "2", "\u5DF2\u5173\u673A");
            SQKJ = new E_JIGKJBZH("SQKJ", 3, "SQKJ", "B", "\u603B\u884C\u6388\u6743\u5F00\u673A");
            $VALUES = (new E_JIGKJBZH[] {
                ZB, YK, GJ, SQKJ
            });
        }

        private E_JIGKJBZH(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_JIGKJBZH.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_QDAOLEIX extends Enum
        implements DefaultEnum
    {

        public static E_QDAOLEIX[] values()
        {
            return (E_QDAOLEIX[])$VALUES.clone();
        }

        public static E_QDAOLEIX valueOf(String name)
        {
            return (E_QDAOLEIX)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_QDAOLEIX, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_QDAOLEIX, value);
        }

        public static E_QDAOLEIX get(Object value)
        {
            return (E_QDAOLEIX)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_QDAOLEIX, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_QDAOLEIX GM;
        public static final E_QDAOLEIX DE;
        public static final E_QDAOLEIX XE;
        public static final E_QDAOLEIX LC;
        public static final E_QDAOLEIX GZ;
        public static final E_QDAOLEIX CW;
        public static final E_QDAOLEIX TP;
        public static final E_QDAOLEIX YL;
        public static final E_QDAOLEIX ATM;
        public static final E_QDAOLEIX WB;
        public static final E_QDAOLEIX SW;
        public static final E_QDAOLEIX HB;
        public static final E_QDAOLEIX KJ;
        public static final E_QDAOLEIX XD;
        public static final E_QDAOLEIX DH;
        public static final E_QDAOLEIX DX;
        public static final E_QDAOLEIX WY;
        public static final E_QDAOLEIX MM;
        public static final E_QDAOLEIX JJ;
        public static final E_QDAOLEIX XY;
        public static final E_QDAOLEIX POS;
        public static final E_QDAOLEIX QZ;
        public static final E_QDAOLEIX GJ;
        public static final E_QDAOLEIX YY;
        public static final E_QDAOLEIX JYPL;
        public static final E_QDAOLEIX KJPL;
        public static final E_QDAOLEIX FGM;
        public static final E_QDAOLEIX QB;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_QDAOLEIX $VALUES[];

        static 
        {
            GM = new E_QDAOLEIX("GM", 0, "GM", "000", "\u67DC\u9762");
            DE = new E_QDAOLEIX("DE", 1, "DE", "001", "\u5927\u989D");
            XE = new E_QDAOLEIX("XE", 2, "XE", "002", "\u5C0F\u989D");
            LC = new E_QDAOLEIX("LC", 3, "LC", "003", "\u7406\u8D22");
            GZ = new E_QDAOLEIX("GZ", 4, "GZ", "004", "\u7535\u5B50\u56FD\u503A");
            CW = new E_QDAOLEIX("CW", 5, "CW", "005", "\u8D22\u52A1\u7CFB\u7EDF");
            TP = new E_QDAOLEIX("TP", 6, "TP", "006", "tips");
            YL = new E_QDAOLEIX("YL", 7, "YL", "007", "\u94F6\u8054\u603B\u4E2D\u5FC3");
            ATM = new E_QDAOLEIX("ATM", 8, "ATM", "008", "\u672C\u884CATM");
            WB = new E_QDAOLEIX("WB", 9, "WB", "009", "\u5883\u5185\u5916\u5E01\u652F\u4ED8");
            SW = new E_QDAOLEIX("SW", 10, "SW", "010", "swift");
            HB = new E_QDAOLEIX("HB", 11, "HB", "011", "\u6CB3\u5317\u94F6\u8054");
            KJ = new E_QDAOLEIX("KJ", 12, "KJ", "012", "\u4F1A\u8BA1\u7CFB\u7EDF");
            XD = new E_QDAOLEIX("XD", 13, "XD", "013", "\u4FE1\u8D37\u7CFB\u7EDF");
            DH = new E_QDAOLEIX("DH", 14, "DH", "015", "\u7535\u8BDD\u94F6\u884C");
            DX = new E_QDAOLEIX("DX", 15, "DX", "016", "\u77ED\u4FE1\u5E73\u53F0");
            WY = new E_QDAOLEIX("WY", 16, "WY", "017", "\u7F51\u4E0A\u94F6\u884C");
            MM = new E_QDAOLEIX("MM", 17, "MM", "018", "\u652F\u4ED8\u5BC6\u7801\u6838\u9A8C\u7CFB\u7EDF");
            JJ = new E_QDAOLEIX("JJ", 18, "JJ", "019", "\u57FA\u91D1");
            XY = new E_QDAOLEIX("XY", 19, "XY", "020", "\u6821\u56ED\u5361");
            POS = new E_QDAOLEIX("POS", 20, "POS", "022", "\u672C\u884CPOS");
            QZ = new E_QDAOLEIX("QZ", 21, "QZ", "023", "\u5927\u524D\u7F6E");
            GJ = new E_QDAOLEIX("GJ", 22, "GJ", "024", "\u56FD\u9645\u7ED3\u7B97");
            YY = new E_QDAOLEIX("YY", 23, "YY", "025", "\u9A8C\u5370\u7CFB\u7EDF");
            JYPL = new E_QDAOLEIX("JYPL", 24, "JYPL", "990", "\u4EA4\u6613\u6279\u91CF");
            KJPL = new E_QDAOLEIX("KJPL", 25, "KJPL", "992", "\u4F1A\u8BA1\u6279\u91CF");
            FGM = new E_QDAOLEIX("FGM", 26, "FGM", "998", "\u975E\u67DC\u9762\u6E20\u9053");
            QB = new E_QDAOLEIX("QB", 27, "QB", "999", "\u5168\u90E8");
            $VALUES = (new E_QDAOLEIX[] {
                GM, DE, XE, LC, GZ, CW, TP, YL, ATM, WB, 
                SW, HB, KJ, XD, DH, DX, WY, MM, JJ, XY, 
                POS, QZ, GJ, YY, JYPL, KJPL, FGM, QB
            });
        }

        private E_QDAOLEIX(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_QDAOLEIX.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_HUANCULX extends Enum
        implements DefaultEnum
    {

        public static E_HUANCULX[] values()
        {
            return (E_HUANCULX[])$VALUES.clone();
        }

        public static E_HUANCULX valueOf(String name)
        {
            return (E_HUANCULX)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_HUANCULX, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_HUANCULX, value);
        }

        public static E_HUANCULX get(Object value)
        {
            return (E_HUANCULX)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_HUANCULX, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_HUANCULX none;
        public static final E_HUANCULX trans;
        public static final E_HUANCULX global;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_HUANCULX $VALUES[];

        static 
        {
            none = new E_HUANCULX("none", 0, "none", "none", "\u65E0\u7F13\u5B58");
            trans = new E_HUANCULX("trans", 1, "trans", "trans", "\u4EA4\u6613\u7EA7\u7F13\u5B58");
            global = new E_HUANCULX("global", 2, "global", "global", "\u5168\u5C40\u7F13\u5B58");
            $VALUES = (new E_HUANCULX[] {
                none, trans, global
            });
        }

        private E_HUANCULX(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_HUANCULX.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_ZUOYZXZT extends Enum
        implements DefaultEnum
    {

        public static E_ZUOYZXZT[] values()
        {
            return (E_ZUOYZXZT[])$VALUES.clone();
        }

        public static E_ZUOYZXZT valueOf(String name)
        {
            return (E_ZUOYZXZT)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZUOYZXZT, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZUOYZXZT, value);
        }

        public static E_ZUOYZXZT get(Object value)
        {
            return (E_ZUOYZXZT)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZUOYZXZT, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_ZUOYZXZT onprocess;
        public static final E_ZUOYZXZT reonprocess;
        public static final E_ZUOYZXZT processing;
        public static final E_ZUOYZXZT one_commit;
        public static final E_ZUOYZXZT success;
        public static final E_ZUOYZXZT failure;
        public static final E_ZUOYZXZT interrupted;
        public static final E_ZUOYZXZT stopped;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_ZUOYZXZT $VALUES[];

        static 
        {
            onprocess = new E_ZUOYZXZT("onprocess", 0, "onprocess", "onprocess", "\u5F85\u5904\u7406");
            reonprocess = new E_ZUOYZXZT("reonprocess", 1, "reonprocess", "reonprocess", "\u6682\u505C\u5E76\u91CD\u65B0\u5C31\u7EEA");
            processing = new E_ZUOYZXZT("processing", 2, "processing", "processing", "\u5904\u7406\u4E2D");
            one_commit = new E_ZUOYZXZT("one_commit", 3, "one_commit", "one_commit", "\u4E00\u6B21\u5904\u7406\u7ED3\u675F\uFF0C\u7B49\u5F85\u5F02\u6B65\u4E8B\u4EF6\u89E6\u53D1\u4E8C\u6B21\u5904\u7406");
            success = new E_ZUOYZXZT("success", 4, "success", "success", "\u5904\u7406\u6210\u529F");
            failure = new E_ZUOYZXZT("failure", 5, "failure", "failure", "\u5904\u7406\u5931\u8D25");
            interrupted = new E_ZUOYZXZT("interrupted", 6, "interrupted", "interrupted", "\u5DF2\u4E2D\u65AD");
            stopped = new E_ZUOYZXZT("stopped", 7, "stopped", "stopped", "\u5DF2\u505C\u6B62");
            $VALUES = (new E_ZUOYZXZT[] {
                onprocess, reonprocess, processing, one_commit, success, failure, interrupted, stopped
            });
        }

        private E_ZUOYZXZT(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_ZUOYZXZT.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_ZHIXYZHT extends Enum
        implements DefaultEnum
    {

        public static E_ZHIXYZHT[] values()
        {
            return (E_ZHIXYZHT[])$VALUES.clone();
        }

        public static E_ZHIXYZHT valueOf(String name)
        {
            return (E_ZHIXYZHT)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZHIXYZHT, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZHIXYZHT, value);
        }

        public static E_ZHIXYZHT get(Object value)
        {
            return (E_ZHIXYZHT)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_ZHIXYZHT, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_ZHIXYZHT START;
        public static final E_ZHIXYZHT RUNNING;
        public static final E_ZHIXYZHT STOPPING;
        public static final E_ZHIXYZHT STOPPED;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_ZHIXYZHT $VALUES[];

        static 
        {
            START = new E_ZHIXYZHT("START", 0, "START", "START", "\u5F85\u6267\u884C");
            RUNNING = new E_ZHIXYZHT("RUNNING", 1, "RUNNING", "RUNNING", "\u6B63\u5728\u8FD0\u884C");
            STOPPING = new E_ZHIXYZHT("STOPPING", 2, "STOPPING", "STOPPING", "\u5F85\u505C\u6B62");
            STOPPED = new E_ZHIXYZHT("STOPPED", 3, "STOPPED", "STOPPED", "\u5DF2\u505C\u6B62");
            $VALUES = (new E_ZHIXYZHT[] {
                START, RUNNING, STOPPING, STOPPED
            });
        }

        private E_ZHIXYZHT(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_ZHIXYZHT.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_PILJYZHT extends Enum
        implements DefaultEnum
    {

        public static E_PILJYZHT[] values()
        {
            return (E_PILJYZHT[])$VALUES.clone();
        }

        public static E_PILJYZHT valueOf(String name)
        {
            return (E_PILJYZHT)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILJYZHT, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILJYZHT, value);
        }

        public static E_PILJYZHT get(Object value)
        {
            return (E_PILJYZHT)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILJYZHT, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_PILJYZHT onprocess;
        public static final E_PILJYZHT reonprocess;
        public static final E_PILJYZHT distributing;
        public static final E_PILJYZHT processing;
        public static final E_PILJYZHT one_commit;
        public static final E_PILJYZHT success;
        public static final E_PILJYZHT failure;
        public static final E_PILJYZHT interrupted;
        public static final E_PILJYZHT stopped;
        public static final E_PILJYZHT unknown;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_PILJYZHT $VALUES[];

        static 
        {
            onprocess = new E_PILJYZHT("onprocess", 0, "onprocess", "onprocess", "\u5F85\u5904\u7406");
            reonprocess = new E_PILJYZHT("reonprocess", 1, "reonprocess", "reonprocess", "\u6682\u505C\u5E76\u91CD\u65B0\u5C31\u7EEA");
            distributing = new E_PILJYZHT("distributing", 2, "distributing", "distributing", "\u6B63\u5206\u6D3E");
            processing = new E_PILJYZHT("processing", 3, "processing", "processing", "\u5904\u7406\u4E2D");
            one_commit = new E_PILJYZHT("one_commit", 4, "one_commit", "one_commit", "\u4E00\u6B21\u5904\u7406\u7ED3\u675F\uFF0C\u7B49\u5F85\u5F02\u6B65\u4E8B\u4EF6\u89E6\u53D1\u4E8C\u6B21\u5904\u7406");
            success = new E_PILJYZHT("success", 5, "success", "success", "\u5904\u7406\u6210\u529F");
            failure = new E_PILJYZHT("failure", 6, "failure", "failure", "\u5904\u7406\u5931\u8D25");
            interrupted = new E_PILJYZHT("interrupted", 7, "interrupted", "interrupted", "\u5DF2\u4E2D\u65AD");
            stopped = new E_PILJYZHT("stopped", 8, "stopped", "stopped", "\u5DF2\u505C\u6B62");
            unknown = new E_PILJYZHT("unknown", 9, "unknown", "unknown", "\u672A\u77E5");
            $VALUES = (new E_PILJYZHT[] {
                onprocess, reonprocess, distributing, processing, one_commit, success, failure, interrupted, stopped, unknown
            });
        }

        private E_PILJYZHT(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_PILJYZHT.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_DIAODZTA extends Enum
        implements DefaultEnum
    {

        public static E_DIAODZTA[] values()
        {
            return (E_DIAODZTA[])$VALUES.clone();
        }

        public static E_DIAODZTA valueOf(String name)
        {
            return (E_DIAODZTA)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_DIAODZTA, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_DIAODZTA, value);
        }

        public static E_DIAODZTA get(Object value)
        {
            return (E_DIAODZTA)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_DIAODZTA, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_DIAODZTA START;
        public static final E_DIAODZTA SCHEDULING;
        public static final E_DIAODZTA STOPPING;
        public static final E_DIAODZTA STOPPED;
        public static final E_DIAODZTA DELETE;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_DIAODZTA $VALUES[];

        static 
        {
            START = new E_DIAODZTA("START", 0, "START", "START", "\u5F85\u8C03\u5EA6");
            SCHEDULING = new E_DIAODZTA("SCHEDULING", 1, "SCHEDULING", "SCHEDULING", "\u6B63\u5728\u8C03\u5EA6");
            STOPPING = new E_DIAODZTA("STOPPING", 2, "STOPPING", "STOPPING", "\u5F85\u505C\u6B62");
            STOPPED = new E_DIAODZTA("STOPPED", 3, "STOPPED", "STOPPED", "\u5DF2\u505C\u6B62");
            DELETE = new E_DIAODZTA("DELETE", 4, "DELETE", "DELETE", "\u5F85\u5220\u9664");
            $VALUES = (new E_DIAODZTA[] {
                START, SCHEDULING, STOPPING, STOPPED, DELETE
            });
        }

        private E_DIAODZTA(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_DIAODZTA.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_PLJYYXMS extends Enum
        implements DefaultEnum
    {

        public static E_PLJYYXMS[] values()
        {
            return (E_PLJYYXMS[])$VALUES.clone();
        }

        public static E_PLJYYXMS valueOf(String name)
        {
            return (E_PLJYYXMS)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PLJYYXMS, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PLJYYXMS, value);
        }

        public static E_PLJYYXMS get(Object value)
        {
            return (E_PLJYYXMS)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PLJYYXMS, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_PLJYYXMS default_;
        public static final E_PLJYYXMS two_process;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_PLJYYXMS $VALUES[];

        static 
        {
            default_ = new E_PLJYYXMS("default_", 0, "default_", "0", "\u7F3A\u7701\u6A21\u5F0F");
            two_process = new E_PLJYYXMS("two_process", 1, "two_process", "1", "\u4E8C\u6B21\u5904\u7406\u6A21\u5F0F");
            $VALUES = (new E_PLJYYXMS[] {
                default_, two_process
            });
        }

        private E_PLJYYXMS(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_PLJYYXMS.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_RUNMOD extends Enum
        implements DefaultEnum
    {

        public static E_RUNMOD[] values()
        {
            return (E_RUNMOD[])$VALUES.clone();
        }

        public static E_RUNMOD valueOf(String name)
        {
            return (E_RUNMOD)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RUNMOD, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RUNMOD, value);
        }

        public static E_RUNMOD get(Object value)
        {
            return (E_RUNMOD)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_RUNMOD, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_RUNMOD single_thread;
        public static final E_RUNMOD one_for_one;
        public static final E_RUNMOD thread_pool_one_jvm;
        public static final E_RUNMOD thread_pool_multi_jvm;
        public static final E_RUNMOD thread_pool_multi_jvm_by_discovery;
        public static final E_RUNMOD netty_access_multi_jvm;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_RUNMOD $VALUES[];

        static 
        {
            single_thread = new E_RUNMOD("single_thread", 0, "single_thread", "0", "\u5355JVM\u6240\u6709\u4F5C\u4E1A\u5728\u4E3B\u7EBF\u7A0B\u8FD0\u884C");
            one_for_one = new E_RUNMOD("one_for_one", 1, "one_for_one", "1", "\u5355JVM\u6BCF\u4E2A\u4F5C\u4E1A\u521B\u5EFA\u4E00\u4E2A\u7EBF\u7A0B\u8FD0\u884C");
            thread_pool_one_jvm = new E_RUNMOD("thread_pool_one_jvm", 2, "thread_pool_one_jvm", "2", "\u5355JVM\u5171\u4EAB\u4F5C\u4E1A\u7EBF\u7A0B\u6C60\u8FD0\u884C");
            thread_pool_multi_jvm = new E_RUNMOD("thread_pool_multi_jvm", 3, "thread_pool_multi_jvm", "3", "\u591AJVM\u5171\u4EAB\u7EBF\u7A0B\u6C60\u65B9\u5F0F\u8FD0\u884C");
            thread_pool_multi_jvm_by_discovery = new E_RUNMOD("thread_pool_multi_jvm_by_discovery", 4, "thread_pool_multi_jvm_by_discovery", "4", "\u81EA\u52A8\u53D1\u73B0\u591AJVM\u5171\u4EAB\u7EBF\u7A0B\u6C60\u65B9\u5F0F\u8FD0\u884C");
            netty_access_multi_jvm = new E_RUNMOD("netty_access_multi_jvm", 5, "netty_access_multi_jvm", "5", "NIO\u65B9\u5F0F\u63A5\u5165\u591AJVM\u65B9\u5F0F\u8FD0\u884C");
            $VALUES = (new E_RUNMOD[] {
                single_thread, one_for_one, thread_pool_one_jvm, thread_pool_multi_jvm, thread_pool_multi_jvm_by_discovery, netty_access_multi_jvm
            });
        }

        private E_RUNMOD(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_RUNMOD.").append(__id__).append(".longname").toString();
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
            return (E_YESORNO)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_YESORNO, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_YESORNO, value);
        }

        public static E_YESORNO get(Object value)
        {
            return (E_YESORNO)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_YESORNO, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_YESORNO NO;
        public static final E_YESORNO YES;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_YESORNO $VALUES[];

        static 
        {
            NO = new E_YESORNO("NO", 0, "NO", "0", "\u5426");
            YES = new E_YESORNO("YES", 1, "YES", "1", "\u662F");
            $VALUES = (new E_YESORNO[] {
                NO, YES
            });
        }

        private E_YESORNO(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_YESORNO.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_SPLITMOD extends Enum
        implements DefaultEnum
    {

        public static E_SPLITMOD[] values()
        {
            return (E_SPLITMOD[])$VALUES.clone();
        }

        public static E_SPLITMOD valueOf(String name)
        {
            return (E_SPLITMOD)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SPLITMOD, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SPLITMOD, value);
        }

        public static E_SPLITMOD get(Object value)
        {
            return (E_SPLITMOD)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_SPLITMOD, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_SPLITMOD split_by_key;
        public static final E_SPLITMOD split_by_hash_mod;
        public static final E_SPLITMOD no_split;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_SPLITMOD $VALUES[];

        static 
        {
            split_by_key = new E_SPLITMOD("split_by_key", 0, "split_by_key", "0", "\u6309key\u62C6\u5206");
            split_by_hash_mod = new E_SPLITMOD("split_by_hash_mod", 1, "split_by_hash_mod", "1", "\u6309HASH\u6C42\u6A21\u62C6\u5206");
            no_split = new E_SPLITMOD("no_split", 2, "no_split", "9", "\u4E0D\u62C6\u5206");
            $VALUES = (new E_SPLITMOD[] {
                split_by_key, split_by_hash_mod, no_split
            });
        }

        private E_SPLITMOD(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_SPLITMOD.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_JILUZTAI extends Enum
        implements DefaultEnum
    {

        public static E_JILUZTAI[] values()
        {
            return (E_JILUZTAI[])$VALUES.clone();
        }

        public static E_JILUZTAI valueOf(String name)
        {
            return (E_JILUZTAI)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JILUZTAI, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JILUZTAI, value);
        }

        public static E_JILUZTAI get(Object value)
        {
            return (E_JILUZTAI)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_JILUZTAI, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_JILUZTAI Normal;
        public static final E_JILUZTAI Delete;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_JILUZTAI $VALUES[];

        static 
        {
            Normal = new E_JILUZTAI("Normal", 0, "Normal", "0", "\u6B63\u5E38");
            Delete = new E_JILUZTAI("Delete", 1, "Delete", "1", "\u5220\u9664");
            $VALUES = (new E_JILUZTAI[] {
                Normal, Delete
            });
        }

        private E_JILUZTAI(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_JILUZTAI.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_PILZXM0S extends Enum
        implements DefaultEnum
    {

        public static E_PILZXM0S[] values()
        {
            return (E_PILZXM0S[])$VALUES.clone();
        }

        public static E_PILZXM0S valueOf(String name)
        {
            return (E_PILZXM0S)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILZXM0S, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILZXM0S, value);
        }

        public static E_PILZXM0S get(Object value)
        {
            return (E_PILZXM0S)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_PILZXM0S, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_PILZXM0S FLOW;
        public static final E_PILZXM0S FLOW_STEP;
        public static final E_PILZXM0S TRAN_GROUP;
        public static final E_PILZXM0S TRAN;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_PILZXM0S $VALUES[];

        static 
        {
            FLOW = new E_PILZXM0S("FLOW", 0, "FLOW", "1", "\u6309\u6279\u91CF\u4EA4\u6613\u6D41\u7A0B\u6267\u884C");
            FLOW_STEP = new E_PILZXM0S("FLOW_STEP", 1, "FLOW_STEP", "2", "\u6309\u6279\u91CF\u4EA4\u6613\u6D41\u7A0B\u6B65\u9AA4\u6267\u884C");
            TRAN_GROUP = new E_PILZXM0S("TRAN_GROUP", 2, "TRAN_GROUP", "3", "\u6309\u6279\u91CF\u4EA4\u6613\u7EC4\u6267\u884C");
            TRAN = new E_PILZXM0S("TRAN", 3, "TRAN", "4", "\u6309\u6279\u91CF\u4EA4\u6613\u6267\u884C");
            $VALUES = (new E_PILZXM0S[] {
                FLOW, FLOW_STEP, TRAN_GROUP, TRAN
            });
        }

        private E_PILZXM0S(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_PILZXM0S.").append(__id__).append(".longname").toString();
        }
    }

    public static final class E_XITONGLB extends Enum
        implements DefaultEnum
    {

        public static E_XITONGLB[] values()
        {
            return (E_XITONGLB[])$VALUES.clone();
        }

        public static E_XITONGLB valueOf(String name)
        {
            return (E_XITONGLB)Enum.valueOf(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGLB, name);
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
            return EnumUtils.isInEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGLB, value);
        }

        public static E_XITONGLB get(Object value)
        {
            return (E_XITONGLB)EnumUtils.toEnum(cn/sunline/adp/cedar/base/type/KBaseEnumType$E_XITONGLB, value);
        }

        public volatile Object getValue()
        {
            return getValue();
        }

        public static final E_XITONGLB GL;
        public static final E_XITONGLB ONLINE;
        private String __id__;
        private String __value__;
        private String __longName__;
        private final String KEY;
        private static final E_XITONGLB $VALUES[];

        static 
        {
            GL = new E_XITONGLB("GL", 0, "GL", "9", "\u4F1A\u8BA1\u7CFB\u7EDF");
            ONLINE = new E_XITONGLB("ONLINE", 1, "ONLINE", "0", "\u6838\u5FC3\u7CFB\u7EDF");
            $VALUES = (new E_XITONGLB[] {
                GL, ONLINE
            });
        }

        private E_XITONGLB(String s, int i, String __id__, String __value__, String __longName__)
        {
            super(s, i);
            this.__id__ = __id__;
            this.__value__ = __value__;
            this.__longName__ = __longName__;
            KEY = (new StringBuilder()).append("KBaseEnumType.E_XITONGLB.").append(__id__).append(".longname").toString();
        }
    }

}
