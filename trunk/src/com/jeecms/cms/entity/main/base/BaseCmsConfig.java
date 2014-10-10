/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.EmailConfig;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class BaseCmsConfig
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsConfig";
/*  18 */   public static String PROP_LOGIN_URL = "loginUrl";
/*  19 */   public static String PROP_COLOR = "color";
/*  20 */   public static String PROP_PROCESS_URL = "processUrl";
/*  21 */   public static String PROP_ALPHA = "alpha";
/*  22 */   public static String PROP_DEF_IMG = "defImg";
/*  23 */   public static String PROP_COUNT_CLEAR_TIME = "countClearTime";
/*  24 */   public static String PROP_COUNT_COPY_TIME = "countCopyTime";
/*  25 */   public static String PROP_PORT = "port";
/*  26 */   public static String PROP_DB_FILE_URI = "dbFileUri";
/*  27 */   public static String PROP_CONTEXT_PATH = "contextPath";
/*  28 */   public static String PROP_PASSWORD = "password";
/*  29 */   public static String PROP_OFFSET_X = "offsetX";
/*  30 */   public static String PROP_SERVLET_POINT = "servletPoint";
/*  31 */   public static String PROP_MIN_HEIGHT = "minHeight";
/*  32 */   public static String PROP_CONTENT = "content";
/*  33 */   public static String PROP_ON = "on";
/*  34 */   public static String PROP_DOWNLOAD_CODE = "downloadCode";
/*  35 */   public static String PROP_DOWNLOAD_TIME = "downloadTime";
/*  36 */   public static String PROP_HOST = "host";
/*  37 */   public static String PROP_POS = "pos";
/*  38 */   public static String PROP_MIN_WIDTH = "minWidth";
/*  39 */   public static String PROP_OFFSET_Y = "offsetY";
/*  40 */   public static String PROP_ENCODING = "encoding";
/*  41 */   public static String PROP_SIZE = "size";
/*  42 */   public static String PROP_IMAGE_PATH = "imagePath";
/*  43 */   public static String PROP_PERSONAL = "personal";
/*  44 */   public static String PROP_UPLOAD_TO_DB = "uploadToDb";
/*  45 */   public static String PROP_ID = "id";
/*  46 */   public static String PROP_USERNAME = "username";
/*     */ 
/*  92 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String contextPath;
/*     */   private String servletPoint;
/*     */   private Integer port;
/*     */   private String dbFileUri;
/*     */   private Boolean uploadToDb;
/*     */   private String defImg;
/*     */   private String loginUrl;
/*     */   private String processUrl;
/*     */   private Date countClearTime;
/*     */   private Date countCopyTime;
/*     */   private String downloadCode;
/*     */   private Integer downloadTime;
/*     */   private Boolean emailValidate;
/*     */   MarkConfig m_markConfig;
/*     */   EmailConfig m_emailConfig;
/*     */   private Map<String, String> attr;
/*     */ 
/*     */   public BaseCmsConfig()
/*     */   {
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsConfig(Integer id)
/*     */   {
/*  58 */     setId(id);
/*  59 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsConfig(Integer id, String dbFileUri, Boolean uploadToDb, String defImg, String loginUrl, Date countClearTime, Date countCopyTime, String downloadCode, Integer downloadTime)
/*     */   {
/*  76 */     setId(id);
/*  77 */     setDbFileUri(dbFileUri);
/*  78 */     setUploadToDb(uploadToDb);
/*  79 */     setDefImg(defImg);
/*  80 */     setLoginUrl(loginUrl);
/*  81 */     setCountClearTime(countClearTime);
/*  82 */     setCountCopyTime(countCopyTime);
/*  83 */     setDownloadCode(downloadCode);
/*  84 */     setDownloadTime(downloadTime);
/*  85 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 129 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 137 */     this.id = id;
/* 138 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getContextPath()
/*     */   {
/* 148 */     return this.contextPath;
/*     */   }
/*     */ 
/*     */   public void setContextPath(String contextPath)
/*     */   {
/* 156 */     this.contextPath = contextPath;
/*     */   }
/*     */ 
/*     */   public String getServletPoint()
/*     */   {
/* 164 */     return this.servletPoint;
/*     */   }
/*     */ 
/*     */   public void setServletPoint(String servletPoint)
/*     */   {
/* 172 */     this.servletPoint = servletPoint;
/*     */   }
/*     */ 
/*     */   public Integer getPort()
/*     */   {
/* 180 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(Integer port)
/*     */   {
/* 188 */     this.port = port;
/*     */   }
/*     */ 
/*     */   public String getDbFileUri()
/*     */   {
/* 196 */     return this.dbFileUri;
/*     */   }
/*     */ 
/*     */   public void setDbFileUri(String dbFileUri)
/*     */   {
/* 204 */     this.dbFileUri = dbFileUri;
/*     */   }
/*     */ 
/*     */   public Boolean getUploadToDb()
/*     */   {
/* 212 */     return this.uploadToDb;
/*     */   }
/*     */ 
/*     */   public void setUploadToDb(Boolean uploadToDb)
/*     */   {
/* 220 */     this.uploadToDb = uploadToDb;
/*     */   }
/*     */ 
/*     */   public String getDefImg()
/*     */   {
/* 228 */     return this.defImg;
/*     */   }
/*     */ 
/*     */   public void setDefImg(String defImg)
/*     */   {
/* 236 */     this.defImg = defImg;
/*     */   }
/*     */ 
/*     */   public String getLoginUrl()
/*     */   {
/* 244 */     return this.loginUrl;
/*     */   }
/*     */ 
/*     */   public void setLoginUrl(String loginUrl)
/*     */   {
/* 252 */     this.loginUrl = loginUrl;
/*     */   }
/*     */ 
/*     */   public String getProcessUrl()
/*     */   {
/* 260 */     return this.processUrl;
/*     */   }
/*     */ 
/*     */   public void setProcessUrl(String processUrl)
/*     */   {
/* 268 */     this.processUrl = processUrl;
/*     */   }
/*     */ 
/*     */   public Date getCountClearTime()
/*     */   {
/* 276 */     return this.countClearTime;
/*     */   }
/*     */ 
/*     */   public void setCountClearTime(Date countClearTime)
/*     */   {
/* 284 */     this.countClearTime = countClearTime;
/*     */   }
/*     */ 
/*     */   public Date getCountCopyTime()
/*     */   {
/* 292 */     return this.countCopyTime;
/*     */   }
/*     */ 
/*     */   public void setCountCopyTime(Date countCopyTime)
/*     */   {
/* 300 */     this.countCopyTime = countCopyTime;
/*     */   }
/*     */ 
/*     */   public String getDownloadCode()
/*     */   {
/* 308 */     return this.downloadCode;
/*     */   }
/*     */ 
/*     */   public void setDownloadCode(String downloadCode)
/*     */   {
/* 316 */     this.downloadCode = downloadCode;
/*     */   }
/*     */ 
/*     */   public Integer getDownloadTime()
/*     */   {
/* 324 */     return this.downloadTime;
/*     */   }
/*     */ 
/*     */   public void setDownloadTime(Integer downloadTime)
/*     */   {
/* 332 */     this.downloadTime = downloadTime;
/*     */   }
/*     */ 
/*     */   public Boolean getEmailValidate()
/*     */   {
/* 337 */     return this.emailValidate;
/*     */   }
/*     */ 
/*     */   public void setEmailValidate(Boolean emailValidate) {
/* 341 */     this.emailValidate = emailValidate;
/*     */   }
/*     */ 
/*     */   public MarkConfig getMarkConfig() {
/* 345 */     return this.m_markConfig;
/*     */   }
/*     */ 
/*     */   public void setMarkConfig(MarkConfig m_markConfig)
/*     */   {
/* 353 */     this.m_markConfig = m_markConfig;
/*     */   }
/*     */ 
/*     */   public EmailConfig getEmailConfig()
/*     */   {
/* 358 */     return this.m_emailConfig;
/*     */   }
/*     */ 
/*     */   public void setEmailConfig(EmailConfig m_emailConfig)
/*     */   {
/* 366 */     this.m_emailConfig = m_emailConfig;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 374 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 382 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 388 */     if (obj == null) return false;
/* 389 */     if (!(obj instanceof CmsConfig)) return false;
/*     */ 
/* 391 */     CmsConfig cmsConfig = (CmsConfig)obj;
/* 392 */     if ((getId() == null) || (cmsConfig.getId() == null)) return false;
/* 393 */     return getId().equals(cmsConfig.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 398 */     if (-2147483648 == this.hashCode) {
/* 399 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 401 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 402 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 405 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 410 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsConfig
 * JD-Core Version:    0.6.0
 */