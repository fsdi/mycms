/*    */ package com.jeecms.cms.web;
/*    */ 
/*    */ public class SiteNotFoundException extends RuntimeException
/*    */ {
/*    */   private String domain;
/*    */ 
/*    */   public SiteNotFoundException(String domain)
/*    */   {
/*  8 */     super(domain);
/*  9 */     this.domain = domain;
/*    */   }
/*    */ 
/*    */   public String getDomain() {
/* 13 */     return this.domain;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.SiteNotFoundException
 * JD-Core Version:    0.6.0
 */