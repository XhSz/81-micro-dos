// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExcelConfigManager.java

package cn.sunline.ltts.frw.model.util;

import cn.sunline.ltts.core.api.logging.SysLog;
import cn.sunline.ltts.core.api.logging.SysLogUtil;
import cn.sunline.ltts.core.api.model.ModelFile;
import cn.sunline.ltts.frw.model.resource.PatternModelFilesLoader;
import cu.sunline.ltts.ModelRtConst;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import jxl.*;

// Referenced classes of package cn.sunline.ltts.frw.model.util:
//            ModelFilesLoader

public class ExcelConfigManager
{

    private ExcelConfigManager()
    {
    }

    public static ExcelConfigManager get()
    {
        return instance;
    }

    public void load(String path[])
    {
        if(path == null || path.length == 0)
            return;
        ModelFilesLoader loader = new PatternModelFilesLoader(path);
        ModelFile arr$[] = loader.load();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            ModelFile f = arr$[i$];
            loadExcelFile(f);
            log.info(ModelRtConst.ExcelConfigManager01, new Object[] {
                f.getFullPath()
            });
        }

    }

    private void loadExcelFile(ModelFile file)
    {
        try
        {
            Workbook book = Workbook.getWorkbook(file.getInputStream());
            Sheet sheets[] = book.getSheets();
            Sheet arr$[] = sheets;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Sheet sheet = arr$[i$];
                if(excelDatas.get(sheet.getName()) != null)
                {
                    log.error(ModelRtConst.ExcelConfigManager02, new Object[] {
                        sheet.getName()
                    });
                } else
                {
                    List data = new ArrayList();
                    Cell fieldNames[] = sheet.getRow(0);
                    for(int i = 1; i < sheet.getRows(); i++)
                    {
                        Map d = new HashMap();
                        Cell fieldValue[] = sheet.getRow(i);
                        for(int j = 0; j < fieldValue.length; j++)
                            d.put(fieldNames[j].getContents(), fieldValue[j].getContents());

                        data.add(d);
                    }

                    excelDatas.put(sheet.getName(), data);
                }
            }

        }
        catch(Exception e1)
        {
            throw new RuntimeException(e1);
        }
    }

    public List getData(String tableName)
    {
        return (List)excelDatas.get(tableName);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/ltts/frw/model/util/ExcelConfigManager);
    private final Map excelDatas = new ConcurrentHashMap();
    private static final ExcelConfigManager instance = new ExcelConfigManager();

}
