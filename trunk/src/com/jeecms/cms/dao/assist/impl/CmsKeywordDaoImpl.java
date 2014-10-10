/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsKeywordDao;
/*    */ import com.jeecms.cms.entity.assist.CmsKeyword;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsKeywordDaoImpl extends HibernateBaseDao<CmsKeyword, Integer>
/*    */   implements CmsKeywordDao
/*    */ {
/*    */   public List<CmsKeyword> getList(Integer siteId, boolean onlyEnabled, boolean cacheable)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsKeyword bean where 1=1");
/* 19 */     if (siteId != null) {
/* 20 */       f.append(" and bean.site.id=:siteId");
/* 21 */       f.setParam("siteId", siteId);
/*    */     }
/* 23 */     if (onlyEnabled) {
/* 24 */       f.append(" and bean.disabled=false");
/*    */     }
/* 26 */     f.append(" order by bean.id desc");
/* 27 */     f.setCacheable(cacheable);
/* 28 */     return find(f);
/*    */   }
/*    */ 
/*    */   public List<CmsKeyword> getListGlobal(boolean onlyEnabled, boolean cacheable)
/*    */   {
/* 33 */     Finder f = 
/* 34 */       Finder.create("from CmsKeyword bean where bean.site.id is null");
/* 35 */     if (onlyEnabled) {
/* 36 */       f.append(" and bean.disabled=false");
/*    */     }
/* 38 */     f.append(" order by bean.id desc");
/* 39 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsKeyword findById(Integer id) {
/* 43 */     CmsKeyword entity = (CmsKeyword)get(id);
/* 44 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsKeyword save(CmsKeyword bean) {
/* 48 */     getSession().save(bean);
/* 49 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsKeyword deleteById(Integer id) {
/* 53 */     CmsKeyword entity = (CmsKeyword)super.get(id);
/* 54 */     if (entity != null) {
/* 55 */       getSession().delete(entity);
/*    */     }
/* 57 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsKeyword> getEntityClass()
/*    */   {
/* 62 */     return CmsKeyword.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsKeywordDaoImpl
 * JD-Core Version:    0.6.0
 */