/*     */ package com.jeecms.common.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class Num62
/*     */ {
/*  10 */   public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6', 
/*  11 */     '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*  12 */     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
/*  13 */     'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
/*  14 */     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
/*  15 */     'x', 'y', 'z' };
/*     */ 
/*  19 */   public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6', 
/*  20 */     '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
/*  21 */     'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
/*  22 */     'x', 'y', 'z' };
/*     */   public static final int LONG_N36_LEN = 13;
/*     */   public static final int LONG_N62_LEN = 11;
/*     */ 
/*     */   private static StringBuilder longToNBuf(long l, char[] chars)
/*     */   {
/*  40 */     int upgrade = chars.length;
/*  41 */     StringBuilder result = new StringBuilder();
/*     */ 
/*  43 */     while (l > 0L) {
/*  44 */       int last = (int)(l % upgrade);
/*  45 */       result.append(chars[last]);
/*  46 */       l /= upgrade;
/*     */     }
/*  48 */     return result;
/*     */   }
/*     */ 
/*     */   public static String longToN62(long l)
/*     */   {
/*  58 */     return longToNBuf(l, N62_CHARS).reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN36(long l)
/*     */   {
/*  68 */     return longToNBuf(l, N36_CHARS).reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN62(long l, int length)
/*     */   {
/*  80 */     StringBuilder sb = longToNBuf(l, N62_CHARS);
/*  81 */     for (int i = sb.length(); i < length; i++) {
/*  82 */       sb.append('0');
/*     */     }
/*  84 */     return sb.reverse().toString();
/*     */   }
/*     */ 
/*     */   public static String longToN36(long l, int length)
/*     */   {
/*  96 */     StringBuilder sb = longToNBuf(l, N36_CHARS);
/*  97 */     for (int i = sb.length(); i < length; i++) {
/*  98 */       sb.append('0');
/*     */     }
/* 100 */     return sb.reverse().toString();
/*     */   }
/*     */ 
/*     */   public static long n62ToLong(String n62)
/*     */   {
/* 110 */     return nToLong(n62, N62_CHARS);
/*     */   }
/*     */ 
/*     */   public static long n36ToLong(String n36)
/*     */   {
/* 120 */     return nToLong(n36, N36_CHARS);
/*     */   }
/*     */ 
/*     */   private static long nToLong(String s, char[] chars) {
/* 124 */     char[] nc = s.toCharArray();
/* 125 */     long result = 0L;
/* 126 */     long pow = 1L;
/* 127 */     for (int i = nc.length - 1; i >= 0; pow *= chars.length) {
/* 128 */       int n = findNIndex(nc[i], chars);
/* 129 */       result += n * pow;
/*     */ 
/* 127 */       i--;
/*     */     }
/*     */ 
/* 131 */     return result;
/*     */   }
/*     */ 
/*     */   private static int findNIndex(char c, char[] chars) {
/* 135 */     for (int i = 0; i < chars.length; i++) {
/* 136 */       if (c == chars[i]) {
/* 137 */         return i;
/*     */       }
/*     */     }
/* 140 */     throw new RuntimeException("N62(N36)非法字符：" + c);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 144 */     System.out.println(longToN62(9223372036854775807L));
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.Num62
 * JD-Core Version:    0.6.0
 */