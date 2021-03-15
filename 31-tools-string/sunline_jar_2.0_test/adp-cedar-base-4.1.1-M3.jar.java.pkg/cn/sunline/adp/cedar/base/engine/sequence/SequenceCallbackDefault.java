// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SequenceCallbackDefault.java

package cn.sunline.adp.cedar.base.engine.sequence;

import cn.sunline.adp.cedar.base.bean.SequenceMessage;
import cn.sunline.edsp.base.util.date.DateUtil;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.sequence:
//            SequenceCallback

public class SequenceCallbackDefault
    implements SequenceCallback
{

    public SequenceCallbackDefault()
    {
        r = new Random(10000L);
        value = new AtomicLong(0L);
    }

    public SequenceMessage nextval(String type, String sequenceName)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtil.getNow("HHmmssSSS"));
        sb.append(String.format("%08d", new Object[] {
            Long.valueOf(value.addAndGet(1L))
        }));
        sb.append(String.format("%04d", new Object[] {
            Integer.valueOf(Math.abs(r.nextInt()) % 10000)
        }));
        return new SequenceMessage(null, sb.toString());
    }

    public void init()
    {
    }

    public void collection()
    {
    }

    private Random r;
    private AtomicLong value;
}
