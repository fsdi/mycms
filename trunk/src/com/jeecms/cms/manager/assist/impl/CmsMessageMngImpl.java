/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsMessageDao;
/*    */ import com.jeecms.cms.entity.assist.CmsMessage;
/*    */ import com.jeecms.cms.manager.assist.CmsMessageMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Date;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsMessageMngImpl
/*    */   implements CmsMessageMng
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsMessageDao dao;
/*    */ 
/*    */   public Pagination getPage(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable, int pageNo, int pageSize)
/*    */   {
/* 26 */     return this.dao.getPage(siteId, sendUserId, receiverUserId, title, 
/* 27 */       sendBeginTime, sendEndTime, status, box, cacheable, pageNo, 
/* 28 */       pageSize);
/*    */   }
/*    */ 
/*    */   public CmsMessage findById(Integer id) {
/* 32 */     return this.dao.findById(id);
/*    */   }
/*    */ 
/*    */   public CmsMessage save(CmsMessage bean) {
/* 36 */     return this.dao.save(bean);
/*    */   }
/*    */ 
/*    */   public CmsMessage update(CmsMessage bean) {
/* 40 */     Updater updater = new Updater(bean);
/* 41 */     bean = this.dao.updateByUpdater(updater);
/* 42 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsMessage deleteById(Integer id) {
/* 46 */     return this.dao.deleteById(id);
/*    */   }
/*    */ 
/*    */   public CmsMessage[] deleteByIds(Integer[] ids) {
/* 50 */     return this.dao.deleteByIds(ids);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsMessageMngImpl
 * JD-Core Version:    0.6.0
 */