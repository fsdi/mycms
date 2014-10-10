/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsSiteCompanyDao;
/*    */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsSiteCompanyDaoImpl extends HibernateBaseDao<CmsSiteCompany, Integer>
/*    */   implements CmsSiteCompanyDao
/*    */ {
/*    */   public CmsSiteCompany save(CmsSiteCompany bean)
/*    */   {
/* 14 */     getSession().save(bean);
/* 15 */     return bean;
/*    */   }
/*    */ 
/*    */   protected Class<CmsSiteCompany> getEntityClass()
/*    */   {
/* 20 */     return CmsSiteCompany.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsSiteCompanyDaoImpl
 * JD-Core Version:    0.6.0
 */