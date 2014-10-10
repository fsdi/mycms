/*     */ package com.jeecms.common.image;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ public class AverageImageScale
/*     */ {
/*     */   public static void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight)
/*     */     throws IOException
/*     */   {
/*  36 */     BufferedImage srcImgBuff = ImageIO.read(srcFile);
/*  37 */     int width = srcImgBuff.getWidth();
/*  38 */     int height = srcImgBuff.getHeight();
/*  39 */     if ((width <= boxWidth) && (height <= boxHeight)) {
/*  40 */       FileUtils.copyFile(srcFile, destFile);
/*  41 */       return;
/*     */     }
/*     */     int zoomHeight;
/*     */     int zoomWidth;
/*  45 */     if (width / height > boxWidth / boxHeight) {
/*  46 */       zoomWidth = boxWidth;
/*  47 */       zoomHeight = Math.round(boxWidth * height / width);
/*     */     } else {
/*  49 */       zoomWidth = Math.round(boxHeight * width / height);
/*  50 */       zoomHeight = boxHeight;
/*     */     }
/*  52 */     BufferedImage imgBuff = scaleImage(srcImgBuff, width, height, 
/*  53 */       zoomWidth, zoomHeight);
/*  54 */     writeFile(imgBuff, destFile);
/*     */   }
/*     */ 
/*     */   public static void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight)
/*     */     throws IOException
/*     */   {
/*  81 */     BufferedImage srcImgBuff = ImageIO.read(srcFile);
/*  82 */     srcImgBuff = srcImgBuff.getSubimage(cutTop, cutLeft, cutWidth, 
/*  83 */       catHeight);
/*  84 */     int width = srcImgBuff.getWidth();
/*  85 */     int height = srcImgBuff.getHeight();
/*  86 */     if ((width <= boxWidth) && (height <= boxHeight)) {
/*  87 */       writeFile(srcImgBuff, destFile);
/*  88 */       return;
/*     */     }
/*     */     int zoomHeight;
/*     */     int zoomWidth;
/*  92 */     if (width / height > boxWidth / boxHeight) {
/*  93 */       zoomWidth = boxWidth;
/*  94 */       zoomHeight = Math.round(boxWidth * height / width);
/*     */     } else {
/*  96 */       zoomWidth = Math.round(boxHeight * width / height);
/*  97 */       zoomHeight = boxHeight;
/*     */     }
/*  99 */     BufferedImage imgBuff = scaleImage(srcImgBuff, width, height, 
/* 100 */       zoomWidth, zoomHeight);
/* 101 */     writeFile(imgBuff, destFile);
/*     */   }
/*     */ 
/*     */   public static void writeFile(BufferedImage imgBuf, File destFile) throws IOException
/*     */   {
/* 106 */     File parent = destFile.getParentFile();
/* 107 */     if (!parent.exists()) {
/* 108 */       parent.mkdirs();
/*     */     }
/* 110 */     ImageIO.write(imgBuf, "jpeg", destFile);
/*     */   }
/*     */ 
/*     */   public static void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha)
/*     */     throws IOException
/*     */   {
/* 143 */     BufferedImage imgBuff = ImageIO.read(srcFile);
/* 144 */     int width = imgBuff.getWidth();
/* 145 */     int height = imgBuff.getHeight();
/* 146 */     if ((width <= minWidth) || (height <= minHeight)) {
/* 147 */       imgBuff = null;
/* 148 */       if (!srcFile.equals(destFile))
/* 149 */         FileUtils.copyFile(srcFile, destFile);
/*     */     }
/*     */     else {
/* 152 */       imageMark(imgBuff, width, height, pos, offsetX, offsetY, text, 
/* 153 */         color, size, alpha);
/* 154 */       writeFile(imgBuff, destFile);
/* 155 */       imgBuff = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, File markFile)
/*     */     throws IOException
/*     */   {
/* 183 */     BufferedImage imgBuff = ImageIO.read(srcFile);
/* 184 */     int width = imgBuff.getWidth();
/* 185 */     int height = imgBuff.getHeight();
/* 186 */     if ((width <= minWidth) || (height <= minHeight)) {
/* 187 */       imgBuff = null;
/* 188 */       if (!srcFile.equals(destFile))
/* 189 */         FileUtils.copyFile(srcFile, destFile);
/*     */     }
/*     */     else {
/* 192 */       imageMark(imgBuff, width, height, pos, offsetX, offsetY, markFile);
/* 193 */       writeFile(imgBuff, destFile);
/* 194 */       imgBuff = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void imageMark(BufferedImage imgBuff, int width, int height, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha)
/*     */     throws IOException
/*     */   {
/* 227 */     ImageUtils.Position p = ImageUtils.markPosition(width, height, pos, offsetX, 
/* 228 */       offsetY);
/* 229 */     Graphics2D g = imgBuff.createGraphics();
/* 230 */     g.setColor(color);
/* 231 */     g.setFont(new Font(null, 0, size));
/* 232 */     AlphaComposite a = AlphaComposite.getInstance(10, 
/* 233 */       alpha / 100.0F);
/* 234 */     g.setComposite(a);
/* 235 */     g.drawString(text, p.getX(), p.getY());
/* 236 */     g.dispose();
/*     */   }
/*     */ 
/*     */   private static void imageMark(BufferedImage imgBuff, int width, int height, int pos, int offsetX, int offsetY, File markFile)
/*     */     throws IOException
/*     */   {
/* 242 */     ImageUtils.Position p = ImageUtils.markPosition(width, height, pos, offsetX, 
/* 243 */       offsetY);
/* 244 */     Graphics2D g = imgBuff.createGraphics();
/* 245 */     AlphaComposite a = AlphaComposite.getInstance(10);
/* 246 */     g.setComposite(a);
/* 247 */     g.drawImage(ImageIO.read(markFile), p.getX(), p.getY(), null);
/* 248 */     g.dispose();
/*     */   }
/*     */ 
/*     */   private static BufferedImage scaleImage(BufferedImage srcImgBuff, int width, int height, int zoomWidth, int zoomHeight)
/*     */   {
/* 253 */     int[] colorArray = srcImgBuff.getRGB(0, 0, width, height, null, 0, 
/* 254 */       width);
/* 255 */     BufferedImage outBuff = new BufferedImage(zoomWidth, zoomHeight, 
/* 256 */       1);
/*     */ 
/* 258 */     float wScale = width / zoomWidth;
/* 259 */     int wScaleInt = (int)(wScale + 0.5D);
/*     */ 
/* 261 */     float hScale = height / zoomHeight;
/* 262 */     int hScaleInt = (int)(hScale + 0.5D);
/* 263 */     int area = wScaleInt * hScaleInt;
/*     */ 
/* 268 */     for (int y = 0; y < zoomHeight; y++)
/*     */     {
/* 270 */       int y0 = (int)(y * hScale);
/* 271 */       int y1 = y0 + hScaleInt;
/* 272 */       for (int x = 0; x < zoomWidth; x++) {
/* 273 */         int x0 = (int)(x * wScale);
/* 274 */         int x1 = x0 + wScaleInt;
/*     */         long blue;
/*     */         long green;
/* 275 */         long red = green = blue = 0L;
/* 276 */         for (int i = x0; i < x1; i++) {
/* 277 */           for (int j = y0; j < y1; j++) {
/* 278 */             int color = colorArray[(width * j + i)];
/* 279 */             red += getRedValue(color);
/* 280 */             green += getGreenValue(color);
/* 281 */             blue += getBlueValue(color);
/*     */           }
/*     */         }
/* 284 */         outBuff.setRGB(x, y, 
/* 285 */           comRGB((int)(red / area), 
/* 285 */           (int)(green / area), (int)(blue / area)));
/*     */       }
/*     */     }
/* 288 */     return outBuff;
/*     */   }
/*     */ 
/*     */   private static int getRedValue(int rgbValue) {
/* 292 */     return (rgbValue & 0xFF0000) >> 16;
/*     */   }
/*     */ 
/*     */   private static int getGreenValue(int rgbValue) {
/* 296 */     return (rgbValue & 0xFF00) >> 8;
/*     */   }
/*     */ 
/*     */   private static int getBlueValue(int rgbValue) {
/* 300 */     return rgbValue & 0xFF;
/*     */   }
/*     */ 
/*     */   private static int comRGB(int redValue, int greenValue, int blueValue) {
/* 304 */     return (redValue << 16) + (greenValue << 8) + blueValue;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 308 */     long time = System.currentTimeMillis();
/* 309 */     resizeFix(new File(
/* 310 */       "test/com/jeecms/common/util/1.bmp"), new File(
/* 311 */       "test/com/jeecms/common/util/1-n-2.bmp"), 310, 310, 50, 50, 
/* 312 */       320, 320);
/* 313 */     time = System.currentTimeMillis() - time;
/* 314 */     System.out.println("resize2 img in " + time + "ms");
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.image.AverageImageScale
 * JD-Core Version:    0.6.0
 */