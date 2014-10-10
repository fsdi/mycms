/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsSensitivityDao;
/*    */ import com.jeecms.cms.entity.assist.CmsSensitivity;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsSensitivityDaoImpl extends HibernateBaseDao<CmsSensitivity, Integer>
/*    */   implements CmsSensitivityDao
/*    */ {
/*    */   public List<CmsSensitivity> getList(boolean cacheable)
/*    */   {
/* 16 */     String hql = "from CmsSensitivity bean order by bean.id desc";
/* 17 */     return getSession().createQuery(hql).setCacheable(cacheable).list();
/*    */   }
/*    */ 
/*    */   public CmsSensitivity findById(Integer id) {
/* 21 */     CmsSensitivity entity = (CmsSensitivity)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsSensitivity save(CmsSensitivity bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsSensitivity deleteById(Integer id) {
/* 31 */     CmsSensitivity entity = (CmsSensitivity)super.get(id);
/* 32 */     if (entity != null) {
/* 33 */       getSession().delete(entity);
/*    */     }
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsSensitivity> getEntityClass()
/*    */   {
/* 40 */     return CmsSensitivity.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsSensitivityDaoImpl
 * JD-Core Version:    0.6.0
 */