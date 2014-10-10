/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsAcquisitionTemp
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsAcquisitionTemp";
/*  18 */   public static String PROP_SEQ = "seq";
/*  19 */   public static String PROP_DESCRIPTION = "description";
/*  20 */   public static String PROP_SITE = "site";
/*  21 */   public static String PROP_CONTENT_URL = "contentUrl";
/*  22 */   public static String PROP_PERCENT = "percent";
/*  23 */   public static String PROP_ID = "id";
/*  24 */   public static String PROP_CHANNEL_URL = "channelUrl";
/*  25 */   public static String PROP_TITLE = "title";
/*     */ 
/*  65 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String channelUrl;
/*     */   private String contentUrl;
/*     */   private String title;
/*     */   private Integer percent;
/*     */   private String description;
/*     */   private Integer seq;
/*     */   private CmsSite site;
/*     */ 
/*     */   public BaseCmsAcquisitionTemp()
/*     */   {
/*  30 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisitionTemp(Integer id)
/*     */   {
/*  37 */     setId(id);
/*  38 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisitionTemp(Integer id, String channelUrl, String contentUrl, Integer percent, String description, Integer seq)
/*     */   {
/*  52 */     setId(id);
/*  53 */     setChannelUrl(channelUrl);
/*  54 */     setContentUrl(contentUrl);
/*  55 */     setPercent(percent);
/*  56 */     setDescription(description);
/*  57 */     setSeq(seq);
/*  58 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  90 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  98 */     this.id = id;
/*  99 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getChannelUrl()
/*     */   {
/* 109 */     return this.channelUrl;
/*     */   }
/*     */ 
/*     */   public void setChannelUrl(String channelUrl)
/*     */   {
/* 117 */     this.channelUrl = channelUrl;
/*     */   }
/*     */ 
/*     */   public String getContentUrl()
/*     */   {
/* 125 */     return this.contentUrl;
/*     */   }
/*     */ 
/*     */   public void setContentUrl(String contentUrl)
/*     */   {
/* 133 */     this.contentUrl = contentUrl;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 141 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 149 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Integer getPercent()
/*     */   {
/* 157 */     return this.percent;
/*     */   }
/*     */ 
/*     */   public void setPercent(Integer percent)
/*     */   {
/* 165 */     this.percent = percent;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 173 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 181 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Integer getSeq()
/*     */   {
/* 189 */     return this.seq;
/*     */   }
/*     */ 
/*     */   public void setSeq(Integer seq)
/*     */   {
/* 197 */     this.seq = seq;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 205 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 213 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 219 */     if (obj == null) return false;
/* 220 */     if (!(obj instanceof CmsAcquisitionTemp)) return false;
/*     */ 
/* 222 */     CmsAcquisitionTemp cmsAcquisitionTemp = (CmsAcquisitionTemp)obj;
/* 223 */     if ((getId() == null) || (cmsAcquisitionTemp.getId() == null)) return false;
/* 224 */     return getId().equals(cmsAcquisitionTemp.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 229 */     if (-2147483648 == this.hashCode) {
/* 230 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 232 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 233 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 236 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 241 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsAcquisitionTemp
 * JD-Core Version:    0.6.0
 */