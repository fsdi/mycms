/*    */ package com.jeecms.common.util;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateFormatUtils extends org.apache.commons.lang.time.DateFormatUtils
/*    */ {
/*    */   public static String formatDate(Date date)
/*    */   {
/* 10 */     return DateFormat.getDateInstance().format(date);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.DateFormatUtils
 * JD-Core Version:    0.6.0
 */