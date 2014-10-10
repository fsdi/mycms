/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.File;
/*     */ 
/*     */ class UploadedFile
/*     */ {
/*     */   private String dir;
/*     */   private String filename;
/*     */   private String original;
/*     */   private String type;
/*     */ 
/*     */   UploadedFile(String dir, String filename, String original, String type)
/*     */   {
/* 448 */     this.dir = dir;
/* 449 */     this.filename = filename;
/* 450 */     this.original = original;
/* 451 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getContentType() {
/* 455 */     return this.type;
/*     */   }
/*     */ 
/*     */   public String getFilesystemName() {
/* 459 */     return this.filename;
/*     */   }
/*     */ 
/*     */   public String getOriginalFileName() {
/* 463 */     return this.original;
/*     */   }
/*     */ 
/*     */   public File getFile() {
/* 467 */     if ((this.dir == null) || (this.filename == null)) {
/* 468 */       return null;
/*     */     }
/*     */ 
/* 471 */     return new File(this.dir + File.separator + this.filename);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.UploadedFile
 * JD-Core Version:    0.6.0
 */