/*     */ package com.jeecms.core.entity.base;
/*     */ 
/*     */ import com.jeecms.core.entity.DbFile;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseDbFile
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "DbFile";
/*  18 */   public static String PROP_LAST_MODIFIED = "lastModified";
/*  19 */   public static String PROP_LENGTH = "length";
/*  20 */   public static String PROP_CONTENT = "content";
/*  21 */   public static String PROP_ID = "id";
/*     */ 
/*  57 */   private int hashCode = -2147483648;
/*     */   private String id;
/*     */   private Integer length;
/*     */   private Long lastModified;
/*     */   private byte[] content;
/*     */ 
/*     */   public BaseDbFile()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseDbFile(String id)
/*     */   {
/*  33 */     setId(id);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseDbFile(String id, Integer length, Long lastModified, byte[] content)
/*     */   {
/*  46 */     setId(id);
/*  47 */     setLength(length);
/*  48 */     setLastModified(lastModified);
/*  49 */     setContent(content);
/*  50 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  76 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  84 */     this.id = id;
/*  85 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Integer getLength()
/*     */   {
/*  95 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void setLength(Integer length)
/*     */   {
/* 103 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public Long getLastModified()
/*     */   {
/* 111 */     return this.lastModified;
/*     */   }
/*     */ 
/*     */   public void setLastModified(Long lastModified)
/*     */   {
/* 119 */     this.lastModified = lastModified;
/*     */   }
/*     */ 
/*     */   public byte[] getContent()
/*     */   {
/* 127 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(byte[] content)
/*     */   {
/* 135 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 141 */     if (obj == null) return false;
/* 142 */     if (!(obj instanceof DbFile)) return false;
/*     */ 
/* 144 */     DbFile dbFile = (DbFile)obj;
/* 145 */     if ((getId() == null) || (dbFile.getId() == null)) return false;
/* 146 */     return getId().equals(dbFile.getId());
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
 * Qualified Name:     com.jeecms.core.entity.base.BaseDbFile
 * JD-Core Version:    0.6.0
 */