/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsAcquisitionTemp;
/*    */ 
/*    */ public class CmsAcquisitionTemp extends BaseCmsAcquisitionTemp
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsAcquisitionTemp()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp(Integer id, String channelUrl, String contentUrl, Integer percent, String description, Integer seq)
/*    */   {
/* 39 */     super(id, 
/* 35 */       channelUrl, 
/* 36 */       contentUrl, 
/* 37 */       percent, 
/* 38 */       description, 
/* 39 */       seq);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsAcquisitionTemp
 * JD-Core Version:    0.6.0
 */