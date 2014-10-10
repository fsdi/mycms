/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsSensitivityDao;
/*    */ import com.jeecms.cms.entity.assist.CmsSensitivity;
/*    */ import com.jeecms.cms.manager.assist.CmsSensitivityMng;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsSensitivityMngImpl
/*    */   implements CmsSensitivityMng
/*    */ {
/*    */   private CmsSensitivityDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public String replaceSensitivity(String s)
/*    */   {
/* 19 */     if (StringUtils.isBlank(s)) {
/* 20 */       return s;
/*    */     }
/* 22 */     List<CmsSensitivity> list = getList(true);
/* 23 */     for (CmsSensitivity sen : list) {
/* 24 */       s = StringUtils.replace(s, sen.getSearch(), sen.getReplacement());
/*    */     }
/* 26 */     return s;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsSensitivity> getList(boolean cacheable) {
/* 31 */     return this.dao.getList(cacheable);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsSensitivity findById(Integer id) {
/* 36 */     CmsSensitivity entity = this.dao.findById(id);
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsSensitivity save(CmsSensitivity bean) {
/* 41 */     this.dao.save(bean);
/* 42 */     return bean;
/*    */   }
/*    */ 
/*    */   public void updateEnsitivity(Integer[] ids, String[] searchs, String[] replacements)
/*    */   {
/* 48 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 49 */       CmsSensitivity ensitivity = findById(ids[i]);
/* 50 */       ensitivity.setSearch(searchs[i]);
/* 51 */       ensitivity.setReplacement(replacements[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */   public CmsSensitivity deleteById(Integer id) {
/* 56 */     CmsSensitivity bean = this.dao.deleteById(id);
/* 57 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsSensitivity[] deleteByIds(Integer[] ids) {
/* 61 */     CmsSensitivity[] beans = new CmsSensitivity[ids.length];
/* 62 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 63 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 65 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsSensitivityDao dao)
/*    */   {
/* 72 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsSensitivityMngImpl
 * JD-Core Version:    0.6.0
 */