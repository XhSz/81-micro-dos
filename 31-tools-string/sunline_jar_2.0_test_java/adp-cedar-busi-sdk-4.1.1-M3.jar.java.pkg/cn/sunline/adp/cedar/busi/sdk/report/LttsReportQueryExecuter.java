// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsReportQueryExecuter.java

package cn.sunline.adp.cedar.busi.sdk.report;

import cn.sunline.adp.core.exception.AdpException;
import cn.sunline.adp.metadata.base.dao.*;
import cn.sunline.adp.metadata.base.util.EdspCoreBeanUtil;
import java.util.*;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.report:
//            LttsReportDataProcessor

public class LttsReportQueryExecuter
{

    public LttsReportQueryExecuter(String namedSqlId, Map param)
    {
        this.namedSqlId = namedSqlId;
        this.param = param;
    }

    public List fetchNextPageData(int start, int pageSize)
    {
        final List nextPageList = new ArrayList();
        dao.querySqlList(namedSqlId, param, start, pageSize, new CursorHandler() {

            public boolean handle(int index, Object entity)
            {
                try
                {
                    boolean status = processor.processSingleData(index, entity);
                    if(status)
                        nextPageList.add(entity);
                    return true;
                }
                catch(Exception e)
                {
                    throw getLTTSException(e);
                }
            }

            final List val$nextPageList;
            final LttsReportQueryExecuter this$0;

            
            {
                this.this$0 = LttsReportQueryExecuter.this;
                nextPageList = list;
                super();
            }
        }
);
        return nextPageList;
    }

    public void setReportDataProcessor(LttsReportDataProcessor processor)
    {
        this.processor = processor;
    }

    private AdpException getLTTSException(Throwable t)
    {
        if(t == null)
            return null;
        if(t instanceof AdpException)
            return (AdpException)t;
        else
            return getLTTSException(t.getCause());
    }

    private static CommonDao dao = EdspCoreBeanUtil.getCommonDaoFactory().getInstance();
    private String namedSqlId;
    private Map param;
    private LttsReportDataProcessor processor;



}
