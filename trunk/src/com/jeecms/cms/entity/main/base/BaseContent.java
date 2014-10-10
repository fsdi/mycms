/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsTopic;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentAttachment;
/*     */ import com.jeecms.cms.entity.main.ContentCheck;
/*     */ import com.jeecms.cms.entity.main.ContentCount;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import com.jeecms.cms.entity.main.ContentPicture;
/*     */ import com.jeecms.cms.entity.main.ContentTag;
/*     */ import com.jeecms.cms.entity.main.ContentTxt;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseContent
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "Content";
/*  18 */   public static String PROP_STATUS = "status";
/*  19 */   public static String PROP_TYPE = "type";
/*  20 */   public static String PROP_RECOMMEND = "recommend";
/*  21 */   public static String PROP_SITE = "site";
/*  22 */   public static String PROP_USER = "user";
/*  23 */   public static String PROP_HAS_TITLE_IMG = "hasTitleImg";
/*  24 */   public static String PROP_SORT_DATE = "sortDate";
/*  25 */   public static String PROP_TOP_LEVEL = "topLevel";
/*  26 */   public static String PROP_COMMENTS_DAY = "commentsDay";
/*  27 */   public static String PROP_CONTENT_EXT = "contentExt";
/*  28 */   public static String PROP_VIEWS_DAY = "viewsDay";
/*  29 */   public static String PROP_UPS_DAY = "upsDay";
/*  30 */   public static String PROP_CHANNEL = "channel";
/*  31 */   public static String PROP_CONTENT_COUNT = "contentCount";
/*  32 */   public static String PROP_ID = "id";
/*  33 */   public static String PROP_DOWNLOADS_DAY = "downloadsDay";
/*     */ 
/*  83 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private Date sortDate;
/*     */   private Byte topLevel;
/*     */   private Boolean hasTitleImg;
/*     */   private Boolean recommend;
/*     */   private Byte status;
/*     */   private Integer viewsDay;
/*     */   private Short commentsDay;
/*     */   private Short downloadsDay;
/*     */   private Short upsDay;
/*     */   private ContentExt contentExt;
/*     */   private ContentCount contentCount;
/*     */   private ContentType type;
/*     */   private CmsSite site;
/*     */   private CmsUser user;
/*     */   private Channel channel;
/*     */   private CmsModel model;
/*     */   private Set<Channel> channels;
/*     */   private Set<CmsTopic> topics;
/*     */   private Set<CmsGroup> viewGroups;
/*     */   private List<ContentTag> tags;
/*     */   private List<ContentPicture> pictures;
/*     */   private List<ContentAttachment> attachments;
/*     */   private Set<ContentTxt> contentTxtSet;
/*     */   private Set<ContentCheck> contentCheckSet;
/*     */   private Map<String, String> attr;
/*     */   private Set<CmsUser> collectUsers;
/*     */   private Set<CmsComment> comments;
/*     */   private Set<CmsFile> files;
/*     */   private Set<CmsJobApply> jobApplys;
/*     */ 
/*     */   public BaseContent()
/*     */   {
/*  38 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContent(Integer id)
/*     */   {
/*  45 */     setId(id);
/*  46 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseContent(Integer id, CmsSite site, Date sortDate, Byte topLevel, Boolean hasTitleImg, Boolean recommend, Byte status, Integer viewsDay, Short commentsDay, Short downloadsDay, Short upsDay)
/*     */   {
/*  65 */     setId(id);
/*  66 */     setSite(site);
/*  67 */     setSortDate(sortDate);
/*  68 */     setTopLevel(topLevel);
/*  69 */     setHasTitleImg(hasTitleImg);
/*  70 */     setRecommend(recommend);
/*  71 */     setStatus(status);
/*  72 */     setViewsDay(viewsDay);
/*  73 */     setCommentsDay(commentsDay);
/*  74 */     setDownloadsDay(downloadsDay);
/*  75 */     setUpsDay(upsDay);
/*  76 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 134 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 142 */     this.id = id;
/* 143 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public Date getSortDate()
/*     */   {
/* 153 */     return this.sortDate;
/*     */   }
/*     */ 
/*     */   public void setSortDate(Date sortDate)
/*     */   {
/* 161 */     this.sortDate = sortDate;
/*     */   }
/*     */ 
/*     */   public Byte getTopLevel()
/*     */   {
/* 169 */     return this.topLevel;
/*     */   }
/*     */ 
/*     */   public void setTopLevel(Byte topLevel)
/*     */   {
/* 177 */     this.topLevel = topLevel;
/*     */   }
/*     */ 
/*     */   public Boolean getHasTitleImg()
/*     */   {
/* 185 */     return this.hasTitleImg;
/*     */   }
/*     */ 
/*     */   public void setHasTitleImg(Boolean hasTitleImg)
/*     */   {
/* 193 */     this.hasTitleImg = hasTitleImg;
/*     */   }
/*     */ 
/*     */   public Boolean getRecommend()
/*     */   {
/* 201 */     return this.recommend;
/*     */   }
/*     */ 
/*     */   public void setRecommend(Boolean recommend)
/*     */   {
/* 209 */     this.recommend = recommend;
/*     */   }
/*     */ 
/*     */   public Byte getStatus()
/*     */   {
/* 217 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Byte status)
/*     */   {
/* 225 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Integer getViewsDay()
/*     */   {
/* 233 */     return this.viewsDay;
/*     */   }
/*     */ 
/*     */   public void setViewsDay(Integer viewsDay)
/*     */   {
/* 241 */     this.viewsDay = viewsDay;
/*     */   }
/*     */ 
/*     */   public Short getCommentsDay()
/*     */   {
/* 249 */     return this.commentsDay;
/*     */   }
/*     */ 
/*     */   public void setCommentsDay(Short commentsDay)
/*     */   {
/* 257 */     this.commentsDay = commentsDay;
/*     */   }
/*     */ 
/*     */   public Set<CmsFile> getFiles()
/*     */   {
/* 263 */     return this.files;
/*     */   }
/*     */ 
/*     */   public void setFiles(Set<CmsFile> files) {
/* 267 */     this.files = files;
/*     */   }
/*     */ 
/*     */   public Set<CmsJobApply> getJobApplys() {
/* 271 */     return this.jobApplys;
/*     */   }
/*     */ 
/*     */   public void setJobApplys(Set<CmsJobApply> jobApplys)
/*     */   {
/* 276 */     this.jobApplys = jobApplys;
/*     */   }
/*     */ 
/*     */   public Short getDownloadsDay()
/*     */   {
/* 283 */     return this.downloadsDay;
/*     */   }
/*     */ 
/*     */   public void setDownloadsDay(Short downloadsDay)
/*     */   {
/* 291 */     this.downloadsDay = downloadsDay;
/*     */   }
/*     */ 
/*     */   public Short getUpsDay()
/*     */   {
/* 299 */     return this.upsDay;
/*     */   }
/*     */ 
/*     */   public void setUpsDay(Short upsDay)
/*     */   {
/* 307 */     this.upsDay = upsDay;
/*     */   }
/*     */ 
/*     */   public ContentExt getContentExt()
/*     */   {
/* 315 */     return this.contentExt;
/*     */   }
/*     */ 
/*     */   public void setContentExt(ContentExt contentExt)
/*     */   {
/* 323 */     this.contentExt = contentExt;
/*     */   }
/*     */ 
/*     */   public ContentCount getContentCount()
/*     */   {
/* 331 */     return this.contentCount;
/*     */   }
/*     */ 
/*     */   public void setContentCount(ContentCount contentCount)
/*     */   {
/* 339 */     this.contentCount = contentCount;
/*     */   }
/*     */ 
/*     */   public ContentType getType()
/*     */   {
/* 347 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(ContentType type)
/*     */   {
/* 355 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 363 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 371 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 379 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 387 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/* 395 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel)
/*     */   {
/* 403 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public CmsModel getModel() {
/* 407 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(CmsModel model) {
/* 411 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getChannels()
/*     */   {
/* 418 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public void setChannels(Set<Channel> channels)
/*     */   {
/* 426 */     this.channels = channels;
/*     */   }
/*     */ 
/*     */   public Set<CmsTopic> getTopics()
/*     */   {
/* 434 */     return this.topics;
/*     */   }
/*     */ 
/*     */   public void setTopics(Set<CmsTopic> topics)
/*     */   {
/* 442 */     this.topics = topics;
/*     */   }
/*     */ 
/*     */   public Set<CmsGroup> getViewGroups()
/*     */   {
/* 450 */     return this.viewGroups;
/*     */   }
/*     */ 
/*     */   public void setViewGroups(Set<CmsGroup> viewGroups)
/*     */   {
/* 458 */     this.viewGroups = viewGroups;
/*     */   }
/*     */ 
/*     */   public List<ContentTag> getTags()
/*     */   {
/* 466 */     return this.tags;
/*     */   }
/*     */ 
/*     */   public void setTags(List<ContentTag> tags)
/*     */   {
/* 474 */     this.tags = tags;
/*     */   }
/*     */ 
/*     */   public List<ContentPicture> getPictures()
/*     */   {
/* 482 */     return this.pictures;
/*     */   }
/*     */ 
/*     */   public void setPictures(List<ContentPicture> pictures)
/*     */   {
/* 490 */     this.pictures = pictures;
/*     */   }
/*     */ 
/*     */   public List<ContentAttachment> getAttachments()
/*     */   {
/* 498 */     return this.attachments;
/*     */   }
/*     */ 
/*     */   public void setAttachments(List<ContentAttachment> attachments)
/*     */   {
/* 506 */     this.attachments = attachments;
/*     */   }
/*     */ 
/*     */   public Set<ContentTxt> getContentTxtSet()
/*     */   {
/* 514 */     return this.contentTxtSet;
/*     */   }
/*     */ 
/*     */   public void setContentTxtSet(Set<ContentTxt> contentTxtSet)
/*     */   {
/* 522 */     this.contentTxtSet = contentTxtSet;
/*     */   }
/*     */ 
/*     */   public Set<ContentCheck> getContentCheckSet()
/*     */   {
/* 530 */     return this.contentCheckSet;
/*     */   }
/*     */ 
/*     */   public void setContentCheckSet(Set<ContentCheck> contentCheckSet)
/*     */   {
/* 538 */     this.contentCheckSet = contentCheckSet;
/*     */   }
/*     */ 
/*     */   public Map<String, String> getAttr()
/*     */   {
/* 546 */     return this.attr;
/*     */   }
/*     */ 
/*     */   public Set<CmsUser> getCollectUsers()
/*     */   {
/* 551 */     return this.collectUsers;
/*     */   }
/*     */ 
/*     */   public void setCollectUsers(Set<CmsUser> collectUsers)
/*     */   {
/* 556 */     this.collectUsers = collectUsers;
/*     */   }
/*     */ 
/*     */   public Set<CmsComment> getComments()
/*     */   {
/* 561 */     return this.comments;
/*     */   }
/*     */ 
/*     */   public void setComments(Set<CmsComment> comments)
/*     */   {
/* 566 */     this.comments = comments;
/*     */   }
/*     */ 
/*     */   public void setAttr(Map<String, String> attr)
/*     */   {
/* 574 */     this.attr = attr;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 580 */     if (obj == null) return false;
/* 581 */     if (!(obj instanceof Content)) return false;
/*     */ 
/* 583 */     Content content = (Content)obj;
/* 584 */     if ((getId() == null) || (content.getId() == null)) return false;
/* 585 */     return getId().equals(content.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 590 */     if (-2147483648 == this.hashCode) {
/* 591 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 593 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 594 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 597 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 602 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseContent
 * JD-Core Version:    0.6.0
 */