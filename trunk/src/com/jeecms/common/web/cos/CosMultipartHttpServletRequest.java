/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.util.FileCopyUtils;
/*     */ import org.springframework.util.LinkedMultiValueMap;
/*     */ import org.springframework.util.MultiValueMap;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.support.AbstractMultipartHttpServletRequest;
/*     */ 
/*     */ public class CosMultipartHttpServletRequest extends AbstractMultipartHttpServletRequest
/*     */ {
/*  42 */   protected static final Logger logger = LoggerFactory.getLogger(CosMultipartHttpServletRequest.class);
/*     */   private final CosMultipartRequest multipartRequest;
/*     */ 
/*     */   protected CosMultipartHttpServletRequest(HttpServletRequest request, CosMultipartRequest multipartRequest)
/*     */   {
/*  56 */     super(request);
/*  57 */     this.multipartRequest = multipartRequest;
/*  58 */     setMultipartFiles(initFileMap(multipartRequest));
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest getMultipartRequest()
/*     */   {
/*  66 */     return this.multipartRequest;
/*     */   }
/*     */ 
/*     */   private MultiValueMap<String, MultipartFile> initFileMap(CosMultipartRequest multipartRequest)
/*     */   {
/*  78 */     MultiValueMap files = new LinkedMultiValueMap();
/*  79 */     Set<String> fileNames = multipartRequest.getFileNames();
/*  80 */     for (String fileName : fileNames) {
/*  81 */       files.add(fileName, new CosMultipartFile(fileName));
/*     */     }
/*  83 */     return files;
/*     */   }
/*     */ 
/*     */   public Enumeration<String> getParameterNames() {
/*  87 */     return this.multipartRequest.getParameterNames();
/*     */   }
/*     */ 
/*     */   public String getParameter(String name) {
/*  91 */     return this.multipartRequest.getParameter(name);
/*     */   }
/*     */ 
/*     */   public String[] getParameterValues(String name) {
/*  95 */     return this.multipartRequest.getParameterValues(name);
/*     */   }
/*     */ 
/*     */   public Map<String, String[]> getParameterMap() {
/*  99 */     Map params = new HashMap();
/* 100 */     Enumeration names = getParameterNames();
/* 101 */     while (names.hasMoreElements()) {
/* 102 */       String name = (String)names.nextElement();
/* 103 */       params.put(name, getParameterValues(name));
/*     */     }
/* 105 */     return Collections.unmodifiableMap(params);
/*     */   }
/*     */ 
/*     */   private class CosMultipartFile
/*     */     implements MultipartFile
/*     */   {
/*     */     private final String name;
/*     */     private final File file;
/*     */     private final long size;
/*     */ 
/*     */     public CosMultipartFile(String name)
/*     */     {
/* 121 */       this.name = name;
/* 122 */       this.file = CosMultipartHttpServletRequest.this.multipartRequest.getFile(this.name);
/* 123 */       this.size = (this.file != null ? this.file.length() : 0L);
/*     */     }
/*     */ 
/*     */     public String getName() {
/* 127 */       return this.name;
/*     */     }
/*     */ 
/*     */     public String getOriginalFilename() {
/* 131 */       String filename = CosMultipartHttpServletRequest.this.multipartRequest.getOriginalFileName(this.name);
/* 132 */       return filename != null ? filename : "";
/*     */     }
/*     */ 
/*     */     public String getContentType() {
/* 136 */       return CosMultipartHttpServletRequest.this.multipartRequest.getContentType(this.name);
/*     */     }
/*     */ 
/*     */     public boolean isEmpty() {
/* 140 */       return this.size == 0L;
/*     */     }
/*     */ 
/*     */     public long getSize() {
/* 144 */       return this.size;
/*     */     }
/*     */ 
/*     */     public byte[] getBytes() throws IOException {
/* 148 */       if ((this.file != null) && (!this.file.exists())) {
/* 149 */         throw new IllegalStateException(
/* 150 */           "File has been moved - cannot be read again");
/*     */       }
/* 152 */       return this.size > 0L ? FileCopyUtils.copyToByteArray(this.file) : 
/* 153 */         new byte[0];
/*     */     }
/*     */ 
/*     */     public InputStream getInputStream() throws IOException {
/* 157 */       if ((this.file != null) && (!this.file.exists())) {
/* 158 */         throw new IllegalStateException(
/* 159 */           "File has been moved - cannot be read again");
/*     */       }
/* 161 */       if (this.size != 0L) {
/* 162 */         return new FileInputStream(this.file);
/*     */       }
/* 164 */       return new ByteArrayInputStream(new byte[0]);
/*     */     }
/*     */ 
/*     */     public void transferTo(File dest)
/*     */       throws IOException, IllegalStateException
/*     */     {
/* 170 */       if ((this.file != null) && (!this.file.exists())) {
/* 171 */         throw new IllegalStateException(
/* 172 */           "File has already been moved - cannot be transferred again");
/*     */       }
/*     */ 
/* 175 */       if ((dest.exists()) && (!dest.delete())) {
/* 176 */         throw new IOException("Destination file [" + 
/* 177 */           dest.getAbsolutePath() + 
/* 178 */           "] already exists and could not be deleted");
/*     */       }
/*     */ 
/* 181 */       boolean moved = false;
/* 182 */       if (this.file != null) {
/* 183 */         moved = this.file.renameTo(dest);
/* 184 */         if (!moved)
/* 185 */           FileCopyUtils.copy(this.file, dest);
/*     */       }
/*     */       else {
/* 188 */         dest.createNewFile();
/*     */       }
/*     */ 
/* 191 */       if (CosMultipartHttpServletRequest.logger.isDebugEnabled())
/* 192 */         CosMultipartHttpServletRequest.logger.debug("Multipart file '" + 
/* 193 */           getName() + 
/* 194 */           "' with original filename [" + 
/* 195 */           getOriginalFilename() + 
/* 196 */           "], stored " + (
/* 197 */           this.file != null ? "at [" + 
/* 198 */           this.file.getAbsolutePath() + "]" : 
/* 199 */           "in memory") + ": " + (
/* 200 */           moved ? "moved" : "copied") + " to [" + 
/* 201 */           dest.getAbsolutePath() + "]");
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.CosMultipartHttpServletRequest
 * JD-Core Version:    0.6.0
 */