/*    */ package com.jeecms.common.security;
/*    */ 
/*    */ public class LockedException extends AccountStatusException
/*    */ {
/*    */   public LockedException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public LockedException(String msg)
/*    */   {
/* 12 */     super(msg);
/*    */   }
/*    */ 
/*    */   public LockedException(String msg, Object extraInformation) {
/* 16 */     super(msg, extraInformation);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.LockedException
 * JD-Core Version:    0.6.0
 */