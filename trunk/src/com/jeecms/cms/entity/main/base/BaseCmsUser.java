/*     */ package com.jeecms.cms.entity.main.base;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.assist.CmsMessage;
/*     */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsRole;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.entity.main.CmsUserResume;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import java.io.Serializable;
/*     */ import java.util.Set;
/*     */ 
/*     */ public abstract class BaseCmsUser
/*     */   implements Serializable
/*     */ {
/*  17 */   public static String REF = "CmsUser";
/*  18 */   public static String PROP_REGISTER_TIME = "registerTime";
/*  19 */   public static String PROP_LOGIN_COUNT = "loginCount";
/*  20 */   public static String PROP_SELF_ADMIN = "selfAdmin";
/*  21 */   public static String PROP_UPLOAD_TOTAL = "uploadTotal";
/*  22 */   public static String PROP_LAST_LOGIN_IP = "lastLoginIp";
/*  23 */   public static String PROP_DISABLED = "disabled";
/*  24 */   public static String PROP_LAST_LOGIN_TIME = "lastLoginTime";
/*  25 */   public static String PROP_UPLOAD_DATE = "uploadDate";
/*  26 */   public static String PROP_GROUP = "group";
/*  27 */   public static String PROP_EMAIL = "email";
/*  28 */   public static String PROP_UPLOAD_SIZE = "uploadSize";
/*  29 */   public static String PROP_RANK = "rank";
/*  30 */   public static String PROP_VIEWONLY_ADMIN = "viewonlyAdmin";
/*  31 */   public static String PROP_ADMIN = "admin";
/*  32 */   public static String PROP_ID = "id";
/*  33 */   public static String PROP_REGISTER_IP = "registerIp";
/*  34 */   public static String PROP_USERNAME = "username";
/*     */ 
/*  88 */   private int hashCode = -2147483648;
/*     */   private Integer id;
/*     */   private String username;
/*     */   private String email;
/*     */   private java.util.Date registerTime;
/*     */   private String registerIp;
/*     */   private java.util.Date lastLoginTime;
/*     */   private String lastLoginIp;
/*     */   private Integer loginCount;
/*     */   private Integer rank;
/*     */   private Long uploadTotal;
/*     */   private Integer uploadSize;
/*     */   private java.sql.Date uploadDate;
/*     */   private Boolean admin;
/*     */   private Boolean viewonlyAdmin;
/*     */   private Boolean selfAdmin;
/*     */   private Boolean disabled;
/*     */   private CmsGroup group;
/*     */   private Set<CmsUserExt> userExtSet;
/*     */   private Set<CmsUserSite> userSites;
/*     */   private Set<CmsRole> roles;
/*     */   private Set<Channel> channels;
/*     */   private Set<Content> collectContents;
/*     */   private Set<CmsJobApply> jobApplys;
/*     */   private Set<CmsUserResume> userResumeSet;
/*     */   private Set<CmsMessage> sendMessages;
/*     */   private Set<CmsMessage> receivMessages;
/*     */   private Set<CmsReceiverMessage> sendReceiverMessages;
/*     */   private Set<CmsReceiverMessage> receivReceiverMessages;
/*     */ 
/*     */   public BaseCmsUser()
/*     */   {
/*  39 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUser(Integer id)
/*     */   {
/*  46 */     setId(id);
/*  47 */     initialize();
/*     */   }
/*     */ 
/*     */   public BaseCmsUser(Integer id, CmsGroup group, String username, java.util.Date registerTime, String registerIp, Integer loginCount, Integer rank, Long uploadTotal, Integer uploadSize, Boolean admin, Boolean viewonlyAdmin, Boolean selfAdmin, Boolean disabled)
/*     */   {
/*  68 */     setId(id);
/*  69 */     setGroup(group);
/*  70 */     setUsername(username);
/*  71 */     setRegisterTime(registerTime);
/*  72 */     setRegisterIp(registerIp);
/*  73 */     setLoginCount(loginCount);
/*  74 */     setRank(rank);
/*  75 */     setUploadTotal(uploadTotal);
/*  76 */     setUploadSize(uploadSize);
/*  77 */     setAdmin(admin);
/*  78 */     setViewonlyAdmin(viewonlyAdmin);
/*  79 */     setSelfAdmin(selfAdmin);
/*  80 */     setDisabled(disabled);
/*  81 */     initialize();
/*     */   }
/*     */ 
/*     */   protected void initialize()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/* 136 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id)
/*     */   {
/* 144 */     this.id = id;
/* 145 */     this.hashCode = -2147483648;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/* 155 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/* 163 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 171 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 179 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public java.util.Date getRegisterTime()
/*     */   {
/* 187 */     return this.registerTime;
/*     */   }
/*     */ 
/*     */   public void setRegisterTime(java.util.Date registerTime)
/*     */   {
/* 195 */     this.registerTime = registerTime;
/*     */   }
/*     */ 
/*     */   public String getRegisterIp()
/*     */   {
/* 203 */     return this.registerIp;
/*     */   }
/*     */ 
/*     */   public void setRegisterIp(String registerIp)
/*     */   {
/* 211 */     this.registerIp = registerIp;
/*     */   }
/*     */ 
/*     */   public java.util.Date getLastLoginTime()
/*     */   {
/* 219 */     return this.lastLoginTime;
/*     */   }
/*     */ 
/*     */   public void setLastLoginTime(java.util.Date lastLoginTime)
/*     */   {
/* 227 */     this.lastLoginTime = lastLoginTime;
/*     */   }
/*     */ 
/*     */   public String getLastLoginIp()
/*     */   {
/* 235 */     return this.lastLoginIp;
/*     */   }
/*     */ 
/*     */   public void setLastLoginIp(String lastLoginIp)
/*     */   {
/* 243 */     this.lastLoginIp = lastLoginIp;
/*     */   }
/*     */ 
/*     */   public Integer getLoginCount()
/*     */   {
/* 251 */     return this.loginCount;
/*     */   }
/*     */ 
/*     */   public void setLoginCount(Integer loginCount)
/*     */   {
/* 259 */     this.loginCount = loginCount;
/*     */   }
/*     */ 
/*     */   public Integer getRank()
/*     */   {
/* 267 */     return this.rank;
/*     */   }
/*     */ 
/*     */   public void setRank(Integer rank)
/*     */   {
/* 275 */     this.rank = rank;
/*     */   }
/*     */ 
/*     */   public Long getUploadTotal()
/*     */   {
/* 283 */     return this.uploadTotal;
/*     */   }
/*     */ 
/*     */   public void setUploadTotal(Long uploadTotal)
/*     */   {
/* 291 */     this.uploadTotal = uploadTotal;
/*     */   }
/*     */ 
/*     */   public Integer getUploadSize()
/*     */   {
/* 299 */     return this.uploadSize;
/*     */   }
/*     */ 
/*     */   public void setUploadSize(Integer uploadSize)
/*     */   {
/* 307 */     this.uploadSize = uploadSize;
/*     */   }
/*     */ 
/*     */   public java.sql.Date getUploadDate()
/*     */   {
/* 314 */     return this.uploadDate;
/*     */   }
/*     */ 
/*     */   public void setUploadDate(java.sql.Date uploadDate)
/*     */   {
/* 322 */     this.uploadDate = uploadDate;
/*     */   }
/*     */ 
/*     */   public Boolean getAdmin()
/*     */   {
/* 330 */     return this.admin;
/*     */   }
/*     */ 
/*     */   public void setAdmin(Boolean admin)
/*     */   {
/* 338 */     this.admin = admin;
/*     */   }
/*     */ 
/*     */   public Boolean getViewonlyAdmin()
/*     */   {
/* 346 */     return this.viewonlyAdmin;
/*     */   }
/*     */ 
/*     */   public void setViewonlyAdmin(Boolean viewonlyAdmin)
/*     */   {
/* 354 */     this.viewonlyAdmin = viewonlyAdmin;
/*     */   }
/*     */ 
/*     */   public Boolean getSelfAdmin()
/*     */   {
/* 362 */     return this.selfAdmin;
/*     */   }
/*     */ 
/*     */   public void setSelfAdmin(Boolean selfAdmin)
/*     */   {
/* 370 */     this.selfAdmin = selfAdmin;
/*     */   }
/*     */ 
/*     */   public Boolean getDisabled()
/*     */   {
/* 378 */     return this.disabled;
/*     */   }
/*     */ 
/*     */   public void setDisabled(Boolean disabled)
/*     */   {
/* 386 */     this.disabled = disabled;
/*     */   }
/*     */ 
/*     */   public CmsGroup getGroup()
/*     */   {
/* 392 */     return this.group;
/*     */   }
/*     */ 
/*     */   public void setGroup(CmsGroup group)
/*     */   {
/* 400 */     this.group = group;
/*     */   }
/*     */ 
/*     */   public Set<CmsUserExt> getUserExtSet()
/*     */   {
/* 408 */     return this.userExtSet;
/*     */   }
/*     */ 
/*     */   public void setUserExtSet(Set<CmsUserExt> userExtSet)
/*     */   {
/* 416 */     this.userExtSet = userExtSet;
/*     */   }
/*     */ 
/*     */   public Set<CmsUserSite> getUserSites()
/*     */   {
/* 424 */     return this.userSites;
/*     */   }
/*     */ 
/*     */   public void setUserSites(Set<CmsUserSite> userSites)
/*     */   {
/* 432 */     this.userSites = userSites;
/*     */   }
/*     */ 
/*     */   public Set<CmsRole> getRoles()
/*     */   {
/* 440 */     return this.roles;
/*     */   }
/*     */ 
/*     */   public void setRoles(Set<CmsRole> roles)
/*     */   {
/* 448 */     this.roles = roles;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getChannels()
/*     */   {
/* 456 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public void setChannels(Set<Channel> channels)
/*     */   {
/* 464 */     this.channels = channels;
/*     */   }
/*     */ 
/*     */   public Set<Content> getCollectContents()
/*     */   {
/* 469 */     return this.collectContents;
/*     */   }
/*     */ 
/*     */   public void setCollectContents(Set<Content> collectContents)
/*     */   {
/* 474 */     this.collectContents = collectContents;
/*     */   }
/*     */ 
/*     */   public Set<CmsJobApply> getJobApplys() {
/* 478 */     return this.jobApplys;
/*     */   }
/*     */ 
/*     */   public void setJobApplys(Set<CmsJobApply> jobApplys)
/*     */   {
/* 483 */     this.jobApplys = jobApplys;
/*     */   }
/*     */ 
/*     */   public Set<CmsUserResume> getUserResumeSet() {
/* 487 */     return this.userResumeSet;
/*     */   }
/*     */ 
/*     */   public void setUserResumeSet(Set<CmsUserResume> userResumeSet)
/*     */   {
/* 492 */     this.userResumeSet = userResumeSet;
/*     */   }
/*     */ 
/*     */   public Set<CmsMessage> getSendMessages() {
/* 496 */     return this.sendMessages;
/*     */   }
/*     */ 
/*     */   public void setSendMessages(Set<CmsMessage> sendMessages)
/*     */   {
/* 501 */     this.sendMessages = sendMessages;
/*     */   }
/*     */ 
/*     */   public Set<CmsMessage> getReceivMessages() {
/* 505 */     return this.receivMessages;
/*     */   }
/*     */ 
/*     */   public void setReceivMessages(Set<CmsMessage> receivMessages)
/*     */   {
/* 510 */     this.receivMessages = receivMessages;
/*     */   }
/*     */ 
/*     */   public Set<CmsReceiverMessage> getSendReceiverMessages() {
/* 514 */     return this.sendReceiverMessages;
/*     */   }
/*     */ 
/*     */   public void setSendReceiverMessages(Set<CmsReceiverMessage> sendReceiverMessages)
/*     */   {
/* 519 */     this.sendReceiverMessages = sendReceiverMessages;
/*     */   }
/*     */ 
/*     */   public Set<CmsReceiverMessage> getReceivReceiverMessages() {
/* 523 */     return this.receivReceiverMessages;
/*     */   }
/*     */ 
/*     */   public void setReceivReceiverMessages(Set<CmsReceiverMessage> receivReceiverMessages)
/*     */   {
/* 528 */     this.receivReceiverMessages = receivReceiverMessages;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj) {
/* 532 */     if (obj == null) return false;
/* 533 */     if (!(obj instanceof CmsUser)) return false;
/*     */ 
/* 535 */     CmsUser cmsUser = (CmsUser)obj;
/* 536 */     if ((getId() == null) || (cmsUser.getId() == null)) return false;
/* 537 */     return getId().equals(cmsUser.getId());
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 542 */     if (-2147483648 == this.hashCode) {
/* 543 */       if (getId() == null) return super.hashCode();
/*     */ 
/* 545 */       String hashStr = getClass().getName() + ":" + getId().hashCode();
/* 546 */       this.hashCode = hashStr.hashCode();
/*     */     }
/*     */ 
/* 549 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 554 */     return super.toString();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.base.BaseCmsUser
 * JD-Core Version:    0.6.0
 */