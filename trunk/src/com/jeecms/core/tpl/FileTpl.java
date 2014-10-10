/*    */ package com.jeecms.core.tpl;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ 
/*    */ public class FileTpl
/*    */   implements Tpl
/*    */ {
/*    */   private File file;
/*    */   private String root;
/*    */ 
/*    */   public FileTpl(File file, String root)
/*    */   {
/* 18 */     this.file = file;
/* 19 */     this.root = root;
/*    */   }
/*    */ 
/*    */   public String getName() {
/* 23 */     String ap = this.file.getAbsolutePath().substring(this.root.length());
/* 24 */     ap = ap.replace(File.separatorChar, '/');
/*    */ 
/* 26 */     if (!ap.startsWith("/")) {
/* 27 */       ap = "/" + ap;
/*    */     }
/* 29 */     return ap;
/*    */   }
/*    */ 
/*    */   public String getPath() {
/* 33 */     String name = getName();
/* 34 */     return name.substring(0, name.lastIndexOf('/'));
/*    */   }
/*    */ 
/*    */   public String getFilename() {
/* 38 */     return this.file.getName();
/*    */   }
/*    */ 
/*    */   public String getSource() {
/* 42 */     if (this.file.isDirectory())
/* 43 */       return null;
/*    */     try
/*    */     {
/* 46 */       return FileUtils.readFileToString(this.file, "UTF-8"); } catch (IOException e) {
/*    */     }
/* 48 */     throw new RuntimeException();
/*    */   }
/*    */ 
/*    */   public long getLastModified()
/*    */   {
/* 53 */     return this.file.lastModified();
/*    */   }
/*    */ 
/*    */   public Date getLastModifiedDate() {
/* 57 */     return new Timestamp(getLastModified());
/*    */   }
/*    */ 
/*    */   public long getLength() {
/* 61 */     return this.file.length();
/*    */   }
/*    */ 
/*    */   public int getSize() {
/* 65 */     return (int)(getLength() / 1024L) + 1;
/*    */   }
/*    */ 
/*    */   public boolean isDirectory() {
/* 69 */     return this.file.isDirectory();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.tpl.FileTpl
 * JD-Core Version:    0.6.0
 */