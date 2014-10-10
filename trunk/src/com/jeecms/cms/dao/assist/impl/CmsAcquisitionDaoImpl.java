/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAcquisitionDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsAcquisitionDaoImpl extends HibernateBaseDao<CmsAcquisition, Integer>
/*    */   implements CmsAcquisitionDao
/*    */ {
/*    */   public List<CmsAcquisition> getList(Integer siteId)
/*    */   {
/* 20 */     Finder f = Finder.create("from CmsAcquisition bean");
/* 21 */     if (siteId != null) {
/* 22 */       f.append(" where bean.site.id=:siteId");
/* 23 */       f.setParam("siteId", siteId);
/*    */     }
/* 25 */     f.append(" order by bean.id asc");
/* 26 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsAcquisition findById(Integer id) {
/* 30 */     CmsAcquisition entity = (CmsAcquisition)get(id);
/* 31 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAcquisition save(CmsAcquisition bean) {
/* 35 */     getSession().save(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisition deleteById(Integer id) {
/* 40 */     CmsAcquisition entity = (CmsAcquisition)super.get(id);
/* 41 */     if (entity != null) {
/* 42 */       getSession().delete(entity);
/*    */     }
/* 44 */     return entity;
/*    */   }
/*    */ 
/*    */   public int countByChannelId(Integer channelId) {
/* 48 */     String hql = "select count(*) from CmsAcquisition bean where bean.channel.id=:channelId";
/*    */ 
/* 50 */     Query query = getSession().createQuery(hql);
/* 51 */     query.setParameter("channelId", channelId);
/* 52 */     return ((Number)query.iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   public CmsAcquisition getStarted(Integer siteId) {
/* 56 */     Criteria crit = createCriteria(new Criterion[] { 
/* 57 */       Restrictions.eq("status", Integer.valueOf(1)), 
/* 58 */       Restrictions.eq("site.id", siteId) }).setMaxResults(1);
/* 59 */     return (CmsAcquisition)crit.uniqueResult();
/*    */   }
/*    */ 
/*    */   public Integer getMaxQueue(Integer siteId) {
/* 63 */     Query query = createQuery("select max(bean.queue) from CmsAcquisition bean where bean.site.id=?", new Object[] { siteId });
/* 64 */     return Integer.valueOf(((Number)query.iterate().next()).intValue());
/*    */   }
/*    */ 
/*    */   public List<CmsAcquisition> getLargerQueues(Integer siteId, Integer queueNum)
/*    */   {
/* 69 */     Finder f = Finder.create("from CmsAcquisition bean where bean.queue>:queueNum and bean.site.id=:siteId")
/* 70 */       .setParam("queueNum", queueNum)
/* 71 */       .setParam("siteId", siteId);
/* 72 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsAcquisition popAcquFromQueue(Integer siteId) {
/* 76 */     Query query = getSession().createQuery("from CmsAcquisition bean where bean.queue>0 and bean.site.id=:siteId order by bean.queue")
/* 77 */       .setParameter("siteId", siteId).setMaxResults(1);
/* 78 */     return (CmsAcquisition)query.uniqueResult();
/*    */   }
/*    */ 
/*    */   protected Class<CmsAcquisition> getEntityClass()
/*    */   {
/* 83 */     return CmsAcquisition.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsAcquisitionDaoImpl
 * JD-Core Version:    0.6.0
 */