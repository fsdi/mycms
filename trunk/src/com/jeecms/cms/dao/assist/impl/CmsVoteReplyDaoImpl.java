/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteReplyDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteReply;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsVoteReplyDaoImpl extends HibernateBaseDao<CmsVoteReply, Integer>
/*    */   implements CmsVoteReplyDao
/*    */ {
/*    */   public Pagination getPage(Integer subTopicId, int pageNo, int pageSize)
/*    */   {
/* 16 */     String hql = "select bean from CmsVoteReply bean";
/* 17 */     Finder f = Finder.create(hql);
/* 18 */     if (subTopicId != null) {
/* 19 */       f.append(" where bean.subTopic.id=:subTopicId").setParam("subTopicId", subTopicId);
/*    */     }
/* 21 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public CmsVoteReply findById(Integer id) {
/* 25 */     CmsVoteReply entity = (CmsVoteReply)get(id);
/* 26 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsVoteReply save(CmsVoteReply bean) {
/* 30 */     getSession().save(bean);
/* 31 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteReply deleteById(Integer id) {
/* 35 */     CmsVoteReply entity = (CmsVoteReply)super.get(id);
/* 36 */     if (entity != null) {
/* 37 */       getSession().delete(entity);
/*    */     }
/* 39 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsVoteReply> getEntityClass()
/*    */   {
/* 44 */     return CmsVoteReply.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsVoteReplyDaoImpl
 * JD-Core Version:    0.6.0
 */