/*    */ package com.jeecms.common.upload;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletContext;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.web.context.ServletContextAware;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ public class FileRepository
/*    */   implements ServletContextAware
/*    */ {
/* 18 */   private Logger log = LoggerFactory.getLogger(FileRepository.class);
/*    */   private ServletContext ctx;
/*    */ 
/*    */   public String storeByExt(String path, String ext, MultipartFile file)
/*    */     throws IOException
/*    */   {
/* 22 */     String filename = UploadUtils.generateFilename(path, ext);
/* 23 */     File dest = new File(getRealPath(filename));
/* 24 */     dest = UploadUtils.getUniqueFile(dest);
/* 25 */     store(file, dest);
/* 26 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByFilename(String filename, MultipartFile file) throws IOException
/*    */   {
/* 31 */     File dest = new File(getRealPath(filename));
/* 32 */     store(file, dest);
/* 33 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByExt(String path, String ext, File file) throws IOException
/*    */   {
/* 38 */     String filename = UploadUtils.generateFilename(path, ext);
/* 39 */     File dest = new File(getRealPath(filename));
/* 40 */     dest = UploadUtils.getUniqueFile(dest);
/* 41 */     store(file, dest);
/* 42 */     return filename;
/*    */   }
/*    */ 
/*    */   public String storeByFilename(String filename, File file) throws IOException
/*    */   {
/* 47 */     File dest = new File(getRealPath(filename));
/* 48 */     store(file, dest);
/* 49 */     return filename;
/*    */   }
/*    */ 
/*    */   private void store(MultipartFile file, File dest) throws IOException {
/*    */     try {
/* 54 */       UploadUtils.checkDirAndCreate(dest.getParentFile());
/* 55 */       file.transferTo(dest);
/*    */     } catch (IOException e) {
/* 57 */       this.log.error("Transfer file error when upload file", e);
/* 58 */       throw e;
/*    */     }
/*    */   }
/*    */ 
/*    */   private void store(File file, File dest) throws IOException {
/*    */     try {
/* 64 */       UploadUtils.checkDirAndCreate(dest.getParentFile());
/* 65 */       FileUtils.copyFile(file, dest);
/*    */     } catch (IOException e) {
/* 67 */       this.log.error("Transfer file error when upload file", e);
/* 68 */       throw e;
/*    */     }
/*    */   }
/*    */ 
/*    */   public File retrieve(String name) {
/* 73 */     return new File(this.ctx.getRealPath(name));
/*    */   }
/*    */ 
/*    */   private String getRealPath(String name) {
/* 77 */     String realpath = this.ctx.getRealPath(name);
/* 78 */     if (realpath == null) {
/* 79 */       realpath = this.ctx.getRealPath("/") + name;
/*    */     }
/* 81 */     return realpath;
/*    */   }
/*    */ 
/*    */   public void setServletContext(ServletContext servletContext)
/*    */   {
/* 87 */     this.ctx = servletContext;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.upload.FileRepository
 * JD-Core Version:    0.6.0
 */