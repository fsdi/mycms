/*     */ package com.jeecms.cms.statistic.workload;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.util.DateUtils;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsWorkLoadStatisticAct
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private CmsWorkLoadStatisticSvc workloadStatisticSvc;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng userMng;
/*     */ 
/*     */   @RequestMapping({"/workloadstatistic/v_list.do"})
/*     */   public String contentList(HttpServletRequest request, ModelMap model, Integer channelId, Integer reviewerId, Integer authorId, Date beginDate, Date endDate, String group)
/*     */   {
/*  35 */     if (StringUtils.isBlank(group)) {
/*  36 */       group = "year";
/*     */     }
/*  38 */     if ((reviewerId != null) && (reviewerId.equals(Integer.valueOf(0)))) {
/*  39 */       reviewerId = null;
/*     */     }
/*  41 */     CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup statisticGroup = initialGrop(group);
/*     */     CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind kind;
/*  43 */     if ((reviewerId != null) && (!reviewerId.equals(Integer.valueOf(0))))
/*  44 */       kind = initialDateKind(Boolean.valueOf(true));
/*     */     else {
/*  46 */       kind = initialDateKind(Boolean.valueOf(false));
/*     */     }
/*  48 */     if ((authorId != null) && (authorId.equals(Integer.valueOf(0)))) {
/*  49 */       authorId = null;
/*     */     }
/*  51 */     Date now = Calendar.getInstance().getTime();
/*  52 */     if (beginDate == null) {
/*  53 */       beginDate = getNextDate(statisticGroup, now, 0);
/*     */     }
/*  55 */     if (endDate == null) {
/*  56 */       endDate = now;
/*     */     }
/*  58 */     Integer siteId = CmsUtils.getSiteId(request);
/*  59 */     List topList = this.channelMng.getTopList(siteId, true);
/*  60 */     List channelList = Channel.getListForSelect(topList, null, 
/*  61 */       true);
/*  62 */     List admins = this.userMng.getAdminList(siteId, null, Boolean.valueOf(false), null);
/*  63 */     List users = this.userMng.getList(null, null, null, null, Boolean.valueOf(false), null, null);
/*  64 */     List li = this.workloadStatisticSvc.statistic(channelId, reviewerId, 
/*  65 */       authorId, beginDate, endDate, statisticGroup, kind);
/*  66 */     model.addAttribute("channelId", channelId);
/*  67 */     model.addAttribute("reviewerId", reviewerId);
/*  68 */     model.addAttribute("authorId", authorId);
/*  69 */     model.addAttribute("beginDate", beginDate);
/*  70 */     model.addAttribute("endDate", endDate);
/*  71 */     model.addAttribute("group", group);
/*  72 */     model.addAttribute("channelList", channelList);
/*  73 */     model.addAttribute("admins", admins);
/*  74 */     model.addAttribute("users", users);
/*  75 */     model.addAttribute("list", li);
/*  76 */     return "statistic/workload/list";
/*     */   }
/*     */ 
/*     */   private CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup initialGrop(String group) {
/*  80 */     CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup statisticGroup = 
/*  81 */       CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.valueOf(group);
/*  82 */     return statisticGroup;
/*     */   }
/*     */   private CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind initialDateKind(Boolean checkDate) {
/*  85 */     CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind kind = CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind.valueOf("release");
/*  86 */     if (checkDate.booleanValue()) {
/*  87 */       kind = CmsWorkLoadStatistic.CmsWorkLoadStatisticDateKind.valueOf("check");
/*     */     }
/*  89 */     return kind;
/*     */   }
/*     */ 
/*     */   private Date getNextDate(CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup group, Date date, int amount)
/*     */   {
/*  94 */     Date result = null;
/*  95 */     if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.year)
/*  96 */       result = DateUtils.getSpecficYearStart(date, amount);
/*  97 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.month)
/*  98 */       result = DateUtils.getSpecficMonthStart(date, amount);
/*  99 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.week)
/* 100 */       result = DateUtils.getSpecficWeekStart(date, amount);
/* 101 */     else if (group == CmsWorkLoadStatistic.CmsWorkLoadStatisticGroup.day) {
/* 102 */       result = DateUtils.getSpecficDateStart(date, amount);
/*     */     }
/* 104 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatisticAct
 * JD-Core Version:    0.6.0
 */