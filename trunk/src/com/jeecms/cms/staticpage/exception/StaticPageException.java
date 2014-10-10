/*    */ package com.jeecms.cms.staticpage.exception;
/*    */ 
/*    */ public class StaticPageException extends Exception
/*    */ {
/*    */   private Integer generated;
/*    */   private String errorTitle;
/*    */ 
/*    */   public StaticPageException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public StaticPageException(String msg)
/*    */   {
/* 17 */     super(msg);
/*    */   }
/*    */ 
/*    */   public StaticPageException(String msg, Integer generated) {
/* 21 */     super(msg);
/* 22 */     this.generated = generated;
/*    */   }
/*    */ 
/*    */   public StaticPageException(String msg, Integer generated, String errorTitle) {
/* 26 */     super(msg);
/* 27 */     this.generated = generated;
/* 28 */     this.errorTitle = errorTitle;
/*    */   }
/*    */ 
/*    */   public Integer getGenerated() {
/* 32 */     return this.generated;
/*    */   }
/*    */ 
/*    */   public String getErrorTitle() {
/* 36 */     return this.errorTitle;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.staticpage.exception.StaticPageException
 * JD-Core Version:    0.6.0
 */