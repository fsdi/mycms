/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteItemDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteItem;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteSubTopic;
/*    */ import com.jeecms.cms.manager.assist.CmsVoteItemMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsVoteItemMngImpl
/*    */   implements CmsVoteItemMng
/*    */ {
/*    */   private CmsVoteItemDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(int pageNo, int pageSize)
/*    */   {
/* 23 */     Pagination page = this.dao.getPage(pageNo, pageSize);
/* 24 */     return page;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsVoteItem findById(Integer id) {
/* 29 */     CmsVoteItem entity = this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public Collection<CmsVoteItem> save(Collection<CmsVoteItem> items, CmsVoteSubTopic topic)
/*    */   {
/* 35 */     for (CmsVoteItem item : items) {
/* 36 */       item.setSubTopic(topic);
/* 37 */       item.setTopic(topic.getVoteTopic());
/* 38 */       item.init();
/* 39 */       this.dao.save(item);
/*    */     }
/* 41 */     return items;
/*    */   }
/*    */ 
/*    */   public Collection<CmsVoteItem> update(Collection<CmsVoteItem> items, CmsVoteSubTopic topic)
/*    */   {
/* 46 */     Set<CmsVoteItem> set = topic.getVoteItems();
/* 47 */     if (set == null) {
/* 48 */       set = new HashSet();
/*    */     }
/*    */ 
/* 51 */     Set toDel = new HashSet();
/* 52 */     for (CmsVoteItem item : set) {
/* 53 */       if (!items.contains(item)) {
/* 54 */         toDel.add(item);
/*    */       }
/*    */     }
/* 57 */     set.removeAll(toDel);
/*    */ 
/* 60 */     Object toAdd = new HashSet();
/* 61 */     for (CmsVoteItem item : items) {
/* 62 */       if (set.contains(item))
/*    */       {
/* 64 */         Updater updater = new Updater(item);
/* 65 */         this.dao.updateByUpdater(updater);
/*    */       }
/*    */       else {
/* 68 */         ((Set)toAdd).add(item);
/*    */       }
/*    */     }
/* 71 */     save((Collection)toAdd, topic);
/* 72 */     set.addAll((Collection)toAdd);
/* 73 */     return (Collection<CmsVoteItem>)set;
/*    */   }
/*    */ 
/*    */   public CmsVoteItem deleteById(Integer id) {
/* 77 */     CmsVoteItem bean = this.dao.deleteById(id);
/* 78 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteItem[] deleteByIds(Integer[] ids) {
/* 82 */     CmsVoteItem[] beans = new CmsVoteItem[ids.length];
/* 83 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 84 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 86 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsVoteItemDao dao)
/*    */   {
/* 93 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsVoteItemMngImpl
 * JD-Core Version:    0.6.0
 */