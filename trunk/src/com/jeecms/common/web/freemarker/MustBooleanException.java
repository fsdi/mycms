/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.template.TemplateModelException;
/*    */ 
/*    */ public class MustBooleanException extends TemplateModelException
/*    */ {
/*    */   public MustBooleanException(String paramName)
/*    */   {
/* 11 */     super("The \"" + paramName + "\" parameter must be a boolean.");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.MustBooleanException
 * JD-Core Version:    0.6.0
 */