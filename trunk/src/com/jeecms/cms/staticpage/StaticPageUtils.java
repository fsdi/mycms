/*    */ package com.jeecms.cms.staticpage;
/*    */ 
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class StaticPageUtils
/*    */ {
/*    */   public static String staticUrlRule(String rule, Integer modelId, String modelPath, Integer channelId, String channelPath, Integer contentId, Date date)
/*    */   {
/* 24 */     if (StringUtils.isBlank(rule)) {
/* 25 */       return rule;
/*    */     }
/* 27 */     if (modelId != null) {
/* 28 */       rule = StringUtils.replace(rule, "${modelId}", modelId.toString());
/*    */     }
/* 30 */     if (!StringUtils.isBlank(modelPath)) {
/* 31 */       rule = StringUtils.replace(rule, "${modelPath}", modelPath);
/*    */     }
/* 33 */     if (channelId != null) {
/* 34 */       rule = StringUtils.replace(rule, "${channelId}", 
/* 35 */         channelId.toString());
/*    */     }
/* 37 */     if (StringUtils.isBlank(channelPath)) {
/* 38 */       rule = StringUtils.replace(rule, "${channelPath}", channelPath);
/*    */     }
/* 40 */     if (contentId != null) {
/* 41 */       rule = StringUtils.replace(rule, "${contentId}", 
/* 42 */         contentId.toString());
/*    */     }
/* 44 */     if (date != null)
/*    */     {
/* 46 */       Calendar cal = Calendar.getInstance();
/* 47 */       cal.setTime(date);
/* 48 */       int year = cal.get(1);
/* 49 */       int month = cal.get(2) + 1;
/* 50 */       String mm = "0" + month;
/* 51 */       int day = cal.get(5);
/* 52 */       String dd = "0" + day;
/* 53 */       long time = date.getTime();
/* 54 */       rule = StringUtils.replace(rule, "${year}", String.valueOf(year));
/* 55 */       rule = StringUtils.replace(rule, "${month}", String.valueOf(month));
/* 56 */       rule = StringUtils.replace(rule, "${MM}", mm);
/* 57 */       rule = StringUtils.replace(rule, "${day}", String.valueOf(day));
/* 58 */       rule = StringUtils.replace(rule, "${DD}", dd);
/* 59 */       rule = StringUtils.replace(rule, "${time}", String.valueOf(time));
/*    */     }
/* 61 */     return rule;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.StaticPageUtils
 * JD-Core Version:    0.6.0
 */