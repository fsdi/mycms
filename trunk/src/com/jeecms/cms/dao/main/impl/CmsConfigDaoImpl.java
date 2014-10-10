/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsConfigDao;
/*    */ import com.jeecms.cms.entity.main.CmsConfig;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsConfigDaoImpl extends HibernateBaseDao<CmsConfig, Integer>
/*    */   implements CmsConfigDao
/*    */ {
/*    */   public CmsConfig findById(Integer id)
/*    */   {
/* 13 */     CmsConfig entity = (CmsConfig)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsConfig> getEntityClass()
/*    */   {
/* 19 */     return CmsConfig.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsConfigDaoImpl
 * JD-Core Version:    0.6.0
 */