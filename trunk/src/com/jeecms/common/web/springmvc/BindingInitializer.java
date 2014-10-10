/*    */ package com.jeecms.common.web.springmvc;
/*    */ 
/*    */ import java.util.Date;
/*    */ import org.springframework.web.bind.WebDataBinder;
/*    */ import org.springframework.web.bind.support.WebBindingInitializer;
/*    */ import org.springframework.web.context.request.WebRequest;
/*    */ 
/*    */ public class BindingInitializer
/*    */   implements WebBindingInitializer
/*    */ {
/*    */   public void initBinder(WebDataBinder binder, WebRequest request)
/*    */   {
/* 17 */     binder.registerCustomEditor(Date.class, new DateTypeEditor());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.springmvc.BindingInitializer
 * JD-Core Version:    0.6.0
 */