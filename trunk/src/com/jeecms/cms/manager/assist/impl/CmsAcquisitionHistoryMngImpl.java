/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAcquisitionHistoryDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*    */ import com.jeecms.cms.manager.assist.CmsAcquisitionHistoryMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsAcquisitionHistoryMngImpl
/*    */   implements CmsAcquisitionHistoryMng
/*    */ {
/*    */   private CmsAcquisitionHistoryDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsAcquisitionHistory> getList(Integer siteId, Integer acquId)
/*    */   {
/* 20 */     return this.dao.getList(siteId, acquId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(Integer siteId, Integer acquId, Integer pageNo, Integer pageSize) {
/* 25 */     return this.dao.getPage(siteId, acquId, pageNo, pageSize);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsAcquisitionHistory findById(Integer id) {
/* 30 */     CmsAcquisitionHistory entity = this.dao.findById(id);
/* 31 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory save(CmsAcquisitionHistory bean)
/*    */   {
/* 36 */     this.dao.save(bean);
/* 37 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory update(CmsAcquisitionHistory bean) {
/* 41 */     Updater updater = new Updater(bean);
/* 42 */     bean = this.dao.updateByUpdater(updater);
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory deleteById(Integer id) {
/* 47 */     CmsAcquisitionHistory bean = this.dao.deleteById(id);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionHistory[] deleteByIds(Integer[] ids) {
/* 52 */     CmsAcquisitionHistory[] beans = new CmsAcquisitionHistory[ids.length];
/* 53 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 54 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 56 */     return beans;
/*    */   }
/*    */ 
/*    */   public Boolean checkExistByProperties(Boolean title, String value) {
/* 60 */     return this.dao.checkExistByProperties(title, value);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsAcquisitionHistoryDao dao)
/*    */   {
/* 67 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsAcquisitionHistoryMngImpl
 * JD-Core Version:    0.6.0
 */