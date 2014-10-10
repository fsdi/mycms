/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsAdvertisingSpace;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ 
/*    */ public class CmsAdvertisingSpace extends BaseCmsAdvertisingSpace
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsAdvertisingSpace()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace(Integer id, CmsSite site, String name, Boolean enabled)
/*    */   {
/* 35 */     super(id, 
/* 33 */       site, 
/* 34 */       name, 
/* 35 */       enabled);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsAdvertisingSpace
 * JD-Core Version:    0.6.0
 */