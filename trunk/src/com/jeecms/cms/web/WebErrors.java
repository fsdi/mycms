/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.context.MessageSource;
/*    */ 
/*    */ public class WebErrors extends com.jeecms.core.web.WebErrors
/*    */ {
/*    */   public static WebErrors create(HttpServletRequest request)
/*    */   {
/* 20 */     return new WebErrors(request);
/*    */   }
/*    */ 
/*    */   public WebErrors() {
/*    */   }
/*    */ 
/*    */   public WebErrors(HttpServletRequest request) {
/* 27 */     super(request);
/*    */   }
/*    */ 
/*    */   public WebErrors(MessageSource messageSource, Locale locale)
/*    */   {
/* 37 */     super(messageSource, locale);
/*    */   }
/*    */ 
/*    */   public void notInSite(Class<?> clazz, Serializable id)
/*    */   {
/* 47 */     addErrorCode("error.notInSite", new Object[] { clazz.getSimpleName(), id });
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.WebErrors
 * JD-Core Version:    0.6.0
 */