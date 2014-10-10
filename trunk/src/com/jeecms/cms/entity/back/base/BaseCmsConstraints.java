/*    */ package com.jeecms.cms.entity.back.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class BaseCmsConstraints
/*    */   implements Serializable
/*    */ {
/* 15 */   private int hashCode = -2147483648;
/*    */   private String name;
/*    */   private String tableName;
/*    */   private String columnName;
/*    */   private String referencedTableName;
/*    */   private String referencedColumnName;
/*    */ 
/*    */   public BaseCmsConstraints()
/*    */   {
/*  9 */     initialize();
/*    */   }
/*    */ 
/*    */   protected void initialize()
/*    */   {
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 29 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getTableName() {
/* 33 */     return this.tableName;
/*    */   }
/*    */ 
/*    */   public void setTableName(String tableName) {
/* 37 */     this.tableName = tableName;
/*    */   }
/*    */ 
/*    */   public String getColumnName() {
/* 41 */     return this.columnName;
/*    */   }
/*    */ 
/*    */   public void setColumnName(String columnName) {
/* 45 */     this.columnName = columnName;
/*    */   }
/*    */ 
/*    */   public String getReferencedTableName() {
/* 49 */     return this.referencedTableName;
/*    */   }
/*    */ 
/*    */   public void setReferencedTableName(String referencedTableName) {
/* 53 */     this.referencedTableName = referencedTableName;
/*    */   }
/*    */ 
/*    */   public String getReferencedColumnName() {
/* 57 */     return this.referencedColumnName;
/*    */   }
/*    */ 
/*    */   public void setReferencedColumnName(String referencedColumnName) {
/* 61 */     this.referencedColumnName = referencedColumnName;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 65 */     return super.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.back.base.BaseCmsConstraints
 * JD-Core Version:    0.6.0
 */