/*    */ package com.jeecms.core.web;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class CoreUtils
/*    */ {
/*    */   public static List<String> tplTrim(List<String> list, String prefix, String include, String[] excludes)
/*    */   {
/* 27 */     List result = new ArrayList(list.size());
/* 28 */     if ((!StringUtils.isBlank(include)) && (!list.contains(include)) && 
/* 29 */       (!tplContain(excludes, include))) {
/* 30 */       result.add(include.substring(prefix.length()));
/*    */     }
/*    */ 
/* 33 */     for (String t : list) {
/* 34 */       if (!tplContain(excludes, t)) {
/* 35 */         result.add(t.substring(prefix.length()));
/*    */       }
/*    */     }
/* 38 */     return result;
/*    */   }
/*    */ 
/*    */   private static boolean tplContain(String[] excludes, String tpl)
/*    */   {
/* 49 */     int start = tpl.lastIndexOf("/");
/* 50 */     int end = tpl.lastIndexOf(".");
/* 51 */     if ((start == -1) || (end == -1)) {
/* 52 */       throw new RuntimeException("tpl not contain '/' or '.':" + tpl);
/*    */     }
/* 54 */     String name = tpl.substring(start + 1, end);
/* 55 */     String[] arrayOfString = excludes; int j = excludes.length; for (int i = 0; i < j; i++) { String e = arrayOfString[i];
/* 56 */       if (e.equals(name)) {
/* 57 */         return true;
/*    */       }
/*    */     }
/* 60 */     return false;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.web.CoreUtils
 * JD-Core Version:    0.6.0
 */