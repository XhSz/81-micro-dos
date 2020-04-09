// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtDataSource.java

package cn.sunline.adp.cedar.busi.sdk.report;

import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.*;

public class ExtDataSource
    implements JRDataSource
{

    public ExtDataSource(List list)
    {
        it = list.iterator();
    }

    public Object getFieldValue(JRField arg0)
        throws JRException
    {
        return it.next();
    }

    public boolean next()
        throws JRException
    {
        return it.hasNext();
    }

    private Iterator it;
}
