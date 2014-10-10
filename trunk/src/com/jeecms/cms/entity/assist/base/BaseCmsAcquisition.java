/*     */ package com.jeecms.cms.entity.assist.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ public abstract class BaseCmsAcquisition
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsAcquisition";
/*  18 */   public static String PROP_USER = "user";
/*  19 */   public static String PROP_LINK_START = "linkStart";
/*  20 */   public static String PROP_DESCRIPTION_END = "descriptionEnd";
/*  21 */   public static String PROP_CHANNEL = "channel";
/*  22 */   public static String PROP_DYNAMIC_START = "dynamicStart";
/*  23 */   public static String PROP_CONTENT_START = "contentStart";
/*  24 */   public static String PROP_TYPE = "type";
/*  25 */   public static String PROP_PAGINATION_END = "paginationEnd";
/*  26 */   public static String PROP_LINKSET_START = "linksetStart";
/*  27 */   public static String PROP_DYNAMIC_ADDR = "dynamicAddr";
/*  28 */   public static String PROP_LINKSET_END = "linksetEnd";
/*  29 */   public static String PROP_KEYWORDS_END = "keywordsEnd";
/*  30 */   public static String PROP_CURR_NUM = "currNum";
/*  31 */   public static String PROP_QUEUE = "queue";
/*  32 */   public static String PROP_LINK_END = "linkEnd";
/*  33 */   public static String PROP_START_TIME = "startTime";
/*  34 */   public static String PROP_PAGINATION_START = "paginationStart";
/*  35 */   public static String PROP_SITE = "site";
/*  36 */   public static String PROP_TOTAL_ITEM = "totalItem";
/*  37 */   public static String PROP_CURR_ITEM = "currItem";
/*  38 */   public static String PROP_NAME = "name";
/*  39 */   public static String PROP_STATUS = "status";
/*  40 */   public static String PROP_PAUSE_TIME = "pauseTime";
/*  41 */   public static String PROP_TITLE_START = "titleStart";
/*  42 */   public static String PROP_TITLE_END = "titleEnd";
/*  43 */   public static String PROP_CONTENT_END = "contentEnd";
/*  44 */   public static String PROP_PAGE_ENCODING = "pageEncoding";
/*  45 */   public static String PROP_ID = "id";
/*  46 */   public static String PROP_PLAN_LIST = "planList";
/*  47 */   public static String PROP_END_TIME = "endTime";
/*  48 */   public static String PROP_KEYWORDS_START = "keywordsStart";
/*  49 */   public static String PROP_DESCRIPTION_START = "descriptionStart";
/*  50 */   public static String PROP_DYNAMIC_END = "dynamicEnd";
/*     */ 
/* 104 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String name;
/*     */   private Date startTime;
/*     */   private Date endTime;
/*     */   private Integer status;
/*     */   private Integer currNum;
/*     */   private Integer currItem;
/*     */   private Integer totalItem;
/*     */   private Integer pauseTime;
/*     */   private Boolean imgAcqu;
/*     */   private String pageEncoding;
/*     */   private String planList;
/*     */   private String dynamicAddr;
/*     */   private Integer dynamicStart;
/*     */   private Integer dynamicEnd;
/*     */   private String linksetStart;
/*     */   private String linksetEnd;
/*     */   private String linkStart;
/*     */   private String linkEnd;
/*     */   private String titleStart;
/*     */   private String titleEnd;
/*     */   private String keywordsStart;
/*     */   private String keywordsEnd;
/*     */   private String descriptionStart;
/*     */   private String descriptionEnd;
/*     */   private String contentStart;
/*     */   private String contentEnd;
/*     */   private String paginationStart;
/*     */   private String paginationEnd;
/*     */   private String viewStart;
/*     */   private String viewEnd;
/*     */   private String viewIdStart;
/*     */   private String viewIdEnd;
/*     */   private String viewLink;
/*     */   private String releaseTimeStart;
/*     */   private String releaseTimeEnd;
/*     */   private String releaseTimeFormat;
/*     */   private String authorStart;
/*     */   private String authorEnd;
/*     */   private String originStart;
/*     */   private String originEnd;
/*     */   private String contentPrefix;
/*     */   private String imgPrefix;
/*     */   private Integer queue;
/*     */   private CmsUser user;
/*     */   private ContentType type;
/*     */   private CmsSite site;
/*     */   private Channel channel;
/*     */ 
/*     */   public BaseCmsAcquisition()
/*     */   {
/*  55 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisition(Integer id)
/*     */   {
/*  62 */     setId(id);
/*  63 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsAcquisition(Integer id, CmsUser user, ContentType type, CmsSite site, Channel channel, String name, Integer status, Integer currNum, Integer currItem, Integer totalItem, Integer pauseTime, String pageEncoding, Integer queue)
/*     */   {
/*  84 */     setId(id);
/*  85 */     setUser(user);
/*  86 */     setType(type);
/*  87 */     setSite(site);
/*  88 */     setChannel(channel);
/*  89 */     setName(name);
/*  90 */     setStatus(status);
/*  91 */     setCurrNum(currNum);
/*  92 */     setCurrItem(currItem);
/*  93 */     setTotalItem(totalItem);
/*  94 */     setPauseTime(pauseTime);
/*  95 */     setPageEncoding(pageEncoding);
/*  96 */     setQueue(queue);
/*  97 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 169 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 177 */     this.id = id;
/* 178 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 188 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 196 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Date getStartTime()
/*     */   {
/* 204 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(Date startTime)
/*     */   {
/* 212 */     this.startTime = startTime;
/*     */   }
/*     */ 
/*     */   public Date getEndTime()
/*     */   {
/* 220 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   public void setEndTime(Date endTime)
/*     */   {
/* 228 */     this.endTime = endTime;
/*     */   }
/*     */ 
/*     */   public Integer getStatus()
/*     */   {
/* 236 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status)
/*     */   {
/* 244 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Integer getCurrNum()
/*     */   {
/* 252 */     return this.currNum;
/*     */   }
/*     */ 
/*     */   public void setCurrNum(Integer currNum)
/*     */   {
/* 260 */     this.currNum = currNum;
/*     */   }
/*     */ 
/*     */   public Integer getCurrItem()
/*     */   {
/* 268 */     return this.currItem;
/*     */   }
/*     */ 
/*     */   public void setCurrItem(Integer currItem)
/*     */   {
/* 276 */     this.currItem = currItem;
/*     */   }
/*     */ 
/*     */   public Integer getTotalItem()
/*     */   {
/* 284 */     return this.totalItem;
/*     */   }
/*     */ 
/*     */   public void setTotalItem(Integer totalItem)
/*     */   {
/* 292 */     this.totalItem = totalItem;
/*     */   }
/*     */ 
/*     */   public Integer getPauseTime()
/*     */   {
/* 300 */     return this.pauseTime;
/*     */   }
/*     */ 
/*     */   public void setPauseTime(Integer pauseTime)
/*     */   {
/* 308 */     this.pauseTime = pauseTime;
/*     */   }
/*     */ 
/*     */   public Boolean getImgAcqu()
/*     */   {
/* 314 */     return this.imgAcqu;
/*     */   }
/*     */ 
/*     */   public void setImgAcqu(Boolean imgAcqu) {
/* 318 */     this.imgAcqu = imgAcqu;
/*     */   }
/*     */ 
/*     */   public String getPageEncoding()
/*     */   {
/* 325 */     return this.pageEncoding;
/*     */   }
/*     */ 
/*     */   public void setPageEncoding(String pageEncoding)
/*     */   {
/* 333 */     this.pageEncoding = pageEncoding;
/*     */   }
/*     */ 
/*     */   public String getPlanList()
/*     */   {
/* 341 */     return this.planList;
/*     */   }
/*     */ 
/*     */   public void setPlanList(String planList)
/*     */   {
/* 349 */     this.planList = planList;
/*     */   }
/*     */ 
/*     */   public String getDynamicAddr()
/*     */   {
/* 357 */     return this.dynamicAddr;
/*     */   }
/*     */ 
/*     */   public void setDynamicAddr(String dynamicAddr)
/*     */   {
/* 365 */     this.dynamicAddr = dynamicAddr;
/*     */   }
/*     */ 
/*     */   public Integer getDynamicStart()
/*     */   {
/* 373 */     return this.dynamicStart;
/*     */   }
/*     */ 
/*     */   public void setDynamicStart(Integer dynamicStart)
/*     */   {
/* 381 */     this.dynamicStart = dynamicStart;
/*     */   }
/*     */ 
/*     */   public Integer getDynamicEnd()
/*     */   {
/* 389 */     return this.dynamicEnd;
/*     */   }
/*     */ 
/*     */   public void setDynamicEnd(Integer dynamicEnd)
/*     */   {
/* 397 */     this.dynamicEnd = dynamicEnd;
/*     */   }
/*     */ 
/*     */   public String getLinksetStart()
/*     */   {
/* 405 */     return this.linksetStart;
/*     */   }
/*     */ 
/*     */   public void setLinksetStart(String linksetStart)
/*     */   {
/* 413 */     this.linksetStart = linksetStart;
/*     */   }
/*     */ 
/*     */   public String getLinksetEnd()
/*     */   {
/* 421 */     return this.linksetEnd;
/*     */   }
/*     */ 
/*     */   public void setLinksetEnd(String linksetEnd)
/*     */   {
/* 429 */     this.linksetEnd = linksetEnd;
/*     */   }
/*     */ 
/*     */   public String getLinkStart()
/*     */   {
/* 437 */     return this.linkStart;
/*     */   }
/*     */ 
/*     */   public void setLinkStart(String linkStart)
/*     */   {
/* 445 */     this.linkStart = linkStart;
/*     */   }
/*     */ 
/*     */   public String getLinkEnd()
/*     */   {
/* 453 */     return this.linkEnd;
/*     */   }
/*     */ 
/*     */   public void setLinkEnd(String linkEnd)
/*     */   {
/* 461 */     this.linkEnd = linkEnd;
/*     */   }
/*     */ 
/*     */   public String getTitleStart()
/*     */   {
/* 469 */     return this.titleStart;
/*     */   }
/*     */ 
/*     */   public void setTitleStart(String titleStart)
/*     */   {
/* 477 */     this.titleStart = titleStart;
/*     */   }
/*     */ 
/*     */   public String getTitleEnd()
/*     */   {
/* 485 */     return this.titleEnd;
/*     */   }
/*     */ 
/*     */   public void setTitleEnd(String titleEnd)
/*     */   {
/* 493 */     this.titleEnd = titleEnd;
/*     */   }
/*     */ 
/*     */   public String getKeywordsStart()
/*     */   {
/* 501 */     return this.keywordsStart;
/*     */   }
/*     */ 
/*     */   public void setKeywordsStart(String keywordsStart)
/*     */   {
/* 509 */     this.keywordsStart = keywordsStart;
/*     */   }
/*     */ 
/*     */   public String getKeywordsEnd()
/*     */   {
/* 517 */     return this.keywordsEnd;
/*     */   }
/*     */ 
/*     */   public void setKeywordsEnd(String keywordsEnd)
/*     */   {
/* 525 */     this.keywordsEnd = keywordsEnd;
/*     */   }
/*     */ 
/*     */   public String getDescriptionStart()
/*     */   {
/* 533 */     return this.descriptionStart;
/*     */   }
/*     */ 
/*     */   public void setDescriptionStart(String descriptionStart)
/*     */   {
/* 541 */     this.descriptionStart = descriptionStart;
/*     */   }
/*     */ 
/*     */   public String getDescriptionEnd()
/*     */   {
/* 549 */     return this.descriptionEnd;
/*     */   }
/*     */ 
/*     */   public void setDescriptionEnd(String descriptionEnd)
/*     */   {
/* 557 */     this.descriptionEnd = descriptionEnd;
/*     */   }
/*     */ 
/*     */   public String getContentStart()
/*     */   {
/* 565 */     return this.contentStart;
/*     */   }
/*     */ 
/*     */   public void setContentStart(String contentStart)
/*     */   {
/* 573 */     this.contentStart = contentStart;
/*     */   }
/*     */ 
/*     */   public String getContentEnd()
/*     */   {
/* 581 */     return this.contentEnd;
/*     */   }
/*     */ 
/*     */   public void setContentEnd(String contentEnd)
/*     */   {
/* 589 */     this.contentEnd = contentEnd;
/*     */   }
/*     */ 
/*     */   public String getPaginationStart()
/*     */   {
/* 597 */     return this.paginationStart;
/*     */   }
/*     */ 
/*     */   public void setPaginationStart(String paginationStart)
/*     */   {
/* 605 */     this.paginationStart = paginationStart;
/*     */   }
/*     */ 
/*     */   public String getPaginationEnd()
/*     */   {
/* 613 */     return this.paginationEnd;
/*     */   }
/*     */ 
/*     */   public void setPaginationEnd(String paginationEnd)
/*     */   {
/* 621 */     this.paginationEnd = paginationEnd;
/*     */   }
/*     */ 
/*     */   public String getViewStart()
/*     */   {
/* 627 */     return this.viewStart;
/*     */   }
/*     */ 
/*     */   public void setViewStart(String viewStart) {
/* 631 */     this.viewStart = viewStart;
/*     */   }
/*     */ 
/*     */   public String getViewEnd() {
/* 635 */     return this.viewEnd;
/*     */   }
/*     */ 
/*     */   public void setViewEnd(String viewEnd) {
/* 639 */     this.viewEnd = viewEnd;
/*     */   }
/*     */ 
/*     */   public String getViewIdStart() {
/* 643 */     return this.viewIdStart;
/*     */   }
/*     */ 
/*     */   public void setViewIdStart(String viewIdStart) {
/* 647 */     this.viewIdStart = viewIdStart;
/*     */   }
/*     */ 
/*     */   public String getViewIdEnd() {
/* 651 */     return this.viewIdEnd;
/*     */   }
/*     */ 
/*     */   public void setViewIdEnd(String viewIdEnd) {
/* 655 */     this.viewIdEnd = viewIdEnd;
/*     */   }
/*     */ 
/*     */   public String getViewLink() {
/* 659 */     return this.viewLink;
/*     */   }
/*     */ 
/*     */   public void setViewLink(String viewLink) {
/* 663 */     this.viewLink = viewLink;
/*     */   }
/*     */ 
/*     */   public String getReleaseTimeStart() {
/* 667 */     return this.releaseTimeStart;
/*     */   }
/*     */ 
/*     */   public void setReleaseTimeStart(String releaseTimeStart) {
/* 671 */     this.releaseTimeStart = releaseTimeStart;
/*     */   }
/*     */ 
/*     */   public String getReleaseTimeEnd() {
/* 675 */     return this.releaseTimeEnd;
/*     */   }
/*     */ 
/*     */   public void setReleaseTimeEnd(String releaseTimeEnd) {
/* 679 */     this.releaseTimeEnd = releaseTimeEnd;
/*     */   }
/*     */ 
/*     */   public String getReleaseTimeFormat()
/*     */   {
/* 684 */     return this.releaseTimeFormat;
/*     */   }
/*     */ 
/*     */   public void setReleaseTimeFormat(String releaseTimeFormat) {
/* 688 */     this.releaseTimeFormat = releaseTimeFormat;
/*     */   }
/*     */ 
/*     */   public String getAuthorStart() {
/* 692 */     return this.authorStart;
/*     */   }
/*     */ 
/*     */   public void setAuthorStart(String authorStart) {
/* 696 */     this.authorStart = authorStart;
/*     */   }
/*     */ 
/*     */   public String getAuthorEnd() {
/* 700 */     return this.authorEnd;
/*     */   }
/*     */ 
/*     */   public void setAuthorEnd(String authorEnd) {
/* 704 */     this.authorEnd = authorEnd;
/*     */   }
/*     */ 
/*     */   public String getOriginStart() {
/* 708 */     return this.originStart;
/*     */   }
/*     */ 
/*     */   public void setOriginStart(String originStart) {
/* 712 */     this.originStart = originStart;
/*     */   }
/*     */ 
/*     */   public String getOriginEnd() {
/* 716 */     return this.originEnd;
/*     */   }
/*     */ 
/*     */   public void setOriginEnd(String originEnd) {
/* 720 */     this.originEnd = originEnd;
/*     */   }
/*     */ 
/*     */   public String getContentPrefix() {
/* 724 */     return this.contentPrefix;
/*     */   }
/*     */ 
/*     */   public void setContentPrefix(String contentPrefix) {
/* 728 */     this.contentPrefix = contentPrefix;
/*     */   }
/*     */ 
/*     */   public String getImgPrefix() {
/* 732 */     return this.imgPrefix;
/*     */   }
/*     */ 
/*     */   public void setImgPrefix(String imgPrefix) {
/* 736 */     this.imgPrefix = imgPrefix;
/*     */   }
/*     */ 
/*     */   public Integer getQueue()
/*     */   {
/* 743 */     return this.queue;
/*     */   }
/*     */ 
/*     */   public void setQueue(Integer queue)
/*     */   {
/* 751 */     this.queue = queue;
/*     */   }
/*     */ 
/*     */   public CmsUser getUser()
/*     */   {
/* 759 */     return this.user;
/*     */   }
/*     */ 
/*     */   public void setUser(CmsUser user)
/*     */   {
/* 767 */     this.user = user;
/*     */   }
/*     */ 
/*     */   public ContentType getType()
/*     */   {
/* 775 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(ContentType type)
/*     */   {
/* 783 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public CmsSite getSite()
/*     */   {
/* 791 */     return this.site;
/*     */   }
/*     */ 
/*     */   public void setSite(CmsSite site)
/*     */   {
/* 799 */     this.site = site;
/*     */   }
/*     */ 
/*     */   public Channel getChannel()
/*     */   {
/* 807 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel)
/*     */   {
/* 815 */     this.channel = channel;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 821 */     if (obj == null) return false;
/* 822 */     if (!(obj instanceof CmsAcquisition)) return false;
/*     */ 
/* 824 */     CmsAcquisition cmsAcquisition = (CmsAcquisition)obj;
/* 825 */     if ((getId() == null) || (cmsAcquisition.getId() == null)) return false;
/* 826 */     return getId().equals(cmsAcquisition.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 831 */     if (-2147483648 == this.hashCode) {
/* 832 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 834 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 835 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 838 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 843 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.base.BaseCmsAcquisition
 * JD-Core Version:    0.6.0
 */