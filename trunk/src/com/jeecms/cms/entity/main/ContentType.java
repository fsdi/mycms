/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseContentType;
/*    */ 
/*    */ public class ContentType extends BaseContentType
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public ContentType()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ContentType(Integer id)
/*    */   {
/* 17 */     super(id);
/*    */   }
/*    */ 
/*    */   public ContentType(Integer id, String name, Boolean hasImage, Boolean disabled)
/*    */   {
/* 33 */     super(id, 
/* 31 */       name, 
/* 32 */       hasImage, 
/* 33 */       disabled);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentType
 * JD-Core Version:    0.6.0
 */