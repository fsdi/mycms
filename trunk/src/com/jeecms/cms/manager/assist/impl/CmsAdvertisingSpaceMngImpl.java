/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAdvertisingSpaceDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsAdvertisingSpaceMngImpl
/*    */   implements CmsAdvertisingSpaceMng
/*    */ {
/*    */   private CmsAdvertisingSpaceDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsAdvertisingSpace> getList(Integer siteId)
/*    */   {
/* 19 */     return this.dao.getList(siteId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsAdvertisingSpace findById(Integer id) {
/* 24 */     CmsAdvertisingSpace entity = this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace save(CmsAdvertisingSpace bean) {
/* 29 */     this.dao.save(bean);
/* 30 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace update(CmsAdvertisingSpace bean) {
/* 34 */     Updater updater = new Updater(
/* 35 */       bean);
/* 36 */     bean = this.dao.updateByUpdater(updater);
/* 37 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace deleteById(Integer id) {
/* 41 */     CmsAdvertisingSpace bean = this.dao.deleteById(id);
/* 42 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertisingSpace[] deleteByIds(Integer[] ids) {
/* 46 */     CmsAdvertisingSpace[] beans = new CmsAdvertisingSpace[ids.length];
/* 47 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 48 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 50 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsAdvertisingSpaceDao dao)
/*    */   {
/* 57 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsAdvertisingSpaceMngImpl
 * JD-Core Version:    0.6.0
 */