/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteRecordDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteRecord;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsVoteRecordDaoImpl extends HibernateBaseDao<CmsVoteRecord, Integer>
/*    */   implements CmsVoteRecordDao
/*    */ {
/*    */   public CmsVoteRecord save(CmsVoteRecord bean)
/*    */   {
/* 16 */     getSession().save(bean);
/* 17 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteByTopic(Integer topicId) {
/* 21 */     String hql = "delete from CmsVoteRecord bean where bean.topic.id=:topicId";
/*    */ 
/* 23 */     return getSession().createQuery(hql).setParameter("topicId", topicId)
/* 24 */       .executeUpdate();
/*    */   }
/*    */ 
/*    */   public CmsVoteRecord findByUserId(Integer userId, Integer topicId)
/*    */   {
/* 29 */     String hql = "from CmsVoteRecord bean where bean.user.id=:userId and bean.topic.id=:topicId order by bean.time desc";
/*    */ 
/* 31 */     List list = getSession().createQuery(hql).setParameter(
/* 32 */       "userId", userId).setParameter("topicId", topicId)
/* 33 */       .setMaxResults(1).list();
/* 34 */     return list.size() > 0 ? (CmsVoteRecord)list.get(0) : null;
/*    */   }
/*    */ 
/*    */   public CmsVoteRecord findByIp(String ip, Integer topicId)
/*    */   {
/* 39 */     String hql = "from CmsVoteRecord bean where bean.ip=:ip and bean.topic.id=:topicId order by bean.time desc";
/*    */ 
/* 41 */     List list = getSession().createQuery(hql).setParameter(
/* 42 */       "ip", ip).setParameter("topicId", topicId).setMaxResults(1)
/* 43 */       .list();
/* 44 */     return list.size() > 0 ? (CmsVoteRecord)list.get(0) : null;
/*    */   }
/*    */ 
/*    */   public CmsVoteRecord findByCookie(String cookie, Integer topicId)
/*    */   {
/* 49 */     String hql = "from CmsVoteRecord bean where bean.cookie=:cookie and bean.topic.id=:topicId order by bean.time desc";
/*    */ 
/* 51 */     List list = getSession().createQuery(hql).setParameter(
/* 52 */       "cookie", cookie).setParameter("topicId", topicId)
/* 53 */       .setMaxResults(1).list();
/* 54 */     return list.size() > 0 ? (CmsVoteRecord)list.get(0) : null;
/*    */   }
/*    */ 
/*    */   protected Class<CmsVoteRecord> getEntityClass()
/*    */   {
/* 59 */     return CmsVoteRecord.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsVoteRecordDaoImpl
 * JD-Core Version:    0.6.0
 */