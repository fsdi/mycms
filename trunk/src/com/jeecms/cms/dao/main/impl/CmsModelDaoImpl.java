/*    */ package com.jeecms.cms.dao.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsModelDao;
/*    */ import com.jeecms.cms.entity.main.CmsModel;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsModelDaoImpl extends HibernateBaseDao<CmsModel, Integer>
/*    */   implements CmsModelDao
/*    */ {
/*    */   public List<CmsModel> getList(boolean containDisabled, Boolean hasContent)
/*    */   {
/* 17 */     Finder f = Finder.create("from CmsModel bean");
/* 18 */     if (!containDisabled) {
/* 19 */       f.append(" where bean.disabled=false");
/*    */     }
/* 21 */     if (hasContent != null) {
/* 22 */       if (hasContent.booleanValue())
/* 23 */         f.append(" and bean.hasContent=true");
/*    */       else {
/* 25 */         f.append(" and bean.hasContent=false");
/*    */       }
/*    */     }
/* 28 */     f.append(" order by bean.priority");
/* 29 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsModel getDefModel()
/*    */   {
/* 34 */     String hql = "from CmsModel bean where bean.def=true";
/* 35 */     List list = getSession().createQuery(hql).setMaxResults(1)
/* 36 */       .list();
/* 37 */     if (list.size() > 0) {
/* 38 */       return (CmsModel)list.get(0);
/*    */     }
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   public CmsModel findById(Integer id)
/*    */   {
/* 45 */     CmsModel entity = (CmsModel)get(id);
/* 46 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModel findByPath(String path) {
/* 50 */     String hql = "from CmsModel bean where bean.path=:path";
/* 51 */     List list = getSession().createQuery(hql).setParameter("path", path).setMaxResults(1).list();
/* 52 */     if (list.size() > 0) {
/* 53 */       return (CmsModel)list.get(0);
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   public CmsModel save(CmsModel bean)
/*    */   {
/* 61 */     getSession().save(bean);
/* 62 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsModel deleteById(Integer id) {
/* 66 */     CmsModel entity = (CmsModel)super.get(id);
/* 67 */     if (entity != null) {
/* 68 */       getSession().delete(entity);
/*    */     }
/* 70 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsModel> getEntityClass()
/*    */   {
/* 75 */     return CmsModel.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.main.impl.CmsModelDaoImpl
 * JD-Core Version:    0.6.0
 */