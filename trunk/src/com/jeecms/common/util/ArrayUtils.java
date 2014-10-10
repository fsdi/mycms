/*    */ package com.jeecms.common.util;
/*    */ 
/*    */ public class ArrayUtils
/*    */ {
/*    */   public static Integer[] convertStrArrayToInt(String[] strArray)
/*    */   {
/*  7 */     if ((strArray != null) && (strArray.length > 0)) {
/*  8 */       Integer[] array = new Integer[strArray.length];
/*  9 */       for (int i = 0; i < strArray.length; i++) {
/* 10 */         array[i] = Integer.valueOf(Integer.parseInt(strArray[i]));
/*    */       }
/* 12 */       return array;
/*    */     }
/* 14 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.ArrayUtils
 * JD-Core Version:    0.6.0
 */