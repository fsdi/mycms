/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsDictionary;
/*    */ 
/*    */ public class CmsDictionary extends BaseCmsDictionary
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsDictionary()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsDictionary(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsDictionary(Integer id, String name, String value, String type)
/*    */   {
/* 35 */     super(id, 
/* 33 */       name, 
/* 34 */       value, 
/* 35 */       type);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsDictionary
 * JD-Core Version:    0.6.0
 */