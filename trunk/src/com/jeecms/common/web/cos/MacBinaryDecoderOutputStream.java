/*    */ package com.jeecms.common.web.cos;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ public class MacBinaryDecoderOutputStream extends FilterOutputStream
/*    */ {
/* 14 */   private int bytesFiltered = 0;
/* 15 */   private int dataForkLength = 0;
/*    */ 
/*    */   public MacBinaryDecoderOutputStream(OutputStream out) {
/* 18 */     super(out);
/*    */   }
/*    */ 
/*    */   public void write(int b)
/*    */     throws IOException
/*    */   {
/* 24 */     if ((this.bytesFiltered <= 86) && (this.bytesFiltered >= 83)) {
/* 25 */       int leftShift = (86 - this.bytesFiltered) * 8;
/* 26 */       this.dataForkLength |= (b & 0xFF) << leftShift;
/*    */     }
/* 30 */     else if ((this.bytesFiltered < 128 + this.dataForkLength) && (this.bytesFiltered >= 128)) {
/* 31 */       this.out.write(b);
/*    */     }
/*    */ 
/* 34 */     this.bytesFiltered += 1;
/*    */   }
/*    */ 
/*    */   public void write(byte[] b) throws IOException {
/* 38 */     write(b, 0, b.length);
/*    */   }
/*    */ 
/*    */   public void write(byte[] b, int off, int len) throws IOException
/*    */   {
/* 43 */     if (this.bytesFiltered >= 128 + this.dataForkLength) {
/* 44 */       this.bytesFiltered += len;
/*    */     }
/* 47 */     else if ((this.bytesFiltered >= 128) && 
/* 48 */       (this.bytesFiltered + len <= 128 + this.dataForkLength)) {
/* 49 */       this.out.write(b, off, len);
/* 50 */       this.bytesFiltered += len;
/*    */     }
/*    */     else
/*    */     {
/* 54 */       for (int i = 0; i < len; i++)
/* 55 */         write(b[(off + i)]);
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.MacBinaryDecoderOutputStream
 * JD-Core Version:    0.6.0
 */