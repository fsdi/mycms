/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Hashtable;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class CosMultipartRequest
/*     */ {
/*     */   private static final int DEFAULT_MAX_POST_SIZE = 1048576;
/*  66 */   protected Hashtable<String, List<String>> parameters = new Hashtable();
/*  67 */   protected Map<String, UploadedFile> files = new HashMap();
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory)
/*     */     throws IOException
/*     */   {
/*  84 */     this(request, saveDirectory, 1048576);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize)
/*     */     throws IOException
/*     */   {
/* 104 */     this(request, saveDirectory, maxPostSize, null, null);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory, String encoding)
/*     */     throws IOException
/*     */   {
/* 124 */     this(request, saveDirectory, 1048576, encoding, null);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, FileRenamePolicy policy)
/*     */     throws IOException
/*     */   {
/* 146 */     this(request, saveDirectory, maxPostSize, null, policy);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding)
/*     */     throws IOException
/*     */   {
/* 168 */     this(request, saveDirectory, maxPostSize, encoding, null);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(HttpServletRequest request, String saveDirectory, int maxPostSize, String encoding, FileRenamePolicy policy)
/*     */     throws IOException
/*     */   {
/* 196 */     if (request == null)
/* 197 */       throw new IllegalArgumentException("request cannot be null");
/* 198 */     if (saveDirectory == null)
/* 199 */       throw new IllegalArgumentException("saveDirectory cannot be null");
/* 200 */     if (maxPostSize <= 0) {
/* 201 */       throw new IllegalArgumentException("maxPostSize must be positive");
/*     */     }
/*     */ 
/* 205 */     File dir = new File(saveDirectory);
/*     */ 
/* 208 */     if (!dir.isDirectory()) {
/* 209 */       throw new IllegalArgumentException("Not a directory: " + saveDirectory);
/*     */     }
/*     */ 
/* 212 */     if (!dir.canWrite()) {
/* 213 */       throw new IllegalArgumentException("Not writable: " + saveDirectory);
/*     */     }
/*     */ 
/* 217 */     MultipartParser parser = 
/* 218 */       new MultipartParser(request, maxPostSize, true, true, encoding);
/*     */ 
/* 223 */     if (request.getQueryString() != null)
/*     */     {
/* 225 */       Map<String, String[]> queryParameters = RequestUtils.parseQueryString(request.getQueryString());
/*     */ 
/* 228 */       for (Map.Entry entry : queryParameters.entrySet())
/* 229 */         this.parameters.put((String)entry.getKey(), Arrays.asList((String[])entry.getValue()));
/*     */     }
/*     */     Part part;
/* 234 */     while ((part = parser.readNextPart()) != null)
/*     */     {
/* 235 */       String name = part.getName();
/* 236 */       if (name == null) {
/* 237 */         throw new IOException(
/* 238 */           "Malformed input: parameter name missing (known Opera 7 bug)");
/*     */       }
/* 240 */       if (part.isParam())
/*     */       {
/* 242 */         ParamPart paramPart = (ParamPart)part;
/* 243 */         String value = paramPart.getStringValue();
/* 244 */         List existingValues = (List)this.parameters.get(name);
/* 245 */         if (existingValues == null) {
/* 246 */           existingValues = new ArrayList();
/* 247 */           this.parameters.put(name, existingValues);
/*     */         }
/* 249 */         existingValues.add(value);
/*     */       } else {
/* 251 */         if (!part.isFile())
/*     */           continue;
/* 253 */         FilePart filePart = (FilePart)part;
/* 254 */         String fileName = filePart.getFileName();
/* 255 */         if (fileName != null) {
/* 256 */           filePart.setRenamePolicy(policy);
/*     */ 
/* 258 */           filePart.writeTo(dir);
/* 259 */           this.files.put(name, 
/* 262 */             new UploadedFile(dir.toString(), 
/* 260 */             filePart.getFileName(), 
/* 261 */             fileName, 
/* 262 */             filePart.getContentType()));
/*     */         }
/*     */         else
/*     */         {
/* 266 */           this.files.put(name, new UploadedFile(null, null, null, null));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(ServletRequest request, String saveDirectory)
/*     */     throws IOException
/*     */   {
/* 282 */     this((HttpServletRequest)request, saveDirectory);
/*     */   }
/*     */ 
/*     */   public CosMultipartRequest(ServletRequest request, String saveDirectory, int maxPostSize)
/*     */     throws IOException
/*     */   {
/* 296 */     this((HttpServletRequest)request, saveDirectory, maxPostSize);
/*     */   }
/*     */ 
/*     */   public Enumeration<String> getParameterNames()
/*     */   {
/* 306 */     return this.parameters.keys();
/*     */   }
/*     */ 
/*     */   public Set<String> getFileNames()
/*     */   {
/* 319 */     return this.files.keySet();
/*     */   }
/*     */ 
/*     */   public String getParameter(String name)
/*     */   {
/*     */     try
/*     */     {
/* 335 */       List values = (List)this.parameters.get(name);
/* 336 */       if ((values == null) || (values.size() == 0)) {
/* 337 */         return null;
/*     */       }
/* 339 */       return (String)values.get(values.size() - 1);
/*     */     } catch (Exception e) {
/*     */     }
/* 342 */     return null;
/*     */   }
/*     */ 
/*     */   public String[] getParameterValues(String name)
/*     */   {
/* 357 */     List values = (List)this.parameters.get(name);
/* 358 */     if ((values == null) || (values.size() == 0)) {
/* 359 */       return null;
/*     */     }
/* 361 */     return (String[])values.toArray(new String[values.size()]);
/*     */   }
/*     */ 
/*     */   public String getFilesystemName(String name)
/*     */   {
/*     */     try
/*     */     {
/* 376 */       UploadedFile file = (UploadedFile)this.files.get(name);
/* 377 */       return file.getFilesystemName();
/*     */     } catch (Exception e) {
/*     */     }
/* 380 */     return null;
/*     */   }
/*     */ 
/*     */   public String getOriginalFileName(String name)
/*     */   {
/*     */     try
/*     */     {
/* 394 */       UploadedFile file = (UploadedFile)this.files.get(name);
/* 395 */       return file.getOriginalFileName();
/*     */     } catch (Exception e) {
/*     */     }
/* 398 */     return null;
/*     */   }
/*     */ 
/*     */   public String getContentType(String name)
/*     */   {
/*     */     try
/*     */     {
/* 411 */       UploadedFile file = (UploadedFile)this.files.get(name);
/* 412 */       return file.getContentType();
/*     */     } catch (Exception e) {
/*     */     }
/* 415 */     return null;
/*     */   }
/*     */ 
/*     */   public File getFile(String name)
/*     */   {
/*     */     try
/*     */     {
/* 428 */       UploadedFile file = (UploadedFile)this.files.get(name);
/* 429 */       return file.getFile();
/*     */     } catch (Exception e) {
/*     */     }
/* 432 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.CosMultipartRequest
 * JD-Core Version:    0.6.0
 */