/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsKeyword;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsKeyword
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsKeyword";
/*  18 */   public static String PROP_SITE = "site";
/*  19 */   public static String PROP_DISABLED = "disabled";
/*  20 */   public static String PROP_URL = "url";
/*  21 */   public static String PROP_NAME = "name";
/*  22 */   public static String PROP_ID = "id";
/*     */ 
/*  58 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String url;
/*     */   private Boolean disabled;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsKeyword()
/*     */   {
/*  27 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsKeyword(Integer id)
/*     */   {
/*  34 */     setId(id);
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsKeyword(Integer id, String name, String url, Boolean disabled)
/*     */   {
/*  47 */     setId(id);
/*  48 */     setName(name);
/*  49 */     setUrl(url);
/*  50 */     setDisabled(disabled);
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
/*     */   public String getUrl()
/*     */   {
/* 115 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 123 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public Boolean getDisabled()
/*     */   {
/* 131 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled)
/*     */   {
/* 139 */     this.disabled = disabled;
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
/* 162 */     if (!(obj instanceof CmsKeyword)) return false;
/*     */ 
/* 164 */     CmsKeyword cmsKeyword = (CmsKeyword)obj;
/* 165 */     if ((getId() == null) || (cmsKeyword.getId() == null)) return false;
/* 166 */     return getId().equals(cmsKeyword.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsKeyword
 * JD-Core Version:    0.6.0
 */