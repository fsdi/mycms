/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAdvertisingDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsAdvertisingDaoImpl extends HibernateBaseDao<CmsAdvertising, Integer>
/*    */   implements CmsAdvertisingDao
/*    */ {
/*    */   public Pagination getPage(Integer siteId, Integer adspaceId, Boolean enabled, int pageNo, int pageSize)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsAdvertising bean where 1=1");
/* 19 */     if ((siteId != null) && (adspaceId == null)) {
/* 20 */       f.append(" and bean.site.id=:siteId");
/* 21 */       f.setParam("siteId", siteId);
/* 22 */     } else if (adspaceId != null) {
/* 23 */       f.append(" and bean.adspace.id=:adspaceId");
/* 24 */       f.setParam("adspaceId", adspaceId);
/*    */     }
/* 26 */     if (enabled != null) {
/* 27 */       f.append(" and bean.enabled=:enabled");
/* 28 */       f.setParam("enabled", enabled);
/*    */     }
/* 30 */     f.append(" order by bean.id desc");
/* 31 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<CmsAdvertising> getList(Integer adspaceId, Boolean enabled)
/*    */   {
/* 36 */     Finder f = Finder.create("from CmsAdvertising bean where 1=1");
/* 37 */     if (adspaceId != null) {
/* 38 */       f.append(" and bean.adspace.id=:adspaceId");
/* 39 */       f.setParam("adspaceId", adspaceId);
/*    */     }
/* 41 */     if (enabled != null) {
/* 42 */       f.append(" and bean.enabled=:enabled");
/* 43 */       f.setParam("enabled", enabled);
/*    */     }
/* 45 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsAdvertising findById(Integer id) {
/* 49 */     CmsAdvertising entity = (CmsAdvertising)get(id);
/* 50 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising save(CmsAdvertising bean) {
/* 54 */     getSession().save(bean);
/* 55 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising deleteById(Integer id) {
/* 59 */     CmsAdvertising entity = (CmsAdvertising)super.get(id);
/* 60 */     if (entity != null) {
/* 61 */       getSession().delete(entity);
/*    */     }
/* 63 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsAdvertising> getEntityClass()
/*    */   {
/* 68 */     return CmsAdvertising.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsAdvertisingDaoImpl
 * JD-Core Version:    0.6.0
 */