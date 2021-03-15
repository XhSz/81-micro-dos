// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ThreadCriticalResourceAccessor.java

package cn.sunline.adp.cedar.base.srv;

import java.sql.SQLException;

public interface ThreadCriticalResourceAccessor
{
    public static interface CriticalResource
    {

        public abstract String getType();

        public abstract void close()
            throws SQLException;
    }

    public static class Factory
    {

        public static ThreadCriticalResourceAccessor get()
        {
            return (ThreadCriticalResourceAccessor)instance.get();
        }

        public static void set(ThreadCriticalResourceAccessor listener)
        {
            instance.set(listener);
        }

        private static ThreadLocal instance = new ThreadLocal() {

            protected ThreadCriticalResourceAccessor initialValue()
            {
                return ThreadCriticalResourceAccessor.NULL;
            }

            protected volatile Object initialValue()
            {
                return initialValue();
            }

        }
;


        public Factory()
        {
        }
    }


    public abstract boolean isTransactionTimeout();

    public abstract void attatchCriticalResource(CriticalResource criticalresource);

    public abstract void detatchCriticalResource(CriticalResource criticalresource);

    public static final ThreadCriticalResourceAccessor NULL = new ThreadCriticalResourceAccessor() {

        public boolean isTransactionTimeout()
        {
            return false;
        }

        public void attatchCriticalResource(CriticalResource criticalresource)
        {
        }

        public void detatchCriticalResource(CriticalResource criticalresource)
        {
        }

    }
;

}
