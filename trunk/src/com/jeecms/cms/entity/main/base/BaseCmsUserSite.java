/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsUserSite
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsUserSite";
/*  18 */   public static String PROP_ALL_CHANNEL = "allChannel";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_USER = "user";
/*  21 */   public static String PROP_CHECK_STEP = "checkStep";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  60 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Byte checkStep;
/*     */   private Boolean allChannel;
/*     */   private CmsUser user;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsUserSite()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUserSite(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUserSite(Integer id, CmsUser user, CmsSite site, Byte checkStep, Boolean allChannel)
/*     */   {
/*  48 */     setId(id);
/*  49 */     setUser(user);
/*  50 */     setSite(site);
/*  51 */     setCheckStep(checkStep);
/*  52 */     setAllChannel(allChannel);
/*  53 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  90 */     this.id = id;
/*  91 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Byte getCheckStep()
/*     */   {
/* 101 */     return this.checkStep;
/*     */   }
/*     */ 
/*     */   public void setCheckStep(Byte checkStep)
/*     */   {
/* 109 */     this.checkStep = checkStep;
/*     */   }
/*     */ 
/*     */   public Boolean getAllChannel()
/*     */   {
/* 117 */     return this.allChannel;
/*     */   }
/*     */ 
/*     */   public void setAllChannel(Boolean allChannel)
/*     */   {
/* 125 */     this.allChannel = allChannel;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 133 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 141 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 149 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 157 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 163 */     if (obj == null) return false;
/* 164 */     if (!(obj instanceof CmsUserSite)) return false;
/*     */ 
/* 166 */     CmsUserSite cmsUserSite = (CmsUserSite)obj;
/* 167 */     if ((getId() == null) || (cmsUserSite.getId() == null)) return false;
/* 168 */     return getId().equals(cmsUserSite.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 173 */     if (-2147483648 == this.hashCode) {
/* 174 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 176 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 177 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 180 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 185 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsUserSite
 * JD-Core Version:    0.6.0
 */