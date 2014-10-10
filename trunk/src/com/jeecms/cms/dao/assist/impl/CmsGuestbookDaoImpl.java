/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsGuestbookDao;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsGuestbookDaoImpl extends HibernateBaseDao<CmsGuestbook, Integer>
/*    */   implements CmsGuestbookDao
/*    */ {
/*    */   public Pagination getPage(Integer siteId, Integer ctgId, Integer userId, Boolean recommend, Boolean checked, boolean asc, boolean cacheable, int pageNo, int pageSize)
/*    */   {
/* 19 */     Finder f = createFinder(siteId, ctgId, userId, recommend, checked, asc, 
/* 20 */       cacheable);
/* 21 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<CmsGuestbook> getList(Integer siteId, Integer ctgId, Boolean recommend, Boolean checked, boolean desc, boolean cacheable, int first, int max)
/*    */   {
/* 28 */     Finder f = createFinder(siteId, ctgId, null, recommend, checked, desc, 
/* 29 */       cacheable);
/* 30 */     f.setFirstResult(first);
/* 31 */     f.setMaxResults(max);
/* 32 */     return find(f);
/*    */   }
/*    */ 
/*    */   private Finder createFinder(Integer siteId, Integer ctgId, Integer userId, Boolean recommend, Boolean checked, boolean desc, boolean cacheable)
/*    */   {
/* 37 */     Finder f = Finder.create("from CmsGuestbook bean where 1=1");
/* 38 */     if (siteId != null) {
/* 39 */       f.append(" and bean.site.id=:siteId");
/* 40 */       f.setParam("siteId", siteId);
/*    */     }
/* 42 */     if (ctgId != null) {
/* 43 */       f.append(" and bean.ctg.id=:ctgId");
/* 44 */       f.setParam("ctgId", ctgId);
/*    */     }
/* 46 */     if (userId != null) {
/* 47 */       f.append(" and bean.member.id=:userId");
/* 48 */       f.setParam("userId", userId);
/*    */     }
/* 50 */     if (recommend != null) {
/* 51 */       f.append(" and bean.recommend=:recommend");
/* 52 */       f.setParam("recommend", recommend);
/*    */     }
/* 54 */     if (checked != null) {
/* 55 */       f.append(" and bean.checked=:checked");
/* 56 */       f.setParam("checked", checked);
/*    */     }
/* 58 */     if (desc)
/* 59 */       f.append(" order by bean.id desc");
/*    */     else {
/* 61 */       f.append(" order by bean.id asc");
/*    */     }
/* 63 */     f.setCacheable(cacheable);
/* 64 */     return f;
/*    */   }
/*    */ 
/*    */   public CmsGuestbook findById(Integer id) {
/* 68 */     CmsGuestbook entity = (CmsGuestbook)get(id);
/* 69 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsGuestbook save(CmsGuestbook bean) {
/* 73 */     getSession().save(bean);
/* 74 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbook deleteById(Integer id) {
/* 78 */     CmsGuestbook entity = (CmsGuestbook)super.get(id);
/* 79 */     if (entity != null) {
/* 80 */       getSession().delete(entity);
/*    */     }
/* 82 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsGuestbook> getEntityClass()
/*    */   {
/* 87 */     return CmsGuestbook.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsGuestbookDaoImpl
 * JD-Core Version:    0.6.0
 */