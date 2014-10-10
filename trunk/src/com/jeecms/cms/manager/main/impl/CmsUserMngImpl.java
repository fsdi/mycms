/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsUserDao;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsRoleMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserExtMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserSiteMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.mail.MessagingException;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsUserMngImpl
/*     */   implements CmsUserMng
/*     */ {
/*     */   private CmsUserSiteMng cmsUserSiteMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private ChannelMng channelMng;
/*     */   private CmsRoleMng cmsRoleMng;
/*     */   private CmsGroupMng cmsGroupMng;
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */   private CmsUserExtMng cmsUserExtMng;
/*     */   private CmsUserDao dao;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(String username, String email, Integer siteId, Integer groupId, Boolean disabled, Boolean admin, Integer rank, int pageNo, int pageSize)
/*     */   {
/*  45 */     Pagination page = this.dao.getPage(username, email, siteId, groupId, 
/*  46 */       disabled, admin, rank, pageNo, pageSize);
/*  47 */     return page;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsUser> getList(String username, String email, Integer siteId, Integer groupId, Boolean disabled, Boolean admin, Integer rank) {
/*  53 */     List list = this.dao.getList(username, email, siteId, groupId, 
/*  54 */       disabled, admin, rank);
/*  55 */     return list;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel, Boolean disabled, Integer rank) {
/*  61 */     return this.dao.getAdminList(siteId, allChannel, disabled, rank);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsUser findById(Integer id) {
/*  66 */     CmsUser entity = this.dao.findById(id);
/*  67 */     return entity;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsUser findByUsername(String username) {
/*  72 */     CmsUser entity = this.dao.findByUsername(username);
/*  73 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsUser registerMember(String username, String email, String password, String ip, Integer groupId, CmsUserExt userExt)
/*     */   {
/*  78 */     UnifiedUser unifiedUser = this.unifiedUserMng.save(username, email, 
/*  79 */       password, ip);
/*  80 */     CmsUser user = new CmsUser();
/*  81 */     user.forMember(unifiedUser);
/*     */ 
/*  83 */     CmsGroup group = null;
/*  84 */     if (groupId != null)
/*  85 */       group = this.cmsGroupMng.findById(groupId);
/*     */     else {
/*  87 */       group = this.cmsGroupMng.getRegDef();
/*     */     }
/*  89 */     if (group == null) {
/*  90 */       throw new RuntimeException(
/*  91 */         "register default member group not found!");
/*     */     }
/*  93 */     user.setGroup(group);
/*  94 */     user.init();
/*  95 */     this.dao.save(user);
/*  96 */     this.cmsUserExtMng.save(userExt, user);
/*  97 */     return user;
/*     */   }
/*     */ 
/*     */   public CmsUser registerMember(String username, String email, String password, String ip, Integer groupId, CmsUserExt userExt, Boolean activation, EmailSender sender, MessageTemplate msgTpl)
/*     */     throws UnsupportedEncodingException, MessagingException
/*     */   {
/* 104 */     UnifiedUser unifiedUser = this.unifiedUserMng.save(username, email, 
/* 105 */       password, ip, activation, sender, msgTpl);
/* 106 */     CmsUser user = new CmsUser();
/* 107 */     user.forMember(unifiedUser);
/*     */ 
/* 109 */     CmsGroup group = null;
/* 110 */     if (groupId != null)
/* 111 */       group = this.cmsGroupMng.findById(groupId);
/*     */     else {
/* 113 */       group = this.cmsGroupMng.getRegDef();
/*     */     }
/* 115 */     if (group == null) {
/* 116 */       throw new RuntimeException(
/* 117 */         "register default member group not found!");
/*     */     }
/* 119 */     user.setGroup(group);
/* 120 */     user.init();
/* 121 */     this.dao.save(user);
/* 122 */     this.cmsUserExtMng.save(userExt, user);
/* 123 */     return user;
/*     */   }
/*     */ 
/*     */   public void updateLoginInfo(Integer userId, String ip) {
/* 127 */     java.util.Date now = new Timestamp(System.currentTimeMillis());
/* 128 */     CmsUser user = findById(userId);
/* 129 */     if (user != null) {
/* 130 */       user.setLoginCount(Integer.valueOf(user.getLoginCount().intValue() + 1));
/* 131 */       user.setLastLoginIp(ip);
/* 132 */       user.setLastLoginTime(now);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateUploadSize(Integer userId, Integer size) {
/* 137 */     CmsUser user = findById(userId);
/* 138 */     user.setUploadTotal(Long.valueOf(user.getUploadTotal().longValue() + size.intValue()));
/* 139 */     if ((user.getUploadDate() != null) && 
/* 140 */       (CmsUser.isToday(user.getUploadDate()))) {
/* 141 */       size = Integer.valueOf(size.intValue() + user.getUploadSize().intValue());
/*     */     }
/*     */ 
/* 144 */     user.setUploadDate(new java.sql.Date(System.currentTimeMillis()));
/* 145 */     user.setUploadSize(size);
/*     */   }
/*     */ 
/*     */   public void updateUser(CmsUser user) {
/* 149 */     Updater updater = new Updater(user);
/* 150 */     this.dao.updateByUpdater(updater);
/*     */   }
/*     */ 
/*     */   public boolean isPasswordValid(Integer id, String password) {
/* 154 */     return this.unifiedUserMng.isPasswordValid(id, password);
/*     */   }
/*     */ 
/*     */   public void updatePwdEmail(Integer id, String password, String email) {
/* 158 */     CmsUser user = findById(id);
/* 159 */     if (!StringUtils.isBlank(email))
/* 160 */       user.setEmail(email);
/*     */     else {
/* 162 */       user.setEmail(null);
/*     */     }
/* 164 */     this.unifiedUserMng.update(id, password, email);
/*     */   }
/*     */ 
/*     */   public CmsUser saveAdmin(String username, String email, String password, String ip, boolean viewOnly, boolean selfAdmin, int rank, Integer groupId, Integer[] roleIds, Integer[] channelIds, Integer[] siteIds, Byte[] steps, Boolean[] allChannels, CmsUserExt userExt)
/*     */   {
/* 172 */     UnifiedUser unifiedUser = this.unifiedUserMng.save(username, email, 
/* 173 */       password, ip);
/* 174 */     CmsUser user = new CmsUser();
/* 175 */     user.forAdmin(unifiedUser, viewOnly, selfAdmin, rank);
/* 176 */     CmsGroup group = null;
/* 177 */     if (groupId != null)
/* 178 */       group = this.cmsGroupMng.findById(groupId);
/*     */     else {
/* 180 */       group = this.cmsGroupMng.getRegDef();
/*     */     }
/* 182 */     if (group == null) {
/* 183 */       throw new RuntimeException(
/* 184 */         "register default member group not setted!");
/*     */     }
/* 186 */     user.setGroup(group);
/* 187 */     user.init();
/* 188 */     this.dao.save(user);
/* 189 */     this.cmsUserExtMng.save(userExt, user);
/* 190 */     if (roleIds != null) {
/* 191 */       for (Integer rid : roleIds) {
/* 192 */         user.addToRoles(this.cmsRoleMng.findById(rid));
/*     */       }
/*     */     }
/* 195 */     if (channelIds != null)
/*     */     {
/* 197 */       for (Integer cid : channelIds) {
/* 198 */         Channel channel = this.channelMng.findById(cid);
/* 199 */         channel.addToUsers(user);
/*     */       }
/*     */     }
/* 202 */     if (siteIds != null)
/*     */     {
/* 204 */       int i = 0; for (int len = siteIds.length; i < len; i++) {
/* 205 */         CmsSite site = this.cmsSiteMng.findById(siteIds[i]);
/* 206 */         this.cmsUserSiteMng.save(site, user, steps[i], allChannels[i]);
/*     */       }
/*     */     }
/* 209 */     return user;
/*     */   }
/*     */ 
/*     */   public void addSiteToUser(CmsUser user, CmsSite site, Byte checkStep) {
/* 213 */     this.cmsUserSiteMng.save(site, user, checkStep, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password, Integer groupId, Integer[] roleIds, Integer[] channelIds, Integer siteId, Byte step, Boolean allChannel)
/*     */   {
/* 219 */     CmsUser user = updateAdmin(bean, ext, password, groupId, roleIds, 
/* 220 */       channelIds);
/*     */ 
/* 222 */     this.cmsUserSiteMng.updateByUser(user, siteId, step, allChannel);
/* 223 */     return user;
/*     */   }
/*     */ 
/*     */   public CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password, Integer groupId, Integer[] roleIds, Integer[] channelIds, Integer[] siteIds, Byte[] steps, Boolean[] allChannels)
/*     */   {
/* 229 */     CmsUser user = updateAdmin(bean, ext, password, groupId, roleIds, 
/* 230 */       channelIds);
/*     */ 
/* 232 */     this.cmsUserSiteMng.updateByUser(user, siteIds, steps, allChannels);
/* 233 */     return user;
/*     */   }
/*     */ 
/*     */   private CmsUser updateAdmin(CmsUser bean, CmsUserExt ext, String password, Integer groupId, Integer[] roleIds, Integer[] channelIds)
/*     */   {
/* 238 */     Updater updater = new Updater(bean);
/* 239 */     updater.include("email");
/* 240 */     CmsUser user = this.dao.updateByUpdater(updater);
/* 241 */     user.setGroup(this.cmsGroupMng.findById(groupId));
/* 242 */     this.cmsUserExtMng.update(ext, user);
/*     */ 
/* 244 */     user.getRoles().clear();
/* 245 */     if (roleIds != null) {
/* 246 */       for (Integer rid : roleIds) {
/* 247 */         user.addToRoles(this.cmsRoleMng.findById(rid));
/*     */       }
/*     */     }
/*     */ 
/* 251 */     Set<Channel> channels = user.getChannels();
/*     */ 
/* 253 */     for (Channel channel : channels) {
/* 254 */       channel.getUsers().remove(user);
/*     */     }
/* 256 */     user.getChannels().clear();
/*     */ 
/* 258 */     if (channelIds != null)
/*     */     {
/* 260 */       for (Integer cid : channelIds) {
/* 261 */         Channel channel = this.channelMng.findById(cid);
/* 262 */         channel.addToUsers(user);
/*     */       }
/*     */     }
/* 265 */     this.unifiedUserMng.update(bean.getId(), password, bean.getEmail());
/* 266 */     return user;
/*     */   }
/*     */ 
/*     */   public CmsUser updateMember(Integer id, String email, String password, Boolean isDisabled, CmsUserExt ext, Integer groupId)
/*     */   {
/* 271 */     CmsUser entity = findById(id);
/* 272 */     if (!StringUtils.isBlank(email)) {
/* 273 */       entity.setEmail(email);
/*     */     }
/* 275 */     if (isDisabled != null) {
/* 276 */       entity.setDisabled(isDisabled);
/*     */     }
/* 278 */     if (groupId != null) {
/* 279 */       entity.setGroup(this.cmsGroupMng.findById(groupId));
/*     */     }
/* 281 */     this.cmsUserExtMng.update(ext, entity);
/* 282 */     this.unifiedUserMng.update(id, password, email);
/* 283 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsUser updateUserConllection(CmsUser user, Integer cid, Integer operate) {
/* 287 */     Updater updater = new Updater(user);
/* 288 */     user = this.dao.updateByUpdater(updater);
/* 289 */     if (operate.equals(Integer.valueOf(1))) {
/* 290 */       user.addToCollection(this.contentMng.findById(cid));
/*     */     }
/* 292 */     else if (operate.equals(Integer.valueOf(0))) {
/* 293 */       user.delFromCollection(this.contentMng.findById(cid));
/*     */     }
/* 295 */     return user;
/*     */   }
/*     */ 
/*     */   public CmsUser deleteById(Integer id) {
/* 299 */     this.unifiedUserMng.deleteById(id);
/* 300 */     CmsUser bean = this.dao.deleteById(id);
/*     */ 
/* 302 */     bean.clearCollection();
/* 303 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsUser[] deleteByIds(Integer[] ids) {
/* 307 */     CmsUser[] beans = new CmsUser[ids.length];
/* 308 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 309 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 311 */     return beans;
/*     */   }
/*     */ 
/*     */   public boolean usernameNotExist(String username) {
/* 315 */     return this.dao.countByUsername(username) <= 0;
/*     */   }
/*     */ 
/*     */   public boolean usernameNotExistInMember(String username) {
/* 319 */     return this.dao.countMemberByUsername(username) <= 0;
/*     */   }
/*     */ 
/*     */   public boolean emailNotExist(String email) {
/* 323 */     return this.dao.countByEmail(email) <= 0;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsUserSiteMng(CmsUserSiteMng cmsUserSiteMng)
/*     */   {
/* 339 */     this.cmsUserSiteMng = cmsUserSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 344 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setChannelMng(ChannelMng channelMng) {
/* 349 */     this.channelMng = channelMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsRoleMng(CmsRoleMng cmsRoleMng) {
/* 354 */     this.cmsRoleMng = cmsRoleMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setUnifiedUserMng(UnifiedUserMng unifiedUserMng) {
/* 359 */     this.unifiedUserMng = unifiedUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserExtMng(CmsUserExtMng cmsUserExtMng) {
/* 364 */     this.cmsUserExtMng = cmsUserExtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsGroupMng(CmsGroupMng cmsGroupMng) {
/* 369 */     this.cmsGroupMng = cmsGroupMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsUserDao dao) {
/* 374 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsUserMngImpl
 * JD-Core Version:    0.6.0
 */