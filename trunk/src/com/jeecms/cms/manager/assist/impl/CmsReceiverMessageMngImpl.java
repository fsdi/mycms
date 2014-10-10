/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsReceiverMessageDao;
/*    */ import com.jeecms.cms.entity.assist.CmsReceiverMessage;
/*    */ import com.jeecms.cms.manager.assist.CmsReceiverMessageMng;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsReceiverMessageMngImpl
/*    */   implements CmsReceiverMessageMng
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsReceiverMessageDao dao;
/*    */ 
/*    */   public Pagination getPage(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable, int pageNo, int pageSize)
/*    */   {
/* 27 */     return this.dao.getPage(siteId, sendUserId, receiverUserId, title, 
/* 28 */       sendBeginTime, sendEndTime, status, box, cacheable, pageNo, 
/* 29 */       pageSize);
/*    */   }
/*    */ 
/*    */   public List getList(Integer siteId, Integer sendUserId, Integer receiverUserId, String title, Date sendBeginTime, Date sendEndTime, Boolean status, Integer box, Boolean cacheable)
/*    */   {
/* 35 */     return this.dao.getList(siteId, sendUserId, receiverUserId, title, 
/* 36 */       sendBeginTime, sendEndTime, status, box, cacheable);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage findById(Integer id) {
/* 40 */     return this.dao.findById(id);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage save(CmsReceiverMessage bean) {
/* 44 */     return this.dao.save(bean);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage update(CmsReceiverMessage bean) {
/* 48 */     return this.dao.update(bean);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage deleteById(Integer id) {
/* 52 */     return this.dao.deleteById(id);
/*    */   }
/*    */ 
/*    */   public CmsReceiverMessage[] deleteByIds(Integer[] ids) {
/* 56 */     return this.dao.deleteByIds(ids);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsReceiverMessageMngImpl
 * JD-Core Version:    0.6.0
 */