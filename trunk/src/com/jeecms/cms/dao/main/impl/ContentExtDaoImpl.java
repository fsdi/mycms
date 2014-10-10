/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentExtDao;
/*    */ import com.jeecms.cms.entity.main.ContentExt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentExtDaoImpl extends HibernateBaseDao<ContentExt, Integer>
/*    */   implements ContentExtDao
/*    */ {
/*    */   public ContentExt findById(Integer id)
/*    */   {
/* 13 */     ContentExt entity = (ContentExt)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentExt save(ContentExt bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ContentExt> getEntityClass()
/*    */   {
/* 24 */     return ContentExt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentExtDaoImpl
 * JD-Core Version:    0.6.0
 */