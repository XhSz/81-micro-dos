// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlUtil.java

package cn.sunline.adp.cedar.base.util;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.edsp.base.lang.EncString;
import cn.sunline.edsp.base.util.lang.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtil
{

    public XmlUtil()
    {
    }

    public static String format(Map data)
    {
        String sValue = getTargetXml(data);
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("sSendInfo=").append(sValue).toString());
        return sValue;
    }

    public static Map parse(String message)
    {
        Map ret = new HashMap();
        if(StringUtil.isEmpty(message))
            return ret;
        if(!message.startsWith("<?xml"))
            throw new RuntimeException(String.format(BaseConst.XmlUtil01, new Object[] {
                message
            }));
        Document dom = cn.sunline.edsp.base.util.xml.XmlUtil.str2Dom(message);
        if(dom == null)
            return ret;
        else
            return node2Map(getDomRootNode(dom));
    }

    private static Document getDocument()
    {
        try
        {
            return cn.sunline.edsp.base.util.xml.XmlUtil.createDocumentBuilder().newDocument();
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String getTargetXml(Map data)
    {
        Document dom = getDocument();
        Element root = dom.createElement(XML_ROOT);
        dom.appendChild(root);
        if(data != null)
        {
            Iterator iterator = data.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String key = (String)iterator.next();
                Object childData = data.get(key);
                if(childData != null)
                {
                    Element childElement = null;
                    if(childData instanceof Map)
                        childElement = getElement(dom, key, (Map)childData);
                    else
                        throw new RuntimeException(BaseConst.XmlUtil02);
                    root.appendChild(childElement);
                }
            } while(true);
        }
        return cn.sunline.edsp.base.util.xml.XmlUtil.dom2Xml(dom);
    }

    private static Element getElement(Document dom, String key, Map data)
    {
        Element ret = dom.createElement(XML_OBJECT);
        if(key != null)
            ret.setAttribute(XML_NAME, key);
        for(Iterator iterator = data.keySet().iterator(); iterator.hasNext();)
        {
            String childKey = (String)iterator.next();
            Object childValue = data.get(childKey);
            if(childValue == null)
            {
                if(childKey.equals("cjsfsvrlst"))
                    ret.appendChild(getElement(dom, childKey, ((List) (new ArrayList()))));
                else
                    ret.appendChild(getElement(dom, childKey, ""));
            } else
            if(childValue instanceof List)
                ret.appendChild(getElement(dom, childKey, (List)childValue));
            else
            if(childValue instanceof Map)
                ret.appendChild(getElement(dom, childKey, (Map)childValue));
            else
            if(childValue instanceof BigDecimal)
                ret.appendChild(getElement(dom, childKey, ((BigDecimal)childValue).toPlainString()));
            else
            if(childValue instanceof EncString)
                ret.appendChild(getElement(dom, childKey, ((EncString)childValue).getValue()));
            else
                ret.appendChild(getElement(dom, childKey, String.valueOf(childValue)));
        }

        return ret;
    }

    private static Element getElement(Document dom, String key, List list)
    {
        Element ret = dom.createElement(XML_ARRAY);
        ret.setAttribute(XML_NAME, key);
        ret.setAttribute(XML_LENGTH, String.valueOf(list.size()));
        Map mapData;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); ret.appendChild(getElement(dom, "", mapData)))
            mapData = (Map)iterator.next();

        return ret;
    }

    private static Element getElement(Document dom, String key, String value)
    {
        Element ret = dom.createElement(XML_FIELD);
        ret.setAttribute(XML_NAME, key);
        ret.setAttribute(XML_VALUE, value);
        return ret;
    }

    private static Map node2Map(Node node)
    {
        Map ret = new HashMap();
        if(node == null)
            return ret;
        NodeList childNodes = node.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++)
        {
            Node childNode = childNodes.item(i);
            if(!(childNode instanceof Element))
                continue;
            NamedNodeMap attributeNodes = childNode.getAttributes();
            Node nameNode = attributeNodes.getNamedItem(XML_NAME);
            if(nameNode == null)
                continue;
            Object value = null;
            String childNodeName = childNode.getNodeName();
            if(XML_FIELD.equals(childNodeName))
            {
                Node valueNode = attributeNodes.getNamedItem(XML_VALUE);
                if(valueNode != null)
                    value = valueNode.getNodeValue();
            } else
            if(XML_ARRAY.equals(childNodeName))
            {
                Node lengthNode = attributeNodes.getNamedItem(XML_LENGTH);
                if(lengthNode == null)
                    throw new RuntimeException(String.format(BaseConst.XmlUtil03, new Object[] {
                        XML_NAME, nameNode, XML_ARRAY, XML_LENGTH
                    }));
                int arrayLength = 0;
                try
                {
                    arrayLength = Integer.parseInt(lengthNode.getNodeValue());
                }
                catch(Exception e)
                {
                    throw new RuntimeException(String.format(BaseConst.XmlUtil04, new Object[] {
                        XML_NAME, nameNode, XML_ARRAY, XML_LENGTH
                    }));
                }
                if(arrayLength == 0)
                    value = new ArrayList();
                else
                    value = node2List(childNode);
            } else
            if(XML_OBJECT.equals(childNodeName))
                value = node2Map(childNode);
            else
                throw new RuntimeException(String.format(BaseConst.XmlUtil05, new Object[] {
                    childNodeName
                }));
            ret.put(nameNode.getNodeValue(), value);
        }

        return ret;
    }

    private static List node2List(Node node)
    {
        List ret = new ArrayList();
        NodeList childNodes = node.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++)
        {
            Node fieldnode = childNodes.item(i);
            if(!(fieldnode instanceof Element))
                continue;
            if(!XML_OBJECT.equals(fieldnode.getNodeName()))
                throw new RuntimeException(String.format(BaseConst.XmlUtil06, new Object[] {
                    fieldnode.getNodeName(), XML_ARRAY, XML_OBJECT
                }));
            ret.add(node2Map(fieldnode));
        }

        return ret;
    }

    private static Node getDomRootNode(Document dom)
    {
        if(dom == null)
            return null;
        NodeList nodeList = dom.getDocumentElement().getChildNodes();
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            Node node = nodeList.item(i);
            if(!(node instanceof Element))
                continue;
            String nodeName = node.getNodeName();
            if(nodeName.equalsIgnoreCase(XML_ROOT))
                return node;
        }

        return dom.getDocumentElement();
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/base/util/XmlUtil);
    private static String XML_ROOT = "root";
    private static String XML_NAME = "name";
    private static String XML_VALUE = "value";
    private static String XML_LENGTH = "length";
    private static String XML_ARRAY = "array";
    private static String XML_OBJECT = "object";
    private static String XML_FIELD = "field";

}
