/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class Updater<T>
/*     */ {
/*     */   private T bean;
/* 102 */   private Set<String> includeProperties = new HashSet();
/*     */ 
/* 104 */   private Set<String> excludeProperties = new HashSet();
/*     */ 
/* 106 */   private UpdateMode mode = UpdateMode.MIDDLE;
/*     */ 
/*     */   public Updater(T bean)
/*     */   {
/*  24 */     this.bean = bean;
/*     */   }
/*     */ 
/*     */   public Updater(T bean, UpdateMode mode)
/*     */   {
/*  37 */     this.bean = bean;
/*  38 */     this.mode = mode;
/*     */   }
/*     */ 
/*     */   public Updater<T> setUpdateMode(UpdateMode mode)
/*     */   {
/*  48 */     this.mode = mode;
/*  49 */     return this;
/*     */   }
/*     */ 
/*     */   public Updater<T> include(String property)
/*     */   {
/*  59 */     this.includeProperties.add(property);
/*  60 */     return this;
/*     */   }
/*     */ 
/*     */   public Updater<T> exclude(String property)
/*     */   {
/*  70 */     this.excludeProperties.add(property);
/*  71 */     return this;
/*     */   }
/*     */ 
/*     */   public boolean isUpdate(String name, Object value)
/*     */   {
/*  84 */     if (this.mode == UpdateMode.MAX)
/*  85 */       return !this.excludeProperties.contains(name);
/*  86 */     if (this.mode == UpdateMode.MIN)
/*  87 */       return this.includeProperties.contains(name);
/*  88 */     if (this.mode == UpdateMode.MIDDLE) {
/*  89 */       if (value != null) {
/*  90 */         return !this.excludeProperties.contains(name);
/*     */       }
/*  92 */       return this.includeProperties.contains(name);
/*     */     }
/*     */ 
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   public T getBean()
/*     */   {
/* 115 */     return this.bean;
/*     */   }
/*     */ 
/*     */   public Set<String> getExcludeProperties() {
/* 119 */     return this.excludeProperties;
/*     */   }
/*     */ 
/*     */   public Set<String> getIncludeProperties() {
/* 123 */     return this.includeProperties;
/*     */   }
/*     */ 
/*     */   public static enum UpdateMode
/*     */   {
/* 111 */     MAX, MIN, MIDDLE;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.Updater
 * JD-Core Version:    0.6.0
 */