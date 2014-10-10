/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsModelItem;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsModel
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsModel";
/*  18 */   public static String PROP_TPL_CHANNEL_PREFIX = "tplChannelPrefix";
/*  19 */   public static String PROP_TITLE_IMG_HEIGHT = "titleImgHeight";
/*  20 */   public static String PROP_DISABLED = "disabled";
/*  21 */   public static String PROP_DEF = "def";
/*  22 */   public static String PROP_PRIORITY = "priority";
/*  23 */   public static String PROP_TITLE_IMG_WIDTH = "titleImgWidth";
/*  24 */   public static String PROP_CONTENT_IMG_WIDTH = "contentImgWidth";
/*  25 */   public static String PROP_PATH = "path";
/*  26 */   public static String PROP_HAS_CONTENT = "hasContent";
/*  27 */   public static String PROP_NAME = "name";
/*  28 */   public static String PROP_CONTENT_IMG_HEIGHT = "contentImgHeight";
/*  29 */   public static String PROP_TPL_CONTENT_PREFIX = "tplContentPrefix";
/*  30 */   public static String PROP_ID = "id";
/*     */ 
/*  80 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String path;
/*     */   private String tplChannelPrefix;
/*     */   private String tplContentPrefix;
/*     */   private Integer titleImgWidth;
/*     */   private Integer titleImgHeight;
/*     */   private Integer contentImgWidth;
/*     */   private Integer contentImgHeight;
/*     */   private Integer priority;
/*     */   private Boolean hasContent;
/*     */   private Boolean disabled;
/*     */   private Boolean def;
/*     */   private Set<CmsModelItem> items;
/*     */ 
/*     */   public BaseCmsModel()
/*     */   {
/*  35 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsModel(Integer id)
/*     */   {
/*  42 */     setId(id);
/*  43 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsModel(Integer id, String name, String path, Integer titleImgWidth, Integer titleImgHeight, Integer contentImgWidth, Integer contentImgHeight, Integer priority, Boolean hasContent, Boolean disabled, Boolean def)
/*     */   {
/*  62 */     setId(id);
/*  63 */     setName(name);
/*  64 */     setPath(path);
/*  65 */     setTitleImgWidth(titleImgWidth);
/*  66 */     setTitleImgHeight(titleImgHeight);
/*  67 */     setContentImgWidth(contentImgWidth);
/*  68 */     setContentImgHeight(contentImgHeight);
/*  69 */     setPriority(priority);
/*  70 */     setHasContent(hasContent);
/*  71 */     setDisabled(disabled);
/*  72 */     setDef(def);
/*  73 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 111 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 119 */     this.id = id;
/* 120 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 130 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 138 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 146 */     return this.path;
/*     */   }
/*     */ 
/*     */   public void setPath(String path)
/*     */   {
/* 154 */     this.path = path;
/*     */   }
/*     */ 
/*     */   public String getTplChannelPrefix()
/*     */   {
/* 162 */     return this.tplChannelPrefix;
/*     */   }
/*     */ 
/*     */   public void setTplChannelPrefix(String tplChannelPrefix)
/*     */   {
/* 170 */     this.tplChannelPrefix = tplChannelPrefix;
/*     */   }
/*     */ 
/*     */   public String getTplContentPrefix()
/*     */   {
/* 178 */     return this.tplContentPrefix;
/*     */   }
/*     */ 
/*     */   public void setTplContentPrefix(String tplContentPrefix)
/*     */   {
/* 186 */     this.tplContentPrefix = tplContentPrefix;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgWidth()
/*     */   {
/* 194 */     return this.titleImgWidth;
/*     */   }
/*     */ 
/*     */   public void setTitleImgWidth(Integer titleImgWidth)
/*     */   {
/* 202 */     this.titleImgWidth = titleImgWidth;
/*     */   }
/*     */ 
/*     */   public Integer getTitleImgHeight()
/*     */   {
/* 210 */     return this.titleImgHeight;
/*     */   }
/*     */ 
/*     */   public void setTitleImgHeight(Integer titleImgHeight)
/*     */   {
/* 218 */     this.titleImgHeight = titleImgHeight;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgWidth()
/*     */   {
/* 226 */     return this.contentImgWidth;
/*     */   }
/*     */ 
/*     */   public void setContentImgWidth(Integer contentImgWidth)
/*     */   {
/* 234 */     this.contentImgWidth = contentImgWidth;
/*     */   }
/*     */ 
/*     */   public Integer getContentImgHeight()
/*     */   {
/* 242 */     return this.contentImgHeight;
/*     */   }
/*     */ 
/*     */   public void setContentImgHeight(Integer contentImgHeight)
/*     */   {
/* 250 */     this.contentImgHeight = contentImgHeight;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 258 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 266 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Boolean getHasContent()
/*     */   {
/* 274 */     return this.hasContent;
/*     */   }
/*     */ 
/*     */   public void setHasContent(Boolean hasContent)
/*     */   {
/* 282 */     this.hasContent = hasContent;
/*     */   }
/*     */ 
/*     */   public Boolean getDisabled()
/*     */   {
/* 290 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled)
/*     */   {
/* 298 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public Boolean getDef()
/*     */   {
/* 306 */     return this.def;
/*     */   }
/*     */ 
/*     */   public void setDef(Boolean def)
/*     */   {
/* 314 */     this.def = def;
/*     */   }
/*     */ 
/*     */   public Set<CmsModelItem> getItems()
/*     */   {
/* 322 */     return this.items;
/*     */   }
/*     */ 
/*     */   public void setItems(Set<CmsModelItem> items)
/*     */   {
/* 330 */     this.items = items;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 336 */     if (obj == null) return false;
/* 337 */     if (!(obj instanceof CmsModel)) return false;
/*     */ 
/* 339 */     CmsModel cmsModel = (CmsModel)obj;
/* 340 */     if ((getId() == null) || (cmsModel.getId() == null)) return false;
/* 341 */     return getId().equals(cmsModel.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 346 */     if (-2147483648 == this.hashCode) {
/* 347 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 349 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 350 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 353 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 358 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsModel
 * JD-Core Version:    0.6.0
 */