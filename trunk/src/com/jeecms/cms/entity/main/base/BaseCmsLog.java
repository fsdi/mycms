/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsLog;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsLog
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsLog";
/*  18 */   public static String PROP_TIME = "time";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_USER = "user";
/*  21 */   public static String PROP_URL = "url";
/*  22 */   public static String PROP_IP = "ip";
/*  23 */   public static String PROP_CATEGORY = "category";
/*  24 */   public static String PROP_TITLE = "title";
/*  25 */   public static String PROP_CONTENT = "content";
/*  26 */   public static String PROP_ID = "id";
/*     */ 
/*  60 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Integer category;
/*     */   private Date time;
/*     */   private String ip;
/*     */   private String url;
/*     */   private String title;
/*     */   private String content;
/*     */   private CmsUser user;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsLog()
/*     */   {
/*  31 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsLog(Integer id)
/*     */   {
/*  38 */     setId(id);
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsLog(Integer id, Integer category, Date time)
/*     */   {
/*  50 */     setId(id);
/*  51 */     setCategory(category);
/*  52 */     setTime(time);
/*  53 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  86 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  94 */     this.id = id;
/*  95 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Integer getCategory()
/*     */   {
/* 105 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(Integer category)
/*     */   {
/* 113 */     this.category = category;
/*     */   }
/*     */ 
/*     */   public Date getTime()
/*     */   {
/* 121 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(Date time)
/*     */   {
/* 129 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getIp()
/*     */   {
/* 137 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 145 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 153 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 161 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 169 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 177 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 185 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 193 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 201 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 209 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 217 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 225 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 231 */     if (obj == null) return false;
/* 232 */     if (!(obj instanceof CmsLog)) return false;
/*     */ 
/* 234 */     CmsLog cmsLog = (CmsLog)obj;
/* 235 */     if ((getId() == null) || (cmsLog.getId() == null)) return false;
/* 236 */     return getId().equals(cmsLog.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 241 */     if (-2147483648 == this.hashCode) {
/* 242 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 244 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 245 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 248 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 253 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsLog
 * JD-Core Version:    0.6.0
 */