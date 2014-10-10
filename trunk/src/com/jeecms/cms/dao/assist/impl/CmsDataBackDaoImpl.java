/*     */ package com.jeecms.cms.dao.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.Constants;
/*     */ import com.jeecms.cms.dao.assist.CmsDataBackDao;
/*     */ import com.jeecms.cms.entity.back.CmsField;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.support.JdbcDaoSupport;
/*     */ import org.springframework.jdbc.support.rowset.SqlRowSet;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class CmsDataBackDaoImpl extends JdbcDaoSupport
/*     */   implements CmsDataBackDao
/*     */ {
/*     */   public String createTableDDL(String tablename)
/*     */   {
/*  23 */     String sql = " show create table " + tablename;
/*  24 */     String ddl = (String)getJdbcTemplate().queryForObject(sql, 
/*  25 */       new RowMapper()
/*     */     {
/*     */       public String mapRow(ResultSet set, int arg1) throws SQLException {
/*  28 */         return set.getString(2);
/*     */       }
/*     */     });
/*  31 */     return ddl;
/*     */   }
/*     */ 
/*     */   public List<Object[]> createTableData(String tablename) {
/*  35 */     int filedNum = getTableFieldNums(tablename);
/*  36 */     List results = new ArrayList();
/*  37 */     String sql = " select * from   " + tablename;
/*  38 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/*  39 */     while (set.next()) {
/*  40 */       Object[] oneResult = new Object[filedNum];
/*  41 */       for (int i = 1; i <= filedNum; i++) {
/*  42 */         oneResult[(i - 1)] = set.getObject(i);
/*     */       }
/*  44 */       results.add(oneResult);
/*     */     }
/*  46 */     return results;
/*     */   }
/*     */ 
/*     */   public List<CmsField> listFields(String tablename) {
/*  50 */     String sql = " desc  " + tablename;
/*  51 */     List fields = new ArrayList();
/*  52 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/*  53 */     while (set.next()) {
/*  54 */       CmsField field = new CmsField();
/*  55 */       field.setName(set.getString(1));
/*  56 */       field.setFieldType(set.getString(2));
/*  57 */       field.setNullable(set.getString(3));
/*  58 */       field.setFieldProperty(set.getString(4));
/*  59 */       field.setFieldDefault(set.getString(5));
/*  60 */       field.setExtra(set.getString(6));
/*  61 */       fields.add(field);
/*     */     }
/*  63 */     return fields;
/*     */   }
/*     */ 
/*     */   public List<String> listTables(String catalog)
/*     */   {
/*  68 */     String sql = " SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA='" + catalog + "' ";
/*  69 */     List tables = new ArrayList();
/*  70 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/*  71 */     while (set.next()) {
/*  72 */       tables.add(set.getString(1));
/*     */     }
/*  74 */     return tables;
/*     */   }
/*     */ 
/*     */   public List<String> listDataBases() {
/*  78 */     String sql = " show  databases ";
/*  79 */     List tables = new ArrayList();
/*  80 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/*  81 */     while (set.next()) {
/*  82 */       tables.add(set.getString(1));
/*     */     }
/*  84 */     return tables;
/*     */   }
/*     */   public String getDefaultCatalog() throws SQLException {
/*  87 */     return getJdbcTemplate().getDataSource().getConnection().getCatalog();
/*     */   }
/*     */ 
/*     */   public void setDefaultCatalog(String catalog) throws SQLException {
/*  91 */     getJdbcTemplate().getDataSource().getConnection().setCatalog(catalog);
/*     */   }
/*     */ 
/*     */   private int getTableFieldNums(String tablename) {
/*  95 */     String sql = " desc  " + tablename;
/*  96 */     SqlRowSet set = getJdbcTemplate().queryForRowSet(sql);
/*  97 */     int rownum = 0;
/*  98 */     while (set.next()) {
/*  99 */       rownum++;
/*     */     }
/* 101 */     return rownum;
/*     */   }
/*     */ 
/*     */   public Boolean executeSQL(String sql) {
/*     */     try {
/* 106 */       String[] s = sql.split(Constants.ONESQL_PREFIX);
/* 107 */       for (String sqls : s)
/* 108 */         if (StringUtils.isNotBlank(sqls))
/* 109 */           getJdbcTemplate().execute(sqls.trim());
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 113 */       e.printStackTrace();
/* 114 */       return Boolean.valueOf(false);
/*     */     }
/* 116 */     return Boolean.valueOf(true);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsDataBackDaoImpl
 * JD-Core Version:    0.6.0
 */