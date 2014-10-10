/*    */ package com.jeecms.common.web.springmvc;
/*    */ 
/*    */ import javax.servlet.ServletContext;
/*    */ import org.springframework.stereotype.Component;
/*    */ import org.springframework.web.context.ServletContextAware;
/*    */ 
/*    */ @Component
/*    */ public class ServletContextRealPathResolver
/*    */   implements RealPathResolver, ServletContextAware
/*    */ {
/*    */   private ServletContext context;
/*    */ 
/*    */   public String get(String path)
/*    */   {
/* 12 */     String realpath = this.context.getRealPath(path);
/*    */ 
/* 14 */     if (realpath == null) {
/* 15 */       realpath = this.context.getRealPath("/") + path;
/*    */     }
/* 17 */     return realpath;
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext) {
/* 21 */     this.context = servletContext;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.springmvc.ServletContextRealPathResolver
 * JD-Core Version:    0.6.0
 */