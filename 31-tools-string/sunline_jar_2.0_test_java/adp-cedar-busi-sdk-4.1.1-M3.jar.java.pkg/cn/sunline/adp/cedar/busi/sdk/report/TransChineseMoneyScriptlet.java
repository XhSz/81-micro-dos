// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransChineseMoneyScriptlet.java

package cn.sunline.adp.cedar.busi.sdk.report;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;

public class TransChineseMoneyScriptlet extends JRDefaultScriptlet
{

    public TransChineseMoneyScriptlet()
    {
    }

    public static String getChineseMoney(String money)
    {
        String money2 = transChineseMoney2(money);
        String text = "";
        if(money2.equals(""))
            text = (new StringBuilder()).append(transChineseMoney1(money)).append(transChineseMoney2(money)).append("\u5706").toString();
        else
            text = (new StringBuilder()).append(transChineseMoney1(money)).append("\u70B9").append(transChineseMoney2(money)).append("\u5706").toString();
        Pattern p = Pattern.compile("\u96F6\u5206", 2);
        Matcher m = p.matcher(text);
        text = m.replaceAll("");
        return text;
    }

    public static String transChineseMoney1(String s)
    {
        String ss = s;
        String tmpnewchar = "";
        String part[] = ss.split("\\.");
        if(part[0].length() > 12)
            return "";
        for(int i = 0; i < part[0].length(); i++)
        {
            char perchar = part[0].charAt(i);
            if(perchar == '0' && i != part[0].length() - 1 && i != part[0].length() - 2)
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u96F6").toString();
            if(perchar == '1')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u58F9").toString();
            if(perchar == '2')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u8D30").toString();
            if(perchar == '3')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u53C1").toString();
            if(perchar == '4')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u8086").toString();
            if(perchar == '5')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4F0D").toString();
            if(perchar == '6')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u9646").toString();
            if(perchar == '7')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u67D2").toString();
            if(perchar == '8')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u634C").toString();
            if(perchar == '9')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u7396").toString();
            int j = part[0].length() - i - 1;
            if(j == 1 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u62FE").toString();
            if(j == 2 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4F70").toString();
            if(j == 3 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4EDF").toString();
            if(j == 4 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4E07").toString();
            if(j == 5 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u62FE").toString();
            if(j == 6 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4F70").toString();
            if(j == 7 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4EDF").toString();
            if(j == 8 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4EBF").toString();
            if(j == 9 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u62FE").toString();
            if(j == 10 && perchar != '0')
                tmpnewchar = (new StringBuilder()).append(tmpnewchar).append("\u4F70").toString();
        }

        return tmpnewchar;
    }

    public static String transChineseMoney2(String s)
    {
        String ss = s;
        String tmpnewchar1 = "";
        String part[] = ss.split("\\.");
        if(ss.indexOf(".") != -1)
        {
            if(part[1].length() > 2)
                part[1] = part[1].substring(0, 2);
            for(int i = 0; i < part[1].length(); i++)
            {
                char perchar = part[1].charAt(i);
                if(perchar == '0')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u96F6").toString();
                if(perchar == '1')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u58F9").toString();
                if(perchar == '2')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u8D30").toString();
                if(perchar == '3')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u53C1").toString();
                if(perchar == '4')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u8086").toString();
                if(perchar == '5')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u4F0D").toString();
                if(perchar == '6')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u9646").toString();
                if(perchar == '7')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u67D2").toString();
                if(perchar == '8')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u634C").toString();
                if(perchar == '9')
                    tmpnewchar1 = (new StringBuilder()).append(tmpnewchar1).append("\u7396").toString();
            }

        }
        return tmpnewchar1;
    }

    public void afterColumnInit()
        throws JRScriptletException
    {
        super.beforeColumnInit();
    }

    public void afterDetailEval()
        throws JRScriptletException
    {
        Double sumTaxMoney = getVariableValue("sumTaxMoney") != null ? (Double)getVariableValue("sumTaxMoney") : new Double(0.0D);
        String cnMoney = getChineseMoney((new StringBuilder()).append(sumTaxMoney).append("").toString());
        setVariableValue("cnMoney", cnMoney);
        super.afterDetailEval();
    }

    public void afterGroupInit(String groupName)
        throws JRScriptletException
    {
        super.afterGroupInit(groupName);
    }

    public void afterPageInit()
        throws JRScriptletException
    {
        super.afterPageInit();
    }

    public void afterReportInit()
        throws JRScriptletException
    {
    }

    public void beforeColumnInit()
        throws JRScriptletException
    {
    }

    public void beforeDetailEval()
        throws JRScriptletException
    {
    }

    public void beforeGroupInit(String s)
        throws JRScriptletException
    {
    }

    public void beforePageInit()
        throws JRScriptletException
    {
    }

    public void beforeReportInit()
        throws JRScriptletException
    {
    }
}
