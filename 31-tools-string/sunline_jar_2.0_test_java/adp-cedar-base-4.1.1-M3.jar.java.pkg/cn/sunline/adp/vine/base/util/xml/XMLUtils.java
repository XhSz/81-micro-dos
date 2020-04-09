// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMLUtils.java

package cn.sunline.adp.vine.base.util.xml;

import cn.sunline.adp.vine.base.exception.FlowException;
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.dom4j.*;
import org.dom4j.io.*;
import org.xml.sax.*;

public class XMLUtils
{

    public XMLUtils()
    {
    }

    public static Document getDocument(Reader xml, Class resourceFinder)
    {
        return getDocument(xml, true, resourceFinder);
    }

    public static Document getDocument(Reader xml, boolean isValidate, Class resourceFinder)
    {
        SAXReader r = new SAXReader(isValidate);
        try
        {
            if(isValidate)
            {
                r.setFeature("http://apache.org/xml/features/validation/schema", true);
                r.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
                r.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
                r.setEntityResolver(new EntityResolver(resourceFinder) {

                    public InputSource resolveEntity(String publicId, String systemId)
                        throws SAXException, IOException
                    {
                        String xsd = systemId.substring(systemId.lastIndexOf("/") + 1);
                        return new InputSource(resourceFinder.getResourceAsStream(xsd));
                    }

                    final Class val$resourceFinder;

            
            {
                resourceFinder = class1;
                super();
            }
                }
);
            }
            return r.read(xml);
        }
        catch(Exception e)
        {
            throw new FlowException(e.getMessage(), e);
        }
    }

    public static Document getDocument(InputStream is)
    {
        try
        {
            SAXReader reader = new SAXReader();
            return reader.read(is);
        }
        catch(Exception e)
        {
            throw new FlowException(e.getMessage(), e);
        }
    }

    public static Document getDocumentByDOM(InputStream is)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            org.w3c.dom.Document doc = builder.parse(is);
            DOMReader reader = new DOMReader();
            return reader.read(doc);
        }
        catch(Exception e)
        {
            throw new FlowException(e.getMessage(), e);
        }
    }

    public static void write(Document doc, Writer w)
    {
        OutputFormat of = OutputFormat.createPrettyPrint();
        of.setTrimText(false);
        of.setEncoding(doc.getXMLEncoding());
        XMLWriter xw = new XMLWriter(w, of);
        try
        {
            xw.write(doc);
            xw.flush();
        }
        catch(IOException e)
        {
            throw new FlowException(e.getMessage(), e);
        }
    }

    public static String xml2Str(Document doc)
    {
        CharArrayWriter w = new CharArrayWriter();
        write(doc, w);
        return w.toString();
    }

    public static String xml2Str(Element ele)
    {
        return xml2Str(DocumentHelper.createDocument(ele));
    }

    public static Element createElement(Namespace ns, String na, Object attrs[])
    {
        return createElement(ns, na, attrs, null);
    }

    public static Element createElement(Namespace ns, String na, Object attrs[], String text)
    {
        Element e = DocumentHelper.createElement(DocumentHelper.createQName(na, ns));
        for(int i = 0; i < attrs.length; i += 2)
            if(attrs[i] instanceof QName)
                e.addAttribute((QName)attrs[i], (String)attrs[i + 1]);
            else
                e.addAttribute(DocumentHelper.createQName((String)attrs[i], ns), (new StringBuilder()).append("").append(attrs[i + 1]).toString());

        if(text != null)
            e.addText(text);
        return e;
    }

    public static Namespace getXSINamespace()
    {
        return DocumentHelper.createNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    }

    public static String getElementAttrValue(Element e, String name)
    {
        return (String)getElementAttrValue(e, name, "");
    }

    public static Object getElementAttrValue(Element e, String name, Object defVal)
    {
        Object ret = defVal;
        try
        {
            String s = e.attributeValue(name);
            if(s != null)
                if(defVal instanceof Integer)
                    ret = Integer.valueOf(Integer.parseInt(s.trim()));
                else
                if(defVal instanceof Boolean)
                    ret = Boolean.valueOf(Boolean.parseBoolean(s.trim()));
                else
                if(defVal instanceof Float)
                    ret = Float.valueOf(Float.parseFloat(s.trim()));
                else
                if(defVal instanceof Double)
                    ret = Double.valueOf(Double.parseDouble(s.trim()));
                else
                if(defVal instanceof String)
                    ret = s;
        }
        catch(Exception exception) { }
        return ret;
    }

    public static final String DEF_NAMESPACE = "http://www.sunline.cn/bpl";
}
