/*     */ package com.jeecms.common.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.tools.zip.ZipEntry;
/*     */ import org.apache.tools.zip.ZipOutputStream;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public class Zipper
/*     */ {
/*  22 */   private static final Logger log = LoggerFactory.getLogger(Zipper.class);
/*     */ 
/* 129 */   private byte[] buf = new byte[1024];
/*     */   private ZipOutputStream zipOut;
/*     */ 
/*     */   public static void zip(OutputStream out, List<FileEntry> fileEntrys, String encoding)
/*     */   {
/*  30 */     new Zipper(out, fileEntrys, encoding);
/*     */   }
/*     */ 
/*     */   public static void zip(OutputStream out, List<FileEntry> fileEntrys)
/*     */   {
/*  38 */     new Zipper(out, fileEntrys, null);
/*     */   }
/*     */ 
/*     */   protected Zipper(OutputStream out, List<FileEntry> fileEntrys, String encoding)
/*     */   {
/*  53 */     Assert.notEmpty(fileEntrys);
/*  54 */     long begin = System.currentTimeMillis();
/*  55 */     log.debug("开始制作压缩包");
/*     */     try {
/*     */       try {
/*  58 */         this.zipOut = new ZipOutputStream(out);
/*  59 */         if (!StringUtils.isBlank(encoding)) {
/*  60 */           log.debug("using encoding: {}", encoding);
/*  61 */           this.zipOut.setEncoding(encoding);
/*     */         } else {
/*  63 */           log.debug("using default encoding");
/*     */         }
/*  65 */         for (FileEntry fe : fileEntrys)
/*  66 */           zip(fe.getFile(), fe.getFilter(), fe.getZipEntry(), 
/*  67 */             fe.getPrefix());
/*     */       }
/*     */       finally {
/*  70 */         this.zipOut.close();
/*     */       }
/*     */     } catch (IOException e) {
/*  73 */       throw new RuntimeException("制作压缩包时，出现IO异常！", e);
/*     */     }
/*  75 */     long end = System.currentTimeMillis();
/*  76 */     log.info("制作压缩包成功。耗时：{}ms。", Long.valueOf(end - begin));
/*     */   }
/*     */ 
/*     */   private void zip(File srcFile, FilenameFilter filter, ZipEntry pentry, String prefix)
/*     */     throws IOException
/*     */   {
/*  91 */     if (srcFile.isDirectory())
/*     */     {
/*     */       ZipEntry entry;
/*  92 */       if (pentry == null)
/*  93 */         entry = new ZipEntry(srcFile.getName());
/*     */       else {
/*  95 */         entry = new ZipEntry(pentry.getName() + "/" + srcFile.getName());
/*     */       }
/*  97 */       File[] files = srcFile.listFiles(filter);
/*  98 */       for (File f : files)
/*  99 */         zip(f, filter, entry, prefix);
/*     */     }
/*     */     else
/*     */     {
/*     */       ZipEntry entry;
/* 102 */       if (pentry == null)
/* 103 */         entry = new ZipEntry(prefix + srcFile.getName());
/*     */       else {
/* 105 */         entry = new ZipEntry(pentry.getName() + "/" + prefix + 
/* 106 */           srcFile.getName());
/*     */       }
/*     */       try
/*     */       {
/* 110 */         log.debug("读取文件：{}", srcFile.getAbsolutePath());
/* 111 */         FileInputStream in = new FileInputStream(srcFile);
/*     */         try {
/* 113 */           this.zipOut.putNextEntry(entry);
/*     */           int len;
/* 115 */           while ((len = in.read(this.buf)) > 0)
/*     */           {
/* 116 */             this.zipOut.write(this.buf, 0, len);
/*     */           }
/* 118 */           this.zipOut.closeEntry();
/*     */         } finally {
/* 120 */           in.close();
/*     */         }
/*     */       } catch (FileNotFoundException e) {
/* 123 */         throw new RuntimeException("制作压缩包时，源文件不存在：" + 
/* 124 */           srcFile.getAbsolutePath(), e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class FileEntry {
/*     */     private FilenameFilter filter;
/*     */     private String parent;
/*     */     private File file;
/*     */     private String prefix;
/*     */ 
/*     */     public FileEntry(String parent, String prefix, File file, FilenameFilter filter) {
/* 140 */       this.parent = parent;
/* 141 */       this.prefix = prefix;
/* 142 */       this.file = file;
/* 143 */       this.filter = filter;
/*     */     }
/*     */ 
/*     */     public FileEntry(String parent, File file) {
/* 147 */       this.parent = parent;
/* 148 */       this.file = file;
/*     */     }
/*     */ 
/*     */     public FileEntry(String parent, String prefix, File file) {
/* 152 */       this(parent, prefix, file, null);
/*     */     }
/*     */ 
/*     */     public ZipEntry getZipEntry() {
/* 156 */       if (StringUtils.isBlank(this.parent)) {
/* 157 */         return null;
/*     */       }
/* 159 */       return new ZipEntry(this.parent);
/*     */     }
/*     */ 
/*     */     public FilenameFilter getFilter()
/*     */     {
/* 164 */       return this.filter;
/*     */     }
/*     */ 
/*     */     public void setFilter(FilenameFilter filter) {
/* 168 */       this.filter = filter;
/*     */     }
/*     */ 
/*     */     public String getParent() {
/* 172 */       return this.parent;
/*     */     }
/*     */ 
/*     */     public void setParent(String parent) {
/* 176 */       this.parent = parent;
/*     */     }
/*     */ 
/*     */     public File getFile() {
/* 180 */       return this.file;
/*     */     }
/*     */ 
/*     */     public void setFile(File file) {
/* 184 */       this.file = file;
/*     */     }
/*     */ 
/*     */     public String getPrefix() {
/* 188 */       if (this.prefix == null) {
/* 189 */         return "";
/*     */       }
/* 191 */       return this.prefix;
/*     */     }
/*     */ 
/*     */     public void setPrefix(String prefix)
/*     */     {
/* 196 */       this.prefix = prefix;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.Zipper
 * JD-Core Version:    0.6.0
 */