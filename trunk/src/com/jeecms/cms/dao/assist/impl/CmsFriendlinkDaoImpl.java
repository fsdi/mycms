/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsFriendlinkDao;
/*    */ import com.jeecms.cms.entity.assist.CmsFriendlink;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsFriendlinkDaoImpl extends HibernateBaseDao<CmsFriendlink, Integer>
/*    */   implements CmsFriendlinkDao
/*    */ {
/*    */   public List<CmsFriendlink> getList(Integer siteId, Integer ctgId, Boolean enabled)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsFriendlink bean where 1=1");
/* 19 */     if (enabled != null) {
/* 20 */       f.append(" and bean.enabled=:enabled");
/* 21 */       f.setParam("enabled", enabled);
/*    */     }
/* 23 */     if (siteId != null) {
/* 24 */       f.append(" and bean.site.id=:siteId");
/* 25 */       f.setParam("siteId", siteId);
/*    */     }
/* 27 */     if (ctgId != null) {
/* 28 */       f.append(" and bean.category.id=:ctgId");
/* 29 */       f.setParam("ctgId", ctgId);
/*    */     }
/* 31 */     f.append(" order by bean.priority asc");
/* 32 */     return find(f);
/*    */   }
/*    */ 
/*    */   public int countByCtgId(Integer ctgId) {
/* 36 */     String hql = "select count(*) from CmsFriendlink bean where bean.category.id=:ctgId";
/* 37 */     return 
/* 38 */       ((Number)getSession().createQuery(hql).setParameter("ctgId", 
/* 38 */       ctgId).iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   public CmsFriendlink findById(Integer id) {
/* 42 */     CmsFriendlink entity = (CmsFriendlink)get(id);
/* 43 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsFriendlink save(CmsFriendlink bean) {
/* 47 */     getSession().save(bean);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlink deleteById(Integer id) {
/* 52 */     CmsFriendlink entity = (CmsFriendlink)super.get(id);
/* 53 */     if (entity != null) {
/* 54 */       getSession().delete(entity);
/*    */     }
/* 56 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsFriendlink> getEntityClass()
/*    */   {
/* 61 */     return CmsFriendlink.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsFriendlinkDaoImpl
 * JD-Core Version:    0.6.0
 */