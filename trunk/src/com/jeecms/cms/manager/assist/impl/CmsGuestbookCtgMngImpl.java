/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsGuestbookCtgDao;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
/*    */ import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsGuestbookCtgMngImpl
/*    */   implements CmsGuestbookCtgMng
/*    */ {
/*    */   private CmsGuestbookCtgDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsGuestbookCtg> getList(Integer siteId)
/*    */   {
/* 19 */     return this.dao.getList(siteId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsGuestbookCtg findById(Integer id) {
/* 24 */     CmsGuestbookCtg entity = this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg save(CmsGuestbookCtg bean) {
/* 29 */     this.dao.save(bean);
/* 30 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg update(CmsGuestbookCtg bean) {
/* 34 */     Updater updater = new Updater(bean);
/* 35 */     bean = this.dao.updateByUpdater(updater);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg deleteById(Integer id) {
/* 40 */     CmsGuestbookCtg bean = this.dao.deleteById(id);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookCtg[] deleteByIds(Integer[] ids) {
/* 45 */     CmsGuestbookCtg[] beans = new CmsGuestbookCtg[ids.length];
/* 46 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 47 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 49 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsGuestbookCtgDao dao)
/*    */   {
/* 56 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsGuestbookCtgMngImpl
 * JD-Core Version:    0.6.0
 */