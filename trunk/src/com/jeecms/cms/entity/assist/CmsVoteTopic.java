/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsVoteTopic;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class CmsVoteTopic extends BaseCmsVoteTopic
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 12 */     if (getTotalCount() == null) {
/* 13 */       setTotalCount(Integer.valueOf(0));
/*    */     }
/* 15 */     if (getMultiSelect() == null) {
/* 16 */       setMultiSelect(Integer.valueOf(1));
/*    */     }
/* 18 */     if (getDef() == null) {
/* 19 */       setDef(Boolean.valueOf(false));
/*    */     }
/* 21 */     if (getDisabled() == null) {
/* 22 */       setDisabled(Boolean.valueOf(false));
/*    */     }
/* 24 */     if (getRestrictMember() == null) {
/* 25 */       setRestrictMember(Boolean.valueOf(false));
/*    */     }
/* 27 */     if (getRestrictIp() == null) {
/* 28 */       setRestrictIp(Boolean.valueOf(false));
/*    */     }
/* 30 */     if (getRestrictCookie() == null)
/* 31 */       setRestrictCookie(Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic(Integer id)
/*    */   {
/* 44 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic(Integer id, CmsSite site, String title, Integer totalCount, Integer multiSelect, Boolean restrictMember, Boolean restrictIp, Boolean restrictCookie, Boolean disabled, Boolean def)
/*    */   {
/* 72 */     super(id, 
/* 64 */       site, 
/* 65 */       title, 
/* 66 */       totalCount, 
/* 67 */       multiSelect, 
/* 68 */       restrictMember, 
/* 69 */       restrictIp, 
/* 70 */       restrictCookie, 
/* 71 */       disabled, 
/* 72 */       def);
/*    */   }
/*    */   public void addToSubTopics(CmsVoteSubTopic subTopic) {
/* 75 */     Set subTopics = getSubtopics();
/* 76 */     if (subTopics == null) {
/* 77 */       subTopics = new HashSet();
/* 78 */       setSubtopics(subTopics);
/*    */     }
/* 80 */     subTopics.add(subTopic);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsVoteTopic
 * JD-Core Version:    0.6.0
 */