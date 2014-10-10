/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsModelDao;
/*    */ import com.jeecms.cms.entity.main.CmsModel;
/*    */ import com.jeecms.cms.manager.main.CmsModelMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsModelMngImpl
/*    */   implements CmsModelMng
/*    */ {
/*    */   private CmsModelDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsModel> getList(boolean containDisabled, Boolean hasContent)
/*    */   {
/* 19 */     return this.dao.getList(containDisabled, hasContent);
/*    */   }
/*    */ 
/*    */   public CmsModel getDefModel() {
/* 23 */     return this.dao.getDefModel();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsModel findById(Integer id) {
/* 28 */     CmsModel entity = this.dao.findById(id);
/* 29 */     return entity;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsModel findByPath(String path) {
/* 34 */     CmsModel entity = this.dao.findByPath(path);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModel save(CmsModel bean) {
/* 39 */     bean.init();
/* 40 */     this.dao.save(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsModel update(CmsModel bean) {
/* 45 */     Updater updater = new Updater(bean);
/* 46 */     CmsModel entity = this.dao.updateByUpdater(updater);
/* 47 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsModel deleteById(Integer id) {
/* 51 */     CmsModel bean = this.dao.deleteById(id);
/* 52 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsModel[] deleteByIds(Integer[] ids) {
/* 56 */     CmsModel[] beans = new CmsModel[ids.length];
/* 57 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 58 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 60 */     return beans;
/*    */   }
/*    */ 
/*    */   public CmsModel[] updatePriority(Integer[] ids, Integer[] priority, Boolean[] disabled, Integer defId)
/*    */   {
/* 65 */     int len = ids.length;
/* 66 */     CmsModel[] beans = new CmsModel[len];
/* 67 */     for (int i = 0; i < len; i++) {
/* 68 */       beans[i] = findById(ids[i]);
/* 69 */       beans[i].setPriority(priority[i]);
/* 70 */       beans[i].setDisabled(disabled[i]);
/* 71 */       if (beans[i].getId().equals(defId))
/* 72 */         beans[i].setDef(Boolean.valueOf(true));
/*    */       else {
/* 74 */         beans[i].setDef(Boolean.valueOf(false));
/*    */       }
/*    */     }
/* 77 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsModelDao dao)
/*    */   {
/* 84 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsModelMngImpl
 * JD-Core Version:    0.6.0
 */