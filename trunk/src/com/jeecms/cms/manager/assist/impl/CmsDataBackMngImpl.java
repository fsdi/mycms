/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsDataBackDao;
/*    */ import com.jeecms.cms.entity.back.CmsField;
/*    */ import com.jeecms.cms.manager.assist.CmsDataBackMng;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsDataBackMngImpl
/*    */   implements CmsDataBackMng
/*    */ {
/*    */   private CmsDataBackDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public String createTableDDL(String tablename)
/*    */   {
/* 20 */     return this.dao.createTableDDL(tablename);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<Object[]> createTableData(String tablename) {
/* 25 */     return this.dao.createTableData(tablename);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsField> listFields(String tablename) {
/* 30 */     return this.dao.listFields(tablename);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<String> listTabels(String catalog) {
/* 35 */     return this.dao.listTables(catalog);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public List<String> listDataBases() {
/* 40 */     return this.dao.listDataBases();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public String getDefaultCatalog() throws SQLException {
/* 45 */     return this.dao.getDefaultCatalog();
/*    */   }
/*    */ 
/*    */   public void setDefaultCatalog(String catalog) throws SQLException {
/* 49 */     this.dao.setDefaultCatalog(catalog);
/*    */   }
/*    */ 
/*    */   public Boolean executeSQL(String sql) {
/* 53 */     return this.dao.executeSQL(sql);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsDataBackDao dao)
/*    */   {
/* 60 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsDataBackMngImpl
 * JD-Core Version:    0.6.0
 */