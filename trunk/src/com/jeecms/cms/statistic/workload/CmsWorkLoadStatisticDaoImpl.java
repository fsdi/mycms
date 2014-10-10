/*    */ package com.jeecms.cms.statistic.workload;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateSimpleDao;
/*    */ import java.util.Date;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsWorkLoadStatisticDaoImpl extends HibernateSimpleDao
/*    */   implements CmsWorkLoadStatisticDao
/*    */ {
/*    */   public Long statistic(Integer channelId, Integer reviewerId, Integer authorId, Date beginDate, Date endDate, CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind dateKind)
/*    */   {
/* 15 */     String hql = "select count(*) from Content bean";
/* 16 */     if (reviewerId != null) {
/* 17 */       hql = hql + " join bean.contentCheckSet check";
/*    */     }
/* 19 */     if (channelId != null) {
/* 20 */       hql = hql + " join bean.channel channel,Channel parent";
/* 21 */       hql = hql + " where channel.lft between parent.lft and parent.rgt";
/* 22 */       hql = hql + " and channel.site.id=parent.site.id";
/* 23 */       hql = hql + " and parent.id=:parentId";
/*    */     } else {
/* 25 */       hql = hql + " where 1=1";
/*    */     }
/* 27 */     if (reviewerId != null) {
/* 28 */       hql = hql + " and check.reviewer.id=:reviewerId";
/*    */     }
/* 30 */     if (authorId != null) {
/* 31 */       hql = hql + " and bean.user.id=:authorId";
/*    */     }
/* 33 */     if (dateKind == CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind.release) {
/* 34 */       if (beginDate != null) {
/* 35 */         hql = hql + " and  bean.contentExt.releaseDate>=:beginDate";
/*    */       }
/* 37 */       if (endDate != null)
/* 38 */         hql = hql + " and  bean.contentExt.releaseDate<=:endDate";
/*    */     }
/*    */     else {
/* 41 */       if (beginDate != null) {
/* 42 */         hql = hql + " and  check.checkDate>=:beginDate";
/*    */       }
/* 44 */       if (endDate != null) {
/* 45 */         hql = hql + " and  check.checkDate<=:endDate";
/*    */       }
/*    */     }
/* 48 */     Query query = getSession().createQuery(hql);
/* 49 */     if (channelId != null) {
/* 50 */       query.setParameter("parentId", channelId);
/*    */     }
/* 52 */     if (reviewerId != null) {
/* 53 */       query.setParameter("reviewerId", reviewerId);
/*    */     }
/* 55 */     if (authorId != null) {
/* 56 */       query.setParameter("authorId", authorId);
/*    */     }
/* 58 */     if (beginDate != null) {
/* 59 */       query.setParameter("beginDate", beginDate);
/*    */     }
/* 61 */     if (endDate != null) {
/* 62 */       query.setParameter("endDate", endDate);
/*    */     }
/* 64 */     return (Long)query.uniqueResult();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatisticDaoImpl
 * JD-Core Version:    0.6.0
 */