/*    */ package com.jeecms.cms.statistic.workload;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.Channel;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.manager.main.ChannelMng;
/*    */ import com.jeecms.cms.manager.main.CmsUserMng;
/*    */ import com.jeecms.common.util.DateUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional(readOnly=true)
/*    */ public class CmsWorkLoadStatisticSvcImpl
/*    */   implements CmsWorkLoadStatisticSvc
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsWorkLoadStatisticDao dao;
/*    */ 
/*    */   @Autowired
/*    */   private CmsUserMng userMng;
/*    */ 
/*    */   @Autowired
/*    */   private ChannelMng channelMng;
/*    */ 
/*    */   public List<CmsWorkLoadStatistic> statistic(Integer channelId, Integer reviewerId, Integer authorId, Date beginDate, Date endDate, CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup group, CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind kind)
/*    */   {
/* 27 */     Channel channel = null;
/* 28 */     CmsUser author = null;
/* 29 */     CmsUser reviewer = null;
/* 30 */     Date begin = beginDate;
/* 31 */     Date end = null;
/* 32 */     int add = 1;
/* 33 */     if (channelId != null) {
/* 34 */       channel = this.channelMng.findById(channelId);
/*    */     }
/* 36 */     if (reviewerId != null) {
/* 37 */       reviewer = this.userMng.findById(reviewerId);
/*    */     }
/* 39 */     if (authorId != null) {
/* 40 */       author = this.userMng.findById(authorId);
/*    */     }
/* 42 */     begin = getNextDate(group, beginDate, 0);
/* 43 */     end = getNextDate(group, beginDate, add);
/* 44 */     List list = new ArrayList();
/* 45 */     while (end.before(endDate)) {
/* 46 */       Long count = this.dao.statistic(channelId, reviewerId, authorId, begin, end, 
/* 47 */         kind);
/* 48 */       CmsWorkLoadStatistic bean = new CmsWorkLoadStatistic(channel, author, reviewer, begin, 
/* 49 */         count);
/* 50 */       list.add(bean);
/* 51 */       begin = end;
/* 52 */       add++; end = getNextDate(group, beginDate, add);
/*    */     }
/* 54 */     Long count = this.dao.statistic(channelId, reviewerId, authorId, begin, end, kind);
/* 55 */     CmsWorkLoadStatistic bean = new CmsWorkLoadStatistic(channel, author, reviewer, begin, count);
/* 56 */     list.add(bean);
/* 57 */     return list;
/*    */   }
/*    */ 
/*    */   private Date getNextDate(CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup group, Date date, int amount)
/*    */   {
/* 62 */     Date result = null;
/* 63 */     if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.year)
/* 64 */       result = DateUtils.getSpecficYearStart(date, amount);
/* 65 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.month)
/* 66 */       result = DateUtils.getSpecficMonthStart(date, amount);
/* 67 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.week)
/* 68 */       result = DateUtils.getSpecficWeekStart(date, amount);
/* 69 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.day) {
/* 70 */       result = DateUtils.getSpecficDateStart(date, amount);
/*    */     }
/* 72 */     return result;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatisticSvcImpl
 * JD-Core Version:    0.6.0
 */