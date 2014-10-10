/*     */ package com.jeecms.core.entity.base;
/*     */ 
/*     */ import com.jeecms.core.entity.Config;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseConfig
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "Config";
/*  18 */   public static String PROP_VALUE = "value";
/*  19 */   public static String PROP_ID = "id";
/*     */ 
/*  39 */   private int hashCode = -2147483648;
/*     */   private String id;
/*     */   private String value;
/*     */ 
/*     */   public BaseConfig()
/*     */   {
/*  24 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseConfig(String id)
/*     */   {
/*  31 */     setId(id);
/*  32 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  56 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  64 */     this.id = id;
/*  65 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/*  75 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/*  83 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  89 */     if (obj == null) return false;
/*  90 */     if (!(obj instanceof Config)) return false;
/*     */ 
/*  92 */     Config config = (Config)obj;
/*  93 */     if ((getId() == null) || (config.getId() == null)) return false;
/*  94 */     return getId().equals(config.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  99 */     if (-2147483648 == this.hashCode) {
/* 100 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 102 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 103 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 106 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 111 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.base.BaseConfig
 * JD-Core Version:    0.6.0
 */