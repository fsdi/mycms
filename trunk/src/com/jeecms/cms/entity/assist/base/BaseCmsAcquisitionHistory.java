/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class BaseCmsAcquisitionHistory
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsAcquisitionHistory";
/*  18 */   public static String PROP_ACQUISITION = "acquisition";
/*  19 */   public static String PROP_DESCRIPTION = "description";
/*  20 */   public static String PROP_CONTENT_URL = "contentUrl";
/*  21 */   public static String PROP_ID = "id";
/*  22 */   public static String PROP_CONTENT = "content";
/*  23 */   public static String PROP_CHANNEL_URL = "channelUrl";
/*  24 */   public static String PROP_TITLE = "title";
/*     */ 
/*  58 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String channelUrl;
/*     */   private String contentUrl;
/*     */   private String title;
/*     */   private String description;
/*     */   private CmsAcquisition acquisition;
/*     */   private Content content;
/*     */ 
/*     */   public BaseCmsAcquisitionHistory()
/*     */   {
/*  29 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisitionHistory(Integer id)
/*     */   {
/*  36 */     setId(id);
/*  37 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisitionHistory(Integer id, String channelUrl, String contentUrl)
/*     */   {
/*  48 */     setId(id);
/*  49 */     setChannelUrl(channelUrl);
/*  50 */     setContentUrl(contentUrl);
/*  51 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/*  90 */     this.id = id;
/*  91 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getChannelUrl()
/*     */   {
/* 101 */     return this.channelUrl;
/*     */   }
/*     */ 
/*     */   public void setChannelUrl(String channelUrl)
/*     */   {
/* 109 */     this.channelUrl = channelUrl;
/*     */   }
/*     */ 
/*     */   public String getContentUrl()
/*     */   {
/* 117 */     return this.contentUrl;
/*     */   }
/*     */ 
/*     */   public void setContentUrl(String contentUrl)
/*     */   {
/* 125 */     this.contentUrl = contentUrl;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 133 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 141 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 149 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 157 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public CmsAcquisition getAcquisition()
/*     */   {
/* 165 */     return this.acquisition;
/*     */   }
/*     */ 
/*     */   public void setAcquisition(CmsAcquisition acquisition)
/*     */   {
/* 173 */     this.acquisition = acquisition;
/*     */   }
/*     */ 
/*     */   public Content getContent()
/*     */   {
/* 181 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(Content content)
/*     */   {
/* 189 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 195 */     if (obj == null) return false;
/* 196 */     if (!(obj instanceof CmsAcquisitionHistory)) return false;
/*     */ 
/* 198 */     CmsAcquisitionHistory cmsAcquisitionHistory = (CmsAcquisitionHistory)obj;
/* 199 */     if ((getId() == null) || (cmsAcquisitionHistory.getId() == null)) return false;
/* 200 */     return getId().equals(cmsAcquisitionHistory.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 205 */     if (-2147483648 == this.hashCode) {
/* 206 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 208 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 209 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 212 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 217 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsAcquisitionHistory
 * JD-Core Version:    0.6.0
 */