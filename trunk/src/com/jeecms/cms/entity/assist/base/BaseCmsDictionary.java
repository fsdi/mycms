/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsDictionary;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsDictionary
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsDictionary";
/*  18 */   public static String PROP_NAME = "name";
/*  19 */   public static String PROP_VALUE = "value";
/*  20 */   public static String PROP_TYPE = "type";
/*  21 */   public static String PROP_ID = "id";
/*     */ 
/*  57 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String value;
/*     */   private String type;
/*     */ 
/*     */   public BaseCmsDictionary()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsDictionary(Integer id)
/*     */   {
/*  33 */     setId(id);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsDictionary(Integer id, String name, String value, String type)
/*     */   {
/*  46 */     setId(id);
/*  47 */     setName(name);
/*  48 */     setValue(value);
/*  49 */     setType(type);
/*  50 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  84 */     this.id = id;
/*  85 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  95 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 103 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/* 111 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/* 119 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 127 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 135 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 141 */     if (obj == null) return false;
/* 142 */     if (!(obj instanceof CmsDictionary)) return false;
/*     */ 
/* 144 */     CmsDictionary cmsDictionary = (CmsDictionary)obj;
/* 145 */     if ((getId() == null) || (cmsDictionary.getId() == null)) return false;
/* 146 */     return getId().equals(cmsDictionary.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 151 */     if (-2147483648 == this.hashCode) {
/* 152 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 154 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 155 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 158 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 163 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsDictionary
 * JD-Core Version:    0.6.0
 */