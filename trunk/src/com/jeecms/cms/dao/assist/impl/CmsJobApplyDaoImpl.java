/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsJobApplyDao;
/*    */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsJobApplyDaoImpl extends HibernateBaseDao<CmsJobApply, Integer>
/*    */   implements CmsJobApplyDao
/*    */ {
/*    */   public Pagination getPage(Integer userId, Integer contentId, Integer siteId, boolean cacheable, int pageNo, int pageSize)
/*    */   {
/* 16 */     Finder f = Finder.create("from CmsJobApply apply where 1=1 ");
/* 17 */     if (userId != null) {
/* 18 */       f.append(" and apply.user.id=:userId").setParam("userId", userId);
/*    */     }
/* 20 */     if (contentId != null) {
/* 21 */       f.append(" and apply.content.id=:contentId").setParam("contentId", 
/* 22 */         contentId);
/*    */     }
/* 24 */     if (siteId != null) {
/* 25 */       f.append(" and apply.content.site.id=:siteId").setParam("siteId", 
/* 26 */         siteId);
/*    */     }
/* 28 */     f.setCacheable(cacheable);
/* 29 */     Pagination page = find(f, pageNo, pageSize);
/* 30 */     return page;
/*    */   }
/*    */ 
/*    */   public CmsJobApply findById(Integer id) {
/* 34 */     CmsJobApply entity = (CmsJobApply)get(id);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsJobApply save(CmsJobApply bean) {
/* 39 */     getSession().save(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsJobApply deleteById(Integer id) {
/* 44 */     CmsJobApply entity = (CmsJobApply)super.get(id);
/* 45 */     if (entity != null) {
/* 46 */       getSession().delete(entity);
/*    */     }
/* 48 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsJobApply> getEntityClass()
/*    */   {
/* 53 */     return CmsJobApply.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsJobApplyDaoImpl
 * JD-Core Version:    0.6.0
 */