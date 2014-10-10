/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsGuestbookCtgDao;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsGuestbookCtgDaoImpl extends HibernateBaseDao<CmsGuestbookCtg, Integer>
/*    */   implements CmsGuestbookCtgDao
/*    */ {
/*    */   public List<CmsGuestbookCtg> getList(Integer siteId)
/*    */   {
/* 17 */     String hql = "from CmsGuestbookCtg bean where bean.site.id=? order by bean.priority asc";
/*    */ 
/* 19 */     return find(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg findById(Integer id) {
/* 23 */     CmsGuestbookCtg entity = (CmsGuestbookCtg)get(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg save(CmsGuestbookCtg bean) {
/* 28 */     getSession().save(bean);
/* 29 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg deleteById(Integer id) {
/* 33 */     CmsGuestbookCtg entity = (CmsGuestbookCtg)super.get(id);
/* 34 */     if (entity != null) {
/* 35 */       getSession().delete(entity);
/*    */     }
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsGuestbookCtg> getEntityClass()
/*    */   {
/* 42 */     return CmsGuestbookCtg.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsGuestbookCtgDaoImpl
 * JD-Core Version:    0.6.0
 */