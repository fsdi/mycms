/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsFriendlinkCtgDao;
/*    */ import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsFriendlinkCtgDaoImpl extends HibernateBaseDao<CmsFriendlinkCtg, Integer>
/*    */   implements CmsFriendlinkCtgDao
/*    */ {
/*    */   public List<CmsFriendlinkCtg> getList(Integer siteId)
/*    */   {
/* 18 */     Finder f = Finder.create("from CmsFriendlinkCtg bean");
/* 19 */     if (siteId != null) {
/* 20 */       f.append(" where bean.site.id=:siteId");
/* 21 */       f.setParam("siteId", siteId);
/*    */     }
/* 23 */     f.append(" order by bean.priority asc");
/* 24 */     return find(f);
/*    */   }
/*    */ 
/*    */   public int countBySiteId(Integer siteId) {
/* 28 */     String hql = "select count(*) from CmsFriendlinkCtg bean where bean.site.id=:siteId";
/* 29 */     return 
/* 30 */       ((Number)getSession().createQuery(hql).setParameter("siteId", 
/* 30 */       siteId).iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg findById(Integer id) {
/* 34 */     CmsFriendlinkCtg entity = (CmsFriendlinkCtg)get(id);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg save(CmsFriendlinkCtg bean) {
/* 39 */     getSession().save(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg deleteById(Integer id) {
/* 44 */     CmsFriendlinkCtg entity = (CmsFriendlinkCtg)super.get(id);
/* 45 */     if (entity != null) {
/* 46 */       getSession().delete(entity);
/*    */     }
/* 48 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<CmsFriendlinkCtg> getEntityClass()
/*    */   {
/* 53 */     return CmsFriendlinkCtg.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsFriendlinkCtgDaoImpl
 * JD-Core Version:    0.6.0
 */