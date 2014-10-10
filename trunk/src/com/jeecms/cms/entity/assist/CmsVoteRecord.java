/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsVoteRecord;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CmsVoteRecord extends BaseCmsVoteRecord
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsVoteRecord()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsVoteRecord(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsVoteRecord(Integer id, CmsVoteTopic topic, Date time, String ip, String cookie)
/*    */   {
/* 37 */     super(id, 
/* 34 */       topic, 
/* 35 */       time, 
/* 36 */       ip, 
/* 37 */       cookie);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsVoteRecord
 * JD-Core Version:    0.6.0
 */