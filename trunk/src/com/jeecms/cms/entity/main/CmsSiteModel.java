/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsSiteModel;
/*    */ 
/*    */ public class CmsSiteModel extends BaseCmsSiteModel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsSiteModel()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsSiteModel(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsSiteModel(Integer id, String field, String label, Integer priority)
/*    */   {
/* 35 */     super(id, 
/* 33 */       field, 
/* 34 */       label, 
/* 35 */       priority);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsSiteModel
 * JD-Core Version:    0.6.0
 */