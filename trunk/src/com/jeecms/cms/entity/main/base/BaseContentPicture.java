/*    */ package com.jeecms.cms.entity.main.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class BaseContentPicture
/*    */   implements Serializable
/*    */ {
/* 17 */   public static String REF = "ContentPicture";
/* 18 */   public static String PROP_DESCRIPTION = "description";
/* 19 */   public static String PROP_IMG_PATH = "imgPath";
/*    */   private String imgPath;
/*    */   private String description;
/*    */ 
/*    */   public BaseContentPicture()
/*    */   {
/* 24 */     initialize();
/*    */   }
/*    */ 
/*    */   public BaseContentPicture(String imgPath)
/*    */   {
/* 33 */     setImgPath(imgPath);
/* 34 */     initialize();
/*    */   }
/*    */ 
/*    */   protected void initialize()
/*    */   {
/*    */   }
/*    */ 
/*    */   public String getImgPath()
/*    */   {
/* 54 */     return this.imgPath;
/*    */   }
/*    */ 
/*    */   public void setImgPath(String imgPath)
/*    */   {
/* 62 */     this.imgPath = imgPath;
/*    */   }
/*    */ 
/*    */   public String getDescription()
/*    */   {
/* 70 */     return this.description;
/*    */   }
/*    */ 
/*    */   public void setDescription(String description)
/*    */   {
/* 78 */     this.description = description;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 87 */     return super.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentPicture
 * JD-Core Version:    0.6.0
 */