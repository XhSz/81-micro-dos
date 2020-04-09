// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HealthCheck.java

package cn.sunline.adp.cedar.base.healthcheck;


public abstract class HealthCheck
{
    public static class Result
    {

        public static Result healthy()
        {
            return HEALTHY;
        }

        public static Result healthy(String message)
        {
            return new Result(true, message, null);
        }

        public static transient Result healthy(String message, Object args[])
        {
            return healthy(String.format(message, args));
        }

        public static Result unhealthy(String message)
        {
            return new Result(false, message, null);
        }

        public static transient Result unhealthy(String message, Object args[])
        {
            return unhealthy(String.format(message, args));
        }

        public static Result unhealthy(Throwable error)
        {
            return new Result(false, error.getMessage(), error);
        }

        public boolean isHealthy()
        {
            return HEALTHYS;
        }

        public String getMessage()
        {
            return message;
        }

        public Throwable getError()
        {
            return error;
        }

        public boolean equals(Object o)
        {
            if(this == o)
                return true;
            if(o == null || getClass() != o.getClass())
            {
                return false;
            } else
            {
                Result result = (Result)o;
                return HEALTHYS == result.HEALTHYS && (error == null ? result.error == null : error.equals(result.error)) && (message == null ? result.message == null : message.equals(result.message));
            }
        }

        public int hashCode()
        {
            int result = HEALTHYS ? 1 : 0;
            result = 31 * result + (message == null ? 0 : message.hashCode());
            result = 31 * result + (error == null ? 0 : error.hashCode());
            return result;
        }

        public String toString()
        {
            StringBuilder builder = new StringBuilder("Result{isHealthy=");
            builder.append(HEALTHYS);
            if(message != null)
                builder.append(", message=").append(message);
            if(error != null)
                builder.append(", error=").append(error);
            builder.append('}');
            return builder.toString();
        }

        private static final Result HEALTHY = new Result(true, null, null);
        private static final int PRIME = 31;
        private final boolean HEALTHYS;
        private final String message;
        private final Throwable error;


        private Result(boolean isHealthy, String message, Throwable error)
        {
            HEALTHYS = isHealthy;
            this.message = message;
            this.error = error;
        }
    }


    public HealthCheck()
    {
    }

    protected abstract Result check()
        throws Exception;

    public Result execute()
    {
        try
        {
            return check();
        }
        catch(Exception e)
        {
            return Result.unhealthy(e);
        }
    }
}
