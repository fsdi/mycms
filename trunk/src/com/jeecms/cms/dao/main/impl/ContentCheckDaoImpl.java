/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentCheckDao;
/*    */ import com.jeecms.cms.entity.main.ContentCheck;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentCheckDaoImpl extends HibernateBaseDao<ContentCheck, Long>
/*    */   implements ContentCheckDao
/*    */ {
/*    */   public ContentCheck findById(Long id)
/*    */   {
/* 13 */     ContentCheck entity = (ContentCheck)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentCheck save(ContentCheck bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ContentCheck> getEntityClass()
/*    */   {
/* 24 */     return ContentCheck.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentCheckDaoImpl
 * JD-Core Version:    0.6.0
 */