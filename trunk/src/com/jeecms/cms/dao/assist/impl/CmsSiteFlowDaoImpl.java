/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsSiteFlowDao;
/*    */ import com.jeecms.cms.entity.assist.CmsSiteFlow;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsSiteFlowDaoImpl extends HibernateBaseDao<CmsSiteFlow, Integer>
/*    */   implements CmsSiteFlowDao
/*    */ {
/*    */   public CmsSiteFlow save(CmsSiteFlow cmsSiteFlow)
/*    */   {
/* 14 */     getSession().save(cmsSiteFlow);
/* 15 */     return cmsSiteFlow;
/*    */   }
/*    */ 
/*    */   public CmsSiteFlow findUniqueByProperties(Integer siteId, String accessDate, String sessionId, String accessPage)
/*    */   {
/* 20 */     String hql = "from CmsSiteFlow bean where bean.site.id=:siteId and bean.accessDate=:accessDate and bean.sessionId=:sessionId and bean.accessPage=:accessPage";
/* 21 */     Query query = getSession().createQuery(hql);
/* 22 */     query.setParameter("siteId", siteId);
/* 23 */     query.setParameter("accessDate", accessDate);
/* 24 */     query.setParameter("sessionId", sessionId);
/* 25 */     query.setParameter("accessPage", accessPage);
/* 26 */     query.setMaxResults(1);
/* 27 */     query.setCacheable(true);
/* 28 */     return (CmsSiteFlow)query.uniqueResult();
/*    */   }
/*    */ 
/*    */   protected Class<CmsSiteFlow> getEntityClass() {
/* 32 */     return CmsSiteFlow.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsSiteFlowDaoImpl
 * JD-Core Version:    0.6.0
 */