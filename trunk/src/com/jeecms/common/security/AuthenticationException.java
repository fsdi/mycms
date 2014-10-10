/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class AuthenticationException extends Exception
/*    */ {
/*    */   private Object extraInformation;
/*    */ 
/*    */   public AuthenticationException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public AuthenticationException(String msg)
/*    */   {
/* 15 */     super(msg);
/*    */   }
/*    */ 
/*    */   public AuthenticationException(String msg, Object extraInformation) {
/* 19 */     super(msg);
/* 20 */     this.extraInformation = extraInformation;
/*    */   }
/*    */ 
/*    */   public Object getExtraInformation()
/*    */   {
/* 30 */     return this.extraInformation;
/*    */   }
/*    */ 
/*    */   public void clearExtraInformation() {
/* 34 */     this.extraInformation = null;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.AuthenticationException
 * JD-Core Version:    0.6.0
 */