/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.core.Environment;
/*    */ import freemarker.template.TemplateDirectiveBody;
/*    */ import freemarker.template.TemplateDirectiveModel;
/*    */ import freemarker.template.TemplateException;
/*    */ import freemarker.template.TemplateModel;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class UUIDDirective
/*    */   implements TemplateDirectiveModel
/*    */ {
/*    */   public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
/*    */     throws TemplateException, IOException
/*    */   {
/* 22 */     String uuid = UUID.randomUUID().toString();
/* 23 */     uuid = StringUtils.remove(uuid, '-');
/* 24 */     env.getOut().append(uuid);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.UUIDDirective
 * JD-Core Version:    0.6.0
 */