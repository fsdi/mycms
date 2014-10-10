/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAcquisitionTempDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAcquisitionTemp;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsAcquisitionTempMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsAcquisitionTempMngImpl
/*    */   implements CmsAcquisitionTempMng
/*    */ {
/*    */   private CmsAcquisitionTempDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsAcquisitionTemp> getList(Integer siteId)
/*    */   {
/* 19 */     return this.dao.getList(siteId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsAcquisitionTemp findById(Integer id) {
/* 24 */     CmsAcquisitionTemp entity = this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp save(CmsAcquisitionTemp bean) {
/* 29 */     clear(bean.getSite().getId(), bean.getChannelUrl());
/* 30 */     this.dao.save(bean);
/* 31 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp update(CmsAcquisitionTemp bean) {
/* 35 */     Updater updater = new Updater(
/* 36 */       bean);
/* 37 */     bean = this.dao.updateByUpdater(updater);
/* 38 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp deleteById(Integer id) {
/* 42 */     CmsAcquisitionTemp bean = this.dao.deleteById(id);
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAcquisitionTemp[] deleteByIds(Integer[] ids) {
/* 47 */     CmsAcquisitionTemp[] beans = new CmsAcquisitionTemp[ids.length];
/* 48 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 49 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 51 */     return beans;
/*    */   }
/*    */ 
/*    */   public Integer getPercent(Integer siteId) {
/* 55 */     return this.dao.getPercent(siteId);
/*    */   }
/*    */ 
/*    */   public void clear(Integer siteId) {
/* 59 */     this.dao.clear(siteId, null);
/*    */   }
/*    */ 
/*    */   public void clear(Integer siteId, String channelUrl) {
/* 63 */     this.dao.clear(siteId, channelUrl);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsAcquisitionTempDao dao)
/*    */   {
/* 70 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsAcquisitionTempMngImpl
 * JD-Core Version:    0.6.0
 */