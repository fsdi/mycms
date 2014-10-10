/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsSensitivity;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsSensitivity
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsSensitivity";
/*  18 */   public static String PROP_SEARCH = "search";
/*  19 */   public static String PROP_ID = "id";
/*  20 */   public static String PROP_REPLACEMENT = "replacement";
/*     */ 
/*  54 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String search;
/*     */   private String replacement;
/*     */ 
/*     */   public BaseCmsSensitivity()
/*     */   {
/*  25 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSensitivity(Integer id)
/*     */   {
/*  32 */     setId(id);
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsSensitivity(Integer id, String search, String replacement)
/*     */   {
/*  44 */     setId(id);
/*  45 */     setSearch(search);
/*  46 */     setReplacement(replacement);
/*  47 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  72 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  80 */     this.id = id;
/*  81 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getSearch()
/*     */   {
/*  91 */     return this.search;
/*     */   }
/*     */ 
/*     */   public void setSearch(String search)
/*     */   {
/*  99 */     this.search = search;
/*     */   }
/*     */ 
/*     */   public String getReplacement()
/*     */   {
/* 107 */     return this.replacement;
/*     */   }
/*     */ 
/*     */   public void setReplacement(String replacement)
/*     */   {
/* 115 */     this.replacement = replacement;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 121 */     if (obj == null) return false;
/* 122 */     if (!(obj instanceof CmsSensitivity)) return false;
/*     */ 
/* 124 */     CmsSensitivity cmsSensitivity = (CmsSensitivity)obj;
/* 125 */     if ((getId() == null) || (cmsSensitivity.getId() == null)) return false;
/* 126 */     return getId().equals(cmsSensitivity.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 131 */     if (-2147483648 == this.hashCode) {
/* 132 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 134 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 135 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 138 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 143 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsSensitivity
 * JD-Core Version:    0.6.0
 */