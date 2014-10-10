/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsUserExtDao;
/*    */ import com.jeecms.cms.entity.main.CmsUserExt;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsUserExtDaoImpl extends HibernateBaseDao<CmsUserExt, Integer>
/*    */   implements CmsUserExtDao
/*    */ {
/*    */   public CmsUserExt findById(Integer id)
/*    */   {
/* 12 */     CmsUserExt entity = (CmsUserExt)get(id);
/* 13 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsUserExt save(CmsUserExt bean) {
/* 17 */     getSession().save(bean);
/* 18 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<CmsUserExt> getEntityClass()
/*    */   {
/* 23 */     return CmsUserExt.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsUserExtDaoImpl
 * JD-Core Version:    0.6.0
 */