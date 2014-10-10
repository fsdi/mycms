/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentTxtDao;
/*    */ import com.jeecms.cms.entity.main.ContentTxt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ContentTxtDaoImpl extends HibernateBaseDao<ContentTxt, Integer>
/*    */   implements ContentTxtDao
/*    */ {
/*    */   public ContentTxt findById(Integer id)
/*    */   {
/* 13 */     ContentTxt entity = (ContentTxt)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentTxt save(ContentTxt bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<ContentTxt> getEntityClass()
/*    */   {
/* 24 */     return ContentTxt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.ContentTxtDaoImpl
 * JD-Core Version:    0.6.0
 */