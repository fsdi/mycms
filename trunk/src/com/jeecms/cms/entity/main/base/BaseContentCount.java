/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentCount;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseContentCount
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentCount";
/*  18 */   public static String PROP_COMMENTS_WEEK = "commentsWeek";
/*  19 */   public static String PROP_VIEWS_WEEK = "viewsWeek";
/*  20 */   public static String PROP_COMMENTS_MONTH = "commentsMonth";
/*  21 */   public static String PROP_DOWNLOADS = "downloads";
/*  22 */   public static String PROP_DOWNLOADS_WEEK = "downloadsWeek";
/*  23 */   public static String PROP_UPS_WEEK = "upsWeek";
/*  24 */   public static String PROP_UPS_MONTH = "upsMonth";
/*  25 */   public static String PROP_VIEWS_MONTH = "viewsMonth";
/*  26 */   public static String PROP_COMMENTS_DAY = "commentsDay";
/*  27 */   public static String PROP_DOWNS = "downs";
/*  28 */   public static String PROP_VIEWS_DAY = "viewsDay";
/*  29 */   public static String PROP_DOWNLOADS_MONTH = "downloadsMonth";
/*  30 */   public static String PROP_COMMENTS = "comments";
/*  31 */   public static String PROP_UPS = "ups";
/*  32 */   public static String PROP_UPS_DAY = "upsDay";
/*  33 */   public static String PROP_VIEWS = "views";
/*  34 */   public static String PROP_CONTENT = "content";
/*  35 */   public static String PROP_ID = "id";
/*  36 */   public static String PROP_DOWNLOADS_DAY = "downloadsDay";
/*     */ 
/* 100 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Integer views;
/*     */   private Integer viewsMonth;
/*     */   private Integer viewsWeek;
/*     */   private Integer viewsDay;
/*     */   private Integer comments;
/*     */   private Integer commentsMonth;
/*     */   private Short commentsWeek;
/*     */   private Short commentsDay;
/*     */   private Integer downloads;
/*     */   private Integer downloadsMonth;
/*     */   private Short downloadsWeek;
/*     */   private Short downloadsDay;
/*     */   private Integer ups;
/*     */   private Integer upsMonth;
/*     */   private Short upsWeek;
/*     */   private Short upsDay;
/*     */   private Integer downs;
/*     */   private Content content;
/*     */ 
/*     */   public BaseContentCount()
/*     */   {
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentCount(Integer id)
/*     */   {
/*  48 */     setId(id);
/*  49 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentCount(Integer id, Integer views, Integer viewsMonth, Integer viewsWeek, Integer viewsDay, Integer comments, Integer commentsMonth, Short commentsWeek, Short commentsDay, Integer downloads, Integer downloadsMonth, Short downloadsWeek, Short downloadsDay, Integer ups, Integer upsMonth, Short upsWeek, Short upsDay, Integer downs)
/*     */   {
/*  75 */     setId(id);
/*  76 */     setViews(views);
/*  77 */     setViewsMonth(viewsMonth);
/*  78 */     setViewsWeek(viewsWeek);
/*  79 */     setViewsDay(viewsDay);
/*  80 */     setComments(comments);
/*  81 */     setCommentsMonth(commentsMonth);
/*  82 */     setCommentsWeek(commentsWeek);
/*  83 */     setCommentsDay(commentsDay);
/*  84 */     setDownloads(downloads);
/*  85 */     setDownloadsMonth(downloadsMonth);
/*  86 */     setDownloadsWeek(downloadsWeek);
/*  87 */     setDownloadsDay(downloadsDay);
/*  88 */     setUps(ups);
/*  89 */     setUpsMonth(upsMonth);
/*  90 */     setUpsWeek(upsWeek);
/*  91 */     setUpsDay(upsDay);
/*  92 */     setDowns(downs);
/*  93 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 136 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 144 */     this.id = id;
/* 145 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Integer getViews()
/*     */   {
/* 155 */     return this.views;
/*     */   }
/*     */ 
/*     */   public void setViews(Integer views)
/*     */   {
/* 163 */     this.views = views;
/*     */   }
/*     */ 
/*     */   public Integer getViewsMonth()
/*     */   {
/* 171 */     return this.viewsMonth;
/*     */   }
/*     */ 
/*     */   public void setViewsMonth(Integer viewsMonth)
/*     */   {
/* 179 */     this.viewsMonth = viewsMonth;
/*     */   }
/*     */ 
/*     */   public Integer getViewsWeek()
/*     */   {
/* 187 */     return this.viewsWeek;
/*     */   }
/*     */ 
/*     */   public void setViewsWeek(Integer viewsWeek)
/*     */   {
/* 195 */     this.viewsWeek = viewsWeek;
/*     */   }
/*     */ 
/*     */   public Integer getViewsDay()
/*     */   {
/* 203 */     return this.viewsDay;
/*     */   }
/*     */ 
/*     */   public void setViewsDay(Integer viewsDay)
/*     */   {
/* 211 */     this.viewsDay = viewsDay;
/*     */   }
/*     */ 
/*     */   public Integer getComments()
/*     */   {
/* 219 */     return this.comments;
/*     */   }
/*     */ 
/*     */   public void setComments(Integer comments)
/*     */   {
/* 227 */     this.comments = comments;
/*     */   }
/*     */ 
/*     */   public Integer getCommentsMonth()
/*     */   {
/* 235 */     return this.commentsMonth;
/*     */   }
/*     */ 
/*     */   public void setCommentsMonth(Integer commentsMonth)
/*     */   {
/* 243 */     this.commentsMonth = commentsMonth;
/*     */   }
/*     */ 
/*     */   public Short getCommentsWeek()
/*     */   {
/* 251 */     return this.commentsWeek;
/*     */   }
/*     */ 
/*     */   public void setCommentsWeek(Short commentsWeek)
/*     */   {
/* 259 */     this.commentsWeek = commentsWeek;
/*     */   }
/*     */ 
/*     */   public Short getCommentsDay()
/*     */   {
/* 267 */     return this.commentsDay;
/*     */   }
/*     */ 
/*     */   public void setCommentsDay(Short commentsDay)
/*     */   {
/* 275 */     this.commentsDay = commentsDay;
/*     */   }
/*     */ 
/*     */   public Integer getDownloads()
/*     */   {
/* 283 */     return this.downloads;
/*     */   }
/*     */ 
/*     */   public void setDownloads(Integer downloads)
/*     */   {
/* 291 */     this.downloads = downloads;
/*     */   }
/*     */ 
/*     */   public Integer getDownloadsMonth()
/*     */   {
/* 299 */     return this.downloadsMonth;
/*     */   }
/*     */ 
/*     */   public void setDownloadsMonth(Integer downloadsMonth)
/*     */   {
/* 307 */     this.downloadsMonth = downloadsMonth;
/*     */   }
/*     */ 
/*     */   public Short getDownloadsWeek()
/*     */   {
/* 315 */     return this.downloadsWeek;
/*     */   }
/*     */ 
/*     */   public void setDownloadsWeek(Short downloadsWeek)
/*     */   {
/* 323 */     this.downloadsWeek = downloadsWeek;
/*     */   }
/*     */ 
/*     */   public Short getDownloadsDay()
/*     */   {
/* 331 */     return this.downloadsDay;
/*     */   }
/*     */ 
/*     */   public void setDownloadsDay(Short downloadsDay)
/*     */   {
/* 339 */     this.downloadsDay = downloadsDay;
/*     */   }
/*     */ 
/*     */   public Integer getUps()
/*     */   {
/* 347 */     return this.ups;
/*     */   }
/*     */ 
/*     */   public void setUps(Integer ups)
/*     */   {
/* 355 */     this.ups = ups;
/*     */   }
/*     */ 
/*     */   public Integer getUpsMonth()
/*     */   {
/* 363 */     return this.upsMonth;
/*     */   }
/*     */ 
/*     */   public void setUpsMonth(Integer upsMonth)
/*     */   {
/* 371 */     this.upsMonth = upsMonth;
/*     */   }
/*     */ 
/*     */   public Short getUpsWeek()
/*     */   {
/* 379 */     return this.upsWeek;
/*     */   }
/*     */ 
/*     */   public void setUpsWeek(Short upsWeek)
/*     */   {
/* 387 */     this.upsWeek = upsWeek;
/*     */   }
/*     */ 
/*     */   public Short getUpsDay()
/*     */   {
/* 395 */     return this.upsDay;
/*     */   }
/*     */ 
/*     */   public void setUpsDay(Short upsDay)
/*     */   {
/* 403 */     this.upsDay = upsDay;
/*     */   }
/*     */ 
/*     */   public Integer getDowns()
/*     */   {
/* 411 */     return this.downs;
/*     */   }
/*     */ 
/*     */   public void setDowns(Integer downs)
/*     */   {
/* 419 */     this.downs = downs;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 427 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 435 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 441 */     if (obj == null) return false;
/* 442 */     if (!(obj instanceof ContentCount)) return false;
/*     */ 
/* 444 */     ContentCount contentCount = (ContentCount)obj;
/* 445 */     if ((getId() == null) || (contentCount.getId() == null)) return false;
/* 446 */     return getId().equals(contentCount.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 451 */     if (-2147483648 == this.hashCode) {
/* 452 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 454 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 455 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 458 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 463 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentCount
 * JD-Core Version:    0.6.0
 */