/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAcquisitionHistoryDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsAcquisitionHistoryDaoImpl extends HibernateBaseDao<CmsAcquisitionHistory, Integer>
/*    */   implements CmsAcquisitionHistoryDao
/*    */ {
/*    */   public List<CmsAcquisitionHistory> getList(Integer siteId, Integer acquId)
/*    */   {
/* 21 */     Finder f = Finder.create("from CmsAcquisitionHistory bean where 1=1");
/* 22 */     if (siteId != null) {
/* 23 */       f.append(" and bean.acquisition.site.id=:siteId");
/* 24 */       f.setParam("siteId", siteId);
/*    */     }
/* 26 */     if (acquId != null) {
/* 27 */       f.append(" and bean.acquisition.id=:acquId");
/* 28 */       f.setParam("acquId", acquId);
/*    */     }
/* 30 */     f.append(" order by bean.id asc");
/* 31 */     return find(f);
/*    */   }
/*    */ 
/*    */   public Pagination getPage(Integer siteId, Integer acquId, Integer pageNo, Integer pageSize)
/*    */   {
/* 36 */     Finder f = Finder.create("from CmsAcquisitionHistory bean where 1=1");
/* 37 */     if (siteId != null) {
/* 38 */       f.append(" and bean.acquisition.site.id=:siteId");
/* 39 */       f.setParam("siteId", siteId);
/*    */     }
/* 41 */     if (acquId != null) {
/* 42 */       f.append(" and bean.acquisition.id=:acquId");
/* 43 */       f.setParam("acquId", acquId);
/*    */     }
/* 45 */     f.append(" order by bean.id desc");
/* 46 */     return find(f, pageNo.intValue(), pageSize.intValue());
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory findById(Integer id) {
/* 50 */     CmsAcquisitionHistory entity = (CmsAcquisitionHistory)get(id);
/* 51 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory save(CmsAcquisitionHistory bean) {
/* 55 */     getSession().save(bean);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory deleteById(Integer id) {
/* 60 */     CmsAcquisitionHistory entity = (CmsAcquisitionHistory)super.get(id);
/* 61 */     if (entity != null) {
/* 62 */       getSession().delete(entity);
/*    */     }
/* 64 */     return entity;
/*    */   }
/*    */ 
/*    */   public Boolean checkExistByProperties(Boolean title, String value) {
/* 68 */     Criteria crit = createCriteria(new Criterion[0]);
/* 69 */     if (title.booleanValue())
/* 70 */       crit.add(Restrictions.eq("title", value));
/*    */     else {
/* 72 */       crit.add(Restrictions.eq("contentUrl", value));
/*    */     }
/* 74 */     return Boolean.valueOf(crit.list().size() > 0);
/*    */   }
/*    */ 
/*    */   protected Class<CmsAcquisitionHistory> getEntityClass()
/*    */   {
/* 79 */     return CmsAcquisitionHistory.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsAcquisitionHistoryDaoImpl
 * JD-Core Version:    0.6.0
 */