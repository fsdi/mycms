/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsRoleDao;
/*    */ import com.jeecms.cms.entity.main.CmsRole;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsRoleDaoImpl extends HibernateBaseDao<CmsRole, Integer>
/*    */   implements CmsRoleDao
/*    */ {
/*    */   public List<CmsRole> getList()
/*    */   {
/* 16 */     String hql = "from CmsRole bean order by bean.priority asc";
/* 17 */     return find(hql, new Object[0]);
/*    */   }
/*    */ 
/*    */   public CmsRole findById(Integer id) {
/* 21 */     CmsRole entity = (CmsRole)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsRole save(CmsRole bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsRole deleteById(Integer id) {
/* 31 */     CmsRole entity = (CmsRole)super.get(id);
/* 32 */     if (entity != null) {
/* 33 */       getSession().delete(entity);
/*    */     }
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsRole> getEntityClass()
/*    */   {
/* 40 */     return CmsRole.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsRoleDaoImpl
 * JD-Core Version:    0.6.0
 */