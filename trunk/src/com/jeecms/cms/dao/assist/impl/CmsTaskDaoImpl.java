/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsTaskDao;
/*    */ import com.jeecms.cms.entity.assist.CmsTask;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsTaskDaoImpl extends HibernateBaseDao<CmsTask, Integer>
/*    */   implements CmsTaskDao
/*    */ {
/*    */   public Pagination getPage(Integer siteId, int pageNo, int pageSize)
/*    */   {
/* 16 */     Finder f = Finder.create("from CmsTask task where task.site.id=:siteId").setParam("siteId", siteId);
/* 17 */     Pagination page = find(f, pageNo, pageSize);
/* 18 */     return page;
/*    */   }
/*    */ 
/*    */   public List<CmsTask> getList()
/*    */   {
/* 23 */     Finder f = Finder.create("from CmsTask");
/* 24 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsTask findById(Integer id) {
/* 28 */     CmsTask entity = (CmsTask)get(id);
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsTask save(CmsTask bean) {
/* 33 */     getSession().save(bean);
/* 34 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsTask deleteById(Integer id) {
/* 38 */     CmsTask entity = (CmsTask)super.get(id);
/* 39 */     if (entity != null) {
/* 40 */       getSession().delete(entity);
/*    */     }
/* 42 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsTask> getEntityClass()
/*    */   {
/* 47 */     return CmsTask.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsTaskDaoImpl
 * JD-Core Version:    0.6.0
 */