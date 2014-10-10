/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteTopicDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsVoteTopicDaoImpl extends HibernateBaseDao<CmsVoteTopic, Integer>
/*    */   implements CmsVoteTopicDao
/*    */ {
/*    */   public Pagination getPage(Integer siteId, int pageNo, int pageSize)
/*    */   {
/* 17 */     Finder f = Finder.create("from CmsVoteTopic bean where 1=1");
/* 18 */     if (siteId != null) {
/* 19 */       f.append(" and bean.site.id=:siteId");
/* 20 */       f.setParam("siteId", siteId);
/*    */     }
/* 22 */     f.append(" order by bean.id desc");
/* 23 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<CmsVoteTopic> getList(Boolean def, Integer siteId, int count)
/*    */   {
/* 28 */     Finder f = Finder.create("from CmsVoteTopic bean where 1=1");
/* 29 */     if (def != null) {
/* 30 */       if (def.booleanValue())
/* 31 */         f.append(" and bean.def=true");
/*    */       else {
/* 33 */         f.append(" and bean.def=false");
/*    */       }
/*    */     }
/* 36 */     if (siteId != null) {
/* 37 */       f.append(" and bean.site.id=:siteId");
/* 38 */       f.setParam("siteId", siteId);
/*    */     }
/* 40 */     f.append(" order by bean.id desc");
/* 41 */     f.setMaxResults(count);
/* 42 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic getDefTopic(Integer siteId) {
/* 46 */     Finder f = Finder.create("from CmsVoteTopic bean where 1=1");
/* 47 */     if (siteId != null) {
/* 48 */       f.append(" and bean.site.id=:siteId");
/* 49 */       f.setParam("siteId", siteId);
/*    */     }
/* 51 */     f.append(" and bean.def=true");
/* 52 */     f.setMaxResults(1);
/* 53 */     return (CmsVoteTopic)f.createQuery(getSession()).uniqueResult();
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic findById(Integer id) {
/* 57 */     CmsVoteTopic entity = (CmsVoteTopic)get(id);
/* 58 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic save(CmsVoteTopic bean) {
/* 62 */     getSession().save(bean);
/* 63 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteTopic deleteById(Integer id) {
/* 67 */     CmsVoteTopic entity = (CmsVoteTopic)super.get(id);
/* 68 */     if (entity != null) {
/* 69 */       getSession().delete(entity);
/*    */     }
/* 71 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsVoteTopic> getEntityClass()
/*    */   {
/* 76 */     return CmsVoteTopic.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsVoteTopicDaoImpl
 * JD-Core Version:    0.6.0
 */