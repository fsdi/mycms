/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import freemarker.template.TemplateModelException;
/*    */ import freemarker.template.TemplateNumberModel;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Map;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ProcessTimeDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(ProcessTimeDirective.class);
/* 29 */   private static final DecimalFormat FORMAT = new DecimalFormat("0.000");
/*    */ 
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 34 */     long time = getStartTime(env);
/* 35 */     if (time != -1L) {
/* 36 */       time = System.currentTimeMillis() - time;
/* 37 */       Writer out = env.getOut();
/* 38 */       out.append("Processed in " + FORMAT.format((float)time / 1000.0F) + 
/* 39 */         " second(s)");
/*    */     }
/*    */   }
/*    */ 
/*    */   private long getStartTime(Environment env) throws TemplateModelException {
/* 44 */     TemplateModel startTime = env.getGlobalVariable("_start_time");
/* 45 */     if (startTime == null) {
/* 46 */       log.warn("Variable '{}' not found in GlobalVariable", "_start_time");
/* 47 */       return -1L;
/*    */     }
/* 49 */     if ((startTime instanceof TemplateNumberModel)) {
/* 50 */       return ((TemplateNumberModel)startTime).getAsNumber().longValue();
/*    */     }
/* 52 */     throw new MustNumberException("_start_time");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.ProcessTimeDirective
 * JD-Core Version:    0.6.0
 */