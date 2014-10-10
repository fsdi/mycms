/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsAdvertisingDao;
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingMng;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsAdvertisingMngImpl
/*    */   implements CmsAdvertisingMng
/*    */ {
/*    */   private CmsAdvertisingSpaceMng cmsAdvertisingSpaceMng;
/*    */   private CmsAdvertisingDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(Integer siteId, Integer adspaceId, Boolean enabled, int pageNo, int pageSize)
/*    */   {
/* 23 */     Pagination page = this.dao.getPage(siteId, adspaceId, enabled, pageNo, 
/* 24 */       pageSize);
/* 25 */     return page;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsAdvertising> getList(Integer adspaceId, Boolean enabled) {
/* 30 */     return this.dao.getList(adspaceId, enabled);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsAdvertising findById(Integer id) {
/* 35 */     CmsAdvertising entity = this.dao.findById(id);
/* 36 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising save(CmsAdvertising bean, Integer adspaceId, Map<String, String> attr)
/*    */   {
/* 41 */     bean.setAdspace(this.cmsAdvertisingSpaceMng.findById(adspaceId));
/* 42 */     bean.setAttr(attr);
/* 43 */     bean.init();
/* 44 */     this.dao.save(bean);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising update(CmsAdvertising bean, Integer adspaceId, Map<String, String> attr)
/*    */   {
/* 50 */     Updater updater = new Updater(bean);
/* 51 */     updater.include(CmsAdvertising.PROP_CODE);
/* 52 */     bean = this.dao.updateByUpdater(updater);
/* 53 */     bean.setAdspace(this.cmsAdvertisingSpaceMng.findById(adspaceId));
/* 54 */     bean.getAttr().clear();
/* 55 */     bean.getAttr().putAll(attr);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising deleteById(Integer id) {
/* 60 */     CmsAdvertising bean = this.dao.deleteById(id);
/* 61 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsAdvertising[] deleteByIds(Integer[] ids) {
/* 65 */     CmsAdvertising[] beans = new CmsAdvertising[ids.length];
/* 66 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 67 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 69 */     return beans;
/*    */   }
/*    */ 
/*    */   public void display(Integer id) {
/* 73 */     CmsAdvertising ad = findById(id);
/* 74 */     if (ad != null)
/* 75 */       ad.setDisplayCount(Long.valueOf(ad.getDisplayCount().longValue() + 1L));
/*    */   }
/*    */ 
/*    */   public void click(Integer id)
/*    */   {
/* 80 */     CmsAdvertising ad = findById(id);
/* 81 */     if (ad != null)
/* 82 */       ad.setClickCount(Long.valueOf(ad.getClickCount().longValue() + 1L));
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCmsAdvertisingSpaceMng(CmsAdvertisingSpaceMng cmsAdvertisingSpaceMng)
/*    */   {
/* 92 */     this.cmsAdvertisingSpaceMng = cmsAdvertisingSpaceMng;
/*    */   }
/*    */   @Autowired
/*    */   public void setDao(CmsAdvertisingDao dao) {
/* 97 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsAdvertisingMngImpl
 * JD-Core Version:    0.6.0
 */