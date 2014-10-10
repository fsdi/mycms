/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsVoteTopic
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsVoteTopic";
/*  18 */   public static String PROP_MULTI_SELECT = "multiSelect";
/*  19 */   public static String PROP_RESTRICT_COOKIE = "restrictCookie";
/*  20 */   public static String PROP_SITE = "site";
/*  21 */   public static String PROP_DISABLED = "disabled";
/*  22 */   public static String PROP_DEF = "def";
/*  23 */   public static String PROP_RESTRICT_MEMBER = "restrictMember";
/*  24 */   public static String PROP_RESTRICT_IP = "restrictIp";
/*  25 */   public static String PROP_TOTAL_COUNT = "totalCount";
/*  26 */   public static String PROP_REPEATE_HOUR = "repeateHour";
/*  27 */   public static String PROP_END_TIME = "endTime";
/*  28 */   public static String PROP_START_TIME = "startTime";
/*  29 */   public static String PROP_DESCRIPTION = "description";
/*  30 */   public static String PROP_TITLE = "title";
/*  31 */   public static String PROP_ID = "id";
/*     */ 
/*  79 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String title;
/*     */   private String description;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Integer repeateHour;
/*     */   private Integer totalCount;
/*     */   private Integer multiSelect;
/*     */   private Boolean restrictMember;
/*     */   private Boolean restrictIp;
/*     */   private Boolean restrictCookie;
/*     */   private Boolean disabled;
/*     */   private Boolean def;
/*     */   private CmsSite site;
/*     */   private Set<CmsVoteItem> items;
/*     */   private Set<CmsVoteSubTopic> subtopics;
/*     */ 
/*     */   public BaseCmsVoteTopic()
/*     */   {
/*  36 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteTopic(Integer id)
/*     */   {
/*  43 */     setId(id);
/*  44 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsVoteTopic(Integer id, CmsSite site, String title, Integer totalCount, Integer multiSelect, Boolean restrictMember, Boolean restrictIp, Boolean restrictCookie, Boolean disabled, Boolean def)
/*     */   {
/*  62 */     setId(id);
/*  63 */     setSite(site);
/*  64 */     setTitle(title);
/*  65 */     setTotalCount(totalCount);
/*  66 */     setMultiSelect(multiSelect);
/*  67 */     setRestrictMember(restrictMember);
/*  68 */     setRestrictIp(restrictIp);
/*  69 */     setRestrictCookie(restrictCookie);
/*  70 */     setDisabled(disabled);
/*  71 */     setDef(def);
/*  72 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 114 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 122 */     this.id = id;
/* 123 */     this.hashCode = -2147483648;
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
/*     */   public Date getStartTime()
/*     */   {
/* 165 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 173 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 181 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 189 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Integer getRepeateHour()
/*     */   {
/* 197 */     return this.repeateHour;
/*     */   }
/*     */ 
/*     */   public void setRepeateHour(Integer repeateHour)
/*     */   {
/* 205 */     this.repeateHour = repeateHour;
/*     */   }
/*     */ 
/*     */   public Integer getTotalCount()
/*     */   {
/* 213 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(Integer totalCount)
/*     */   {
/* 221 */     this.totalCount = totalCount;
/*     */   }
/*     */ 
/*     */   public Integer getMultiSelect()
/*     */   {
/* 229 */     return this.multiSelect;
/*     */   }
/*     */ 
/*     */   public void setMultiSelect(Integer multiSelect)
/*     */   {
/* 237 */     this.multiSelect = multiSelect;
/*     */   }
/*     */ 
/*     */   public Boolean getRestrictMember()
/*     */   {
/* 245 */     return this.restrictMember;
/*     */   }
/*     */ 
/*     */   public void setRestrictMember(Boolean restrictMember)
/*     */   {
/* 253 */     this.restrictMember = restrictMember;
/*     */   }
/*     */ 
/*     */   public Boolean getRestrictIp()
/*     */   {
/* 261 */     return this.restrictIp;
/*     */   }
/*     */ 
/*     */   public void setRestrictIp(Boolean restrictIp)
/*     */   {
/* 269 */     this.restrictIp = restrictIp;
/*     */   }
/*     */ 
/*     */   public Boolean getRestrictCookie()
/*     */   {
/* 277 */     return this.restrictCookie;
/*     */   }
/*     */ 
/*     */   public void setRestrictCookie(Boolean restrictCookie)
/*     */   {
/* 285 */     this.restrictCookie = restrictCookie;
/*     */   }
/*     */ 
/*     */   public Boolean getDisabled()
/*     */   {
/* 293 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled)
/*     */   {
/* 301 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public Boolean getDef()
/*     */   {
/* 309 */     return this.def;
/*     */   }
/*     */ 
/*     */   public void setDef(Boolean def)
/*     */   {
/* 317 */     this.def = def;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 325 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 333 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Set<CmsVoteItem> getItems()
/*     */   {
/* 341 */     return this.items;
/*     */   }
/*     */ 
/*     */   public void setItems(Set<CmsVoteItem> items)
/*     */   {
/* 349 */     this.items = items;
/*     */   }
/*     */ 
/*     */   public Set<CmsVoteSubTopic> getSubtopics() {
/* 353 */     return this.subtopics;
/*     */   }
/*     */ 
/*     */   public void setSubtopics(Set<CmsVoteSubTopic> subtopics)
/*     */   {
/* 358 */     this.subtopics = subtopics;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj) {
/* 362 */     if (obj == null) return false;
/* 363 */     if (!(obj instanceof CmsVoteTopic)) return false;
/*     */ 
/* 365 */     CmsVoteTopic cmsVoteTopic = (CmsVoteTopic)obj;
/* 366 */     if ((getId() == null) || (cmsVoteTopic.getId() == null)) return false;
/* 367 */     return getId().equals(cmsVoteTopic.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 372 */     if (-2147483648 == this.hashCode) {
/* 373 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 375 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 376 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 379 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 384 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsVoteTopic
 * JD-Core Version:    0.6.0
 */