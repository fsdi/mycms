/*     */ package com.jeecms.cms.entity.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.base.BaseCmsTask;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class CmsTask extends BaseCmsTask
/*     */ {
/*  15 */   public static int EXECYCLE_CRON = 2;
/*     */ 
/*  19 */   public static int EXECYCLE_DEFINE = 1;
/*     */ 
/*  23 */   public static int EXECYCLE_MINUTE = 1;
/*     */ 
/*  27 */   public static int EXECYCLE_HOUR = 2;
/*     */ 
/*  31 */   public static int EXECYCLE_DAY = 3;
/*     */ 
/*  35 */   public static int EXECYCLE_WEEK = 4;
/*     */ 
/*  39 */   public static int EXECYCLE_MONTH = 5;
/*     */ 
/*  43 */   public static int TASK_STATIC_INDEX = 1;
/*     */ 
/*  47 */   public static int TASK_STATIC_CHANNEL = 2;
/*     */ 
/*  51 */   public static int TASK_STATIC_CONTENT = 3;
/*     */ 
/*  55 */   public static int TASK_ACQU = 4;
/*     */ 
/*  59 */   public static int TASK_DISTRIBUTE = 5;
/*     */ 
/*  63 */   public static String TASK_PARAM_ACQU_ID = "acqu_id";
/*     */ 
/*  67 */   public static String TASK_PARAM_FTP_ID = "ftp_id";
/*     */ 
/*  71 */   public static String TASK_PARAM_SITE_ID = "site_id";
/*     */ 
/*  75 */   public static String TASK_PARAM_CHANNEL_ID = "channel_id";
/*     */ 
/*  79 */   public static String TASK_PARAM_FOLDER_PREFIX = "floder_";
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public void init(CmsSite site, CmsUser user)
/*     */   {
/*  83 */     if (getCreateTime() == null) {
/*  84 */       setCreateTime(Calendar.getInstance().getTime());
/*     */     }
/*  86 */     if (getUser() == null) {
/*  87 */       setUser(user);
/*     */     }
/*  89 */     if (getSite() == null)
/*  90 */       setSite(site);
/*     */   }
/*     */ 
/*     */   public boolean getEnable() {
/*  94 */     return super.isEnable();
/*     */   }
/*     */ 
/*     */   public CmsTask()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsTask(Integer id)
/*     */   {
/* 106 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsTask(Integer id, CmsUser user, CmsSite site, Integer type, String name, String jobClass, boolean enable, Date createTime)
/*     */   {
/* 130 */     super(id, 
/* 124 */       user, 
/* 125 */       site, 
/* 126 */       type, 
/* 127 */       name, 
/* 128 */       jobClass, 
/* 129 */       enable, 
/* 130 */       createTime);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsTask
 * JD-Core Version:    0.6.0
 */