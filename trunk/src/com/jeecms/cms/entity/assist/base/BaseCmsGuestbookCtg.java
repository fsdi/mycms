/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsGuestbookCtg
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsGuestbookCtg";
/*  18 */   public static String PROP_DESCRIPTION = "description";
/*  19 */   public static String PROP_SITE = "site";
/*  20 */   public static String PROP_PRIORITY = "priority";
/*  21 */   public static String PROP_NAME = "name";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  58 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer priority;
/*     */   private String description;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsGuestbookCtg()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGuestbookCtg(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsGuestbookCtg(Integer id, CmsSite site, String name, Integer priority)
/*     */   {
/*  47 */     setId(id);
/*  48 */     setSite(site);
/*  49 */     setName(name);
/*  50 */     setPriority(priority);
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
/*     */   public Integer getPriority()
/*     */   {
/* 115 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 123 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 131 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 139 */     this.description = description;
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
/* 162 */     if (!(obj instanceof CmsGuestbookCtg)) return false;
/*     */ 
/* 164 */     CmsGuestbookCtg cmsGuestbookCtg = (CmsGuestbookCtg)obj;
/* 165 */     if ((getId() == null) || (cmsGuestbookCtg.getId() == null)) return false;
/* 166 */     return getId().equals(cmsGuestbookCtg.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsGuestbookCtg
 * JD-Core Version:    0.6.0
 */