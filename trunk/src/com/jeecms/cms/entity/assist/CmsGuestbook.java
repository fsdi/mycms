/*     */ package com.jeecms.cms.entity.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.base.BaseCmsGuestbook;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.common.util.StrUtils;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CmsGuestbook extends BaseCmsGuestbook
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getTitleHtml()
/*     */   {
/*  12 */     return StrUtils.txt2htm(getTitle());
/*     */   }
/*     */ 
/*     */   public String getContentHtml() {
/*  16 */     return StrUtils.txt2htm(getContent());
/*     */   }
/*     */ 
/*     */   public String getReplyHtml() {
/*  20 */     return StrUtils.txt2htm(getReply());
/*     */   }
/*     */ 
/*     */   public String getTitle() {
/*  24 */     CmsGuestbookExt ext = getExt();
/*  25 */     if (ext != null) {
/*  26 */       return ext.getTitle();
/*     */     }
/*  28 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/*  33 */     CmsGuestbookExt ext = getExt();
/*  34 */     if (ext != null) {
/*  35 */       return ext.getContent();
/*     */     }
/*  37 */     return null;
/*     */   }
/*     */ 
/*     */   public String getReply()
/*     */   {
/*  42 */     CmsGuestbookExt ext = getExt();
/*  43 */     if (ext != null) {
/*  44 */       return ext.getReply();
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/*  51 */     CmsGuestbookExt ext = getExt();
/*  52 */     if (ext != null) {
/*  53 */       return ext.getEmail();
/*     */     }
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/*  60 */     CmsGuestbookExt ext = getExt();
/*  61 */     if (ext != null) {
/*  62 */       return ext.getPhone();
/*     */     }
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/*  69 */     CmsGuestbookExt ext = getExt();
/*  70 */     if (ext != null) {
/*  71 */       return ext.getQq();
/*     */     }
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/*  78 */     if (getChecked() == null) {
/*  79 */       setChecked(Boolean.valueOf(false));
/*     */     }
/*  81 */     if (getRecommend() == null) {
/*  82 */       setRecommend(Boolean.valueOf(false));
/*     */     }
/*  84 */     if (getCreateTime() == null)
/*  85 */       setCreateTime(new Timestamp(System.currentTimeMillis()));
/*     */   }
/*     */ 
/*     */   public CmsGuestbook()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsGuestbook(Integer id)
/*     */   {
/*  98 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsGuestbook(Integer id, CmsSite site, CmsGuestbookCtg ctg, String ip, Date createTime, Boolean checked, Boolean recommend)
/*     */   {
/* 120 */     super(id, 
/* 115 */       site, 
/* 116 */       ctg, 
/* 117 */       ip, 
/* 118 */       createTime, 
/* 119 */       checked, 
/* 120 */       recommend);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsGuestbook
 * JD-Core Version:    0.6.0
 */