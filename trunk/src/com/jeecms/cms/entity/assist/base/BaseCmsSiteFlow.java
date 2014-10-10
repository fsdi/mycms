/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsSiteFlow;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsSiteFlow
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsSiteFlow";
/*  18 */   public static String PROP_SESSION_ID = "sessionId";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_REFERER_PAGE = "refererPage";
/*  21 */   public static String PROP_AREA = "area";
/*  22 */   public static String PROP_ID = "id";
/*  23 */   public static String PROP_ACCESS_TIME = "accessTime";
/*  24 */   public static String PROP_ACCESS_PAGE = "accessPage";
/*  25 */   public static String PROP_ACCESS_DATE = "accessDate";
/*  26 */   public static String PROP_ACCESS_IP = "accessIp";
/*  27 */   public static String PROP_REFERER_WEB_SITE = "refererWebSite";
/*  28 */   public static String PROP_REFERER_KEYWORD = "refererKeyword";
/*     */ 
/*  68 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String accessIp;
/*     */   private String accessDate;
/*     */   private Date accessTime;
/*     */   private String accessPage;
/*     */   private String refererWebSite;
/*     */   private String refererPage;
/*     */   private String refererKeyword;
/*     */   private String area;
/*     */   private String sessionId;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsSiteFlow()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteFlow(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSiteFlow(Integer id, CmsSite site, String accessIp, String accessDate, String accessPage, String sessionId)
/*     */   {
/*  55 */     setId(id);
/*  56 */     setSite(site);
/*  57 */     setAccessIp(accessIp);
/*  58 */     setAccessDate(accessDate);
/*  59 */     setAccessPage(accessPage);
/*  60 */     setSessionId(sessionId);
/*  61 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  96 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 104 */     this.id = id;
/* 105 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getAccessIp()
/*     */   {
/* 115 */     return this.accessIp;
/*     */   }
/*     */ 
/*     */   public void setAccessIp(String accessIp)
/*     */   {
/* 123 */     this.accessIp = accessIp;
/*     */   }
/*     */ 
/*     */   public String getAccessDate()
/*     */   {
/* 131 */     return this.accessDate;
/*     */   }
/*     */ 
/*     */   public void setAccessDate(String accessDate)
/*     */   {
/* 139 */     this.accessDate = accessDate;
/*     */   }
/*     */ 
/*     */   public Date getAccessTime()
/*     */   {
/* 147 */     return this.accessTime;
/*     */   }
/*     */ 
/*     */   public void setAccessTime(Date accessTime)
/*     */   {
/* 155 */     this.accessTime = accessTime;
/*     */   }
/*     */ 
/*     */   public String getAccessPage()
/*     */   {
/* 163 */     return this.accessPage;
/*     */   }
/*     */ 
/*     */   public void setAccessPage(String accessPage)
/*     */   {
/* 171 */     this.accessPage = accessPage;
/*     */   }
/*     */ 
/*     */   public String getRefererWebSite()
/*     */   {
/* 179 */     return this.refererWebSite;
/*     */   }
/*     */ 
/*     */   public void setRefererWebSite(String refererWebSite)
/*     */   {
/* 187 */     this.refererWebSite = refererWebSite;
/*     */   }
/*     */ 
/*     */   public String getRefererPage()
/*     */   {
/* 195 */     return this.refererPage;
/*     */   }
/*     */ 
/*     */   public void setRefererPage(String refererPage)
/*     */   {
/* 203 */     this.refererPage = refererPage;
/*     */   }
/*     */ 
/*     */   public String getRefererKeyword()
/*     */   {
/* 211 */     return this.refererKeyword;
/*     */   }
/*     */ 
/*     */   public void setRefererKeyword(String refererKeyword)
/*     */   {
/* 219 */     this.refererKeyword = refererKeyword;
/*     */   }
/*     */ 
/*     */   public String getArea()
/*     */   {
/* 227 */     return this.area;
/*     */   }
/*     */ 
/*     */   public void setArea(String area)
/*     */   {
/* 235 */     this.area = area;
/*     */   }
/*     */ 
/*     */   public String getSessionId()
/*     */   {
/* 243 */     return this.sessionId;
/*     */   }
/*     */ 
/*     */   public void setSessionId(String sessionId)
/*     */   {
/* 251 */     this.sessionId = sessionId;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 259 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 267 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 273 */     if (obj == null) return false;
/* 274 */     if (!(obj instanceof CmsSiteFlow)) return false;
/*     */ 
/* 276 */     CmsSiteFlow cmsSiteFlow = (CmsSiteFlow)obj;
/* 277 */     if ((getId() == null) || (cmsSiteFlow.getId() == null)) return false;
/* 278 */     return getId().equals(cmsSiteFlow.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 283 */     if (-2147483648 == this.hashCode) {
/* 284 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 286 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 287 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 290 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 295 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsSiteFlow
 * JD-Core Version:    0.6.0
 */