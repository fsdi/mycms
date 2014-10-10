/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.template.TemplateModelException;
/*    */ 
/*    */ public class ParamsRequiredException extends TemplateModelException
/*    */ {
/*    */   public ParamsRequiredException(String paramName)
/*    */   {
/* 11 */     super("The required \"" + paramName + "\" paramter is missing.");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.ParamsRequiredException
 * JD-Core Version:    0.6.0
 */