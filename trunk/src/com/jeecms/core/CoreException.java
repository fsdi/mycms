/*    */ package com.jeecms.core;
/*    */ 
/*    */ public class CoreException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CoreException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CoreException(String message)
/*    */   {
/* 11 */     super(message);
/*    */   }
/*    */ 
/*    */   public CoreException(String message, Throwable cause) {
/* 15 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   public CoreException(Throwable cause) {
/* 19 */     super(cause);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.CoreException
 * JD-Core Version:    0.6.0
 */