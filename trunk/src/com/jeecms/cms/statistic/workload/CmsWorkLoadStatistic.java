/*     */ package com.jeecms.cms.statistic.workload;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CmsWorkLoadStatistic
/*     */ {
/*     */   private Channel channel;
/*     */   private CmsUser author;
/*     */   private CmsUser reviewer;
/*     */   private Date date;
/*     */   private Long count;
/*     */ 
/*     */   public CmsWorkLoadStatistic()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsWorkLoadStatistic(Channel channel, Date date, Long count)
/*     */   {
/*  31 */     this.channel = channel;
/*  32 */     this.date = date;
/*  33 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public CmsWorkLoadStatistic(Channel channel, CmsUser author, CmsUser reviewer, Long count)
/*     */   {
/*  39 */     this.channel = channel;
/*  40 */     this.author = author;
/*  41 */     this.reviewer = reviewer;
/*  42 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public CmsWorkLoadStatistic(CmsUser author, CmsUser reviewer, Date date, Long count)
/*     */   {
/*  48 */     this.author = author;
/*  49 */     this.reviewer = reviewer;
/*  50 */     this.date = date;
/*  51 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public CmsWorkLoadStatistic(Channel channel, CmsUser author, CmsUser reviewer, Date date, Long count)
/*     */   {
/*  57 */     this.channel = channel;
/*  58 */     this.author = author;
/*  59 */     this.reviewer = reviewer;
/*  60 */     this.date = date;
/*  61 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/*  72 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel) {
/*  76 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public CmsUser getAuthor() {
/*  80 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(CmsUser author) {
/*  84 */     this.author = author;
/*     */   }
/*     */ 
/*     */   public CmsUser getReviewer() {
/*  88 */     return this.reviewer;
/*     */   }
/*     */ 
/*     */   public void setReviewer(CmsUser reviewer) {
/*  92 */     this.reviewer = reviewer;
/*     */   }
/*     */ 
/*     */   public Date getDate() {
/*  96 */     return this.date;
/*     */   }
/*     */ 
/*     */   public void setDate(Date date) {
/* 100 */     this.date = date;
/*     */   }
/*     */ 
/*     */   public Long getCount() {
/* 104 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Long count) {
/* 108 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public static enum CmsWorkLoadStatisticDateKind
/*     */   {
/*  21 */     release, check;
/*     */   }
/*     */ 
/*     */   public static enum CmsWorkLoadStatisticGroup
/*     */   {
/*  18 */     day, week, month, year;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.statistic.workload.CmsWorkLoadStatistic
 * JD-Core Version:    0.6.0
 */