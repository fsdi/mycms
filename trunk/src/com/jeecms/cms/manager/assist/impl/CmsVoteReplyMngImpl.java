/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteReplyDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteReply;
/*    */ import com.jeecms.cms.manager.assist.CmsVoteReplyMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsVoteReplyMngImpl
/*    */   implements CmsVoteReplyMng
/*    */ {
/*    */   private CmsVoteReplyDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(Integer subTopicId, int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(subTopicId, pageNo, pageSize);
/*    */   }
/* 22 */   @Transactional(readOnly=true)
/*    */   public CmsVoteReply findById(Integer id) { CmsVoteReply entity = this.dao.findById(id);
/* 23 */     return entity; }
/*    */ 
/*    */   public CmsVoteReply save(CmsVoteReply bean)
/*    */   {
/* 27 */     this.dao.save(bean);
/* 28 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteReply update(CmsVoteReply bean) {
/* 32 */     Updater updater = new Updater(bean);
/* 33 */     bean = this.dao.updateByUpdater(updater);
/* 34 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteReply deleteById(Integer id)
/*    */   {
/* 39 */     CmsVoteReply bean = this.dao.deleteById(id);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsVoteReply[] deleteByIds(Integer[] ids) {
/* 44 */     CmsVoteReply[] beans = new CmsVoteReply[ids.length];
/* 45 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 46 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 48 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsVoteReplyDao dao)
/*    */   {
/* 55 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsVoteReplyMngImpl
 * JD-Core Version:    0.6.0
 */