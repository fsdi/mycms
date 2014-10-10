/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class DisabledException extends AccountStatusException
/*    */ {
/*    */   public DisabledException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DisabledException(String msg)
/*    */   {
/* 12 */     super(msg);
/*    */   }
/*    */ 
/*    */   public DisabledException(String msg, Object extraInformation) {
/* 16 */     super(msg, extraInformation);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.DisabledException
 * JD-Core Version:    0.6.0
 */