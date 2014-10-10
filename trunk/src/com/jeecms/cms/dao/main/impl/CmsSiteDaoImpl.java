/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsSiteDao;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsSiteDaoImpl extends HibernateBaseDao<CmsSite, Integer>
/*    */   implements CmsSiteDao
/*    */ {
/*    */   public int siteCount(boolean cacheable)
/*    */   {
/* 17 */     String hql = "select count(*) from CmsSite bean";
/* 18 */     return 
/* 19 */       ((Number)getSession().createQuery(hql).setCacheable(cacheable)
/* 19 */       .iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   public List<CmsSite> getList(boolean cacheable)
/*    */   {
/* 24 */     String hql = "from CmsSite bean order by bean.id asc";
/* 25 */     return getSession().createQuery(hql).setCacheable(cacheable).list();
/*    */   }
/*    */ 
/*    */   public CmsSite findByDomain(String domain, boolean cacheable) {
/* 29 */     String hql = "from CmsSite bean where domain=:domain";
/* 30 */     Query query = getSession().createQuery(hql).setString("domain", domain);
/* 31 */     query.setCacheable(cacheable);
/* 32 */     return (CmsSite)query.uniqueResult();
/*    */   }
/*    */ 
/*    */   public CmsSite findById(Integer id) {
/* 36 */     CmsSite entity = (CmsSite)get(id);
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsSite save(CmsSite bean) {
/* 41 */     getSession().save(bean);
/* 42 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsSite deleteById(Integer id) {
/* 46 */     CmsSite entity = (CmsSite)super.get(id);
/* 47 */     if (entity != null) {
/* 48 */       getSession().delete(entity);
/*    */     }
/* 50 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsSite getByDomain(String domain) {
/* 54 */     String hql = "from CmsSite bean where bean.domain=?";
/* 55 */     return (CmsSite)findUniqueByProperty(hql, domain);
/*    */   }
/*    */ 
/*    */   protected Class<CmsSite> getEntityClass()
/*    */   {
/* 60 */     return CmsSite.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsSiteDaoImpl
 * JD-Core Version:    0.6.0
 */