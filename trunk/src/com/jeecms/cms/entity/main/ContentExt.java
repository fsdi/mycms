/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseContentExt;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ContentExt extends BaseContentExt
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getStitle()
/*     */   {
/*  18 */     if (!StringUtils.isBlank(getShortTitle())) {
/*  19 */       return getShortTitle();
/*     */     }
/*  21 */     return getTitle();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  26 */     if (getReleaseDate() == null) {
/*  27 */       setReleaseDate(new Timestamp(System.currentTimeMillis()));
/*     */     }
/*  29 */     if (getBold() == null) {
/*  30 */       setBold(Boolean.valueOf(false));
/*     */     }
/*  32 */     if (getNeedRegenerate() == null) {
/*  33 */       setNeedRegenerate(Boolean.valueOf(true));
/*     */     }
/*  35 */     blankToNull();
/*     */   }
/*     */ 
/*     */   public void blankToNull() {
/*  39 */     if (StringUtils.isBlank(getShortTitle())) {
/*  40 */       setShortTitle(null);
/*     */     }
/*  42 */     if (StringUtils.isBlank(getAuthor())) {
/*  43 */       setAuthor(null);
/*     */     }
/*  45 */     if (StringUtils.isBlank(getOrigin())) {
/*  46 */       setOrigin(null);
/*     */     }
/*  48 */     if (StringUtils.isBlank(getOriginUrl())) {
/*  49 */       setOriginUrl(null);
/*     */     }
/*  51 */     if (StringUtils.isBlank(getDescription())) {
/*  52 */       setDescription(null);
/*     */     }
/*  54 */     if (StringUtils.isBlank(getTitleColor())) {
/*  55 */       setTitleColor(null);
/*     */     }
/*  57 */     if (StringUtils.isBlank(getTitleImg())) {
/*  58 */       setTitleImg(null);
/*     */     }
/*  60 */     if (StringUtils.isBlank(getContentImg())) {
/*  61 */       setContentImg(null);
/*     */     }
/*  63 */     if (StringUtils.isBlank(getTypeImg())) {
/*  64 */       setTypeImg(null);
/*     */     }
/*  66 */     if (StringUtils.isBlank(getLink())) {
/*  67 */       setLink(null);
/*     */     }
/*  69 */     if (StringUtils.isBlank(getTplContent())) {
/*  70 */       setTplContent(null);
/*     */     }
/*  72 */     if (StringUtils.isBlank(getMediaPath())) {
/*  73 */       setMediaPath(null);
/*     */     }
/*  75 */     if (StringUtils.isBlank(getMediaType()))
/*  76 */       setMediaType(null);
/*     */   }
/*     */ 
/*     */   public ContentExt()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ContentExt(Integer id)
/*     */   {
/*  89 */     super(id);
/*     */   }
/*     */ 
/*     */   public ContentExt(Integer id, String title, Date releaseDate, Boolean bold, Boolean needRegenerate)
/*     */   {
/* 107 */     super(id, 
/* 104 */       title, 
/* 105 */       releaseDate, 
/* 106 */       bold, 
/* 107 */       needRegenerate);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ContentExt
 * JD-Core Version:    0.6.0
 */