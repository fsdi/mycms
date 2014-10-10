/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsLogDao;
/*    */ import com.jeecms.cms.entity.main.CmsLog;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsLogDaoImpl extends HibernateBaseDao<CmsLog, Integer>
/*    */   implements CmsLogDao
/*    */ {
/*    */   public Pagination getPage(Integer category, Integer siteId, Integer userId, String title, String ip, int pageNo, int pageSize)
/*    */   {
/* 20 */     Finder f = Finder.create("from CmsLog bean where 1=1");
/* 21 */     if (category != null) {
/* 22 */       f.append(" and bean.category=:category");
/* 23 */       f.setParam("category", category);
/*    */     }
/* 25 */     if (siteId != null) {
/* 26 */       f.append(" and bean.site.id=:siteId");
/* 27 */       f.setParam("siteId", siteId);
/*    */     }
/* 29 */     if (userId != null) {
/* 30 */       f.append(" and bean.user.id=:userId");
/* 31 */       f.setParam("userId", userId);
/*    */     }
/* 33 */     if (StringUtils.isNotBlank(title)) {
/* 34 */       f.append(" and bean.title like :title");
/* 35 */       f.setParam("title", "%" + title + "%");
/*    */     }
/* 37 */     if (StringUtils.isNotBlank(ip)) {
/* 38 */       f.append(" and bean.ip like :ip");
/* 39 */       f.setParam("ip", ip + "%");
/*    */     }
/* 41 */     f.append(" order by bean.id desc");
/* 42 */     return find(f, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public CmsLog findById(Integer id) {
/* 46 */     CmsLog entity = (CmsLog)get(id);
/* 47 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsLog save(CmsLog bean) {
/* 51 */     getSession().save(bean);
/* 52 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsLog deleteById(Integer id) {
/* 56 */     CmsLog entity = (CmsLog)super.get(id);
/* 57 */     if (entity != null) {
/* 58 */       getSession().delete(entity);
/*    */     }
/* 60 */     return entity;
/*    */   }
/*    */ 
/*    */   public int deleteBatch(Integer category, Integer siteId, Date before) {
/* 64 */     Finder f = Finder.create("delete CmsLog bean where 1=1");
/* 65 */     if (category != null) {
/* 66 */       f.append(" and bean.category=:category");
/* 67 */       f.setParam("category", category);
/*    */     }
/* 69 */     if (siteId != null) {
/* 70 */       f.append(" and bean.site.id=:siteId");
/* 71 */       f.setParam("siteId", siteId);
/*    */     }
/* 73 */     if (before != null) {
/* 74 */       f.append(" and bean.time<=:before");
/* 75 */       f.setParam("before", before);
/*    */     }
/* 77 */     Query q = f.createQuery(getSession());
/* 78 */     return q.executeUpdate();
/*    */   }
/*    */ 
/*    */   protected Class<CmsLog> getEntityClass()
/*    */   {
/* 83 */     return CmsLog.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsLogDaoImpl
 * JD-Core Version:    0.6.0
 */