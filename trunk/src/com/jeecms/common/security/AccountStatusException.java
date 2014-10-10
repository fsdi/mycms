/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class AccountStatusException extends AuthenticationException
/*    */ {
/*    */   public AccountStatusException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public AccountStatusException(String msg)
/*    */   {
/* 12 */     super(msg);
/*    */   }
/*    */ 
/*    */   public AccountStatusException(String msg, Object extraInformation) {
/* 16 */     super(msg, extraInformation);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.AccountStatusException
 * JD-Core Version:    0.6.0
 */