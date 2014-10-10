/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsAdvertisingSpace
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsAdvertisingSpace";
/*  18 */   public static String PROP_DESCRIPTION = "description";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_ENABLED = "enabled";
/*  21 */   public static String PROP_NAME = "name";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  58 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String description;
/*     */   private Boolean enabled;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsAdvertisingSpace()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAdvertisingSpace(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAdvertisingSpace(Integer id, CmsSite site, String name, Boolean enabled)
/*     */   {
/*  47 */     setId(id);
/*  48 */     setSite(site);
/*  49 */     setName(name);
/*  50 */     setEnabled(enabled);
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  80 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  88 */     this.id = id;
/*  89 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  99 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 107 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 115 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 123 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Boolean getEnabled()
/*     */   {
/* 131 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   public void setEnabled(Boolean enabled)
/*     */   {
/* 139 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 147 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 155 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 161 */     if (obj == null) return false;
/* 162 */     if (!(obj instanceof CmsAdvertisingSpace)) return false;
/*     */ 
/* 164 */     CmsAdvertisingSpace cmsAdvertisingSpace = (CmsAdvertisingSpace)obj;
/* 165 */     if ((getId() == null) || (cmsAdvertisingSpace.getId() == null)) return false;
/* 166 */     return getId().equals(cmsAdvertisingSpace.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 171 */     if (-2147483648 == this.hashCode) {
/* 172 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 174 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 175 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 178 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 183 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsAdvertisingSpace
 * JD-Core Version:    0.6.0
 */