/*    */ package com.jeecms.cms.entity.back.base;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class BaseCmsField
/*    */   implements Serializable
/*    */ {
/* 15 */   private int hashCode = -2147483648;
/*    */   private String name;
/*    */   private String fieldType;
/*    */   private String fieldDefault;
/*    */   private String fieldProperty;
/*    */   private String comment;
/*    */   private String nullable;
/*    */   private String extra;
/*    */ 
/*    */   public BaseCmsField()
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
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */   public void setName(String name) {
/* 31 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getFieldType() {
/* 35 */     return this.fieldType;
/*    */   }
/*    */ 
/*    */   public void setFieldType(String fieldType) {
/* 39 */     this.fieldType = fieldType;
/*    */   }
/*    */ 
/*    */   public String getFieldDefault() {
/* 43 */     return this.fieldDefault;
/*    */   }
/*    */ 
/*    */   public void setFieldDefault(String fieldDefault) {
/* 47 */     this.fieldDefault = fieldDefault;
/*    */   }
/*    */ 
/*    */   public String getFieldProperty() {
/* 51 */     return this.fieldProperty;
/*    */   }
/*    */ 
/*    */   public void setFieldProperty(String fieldProperty) {
/* 55 */     this.fieldProperty = fieldProperty;
/*    */   }
/*    */ 
/*    */   public String getComment() {
/* 59 */     return this.comment;
/*    */   }
/*    */ 
/*    */   public void setComment(String comment) {
/* 63 */     this.comment = comment;
/*    */   }
/*    */ 
/*    */   public String getNullable() {
/* 67 */     return this.nullable;
/*    */   }
/*    */ 
/*    */   public void setNullable(String nullable) {
/* 71 */     this.nullable = nullable;
/*    */   }
/*    */ 
/*    */   public String getExtra() {
/* 75 */     return this.extra;
/*    */   }
/*    */ 
/*    */   public void setExtra(String extra) {
/* 79 */     this.extra = extra;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 83 */     return super.toString();
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.back.base.BaseCmsField
 * JD-Core Version:    0.6.0
 */