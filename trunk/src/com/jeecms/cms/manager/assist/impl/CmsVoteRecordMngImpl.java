/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsVoteRecordDao;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteRecord;
/*    */ import com.jeecms.cms.entity.assist.CmsVoteTopic;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.manager.assist.CmsVoteRecordMng;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsVoteRecordMngImpl
/*    */   implements CmsVoteRecordMng
/*    */ {
/*    */   private CmsVoteRecordDao dao;
/*    */ 
/*    */   public CmsVoteRecord save(CmsVoteTopic topic, CmsUser user, String ip, String cookie)
/*    */   {
/* 22 */     CmsVoteRecord record = new CmsVoteRecord();
/* 23 */     record.setTopic(topic);
/* 24 */     record.setIp(ip);
/* 25 */     record.setCookie(cookie);
/* 26 */     record.setTime(new Timestamp(System.currentTimeMillis()));
/* 27 */     this.dao.save(record);
/* 28 */     return record;
/*    */   }
/*    */ 
/*    */   public int deleteByTopic(Integer topicId) {
/* 32 */     return this.dao.deleteByTopic(topicId);
/*    */   }
/*    */ 
/*    */   public Date lastVoteTimeByUserId(Integer userId, Integer topicId) {
/* 36 */     CmsVoteRecord record = this.dao.findByUserId(userId, topicId);
/* 37 */     return record != null ? record.getTime() : null;
/*    */   }
/*    */ 
/*    */   public Date lastVoteTimeByIp(String ip, Integer topicId) {
/* 41 */     CmsVoteRecord record = this.dao.findByIp(ip, topicId);
/* 42 */     return record != null ? record.getTime() : null;
/*    */   }
/*    */ 
/*    */   public Date lastVoteTimeByCookie(String cookie, Integer topicId) {
/* 46 */     CmsVoteRecord record = this.dao.findByCookie(cookie, topicId);
/* 47 */     return record != null ? record.getTime() : null;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsVoteRecordDao dao)
/*    */   {
/* 54 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsVoteRecordMngImpl
 * JD-Core Version:    0.6.0
 */