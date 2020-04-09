// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponseHeaderData.java

package cn.sunline.adp.cedar.base.engine;

import cn.sunline.adp.cedar.base.StandardHeaderData;

// Referenced classes of package cn.sunline.adp.cedar.base.engine:
//            HeaderDataConstants, MapListDataContext

public class ResponseHeaderData extends cn.sunline.adp.cedar.base.StandardHeaderData.Response
{
    public static final class RetStatus extends Enum
    {

        public static RetStatus[] values()
        {
            return (RetStatus[])$VALUES.clone();
        }

        public static RetStatus valueOf(String name)
        {
            return (RetStatus)Enum.valueOf(cn/sunline/adp/cedar/base/engine/ResponseHeaderData$RetStatus, name);
        }

        public String getValue()
        {
            return value;
        }

        public String getLongname()
        {
            return longname;
        }

        public String toString()
        {
            return value;
        }

        public static RetStatus get(String value)
        {
            RetStatus aretstatus[] = values();
            int i = aretstatus.length;
            for(int j = 0; j < i; j++)
            {
                RetStatus rs = aretstatus[j];
                if(rs.getValue().equals(value))
                    return rs;
            }

            return null;
        }

        public static final RetStatus WAITER;
        public static final RetStatus PROCESS;
        public static final RetStatus SUCCESS;
        public static final RetStatus FAILURE;
        public static final RetStatus INTERRUPTED;
        public static final RetStatus REONPROCESS;
        private String value;
        private String longname;
        private static final RetStatus $VALUES[];

        static 
        {
            WAITER = new RetStatus("WAITER", 0, "W", "\u7B49\u5F85\u5904\u7406\uFF0C\u5F02\u6B65\u573A\u666F\u4F7F\u7528");
            PROCESS = new RetStatus("PROCESS", 1, "P", "\u5904\u7406\u4E2D");
            SUCCESS = new RetStatus("SUCCESS", 2, "S", "\u6210\u529F");
            FAILURE = new RetStatus("FAILURE", 3, "F", "\u5931\u8D25");
            INTERRUPTED = new RetStatus("INTERRUPTED", 4, "I", "\u4E2D\u65AD");
            REONPROCESS = new RetStatus("REONPROCESS", 5, "R", "\u6682\u505C\u5E76\u91CD\u5C31\u7EEA");
            $VALUES = (new RetStatus[] {
                WAITER, PROCESS, SUCCESS, FAILURE, INTERRUPTED, REONPROCESS
            });
        }

        private RetStatus(String s, int i, String value, String longname)
        {
            super(s, i);
            this.value = value;
            this.longname = longname;
        }
    }


    public ResponseHeaderData()
    {
    }

    public ResponseHeaderData(MapListDataContext headerData)
    {
        super(headerData);
    }

    public String getPkgSeqNo()
    {
        return getString(HeaderDataConstants.NAME_PCKGSQ);
    }

    public void setPkgSeqNo(String pkgseqno)
    {
        put(HeaderDataConstants.NAME_PCKGSQ, pkgseqno);
    }

    public Throwable getException()
    {
        return exception;
    }

    public void setException(Throwable exception)
    {
        this.exception = exception;
    }

    private Throwable exception;
}
