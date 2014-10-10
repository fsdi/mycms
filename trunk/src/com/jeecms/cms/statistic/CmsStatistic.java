/*     */ package com.jeecms.cms.statistic;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CmsStatistic
/*     */ {
/*     */   public static final String JOIN = "-";
/*     */   public static final String TIMEPATTERN = "HH:mm:ss";
/*     */   public static final String PERCENTSIGN = "%";
/*     */   public static final double COEFFICIENT = 0.8D;
/*     */   public static final int MEMBER = 1;
/*     */   public static final int CONTENT = 2;
/*     */   public static final int COMMENT = 3;
/*     */   public static final int GUESTBOOK = 4;
/*     */   public static final int PV = 11;
/*     */   public static final int UNIQUEIP = 12;
/*     */   public static final int UNIQUEVISITOR = 13;
/*     */   public static final int AVGVIEWS = 14;
/*     */   public static final int TODAY = 21;
/*     */   public static final int YESTERDAY = 22;
/*     */   public static final int THISWEEK = 23;
/*     */   public static final int THISMONTH = 24;
/*     */   public static final int THISYEAR = 25;
/*     */   public static final String REFERER_WEBSITE = "refererWebSite";
/*     */   public static final String REFERER_PAGE = "refererPage";
/*     */   public static final String REFERER_KEYWORD = "refererKeyword";
/*     */   public static final String ACCESS_PAGE = "accessPage";
/*     */   public static final String AREA = "area";
/*     */   public static final String SITEID = "siteId";
/*     */   public static final String ISREPLYED = "isReplyed";
/*     */   public static final String USERID = "userId";
/*     */   public static final String CHANNELID = "channelId";
/*     */   private String description;
/*     */   private String vice;
/*     */   private Long count;
/*     */   private Long total;
/*     */ 
/*     */   public CmsStatistic()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsStatistic(Long count)
/*     */   {
/*  43 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public CmsStatistic(String description, Long count) {
/*  47 */     this(count);
/*  48 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public CmsStatistic(String description, Long count, Long total) {
/*  52 */     this(description, count);
/*  53 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 114 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description) {
/* 118 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Long getCount() {
/* 122 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Long count) {
/* 126 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public Long getTotal() {
/* 130 */     return this.total;
/*     */   }
/*     */ 
/*     */   public void setTotal(Long total) {
/* 134 */     this.total = total;
/*     */   }
/*     */ 
/*     */   public String getVice() {
/* 138 */     return this.vice;
/*     */   }
/*     */ 
/*     */   public void setVice(String vice) {
/* 142 */     this.vice = vice;
/*     */   }
/*     */ 
/*     */   public String getPercent() {
/* 146 */     return NumberFormat.getPercentInstance().format(
/* 147 */       this.count.longValue() / (this.total.longValue() == 0L ? 1.0D : this.total.longValue() + 0.0D));
/*     */   }
/*     */ 
/*     */   public String getBarWidth() {
/* 151 */     return (int)(Integer.parseInt(getPercent().replace("%", "")) * 0.8D) + 
/* 152 */       "%";
/*     */   }
/*     */ 
/*     */   public static enum CmsStatisticModel
/*     */   {
/*  65 */     day, 
/*     */ 
/*  69 */     week, 
/*     */ 
/*  73 */     month, 
/*     */ 
/*  77 */     year;
/*     */   }
/*     */ 
/*     */   public static class TimeRange
/*     */   {
/*     */     private final Date begin;
/*     */     private final Date end;
/*     */ 
/*     */     public Date getBegin() {
/*  88 */       return this.begin;
/*     */     }
/*     */ 
/*     */     public Date getEnd() {
/*  92 */       return this.end;
/*     */     }
/*     */ 
/*     */     private TimeRange(Date begin, Date end) {
/*  96 */       this.begin = begin;
/*  97 */       this.end = end;
/*     */     }
/*     */ 
/*     */     public static TimeRange getInstance(Date begin, Date end) {
/* 101 */       if ((begin == null) || (end == null)) {
/* 102 */         throw new IllegalArgumentException("Params begin and end cannot be null!");
/*     */       }
/* 104 */       return new TimeRange(begin, end);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.CmsStatistic
 * JD-Core Version:    0.6.0
 */