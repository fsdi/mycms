/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.ContentTag;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseContentTag
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentTag";
/*  18 */   public static String PROP_NAME = "name";
/*  19 */   public static String PROP_ID = "id";
/*  20 */   public static String PROP_COUNT = "count";
/*     */ 
/*  54 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer count;
/*     */ 
/*     */   public BaseContentTag()
/*     */   {
/*  25 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentTag(Integer id)
/*     */   {
/*  32 */     setId(id);
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentTag(Integer id, String name, Integer count)
/*     */   {
/*  44 */     setId(id);
/*  45 */     setName(name);
/*  46 */     setCount(count);
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
/*     */   public String getName()
/*     */   {
/*  91 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  99 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getCount()
/*     */   {
/* 107 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Integer count)
/*     */   {
/* 115 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 121 */     if (obj == null) return false;
/* 122 */     if (!(obj instanceof ContentTag)) return false;
/*     */ 
/* 124 */     ContentTag contentTag = (ContentTag)obj;
/* 125 */     if ((getId() == null) || (contentTag.getId() == null)) return false;
/* 126 */     return getId().equals(contentTag.getId());
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
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentTag
 * JD-Core Version:    0.6.0
 */