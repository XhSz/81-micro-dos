// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StrParmResolveUtil.java

package cn.sunline.adp.cedar.base.component;

import cn.sunline.edsp.base.util.lang.StringUtil;

public class StrParmResolveUtil
{
    public static interface Runnable
    {

        public abstract String run(String s);
    }


    public StrParmResolveUtil()
    {
    }

    public static String evalTest(Runnable run, String expression)
    {
        return evalText(run, '$', expression, 1);
    }

    public static String evalText(Runnable run, char open, String expression, int maxLoopCount)
    {
        String result = expression;
        int loopCount = 1;
        int pos = 0;
        do
        {
            int start = expression.indexOf((new StringBuilder()).append(open).append("{").toString(), pos);
            if(start == -1)
            {
                pos = 0;
                loopCount++;
                start = expression.indexOf((new StringBuilder()).append(open).append("{").toString());
            }
            if(loopCount > maxLoopCount)
                break;
            int length = expression.length();
            int x = start + 2;
            int count = 1;
            do
            {
                if(start == -1 || x >= length || count == 0)
                    break;
                char c = expression.charAt(x++);
                if(c == '{')
                    count++;
                else
                if(c == '}')
                    count--;
            } while(true);
            int end = x - 1;
            if(start == -1 || end == -1 || count != 0)
                break;
            String var = expression.substring(start + 2, end);
            String o = run.run(var);
            String left = expression.substring(0, start);
            String right = expression.substring(end + 1);
            String middle = null;
            if(o != null)
            {
                middle = o.toString();
                if(StringUtil.isEmpty(left))
                    result = o;
                else
                    result = (new StringBuilder()).append(left).append(middle).toString();
                if(StringUtil.isNotEmpty(right))
                    result = (new StringBuilder()).append(result).append(right).toString();
                expression = (new StringBuilder()).append(left).append(middle).append(right).toString();
            } else
            {
                result = (new StringBuilder()).append(left).append(right).toString();
                expression = (new StringBuilder()).append(left).append(right).toString();
            }
            pos = (left == null || left.length() <= 0 ? 0 : left.length() - 1) + (middle == null || middle.length() <= 0 ? 0 : middle.length() - 1) + 1;
            pos = Math.max(pos, 1);
        } while(true);
        return result;
    }
}
