/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ContentDao;
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.Channel.AfterCheckEnum;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.Content.ContentStatus;
/*     */ import com.jeecms.cms.entity.main.ContentCheck;
/*     */ import com.jeecms.cms.entity.main.ContentCount;
/*     */ import com.jeecms.cms.entity.main.ContentExt;
/*     */ import com.jeecms.cms.entity.main.ContentTxt;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.manager.main.CmsTopicMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.ContentCheckMng;
/*     */ import com.jeecms.cms.manager.main.ContentCountMng;
/*     */ import com.jeecms.cms.manager.main.ContentExtMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.manager.main.ContentTagMng;
/*     */ import com.jeecms.cms.manager.main.ContentTxtMng;
/*     */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*     */ import com.jeecms.cms.service.ChannelDeleteChecker;
/*     */ import com.jeecms.cms.service.ContentListener;
/*     */ import com.jeecms.cms.staticpage.StaticPageSvc;
/*     */ import com.jeecms.cms.staticpage.exception.ContentNotCheckedException;
/*     */ import com.jeecms.cms.staticpage.exception.GeneratedZeroStaticPageException;
/*     */ import com.jeecms.cms.staticpage.exception.StaticPageNotOpenException;
/*     */ import com.jeecms.cms.staticpage.exception.TemplateNotFoundException;
/*     */ import com.jeecms.cms.staticpage.exception.TemplateParseException;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import freemarker.template.TemplateException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ContentMngImpl
/*     */   implements ContentMng, ChannelDeleteChecker
/*     */ {
/*     */   private List<ContentListener> listenerList;
/*     */   private ChannelMng channelMng;
/*     */   private ContentExtMng contentExtMng;
/*     */   private ContentTxtMng contentTxtMng;
/*     */   private ContentTypeMng contentTypeMng;
/*     */   private ContentCountMng contentCountMng;
/*     */   private ContentCheckMng contentCheckMng;
/*     */   private ContentTagMng contentTagMng;
/*     */   private CmsGroupMng cmsGroupMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private CmsTopicMng cmsTopicMng;
/*     */   private CmsCommentMng cmsCommentMng;
/*     */   private ContentDao dao;
/*     */   private StaticPageSvc staticPageSvc;
/*     */   private CmsFileMng fileMng;
/*     */ 
/*     */   @Autowired
/*     */   protected CmsModelMng cmsModelMng;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageByRight(String title, Integer typeId, Integer inputUserId, boolean topLevel, boolean recommend, Content.ContentStatus status, Byte checkStep, Integer siteId, Integer channelId, Integer userId, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  71 */     CmsUser user = this.cmsUserMng.findById(userId);
/*  72 */     CmsUserSite us = user.getUserSite(siteId);
/*     */ 
/*  74 */     boolean allChannel = us.getAllChannel().booleanValue();
/*  75 */     boolean selfData = user.getSelfAdmin().booleanValue();
/*     */     Pagination p;
/*  76 */     if ((allChannel) && (selfData))
/*     */     {
/*  78 */       p = this.dao.getPageBySelf(title, typeId, inputUserId, topLevel, 
/*  79 */         recommend, status, checkStep, siteId, channelId, userId, 
/*  80 */         orderBy, pageNo, pageSize);
/*     */     }
/*     */     else
/*     */     {
/*  81 */       if ((allChannel) && (!selfData))
/*     */       {
/*  83 */         p = this.dao.getPage(title, typeId, inputUserId, topLevel, recommend, 
/*  84 */           status, checkStep, siteId, channelId, orderBy, pageNo, 
/*  85 */           pageSize);
/*     */       }
/*  87 */       else p = this.dao.getPageByRight(title, typeId, inputUserId, topLevel, 
/*  88 */           recommend, status, checkStep, siteId, channelId, userId, 
/*  89 */           selfData, orderBy, pageNo, pageSize);
/*     */     }
/*  91 */     return p;
/*     */   }
/*     */ 
/*     */   public Pagination getPageForMember(String title, Integer channelId, Integer siteId, Integer memberId, int pageNo, int pageSize)
/*     */   {
/*  96 */     return this.dao
/*  97 */       .getPage(title, null, memberId, false, false, 
/*  98 */       Content.ContentStatus.all, null, siteId, channelId, 0, pageNo, 
/*  99 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Content getSide(Integer id, Integer siteId, Integer channelId, boolean next) {
/* 105 */     return this.dao.getSide(id, siteId, channelId, next, true);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListByIdsForTag(Integer[] ids, int orderBy) {
/* 110 */     if (ids.length == 1) {
/* 111 */       Content content = findById(ids[0]);
/*     */       List list;
/* 113 */       if (content != null) {
/* 114 */         list = new ArrayList(1);
/* 115 */         list.add(content);
/*     */       } else {
/* 117 */         list = new ArrayList(0);
/*     */       }
/* 119 */       return list;
/*     */     }
/* 121 */     return this.dao.getListByIdsForTag(ids, orderBy);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageBySiteIdsForTag(Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 129 */     return this.dao.getPageBySiteIdsForTag(siteIds, typeIds, titleImg, 
/* 130 */       recommend, title, orderBy, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListBySiteIdsForTag(Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 137 */     return this.dao.getListBySiteIdsForTag(siteIds, typeIds, titleImg, 
/* 138 */       recommend, title, orderBy, first, count);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageByChannelIdsForTag(Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int option, int pageNo, int pageSize)
/*     */   {
/* 145 */     return this.dao.getPageByChannelIdsForTag(channelIds, typeIds, titleImg, 
/* 146 */       recommend, title, orderBy, option, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListByChannelIdsForTag(Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int option, Integer first, Integer count)
/*     */   {
/* 153 */     return this.dao.getListByChannelIdsForTag(channelIds, typeIds, titleImg, 
/* 154 */       recommend, title, orderBy, option, first, count);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageByChannelPathsForTag(String[] paths, Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 162 */     return this.dao.getPageByChannelPathsForTag(paths, siteIds, typeIds, 
/* 163 */       titleImg, recommend, title, orderBy, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListByChannelPathsForTag(String[] paths, Integer[] siteIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 171 */     return this.dao.getListByChannelPathsForTag(paths, siteIds, typeIds, 
/* 172 */       titleImg, recommend, title, orderBy, first, count);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageByTopicIdForTag(Integer topicId, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 180 */     return this.dao.getPageByTopicIdForTag(topicId, siteIds, channelIds, 
/* 181 */       typeIds, titleImg, recommend, title, orderBy, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListByTopicIdForTag(Integer topicId, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 189 */     return this.dao.getListByTopicIdForTag(topicId, siteIds, channelIds, 
/* 190 */       typeIds, titleImg, recommend, title, orderBy, first, count);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageByTagIdsForTag(Integer[] tagIds, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Integer excludeId, Boolean titleImg, Boolean recommend, String title, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 198 */     return this.dao.getPageByTagIdsForTag(tagIds, siteIds, channelIds, typeIds, 
/* 199 */       excludeId, titleImg, recommend, title, orderBy, pageNo, 
/* 200 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Content> getListByTagIdsForTag(Integer[] tagIds, Integer[] siteIds, Integer[] channelIds, Integer[] typeIds, Integer excludeId, Boolean titleImg, Boolean recommend, String title, int orderBy, Integer first, Integer count)
/*     */   {
/* 208 */     return this.dao.getListByTagIdsForTag(tagIds, siteIds, channelIds, typeIds, 
/* 209 */       excludeId, titleImg, recommend, title, orderBy, first, count);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Content findById(Integer id) {
/* 214 */     Content entity = this.dao.findById(id);
/* 215 */     return entity;
/*     */   }
/*     */ 
/*     */   public Content save(Content bean, ContentExt ext, ContentTxt txt, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] tagArr, String[] attachmentPaths, String[] attachmentNames, String[] attachmentFilenames, String[] picPaths, String[] picDescs, Integer channelId, Integer typeId, Boolean draft, CmsUser user, boolean forMember)
/*     */   {
/* 224 */     saveContent(bean, ext, txt, channelId, typeId, draft, user, forMember);
/*     */ 
/* 226 */     if ((channelIds != null) && (channelIds.length > 0)) {
/* 227 */       for (Integer cid : channelIds) {
/* 228 */         bean.addToChannels(this.channelMng.findById(cid));
/*     */       }
/*     */     }
/*     */ 
/* 232 */     bean.addToChannels(this.channelMng.findById(channelId));
/*     */ 
/* 234 */     if ((topicIds != null) && (topicIds.length > 0)) {
/* 235 */       for (Integer tid : topicIds) {
/* 236 */         bean.addToTopics(this.cmsTopicMng.findById(tid));
/*     */       }
/*     */     }
/*     */ 
/* 240 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0)) {
/* 241 */       for (Integer gid : viewGroupIds) {
/* 242 */         bean.addToGroups(this.cmsGroupMng.findById(gid));
/*     */       }
/*     */     }
/*     */ 
/* 246 */     List tags = this.contentTagMng.saveTags(tagArr);
/* 247 */     bean.setTags(tags);
/*     */ 
/* 249 */     if ((attachmentPaths != null) && (attachmentPaths.length > 0)) {
/* 250 */       int i = 0; for (int len = attachmentPaths.length; i < len; i++) {
/* 251 */         if (!StringUtils.isBlank(attachmentPaths[i])) {
/* 252 */           bean.addToAttachmemts(attachmentPaths[i], 
/* 253 */             attachmentNames[i], attachmentFilenames[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 258 */     if ((picPaths != null) && (picPaths.length > 0)) {
/* 259 */       int i = 0; for (int len = picPaths.length; i < len; i++) {
/* 260 */         if (!StringUtils.isBlank(picPaths[i])) {
/* 261 */           bean.addToPictures(picPaths[i], picDescs[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 266 */     afterSave(bean);
/* 267 */     return bean;
/*     */   }
/*     */ 
/*     */   private Content saveContent(Content bean, ContentExt ext, ContentTxt txt, Integer channelId, Integer typeId, Boolean draft, CmsUser user, boolean forMember)
/*     */   {
/* 272 */     bean.setChannel(this.channelMng.findById(channelId));
/* 273 */     bean.setType(this.contentTypeMng.findById(typeId));
/* 274 */     bean.setUser(user);
/*     */     Byte userStep;
/* 276 */     if (forMember)
/*     */     {
/* 278 */       userStep = Byte.valueOf((byte) 0);
/*     */     } else {
/* 280 */       CmsSite site = bean.getSite();
/* 281 */       userStep = user.getCheckStep(site.getId());
/*     */     }
/* 283 */     if ((draft != null) && (draft.booleanValue())) {
/* 284 */       bean.setStatus(Byte.valueOf((byte) 0));
/*     */     }
/* 286 */     else if (userStep.byteValue() >= bean.getChannel().getFinalStepExtends().byteValue())
/* 287 */       bean.setStatus(Byte.valueOf((byte) 2));
/*     */     else {
/* 289 */       bean.setStatus(Byte.valueOf((byte) 1));
/*     */     }
/*     */ 
/* 293 */     bean.setHasTitleImg(Boolean.valueOf(!StringUtils.isBlank(ext.getTitleImg())));
/* 294 */     bean.init();
/*     */ 
/* 296 */     preSave(bean);
/* 297 */     this.dao.save(bean);
/* 298 */     this.contentExtMng.save(ext, bean);
/* 299 */     this.contentTxtMng.save(txt, bean);
/* 300 */     ContentCheck check = new ContentCheck();
/* 301 */     check.setCheckStep(userStep);
/* 302 */     this.contentCheckMng.save(check, bean);
/* 303 */     this.contentCountMng.save(new ContentCount(), bean);
/* 304 */     return bean;
/*     */   }
/*     */ 
/*     */   public Content update(Content bean, ContentExt ext, ContentTxt txt, String[] tagArr, Integer[] channelIds, Integer[] topicIds, Integer[] viewGroupIds, String[] attachmentPaths, String[] attachmentNames, String[] attachmentFilenames, String[] picPaths, String[] picDescs, Map<String, String> attr, Integer channelId, Integer typeId, Boolean draft, CmsUser user, boolean forMember)
/*     */   {
/* 314 */     Content entity = findById(bean.getId());
/*     */ 
/* 316 */     List mapList = preChange(entity);
/*     */ 
/* 318 */     Updater updater = new Updater(bean);
/* 319 */     bean = this.dao.updateByUpdater(updater);
/*     */     Byte userStep;
/* 322 */     if (forMember)
/*     */     {
/* 324 */       userStep = Byte.valueOf((byte)0);
/*     */     } else {
/* 326 */       CmsSite site = bean.getSite();
/* 327 */       userStep = user.getCheckStep(site.getId());
/*     */     }
/* 329 */     Channel.AfterCheckEnum after = bean.getChannel().getAfterCheckEnum();
/* 330 */     if ((after == Channel.AfterCheckEnum.BACK_UPDATE) && 
/* 331 */       (bean.getCheckStep().byteValue() > userStep.byteValue())) {
/* 332 */       bean.getContentCheck().setCheckStep(userStep);
/* 333 */       if (bean.getCheckStep().byteValue() >= bean.getChannel().getFinalStepExtends().byteValue())
/* 334 */         bean.setStatus(Byte.valueOf((byte)2));
/*     */       else {
/* 336 */         bean.setStatus(Byte.valueOf((byte)1));
/*     */       }
/*     */     }
/*     */ 
/* 340 */     if (draft != null) {
/* 341 */       if (draft.booleanValue()) {
/* 342 */         bean.setStatus(Byte.valueOf((byte)0));
/*     */       }
/* 344 */       else if (bean.getStatus().byteValue() == 0)
/*     */       {
/* 346 */         if (bean.getCheckStep().byteValue() >= 
/* 346 */           bean.getChannel().getFinalStepExtends().byteValue())
/* 347 */           bean.setStatus(Byte.valueOf((byte)2));
/*     */         else {
/* 349 */           bean.setStatus(Byte.valueOf((byte)1));
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 355 */     bean.setHasTitleImg(Boolean.valueOf(!StringUtils.isBlank(ext.getTitleImg())));
/*     */ 
/* 357 */     if (channelId != null) {
/* 358 */       bean.setChannel(this.channelMng.findById(channelId));
/*     */     }
/*     */ 
/* 361 */     if (typeId != null) {
/* 362 */       bean.setType(this.contentTypeMng.findById(typeId));
/*     */     }
/*     */ 
/* 365 */     this.contentExtMng.update(ext);
/*     */ 
/* 367 */     this.contentTxtMng.update(txt, bean);
/*     */ 
/* 369 */     if (attr != null) {
/* 370 */       Map attrOrig = bean.getAttr();
/* 371 */       attrOrig.clear();
/* 372 */       attrOrig.putAll(attr);
/*     */     }
/*     */ 
/* 375 */     Set channels = bean.getChannels();
/* 376 */     channels.clear();
/* 377 */     if ((channelIds != null) && (channelIds.length > 0)) {
/* 378 */       for (Integer cid : channelIds) {
/* 379 */         channels.add(this.channelMng.findById(cid));
/*     */       }
/*     */     }
/* 382 */     channels.add(bean.getChannel());
/*     */ 
/* 384 */     Set topics = bean.getTopics();
/* 385 */     topics.clear();
/* 386 */     if ((topicIds != null) && (topicIds.length > 0)) {
/* 387 */       for (Integer tid : topicIds) {
/* 388 */         topics.add(this.cmsTopicMng.findById(tid));
/*     */       }
/*     */     }
/*     */ 
/* 392 */     Set groups = bean.getViewGroups();
/* 393 */     groups.clear();
/* 394 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0)) {
/* 395 */       for (Integer gid : viewGroupIds) {
/* 396 */         groups.add(this.cmsGroupMng.findById(gid));
/*     */       }
/*     */     }
/*     */ 
/* 400 */     this.contentTagMng.updateTags(bean.getTags(), tagArr);
/*     */ 
/* 402 */     bean.getAttachments().clear();
/* 403 */     if ((attachmentPaths != null) && (attachmentPaths.length > 0)) {
/* 404 */       int i = 0; for (int len = attachmentPaths.length; i < len; i++) {
/* 405 */         if (!StringUtils.isBlank(attachmentPaths[i])) {
/* 406 */           bean.addToAttachmemts(attachmentPaths[i], 
/* 407 */             attachmentNames[i], attachmentFilenames[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 412 */     bean.getPictures().clear();
/* 413 */     if ((picPaths != null) && (picPaths.length > 0)) {
/* 414 */       int i = 0; for (int len = picPaths.length; i < len; i++) {
/* 415 */         if (!StringUtils.isBlank(picPaths[i])) {
/* 416 */           bean.addToPictures(picPaths[i], picDescs[i]);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 421 */     afterChange(bean, mapList);
/* 422 */     return bean;
/*     */   }
/*     */ 
/*     */   public Content check(Integer id, CmsUser user) {
/* 426 */     Content content = findById(id);
/*     */ 
/* 428 */     List mapList = preChange(content);
/* 429 */     ContentCheck check = content.getContentCheck();
/* 430 */     byte userStep = user.getCheckStep(content.getSite().getId()).byteValue();
/* 431 */     byte contentStep = check.getCheckStep().byteValue();
/* 432 */     byte finalStep = content.getChannel().getFinalStepExtends().byteValue();
/*     */ 
/* 434 */     if (userStep < contentStep) {
/* 435 */       return content;
/*     */     }
/* 437 */     check.setRejected(Boolean.valueOf(false));
/*     */ 
/* 439 */     if (userStep > contentStep) {
/* 440 */       check.setCheckOpinion(null);
/*     */     }
/* 442 */     check.setCheckStep(Byte.valueOf(userStep));
/*     */ 
/* 444 */     if (userStep >= finalStep) {
/* 445 */       content.setStatus(Byte.valueOf((byte)2));
/*     */ 
/* 447 */       check.setCheckOpinion(null);
/*     */ 
/* 449 */       check.setReviewer(user);
/* 450 */       check.setCheckDate(Calendar.getInstance().getTime());
/*     */     }
/*     */ 
/* 453 */     afterChange(content, mapList);
/* 454 */     return content;
/*     */   }
/*     */ 
/*     */   public Content[] check(Integer[] ids, CmsUser user) {
/* 458 */     Content[] beans = new Content[ids.length];
/* 459 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 460 */       beans[i] = check(ids[i], user);
/*     */     }
/* 462 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content update(Content bean) {
/* 466 */     Updater updater = new Updater(bean);
/* 467 */     bean = this.dao.updateByUpdater(updater);
/* 468 */     return bean;
/*     */   }
/*     */ 
/*     */   public Content reject(Integer id, CmsUser user, Byte step, String opinion) {
/* 472 */     Content content = findById(id);
/* 473 */     Integer siteId = content.getSite().getId();
/* 474 */     byte userStep = user.getCheckStep(siteId).byteValue();
/* 475 */     byte contentStep = content.getCheckStep().byteValue();
/*     */ 
/* 477 */     if (userStep < contentStep) {
/* 478 */       return content;
/*     */     }
/*     */ 
/* 481 */     List mapList = preChange(content);
/* 482 */     ContentCheck check = content.getContentCheck();
/* 483 */     if (!StringUtils.isBlank(opinion)) {
/* 484 */       check.setCheckOpinion(opinion);
/*     */     }
/* 486 */     check.setRejected(Boolean.valueOf(true));
/*     */ 
/* 488 */     content.setStatus(Byte.valueOf((byte)1));
/*     */ 
/* 490 */     if (step != null)
/*     */     {
/* 492 */       if (step.byteValue() < userStep)
/* 493 */         check.setCheckStep(step);
/*     */       else {
/* 495 */         check.setCheckStep(Byte.valueOf(userStep));
/*     */       }
/*     */ 
/*     */     }
/* 499 */     else if (contentStep >= userStep)
/*     */     {
/* 501 */       if (contentStep == userStep)
/*     */       {
/* 503 */         check.setCheckStep(Byte.valueOf((byte)(check.getCheckStep().byteValue() - 1)));
/*     */       }
/*     */     }
/*     */ 
/* 507 */     afterChange(content, mapList);
/* 508 */     return content;
/*     */   }
/*     */ 
/*     */   public Content[] reject(Integer[] ids, CmsUser user, Byte step, String opinion)
/*     */   {
/* 513 */     Content[] beans = new Content[ids.length];
/* 514 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 515 */       beans[i] = reject(ids[i], user, step, opinion);
/*     */     }
/* 517 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content cycle(Integer id) {
/* 521 */     Content content = findById(id);
/*     */ 
/* 523 */     List mapList = preChange(content);
/* 524 */     content.setStatus(Byte.valueOf((byte)3));
/*     */ 
/* 526 */     afterChange(content, mapList);
/* 527 */     return content;
/*     */   }
/*     */ 
/*     */   public Content[] cycle(Integer[] ids) {
/* 531 */     Content[] beans = new Content[ids.length];
/* 532 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 533 */       beans[i] = cycle(ids[i]);
/*     */     }
/* 535 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content recycle(Integer id) {
/* 539 */     Content content = findById(id);
/*     */ 
/* 541 */     List mapList = preChange(content);
/* 542 */     byte contentStep = content.getCheckStep().byteValue();
/* 543 */     byte finalStep = content.getChannel().getFinalStepExtends().byteValue();
/* 544 */     if ((contentStep >= finalStep) && (!content.getRejected().booleanValue()))
/* 545 */       content.setStatus(Byte.valueOf((byte)2));
/*     */     else {
/* 547 */       content.setStatus(Byte.valueOf((byte)1));
/*     */     }
/*     */ 
/* 550 */     afterChange(content, mapList);
/* 551 */     return content;
/*     */   }
/*     */ 
/*     */   public Content[] recycle(Integer[] ids) {
/* 555 */     Content[] beans = new Content[ids.length];
/* 556 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 557 */       beans[i] = recycle(ids[i]);
/*     */     }
/* 559 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content deleteById(Integer id) {
/* 563 */     Content bean = findById(id);
/*     */ 
/* 565 */     preDelete(bean);
/*     */ 
/* 567 */     this.contentTagMng.removeTags(bean.getTags());
/* 568 */     bean.getTags().clear();
/*     */ 
/* 570 */     this.cmsCommentMng.deleteByContentId(id);
/*     */ 
/* 572 */     this.fileMng.deleteByContentId(id);
/* 573 */     bean.clear();
/* 574 */     bean = this.dao.deleteById(id);
/*     */ 
/* 576 */     afterDelete(bean);
/* 577 */     return bean;
/*     */   }
/*     */ 
/*     */   public Content[] deleteByIds(Integer[] ids) {
/* 581 */     Content[] beans = new Content[ids.length];
/* 582 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 583 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 585 */     return beans;
/*     */   }
/*     */ 
/*     */   public Content[] contentStatic(Integer[] ids)
/*     */     throws TemplateNotFoundException, TemplateParseException, GeneratedZeroStaticPageException, StaticPageNotOpenException, ContentNotCheckedException
/*     */   {
/* 591 */     int count = 0;
/* 592 */     List list = new ArrayList();
/* 593 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 594 */       Content content = findById(ids[i]);
/*     */       try {
/* 596 */         if (!content.getChannel().getStaticContent().booleanValue()) {
/* 597 */           throw new StaticPageNotOpenException(
/* 598 */             "content.staticNotOpen", Integer.valueOf(count), content.getTitle());
/*     */         }
/* 600 */         if (!content.isChecked()) {
/* 601 */           throw new ContentNotCheckedException("content.notChecked", Integer.valueOf(count), content.getTitle());
/*     */         }
/* 603 */         if (this.staticPageSvc.content(content)) {
/* 604 */           list.add(content);
/* 605 */           count++;
/*     */         }
/*     */       } catch (IOException e) {
/* 608 */         throw new TemplateNotFoundException(
/* 609 */           "content.tplContentNotFound", Integer.valueOf(count), content.getTitle());
/*     */       } catch (TemplateException e) {
/* 611 */         throw new TemplateParseException("content.tplContentException", 
/* 612 */           Integer.valueOf(count), content.getTitle());
/*     */       }
/*     */     }
/* 615 */     if (count == 0) {
/* 616 */       throw new GeneratedZeroStaticPageException(
/* 617 */         "content.staticGenerated");
/*     */     }
/* 619 */     Content[] beans = new Content[count];
/* 620 */     return (Content[])list.toArray(beans);
/*     */   }
/*     */ 
/*     */   public Pagination getPageForCollection(Integer siteId, Integer memberId, int pageNo, int pageSize) {
/* 624 */     return this.dao.getPageForCollection(siteId, memberId, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public void updateFileByContent(Content bean, Boolean valid)
/*     */   {
/* 632 */     Set files = bean.getFiles();
/* 633 */     Iterator it = files.iterator();
/* 634 */     while (it.hasNext()) {
/* 635 */       CmsFile tempFile = (CmsFile)it.next();
/* 636 */       tempFile.setFileIsvalid(valid.booleanValue());
/* 637 */       this.fileMng.update(tempFile);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String checkForChannelDelete(Integer channelId) {
/* 642 */     int count = this.dao.countByChannelId(channelId.intValue());
/* 643 */     if (count > 0) {
/* 644 */       return "content.error.cannotDeleteChannel";
/*     */     }
/* 646 */     return null;
/*     */   }
/*     */ 
/*     */   private void preSave(Content content)
/*     */   {
/* 651 */     if (this.listenerList != null)
/* 652 */       for (ContentListener listener : this.listenerList)
/* 653 */         listener.preSave(content);
/*     */   }
/*     */ 
/*     */   private void afterSave(Content content)
/*     */   {
/* 659 */     if (this.listenerList != null)
/* 660 */       for (ContentListener listener : this.listenerList)
/* 661 */         listener.afterSave(content);
/*     */   }
/*     */ 
/*     */   private List<Map<String, Object>> preChange(Content content)
/*     */   {
/* 667 */     if (this.listenerList != null) {
/* 668 */       int len = this.listenerList.size();
/* 669 */       List list = new ArrayList(
/* 670 */         len);
/* 671 */       for (ContentListener listener : this.listenerList) {
/* 672 */         list.add(listener.preChange(content));
/*     */       }
/* 674 */       return list;
/*     */     }
/* 676 */     return null;
/*     */   }
/*     */ 
/*     */   private void afterChange(Content content, List<Map<String, Object>> mapList)
/*     */   {
/* 681 */     if (this.listenerList != null) {
/* 682 */       Assert.notNull(mapList);
/* 683 */       Assert.isTrue(mapList.size() == this.listenerList.size());
/* 684 */       int len = this.listenerList.size();
/*     */ 
/* 686 */       for (int i = 0; i < len; i++) {
/* 687 */         ContentListener listener = (ContentListener)this.listenerList.get(i);
/* 688 */         listener.afterChange(content, (Map)mapList.get(i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void preDelete(Content content) {
/* 694 */     if (this.listenerList != null)
/* 695 */       for (ContentListener listener : this.listenerList)
/* 696 */         listener.preDelete(content);
/*     */   }
/*     */ 
/*     */   private void afterDelete(Content content)
/*     */   {
/* 702 */     if (this.listenerList != null)
/* 703 */       for (ContentListener listener : this.listenerList)
/* 704 */         listener.afterDelete(content);
/*     */   }
/*     */ 
/*     */   public void setListenerList(List<ContentListener> listenerList)
/*     */   {
/* 712 */     this.listenerList = listenerList;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelMng(ChannelMng channelMng)
/*     */   {
/* 734 */     this.channelMng = channelMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentTypeMng(ContentTypeMng contentTypeMng) {
/* 739 */     this.contentTypeMng = contentTypeMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentCountMng(ContentCountMng contentCountMng) {
/* 744 */     this.contentCountMng = contentCountMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentExtMng(ContentExtMng contentExtMng) {
/* 749 */     this.contentExtMng = contentExtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentTxtMng(ContentTxtMng contentTxtMng) {
/* 754 */     this.contentTxtMng = contentTxtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentCheckMng(ContentCheckMng contentCheckMng) {
/* 759 */     this.contentCheckMng = contentCheckMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsTopicMng(CmsTopicMng cmsTopicMng) {
/* 764 */     this.cmsTopicMng = cmsTopicMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentTagMng(ContentTagMng contentTagMng) {
/* 769 */     this.contentTagMng = contentTagMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsGroupMng(CmsGroupMng cmsGroupMng) {
/* 774 */     this.cmsGroupMng = cmsGroupMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 779 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsCommentMng(CmsCommentMng cmsCommentMng) {
/* 784 */     this.cmsCommentMng = cmsCommentMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setFileMng(CmsFileMng fileMng) {
/* 789 */     this.fileMng = fileMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(ContentDao dao) {
/* 794 */     this.dao = dao;
/*     */   }
/*     */   @Autowired
/*     */   public void setStaticPageSvc(StaticPageSvc staticPageSvc) {
/* 799 */     this.staticPageSvc = staticPageSvc;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentMngImpl
 * JD-Core Version:    0.6.0
 */