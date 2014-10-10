/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsSiteFlow;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ 
/*    */ public class CmsSiteFlow extends BaseCmsSiteFlow
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsSiteFlow()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsSiteFlow(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsSiteFlow(Integer id, CmsSite site, String accessIp, String accessDate, String accessPage, String sessionId)
/*    */   {
/* 39 */     super(id, 
/* 35 */       site, 
/* 36 */       accessIp, 
/* 37 */       accessDate, 
/* 38 */       accessPage, 
/* 39 */       sessionId);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsSiteFlow
 * JD-Core Version:    0.6.0
 */