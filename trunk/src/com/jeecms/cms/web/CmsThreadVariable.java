/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ 
/*    */ public class CmsThreadVariable
/*    */ {
/* 13 */   private static ThreadLocal<CmsUser> cmsUserVariable = new ThreadLocal();
/*    */ 
/* 17 */   private static ThreadLocal<CmsSite> cmsSiteVariable = new ThreadLocal();
/*    */ 
/*    */   public static CmsUser getUser()
/*    */   {
/* 25 */     return (CmsUser)cmsUserVariable.get();
/*    */   }
/*    */ 
/*    */   public static void setUser(CmsUser user)
/*    */   {
/* 34 */     cmsUserVariable.set(user);
/*    */   }
/*    */ 
/*    */   public static void removeUser()
/*    */   {
/* 41 */     cmsUserVariable.remove();
/*    */   }
/*    */ 
/*    */   public static CmsSite getSite()
/*    */   {
/* 50 */     return (CmsSite)cmsSiteVariable.get();
/*    */   }
/*    */ 
/*    */   public static void setSite(CmsSite site)
/*    */   {
/* 59 */     cmsSiteVariable.set(site);
/*    */   }
/*    */ 
/*    */   public static void removeSite()
/*    */   {
/* 66 */     cmsSiteVariable.remove();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.CmsThreadVariable
 * JD-Core Version:    0.6.0
 */