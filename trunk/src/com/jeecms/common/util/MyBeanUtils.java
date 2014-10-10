/*     */ package com.jeecms.common.util;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Locale;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public class MyBeanUtils
/*     */ {
/*     */   public static Object getFieldValue(Object object, String fieldName)
/*     */   {
/*  16 */     Field field = getDeclaredField(object, fieldName);
/*     */ 
/*  18 */     if (field == null) {
/*  19 */       throw new IllegalArgumentException("Could not find field [" + 
/*  20 */         fieldName + "] on target [" + object + "]");
/*     */     }
/*     */ 
/*  23 */     makeAccessible(field);
/*     */ 
/*  25 */     Object result = null;
/*     */     try {
/*  27 */       result = field.get(object);
/*     */     } catch (IllegalAccessException e) {
/*  29 */       throw new RuntimeException("never happend exception!", e);
/*     */     }
/*  31 */     return result;
/*     */   }
/*     */ 
/*     */   public static void setFieldValue(Object object, String fieldName, Object value)
/*     */   {
/*  39 */     Field field = getDeclaredField(object, fieldName);
/*     */ 
/*  41 */     if (field == null) {
/*  42 */       throw new IllegalArgumentException("Could not find field [" + 
/*  43 */         fieldName + "] on target [" + object + "]");
/*     */     }
/*     */ 
/*  46 */     makeAccessible(field);
/*     */     try
/*     */     {
/*  49 */       field.set(object, value);
/*     */     } catch (IllegalAccessException e) {
/*  51 */       throw new RuntimeException("never happend exception!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static Field getDeclaredField(Object object, String fieldName)
/*     */   {
/*  60 */     Assert.notNull(object);
/*  61 */     return getDeclaredField(object.getClass(), fieldName);
/*     */   }
/*     */ 
/*     */   protected static Field getDeclaredField(Class clazz, String fieldName)
/*     */   {
/*  70 */     Assert.notNull(clazz);
/*  71 */     Assert.hasText(fieldName);
/*  72 */     for (Class superClass = clazz; superClass != Object.class; ) {
/*     */       try
/*     */       {
/*  75 */         return superClass.getDeclaredField(fieldName);
/*     */       }
/*     */       catch (NoSuchFieldException localNoSuchFieldException)
/*     */       {
/*  72 */         superClass = superClass
/*  73 */           .getSuperclass();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  80 */     return null;
/*     */   }
/*     */ 
/*     */   protected static void makeAccessible(Field field)
/*     */   {
/*  87 */     if ((!Modifier.isPublic(field.getModifiers())) || 
/*  88 */       (!Modifier.isPublic(field.getDeclaringClass().getModifiers())))
/*  89 */       field.setAccessible(true);
/*     */   }
/*     */ 
/*     */   public static Object getSimpleProperty(Object bean, String propName)
/*     */     throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
/*     */   {
/*  97 */     return bean.getClass().getMethod(getReadMethod(propName), new Class[0]).invoke(bean, new Object[0]);
/*     */   }
/*     */ 
/*     */   private static String getReadMethod(String name) {
/* 101 */     return "get" + name.substring(0, 1).toUpperCase(Locale.ENGLISH) + 
/* 102 */       name.substring(1);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.MyBeanUtils
 * JD-Core Version:    0.6.0
 */