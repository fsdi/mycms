/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsGuestbookExtDao;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsGuestbookExtDaoImpl extends HibernateBaseDao<CmsGuestbookExt, Integer>
/*    */   implements CmsGuestbookExtDao
/*    */ {
/*    */   public CmsGuestbookExt findById(Integer id)
/*    */   {
/* 15 */     CmsGuestbookExt entity = (CmsGuestbookExt)get(id);
/* 16 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookExt save(CmsGuestbookExt bean) {
/* 20 */     getSession().save(bean);
/* 21 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookExt deleteById(Integer id) {
/* 25 */     CmsGuestbookExt entity = (CmsGuestbookExt)super.get(id);
/* 26 */     if (entity != null) {
/* 27 */       getSession().delete(entity);
/*    */     }
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsGuestbookExt> getEntityClass()
/*    */   {
/* 34 */     return CmsGuestbookExt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsGuestbookExtDaoImpl
 * JD-Core Version:    0.6.0
 */