/*    */ package com.jeecms.common.lucene;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ import org.apache.lucene.util.NumericUtils;
/*    */ import org.springframework.util.Assert;
/*    */ 
/*    */ public class MoneyTools
/*    */ {
/* 12 */   private static final BigDecimal MULTIPLE = new BigDecimal(1000);
/*    */ 
/*    */   public static String moneyToString(BigDecimal money)
/*    */   {
/* 22 */     Assert.notNull(money);
/* 23 */     return NumericUtils.longToPrefixCoded(money.multiply(MULTIPLE)
/* 24 */       .longValue());
/*    */   }
/*    */ 
/*    */   public static BigDecimal stringToMoney(String s)
/*    */   {
/* 34 */     BigDecimal number = new BigDecimal(NumericUtils.prefixCodedToLong(s));
/* 35 */     return number.divide(MULTIPLE);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.lucene.MoneyTools
 * JD-Core Version:    0.6.0
 */