/*     */ package com.jeecms.cms.dao.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsTopicDao;
/*     */ import com.jeecms.cms.entity.main.CmsTopic;
/*     */ import com.jeecms.common.hibernate3.Finder;
/*     */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CmsTopicDaoImpl extends HibernateBaseDao<CmsTopic, Integer>
/*     */   implements CmsTopicDao
/*     */ {
/*     */   public List<CmsTopic> getList(Integer channelId, boolean recommend, Integer count, boolean cacheable)
/*     */   {
/*  20 */     Finder f = Finder.create("from CmsTopic bean where 1=1");
/*  21 */     if (channelId != null) {
/*  22 */       f.append(" and bean.channel.id=:channelId");
/*  23 */       f.setParam("channelId", channelId);
/*     */     }
/*  25 */     if (recommend) {
/*  26 */       f.append(" and bean.recommend=true");
/*     */     }
/*  28 */     f.append(" order by bean.priority asc,bean.id desc");
/*  29 */     if (count != null) {
/*  30 */       f.setMaxResults(count.intValue());
/*     */     }
/*  32 */     f.setCacheable(cacheable);
/*  33 */     return find(f);
/*     */   }
/*     */ 
/*     */   public Pagination getPage(Integer channelId, boolean recommend, int pageNo, int pageSize, boolean cacheable)
/*     */   {
/*  38 */     Finder f = Finder.create("from CmsTopic bean where 1=1");
/*  39 */     if (channelId != null) {
/*  40 */       f.append(" and bean.channel.id=:channelId");
/*  41 */       f.setParam("channelId", channelId);
/*     */     }
/*  43 */     if (recommend) {
/*  44 */       f.append(" and bean.recommend=true");
/*     */     }
/*  46 */     f.append(" order by bean.priority asc,bean.id desc");
/*  47 */     return find(f, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public List<CmsTopic> getListByChannelIds(Integer[] channelIds)
/*     */   {
/*  52 */     String hql = "from CmsTopic bean where bean.channel.id in (:ids) order by bean.id asc";
/*  53 */     return getSession().createQuery(hql)
/*  54 */       .setParameterList("ids", channelIds).list();
/*     */   }
/*     */ 
/*     */   public List<CmsTopic> getListByChannelId(Integer channelId)
/*     */   {
/*  59 */     String hql = "select bean from CmsTopic bean inner join bean.channel as node,Channel parent where node.lft between parent.lft and parent.rgt and parent.id=? order by bean.priority asc,bean.id desc";
/*     */ 
/*  63 */     return find(hql, new Object[] { channelId });
/*     */   }
/*     */ 
/*     */   public List<CmsTopic> getGlobalTopicList()
/*     */   {
/*  68 */     String hql = "from CmsTopic bean where bean.channel.id is null order by bean.priority asc,bean.id desc";
/*     */ 
/*  70 */     return find(hql, new Object[0]);
/*     */   }
/*     */ 
/*     */   public CmsTopic findById(Integer id) {
/*  74 */     CmsTopic entity = (CmsTopic)get(id);
/*  75 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsTopic save(CmsTopic bean) {
/*  79 */     getSession().save(bean);
/*  80 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTopic deleteById(Integer id) {
/*  84 */     CmsTopic entity = (CmsTopic)super.get(id);
/*  85 */     if (entity != null) {
/*  86 */       getSession().delete(entity);
/*     */     }
/*  88 */     return entity;
/*     */   }
/*     */ 
/*     */   public int deleteContentRef(Integer id) {
/*  92 */     Query query = getSession().getNamedQuery("CmsTopic.deleteContentRef");
/*  93 */     return query.setParameter(0, id).executeUpdate();
/*     */   }
/*     */ 
/*     */   public int countByChannelId(Integer channelId) {
/*  97 */     String hql = "select count(*) from CmsTopic bean where bean.channel.id=:channelId";
/*     */ 
/*  99 */     Query query = getSession().createQuery(hql);
/* 100 */     query.setParameter("channelId", channelId);
/* 101 */     return ((Number)query.iterate().next()).hashCode();
/*     */   }
/*     */ 
/*     */   protected Class<CmsTopic> getEntityClass()
/*     */   {
/* 106 */     return CmsTopic.class;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsTopicDaoImpl
 * JD-Core Version:    0.6.0
 */