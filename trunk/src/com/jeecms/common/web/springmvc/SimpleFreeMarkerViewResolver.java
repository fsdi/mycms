/*    */ package com.jeecms.common.web.springmvc;
/*    */ 
/*    */ import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
/*    */ import org.springframework.web.servlet.view.AbstractUrlBasedView;
/*    */ 
/*    */ public class SimpleFreeMarkerViewResolver extends AbstractTemplateViewResolver
/*    */ {
/*    */   public SimpleFreeMarkerViewResolver()
/*    */   {
/* 16 */     setViewClass(SimpleFreeMarkerView.class);
/*    */   }
/*    */ 
/*    */   protected AbstractUrlBasedView buildView(String viewName)
/*    */     throws Exception
/*    */   {
/* 24 */     AbstractUrlBasedView view = super.buildView(viewName);
/*    */ 
/* 26 */     if (viewName.startsWith("/")) {
/* 27 */       view.setUrl(viewName + getSuffix());
/*    */     }
/* 29 */     return view;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.springmvc.SimpleFreeMarkerViewResolver
 * JD-Core Version:    0.6.0
 */