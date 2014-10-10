/*    */ package com.jeecms.common.image;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.io.File;
/*    */ import magick.Magick;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class ImageScaleImpl
/*    */   implements ImageScale
/*    */ {
/* 18 */   private static final Logger log = LoggerFactory.getLogger(ImageScaleImpl.class);
/*    */ 
/* 86 */   private boolean isMagick = false;
/* 87 */   private boolean tryMagick = true;
/*    */ 
/*    */   public void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight)
/*    */     throws Exception
/*    */   {
/* 22 */     if (this.isMagick)
/* 23 */       MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
/*    */     else
/* 25 */       AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight);
/*    */   }
/*    */ 
/*    */   public void resizeFix(File srcFile, File destFile, int boxWidth, int boxHeight, int cutTop, int cutLeft, int cutWidth, int catHeight)
/*    */     throws Exception
/*    */   {
/* 32 */     if (this.isMagick)
/* 33 */       MagickImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight, 
/* 34 */         cutTop, cutLeft, cutWidth, catHeight);
/*    */     else
/* 36 */       AverageImageScale.resizeFix(srcFile, destFile, boxWidth, boxHeight, 
/* 37 */         cutTop, cutLeft, cutWidth, catHeight);
/*    */   }
/*    */ 
/*    */   public void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, String text, Color color, int size, int alpha)
/*    */     throws Exception
/*    */   {
/* 44 */     if (this.isMagick)
/* 45 */       MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight, 
/* 46 */         pos, offsetX, offsetY, text, color, size, alpha);
/*    */     else
/* 48 */       AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight, 
/* 49 */         pos, offsetX, offsetY, text, color, size, alpha);
/*    */   }
/*    */ 
/*    */   public void imageMark(File srcFile, File destFile, int minWidth, int minHeight, int pos, int offsetX, int offsetY, File markFile)
/*    */     throws Exception
/*    */   {
/* 56 */     if (this.isMagick)
/* 57 */       MagickImageScale.imageMark(srcFile, destFile, minWidth, minHeight, 
/* 58 */         pos, offsetX, offsetY, markFile);
/*    */     else
/* 60 */       AverageImageScale.imageMark(srcFile, destFile, minWidth, minHeight, 
/* 61 */         pos, offsetX, offsetY, markFile);
/*    */   }
/*    */ 
/*    */   public void init()
/*    */   {
/* 69 */     if (this.tryMagick) {
/*    */       try {
/* 71 */         System.setProperty("jmagick.systemclassloader", "no");
/* 72 */         new Magick();
/* 73 */         log.info("using jmagick");
/* 74 */         this.isMagick = true;
/*    */       } catch (Throwable e) {
/* 76 */         log.warn("load jmagick fail, use java image scale. message:{}", 
/* 77 */           e.getMessage());
/* 78 */         this.isMagick = false;
/*    */       }
/*    */     } else {
/* 81 */       log.info("jmagick is disabled.");
/* 82 */       this.isMagick = false;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void setTryMagick(boolean tryMagick)
/*    */   {
/* 90 */     this.tryMagick = tryMagick;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.image.ImageScaleImpl
 * JD-Core Version:    0.6.0
 */