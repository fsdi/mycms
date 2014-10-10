/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import java.util.Locale;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.propertyeditors.LocaleEditor;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.servlet.LocaleResolver;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*    */ import org.springframework.web.servlet.support.RequestContextUtils;
/*    */ 
/*    */ public class FrontLocaleInterceptor extends HandlerInterceptorAdapter
/*    */ {
/*    */   public static final String LOCALE = "locale";
/*    */ 
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*    */     throws ServletException
/*    */   {
/* 30 */     LocaleResolver localeResolver = 
/* 31 */       RequestContextUtils.getLocaleResolver(request);
/* 32 */     if (localeResolver == null) {
/* 33 */       throw new IllegalStateException(
/* 34 */         "No LocaleResolver found: not in a DispatcherServlet request?");
/*    */     }
/* 36 */     CmsSite site = CmsUtils.getSite(request);
/* 37 */     String newLocale = site.getLocaleFront();
/* 38 */     LocaleEditor localeEditor = new LocaleEditor();
/* 39 */     localeEditor.setAsText(newLocale);
/* 40 */     localeResolver.setLocale(request, response, (Locale)
/* 41 */       localeEditor.getValue());
/*    */ 
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
/*    */     throws Exception
/*    */   {
/* 50 */     LocaleResolver localeResolver = 
/* 51 */       RequestContextUtils.getLocaleResolver(request);
/* 52 */     if (localeResolver == null) {
/* 53 */       throw new IllegalStateException(
/* 54 */         "No LocaleResolver found: not in a DispatcherServlet request?");
/*    */     }
/* 56 */     if (modelAndView != null)
/* 57 */       modelAndView.getModelMap().addAttribute("locale", 
/* 58 */         localeResolver.resolveLocale(request).toString());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.FrontLocaleInterceptor
 * JD-Core Version:    0.6.0
 */