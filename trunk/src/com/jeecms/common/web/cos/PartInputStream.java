/*     */ package com.jeecms.common.web.cos;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.servlet.ServletInputStream;
/*     */ 
/*     */ public class PartInputStream extends FilterInputStream
/*     */ {
/*     */   private String boundary;
/*  32 */   private byte[] buf = new byte[65536];
/*     */   private int count;
/*     */   private int pos;
/*     */   private boolean eof;
/*     */ 
/*     */   PartInputStream(ServletInputStream in, String boundary)
/*     */     throws IOException
/*     */   {
/*  52 */     super(in);
/*  53 */     this.boundary = boundary;
/*     */   }
/*     */ 
/*     */   private void fill()
/*     */     throws IOException
/*     */   {
/*  67 */     if (this.eof) {
/*  68 */       return;
/*     */     }
/*     */ 
/*  71 */     if (this.count > 0)
/*     */     {
/*  74 */       if (this.count - this.pos == 2)
/*     */       {
/*  76 */         System.arraycopy(this.buf, this.pos, this.buf, 0, this.count - this.pos);
/*  77 */         this.count -= this.pos;
/*  78 */         this.pos = 0;
/*     */       }
/*     */       else {
/*  81 */         throw new IllegalStateException("fill() detected illegal buffer state");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  88 */     int read = 0;
/*  89 */     int boundaryLength = this.boundary.length();
/*  90 */     int maxRead = this.buf.length - boundaryLength - 2;
/*  91 */     while (this.count < maxRead)
/*     */     {
/*  93 */       read = ((ServletInputStream)this.in).readLine(this.buf, this.count, this.buf.length - this.count);
/*     */ 
/*  95 */       if (read == -1) {
/*  96 */         throw new IOException("unexpected end of part");
/*     */       }
/*  98 */       if (read >= boundaryLength) {
/*  99 */         this.eof = true;
/* 100 */         for (int i = 0; i < boundaryLength; i++) {
/* 101 */           if (this.boundary.charAt(i) == this.buf[(this.count + i)])
/*     */             continue;
/* 103 */           this.eof = false;
/* 104 */           break;
/*     */         }
/*     */ 
/* 107 */         if (this.eof)
/*     */         {
/*     */           break;
/*     */         }
/*     */       }
/*     */ 
/* 113 */       this.count += read;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {
/* 129 */     if (this.count - this.pos <= 2) {
/* 130 */       fill();
/* 131 */       if (this.count - this.pos <= 2) {
/* 132 */         return -1;
/*     */       }
/*     */     }
/* 135 */     return this.buf[(this.pos++)] & 0xFF;
/*     */   }
/*     */ 
/*     */   public int read(byte[] b)
/*     */     throws IOException
/*     */   {
/* 152 */     return read(b, 0, b.length);
/*     */   }
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 172 */     int total = 0;
/* 173 */     if (len == 0) {
/* 174 */       return 0;
/*     */     }
/*     */ 
/* 177 */     int avail = this.count - this.pos - 2;
/* 178 */     if (avail <= 0) {
/* 179 */       fill();
/* 180 */       avail = this.count - this.pos - 2;
/* 181 */       if (avail <= 0) {
/* 182 */         return -1;
/*     */       }
/*     */     }
/* 185 */     int copy = Math.min(len, avail);
/* 186 */     System.arraycopy(this.buf, this.pos, b, off, copy);
/* 187 */     this.pos += copy;
/* 188 */     total += copy;
/*     */ 
/* 190 */     while (total < len) {
/* 191 */       fill();
/* 192 */       avail = this.count - this.pos - 2;
/* 193 */       if (avail <= 0) {
/* 194 */         return total;
/*     */       }
/* 196 */       copy = Math.min(len - total, avail);
/* 197 */       System.arraycopy(this.buf, this.pos, b, off + total, copy);
/* 198 */       this.pos += copy;
/* 199 */       total += copy;
/*     */     }
/* 201 */     return total;
/*     */   }
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 215 */     int avail = this.count - this.pos - 2 + this.in.available();
/*     */ 
/* 217 */     return avail < 0 ? 0 : avail;
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 233 */     while ((!this.eof) && 
/* 234 */       (read(this.buf, 0, this.buf.length) != -1));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.PartInputStream
 * JD-Core Version:    0.6.0
 */