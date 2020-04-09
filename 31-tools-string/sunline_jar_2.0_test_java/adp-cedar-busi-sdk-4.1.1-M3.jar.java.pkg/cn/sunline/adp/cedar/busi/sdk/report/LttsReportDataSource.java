// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LttsReportDataSource.java

package cn.sunline.adp.cedar.busi.sdk.report;

import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.*;

// Referenced classes of package cn.sunline.adp.cedar.busi.sdk.report:
//            LttsReportQueryExecuter

public class LttsReportDataSource
    implements JRDataSource
{

    public LttsReportDataSource(LttsReportQueryExecuter executor)
    {
        pageSize = 100;
        this.executor = executor;
        pageCount = 0;
        fetchPage();
    }

    public boolean next()
    {
        if(iterator == null)
            return false;
        boolean hasNext = iterator.hasNext();
        if(!hasNext && nextPage)
        {
            fetchPage();
            hasNext = iterator != null && iterator.hasNext();
        }
        if(hasNext)
            setCurrentRowValue(iterator.next());
        return hasNext;
    }

    public boolean hasNext()
    {
        if(iterator == null)
            return false;
        boolean hasNext = iterator.hasNext();
        if(!hasNext && nextPage)
        {
            returnValues = executor.fetchNextPageData(pageCount * pageSize, pageSize);
            if(returnValues.size() > 0)
                return true;
            hasNext = returnValues.size() > 0;
        }
        return hasNext;
    }

    public Object getFieldValue(JRField jrField)
        throws JRException
    {
        return getCurrentRowValue();
    }

    protected void fetchPage()
    {
        returnValues = executor.fetchNextPageData(pageCount * pageSize, pageSize);
        nextPage = returnValues.size() == pageSize;
        pageCount++;
        initIterator();
    }

    private void initIterator()
    {
        iterator = returnValues != null ? returnValues.iterator() : null;
    }

    public Object getCurrentRowValue()
    {
        return currentRowValue;
    }

    public void setCurrentRowValue(Object currentRowValue)
    {
        this.currentRowValue = currentRowValue;
    }

    private int pageSize;
    private int pageCount;
    private boolean nextPage;
    private Iterator iterator;
    private Object currentRowValue;
    private List returnValues;
    private LttsReportQueryExecuter executor;
}
