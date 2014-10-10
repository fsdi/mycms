/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsTopicDao;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsTopic;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsTopicMng;
/*     */ import com.jeecms.cms.service.ChannelDeleteChecker;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsTopicMngImpl
/*     */   implements CmsTopicMng, ChannelDeleteChecker
/*     */ {
/*     */   private ChannelMng channelMng;
/*     */   private CmsTopicDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsTopic> getListForTag(Integer channelId, boolean recommend, Integer count)
/*     */   {
/*  24 */     return this.dao.getList(channelId, recommend, count, true);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageForTag(Integer channelId, boolean recommend, int pageNo, int pageSize) {
/*  30 */     return this.dao.getPage(channelId, recommend, pageNo, pageSize, true);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(int pageNo, int pageSize) {
/*  35 */     Pagination page = this.dao.getPage(null, false, pageNo, pageSize, false);
/*  36 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsTopic> getListByChannel(Integer channelId) {
/*  41 */     List list = this.dao.getGlobalTopicList();
/*  42 */     Channel c = this.channelMng.findById(channelId);
/*  43 */     list.addAll(this.dao.getListByChannelIds(c.getNodeIds()));
/*  44 */     return list;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsTopic findById(Integer id) {
/*  49 */     CmsTopic entity = this.dao.findById(id);
/*  50 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsTopic save(CmsTopic bean, Integer channelId) {
/*  54 */     if (channelId != null) {
/*  55 */       bean.setChannel(this.channelMng.findById(channelId));
/*     */     }
/*  57 */     bean.init();
/*  58 */     this.dao.save(bean);
/*  59 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTopic update(CmsTopic bean, Integer channelId) {
/*  63 */     Updater updater = new Updater(bean);
/*  64 */     CmsTopic entity = this.dao.updateByUpdater(updater);
/*  65 */     if (channelId != null)
/*  66 */       entity.setChannel(this.channelMng.findById(channelId));
/*     */     else {
/*  68 */       entity.setChannel(null);
/*     */     }
/*  70 */     entity.blankToNull();
/*  71 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsTopic deleteById(Integer id) {
/*  75 */     this.dao.deleteContentRef(id);
/*  76 */     CmsTopic bean = this.dao.deleteById(id);
/*  77 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsTopic[] deleteByIds(Integer[] ids) {
/*  81 */     CmsTopic[] beans = new CmsTopic[ids.length];
/*  82 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  83 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  85 */     return beans;
/*     */   }
/*     */ 
/*     */   public CmsTopic[] updatePriority(Integer[] ids, Integer[] priority) {
/*  89 */     int len = ids.length;
/*  90 */     CmsTopic[] beans = new CmsTopic[len];
/*  91 */     for (int i = 0; i < len; i++) {
/*  92 */       beans[i] = findById(ids[i]);
/*  93 */       beans[i].setPriority(priority[i]);
/*     */     }
/*  95 */     return beans;
/*     */   }
/*     */ 
/*     */   public String checkForChannelDelete(Integer channelId) {
/*  99 */     if (this.dao.countByChannelId(channelId) > 0) {
/* 100 */       return "cmsTopic.error.cannotDeleteChannel";
/*     */     }
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsTopicDao dao)
/*     */   {
/* 111 */     this.dao = dao;
/*     */   }
/*     */   @Autowired
/*     */   public void setChannelMng(ChannelMng channelMng) {
/* 116 */     this.channelMng = channelMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsTopicMngImpl
 * JD-Core Version:    0.6.0
 */