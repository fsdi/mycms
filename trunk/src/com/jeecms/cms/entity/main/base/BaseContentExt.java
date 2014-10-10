/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseContentExt
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "ContentExt";
/*  18 */   public static String PROP_SHORT_TITLE = "shortTitle";
/*  19 */   public static String PROP_TITLE_COLOR = "titleColor";
/*  20 */   public static String PROP_DESCRIPTION = "description";
/*  21 */   public static String PROP_MEDIA_TYPE = "mediaType";
/*  22 */   public static String PROP_TITLE_IMG = "titleImg";
/*  23 */   public static String PROP_AUTHOR = "author";
/*  24 */   public static String PROP_ORIGIN = "origin";
/*  25 */   public static String PROP_TITLE = "title";
/*  26 */   public static String PROP_CONTENT_IMG = "contentImg";
/*  27 */   public static String PROP_TYPE_IMG = "typeImg";
/*  28 */   public static String PROP_ORIGIN_URL = "originUrl";
/*  29 */   public static String PROP_LINK = "link";
/*  30 */   public static String PROP_NEED_REGENERATE = "needRegenerate";
/*  31 */   public static String PROP_MEDIA_PATH = "mediaPath";
/*  32 */   public static String PROP_BOLD = "bold";
/*  33 */   public static String PROP_ID = "id";
/*  34 */   public static String PROP_CONTENT = "content";
/*  35 */   public static String PROP_RELEASE_DATE = "releaseDate";
/*  36 */   public static String PROP_TPL_CONTENT = "tplContent";
/*     */ 
/*  74 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String shortTitle;
/*     */   private String description;
/*     */   private String author;
/*     */   private String origin;
/*     */   private String originUrl;
/*     */   private Date releaseDate;
/*     */   private String mediaPath;
/*     */   private String mediaType;
/*     */   private String titleColor;
/*     */   private Boolean bold;
/*     */   private String titleImg;
/*     */   private String contentImg;
/*     */   private String typeImg;
/*     */   private String link;
/*     */   private String tplContent;
/*     */   private Boolean needRegenerate;
/*     */   private Content content;
/*     */ 
/*     */   public BaseContentExt()
/*     */   {
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentExt(Integer id)
/*     */   {
/*  48 */     setId(id);
/*  49 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContentExt(Integer id, String title, Date releaseDate, Boolean bold, Boolean needRegenerate)
/*     */   {
/*  62 */     setId(id);
/*  63 */     setTitle(title);
/*  64 */     setReleaseDate(releaseDate);
/*  65 */     setBold(bold);
/*  66 */     setNeedRegenerate(needRegenerate);
/*  67 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 110 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 118 */     this.id = id;
/* 119 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 129 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 137 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getShortTitle()
/*     */   {
/* 145 */     return this.shortTitle;
/*     */   }
/*     */ 
/*     */   public void setShortTitle(String shortTitle)
/*     */   {
/* 153 */     this.shortTitle = shortTitle;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 161 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 169 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getAuthor()
/*     */   {
/* 177 */     return this.author;
/*     */   }
/*     */ 
/*     */   public void setAuthor(String author)
/*     */   {
/* 185 */     this.author = author;
/*     */   }
/*     */ 
/*     */   public String getOrigin()
/*     */   {
/* 193 */     return this.origin;
/*     */   }
/*     */ 
/*     */   public void setOrigin(String origin)
/*     */   {
/* 201 */     this.origin = origin;
/*     */   }
/*     */ 
/*     */   public String getOriginUrl()
/*     */   {
/* 209 */     return this.originUrl;
/*     */   }
/*     */ 
/*     */   public void setOriginUrl(String originUrl)
/*     */   {
/* 217 */     this.originUrl = originUrl;
/*     */   }
/*     */ 
/*     */   public Date getReleaseDate()
/*     */   {
/* 225 */     return this.releaseDate;
/*     */   }
/*     */ 
/*     */   public void setReleaseDate(Date releaseDate)
/*     */   {
/* 233 */     this.releaseDate = releaseDate;
/*     */   }
/*     */ 
/*     */   public String getMediaPath()
/*     */   {
/* 241 */     return this.mediaPath;
/*     */   }
/*     */ 
/*     */   public void setMediaPath(String mediaPath)
/*     */   {
/* 249 */     this.mediaPath = mediaPath;
/*     */   }
/*     */ 
/*     */   public String getMediaType()
/*     */   {
/* 257 */     return this.mediaType;
/*     */   }
/*     */ 
/*     */   public void setMediaType(String mediaType)
/*     */   {
/* 265 */     this.mediaType = mediaType;
/*     */   }
/*     */ 
/*     */   public String getTitleColor()
/*     */   {
/* 273 */     return this.titleColor;
/*     */   }
/*     */ 
/*     */   public void setTitleColor(String titleColor)
/*     */   {
/* 281 */     this.titleColor = titleColor;
/*     */   }
/*     */ 
/*     */   public Boolean getBold()
/*     */   {
/* 289 */     return this.bold;
/*     */   }
/*     */ 
/*     */   public void setBold(Boolean bold)
/*     */   {
/* 297 */     this.bold = bold;
/*     */   }
/*     */ 
/*     */   public String getTitleImg()
/*     */   {
/* 305 */     return this.titleImg;
/*     */   }
/*     */ 
/*     */   public void setTitleImg(String titleImg)
/*     */   {
/* 313 */     this.titleImg = titleImg;
/*     */   }
/*     */ 
/*     */   public String getContentImg()
/*     */   {
/* 321 */     return this.contentImg;
/*     */   }
/*     */ 
/*     */   public void setContentImg(String contentImg)
/*     */   {
/* 329 */     this.contentImg = contentImg;
/*     */   }
/*     */ 
/*     */   public String getTypeImg()
/*     */   {
/* 337 */     return this.typeImg;
/*     */   }
/*     */ 
/*     */   public void setTypeImg(String typeImg)
/*     */   {
/* 345 */     this.typeImg = typeImg;
/*     */   }
/*     */ 
/*     */   public String getLink()
/*     */   {
/* 353 */     return this.link;
/*     */   }
/*     */ 
/*     */   public void setLink(String link)
/*     */   {
/* 361 */     this.link = link;
/*     */   }
/*     */ 
/*     */   public String getTplContent()
/*     */   {
/* 369 */     return this.tplContent;
/*     */   }
/*     */ 
/*     */   public void setTplContent(String tplContent)
/*     */   {
/* 377 */     this.tplContent = tplContent;
/*     */   }
/*     */ 
/*     */   public Boolean getNeedRegenerate()
/*     */   {
/* 385 */     return this.needRegenerate;
/*     */   }
/*     */ 
/*     */   public void setNeedRegenerate(Boolean needRegenerate)
/*     */   {
/* 393 */     this.needRegenerate = needRegenerate;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 401 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 409 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 415 */     if (obj == null) return false;
/* 416 */     if (!(obj instanceof ContentExt)) return false;
/*     */ 
/* 418 */     ContentExt contentExt = (ContentExt)obj;
/* 419 */     if ((getId() == null) || (contentExt.getId() == null)) return false;
/* 420 */     return getId().equals(contentExt.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 425 */     if (-2147483648 == this.hashCode) {
/* 426 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 428 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 429 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 432 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 437 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContentExt
 * JD-Core Version:    0.6.0
 */