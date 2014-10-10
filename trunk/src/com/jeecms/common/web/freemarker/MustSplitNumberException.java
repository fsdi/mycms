/*    */ package com.jeecms.common.web.freemarker;
/*    */ 
/*    */ import freemarker.template.TemplateModelException;
/*    */ 
/*    */ public class MustSplitNumberException extends TemplateModelException
/*    */ {
/*    */   public MustSplitNumberException(String paramName)
/*    */   {
/* 12 */     super("The \"" + paramName + 
/* 12 */       "\" parameter must be a number split by ','");
/*    */   }
/*    */ 
/*    */   public MustSplitNumberException(String paramName, Exception cause)
/*    */   {
/* 17 */     super("The \"" + paramName + 
/* 17 */       "\" parameter must be a number split by ','", cause);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.freemarker.MustSplitNumberException
 * JD-Core Version:    0.6.0
 */