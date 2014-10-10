/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsDictionaryDao;
/*    */ import com.jeecms.cms.entity.assist.CmsDictionary;
/*    */ import com.jeecms.cms.manager.assist.CmsDictionaryMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsDictionaryMngImpl
/*    */   implements CmsDictionaryMng
/*    */ {
/*    */   private CmsDictionaryDao dao;
/*    */ 
/*    */   public void init()
/*    */   {
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Pagination getPage(String queryType, int pageNo, int pageSize)
/*    */   {
/* 23 */     Pagination page = this.dao.getPage(queryType, pageNo, pageSize);
/* 24 */     return page;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsDictionary> getList(String queryType) {
/* 29 */     return this.dao.getList(queryType);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<String> getTypeList() {
/* 34 */     return this.dao.getTypeList();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsDictionary findById(Integer id) {
/* 39 */     CmsDictionary entity = this.dao.findById(id);
/* 40 */     return entity;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsDictionary findByValue(String type, String value) {
/* 45 */     CmsDictionary entity = this.dao.findByValue(type, value);
/* 46 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsDictionary save(CmsDictionary bean) {
/* 50 */     this.dao.save(bean);
/* 51 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsDictionary update(CmsDictionary bean) {
/* 55 */     Updater updater = new Updater(bean);
/* 56 */     bean = this.dao.updateByUpdater(updater);
/* 57 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsDictionary deleteById(Integer id) {
/* 61 */     CmsDictionary bean = this.dao.deleteById(id);
/* 62 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsDictionary[] deleteByIds(Integer[] ids) {
/* 66 */     CmsDictionary[] beans = new CmsDictionary[ids.length];
/* 67 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 68 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 70 */     return beans;
/*    */   }
/*    */ 
/*    */   public boolean dicDeplicateValue(String value, String type) {
/* 74 */     return this.dao.countByValue(value, type) > 0;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsDictionaryDao dao)
/*    */   {
/* 81 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsDictionaryMngImpl
 * JD-Core Version:    0.6.0
 */