/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseContentAttachment
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentAttachment";
/*  18 */   public static String PROP_PATH = "path";
/*  19 */   public static String PROP_FILENAME = "filename";
/*  20 */   public static String PROP_NAME = "name";
/*  21 */   public static String PROP_COUNT = "count";
/*     */   private String path;
/*     */   private String name;
/*     */   private String filename;
/*     */   private Integer count;
/*     */ 
/*     */   public BaseContentAttachment()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentAttachment(String path, String name, Integer count)
/*     */   {
/*  37 */     setPath(path);
/*  38 */     setName(name);
/*  39 */     setCount(count);
/*  40 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/*  62 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path)
/*     */   {
/*  70 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  78 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/*  86 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getFilename()
/*     */   {
/*  94 */     return this.filename;
/*     */   }
/*     */ 
/*     */   public void setFilename(String filename)
/*     */   {
/* 102 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */   public Integer getCount()
/*     */   {
/* 110 */     return this.count;
/*     */   }
/*     */ 
/*     */   public void setCount(Integer count)
/*     */   {
/* 118 */     this.count = count;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 127 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentAttachment
 * JD-Core Version:    0.6.0
 */