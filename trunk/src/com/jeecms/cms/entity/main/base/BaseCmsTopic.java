/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsTopic;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsTopic
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsTopic";
/*  18 */   public static String PROP_RECOMMEND = "recommend";
/*  19 */   public static String PROP_DESCRIPTION = "description";
/*  20 */   public static String PROP_TITLE_IMG = "titleImg";
/*  21 */   public static String PROP_SHORT_NAME = "shortName";
/*  22 */   public static String PROP_KEYWORDS = "keywords";
/*  23 */   public static String PROP_CHANNEL = "channel";
/*  24 */   public static String PROP_PRIORITY = "priority";
/*  25 */   public static String PROP_NAME = "name";
/*  26 */   public static String PROP_ID = "id";
/*  27 */   public static String PROP_TPL_CONTENT = "tplContent";
/*  28 */   public static String PROP_CONTENT_IMG = "contentImg";
/*     */ 
/*  64 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private String shortName;
/*     */   private String keywords;
/*     */   private String description;
/*     */   private String titleImg;
/*     */   private String contentImg;
/*     */   private String tplContent;
/*     */   private Integer priority;
/*     */   private Boolean recommend;
/*     */   private Channel channel;
/*     */ 
/*     */   public BaseCmsTopic()
/*     */   {
/*  33 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsTopic(Integer id)
/*     */   {
/*  40 */     setId(id);
/*  41 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsTopic(Integer id, String name, Integer priority, Boolean recommend)
/*     */   {
/*  53 */     setId(id);
/*  54 */     setName(name);
/*  55 */     setPriority(priority);
/*  56 */     setRecommend(recommend);
/*  57 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  92 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 100 */     this.id = id;
/* 101 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 111 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 119 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getShortName()
/*     */   {
/* 127 */     return this.shortName;
/*     */   }
/*     */ 
/*     */   public void setShortName(String shortName)
/*     */   {
/* 135 */     this.shortName = shortName;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 143 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords)
/*     */   {
/* 151 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 159 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 167 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getTitleImg()
/*     */   {
/* 175 */     return this.titleImg;
/*     */   }
/*     */ 
/*     */   public void setTitleImg(String titleImg)
/*     */   {
/* 183 */     this.titleImg = titleImg;
/*     */   }
/*     */ 
/*     */   public String getContentImg()
/*     */   {
/* 191 */     return this.contentImg;
/*     */   }
/*     */ 
/*     */   public void setContentImg(String contentImg)
/*     */   {
/* 199 */     this.contentImg = contentImg;
/*     */   }
/*     */ 
/*     */   public String getTplContent()
/*     */   {
/* 207 */     return this.tplContent;
/*     */   }
/*     */ 
/*     */   public void setTplContent(String tplContent)
/*     */   {
/* 215 */     this.tplContent = tplContent;
/*     */   }
/*     */ 
/*     */   public Integer getPriority()
/*     */   {
/* 223 */     return this.priority;
/*     */   }
/*     */ 
/*     */   public void setPriority(Integer priority)
/*     */   {
/* 231 */     this.priority = priority;
/*     */   }
/*     */ 
/*     */   public Boolean getRecommend()
/*     */   {
/* 239 */     return this.recommend;
/*     */   }
/*     */ 
/*     */   public void setRecommend(Boolean recommend)
/*     */   {
/* 247 */     this.recommend = recommend;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/* 255 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel)
/*     */   {
/* 263 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 269 */     if (obj == null) return false;
/* 270 */     if (!(obj instanceof CmsTopic)) return false;
/*     */ 
/* 272 */     CmsTopic cmsTopic = (CmsTopic)obj;
/* 273 */     if ((getId() == null) || (cmsTopic.getId() == null)) return false;
/* 274 */     return getId().equals(cmsTopic.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 279 */     if (-2147483648 == this.hashCode) {
/* 280 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 282 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 283 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 286 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 291 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsTopic
 * JD-Core Version:    0.6.0
 */