// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataArea.java

package cn.sunline.adp.cedar.base.engine.data;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.engine.MapListDataContext;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.util.*;
import org.apache.commons.collections.map.CompositeMap;

public class DataArea
{

    private DataArea()
    {
        inputAndProperty = null;
        data = new LinkedHashMap();
    }

    private DataArea(Map data)
    {
        inputAndProperty = null;
        if(data == null)
        {
            throw ExceptionUtil.wrapThrow(BaseConst.DataArea01, new String[0]);
        } else
        {
            this.data = data;
            return;
        }
    }

    public static DataArea buildWithEmpty()
    {
        return new DataArea();
    }

    public static DataArea buildWithData(Map data)
    {
        return new DataArea(data);
    }

    public static DataArea buildWithInput(Map input)
    {
        return (new DataArea()).setInput(input);
    }

    public static DataArea buildWithOutput(Map output)
    {
        return (new DataArea()).setOutput(output);
    }

    private MapListDataContext getDataObj(String key)
    {
        Object value = data.get(key);
        if(value instanceof MapListDataContext)
            return (MapListDataContext)value;
        if(value != null && !(value instanceof Map))
            throw new IllegalArgumentException(String.format(BaseConst.DataArea02, new Object[] {
                value
            }));
        MapListDataContext ret;
        if(value != null)
            ret = new MapListDataContext((Map)value);
        else
            ret = new MapListDataContext();
        data.put(key, ret);
        return ret;
    }

    private DataArea putDataObj(String key, Map value)
    {
        if(value == null)
            throw ExceptionUtil.wrapThrow(BaseConst.DataArea03, new String[] {
                key
            });
        if(!(value instanceof MapListDataContext))
            value = new MapListDataContext(value);
        data.put(key, value);
        return this;
    }

    public MapListDataContext getHeader()
    {
        return getDataObj("header");
    }

    public MapListDataContext getSystem()
    {
        return getDataObj("sys");
    }

    public Map getSystemProfile()
    {
        return getDataObj("sys").getMap("profile");
    }

    public MapListDataContext getLinkReq()
    {
        return getDataObj("link_req");
    }

    public MapListDataContext getCommReq()
    {
        return getDataObj("comm_req");
    }

    public MapListDataContext getInput()
    {
        return getDataObj("input");
    }

    public MapListDataContext getProperty()
    {
        return getDataObj("property");
    }

    public DataArea setHeader(Map header)
    {
        return putDataObj("header", header);
    }

    public DataArea setLinkReq(Map link_req)
    {
        return putDataObj("link_req", link_req);
    }

    public DataArea setSystem(Map system)
    {
        return putDataObj("sys", system);
    }

    public DataArea setCommReq(Map commReq)
    {
        return putDataObj("comm_req", commReq);
    }

    public DataArea setInput(Map input)
    {
        return putDataObj("input", input);
    }

    public DataArea setProperty(Map property)
    {
        return putDataObj("property", property);
    }

    public CompositeMap getInputAndProperty()
    {
        if(inputAndProperty == null)
            inputAndProperty = new CompositeMap(getProperty().getMap(), getInput().getMap(), new org.apache.commons.collections.map.CompositeMap.MapMutator() {

                public void resolveCollision(CompositeMap compositemap, Map map, Map map1, Collection collection)
                {
                }

                public void putAll(CompositeMap arg0, Map arg1[], Map arg2)
                {
                    throw ExceptionUtil.wrapThrow(BaseConst.DataArea04, new String[0]);
                }

                public Object put(CompositeMap arg0, Map arg1[], Object arg2, Object arg3)
                {
                    throw ExceptionUtil.wrapThrow(BaseConst.DataArea05, new String[0]);
                }

                final DataArea this$0;

            
            {
                this.this$0 = DataArea.this;
                super();
            }
            }
);
        return inputAndProperty;
    }

    public MapListDataContext getLinkRes()
    {
        return getDataObj("link_res");
    }

    public MapListDataContext getCommRes()
    {
        return getDataObj("comm_res");
    }

    public MapListDataContext getOutput()
    {
        return getDataObj("output");
    }

    public MapListDataContext getPrinter()
    {
        return getDataObj("printer");
    }

    public DataArea setLinkRes(Map link_res)
    {
        return putDataObj("link_res", link_res);
    }

    public DataArea setCommRes(Map data)
    {
        return putDataObj("comm_res", data);
    }

    public DataArea setOutput(Map data)
    {
        return putDataObj("output", data);
    }

    public DataArea setPrinter(Map data)
    {
        return putDataObj("printer", data);
    }

    public Map getData()
    {
        return data;
    }

    public String toString()
    {
        return data.toString();
    }

    public static final String HEADER = "header";
    public static final String LINK_REQUEST = "link_req";
    public static final String LINK_RESPONSE = "link_res";
    public static final String COMM_REQUEST = "comm_req";
    public static final String COMM_RESPONSE = "comm_res";
    public static final String SYSTEM = "sys";
    public static final String INPUT = "input";
    public static final String PROPERTY = "property";
    public static final String OUTPUT = "output";
    public static final String PRINTER = "printer";
    private final Map data;
    CompositeMap inputAndProperty;
}
