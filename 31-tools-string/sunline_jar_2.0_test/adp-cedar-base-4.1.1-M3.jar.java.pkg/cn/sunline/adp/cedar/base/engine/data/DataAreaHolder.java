// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DataAreaHolder.java

package cn.sunline.adp.cedar.base.engine.data;

import cn.sunline.adp.cedar.base.BaseConst;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import java.util.Map;

// Referenced classes of package cn.sunline.adp.cedar.base.engine.data:
//            DataArea

public class DataAreaHolder
{
    public static final class TYPE extends Enum
    {

        public static TYPE[] values()
        {
            return (TYPE[])$VALUES.clone();
        }

        public static TYPE valueOf(String name)
        {
            return (TYPE)Enum.valueOf(cn/sunline/adp/cedar/base/engine/data/DataAreaHolder$TYPE, name);
        }

        public static final TYPE input;
        public static final TYPE property;
        public static final TYPE ouput;
        public static final TYPE input_property;
        private static final TYPE $VALUES[];

        static 
        {
            input = new TYPE("input", 0);
            property = new TYPE("property", 1);
            ouput = new TYPE("ouput", 2);
            input_property = new TYPE("input_property", 3);
            $VALUES = (new TYPE[] {
                input, property, ouput, input_property
            });
        }

        private TYPE(String s, int i)
        {
            super(s, i);
        }
    }


    public DataAreaHolder(DataArea data, TYPE type)
    {
        this.data = data;
        this.type = type;
    }

    public Map getData()
    {
        static class _cls1
        {

            static final int $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE[];

            static 
            {
                $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE = new int[TYPE.values().length];
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE[TYPE.input.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE[TYPE.property.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE[TYPE.input_property.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$cn$sunline$adp$cedar$base$engine$data$DataAreaHolder$TYPE[TYPE.ouput.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
            }
        }

        switch(_cls1..SwitchMap.cn.sunline.adp.cedar.base.engine.data.DataAreaHolder.TYPE[type.ordinal()])
        {
        case 1: // '\001'
            return data.getInput();

        case 2: // '\002'
            return data.getProperty();

        case 3: // '\003'
            return data.getInputAndProperty();

        case 4: // '\004'
            return data.getOutput();
        }
        throw ExceptionUtil.wrapThrow(String.format(BaseConst.DataAreaHolder01, new Object[] {
            type, type
        }), new String[0]);
    }

    private final DataArea data;
    private final TYPE type;
}
