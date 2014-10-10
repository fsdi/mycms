/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsVoteTopicDao;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteReply;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*     */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteRecordMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteReplyMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteSubTopicMng;
/*     */ import com.jeecms.cms.manager.assist.CmsVoteTopicMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsVoteTopicMngImpl
/*     */   implements CmsVoteTopicMng
/*     */ {
/*     */   private CmsVoteSubTopicMng cmsVoteSubtopicMng;
/*     */   private CmsVoteReplyMng cmsVoteReplyMng;
/*     */   private CmsVoteRecordMng cmsVoteRecordMng;
/*     */   private CmsVoteTopicDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(Integer siteId, int pageNo, int pageSize)
/*     */   {
/*  31 */     Pagination page = this.dao.getPage(siteId, pageNo, pageSize);
/*  32 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsVoteTopic> getList(Boolean def, Integer siteId, int count) {
/*  37 */     return this.dao.getList(def, siteId, count);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsVoteTopic findById(Integer id) {
/*  42 */     CmsVoteTopic entity = this.dao.findById(id);
/*  43 */     return entity;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsVoteTopic getDefTopic(Integer siteId) {
/*  48 */     return this.dao.getDefTopic(siteId);
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic save(CmsVoteTopic bean, Set<CmsVoteSubTopic> subTopics) {
/*  52 */     int totalCount = 0;
/*  53 */     bean.setTotalCount(Integer.valueOf(totalCount));
/*  54 */     bean.init();
/*  55 */     this.dao.save(bean);
/*  56 */     this.cmsVoteSubtopicMng.save(bean, subTopics);
/*  57 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic update(CmsVoteTopic bean) {
/*  61 */     Updater updater = new Updater(bean);
/*  62 */     updater.include(CmsVoteTopic.PROP_START_TIME);
/*  63 */     updater.include(CmsVoteTopic.PROP_END_TIME);
/*  64 */     bean = this.dao.updateByUpdater(updater);
/*  65 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic vote(Integer topicId, Integer[] subIds, List<Integer[]> itemIds, String[] replys, CmsUser user, String ip, String cookie)
/*     */   {
/*  71 */     CmsVoteTopic topic = findById(topicId);
/*  72 */     Set<CmsVoteItem> items = topic.getItems();
/*  73 */     List itemIdsList = new ArrayList();
/*  74 */     for (Integer[] itemId : itemIds) {
/*  75 */       if ((itemId != null) && (itemId.length > 0)) {
/*  76 */         for (Integer id : itemId) {
/*  77 */           itemIdsList.add(id);
/*     */         }
/*     */       }
/*     */     }
/*  81 */     Integer[] ids = new Integer[itemIdsList.size()];
/*  82 */     ids = (Integer[])itemIdsList.toArray(ids);
/*  83 */     for (CmsVoteItem item : items) {
/*  84 */       if (ArrayUtils.contains(ids, item.getId())) {
/*  85 */         item.setVoteCount(Integer.valueOf(item.getVoteCount().intValue() + 1));
/*     */       }
/*     */     }
/*     */ 
/*  89 */     if ((replys != null) && (replys.length > 0)) {
/*  90 */       for (int i = 0; i < replys.length; i++) {
/*  91 */         String reply = replys[i];
/*  92 */         if (StringUtils.isNotBlank(reply)) {
/*  93 */           CmsVoteReply voteReply = new CmsVoteReply();
/*  94 */           voteReply.setReply(reply);
/*  95 */           voteReply.setSubTopic(this.cmsVoteSubtopicMng.findById(subIds[i]));
/*  96 */           this.cmsVoteReplyMng.save(voteReply);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 101 */     topic.setTotalCount(Integer.valueOf(topic.getTotalCount().intValue() + 1));
/*     */ 
/* 103 */     if (((topic.getRepeateHour() == null) || (topic.getRepeateHour().intValue() > 0)) && (
/* 104 */       (topic.getRestrictMember().booleanValue()) || (topic.getRestrictIp().booleanValue()) || 
/* 105 */       (topic.getRestrictCookie().booleanValue()))) {
/* 106 */       this.cmsVoteRecordMng.save(topic, user, ip, cookie);
/*     */     }
/* 108 */     return topic;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic deleteById(Integer id) {
/* 112 */     CmsVoteTopic bean = this.dao.deleteById(id);
/* 113 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsVoteTopic[] deleteByIds(Integer[] ids) {
/* 117 */     CmsVoteTopic[] beans = new CmsVoteTopic[ids.length];
/* 118 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 119 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 121 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsVoteSubTopicMng(CmsVoteSubTopicMng cmsVoteSubTopicMng)
/*     */   {
/* 131 */     this.cmsVoteSubtopicMng = cmsVoteSubTopicMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsVoteReplyMng(CmsVoteReplyMng cmsVoteReplyMng) {
/* 136 */     this.cmsVoteReplyMng = cmsVoteReplyMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsVoteRecordMng(CmsVoteRecordMng cmsVoteRecordMng) {
/* 141 */     this.cmsVoteRecordMng = cmsVoteRecordMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsVoteTopicDao dao) {
/* 146 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsVoteTopicMngImpl
 * JD-Core Version:    0.6.0
 */