/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.template.TemplateModelException;
/*    */ 
/*    */ public class MustNumberException extends TemplateModelException
/*    */ {
/*    */   public MustNumberException(String paramName)
/*    */   {
/* 11 */     super("The \"" + paramName + "\" parameter must be a number.");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.MustNumberException
 * JD-Core Version:    0.6.0
 */