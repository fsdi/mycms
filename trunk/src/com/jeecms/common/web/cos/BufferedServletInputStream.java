/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.ServletInputStream;
/*     */ 
/*     */ public class BufferedServletInputStream extends ServletInputStream
/*     */ {
/*     */   private ServletInputStream in;
/*  34 */   private byte[] buf = new byte[65536];
/*     */   private int count;
/*     */   private int pos;
/*     */ 
/*     */   public BufferedServletInputStream(ServletInputStream in)
/*     */   {
/*  49 */     this.in = in;
/*     */   }
/*     */ 
/*     */   private void fill()
/*     */     throws IOException
/*     */   {
/*  60 */     int i = this.in.read(this.buf, 0, this.buf.length);
/*  61 */     if (i > 0) {
/*  62 */       this.pos = 0;
/*  63 */       this.count = i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int readLine(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/*  81 */     int total = 0;
/*  82 */     if (len == 0) {
/*  83 */       return 0;
/*     */     }
/*     */ 
/*  86 */     int avail = this.count - this.pos;
/*  87 */     if (avail <= 0) {
/*  88 */       fill();
/*  89 */       avail = this.count - this.pos;
/*  90 */       if (avail <= 0) {
/*  91 */         return -1;
/*     */       }
/*     */     }
/*  94 */     int copy = Math.min(len, avail);
/*  95 */     int eol = findeol(this.buf, this.pos, copy);
/*  96 */     if (eol != -1) {
/*  97 */       copy = eol;
/*     */     }
/*  99 */     System.arraycopy(this.buf, this.pos, b, off, copy);
/* 100 */     this.pos += copy;
/* 101 */     total += copy;
/*     */ 
/* 103 */     while ((total < len) && (eol == -1)) {
/* 104 */       fill();
/* 105 */       avail = this.count - this.pos;
/* 106 */       if (avail <= 0) {
/* 107 */         return total;
/*     */       }
/* 109 */       copy = Math.min(len - total, avail);
/* 110 */       eol = findeol(this.buf, this.pos, copy);
/* 111 */       if (eol != -1) {
/* 112 */         copy = eol;
/*     */       }
/* 114 */       System.arraycopy(this.buf, this.pos, b, off + total, copy);
/* 115 */       this.pos += copy;
/* 116 */       total += copy;
/*     */     }
/* 118 */     return total;
/*     */   }
/*     */ 
/*     */   private static int findeol(byte[] b, int pos, int len)
/*     */   {
/* 132 */     int end = pos + len;
/* 133 */     int i = pos;
/* 134 */     while (i < end) {
/* 135 */       if (b[(i++)] == 10) {
/* 136 */         return i - pos;
/*     */       }
/*     */     }
/* 139 */     return -1;
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 151 */     if (this.count <= this.pos) {
/* 152 */       fill();
/* 153 */       if (this.count <= this.pos) {
/* 154 */         return -1;
/*     */       }
/*     */     }
/* 157 */     return this.buf[(this.pos++)] & 0xFF;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 174 */     int total = 0;
/* 175 */     while (total < len) {
/* 176 */       int avail = this.count - this.pos;
/* 177 */       if (avail <= 0) {
/* 178 */         fill();
/* 179 */         avail = this.count - this.pos;
/* 180 */         if (avail <= 0) {
/* 181 */           if (total > 0) {
/* 182 */             return total;
/*     */           }
/* 184 */           return -1;
/*     */         }
/*     */       }
/* 187 */       int copy = Math.min(len - total, avail);
/* 188 */       System.arraycopy(this.buf, this.pos, b, off + total, copy);
/* 189 */       this.pos += copy;
/* 190 */       total += copy;
/*     */     }
/* 192 */     return total;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.BufferedServletInputStream
 * JD-Core Version:    0.6.0
 */