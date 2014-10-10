/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsVoteItem;
/*    */ import com.jeecms.common.hibernate3.PriorityInterface;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class CmsVoteItem extends BaseCmsVoteItem
/*    */   implements PriorityInterface
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public int getPercent()
/*    */   {
/* 23 */     Integer totalCount = Integer.valueOf(0);
/* 24 */     Set<CmsVoteItem> subTopicVoteItems = getSubTopic().getVoteItems();
/* 25 */     for (CmsVoteItem vote : subTopicVoteItems) {
/* 26 */       totalCount = Integer.valueOf(totalCount.intValue() + vote.getVoteCount().intValue());
/*    */     }
/* 28 */     if ((totalCount != null) && (totalCount.intValue() != 0)) {
/* 29 */       return getVoteCount().intValue() * 100 / totalCount.intValue();
/*    */     }
/* 31 */     return 0;
/*    */   }
/*    */ 
/*    */   public void init()
/*    */   {
/* 36 */     if (getPriority() == null) {
/* 37 */       setPriority(Integer.valueOf(10));
/*    */     }
/* 39 */     if (getVoteCount() == null)
/* 40 */       setVoteCount(Integer.valueOf(0));
/*    */   }
/*    */ 
/*    */   public CmsVoteItem()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsVoteItem(Integer id)
/*    */   {
/* 53 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsVoteItem(Integer id, CmsVoteTopic topic, String title, Integer voteCount, Integer priority)
/*    */   {
/* 64 */     super(id, topic, title, voteCount, priority);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsVoteItem
 * JD-Core Version:    0.6.0
 */