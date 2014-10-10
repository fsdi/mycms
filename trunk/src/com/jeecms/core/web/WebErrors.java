/*    */ package com.jeecms.core.web;
/*    */ 
/*    */ import java.util.Locale;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.context.MessageSource;
/*    */ 
/*    */ public class WebErrors extends com.jeecms.common.web.springmvc.WebErrors
/*    */ {
/*    */   public static final String ERROR_PAGE = "/common/error_message";
/*    */   public static final String ERROR_ATTR_NAME = "errors";
/*    */ 
/*    */   public static WebErrors create(HttpServletRequest request)
/*    */   {
/* 27 */     return new WebErrors(request);
/*    */   }
/*    */ 
/*    */   public WebErrors() {
/*    */   }
/*    */ 
/*    */   public WebErrors(HttpServletRequest request) {
/* 34 */     super(request);
/*    */   }
/*    */ 
/*    */   public WebErrors(MessageSource messageSource, Locale locale)
/*    */   {
/* 44 */     super(messageSource, locale);
/*    */   }
/*    */ 
/*    */   protected String getErrorAttrName()
/*    */   {
/* 49 */     return "errors";
/*    */   }
/*    */ 
/*    */   protected String getErrorPage()
/*    */   {
/* 54 */     return "/common/error_message";
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.web.WebErrors
 * JD-Core Version:    0.6.0
 */