/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.base.BaseChannelExt;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ChannelExt extends BaseChannelExt
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int COMMENT_OPEN = 0;
/*     */   public static final int COMMENT_LOGIN = 1;
/*     */   public static final int COMMENT_OFF = 2;
/*     */ 
/*     */   public void init()
/*     */   {
/*  23 */     if (getHasTitleImg() == null) {
/*  24 */       setHasTitleImg(Boolean.valueOf(false));
/*     */     }
/*  26 */     if (getHasContentImg() == null) {
/*  27 */       setHasContentImg(Boolean.valueOf(false));
/*     */     }
/*  29 */     if (getTitleImgWidth() == null) {
/*  30 */       setTitleImgWidth(Integer.valueOf(139));
/*     */     }
/*  32 */     if (getTitleImgHeight() == null) {
/*  33 */       setTitleImgHeight(Integer.valueOf(139));
/*     */     }
/*  35 */     if (getContentImgWidth() == null) {
/*  36 */       setContentImgWidth(Integer.valueOf(310));
/*     */     }
/*  38 */     if (getContentImgHeight() == null) {
/*  39 */       setContentImgHeight(Integer.valueOf(310));
/*     */     }
/*  41 */     if (getBlank() == null) {
/*  42 */       setBlank(Boolean.valueOf(false));
/*     */     }
/*  44 */     if (getCommentControl() == null) {
/*  45 */       setCommentControl(Integer.valueOf(0));
/*     */     }
/*  47 */     if (getAllowUpdown() == null) {
/*  48 */       setAllowUpdown(Boolean.valueOf(true));
/*     */     }
/*  50 */     if (getStaticChannel() == null) {
/*  51 */       setStaticChannel(Boolean.valueOf(false));
/*     */     }
/*  53 */     if (getStaticContent() == null) {
/*  54 */       setStaticContent(Boolean.valueOf(false));
/*     */     }
/*  56 */     if (getAccessByDir() == null) {
/*  57 */       setAccessByDir(Boolean.valueOf(true));
/*     */     }
/*  59 */     if (getListChild() == null) {
/*  60 */       setListChild(Boolean.valueOf(false));
/*     */     }
/*  62 */     if (getPageSize() == null) {
/*  63 */       setPageSize(Integer.valueOf(20));
/*     */     }
/*  65 */     blankToNull();
/*     */   }
/*     */ 
/*     */   public void blankToNull() {
/*  69 */     if (StringUtils.isBlank(getLink())) {
/*  70 */       setLink(null);
/*     */     }
/*  72 */     if (StringUtils.isBlank(getTplChannel())) {
/*  73 */       setTplChannel(null);
/*     */     }
/*  75 */     if (StringUtils.isBlank(getTplContent())) {
/*  76 */       setTplContent(null);
/*     */     }
/*  78 */     if (StringUtils.isBlank(getTitle())) {
/*  79 */       setTitle(null);
/*     */     }
/*  81 */     if (StringUtils.isBlank(getKeywords())) {
/*  82 */       setKeywords(null);
/*     */     }
/*  84 */     if (StringUtils.isBlank(getDescription())) {
/*  85 */       setDescription(null);
/*     */     }
/*  87 */     if (StringUtils.isBlank(getTitleImg())) {
/*  88 */       setTitleImg(null);
/*     */     }
/*  90 */     if (StringUtils.isBlank(getContentImg())) {
/*  91 */       setContentImg(null);
/*     */     }
/*  93 */     if (StringUtils.isBlank(getChannelRule())) {
/*  94 */       setChannelRule(null);
/*     */     }
/*  96 */     if (StringUtils.isBlank(getContentRule()))
/*  97 */       setContentRule(null);
/*     */   }
/*     */ 
/*     */   public ChannelExt()
/*     */   {
/*     */   }
/*     */ 
/*     */   public ChannelExt(Integer id)
/*     */   {
/* 110 */     super(id);
/*     */   }
/*     */ 
/*     */   public ChannelExt(Integer id, String name, Boolean staticChannel, Boolean staticContent, Boolean accessByDir, Boolean listChild, Integer pageSize, Boolean hasTitleImg, Boolean hasContentImg, Integer titleImgWidth, Integer titleImgHeight, Integer contentImgWidth, Integer contentImgHeight, Integer commentControl, Boolean blank)
/*     */   {
/* 129 */     super(id, name, staticChannel, staticContent, accessByDir, listChild, 
/* 127 */       pageSize, hasTitleImg, hasContentImg, titleImgWidth, 
/* 128 */       titleImgHeight, contentImgWidth, contentImgHeight, 
/* 129 */       commentControl, blank);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.ChannelExt
 * JD-Core Version:    0.6.0
 */