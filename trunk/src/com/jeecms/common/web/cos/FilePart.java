/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import javax.servlet.ServletInputStream;
/*     */ 
/*     */ public class FilePart extends Part
/*     */ {
/*     */   private String fileName;
/*     */   private String filePath;
/*     */   private String contentType;
/*     */   private PartInputStream partInput;
/*     */   private FileRenamePolicy policy;
/*     */ 
/*     */   FilePart(String name, ServletInputStream in, String boundary, String contentType, String fileName, String filePath)
/*     */     throws IOException
/*     */   {
/*  59 */     super(name);
/*  60 */     this.fileName = fileName;
/*  61 */     this.filePath = filePath;
/*  62 */     this.contentType = contentType;
/*  63 */     this.partInput = new PartInputStream(in, boundary);
/*     */   }
/*     */ 
/*     */   public void setRenamePolicy(FileRenamePolicy policy)
/*     */   {
/*  70 */     this.policy = policy;
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/*  88 */     return this.fileName;
/*     */   }
/*     */ 
/*     */   public String getFilePath()
/*     */   {
/* 102 */     return this.filePath;
/*     */   }
/*     */ 
/*     */   public String getContentType()
/*     */   {
/* 111 */     return this.contentType;
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream()
/*     */   {
/* 126 */     return this.partInput;
/*     */   }
/*     */ 
/*     */   public long writeTo(File fileOrDirectory)
/*     */     throws IOException
/*     */   {
/* 140 */     long written = 0L;
/*     */ 
/* 142 */     OutputStream fileOut = null;
/*     */     try
/*     */     {
/* 145 */       if (this.fileName != null)
/*     */       {
/*     */         File file;
/* 148 */         if (fileOrDirectory.isDirectory())
/*     */         {
/* 151 */           file = new File(fileOrDirectory, this.fileName);
/*     */         }
/*     */         else
/*     */         {
/* 156 */           file = fileOrDirectory;
/*     */         }
/* 158 */         if (this.policy != null) {
/* 159 */           file = this.policy.rename(file);
/* 160 */           this.fileName = file.getName();
/*     */         }
/* 162 */         fileOut = new BufferedOutputStream(new FileOutputStream(file));
/* 163 */         written = write(fileOut);
/*     */       }
/*     */     }
/*     */     finally {
/* 167 */       if (fileOut != null) fileOut.close();
/*     */     }
/* 169 */     return written;
/*     */   }
/*     */ 
/*     */   public long writeTo(OutputStream out)
/*     */     throws IOException
/*     */   {
/* 180 */     long size = 0L;
/*     */ 
/* 182 */     if (this.fileName != null)
/*     */     {
/* 184 */       size = write(out);
/*     */     }
/* 186 */     return size;
/*     */   }
/*     */ 
/*     */   long write(OutputStream out)
/*     */     throws IOException
/*     */   {
/* 198 */     if (this.contentType.equals("application/x-macbinary")) {
/* 199 */       out = new MacBinaryDecoderOutputStream(out);
/*     */     }
/* 201 */     long size = 0L;
/*     */ 
/* 203 */     byte[] buf = new byte[8192];
/*     */     int read;
/* 204 */     while ((read = this.partInput.read(buf)) != -1)
/*     */     {
/* 205 */       out.write(buf, 0, read);
/* 206 */       size += read;
/*     */     }
/* 208 */     return size;
/*     */   }
/*     */ 
/*     */   public boolean isFile()
/*     */   {
/* 217 */     return true;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.FilePart
 * JD-Core Version:    0.6.0
 */