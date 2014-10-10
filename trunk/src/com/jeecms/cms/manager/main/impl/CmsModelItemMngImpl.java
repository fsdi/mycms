/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsModelItemDao;
/*    */ import com.jeecms.cms.entity.main.CmsModelItem;
/*    */ import com.jeecms.cms.manager.main.CmsModelItemMng;
/*    */ import com.jeecms.cms.manager.main.CmsModelMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsModelItemMngImpl
/*    */   implements CmsModelItemMng
/*    */ {
/*    */   private CmsModelMng cmsModelMng;
/*    */   private CmsModelItemDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsModelItem> getList(Integer modelId, boolean isChannel, boolean hasDisabled)
/*    */   {
/* 21 */     return this.dao.getList(modelId, isChannel, hasDisabled);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsModelItem findById(Integer id) {
/* 26 */     CmsModelItem entity = this.dao.findById(id);
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModelItem save(CmsModelItem bean, Integer modelId) {
/* 31 */     bean.setModel(this.cmsModelMng.findById(modelId));
/* 32 */     return save(bean);
/*    */   }
/*    */ 
/*    */   public CmsModelItem save(CmsModelItem bean) {
/* 36 */     bean.init();
/* 37 */     this.dao.save(bean);
/* 38 */     return bean;
/*    */   }
/*    */ 
/*    */   public void saveList(List<CmsModelItem> list) {
/* 42 */     for (CmsModelItem item : list)
/* 43 */       save(item);
/*    */   }
/*    */ 
/*    */   public void updatePriority(Integer[] wids, Integer[] priority, String[] label, Boolean[] single, Boolean[] display)
/*    */   {
/* 50 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 51 */       CmsModelItem item = findById(wids[i]);
/* 52 */       item.setLabel(label[i]);
/* 53 */       item.setPriority(priority[i]);
/* 54 */       item.setSingle(single[i]);
/* 55 */       item.setDisplay(display[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */   public CmsModelItem update(CmsModelItem bean) {
/* 60 */     Updater updater = new Updater(bean);
/* 61 */     CmsModelItem entity = this.dao.updateByUpdater(updater);
/* 62 */     entity.emptyToNull();
/* 63 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModelItem deleteById(Integer id) {
/* 67 */     CmsModelItem bean = this.dao.deleteById(id);
/* 68 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsModelItem[] deleteByIds(Integer[] ids) {
/* 72 */     CmsModelItem[] beans = new CmsModelItem[ids.length];
/* 73 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 74 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 76 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCmsModelMng(CmsModelMng cmsModelMng)
/*    */   {
/* 84 */     this.cmsModelMng = cmsModelMng;
/*    */   }
/*    */   @Autowired
/*    */   public void setDao(CmsModelItemDao dao) {
/* 89 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsModelItemMngImpl
 * JD-Core Version:    0.6.0
 */