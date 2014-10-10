/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletInputStream;
/*     */ 
/*     */ public class LimitedServletInputStream extends ServletInputStream
/*     */ {
/*     */   private ServletInputStream in;
/*     */   private int totalExpected;
/*  27 */   private int totalRead = 0;
/*     */ 
/*     */   public LimitedServletInputStream(ServletInputStream in, int totalExpected)
/*     */   {
/*  34 */     this.in = in;
/*  35 */     this.totalExpected = totalExpected;
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/*  52 */     int left = this.totalExpected - this.totalRead;
/*  53 */     if (left <= 0) {
/*  54 */       return -1;
/*     */     }
/*  56 */     int result = this.in.readLine(b, off, Math.min(left, len));
/*     */ 
/*  58 */     if (result > 0) {
/*  59 */       this.totalRead += result;
/*     */     }
/*  61 */     return result;
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/*  73 */     if (this.totalRead >= this.totalExpected) {
/*  74 */       return -1;
/*     */     }
/*     */ 
/*  77 */     int result = this.in.read();
/*  78 */     if (result != -1) {
/*  79 */       this.totalRead += 1;
/*     */     }
/*  81 */     return result;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/*  96 */     int left = this.totalExpected - this.totalRead;
/*  97 */     if (left <= 0) {
/*  98 */       return -1;
/*     */     }
/* 100 */     int result = this.in.read(b, off, Math.min(left, len));
/*     */ 
/* 102 */     if (result > 0) {
/* 103 */       this.totalRead += result;
/*     */     }
/* 105 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.LimitedServletInputStream
 * JD-Core Version:    0.6.0
 */