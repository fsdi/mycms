/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsGroup
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsGroup";
/*  18 */   public static String PROP_NEED_CHECK = "needCheck";
/*  19 */   public static String PROP_ALLOW_MAX_FILE = "allowMaxFile";
/*  20 */   public static String PROP_ALLOW_SUFFIX = "allowSuffix";
/*  21 */   public static String PROP_ALLOW_PER_DAY = "allowPerDay";
/*  22 */   public static String PROP_PRIORITY = "priority";
/*  23 */   public static String PROP_NAME = "name";
/*  24 */   public static String PROP_ID = "id";
/*  25 */   public static String PROP_REG_DEF = "regDef";
/*  26 */   public static String PROP_NEED_CAPTCHA = "needCaptcha";
/*     */ 
/*  70 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private Integer allowPerDay;
/*     */   private Integer allowMaxFile;
/*     */   private String allowSuffix;
/*     */   private Boolean needCaptcha;
/*     */   private Boolean needCheck;
/*     */   private Boolean regDef;
/*     */   private Set<Channel> viewChannels;
/*     */   private Set<Channel> contriChannels;
/*     */ 
/*     */   public BaseCmsGroup()
/*     */   {
/*  31 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGroup(Integer id)
/*     */   {
/*  38 */     setId(id);
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGroup(Integer id, String name, Integer priority, Integer allowPerDay, Integer allowMaxFile, Boolean needCaptcha, Boolean needCheck, Boolean regDef)
/*     */   {
/*  55 */     setId(id);
/*  56 */     setName(name);
/*  57 */     setPriority(priority);
/*  58 */     setAllowPerDay(allowPerDay);
/*  59 */     setAllowMaxFile(allowMaxFile);
/*  60 */     setNeedCaptcha(needCaptcha);
/*  61 */     setNeedCheck(needCheck);
/*  62 */     setRegDef(regDef);
/*  63 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  98 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 106 */     this.id = id;
/* 107 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 117 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 125 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 133 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 141 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Integer getAllowPerDay()
/*     */   {
/* 149 */     return this.allowPerDay;
/*     */   }
/*     */ 
/*     */   public void setAllowPerDay(Integer allowPerDay)
/*     */   {
/* 157 */     this.allowPerDay = allowPerDay;
/*     */   }
/*     */ 
/*     */   public Integer getAllowMaxFile()
/*     */   {
/* 165 */     return this.allowMaxFile;
/*     */   }
/*     */ 
/*     */   public void setAllowMaxFile(Integer allowMaxFile)
/*     */   {
/* 173 */     this.allowMaxFile = allowMaxFile;
/*     */   }
/*     */ 
/*     */   public String getAllowSuffix()
/*     */   {
/* 181 */     return this.allowSuffix;
/*     */   }
/*     */ 
/*     */   public void setAllowSuffix(String allowSuffix)
/*     */   {
/* 189 */     this.allowSuffix = allowSuffix;
/*     */   }
/*     */ 
/*     */   public Boolean getNeedCaptcha()
/*     */   {
/* 197 */     return this.needCaptcha;
/*     */   }
/*     */ 
/*     */   public void setNeedCaptcha(Boolean needCaptcha)
/*     */   {
/* 205 */     this.needCaptcha = needCaptcha;
/*     */   }
/*     */ 
/*     */   public Boolean getNeedCheck()
/*     */   {
/* 213 */     return this.needCheck;
/*     */   }
/*     */ 
/*     */   public void setNeedCheck(Boolean needCheck)
/*     */   {
/* 221 */     this.needCheck = needCheck;
/*     */   }
/*     */ 
/*     */   public Boolean getRegDef()
/*     */   {
/* 229 */     return this.regDef;
/*     */   }
/*     */ 
/*     */   public void setRegDef(Boolean regDef)
/*     */   {
/* 237 */     this.regDef = regDef;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getViewChannels()
/*     */   {
/* 244 */     return this.viewChannels;
/*     */   }
/*     */ 
/*     */   public void setViewChannels(Set<Channel> viewChannels)
/*     */   {
/* 252 */     this.viewChannels = viewChannels;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getContriChannels()
/*     */   {
/* 260 */     return this.contriChannels;
/*     */   }
/*     */ 
/*     */   public void setContriChannels(Set<Channel> contriChannels)
/*     */   {
/* 268 */     this.contriChannels = contriChannels;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 274 */     if (obj == null) return false;
/* 275 */     if (!(obj instanceof CmsGroup)) return false;
/*     */ 
/* 277 */     CmsGroup cmsGroup = (CmsGroup)obj;
/* 278 */     if ((getId() == null) || (cmsGroup.getId() == null)) return false;
/* 279 */     return getId().equals(cmsGroup.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 284 */     if (-2147483648 == this.hashCode) {
/* 285 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 287 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 288 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 291 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 296 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsGroup
 * JD-Core Version:    0.6.0
 */