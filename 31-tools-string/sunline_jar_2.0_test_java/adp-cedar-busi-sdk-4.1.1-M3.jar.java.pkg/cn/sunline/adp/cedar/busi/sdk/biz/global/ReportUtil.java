// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportUtil.java

package cn.sunline.adp.cedar.busi.sdk.biz.global;

import cn.sunline.adp.cedar.base.constant.ConstantValueManager;
import cn.sunline.adp.cedar.base.engine.datamapping.DataMappingUtil;
import cn.sunline.adp.cedar.base.logging.SysLog;
import cn.sunline.adp.cedar.base.logging.SysLogUtil;
import cn.sunline.adp.cedar.base.util.CommUtil;
import cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef;
import cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef;
import cn.sunline.adp.cedar.busi.sdk.report.*;
import cn.sunline.adp.core.bean.ModelObjectCreator;
import cn.sunline.adp.core.exception.AdpBusinessException;
import cn.sunline.adp.core.expression.ExpressionEvaluator;
import cn.sunline.adp.core.expression.ExpressionEvaluatorFactory;
import cn.sunline.adp.core.profile.ProfileUtil;
import cn.sunline.adp.metadata.base.util.*;
import cn.sunline.adp.metadata.loader.util.ModelFactoryUtil;
import cn.sunline.adp.metadata.model.ComplexType;
import cn.sunline.adp.metadata.model.ModelFactory;
import cn.sunline.adp.metadata.model.datainterface.*;
import cn.sunline.adp.metadata.model.report.Report;
import cn.sunline.edsp.base.util.exception.ExceptionUtil;
import cn.sunline.edsp.base.util.lang.StringUtil;
import cn.sunline.edsp.base.util.reflection.ReflectionUtil;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;

public class ReportUtil
{
    public static abstract class ReportTypeEnum extends Enum
    {

        public static ReportTypeEnum[] values()
        {
            return (ReportTypeEnum[])$VALUES.clone();
        }

        public static ReportTypeEnum valueOf(String name)
        {
            return (ReportTypeEnum)Enum.valueOf(cn/sunline/adp/cedar/busi/sdk/biz/global/ReportUtil$ReportTypeEnum, name);
        }

        public abstract void exportReportFile(JasperPrint jasperprint, String s)
            throws JRException;

        public abstract void exportReportToStream(JasperPrint jasperprint, OutputStream outputstream)
            throws JRException;

        public String toString()
        {
            return name();
        }

        public static final ReportTypeEnum html;
        public static final ReportTypeEnum txt;
        public static final ReportTypeEnum pdf;
        public static final ReportTypeEnum xml;
        public static final ReportTypeEnum excel;
        public static final ReportTypeEnum rtf;
        public static final ReportTypeEnum docx;
        private static final ReportTypeEnum $VALUES[];

        static 
        {
            html = new ReportTypeEnum("html", 0) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JasperExportManager.exportReportToHtmlFile(jp, strorePath);
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JasperExportManager.exportReportToHtmlFile("");
                }

            }
;
            txt = new ReportTypeEnum("txt", 1) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRTextExporter exporter = new JRTextExporter();
                    exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Float(7D));
                    exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Float(14F));
                    exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Float(1079F));
                    exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Float(790F));
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strorePath);
                    exporter.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JRTextExporter exporter = new JRTextExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    exporter.exportReport();
                }

            }
;
            pdf = new ReportTypeEnum("pdf", 2) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRPdfExporter export = new JRPdfExporter();
                    if(CommUtil.isNotNull(ReportUtil.getContext().get("USER_PASSWORD")))
                    {
                        export.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.valueOf(true));
                        export.setParameter(JRPdfExporterParameter.USER_PASSWORD, ReportUtil.getContext().get("USER_PASSWORD"));
                    }
                    export.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    export.setParameter(JRExporterParameter.OUTPUT_FILE, new File(strorePath));
                    export.setParameter(JRXmlExporterParameter.CHARACTER_ENCODING, "UTF-8");
                    export.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JasperExportManager.exportReportToPdfStream(jp, output);
                }

            }
;
            xml = new ReportTypeEnum("xml", 3) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRXml4SwfExporter xmlExporter = new JRXml4SwfExporter();
                    xmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    xmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(strorePath));
                    xmlExporter.setParameter(JRXmlExporterParameter.CHARACTER_ENCODING, "UTF-8");
                    xmlExporter.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JasperExportManager.exportReportToXmlStream(jp, output);
                }

            }
;
            excel = new ReportTypeEnum("excel", 4) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRXlsExporter xlsExporter = new JRXlsExporter();
                    File outfile = new File(strorePath);
                    xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE, outfile);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, Integer.valueOf(65535));
                    xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                    xlsExporter.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JRXlsExporter exporter = new JRXlsExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                    exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                    exporter.exportReport();
                }

            }
;
            rtf = new ReportTypeEnum("rtf", 5) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRRtfExporter rtfExporter = new JRRtfExporter();
                    rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strorePath);
                    rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GB2312");
                    rtfExporter.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JRRtfExporter rtfExporter = new JRRtfExporter();
                    rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    rtfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "GB2312");
                    rtfExporter.exportReport();
                }

            }
;
            docx = new ReportTypeEnum("docx", 6) {

                public void exportReportFile(JasperPrint jp, String strorePath)
                    throws JRException
                {
                    JRDocxExporter exp = new JRDocxExporter();
                    exp.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, strorePath);
                    exp.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.valueOf(false));
                    exp.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.valueOf(true));
                    exp.exportReport();
                }

                public void exportReportToStream(JasperPrint jp, OutputStream output)
                    throws JRException
                {
                    JRDocxExporter exp = new JRDocxExporter();
                    exp.setParameter(JRExporterParameter.JASPER_PRINT, jp);
                    exp.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
                    exp.setParameter(JRDocxExporterParameter.FLEXIBLE_ROW_HEIGHT, Boolean.valueOf(true));
                    exp.setParameter(JRDocxExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.valueOf(true));
                    exp.exportReport();
                }

            }
;
            $VALUES = (new ReportTypeEnum[] {
                html, txt, pdf, xml, excel, rtf, docx
            });
        }

        private ReportTypeEnum(String s, int i)
        {
            super(s, i);
        }

    }


    public ReportUtil()
    {
    }

    public static String getReportById(String reportId, Object input)
    {
        String fileName;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record(String.format(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C001(), new Object[] {
                reportId, input
            }));
        fileName = null;
        try
        {
            fileName = getReportById(reportId, input, null, null);
        }
        catch(Exception e)
        {
            if(e instanceof AdpBusinessException)
                throw (AdpBusinessException)e;
            else
                throw ExceptionUtil.wrapThrow(e.getMessage(), e);
        }
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record(String.format(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C002(), new Object[] {
                reportId, input
            }));
        break MISSING_BLOCK_LABEL_121;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record(String.format(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C002(), new Object[] {
                reportId, input
            }));
        throw exception;
        return fileName;
    }

    public static String getReportById(String reportId, Object param, String brchno, String interval)
    {
        return getReportById(reportId, param, brchno, interval, null);
    }

    public static String getReportById(String reportId, Object param, String brchno, String interval, String chineseName)
    {
        ReportProcessor process = null;
        try
        {
            process = (ReportProcessor)ReflectionUtil.classForName(REPORT_PROCESSOR_IMPL).newInstance();
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E003(REPORT_PROCESSOR_IMPL, e);
        }
        Report currReport = getReportById(reportId);
        String customName = null;
        if(StringUtil.isNotEmpty(currReport.getNamepattern()))
        {
            ExpressionEvaluator ee = ExpressionEvaluatorFactory.getInstance();
            Map p = new HashMap();
            p.putAll(CommUtil_.toMap(param));
            customName = (String)ee.eval(currReport.getNamepattern(), param, p);
        }
        Object input = EdspCoreBeanUtil.getModelObjectCreator().create(currReport.getDataInterface().getInput().getJavaClass());
        DataMappingUtil.dataMapping(currReport.getDataInterface().getInput().getElements(), currReport.getDataInterface().getInput().getElements(), CommUtil_.toMap(param), input, DataMapping.ByInterfaceTrue, true);
        String file = null;
        try
        {
            file = process.process(reportId, CommUtil_.toMap(input), brchno, interval, customName, chineseName);
        }
        catch(Exception e)
        {
            if(e instanceof AdpBusinessException)
                throw (AdpBusinessException)e;
            else
                throw ExceptionUtil.wrapThrow(e.getMessage(), e);
        }
        return file;
    }

    public static Map getContext()
    {
        if(context.get() == null)
            context.set(new HashMap());
        return (Map)context.get();
    }

    public static void clearContext()
    {
        context.set(null);
    }

    public static Boolean generate(String reportId, File localefile, Map input)
        throws JRException
    {
        Report currReport;
        Map paraMap;
        JRDataSource ds;
        Boolean boolean1;
        currReport = getReportById(reportId);
        Object fetcher = getReportDataProcessor(currReport);
        Object assmap = getAssistendData(fetcher, input);
        paraMap = initParam(assmap, currReport);
        ds = getJRDataSources(fetcher, input);
        if(ds != null)
            break MISSING_BLOCK_LABEL_52;
        boolean1 = Boolean.valueOf(false);
        clearContext();
        return boolean1;
        JasperPrint jp = null;
        long start_fill = System.currentTimeMillis();
        try
        {
            JasperReport report = getCompiledReport(currReport);
            jp = fillTemplate(report, paraMap, ds);
        }
        catch(JRException e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E004(currReport.getId(), e.getMessage(), e);
        }
        if(log.isDebugEnabled())
            log.debug(cn.sunline.adp.cedar.busi.sdk.constant.BusiSDKConstantDef.SPC_BS.C003(), new Object[] {
                currReport.getId(), Double.valueOf((double)(System.currentTimeMillis() - start_fill) / 1000D)
            });
        if(!localefile.getParentFile().exists())
            (new File(localefile.getParent())).mkdirs();
        String exportType = convertReportType(localefile, currReport);
        try
        {
            ReportTypeEnum.valueOf(exportType).exportReportFile(jp, localefile.getAbsolutePath());
        }
        catch(JRException e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E005(currReport.getId(), localefile.getAbsolutePath(), e);
        }
        if(!localefile.exists())
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E006(currReport.getId(), localefile.getAbsolutePath());
        clearContext();
        break MISSING_BLOCK_LABEL_245;
        Exception exception;
        exception;
        clearContext();
        throw exception;
        return Boolean.valueOf(true);
    }

    private static Map initParam(Object assmap, Report currReport)
    {
        Map paraMap = getContext();
        if(StringUtil.isNotEmpty(assmap))
        {
            paraMap.put("head", assmap);
            paraMap.putAll(CommUtil_.toMap(assmap));
        } else
        if(currReport.getAssDataItemObj() != null)
            paraMap.put("head", EdspCoreBeanUtil.getModelObjectCreator().create(currReport.getAssDataItemObj().getJavaClass()));
        else
            paraMap.put("head", new HashMap());
        return paraMap;
    }

    private static String convertReportType(File localefile, Report currReport)
    {
        String reportType = currReport.getReportType().getId();
        if(localefile.getName().indexOf(".") < 0)
            reportType = cn.sunline.adp.metadata.model.report.Report.ReportType.txt.getId();
        else
            reportType = localefile.getName().substring(localefile.getName().indexOf(".") + 1);
        if("xls".equals(reportType))
            reportType = cn.sunline.adp.metadata.model.report.Report.ReportType.excel.getId();
        return reportType;
    }

    private static JRDataSource getJRDataSources(Object fetcher, Map input)
    {
        if(fetcher instanceof ReportDataProcessor)
        {
            List list = ((ReportDataProcessor)fetcher).getMainData(input);
            if(CommUtil.isNull(list))
                return null;
            else
                return new ExtDataSource(list);
        }
        LttsReportQueryExecuter executor = ((LttsReportDataProcessor)fetcher).getMainDataQueryExecuter(input);
        executor.setReportDataProcessor((LttsReportDataProcessor)fetcher);
        LttsReportDataSource ds = new LttsReportDataSource(executor);
        if(ds.hasNext())
            return ds;
        else
            return null;
    }

    private static Object getAssistendData(Object fetcher, Map input)
    {
        if(fetcher instanceof ReportDataProcessor)
            return ((ReportDataProcessor)fetcher).getAssistentData(input);
        else
            return ((LttsReportDataProcessor)fetcher).getAssistentData(input);
    }

    private static JasperPrint fillTemplate(JasperReport report, Map paraMap, JRDataSource ds)
        throws JRException
    {
        return JasperFillManager.fillReport(report, paraMap, ds);
    }

    public static String getPathPattern(String reportId)
    {
        return getReportById(reportId).getFilenamepattern();
    }

    public static String getFileNamePattern(String reportId)
    {
        return getReportById(reportId).getNamepattern();
    }

    public static String getFileSuffix(String reportId)
    {
        return getReportById(reportId).getReportType().toString();
    }

    public static String getReportLongName(String reportId)
    {
        return getReportById(reportId).getLongname();
    }

    public static Object getReportDataProcessor(Report report)
    {
        String className = report.getClazz();
        try
        {
            cn.sunline.adp.metadata.base.util.JavaClassModelUtil.JavaClassModel javaClass = new cn.sunline.adp.metadata.base.util.JavaClassModelUtil.JavaClassModel(report.getPackag(), (new StringBuilder()).append(report.getId()).append("ReportProcessor").toString());
            if(javaClass != null)
                className = (new StringBuilder()).append(javaClass.getPackage()).append(".").append(javaClass.getClassName()).toString();
            return ReflectionUtil.classForName(className).newInstance();
        }
        catch(Exception e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E032(report.getId(), report.getLongname(), className, e);
        }
    }

    public static Report getReportById(String reportId)
    {
        if(StringUtil.isEmpty(reportId))
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E033();
        Report report = (Report)ModelFactoryUtil.getModelFactory().getModel(cn/sunline/adp/metadata/model/report/Report, reportId);
        if(null == report)
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E034(reportId);
        else
            return report;
    }

    public static JasperReport getCompiledReport(String reportId)
    {
        return getCompiledReport(getReportById(reportId));
    }

    public static JasperReport getCompiledReport(Report report)
    {
        JasperReport J_report;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.start_record((new StringBuilder()).append("compileReport[").append(report.getId()).append("]").toString());
        J_report = null;
        JasperReport jasperreport;
        if(!StringUtil.isNotEmpty(compiledReport_cache.get(report.getId())))
            break MISSING_BLOCK_LABEL_110;
        jasperreport = (JasperReport)compiledReport_cache.get(report.getId());
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("compileReport[").append(report.getId()).append("]").toString());
        return jasperreport;
        try
        {
            Report currReport = getReportById(report.getId());
            String expotp = StringUtil.nullable(currReport.getReportType().getValue(), ReportTypeEnum.pdf.toString());
            InputStream template_url = getInputStreamByTemplatePath((new StringBuilder()).append("report/").append(currReport.getJaspertemplate()).toString(), expotp);
            if(template_url == null)
                throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E035(report.getId(), currReport.getJaspertemplate());
            J_report = getJasperReportByReportId(report.getId(), template_url);
            currReport.setPageWidth(J_report.getPageWidth());
            currReport.setPageHeight(J_report.getPageHeight());
            compiledReport_cache.put(report.getId(), J_report);
        }
        catch(JRException e)
        {
            throw cn.sunline.adp.cedar.busi.sdk.errors.BusiSDKErrorDef.SP_BS.E036(report.getId());
        }
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("compileReport[").append(report.getId()).append("]").toString());
        break MISSING_BLOCK_LABEL_309;
        Exception exception;
        exception;
        if(ProfileUtil.isProfileLogEnabled())
            ProfileUtil.end_record((new StringBuilder()).append("compileReport[").append(report.getId()).append("]").toString());
        throw exception;
        return J_report;
    }

    public static InputStream getInputStreamByTemplatePath(String path, String expotp)
    {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream((new StringBuilder()).append(path).append(".jrxml").toString());
    }

    public static JasperReport getJasperReportByReportId(String reportid, InputStream templateUri)
        throws JRException
    {
        return JasperCompileManager.compileReport(templateUri);
    }

    public static Object getExpressionValue(String expr, Object root, Map context)
    {
        return ExpressionEvaluatorFactory.getInstance().eval(expr, root, context);
    }

    private static final SysLog log = SysLogUtil.getSysLog(cn/sunline/adp/cedar/busi/sdk/biz/global/ReportUtil);
    private static final String REPORT_PROCESSOR_IMPL = ConstantValueManager.get().getValue("reportProcessor", "cn.sunline.ltts.busi.aplt.report.ReportProcessorImpl");
    private static Map compiledReport_cache = new ConcurrentHashMap(80);
    private static Map template_cache = new ConcurrentHashMap(80);
    private static Map jasperReport_cache = new ConcurrentHashMap(80);
    private static final String HEAD = "head";
    public static final String USER_CIPHER = "USER_PASSWORD";
    private static ThreadLocal context = new ThreadLocal();

}
