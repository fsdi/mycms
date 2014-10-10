/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsJobApplyDao;
/*    */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*    */ import com.jeecms.cms.manager.assist.CmsJobApplyMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsJobApplyMngImpl
/*    */   implements CmsJobApplyMng
/*    */ {
/*    */   private CmsJobApplyDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(Integer userId, Integer contentId, Integer siteId, boolean cacheable, int pageNo, int pageSize)
/*    */   {
/* 18 */     Pagination page = this.dao.getPage(userId, contentId, siteId, cacheable, pageNo, pageSize);
/* 19 */     return page;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsJobApply findById(Integer id) {
/* 24 */     CmsJobApply entity = this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsJobApply save(CmsJobApply bean) {
/* 29 */     this.dao.save(bean);
/* 30 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsJobApply update(CmsJobApply bean) {
/* 34 */     Updater updater = new Updater(bean);
/* 35 */     bean = this.dao.updateByUpdater(updater);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsJobApply deleteById(Integer id) {
/* 40 */     CmsJobApply bean = this.dao.deleteById(id);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsJobApply[] deleteByIds(Integer[] ids) {
/* 45 */     CmsJobApply[] beans = new CmsJobApply[ids.length];
/* 46 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 47 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 49 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsJobApplyDao dao)
/*    */   {
/* 56 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsJobApplyMngImpl
 * JD-Core Version:    0.6.0
 */