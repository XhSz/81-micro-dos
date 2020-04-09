// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CompTypes.java

package cn.sunline.adp.cedar.busi.sdk.type;

import cn.sunline.adp.metadata.model.annotation.ConfigType;
import java.util.Map;

public interface CompTypes
{
    public static interface FileTransferResult
    {

        public abstract Integer getCode();

        public abstract void setCode(Integer integer);

        public abstract String getMessage();

        public abstract void setMessage(String s);

        public abstract String getLocalFullPath();

        public abstract void setLocalFullPath(String s);

        public abstract Map getProperties();

        public abstract void setProperties(Map map);

        public abstract String toString();
    }

}
