/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseContentTag;
/*    */ 
/*    */ public class ContentTag extends BaseContentTag
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/*  9 */     if (getCount() == null)
/* 10 */       setCount(Integer.valueOf(1));
/*    */   }
/*    */ 
/*    */   public ContentTag()
/*    */   {
/*    */   }
/*    */ 
/*    */   public ContentTag(Integer id)
/*    */   {
/* 23 */     super(id);
/*    */   }
/*    */ 
/*    */   public ContentTag(Integer id, String name, Integer count)
/*    */   {
/* 37 */     super(id, 
/* 36 */       name, 
/* 37 */       count);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentTag
 * JD-Core Version:    0.6.0
 */