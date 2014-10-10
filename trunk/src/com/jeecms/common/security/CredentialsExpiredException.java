/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class CredentialsExpiredException extends AccountStatusException
/*    */ {
/*    */   public CredentialsExpiredException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CredentialsExpiredException(String msg)
/*    */   {
/* 12 */     super(msg);
/*    */   }
/*    */ 
/*    */   public CredentialsExpiredException(String msg, Object extraInformation) {
/* 16 */     super(msg, extraInformation);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.CredentialsExpiredException
 * JD-Core Version:    0.6.0
 */