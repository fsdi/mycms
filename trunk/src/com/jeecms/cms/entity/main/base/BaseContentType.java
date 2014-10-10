/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseContentType
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentType";
/*  18 */   public static String PROP_IMG_HEIGHT = "imgHeight";
/*  19 */   public static String PROP_IMG_WIDTH = "imgWidth";
/*  20 */   public static String PROP_DISABLED = "disabled";
/*  21 */   public static String PROP_NAME = "name";
/*  22 */   public static String PROP_ID = "id";
/*  23 */   public static String PROP_HAS_IMAGE = "hasImage";
/*     */ 
/*  59 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Integer imgWidth;
/*     */   private Integer imgHeight;
/*     */   private Boolean hasImage;
/*     */   private Boolean disabled;
/*     */ 
/*     */   public BaseContentType()
/*     */   {
/*  28 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentType(Integer id)
/*     */   {
/*  35 */     setId(id);
/*  36 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentType(Integer id, String name, Boolean hasImage, Boolean disabled)
/*     */   {
/*  48 */     setId(id);
/*  49 */     setName(name);
/*  50 */     setHasImage(hasImage);
/*  51 */     setDisabled(disabled);
/*  52 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  80 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  88 */     this.id = id;
/*  89 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  99 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 107 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Integer getImgWidth()
/*     */   {
/* 115 */     return this.imgWidth;
/*     */   }
/*     */ 
/*     */   public void setImgWidth(Integer imgWidth)
/*     */   {
/* 123 */     this.imgWidth = imgWidth;
/*     */   }
/*     */ 
/*     */   public Integer getImgHeight()
/*     */   {
/* 131 */     return this.imgHeight;
/*     */   }
/*     */ 
/*     */   public void setImgHeight(Integer imgHeight)
/*     */   {
/* 139 */     this.imgHeight = imgHeight;
/*     */   }
/*     */ 
/*     */   public Boolean getHasImage()
/*     */   {
/* 147 */     return this.hasImage;
/*     */   }
/*     */ 
/*     */   public void setHasImage(Boolean hasImage)
/*     */   {
/* 155 */     this.hasImage = hasImage;
/*     */   }
/*     */ 
/*     */   public Boolean getDisabled()
/*     */   {
/* 163 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled)
/*     */   {
/* 171 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 177 */     if (obj == null) return false;
/* 178 */     if (!(obj instanceof ContentType)) return false;
/*     */ 
/* 180 */     ContentType contentType = (ContentType)obj;
/* 181 */     if ((getId() == null) || (contentType.getId() == null)) return false;
/* 182 */     return getId().equals(contentType.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 187 */     if (-2147483648 == this.hashCode) {
/* 188 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 190 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 191 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 194 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 199 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentType
 * JD-Core Version:    0.6.0
 */