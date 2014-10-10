/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsFile
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsFile";
/*  18 */   public static String PROP_FILE_ISVALID = "fileIsvalid";
/*  19 */   public static String PROP_FILE_NAME = "fileName";
/*  20 */   public static String PROP_FILE_PATH = "filePath";
/*  21 */   public static String PROP_CONTENT = "content";
/*     */ 
/*  53 */   private int hashCode = -2147483648;
/*     */   private String filePath;
/*     */   private String fileName;
/*     */   private boolean fileIsvalid;
/*     */   private Content content;
/*     */ 
/*     */   public BaseCmsFile()
/*     */   {
/*  26 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFile(String filePath)
/*     */   {
/*  33 */     setFilePath(filePath);
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsFile(String filePath, boolean fileIsvalid)
/*     */   {
/*  44 */     setFilePath(filePath);
/*  45 */     setFileIsvalid(fileIsvalid);
/*  46 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public String getFilePath()
/*     */   {
/*  74 */     return this.filePath;
/*     */   }
/*     */ 
/*     */   public void setFilePath(String filePath)
/*     */   {
/*  82 */     this.filePath = filePath;
/*  83 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/*  93 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public void setFileName(String fileName)
/*     */   {
/* 101 */     this.fileName = fileName;
/*     */   }
/*     */ 
/*     */   public boolean isFileIsvalid()
/*     */   {
/* 109 */     return this.fileIsvalid;
/*     */   }
/*     */ 
/*     */   public void setFileIsvalid(boolean fileIsvalid)
/*     */   {
/* 117 */     this.fileIsvalid = fileIsvalid;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 125 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 133 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 139 */     if (obj == null) return false;
/* 140 */     if (!(obj instanceof CmsFile)) return false;
/*     */ 
/* 142 */     CmsFile cmsFile = (CmsFile)obj;
/* 143 */     if ((getFilePath() == null) || (cmsFile.getFilePath() == null)) return false;
/* 144 */     return getFilePath().equals(cmsFile.getFilePath());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 149 */     if (-2147483648 == this.hashCode) {
/* 150 */       if (getFilePath() == null) return super.hashCode();
/*     */ 
/* 152 */       String hashStr = getClass().getName() + ":" + getFilePath().hashCode();
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
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsFile
 * JD-Core Version:    0.6.0
 */