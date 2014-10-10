/*    */ package com.jeecms.common.web.springmvc;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
/*    */ 
/*    */ public class RichFreeMarkerView extends FreeMarkerView
/*    */ {
/*    */   public static final String CONTEXT_PATH = "base";
/*    */ 
/*    */   protected void exposeHelpers(Map model, HttpServletRequest request)
/*    */     throws Exception
/*    */   {
/* 26 */     super.exposeHelpers(model, request);
/* 27 */     model.put("base", request.getContextPath());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.springmvc.RichFreeMarkerView
 * JD-Core Version:    0.6.0
 */