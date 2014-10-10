/*     */ package com.jeecms.cms.entity.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.main.base.BaseCmsUser;
/*     */ import com.jeecms.common.hibernate3.PriorityInterface;
/*     */ import com.jeecms.common.util.DateUtils;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CmsUser extends BaseCmsUser
/*     */   implements PriorityInterface
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public Byte getCheckStep(Integer siteId)
/*     */   {
/*  19 */     CmsUserSite us = getUserSite(siteId);
/*  20 */     if (us != null) {
/*  21 */       return getUserSite(siteId).getCheckStep();
/*     */     }
/*  23 */     return null;
/*     */   }
/*     */ 
/*     */   public String getRealname()
/*     */   {
/*  28 */     CmsUserExt ext = getUserExt();
/*  29 */     if (ext != null) {
/*  30 */       return ext.getRealname();
/*     */     }
/*  32 */     return null;
/*     */   }
/*     */ 
/*     */   public Boolean getGender()
/*     */   {
/*  37 */     CmsUserExt ext = getUserExt();
/*  38 */     if (ext != null) {
/*  39 */       return ext.getGender();
/*     */     }
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */   public java.util.Date getBirthday()
/*     */   {
/*  46 */     CmsUserExt ext = getUserExt();
/*  47 */     if (ext != null) {
/*  48 */       return ext.getBirthday();
/*     */     }
/*  50 */     return null;
/*     */   }
/*     */ 
/*     */   public String getIntro()
/*     */   {
/*  55 */     CmsUserExt ext = getUserExt();
/*  56 */     if (ext != null) {
/*  57 */       return ext.getIntro();
/*     */     }
/*  59 */     return null;
/*     */   }
/*     */ 
/*     */   public String getComefrom()
/*     */   {
/*  64 */     CmsUserExt ext = getUserExt();
/*  65 */     if (ext != null) {
/*  66 */       return ext.getComefrom();
/*     */     }
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */   public String getQq()
/*     */   {
/*  73 */     CmsUserExt ext = getUserExt();
/*  74 */     if (ext != null) {
/*  75 */       return ext.getQq();
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   public String getMsn()
/*     */   {
/*  82 */     CmsUserExt ext = getUserExt();
/*  83 */     if (ext != null) {
/*  84 */       return ext.getMsn();
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/*  91 */     CmsUserExt ext = getUserExt();
/*  92 */     if (ext != null) {
/*  93 */       return ext.getPhone();
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 100 */     CmsUserExt ext = getUserExt();
/* 101 */     if (ext != null) {
/* 102 */       return ext.getMobile();
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   public String getUserImg() {
/* 108 */     CmsUserExt ext = getUserExt();
/* 109 */     if (ext != null) {
/* 110 */       return ext.getUserImg();
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */   public String getUserSignature() {
/* 116 */     CmsUserExt ext = getUserExt();
/* 117 */     if (ext != null) {
/* 118 */       return ext.getUserSignature();
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public CmsUserExt getUserExt()
/*     */   {
/* 125 */     Set set = getUserExtSet();
/* 126 */     if ((set != null) && (set.size() > 0)) {
/* 127 */       return (CmsUserExt)set.iterator().next();
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public CmsUserSite getUserSite(Integer siteId)
/*     */   {
/* 134 */     Set<CmsUserSite> set = getUserSites();
/* 135 */     for (CmsUserSite us : set) {
/* 136 */       if (us.getSite().getId().equals(siteId)) {
/* 137 */         return us;
/*     */       }
/*     */     }
/* 140 */     return null;
/*     */   }
/*     */ 
/*     */   public CmsUserResume getUserResume() {
/* 144 */     Set set = getUserResumeSet();
/* 145 */     if ((set != null) && (set.size() > 0)) {
/* 146 */       return (CmsUserResume)set.iterator().next();
/*     */     }
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */   public Set<Channel> getChannels(Integer siteId)
/*     */   {
/* 153 */     Set<Channel> set = getChannels();
/* 154 */     Set results = new HashSet();
/* 155 */     for (Channel c : set) {
/* 156 */       if (c.getSite().getId().equals(siteId)) {
/* 157 */         results.add(c);
/*     */       }
/*     */     }
/* 160 */     return results;
/*     */   }
/*     */ 
/*     */   public Integer[] getChannelIds() {
/* 164 */     Set channels = getChannels();
/* 165 */     return Channel.fetchIds(channels);
/*     */   }
/*     */ 
/*     */   public Set<Integer> getChannelIds(Integer siteId) {
/* 169 */     Set<Channel> channels = getChannels();
/* 170 */     Set ids = new HashSet();
/* 171 */     for (Channel c : channels) {
/* 172 */       if (c.getSite().getId().equals(siteId)) {
/* 173 */         ids.add(c.getId());
/*     */       }
/*     */     }
/* 176 */     return ids;
/*     */   }
/*     */ 
/*     */   public Integer[] getRoleIds() {
/* 180 */     Set roles = getRoles();
/* 181 */     return CmsRole.fetchIds(roles);
/*     */   }
/*     */ 
/*     */   public Integer[] getSiteIds() {
/* 185 */     Set sites = getSites();
/* 186 */     return CmsSite.fetchIds(sites);
/*     */   }
/*     */ 
/*     */   public void addToRoles(CmsRole role) {
/* 190 */     if (role == null) {
/* 191 */       return;
/*     */     }
/* 193 */     Set set = getRoles();
/* 194 */     if (set == null) {
/* 195 */       set = new HashSet();
/* 196 */       setRoles(set);
/*     */     }
/* 198 */     set.add(role);
/*     */   }
/*     */ 
/*     */   public void addToChannels(Channel channel) {
/* 202 */     if (channel == null) {
/* 203 */       return;
/*     */     }
/* 205 */     Set set = getChannels();
/* 206 */     if (set == null) {
/* 207 */       set = new HashSet();
/* 208 */       setChannels(set);
/*     */     }
/* 210 */     set.add(channel);
/*     */   }
/*     */ 
/*     */   public void addToCollection(Content content) {
/* 214 */     if (content == null) {
/* 215 */       return;
/*     */     }
/* 217 */     Set set = getCollectContents();
/* 218 */     if (set == null) {
/* 219 */       set = new HashSet();
/* 220 */       setCollectContents(set);
/*     */     }
/* 222 */     set.add(content);
/*     */   }
/*     */   public void delFromCollection(Content content) {
/* 225 */     if (content == null) {
/* 226 */       return;
/*     */     }
/* 228 */     Set set = getCollectContents();
/* 229 */     if (set == null) {
/* 230 */       return;
/*     */     }
/* 232 */     set.remove(content);
/*     */   }
/*     */ 
/*     */   public void clearCollection()
/*     */   {
/* 237 */     getCollectContents().clear();
/*     */   }
/*     */ 
/*     */   public Set<CmsSite> getSites()
/*     */   {
/* 242 */     Set<CmsUserSite> userSites = getUserSites();
/* 243 */     Set sites = new HashSet(userSites.size());
/* 244 */     for (CmsUserSite us : userSites) {
/* 245 */       sites.add(us.getSite());
/*     */     }
/* 247 */     return sites;
/*     */   }
/*     */ 
/*     */   public Set<Content> getApplyContent() {
/* 251 */     Set<CmsJobApply> jobApplys = getJobApplys();
/* 252 */     Set contents = new HashSet(jobApplys.size());
/* 253 */     for (CmsJobApply apply : jobApplys) {
/* 254 */       contents.add(apply.getContent());
/*     */     }
/* 256 */     return contents;
/*     */   }
/*     */ 
/*     */   public boolean hasApplyToday(Integer contentId) {
/* 260 */     java.util.Date now = Calendar.getInstance().getTime();
/* 261 */     Set<CmsJobApply> jobApplys = getJobApplys();
/* 262 */     for (CmsJobApply apply : jobApplys) {
/* 263 */       if ((DateUtils.isInDate(now, apply.getApplyTime())) && (apply.getContent().getId().equals(contentId))) {
/* 264 */         return true;
/*     */       }
/*     */     }
/* 267 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isSuper() {
/* 271 */     Set roles = getRoles();
/* 272 */     if (roles == null) {
/* 273 */       return false;
/*     */     }
/* 275 */     for (CmsRole role : getRoles()) {
/* 276 */       if (role.getSuper().booleanValue()) {
/* 277 */         return true;
/*     */       }
/*     */     }
/* 280 */     return false;
/*     */   }
/*     */ 
/*     */   public Set<String> getPerms() {
/* 284 */     Set roles = getRoles();
/* 285 */     if (roles == null) {
/* 286 */       return null;
/*     */     }
/* 288 */     Set allPerms = new HashSet();
/* 289 */     for (CmsRole role : getRoles()) {
/* 290 */       allPerms.addAll(role.getPerms());
/*     */     }
/* 292 */     return allPerms;
/*     */   }
/*     */ 
/*     */   public boolean isAllowPerDay(int size)
/*     */   {
/* 302 */     int allowPerDay = getGroup().getAllowPerDay().intValue();
/* 303 */     if (allowPerDay == 0) {
/* 304 */       return true;
/*     */     }
/* 306 */     if ((getUploadDate() != null) && 
/* 307 */       (isToday(getUploadDate()))) {
/* 308 */       size += getUploadSize().intValue();
/*     */     }
/*     */ 
/* 311 */     return allowPerDay >= size;
/*     */   }
/*     */ 
/*     */   public boolean isAllowMaxFile(int size)
/*     */   {
/* 322 */     int allowPerFile = getGroup().getAllowMaxFile().intValue();
/* 323 */     if (allowPerFile == 0) {
/* 324 */       return true;
/*     */     }
/* 326 */     return allowPerFile >= size;
/*     */   }
/*     */ 
/*     */   public boolean isAllowSuffix(String ext)
/*     */   {
/* 337 */     return getGroup().isAllowSuffix(ext);
/*     */   }
/*     */ 
/*     */   public void forMember(UnifiedUser u) {
/* 341 */     forUser(u);
/* 342 */     setAdmin(Boolean.valueOf(false));
/* 343 */     setRank(Integer.valueOf(0));
/* 344 */     setViewonlyAdmin(Boolean.valueOf(false));
/* 345 */     setSelfAdmin(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public void forAdmin(UnifiedUser u, boolean viewonly, boolean selfAdmin, int rank)
/*     */   {
/* 350 */     forUser(u);
/* 351 */     setAdmin(Boolean.valueOf(true));
/* 352 */     setRank(Integer.valueOf(rank));
/* 353 */     setViewonlyAdmin(Boolean.valueOf(viewonly));
/* 354 */     setSelfAdmin(Boolean.valueOf(selfAdmin));
/*     */   }
/*     */ 
/*     */   public void forUser(UnifiedUser u) {
/* 358 */     setDisabled(Boolean.valueOf(false));
/* 359 */     setId(u.getId());
/* 360 */     setUsername(u.getUsername());
/* 361 */     setEmail(u.getEmail());
/* 362 */     setRegisterIp(u.getRegisterIp());
/* 363 */     setRegisterTime(u.getRegisterTime());
/* 364 */     setLastLoginIp(u.getLastLoginIp());
/* 365 */     setLastLoginTime(u.getLastLoginTime());
/* 366 */     setLoginCount(Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   public void init() {
/* 370 */     if (getUploadTotal() == null) {
/* 371 */       setUploadTotal(Long.valueOf(0L));
/*     */     }
/* 373 */     if (getUploadSize() == null) {
/* 374 */       setUploadSize(Integer.valueOf(0));
/*     */     }
/* 376 */     if (getUploadDate() == null) {
/* 377 */       setUploadDate(new java.sql.Date(System.currentTimeMillis()));
/*     */     }
/* 379 */     if (getAdmin() == null) {
/* 380 */       setAdmin(Boolean.valueOf(false));
/*     */     }
/* 382 */     if (getRank() == null) {
/* 383 */       setRank(Integer.valueOf(0));
/*     */     }
/* 385 */     if (getViewonlyAdmin() == null) {
/* 386 */       setViewonlyAdmin(Boolean.valueOf(false));
/*     */     }
/* 388 */     if (getSelfAdmin() == null) {
/* 389 */       setSelfAdmin(Boolean.valueOf(false));
/*     */     }
/* 391 */     if (getDisabled() == null)
/* 392 */       setDisabled(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public static Integer[] fetchIds(Collection<CmsUser> users)
/*     */   {
/* 397 */     if (users == null) {
/* 398 */       return null;
/*     */     }
/* 400 */     Integer[] ids = new Integer[users.size()];
/* 401 */     int i = 0;
/* 402 */     for (CmsUser u : users) {
/* 403 */       ids[(i++)] = u.getId();
/*     */     }
/* 405 */     return ids;
/*     */   }
/*     */ 
/*     */   public Number getPriority()
/*     */   {
/* 412 */     return Integer.valueOf(0);
/*     */   }
/*     */ 
/*     */   public static boolean isToday(java.util.Date date)
/*     */   {
/* 422 */     long day = date.getTime() / 1000L / 60L / 60L / 24L;
/* 423 */     long currentDay = System.currentTimeMillis() / 1000L / 60L / 60L / 24L;
/* 424 */     return day == currentDay;
/*     */   }
/*     */ 
/*     */   public CmsUser()
/*     */   {
/*     */   }
/*     */ 
/*     */   public CmsUser(Integer id)
/*     */   {
/* 436 */     super(id);
/*     */   }
/*     */ 
/*     */   public CmsUser(Integer id, CmsGroup group, String username, java.util.Date registerTime, String registerIp, Integer loginCount, Integer rank, Long uploadTotal, Integer uploadSize, Boolean admin, Boolean viewonlyAdmin, Boolean selfAdmin, Boolean disabled)
/*     */   {
/* 453 */     super(id, group, username, registerTime, registerIp, loginCount, rank, 
/* 452 */       uploadTotal, uploadSize, admin, viewonlyAdmin, selfAdmin, 
/* 453 */       disabled);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsUser
 * JD-Core Version:    0.6.0
 */