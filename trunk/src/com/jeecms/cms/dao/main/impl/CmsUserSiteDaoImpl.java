/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsUserSiteDao;
/*    */ import com.jeecms.cms.entity.main.CmsUserSite;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsUserSiteDaoImpl extends HibernateBaseDao<CmsUserSite, Integer>
/*    */   implements CmsUserSiteDao
/*    */ {
/*    */   public CmsUserSite findById(Integer id)
/*    */   {
/* 13 */     CmsUserSite entity = (CmsUserSite)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsUserSite save(CmsUserSite bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 23 */     String hql = "delete from CmsUserSite bean where bean.site.id=:siteId";
/* 24 */     return getSession().createQuery(hql).setParameter("siteId", siteId)
/* 25 */       .executeUpdate();
/*    */   }
/*    */ 
/*    */   public CmsUserSite deleteById(Integer id) {
/* 29 */     CmsUserSite entity = (CmsUserSite)super.get(id);
/* 30 */     if (entity != null) {
/* 31 */       getSession().delete(entity);
/*    */     }
/* 33 */     return entity;
/*    */   }
/*    */ 
/*    */   public void delete(CmsUserSite entity) {
/* 37 */     getSession().delete(entity);
/*    */   }
/*    */ 
/*    */   protected Class<CmsUserSite> getEntityClass()
/*    */   {
/* 42 */     return CmsUserSite.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsUserSiteDaoImpl
 * JD-Core Version:    0.6.0
 */