/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsGroupDao;
/*    */ import com.jeecms.cms.entity.main.CmsGroup;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsGroupDaoImpl extends HibernateBaseDao<CmsGroup, Integer>
/*    */   implements CmsGroupDao
/*    */ {
/*    */   public List<CmsGroup> getList()
/*    */   {
/* 16 */     String hql = "from CmsGroup bean order by bean.priority asc,bean.id asc";
/* 17 */     return find(hql, new Object[0]);
/*    */   }
/*    */ 
/*    */   public CmsGroup getRegDef() {
/* 21 */     String hql = "from CmsGroup bean where bean.regDef=true";
/* 22 */     return (CmsGroup)findUnique(hql, new Object[0]);
/*    */   }
/*    */ 
/*    */   public CmsGroup findById(Integer id) {
/* 26 */     CmsGroup entity = (CmsGroup)get(id);
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsGroup save(CmsGroup bean) {
/* 31 */     getSession().save(bean);
/* 32 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGroup deleteById(Integer id) {
/* 36 */     CmsGroup entity = (CmsGroup)super.get(id);
/* 37 */     if (entity != null) {
/* 38 */       getSession().delete(entity);
/*    */     }
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsGroup> getEntityClass()
/*    */   {
/* 45 */     return CmsGroup.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsGroupDaoImpl
 * JD-Core Version:    0.6.0
 */