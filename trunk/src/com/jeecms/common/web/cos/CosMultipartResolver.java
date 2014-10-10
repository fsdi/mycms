/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletContext;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.io.Resource;
/*     */ import org.springframework.web.context.ServletContextAware;
/*     */ import org.springframework.web.multipart.MaxUploadSizeExceededException;
/*     */ import org.springframework.web.multipart.MultipartException;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ import org.springframework.web.multipart.MultipartResolver;
/*     */ import org.springframework.web.util.WebUtils;
/*     */ 
/*     */ public class CosMultipartResolver
/*     */   implements MultipartResolver, ServletContextAware
/*     */ {
/*     */   public static final String MULTIPART_CONTENT_TYPE = "multipart/form-data";
/*  45 */   protected final Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*  47 */   private int maxUploadSize = 2147483647;
/*     */ 
/*  49 */   private String defaultEncoding = "ISO-8859-1";
/*     */   private File uploadTempDir;
/*     */ 
/*     */   public CosMultipartResolver()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CosMultipartResolver(ServletContext servletContext)
/*     */   {
/*  75 */     this.uploadTempDir = WebUtils.getTempDir(servletContext);
/*     */   }
/*     */ 
/*     */   public void setMaxUploadSize(int maxUploadSize)
/*     */   {
/*  86 */     this.maxUploadSize = maxUploadSize;
/*     */   }
/*     */ 
/*     */   protected int getMaxUploadSize()
/*     */   {
/*  93 */     return this.maxUploadSize;
/*     */   }
/*     */ 
/*     */   public void setDefaultEncoding(String defaultEncoding)
/*     */   {
/* 114 */     this.defaultEncoding = defaultEncoding;
/*     */   }
/*     */ 
/*     */   protected String getDefaultEncoding()
/*     */   {
/* 121 */     return this.defaultEncoding;
/*     */   }
/*     */ 
/*     */   public void setUploadTempDir(Resource uploadTempDir)
/*     */     throws IOException
/*     */   {
/* 131 */     if ((!uploadTempDir.exists()) && (!uploadTempDir.getFile().mkdirs())) {
/* 132 */       throw new IllegalArgumentException("Given uploadTempDir [" + 
/* 133 */         uploadTempDir + "] could not be created");
/*     */     }
/* 135 */     this.uploadTempDir = uploadTempDir.getFile();
/*     */   }
/*     */ 
/*     */   protected File getUploadTempDir()
/*     */   {
/* 142 */     return this.uploadTempDir;
/*     */   }
/*     */ 
/*     */   public void setServletContext(ServletContext servletContext) {
/* 146 */     if (this.uploadTempDir == null)
/* 147 */       this.uploadTempDir = WebUtils.getTempDir(servletContext);
/*     */   }
/*     */ 
/*     */   public boolean isMultipart(HttpServletRequest request)
/*     */   {
/* 153 */     return (request.getContentType() != null) && 
/* 153 */       (request.getContentType().startsWith("multipart/form-data"));
/*     */   }
/*     */ 
/*     */   public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException
/*     */   {
/*     */     try {
/* 159 */       CosMultipartRequest multipartRequest = newMultipartRequest(request);
/* 160 */       if (this.logger.isDebugEnabled()) {
/* 161 */         Set<String> fileNames = multipartRequest.getFileNames();
/* 162 */         for (String fileName : fileNames) {
/* 163 */           File file = multipartRequest.getFile(fileName);
/* 164 */           this.logger.debug("Found multipart file '" + 
/* 165 */             fileName + 
/* 166 */             "' of size " + (
/* 167 */             file != null ? file.length() : 0L) + 
/* 168 */             " bytes with original filename [" + 
/* 169 */             multipartRequest.getOriginalFileName(fileName) + 
/* 170 */             "]" + (
/* 171 */             file != null ? "stored at [" + 
/* 172 */             file.getAbsolutePath() + "]" : "empty"));
/*     */         }
/*     */       }
/* 175 */       return new CosMultipartHttpServletRequest(request, multipartRequest);
/*     */     }
/*     */     catch (IOException ex)
/*     */     {
/* 179 */       if (ex.getMessage().indexOf("exceeds limit") != -1)
/* 180 */         throw new MaxUploadSizeExceededException(this.maxUploadSize, ex);
/*     */     }
/* 182 */     throw new MultipartException(
/* 183 */       "Could not parse multipart request");
/*     */   }
/*     */ 
/*     */   protected CosMultipartRequest newMultipartRequest(HttpServletRequest request)
/*     */     throws IOException
/*     */   {
/* 200 */     String tempPath = this.uploadTempDir.getAbsolutePath();
/* 201 */     String enc = determineEncoding(request);
/* 202 */     return new CosMultipartRequest(request, tempPath, this.maxUploadSize, 
/* 203 */       enc);
/*     */   }
/*     */ 
/*     */   protected String determineEncoding(HttpServletRequest request)
/*     */   {
/* 220 */     String enc = request.getCharacterEncoding();
/* 221 */     if (enc == null) {
/* 222 */       enc = this.defaultEncoding;
/*     */     }
/* 224 */     return enc;
/*     */   }
/*     */ 
/*     */   public void cleanupMultipart(MultipartHttpServletRequest request) {
/* 228 */     CosMultipartRequest multipartRequest = ((CosMultipartHttpServletRequest)request)
/* 229 */       .getMultipartRequest();
/* 230 */     Set<String> fileNames = multipartRequest.getFileNames();
/* 231 */     for (String fileName : fileNames) {
/* 232 */       File file = multipartRequest.getFile(fileName);
/* 233 */       if (file != null)
/* 234 */         if (file.exists()) {
/* 235 */           if (file.delete()) {
/* 236 */             if (this.logger.isDebugEnabled())
/* 237 */               this.logger.debug("Cleaned up multipart file '" + 
/* 238 */                 fileName + 
/* 239 */                 "' with original filename [" + 
/* 240 */                 multipartRequest
/* 241 */                 .getOriginalFileName(fileName) + 
/* 242 */                 "], stored at [" + file.getAbsolutePath() + 
/* 243 */                 "]");
/*     */           }
/*     */           else
/* 246 */             this.logger.warn("Could not delete multipart file '" + 
/* 247 */               fileName + 
/* 248 */               "' with original filename [" + 
/* 249 */               multipartRequest
/* 250 */               .getOriginalFileName(fileName) + 
/* 251 */               "], stored at [" + file.getAbsolutePath() + 
/* 252 */               "]");
/*     */         }
/*     */         else {
/* 255 */           if (!this.logger.isDebugEnabled()) continue;
/* 256 */           this.logger
/* 257 */             .debug("Multipart file '" + 
/* 258 */             fileName + 
/* 259 */             "' with original filename [" + 
/* 260 */             multipartRequest
/* 261 */             .getOriginalFileName(fileName) + 
/* 262 */             "] has already been moved - no cleanup necessary");
/*     */         }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.CosMultipartResolver
 * JD-Core Version:    0.6.0
 */