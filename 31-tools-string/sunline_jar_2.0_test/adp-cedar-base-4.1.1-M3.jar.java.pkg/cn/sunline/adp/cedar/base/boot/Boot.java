// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Boot.java

package cn.sunline.adp.cedar.base.boot;

import cn.sunline.adp.cedar.base.boot.plugin.IAdditionalExtension;
import cn.sunline.adp.cedar.base.boot.plugin.IProcess;
import cn.sunline.adp.cedar.base.boot.plugin.impl.PluginManager;
import cn.sunline.adp.cedar.base.boot.plugin.util.ExtensionUtil;
import cn.sunline.adp.cedar.base.boot.plugin.util.Log4j2ThreadContext;
import cn.sunline.adp.cedar.base.boot.spi.BootProcessPointExtension;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

public class Boot
{

    private Boot()
    {
        commonLoader = null;
        appLoader = null;
    }

    public static Boot get()
    {
        return instance;
    }

    public void start()
    {
        try
        {
            PluginManager.initPluginServices();
            ExtensionUtil.executeExtensionPoint("edsp-micro-core.process.boot", new IProcess() {

                public void run(BootProcessPointExtension extensionObj)
                {
                    extensionObj.serverStartBefore();
                }

                public volatile void run(IAdditionalExtension iadditionalextension)
                {
                    run((BootProcessPointExtension)iadditionalextension);
                }

                final Boot this$0;

            
            {
                this.this$0 = Boot.this;
                super();
            }
            }
);
            PluginManager.startAllPluginServices();
            ExtensionUtil.executeExtensionPoint("edsp-micro-core.process.boot", new IProcess() {

                public void run(BootProcessPointExtension extensionObj)
                {
                    extensionObj.serverStartAfter();
                }

                public volatile void run(IAdditionalExtension iadditionalextension)
                {
                    run((BootProcessPointExtension)iadditionalextension);
                }

                final Boot this$0;

            
            {
                this.this$0 = Boot.this;
                super();
            }
            }
);
        }
        catch(Exception ex)
        {
            BOOT_LOGGER.error("server start failed", ex, new Object[0]);
            try
            {
                BOOT_LOGGER.info("server stopping");
                stop();
            }
            catch(Exception ignore)
            {
                BOOT_LOGGER.error("server stop failed", ignore, new Object[0]);
            }
            throw new RuntimeException("server start failed", ex);
        }
    }

    public void stop()
    {
        Log4j2ThreadContext.set_sys_boot();
        try
        {
            PluginManager.shutdownAllPluginServices();
            BOOT_LOGGER.info("server stopped");
        }
        catch(Exception ex)
        {
            BOOT_LOGGER.error("server stop failed: ", ex, new Object[0]);
            throw new RuntimeException("server stop failed: ", ex);
        }
    }

    public void init()
        throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        setLTTSHome();
        setLTTSBase();
        commonLoader = appLoader = initLttsClassLoader();
        Thread.currentThread().setContextClassLoader(appLoader);
        initLog4j();
        Log4j2ThreadContext.set_sys_boot();
    }

    public void setLTTSHome(String s)
    {
        System.setProperty("ltts.home", s);
    }

    public void setLTTSBase(String s)
    {
        System.setProperty("ltts.base", s);
    }

    private void setLTTSBase()
    {
        if(System.getProperty("ltts.base") != null)
            return;
        if(System.getProperty("ltts.home") != null)
            System.setProperty("ltts.base", System.getProperty("ltts.home"));
        else
            System.setProperty("ltts.base", System.getProperty("user.dir"));
    }

    private void setLTTSHome()
    {
        if(System.getProperty("ltts.home") != null)
            return;
        File bootstrapJar = new File(System.getProperty("user.dir"), "bootstrap.jar");
        if(bootstrapJar.exists())
            try
            {
                System.setProperty("ltts.home", (new File(System.getProperty("user.dir"), "..")).getCanonicalPath());
            }
            catch(Exception e)
            {
                System.setProperty("ltts.home", System.getProperty("user.dir"));
            }
        else
            System.setProperty("ltts.home", System.getProperty("user.dir"));
    }

    public static String getLTTSHome()
    {
        return System.getProperty("ltts.home", System.getProperty("user.dir"));
    }

    public static String getLTTSBase()
    {
        return System.getProperty("ltts.base", getLTTSHome());
    }

    private void initLog4j()
    {
        System.setProperty("ltts.log.level", "DEBUG");
        setSystemProperty("log4j2.is.webapp", "false");
        setSystemProperty("log4j2.enable.threadlocals", "true");
        String logFile = System.getProperty("log4j.configurationFile");
        if(logFile != null && logFile.trim().length() != 0)
        {
            System.out.println((new StringBuilder()).append("log config file:").append(logFile).toString());
            return;
        }
        List filenames = new ArrayList(Arrays.asList(new String[] {
            "ltts_log.xml", "ltts_log_default.xml"
        }));
        String vmid = System.getProperty("ltts.vmid");
        if(vmid != null)
            filenames.addAll(0, Arrays.asList(new String[] {
                (new StringBuilder()).append("ltts_log_").append(vmid).append(".xml").toString()
            }));
        for(Iterator iterator = filenames.iterator(); iterator.hasNext();)
        {
            String filename = (String)iterator.next();
            URL url = appLoader.getResource(filename);
            if(url != null)
            {
                System.setProperty("log4j.configurationFile", filename);
                System.out.println((new StringBuilder()).append("log config file:").append(filename).toString());
                return;
            }
        }

        System.out.println("Can't find ltts log config file!");
    }

    private void setSystemProperty(String key, String value)
    {
        if(System.getProperty(key) == null)
            System.setProperty(key, value);
    }

    private ClassLoader initLttsClassLoader()
    {
        String value = System.getProperty("ltts.classpath");
        if(value == null || "".equals(value))
            value = System.getenv("LTTS_CLASSPATH");
        if(value != null && !"".equals(value))
        {
            value = convertClassPathElement(value);
            List urls = new ArrayList();
            List ltts = new ArrayList();
            String as[] = value.split(File.pathSeparator);
            int i = as.length;
label0:
            for(int j = 0; j < i; j++)
            {
                String v = as[j];
                if(v == null || v.trim().length() == 0)
                    continue;
                List cps = expendPatternClassPath(v, true);
                Iterator iterator = cps.iterator();
                do
                {
                    if(!iterator.hasNext())
                        continue label0;
                    String cp = (String)iterator.next();
                    try
                    {
                        urls.add((new File(cp)).toURI().toURL());
                        ltts.add(cp);
                    }
                    catch(MalformedURLException e)
                    {
                        BOOT_LOGGER.error((new StringBuilder()).append("Classpath error[").append(cp).append("]: ").append(e.getMessage()).toString(), e, new Object[0]);
                    }
                } while(true);
            }

            if(urls.size() > 0)
            {
                System.setProperty("ltts.class.path", ltts.toString());
                ClassLoader loader = new URLClassLoader((URL[])urls.toArray(new URL[0]), cn/sunline/adp/cedar/base/boot/Boot.getClassLoader());
                return loader;
            }
        }
        return cn/sunline/adp/cedar/base/boot/Boot.getClassLoader();
    }

    private String convertClassPathElement(String e)
    {
        String repository = e;
        if(repository.startsWith("${ltts.home}"))
            repository = (new StringBuilder()).append(getLTTSHome()).append(repository.substring("${ltts.home}".length())).toString();
        else
        if(repository.startsWith("${ltts.base}"))
            repository = (new StringBuilder()).append(getLTTSBase()).append(repository.substring("${ltts.base}".length())).toString();
        String vmid = System.getProperty("ltts.vmid", "");
        if(vmid.length() > 0)
        {
            String str = repository;
            int idx = str.indexOf("${ltts.vmid}");
            if(idx >= 0)
            {
                repository = str.substring(0, idx);
                repository = (new StringBuilder()).append(repository).append(vmid).toString();
                repository = (new StringBuilder()).append(repository).append(str.substring(idx + "${ltts.vmid}".length())).toString();
            }
        }
        return repository;
    }

    private List _expendPatternClassPath(String patternPath, boolean checkExist)
    {
        List ret = new ArrayList();
        patternPath = patternPath.replace("\\", "/");
        String wildcardPart = "/**";
        int patternIndex = patternPath.indexOf(wildcardPart);
        if(patternIndex != -1)
        {
            String rootDir = patternPath.substring(0, patternIndex);
            File rootDirFile = new File(rootDir);
            if(!rootDirFile.exists() || !rootDirFile.isDirectory() || !rootDirFile.canRead())
                return ret;
            patternPath = patternPath.replace("**", ".*");
            collect(rootDirFile, patternPath, ret);
        }
        return ret;
    }

    private void collect(File folder, String regex, List cols)
    {
        File afile[] = folder.listFiles();
        int i = afile.length;
        for(int j = 0; j < i; j++)
        {
            File subFile = afile[j];
            String p = subFile.getPath().replace("\\", "/");
            if(Pattern.matches(regex, p))
            {
                cols.add(p);
                continue;
            }
            if(subFile.isDirectory())
                collect(subFile, regex, cols);
        }

    }

    private List expendPatternClassPath(String patternPath, boolean checkExist)
    {
        if(patternPath.contains("**"))
            return _expendPatternClassPath(patternPath, checkExist);
        List ret = new ArrayList();
        if('/' != File.separatorChar)
            patternPath = patternPath.replace('/', File.separatorChar);
        if('\\' != File.separatorChar)
            patternPath = patternPath.replace('\\', File.separatorChar);
        String wildcardPart = (new StringBuilder()).append(File.separator).append("*").toString();
        int patternIndex = patternPath.indexOf(wildcardPart);
        if(patternIndex == -1)
        {
            File file = new File(patternPath);
            if(!checkExist || file.exists())
                ret.add(file.getPath());
        } else
        {
            String rootDir = patternPath.substring(0, patternIndex);
            String subPattern = patternPath.substring(patternIndex + wildcardPart.length());
            File rootDirFile = new File(rootDir);
            if(!rootDirFile.exists() || !rootDirFile.isDirectory() || !rootDirFile.canRead())
                return ret;
            File afile[] = rootDirFile.listFiles();
            int i = afile.length;
            for(int j = 0; j < i; j++)
            {
                File file = afile[j];
                File cp = new File((new StringBuilder()).append(file.getPath()).append(subPattern).toString());
                if(!checkExist || cp.exists())
                    ret.add(cp.getPath());
            }

        }
        return ret;
    }

    protected static final String LTTS_HOME_TOKEN = "${ltts.home}";
    protected static final String LTTS_BASE_TOKEN = "${ltts.base}";
    protected static final String LTTS_VMID_TOKEN = "${ltts.vmid}";
    private static final String LTTS_LOG = "ltts_log";
    private static final SysLog BOOT_LOGGER = SysLogUtil.getBootLogger();
    protected ClassLoader commonLoader;
    protected ClassLoader appLoader;
    private static final Boot instance = new Boot();

}
