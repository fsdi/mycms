/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentTypeDao;
/*    */ import com.jeecms.cms.entity.main.ContentType;
/*    */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ContentTypeMngImpl
/*    */   implements ContentTypeMng
/*    */ {
/*    */   private ContentTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<ContentType> getList(boolean containDisabled)
/*    */   {
/* 19 */     return this.dao.getList(containDisabled);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public ContentType getDef() {
/* 24 */     return this.dao.getDef();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public ContentType findById(Integer id) {
/* 29 */     ContentType entity = this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentType save(ContentType bean) {
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public ContentType update(ContentType bean) {
/* 39 */     Updater updater = new Updater(bean);
/* 40 */     ContentType entity = this.dao.updateByUpdater(updater);
/* 41 */     return entity;
/*    */   }
/*    */ 
/*    */   public ContentType deleteById(Integer id) {
/* 45 */     ContentType bean = this.dao.deleteById(id);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public ContentType[] deleteByIds(Integer[] ids) {
/* 50 */     ContentType[] beans = new ContentType[ids.length];
/* 51 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 52 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 54 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ContentTypeDao dao)
/*    */   {
/* 61 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentTypeMngImpl
 * JD-Core Version:    0.6.0
 */