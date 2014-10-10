/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class AccountExpiredException extends AccountStatusException
/*    */ {
/*    */   public AccountExpiredException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public AccountExpiredException(String msg)
/*    */   {
/* 12 */     super(msg);
/*    */   }
/*    */ 
/*    */   public AccountExpiredException(String msg, Object extraInformation) {
/* 16 */     super(msg, extraInformation);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.AccountExpiredException
 * JD-Core Version:    0.6.0
 */