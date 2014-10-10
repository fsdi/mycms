/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsKeyword;
/*    */ 
/*    */ public class CmsKeyword extends BaseCmsKeyword
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/*  9 */     if (getDisabled() == null)
/* 10 */       setDisabled(Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */   public CmsKeyword()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsKeyword(Integer id)
/*    */   {
/* 23 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsKeyword(Integer id, String name, String url, Boolean disabled)
/*    */   {
/* 39 */     super(id, 
/* 37 */       name, 
/* 38 */       url, 
/* 39 */       disabled);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsKeyword
 * JD-Core Version:    0.6.0
 */