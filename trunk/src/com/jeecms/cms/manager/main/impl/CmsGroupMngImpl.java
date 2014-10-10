/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsGroupDao;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsGroupMngImpl
/*     */   implements CmsGroupMng
/*     */ {
/*     */   private CmsGroupDao dao;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsGroup> getList()
/*     */   {
/*  22 */     return this.dao.getList();
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsGroup findById(Integer id) {
/*  27 */     CmsGroup entity = this.dao.findById(id);
/*  28 */     return entity;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsGroup getRegDef() {
/*  33 */     return this.dao.getRegDef();
/*     */   }
/*     */ 
/*     */   public void updateRegDef(Integer regDefId) {
/*  37 */     if (regDefId != null)
/*  38 */       for (CmsGroup g : getList())
/*  39 */         if (g.getId().equals(regDefId))
/*  40 */           g.setRegDef(Boolean.valueOf(true));
/*     */         else
/*  42 */           g.setRegDef(Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   public CmsGroup save(CmsGroup bean)
/*     */   {
/*  49 */     bean.init();
/*  50 */     this.dao.save(bean);
/*  51 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGroup save(CmsGroup bean, Integer[] viewChannelIdss, Integer[] contriChannelIds) {
/*  55 */     bean.init();
/*  56 */     this.dao.save(bean);
/*     */ 
/*  58 */     if ((viewChannelIdss != null) && (viewChannelIdss.length > 0)) {
/*  59 */       for (Integer cid : viewChannelIdss) {
/*  60 */         Channel c = this.channelMng.findById(cid);
/*  61 */         bean.addToViewChannels(c);
/*     */       }
/*     */     }
/*  64 */     if ((contriChannelIds != null) && (contriChannelIds.length > 0)) {
/*  65 */       for (Integer cid : contriChannelIds) {
/*  66 */         Channel c = this.channelMng.findById(cid);
/*  67 */         bean.addToContriChannels(c);
/*     */       }
/*     */     }
/*  70 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGroup update(CmsGroup bean) {
/*  74 */     Updater updater = new Updater(bean);
/*  75 */     CmsGroup entity = this.dao.updateByUpdater(updater);
/*  76 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsGroup update(CmsGroup bean, Integer[] viewChannelIds, Integer[] contriChannelIds) {
/*  80 */     Updater updater = new Updater(bean);
/*  81 */     bean = this.dao.updateByUpdater(updater);
/*     */ 
/*  83 */     Set<Channel> viewChannels = bean.getViewChannels();
/*     */ 
/*  85 */     for (Channel channel : viewChannels) {
/*  86 */       channel.getViewGroups().remove(bean);
/*     */     }
/*  88 */     bean.getViewChannels().clear();
/*  89 */     Set<Channel> contriChannels = bean.getContriChannels();
/*     */ 
/*  91 */     for (Channel channel : contriChannels) {
/*  92 */       channel.getContriGroups().remove(bean);
/*     */     }
/*  94 */     bean.getContriChannels().remove(bean);
/*     */ 
/*  96 */     if ((viewChannelIds != null) && (viewChannelIds.length > 0)) {
/*  97 */       for (Integer cid : viewChannelIds) {
/*  98 */         Channel c = this.channelMng.findById(cid);
/*  99 */         bean.addToViewChannels(c);
/*     */       }
/*     */     }
/* 102 */     if ((contriChannelIds != null) && (contriChannelIds.length > 0)) {
/* 103 */       for (Integer cid : contriChannelIds) {
/* 104 */         Channel c = this.channelMng.findById(cid);
/* 105 */         bean.addToContriChannels(c);
/*     */       }
/*     */     }
/* 108 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGroup deleteById(Integer id) {
/* 112 */     CmsGroup bean = this.dao.deleteById(id);
/* 113 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGroup[] deleteByIds(Integer[] ids) {
/* 117 */     CmsGroup[] beans = new CmsGroup[ids.length];
/* 118 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 119 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 121 */     return beans;
/*     */   }
/*     */ 
/*     */   public CmsGroup[] updatePriority(Integer[] ids, Integer[] priority) {
/* 125 */     int len = ids.length;
/* 126 */     CmsGroup[] beans = new CmsGroup[len];
/* 127 */     for (int i = 0; i < len; i++) {
/* 128 */       beans[i] = findById(ids[i]);
/* 129 */       beans[i].setPriority(priority[i]);
/*     */     }
/* 131 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsGroupDao dao)
/*     */   {
/* 140 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsGroupMngImpl
 * JD-Core Version:    0.6.0
 */