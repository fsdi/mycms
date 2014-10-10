/*    */ package com.jeecms.common.web.cos;
/*    */ 
/*    */ public abstract class Part
/*    */ {
/*    */   private String name;
/*    */ 
/*    */   Part(String name)
/*    */   {
/* 21 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 30 */     return this.name;
/*    */   }
/*    */ 
/*    */   public boolean isFile()
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean isParam()
/*    */   {
/* 48 */     return false;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.cos.Part
 * JD-Core Version:    0.6.0
 */