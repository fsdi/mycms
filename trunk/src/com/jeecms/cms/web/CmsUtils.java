/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class CmsUtils
/*    */ {
/*    */   public static final String USER_KEY = "_user_key";
/*    */   public static final String SITE_KEY = "_site_key";
/*    */ 
/*    */   public static CmsUser getUser(HttpServletRequest request)
/*    */   {
/* 30 */     return (CmsUser)request.getAttribute("_user_key");
/*    */   }
/*    */ 
/*    */   public static Integer getUserId(HttpServletRequest request)
/*    */   {
/* 40 */     CmsUser user = getUser(request);
/* 41 */     if (user != null) {
/* 42 */       return user.getId();
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   public static void setUser(HttpServletRequest request, CmsUser user)
/*    */   {
/* 55 */     request.setAttribute("_user_key", user);
/*    */   }
/*    */ 
/*    */   public static CmsSite getSite(HttpServletRequest request)
/*    */   {
/* 65 */     return (CmsSite)request.getAttribute("_site_key");
/*    */   }
/*    */ 
/*    */   public static void setSite(HttpServletRequest request, CmsSite site)
/*    */   {
/* 75 */     request.setAttribute("_site_key", site);
/*    */   }
/*    */ 
/*    */   public static Integer getSiteId(HttpServletRequest request)
/*    */   {
/* 85 */     return getSite(request).getId();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.CmsUtils
 * JD-Core Version:    0.6.0
 */