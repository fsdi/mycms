/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsAcquisitionHistory;
/*    */ 
/*    */ public class CmsAcquisitionHistory extends BaseCmsAcquisitionHistory
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsAcquisitionHistory()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory(Integer id, String channelUrl, String contentUrl)
/*    */   {
/* 33 */     super(id, 
/* 32 */       channelUrl, 
/* 33 */       contentUrl);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsAcquisitionHistory
 * JD-Core Version:    0.6.0
 */