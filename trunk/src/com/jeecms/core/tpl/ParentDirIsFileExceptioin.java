/*    */ package com.jeecms.core.tpl;
/*    */ 
/*    */ public class ParentDirIsFileExceptioin extends RuntimeException
/*    */ {
/*    */   private String parentDir;
/*    */ 
/*    */   public ParentDirIsFileExceptioin(String parentDir)
/*    */   {
/* 17 */     this.parentDir = parentDir;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 22 */     return "parent directory is a file: " + this.parentDir;
/*    */   }
/*    */ 
/*    */   public String getParentDir()
/*    */   {
/* 31 */     return this.parentDir;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.tpl.ParentDirIsFileExceptioin
 * JD-Core Version:    0.6.0
 */