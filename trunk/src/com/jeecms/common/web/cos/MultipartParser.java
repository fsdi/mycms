/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletInputStream;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class MultipartParser
/*     */ {
/*     */   private ServletInputStream in;
/*     */   private String boundary;
/*     */   private FilePart lastFilePart;
/*  79 */   private byte[] buf = new byte[8192];
/*     */ 
/*  82 */   private static String DEFAULT_ENCODING = "ISO-8859-1";
/*     */ 
/*  85 */   private String encoding = DEFAULT_ENCODING;
/*     */ 
/*     */   public MultipartParser(HttpServletRequest req, int maxSize)
/*     */     throws IOException
/*     */   {
/*  98 */     this(req, maxSize, true, true);
/*     */   }
/*     */ 
/*     */   public MultipartParser(HttpServletRequest req, int maxSize, boolean buffer, boolean limitLength)
/*     */     throws IOException
/*     */   {
/* 117 */     this(req, maxSize, buffer, limitLength, null);
/*     */   }
/*     */ 
/*     */   public MultipartParser(HttpServletRequest req, int maxSize, boolean buffer, boolean limitLength, String encoding)
/*     */     throws IOException
/*     */   {
/* 141 */     if (encoding != null) {
/* 142 */       setEncoding(encoding);
/*     */     }
/*     */ 
/* 147 */     String type = null;
/* 148 */     String type1 = req.getHeader("Content-Type");
/* 149 */     String type2 = req.getContentType();
/*     */ 
/* 151 */     if ((type1 == null) && (type2 != null)) {
/* 152 */       type = type2;
/*     */     }
/* 154 */     else if ((type2 == null) && (type1 != null)) {
/* 155 */       type = type1;
/*     */     }
/* 158 */     else if ((type1 != null) && (type2 != null)) {
/* 159 */       type = type1.length() > type2.length() ? type1 : type2;
/*     */     }
/*     */ 
/* 162 */     if ((type == null) || 
/* 163 */       (!type.toLowerCase().startsWith("multipart/form-data"))) {
/* 164 */       throw new IOException("Posted content type isn't multipart/form-data");
/*     */     }
/*     */ 
/* 168 */     int length = req.getContentLength();
/* 169 */     if (length > maxSize) {
/* 170 */       throw new IOException("Posted content length of " + length + 
/* 171 */         " exceeds limit of " + maxSize);
/*     */     }
/*     */ 
/* 176 */     String boundary = extractBoundary(type);
/* 177 */     if (boundary == null) {
/* 178 */       throw new IOException("Separation boundary was not specified");
/*     */     }
/*     */ 
/* 181 */     ServletInputStream in = req.getInputStream();
/*     */ 
/* 185 */     if (buffer) {
/* 186 */       in = new BufferedServletInputStream(in);
/*     */     }
/* 188 */     if ((limitLength) && (length > 0)) {
/* 189 */       in = new LimitedServletInputStream(in, length);
/*     */     }
/*     */ 
/* 193 */     this.in = in;
/* 194 */     this.boundary = boundary;
/*     */     String line;
/*     */     do
/*     */     {
/* 201 */       line = readLine();
/* 202 */       if (line == null) {
/* 203 */         throw new IOException("Corrupt form data: premature ending");
/*     */       }
/*     */     }
/* 206 */     while (!line.startsWith(boundary));
/*     */   }
/*     */ 
/*     */   public void setEncoding(String encoding)
/*     */   {
/* 220 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   public Part readNextPart()
/*     */     throws IOException
/*     */   {
/* 238 */     if (this.lastFilePart != null) {
/* 239 */       this.lastFilePart.getInputStream().close();
/* 240 */       this.lastFilePart = null;
/*     */     }
/*     */ 
/* 247 */     List<String> headers = new ArrayList();
/*     */ 
/* 249 */     String line = readLine();
/* 250 */     if (line == null)
/*     */     {
/* 252 */       return null;
/*     */     }
/* 254 */     if (line.length() == 0)
/*     */     {
/* 257 */       return null;
/*     */     }
/*     */ 
/*     */     do
/*     */     {
/* 265 */       String nextLine = null;
/* 266 */       boolean getNextLine = true;
/* 267 */       while (getNextLine) {
/* 268 */         nextLine = readLine();
/* 269 */         if ((nextLine != null) && (
/* 270 */           (nextLine.startsWith(" ")) || 
/* 271 */           (nextLine.startsWith("\t")))) {
/* 272 */           line = line + nextLine;
/*     */         }
/*     */         else {
/* 275 */           getNextLine = false;
/*     */         }
/*     */       }
/*     */ 
/* 279 */       headers.add(line);
/* 280 */       line = nextLine;
/*     */     }
/* 264 */     while ((line != null) && (line.length() > 0));
/*     */ 
/* 284 */     if (line == null) {
/* 285 */       return null;
/*     */     }
/*     */ 
/* 288 */     String name = null;
/* 289 */     String filename = null;
/* 290 */     String origname = null;
/* 291 */     String contentType = "text/plain";
/*     */ 
/* 293 */     for (String headerline : headers) {
/* 294 */       if (headerline.toLowerCase().startsWith("content-disposition:"))
/*     */       {
/* 296 */         String[] dispInfo = extractDispositionInfo(headerline);
/*     */ 
/* 298 */         name = dispInfo[1];
/* 299 */         filename = dispInfo[2];
/* 300 */         origname = dispInfo[3];
/*     */       } else {
/* 302 */         if (!headerline.toLowerCase().startsWith("content-type:"))
/*     */           continue;
/* 304 */         String type = extractContentType(headerline);
/* 305 */         if (type != null) {
/* 306 */           contentType = type;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 312 */     if (filename == null)
/*     */     {
/* 315 */       return new ParamPart(name, this.in, this.boundary, this.encoding);
/*     */     }
/*     */ 
/* 319 */     if (filename.equals("")) {
/* 320 */       filename = null;
/*     */     }
/* 322 */     this.lastFilePart = 
/* 323 */       new FilePart(name, this.in, this.boundary, 
/* 323 */       contentType, filename, origname);
/* 324 */     return this.lastFilePart;
/*     */   }
/*     */ 
/*     */   private String extractBoundary(String line)
/*     */   {
/* 336 */     int index = line.lastIndexOf("boundary=");
/* 337 */     if (index == -1) {
/* 338 */       return null;
/*     */     }
/* 340 */     String boundary = line.substring(index + 9);
/* 341 */     if (boundary.charAt(0) == '"')
/*     */     {
/* 343 */       index = boundary.lastIndexOf('"');
/* 344 */       boundary = boundary.substring(1, index);
/*     */     }
/*     */ 
/* 348 */     boundary = "--" + boundary;
/*     */ 
/* 350 */     return boundary;
/*     */   }
/*     */ 
/*     */   private String[] extractDispositionInfo(String line)
/*     */     throws IOException
/*     */   {
/* 362 */     String[] retval = new String[4];
/*     */ 
/* 366 */     String origline = line;
/* 367 */     line = origline.toLowerCase();
/*     */ 
/* 370 */     int start = line.indexOf("content-disposition: ");
/* 371 */     int end = line.indexOf(";");
/* 372 */     if ((start == -1) || (end == -1)) {
/* 373 */       throw new IOException("Content disposition corrupt: " + origline);
/*     */     }
/* 375 */     String disposition = line.substring(start + 21, end).trim();
/* 376 */     if (!disposition.equals("form-data")) {
/* 377 */       throw new IOException("Invalid content disposition: " + disposition);
/*     */     }
/*     */ 
/* 381 */     start = line.indexOf("name=\"", end);
/* 382 */     end = line.indexOf("\"", start + 7);
/* 383 */     int startOffset = 6;
/* 384 */     if ((start == -1) || (end == -1))
/*     */     {
/* 387 */       start = line.indexOf("name=", end);
/* 388 */       end = line.indexOf(";", start + 6);
/* 389 */       if (start == -1) {
/* 390 */         throw new IOException("Content disposition corrupt: " + origline);
/*     */       }
/* 392 */       if (end == -1) {
/* 393 */         end = line.length();
/*     */       }
/* 395 */       startOffset = 5;
/*     */     }
/* 397 */     String name = origline.substring(start + startOffset, end);
/*     */ 
/* 400 */     String filename = null;
/* 401 */     String origname = null;
/* 402 */     start = line.indexOf("filename=\"", end + 2);
/* 403 */     end = line.indexOf("\"", start + 10);
/* 404 */     if ((start != -1) && (end != -1)) {
/* 405 */       filename = origline.substring(start + 10, end);
/* 406 */       origname = filename;
/*     */ 
/* 408 */       int slash = 
/* 409 */         Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
/* 410 */       if (slash > -1) {
/* 411 */         filename = filename.substring(slash + 1);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 417 */     retval[0] = disposition;
/* 418 */     retval[1] = name;
/* 419 */     retval[2] = filename;
/* 420 */     retval[3] = origname;
/* 421 */     return retval;
/*     */   }
/*     */ 
/*     */   private static String extractContentType(String line)
/*     */     throws IOException
/*     */   {
/* 433 */     line = line.toLowerCase();
/*     */ 
/* 439 */     int end = line.indexOf(";");
/* 440 */     if (end == -1) {
/* 441 */       end = line.length();
/*     */     }
/*     */ 
/* 444 */     return line.substring(13, end).trim();
/*     */   }
/*     */ 
/*     */   private String readLine() throws IOException
/*     */   {
/* 455 */     StringBuffer sbuf = new StringBuffer();
/*     */     int result;
/*     */     do {
/* 459 */       result = this.in.readLine(this.buf, 0, this.buf.length);
/* 460 */       if (result != -1)
/* 461 */         sbuf.append(new String(this.buf, 0, result, this.encoding));
/*     */     }
/* 463 */     while (result == this.buf.length);
/*     */ 
/* 465 */     if (sbuf.length() == 0) {
/* 466 */       return null;
/*     */     }
/*     */ 
/* 472 */     int len = sbuf.length();
/* 473 */     if ((len >= 2) && (sbuf.charAt(len - 2) == '\r')) {
/* 474 */       sbuf.setLength(len - 2);
/*     */     }
/* 476 */     else if ((len >= 1) && (sbuf.charAt(len - 1) == '\n')) {
/* 477 */       sbuf.setLength(len - 1);
/*     */     }
/* 479 */     return sbuf.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.MultipartParser
 * JD-Core Version:    0.6.0
 */