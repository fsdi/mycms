/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsDataDao;
/*    */ import com.jeecms.cms.entity.back.CmsConstraints;
/*    */ import com.jeecms.cms.entity.back.CmsField;
/*    */ import com.jeecms.cms.entity.back.CmsTable;
/*    */ import com.jeecms.cms.manager.assist.CmsDataMng;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsDataMngImpl
/*    */   implements CmsDataMng
/*    */ {
/*    */   private CmsDataDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsTable> listTabels()
/*    */   {
/* 21 */     return this.dao.listTables();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsTable findTable(String tablename) {
/* 26 */     return this.dao.findTable(tablename);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsField> listFields(String tablename) {
/* 31 */     return this.dao.listFields(tablename);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsConstraints> listConstraints(String tablename) {
/* 36 */     return this.dao.listConstraints(tablename);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsDataDao dao)
/*    */   {
/* 44 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsDataMngImpl
 * JD-Core Version:    0.6.0
 */