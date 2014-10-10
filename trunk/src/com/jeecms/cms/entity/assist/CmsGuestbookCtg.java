/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsGuestbookCtg;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ 
/*    */ public class CmsGuestbookCtg extends BaseCmsGuestbookCtg
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsGuestbookCtg()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg(Integer id, CmsSite site, String name, Integer priority)
/*    */   {
/* 35 */     super(id, 
/* 33 */       site, 
/* 34 */       name, 
/* 35 */       priority);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsGuestbookCtg
 * JD-Core Version:    0.6.0
 */