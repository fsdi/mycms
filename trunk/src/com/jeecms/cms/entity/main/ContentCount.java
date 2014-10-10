/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseContentCount;
/*     */ 
/*     */ public class ContentCount extends BaseContentCount
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public void init()
/*     */   {
/*   9 */     short zero = 0;
/*  10 */     if (getDowns() == null) {
/*  11 */       setDowns(Integer.valueOf(0));
/*     */     }
/*  13 */     if (getViews() == null) {
/*  14 */       setViews(Integer.valueOf(0));
/*     */     }
/*  16 */     if (getViewsMonth() == null) {
/*  17 */       setViewsMonth(Integer.valueOf(0));
/*     */     }
/*  19 */     if (getViewsWeek() == null) {
/*  20 */       setViewsWeek(Integer.valueOf(0));
/*     */     }
/*  22 */     if (getViewsDay() == null) {
/*  23 */       setViewsDay(Integer.valueOf(0));
/*     */     }
/*  25 */     if (getComments() == null) {
/*  26 */       setComments(Integer.valueOf(0));
/*     */     }
/*  28 */     if (getCommentsMonth() == null) {
/*  29 */       setCommentsMonth(Integer.valueOf(0));
/*     */     }
/*  31 */     if (getCommentsWeek() == null) {
/*  32 */       setCommentsWeek(Short.valueOf(zero));
/*     */     }
/*  34 */     if (getCommentsDay() == null) {
/*  35 */       setCommentsDay(Short.valueOf(zero));
/*     */     }
/*  37 */     if (getDownloads() == null) {
/*  38 */       setDownloads(Integer.valueOf(0));
/*     */     }
/*  40 */     if (getDownloadsMonth() == null) {
/*  41 */       setDownloadsMonth(Integer.valueOf(0));
/*     */     }
/*  43 */     if (getDownloadsWeek() == null) {
/*  44 */       setDownloadsWeek(Short.valueOf(zero));
/*     */     }
/*  46 */     if (getDownloadsDay() == null) {
/*  47 */       setDownloadsDay(Short.valueOf(zero));
/*     */     }
/*  49 */     if (getUps() == null) {
/*  50 */       setUps(Integer.valueOf(0));
/*     */     }
/*  52 */     if (getUpsMonth() == null) {
/*  53 */       setUpsMonth(Integer.valueOf(0));
/*     */     }
/*  55 */     if (getUpsWeek() == null) {
/*  56 */       setUpsWeek(Short.valueOf(zero));
/*     */     }
/*  58 */     if (getUpsDay() == null)
/*  59 */       setUpsDay(Short.valueOf(zero));
/*     */   }
/*     */ 
/*     */   public ContentCount()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ContentCount(Integer id)
/*     */   {
/*  72 */     super(id);
/*     */   }
/*     */ 
/*     */   public ContentCount(Integer id, Integer views, Integer viewsMonth, Integer viewsWeek, Integer viewsDay, Integer comments, Integer commentsMonth, Short commentsWeek, Short commentsDay, Integer downloads, Integer downloadsMonth, Short downloadsWeek, Short downloadsDay, Integer ups, Integer upsMonth, Short upsWeek, Short upsDay, Integer downs)
/*     */   {
/* 116 */     super(id, 
/* 100 */       views, 
/* 101 */       viewsMonth, 
/* 102 */       viewsWeek, 
/* 103 */       viewsDay, 
/* 104 */       comments, 
/* 105 */       commentsMonth, 
/* 106 */       commentsWeek, 
/* 107 */       commentsDay, 
/* 108 */       downloads, 
/* 109 */       downloadsMonth, 
/* 110 */       downloadsWeek, 
/* 111 */       downloadsDay, 
/* 112 */       ups, 
/* 113 */       upsMonth, 
/* 114 */       upsWeek, 
/* 115 */       upsDay, 
/* 116 */       downs);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentCount
 * JD-Core Version:    0.6.0
 */