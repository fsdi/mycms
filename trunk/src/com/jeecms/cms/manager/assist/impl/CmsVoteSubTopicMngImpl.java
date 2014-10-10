/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsVoteSubTopicDao;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteItemMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteSubTopicMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsVoteSubTopicMngImpl
/*     */   implements CmsVoteSubTopicMng
/*     */ {
/*     */   private CmsVoteItemMng cmsVoteItemMng;
/*     */   private CmsVoteSubTopicDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsVoteSubTopic> findByVoteTopic(Integer voteTopicId)
/*     */   {
/*  25 */     return this.dao.findByVoteTopic(voteTopicId);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsVoteSubTopic findById(Integer id) {
/*  30 */     CmsVoteSubTopic entity = this.dao.findById(id);
/*  31 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic save(CmsVoteSubTopic bean, List<CmsVoteItem> items) {
/*  35 */     int totalCount = 0;
/*  36 */     for (CmsVoteItem item : items) {
/*  37 */       if (item.getVoteCount() != null) {
/*  38 */         totalCount += item.getVoteCount().intValue();
/*     */       }
/*     */     }
/*  41 */     this.dao.save(bean);
/*  42 */     this.cmsVoteItemMng.save(items, bean);
/*  43 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic save(CmsVoteTopic bean, Set<CmsVoteSubTopic> subTopics) {
/*  47 */     for (CmsVoteSubTopic sub : subTopics) {
/*  48 */       sub.setVoteTopic(bean);
/*  49 */       this.dao.save(sub);
/*     */     }
/*  51 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic update(CmsVoteSubTopic bean, Collection<CmsVoteItem> items) {
/*  55 */     Updater updater = new Updater(bean);
/*  56 */     bean = this.dao.updateByUpdater(updater);
/*  57 */     int totalCount = 0;
/*  58 */     for (CmsVoteItem item : items) {
/*  59 */       totalCount += item.getVoteCount().intValue();
/*     */     }
/*  61 */     this.cmsVoteItemMng.update(items, bean);
/*  62 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic update(CmsVoteTopic bean, Collection<CmsVoteSubTopic> subTopics) {
/*  66 */     for (CmsVoteSubTopic sub : subTopics) {
/*  67 */       Updater updater = new Updater(sub);
/*  68 */       this.dao.updateByUpdater(updater);
/*     */     }
/*  70 */     return bean;
/*     */   }
/*     */ 
/*     */   public Collection<CmsVoteSubTopic> update(Collection<CmsVoteSubTopic> subTopics, CmsVoteTopic topic) {
/*  74 */     Set<CmsVoteSubTopic> set = topic.getSubtopics();
/*     */ 
/*  76 */     Set toDel = new HashSet();
/*  77 */     for (CmsVoteSubTopic s : set) {
/*  78 */       if (!subTopics.contains(s)) {
/*  79 */         toDel.add(s);
/*     */       }
/*     */     }
/*  82 */     set.removeAll(toDel);
/*     */ 
/*  85 */     Object toAdd = new HashSet();
/*  86 */     for (CmsVoteSubTopic item : subTopics) {
/*  87 */       if (set.contains(item))
/*     */       {
/*  89 */         Updater updater = new Updater(item);
/*  90 */         this.dao.updateByUpdater(updater);
/*     */       }
/*     */       else {
/*  93 */         ((Set)toAdd).add(item);
/*     */       }
/*     */     }
/*  96 */     save(topic, (Set)toAdd);
/*  97 */     set.addAll((Collection)toAdd);
/*  98 */     return (Collection<CmsVoteSubTopic>)set;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic deleteById(Integer id) {
/* 102 */     CmsVoteSubTopic bean = this.dao.deleteById(id);
/* 103 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteSubTopic[] deleteByIds(Integer[] ids) {
/* 107 */     CmsVoteSubTopic[] beans = new CmsVoteSubTopic[ids.length];
/* 108 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 109 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 111 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsVoteItemMng(CmsVoteItemMng cmsVoteItemMng)
/*     */   {
/* 119 */     this.cmsVoteItemMng = cmsVoteItemMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsVoteSubTopicDao dao) {
/* 124 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsVoteSubTopicMngImpl
 * JD-Core Version:    0.6.0
 */