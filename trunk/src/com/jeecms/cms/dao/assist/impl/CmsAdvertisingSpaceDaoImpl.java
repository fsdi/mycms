/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAdvertisingSpaceDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsAdvertisingSpaceDaoImpl extends HibernateBaseDao<CmsAdvertisingSpace, Integer>
/*    */   implements CmsAdvertisingSpaceDao
/*    */ {
/*    */   public List<CmsAdvertisingSpace> getList(Integer siteId)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsAdvertisingSpace bean");
/* 19 */     if (siteId != null) {
/* 20 */       f.append(" where bean.site.id=:siteId");
/* 21 */       f.setParam("siteId", siteId);
/*    */     }
/* 23 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace findById(Integer id) {
/* 27 */     CmsAdvertisingSpace entity = (CmsAdvertisingSpace)get(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace save(CmsAdvertisingSpace bean) {
/* 32 */     getSession().save(bean);
/* 33 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace deleteById(Integer id) {
/* 37 */     CmsAdvertisingSpace entity = (CmsAdvertisingSpace)super.get(id);
/* 38 */     if (entity != null) {
/* 39 */       getSession().delete(entity);
/*    */     }
/* 41 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsAdvertisingSpace> getEntityClass()
/*    */   {
/* 46 */     return CmsAdvertisingSpace.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsAdvertisingSpaceDaoImpl
 * JD-Core Version:    0.6.0
 */