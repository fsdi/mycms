/*     */ package com.jeecms.common.image;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public abstract class ImageUtils
/*     */ {
/*  13 */   public static final String[] IMAGE_EXT = { "jpg", "jpeg", 
/*  14 */     "gif", "png", "bmp" };
/*     */ 
/*     */   public static boolean isValidImageExt(String ext)
/*     */   {
/*  23 */     ext = ext.toLowerCase(Locale.ENGLISH);
/*  24 */     for (String s : IMAGE_EXT) {
/*  25 */       if (s.equalsIgnoreCase(ext)) {
/*  26 */         return true;
/*     */       }
/*     */     }
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isImage(InputStream in)
/*     */   {
/*  41 */     ImageInfo ii = new ImageInfo();
/*  42 */     ii.setInput(in);
/*  43 */     return ii.check();
/*     */   }
/*     */ 
/*     */   public static Position markPosition(int width, int height, int p, int offsetx, int offsety)
/*     */   {
/*  63 */     if ((p < 1) || (p > 5))
/*  64 */       p = (int)(Math.random() * 5.0D) + 1;
/*     */     int y;
/*     */     int x;
/*  67 */     switch (p)
/*     */     {
/*     */     case 1:
/*  70 */       x = offsetx;
/*  71 */       y = offsety;
/*  72 */       break;
/*     */     case 2:
/*  75 */       x = width + offsetx;
/*  76 */       y = offsety;
/*  77 */       break;
/*     */     case 3:
/*  80 */       x = offsetx;
/*  81 */       y = height + offsety;
/*  82 */       break;
/*     */     case 4:
/*  85 */       x = width + offsetx;
/*  86 */       y = height + offsety;
/*  87 */       break;
/*     */     case 5:
/*  90 */       x = width / 2 + offsetx;
/*  91 */       y = height / 2 + offsety;
/*  92 */       break;
/*     */     default:
/*  94 */       throw new RuntimeException("never reach ...");
/*     */     }
/*  96 */     return new Position(x, y);
/*     */   }
/*     */ 
/*     */   public static class Position
/*     */   {
/*     */     private int x;
/*     */     private int y;
/*     */ 
/*     */     public Position(int x, int y)
/*     */     {
/* 109 */       this.x = x;
/* 110 */       this.y = y;
/*     */     }
/*     */ 
/*     */     public int getX() {
/* 114 */       return this.x;
/*     */     }
/*     */ 
/*     */     public void setX(int x) {
/* 118 */       this.x = x;
/*     */     }
/*     */ 
/*     */     public int getY() {
/* 122 */       return this.y;
/*     */     }
/*     */ 
/*     */     public void setY(int y) {
/* 126 */       this.y = y;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.image.ImageUtils
 * JD-Core Version:    0.6.0
 */