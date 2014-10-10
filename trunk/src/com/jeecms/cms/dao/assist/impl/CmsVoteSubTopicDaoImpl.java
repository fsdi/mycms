/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteSubTopicDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsVoteSubTopicDaoImpl extends HibernateBaseDao<CmsVoteSubTopic, Integer>
/*    */   implements CmsVoteSubTopicDao
/*    */ {
/*    */   public List<CmsVoteSubTopic> findByVoteTopic(Integer voteTopicId)
/*    */   {
/* 18 */     String hql = "select bean from CmsVoteSubTopic bean";
/* 19 */     Finder finder = Finder.create(hql);
/* 20 */     if (voteTopicId != null) {
/* 21 */       finder.append(" where bean.voteTopic.id=:voteTopicId").setParam("voteTopicId", voteTopicId);
/*    */     }
/* 23 */     finder.append(" order by  bean.priority asc,bean.id desc");
/* 24 */     return find(finder);
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic findById(Integer id) {
/* 28 */     CmsVoteSubTopic entity = (CmsVoteSubTopic)get(id);
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic save(CmsVoteSubTopic bean) {
/* 33 */     getSession().save(bean);
/* 34 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteSubTopic deleteById(Integer id) {
/* 38 */     CmsVoteSubTopic entity = (CmsVoteSubTopic)super.get(id);
/* 39 */     if (entity != null) {
/* 40 */       getSession().delete(entity);
/*    */     }
/* 42 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsVoteSubTopic> getEntityClass()
/*    */   {
/* 47 */     return CmsVoteSubTopic.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsVoteSubTopicDaoImpl
 * JD-Core Version:    0.6.0
 */