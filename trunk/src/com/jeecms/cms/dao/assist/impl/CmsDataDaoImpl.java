/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsDataDao;
/*    */ import com.jeecms.cms.entity.back.CmsConstraints;
/*    */ import com.jeecms.cms.entity.back.CmsField;
/*    */ import com.jeecms.cms.entity.back.CmsTable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.jdbc.core.JdbcTemplate;
/*    */ import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*    */ import org.springframework.jdbc.support.rowset.SqlRowSet;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsDataDaoImpl extends JdbcDaoSupport
/*    */   implements CmsDataDao
/*    */ {
/*    */   private String db;
/*    */ 
/*    */   public List<CmsTable> listTables()
/*    */   {
/* 20 */     String sql = "select table_name,table_comment,engine,table_rows,auto_increment from tables where table_schema='" + 
/* 21 */       this.db + "'";
/* 22 */     List tables = new ArrayList();
/* 23 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/* 24 */     while (set.next()) {
/* 25 */       CmsTable table = new CmsTable();
/* 26 */       table.setName(set.getString(1));
/* 27 */       table.setComment(set.getString(2).split(";")[0]);
/* 28 */       table.setEngine(set.getString(3));
/* 29 */       table.setRows(Integer.valueOf(set.getInt(4)));
/* 30 */       table.setAuto_increment(Integer.valueOf(set.getInt(5)));
/* 31 */       tables.add(table);
/*    */     }
/* 33 */     return tables;
/*    */   }
/*    */ 
/*    */   public CmsTable findTable(String tablename) {
/* 37 */     String sql = "select table_name,table_comment,engine,table_rows,auto_increment from tables where table_schema='" + 
/* 38 */       this.db + "' and table_name='" + tablename + "'";
/* 39 */     List tables = new ArrayList();
/* 40 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/* 41 */     while (set.next()) {
/* 42 */       CmsTable table = new CmsTable();
/* 43 */       table.setName(set.getString(1));
/* 44 */       table.setComment(set.getString(2).split(";")[0]);
/* 45 */       table.setEngine(set.getString(3));
/* 46 */       table.setRows(Integer.valueOf(set.getInt(4)));
/* 47 */       table.setAuto_increment(Integer.valueOf(set.getInt(5)));
/* 48 */       tables.add(table);
/*    */     }
/* 50 */     if (tables.size() > 0) {
/* 51 */       return (CmsTable)tables.get(0);
/*    */     }
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   public List<CmsField> listFields(String tablename)
/*    */   {
/* 59 */     String sql = "select column_name,column_type,column_default,column_key,column_comment,is_nullable,extra from columns where table_schema='" + 
/* 60 */       this.db + "' and table_name='" + tablename + "'";
/* 61 */     List fields = new ArrayList();
/* 62 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/* 63 */     while (set.next()) {
/* 64 */       CmsField field = new CmsField();
/* 65 */       field.setName(set.getString(1));
/* 66 */       field.setFieldType(set.getString(2));
/* 67 */       field.setFieldDefault(set.getString(3));
/* 68 */       field.setFieldProperty(set.getString(4));
/* 69 */       field.setComment(set.getString(5));
/* 70 */       field.setNullable(set.getString(6));
/* 71 */       field.setExtra(set.getString(7));
/* 72 */       fields.add(field);
/*    */     }
/* 74 */     return fields;
/*    */   }
/*    */ 
/*    */   public List<CmsConstraints> listConstraints(String tablename) {
/* 78 */     String sql = "select constraint_name,table_name,column_name,referenced_table_name,referenced_column_name from information_schema.KEY_COLUMN_USAGE where constraint_schema='" + 
/* 79 */       this.db + "' and table_name='" + tablename + "'";
/* 80 */     List constraints = new ArrayList();
/* 81 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/* 82 */     while (set.next()) {
/* 83 */       CmsConstraints constraint = new CmsConstraints();
/* 84 */       constraint.setName(set.getString(1));
/* 85 */       constraint.setTableName(set.getString(2));
/* 86 */       constraint.setColumnName(set.getString(3));
/* 87 */       constraint.setReferencedTableName(set.getString(4));
/* 88 */       constraint.setReferencedColumnName(set.getString(5));
/* 89 */       constraints.add(constraint);
/*    */     }
/* 91 */     return constraints;
/*    */   }
/*    */ 
/*    */   public String getDb() {
/* 95 */     return this.db;
/*    */   }
/*    */ 
/*    */   public void setDb(String db) {
/* 99 */     this.db = db;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsDataDaoImpl
 * JD-Core Version:    0.6.0
 */