/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*     */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ 
/*     */ public abstract class BaseCmsAdvertising
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsAdvertising";
/*  18 */   public static String PROP_END_TIME = "endTime";
/*  19 */   public static String PROP_START_TIME = "startTime";
/*  20 */   public static String PROP_WEIGHT = "weight";
/*  21 */   public static String PROP_SITE = "site";
/*  22 */   public static String PROP_ENABLED = "enabled";
/*  23 */   public static String PROP_CODE = "code";
/*  24 */   public static String PROP_CATEGORY = "category";
/*  25 */   public static String PROP_ADSPACE = "adspace";
/*  26 */   public static String PROP_NAME = "name";
/*  27 */   public static String PROP_ID = "id";
/*  28 */   public static String PROP_CLICK_COUNT = "clickCount";
/*  29 */   public static String PROP_DISPLAY_COUNT = "displayCount";
/*     */ 
/*  75 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String category;
/*     */   private String code;
/*     */   private Integer weight;
/*     */   private Long displayCount;
/*     */   private Long clickCount;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Boolean enabled;
/*     */   private CmsAdvertisingSpace adspace;
/*     */   private CmsSite site;
/*     */   private Map<String, String> attr;
/*     */ 
/*     */   public BaseCmsAdvertising()
/*     */   {
/*  34 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAdvertising(Integer id)
/*     */   {
/*  41 */     setId(id);
/*  42 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAdvertising(Integer id, CmsAdvertisingSpace adspace, CmsSite site, String name, String category, Integer weight, Long displayCount, Long clickCount, Boolean enabled)
/*     */   {
/*  59 */     setId(id);
/*  60 */     setAdspace(adspace);
/*  61 */     setSite(site);
/*  62 */     setName(name);
/*  63 */     setCategory(category);
/*  64 */     setWeight(weight);
/*  65 */     setDisplayCount(displayCount);
/*  66 */     setClickCount(clickCount);
/*  67 */     setEnabled(enabled);
/*  68 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 107 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 115 */     this.id = id;
/* 116 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 126 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 134 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getCategory()
/*     */   {
/* 142 */     return this.category;
/*     */   }
/*     */ 
/*     */   public void setCategory(String category)
/*     */   {
/* 150 */     this.category = category;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 158 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 166 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public Integer getWeight()
/*     */   {
/* 174 */     return this.weight;
/*     */   }
/*     */ 
/*     */   public void setWeight(Integer weight)
/*     */   {
/* 182 */     this.weight = weight;
/*     */   }
/*     */ 
/*     */   public Long getDisplayCount()
/*     */   {
/* 190 */     return this.displayCount;
/*     */   }
/*     */ 
/*     */   public void setDisplayCount(Long displayCount)
/*     */   {
/* 198 */     this.displayCount = displayCount;
/*     */   }
/*     */ 
/*     */   public Long getClickCount()
/*     */   {
/* 206 */     return this.clickCount;
/*     */   }
/*     */ 
/*     */   public void setClickCount(Long clickCount)
/*     */   {
/* 214 */     this.clickCount = clickCount;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 222 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 230 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 238 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 246 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Boolean getEnabled()
/*     */   {
/* 254 */     return this.enabled;
/*     */   }
/*     */ 
/*     */   public void setEnabled(Boolean enabled)
/*     */   {
/* 262 */     this.enabled = enabled;
/*     */   }
/*     */ 
/*     */   public CmsAdvertisingSpace getAdspace()
/*     */   {
/* 270 */     return this.adspace;
/*     */   }
/*     */ 
/*     */   public void setAdspace(CmsAdvertisingSpace adspace)
/*     */   {
/* 278 */     this.adspace = adspace;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 286 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 294 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 302 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 310 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 316 */     if (obj == null) return false;
/* 317 */     if (!(obj instanceof CmsAdvertising)) return false;
/*     */ 
/* 319 */     CmsAdvertising cmsAdvertising = (CmsAdvertising)obj;
/* 320 */     if ((getId() == null) || (cmsAdvertising.getId() == null)) return false;
/* 321 */     return getId().equals(cmsAdvertising.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 326 */     if (-2147483648 == this.hashCode) {
/* 327 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 329 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 330 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 333 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 338 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsAdvertising
 * JD-Core Version:    0.6.0
 */