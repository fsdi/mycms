/*    */ package com.jeecms.common.web.cos;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import javax.servlet.ServletInputStream;
/*    */ 
/*    */ public class ParamPart extends Part
/*    */ {
/*    */   private byte[] value;
/*    */   private String encoding;
/*    */ 
/*    */   ParamPart(String name, ServletInputStream in, String boundary, String encoding)
/*    */     throws IOException
/*    */   {
/* 37 */     super(name);
/* 38 */     this.encoding = encoding;
/*    */ 
/* 41 */     PartInputStream pis = new PartInputStream(in, boundary);
/* 42 */     ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
/* 43 */     byte[] buf = new byte[''];
/*    */     int read;
/* 45 */     while ((read = pis.read(buf)) != -1)
/*    */     {
/* 46 */       baos.write(buf, 0, read);
/*    */     }
/* 48 */     pis.close();
/* 49 */     baos.close();
/*    */ 
/* 52 */     this.value = baos.toByteArray();
/*    */   }
/*    */ 
/*    */   public byte[] getValue()
/*    */   {
/* 62 */     return this.value;
/*    */   }
/*    */ 
/*    */   public String getStringValue()
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 74 */     return getStringValue(this.encoding);
/*    */   }
/*    */ 
/*    */   public String getStringValue(String encoding)
/*    */     throws UnsupportedEncodingException
/*    */   {
/* 85 */     return new String(this.value, encoding);
/*    */   }
/*    */ 
/*    */   public boolean isParam()
/*    */   {
/* 94 */     return true;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.ParamPart
 * JD-Core Version:    0.6.0
 */