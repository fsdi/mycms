/*     */ package com.jeecms.core.manager.impl;
/*     */ 
/*     */ import com.jeecms.common.upload.UploadUtils;
/*     */ import com.jeecms.core.dao.DbFileDao;
/*     */ import com.jeecms.core.entity.DbFile;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class DbFileMngImpl
/*     */   implements DbFileMng
/*     */ {
/*     */   private DbFileDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public DbFile findById(String id)
/*     */   {
/*  25 */     DbFile entity = this.dao.findById(id);
/*  26 */     return entity;
/*     */   }
/*     */ 
/*     */   public String storeByExt(String path, String ext, InputStream in) throws IOException {
/*     */     String filename;
/*     */     DbFile file;
/*     */     do {
/*  35 */       filename = UploadUtils.generateFilename(path, ext);
/*  36 */       file = findById(filename);
/*  37 */     }while (file != null);
/*  38 */     save(filename, in);
/*  39 */     return filename;
/*     */   }
/*     */ 
/*     */   public String storeByFilename(String filename, InputStream in) throws IOException
/*     */   {
/*  44 */     DbFile file = findById(filename);
/*  45 */     if (file != null)
/*  46 */       update(file, in);
/*     */     else {
/*  48 */       save(filename, in);
/*     */     }
/*  50 */     return filename;
/*     */   }
/*     */ 
/*     */   public File retrieve(String name) throws IOException
/*     */   {
/*  55 */     String path = System.getProperty("java.io.tmpdir");
/*  56 */     File file = new File(path, name);
/*  57 */     file = UploadUtils.getUniqueFile(file);
/*  58 */     DbFile df = findById(name);
/*  59 */     FileUtils.writeByteArrayToFile(file, df.getContent());
/*  60 */     return file;
/*     */   }
/*     */ 
/*     */   public boolean restore(String name, File file) throws FileNotFoundException, IOException
/*     */   {
/*  65 */     storeByFilename(name, new FileInputStream(file));
/*  66 */     file.deleteOnExit();
/*  67 */     return true;
/*     */   }
/*     */ 
/*     */   private DbFile update(DbFile file, InputStream in) throws IOException {
/*  71 */     byte[] content = IOUtils.toByteArray(in);
/*  72 */     file.setContent(content);
/*  73 */     file.setLastModified(Long.valueOf(System.currentTimeMillis()));
/*  74 */     file.setLength(Integer.valueOf(content.length));
/*  75 */     in.close();
/*  76 */     return file;
/*     */   }
/*     */ 
/*     */   private DbFile save(String filename, InputStream in) throws IOException {
/*  80 */     byte[] content = IOUtils.toByteArray(in);
/*  81 */     DbFile file = new DbFile(filename, Integer.valueOf(content.length), 
/*  82 */       Long.valueOf(System.currentTimeMillis()), content);
/*  83 */     this.dao.save(file);
/*  84 */     in.close();
/*  85 */     return file;
/*     */   }
/*     */ 
/*     */   public DbFile deleteById(String id) {
/*  89 */     DbFile bean = this.dao.deleteById(id);
/*  90 */     return bean;
/*     */   }
/*     */ 
/*     */   public DbFile[] deleteByIds(String[] ids) {
/*  94 */     DbFile[] beans = new DbFile[ids.length];
/*  95 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  96 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  98 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(DbFileDao dao)
/*     */   {
/* 105 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.impl.DbFileMngImpl
 * JD-Core Version:    0.6.0
 */