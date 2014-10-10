/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsUserSite;
/*    */ 
/*    */ public class CmsUserSite extends BaseCmsUserSite
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsUserSite()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsUserSite(Integer id)
/*    */   {
/* 17 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsUserSite(Integer id, CmsUser user, CmsSite site, Byte checkStep, Boolean allChannel)
/*    */   {
/* 35 */     super(id, 
/* 32 */       user, 
/* 33 */       site, 
/* 34 */       checkStep, 
/* 35 */       allChannel);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsUserSite
 * JD-Core Version:    0.6.0
 */