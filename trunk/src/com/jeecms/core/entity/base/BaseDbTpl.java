/*     */ package com.jeecms.core.entity.base;
/*     */ 
/*     */ import com.jeecms.core.entity.DbTpl;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseDbTpl
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "DbTpl";
/*  18 */   public static String PROP_LAST_MODIFIED = "lastModified";
/*  19 */   public static String PROP_SOURCE = "source";
/*  20 */   public static String PROP_DIRECTORY = "directory";
/*  21 */   public static String PROP_ID = "id";
/*     */ 
/*  55 */   private int hashCode = -2147483648;
/*     */   private String id;
/*     */   private String source;
/*     */   private long lastModified;
/*     */   private boolean directory;
/*     */ 
/*     */   public BaseDbTpl()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseDbTpl(String id)
/*     */   {
/*  33 */     setId(id);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseDbTpl(String id, long lastModified, boolean directory)
/*     */   {
/*  45 */     setId(id);
/*  46 */     setLastModified(lastModified);
/*  47 */     setDirectory(directory);
/*  48 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  74 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  82 */     this.id = id;
/*  83 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getSource()
/*     */   {
/*  93 */     return this.source;
/*     */   }
/*     */ 
/*     */   public void setSource(String source)
/*     */   {
/* 101 */     this.source = source;
/*     */   }
/*     */ 
/*     */   public long getLastModified()
/*     */   {
/* 109 */     return this.lastModified;
/*     */   }
/*     */ 
/*     */   public void setLastModified(long lastModified)
/*     */   {
/* 117 */     this.lastModified = lastModified;
/*     */   }
/*     */ 
/*     */   public boolean isDirectory()
/*     */   {
/* 125 */     return this.directory;
/*     */   }
/*     */ 
/*     */   public void setDirectory(boolean directory)
/*     */   {
/* 133 */     this.directory = directory;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 139 */     if (obj == null) return false;
/* 140 */     if (!(obj instanceof DbTpl)) return false;
/*     */ 
/* 142 */     DbTpl dbTpl = (DbTpl)obj;
/* 143 */     if ((getId() == null) || (dbTpl.getId() == null)) return false;
/* 144 */     return getId().equals(dbTpl.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 149 */     if (-2147483648 == this.hashCode) {
/* 150 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 152 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 153 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 156 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 161 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.base.BaseDbTpl
 * JD-Core Version:    0.6.0
 */