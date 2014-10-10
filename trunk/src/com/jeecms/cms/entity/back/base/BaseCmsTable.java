/*    */ package com.jeecms.cms.entity.back.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class BaseCmsTable
/*    */   implements Serializable
/*    */ {
/* 15 */   private int hashCode = -2147483648;
/*    */   private String name;
/*    */   private String comment;
/*    */   private String engine;
/*    */   private Integer rows;
/*    */   private Integer auto_increment;
/*    */ 
/*    */   public BaseCmsTable()
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
/*    */   public String getComment() {
/* 33 */     return this.comment;
/*    */   }
/*    */ 
/*    */   public void setComment(String comment) {
/* 37 */     this.comment = comment;
/*    */   }
/*    */ 
/*    */   public String getEngine() {
/* 41 */     return this.engine;
/*    */   }
/*    */ 
/*    */   public void setEngine(String engine) {
/* 45 */     this.engine = engine;
/*    */   }
/*    */ 
/*    */   public Integer getRows() {
/* 49 */     return this.rows;
/*    */   }
/*    */ 
/*    */   public void setRows(Integer rows) {
/* 53 */     this.rows = rows;
/*    */   }
/*    */ 
/*    */   public Integer getAuto_increment() {
/* 57 */     return this.auto_increment;
/*    */   }
/*    */ 
/*    */   public void setAuto_increment(Integer auto_increment) {
/* 61 */     this.auto_increment = auto_increment;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 65 */     return super.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.back.base.BaseCmsTable
 * JD-Core Version:    0.6.0
 */