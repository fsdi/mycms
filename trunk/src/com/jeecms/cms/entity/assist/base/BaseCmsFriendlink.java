/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFriendlink;
/*     */ import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsFriendlink
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsFriendlink";
/*  18 */   public static String PROP_DOMAIN = "domain";
/*  19 */   public static String PROP_EMAIL = "email";
/*  20 */   public static String PROP_DESCRIPTION = "description";
/*  21 */   public static String PROP_LOGO = "logo";
/*  22 */   public static String PROP_SITE = "site";
/*  23 */   public static String PROP_ENABLED = "enabled";
/*  24 */   public static String PROP_VIEWS = "views";
/*  25 */   public static String PROP_CATEGORY = "category";
/*  26 */   public static String PROP_PRIORITY = "priority";
/*  27 */   public static String PROP_NAME = "name";
/*  28 */   public static String PROP_ID = "id";
/*     */ 
/*  72 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String domain;
/*     */   private String logo;
/*     */   private String email;
/*     */   private String description;
/*     */   private Integer views;
/*     */   private Integer priority;
/*     */   private Boolean enabled;
/*     */   private CmsFriendlinkCtg category;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsFriendlink()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFriendlink(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFriendlink(Integer id, CmsFriendlinkCtg category, CmsSite site, String name, String domain, Integer views, Integer priority, Boolean enabled)
/*     */   {
/*  57 */     setId(id);
/*  58 */     setCategory(category);
/*  59 */     setSite(site);
/*  60 */     setName(name);
/*  61 */     setDomain(domain);
/*  62 */     setViews(views);
/*  63 */     setPriority(priority);
/*  64 */     setEnabled(enabled);
/*  65 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 100 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 108 */     this.id = id;
/* 109 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 119 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 127 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getDomain()
/*     */   {
/* 135 */     return this.domain;
/*     */   }
/*     */ 
/*     */   public void setDomain(String domain)
/*     */   {
/* 143 */     this.domain = domain;
/*     */   }
/*     */ 
/*     */   public String getLogo()
/*     */   {
/* 151 */     return this.logo;
/*     */   }
/*     */ 
/*     */   public void setLogo(String logo)
/*     */   {
/* 159 */     this.logo = logo;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 167 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 175 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 183 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 191 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Integer getViews()
/*     */   {
/* 199 */     return this.views;
/*     */   }
/*     */ 
/*     */   public void setViews(Integer views)
/*     */   {
/* 207 */     this.views = views;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 215 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 223 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Boolean getEnabled()
/*     */   {
/* 231 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   public void setEnabled(Boolean enabled)
/*     */   {
/* 239 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   public CmsFriendlinkCtg getCategory()
/*     */   {
/* 247 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(CmsFriendlinkCtg category)
/*     */   {
/* 255 */     this.category = category;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 263 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 271 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 277 */     if (obj == null) return false;
/* 278 */     if (!(obj instanceof CmsFriendlink)) return false;
/*     */ 
/* 280 */     CmsFriendlink cmsFriendlink = (CmsFriendlink)obj;
/* 281 */     if ((getId() == null) || (cmsFriendlink.getId() == null)) return false;
/* 282 */     return getId().equals(cmsFriendlink.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 287 */     if (-2147483648 == this.hashCode) {
/* 288 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 290 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 291 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 294 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 299 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsFriendlink
 * JD-Core Version:    0.6.0
 */