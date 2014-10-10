/*    */ package com.jeecms.common.util;
/*    */ 
/*    */ public class ArithmeticUtils
/*    */ {
/*    */   public static int dividend(int dividend)
/*    */   {
/*  8 */     return dividend == 0 ? 1 : dividend;
/*    */   }
/*    */ 
/*    */   public static long dividend(long dividend) {
/* 12 */     return dividend == 0L ? 1L : dividend;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.util.ArithmeticUtils
 * JD-Core Version:    0.6.0
 */