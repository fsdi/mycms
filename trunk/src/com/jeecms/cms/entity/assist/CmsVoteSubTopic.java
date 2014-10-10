/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsVoteSubTopic;
/*    */ import com.jeecms.common.hibernate3.PriorityInterface;
/*    */ 
/*    */ public class CmsVoteSubTopic extends BaseCmsVoteSubTopic
/*    */   implements PriorityInterface, Comparable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public boolean getIsRadio()
/*    */   {
/* 15 */     return getType().intValue() == 1;
/*    */   }
/*    */ 
/*    */   public boolean getIsMulti()
/*    */   {
/* 22 */     return getType().intValue() == 2;
/*    */   }
/*    */ 
/*    */   public boolean getIsText()
/*    */   {
/* 29 */     return getType().intValue() == 3;
/*    */   }
/*    */ 
/*    */   public int compareTo(Object o)
/*    */   {
/* 36 */     return getPriority().intValue();
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic(Integer id)
/*    */   {
/* 48 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic(Integer id, CmsVoteTopic voteTopic, String title, Integer type, Integer priority)
/*    */   {
/* 66 */     super(id, 
/* 63 */       voteTopic, 
/* 64 */       title, 
/* 65 */       type, 
/* 66 */       priority);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsVoteSubTopic
 * JD-Core Version:    0.6.0
 */