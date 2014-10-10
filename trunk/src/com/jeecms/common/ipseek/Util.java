/*    */ package com.jeecms.common.ipseek;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ public class Util
/*    */ {
/* 11 */   private static StringBuilder sb = new StringBuilder();
/*    */ 
/*    */   public static byte[] getIpByteArrayFromString(String ip)
/*    */   {
/* 21 */     byte[] ret = new byte[4];
/* 22 */     StringTokenizer st = new StringTokenizer(ip, ".");
/*    */     try {
/* 24 */       ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
/* 25 */       ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
/* 26 */       ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
/* 27 */       ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 0xFF);
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/* 31 */     return ret;
/*    */   }
/*    */ 
/*    */   public static String getIpStringFromBytes(byte[] ip)
/*    */   {
/* 41 */     if (sb.length() > 0) {
/* 42 */       sb.delete(0, sb.length() - 1);
/*    */     }
/* 44 */     sb.append(ip[0] & 0xFF);
/* 45 */     sb.append('.');
/* 46 */     sb.append(ip[1] & 0xFF);
/* 47 */     sb.append('.');
/* 48 */     sb.append(ip[2] & 0xFF);
/* 49 */     sb.append('.');
/* 50 */     sb.append(ip[3] & 0xFF);
/* 51 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   public static String getString(byte[] b, int offset, int len, String encoding)
/*    */   {
/*    */     try
/*    */     {
/* 70 */       return new String(b, offset, len, encoding); } catch (UnsupportedEncodingException e) {
/*    */     }
/* 72 */     return new String(b, offset, len);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.ipseek.Util
 * JD-Core Version:    0.6.0
 */