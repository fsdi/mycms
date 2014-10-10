/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsUserResumeDao;
/*    */ import com.jeecms.cms.entity.main.CmsUserResume;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsUserResumeDaoImpl extends HibernateBaseDao<CmsUserResume, Integer>
/*    */   implements CmsUserResumeDao
/*    */ {
/*    */   public CmsUserResume findById(Integer id)
/*    */   {
/* 12 */     CmsUserResume entity = (CmsUserResume)get(id);
/* 13 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsUserResume save(CmsUserResume bean) {
/* 17 */     getSession().save(bean);
/* 18 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<CmsUserResume> getEntityClass()
/*    */   {
/* 23 */     return CmsUserResume.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsUserResumeDaoImpl
 * JD-Core Version:    0.6.0
 */