/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsFile;
/*    */ 
/*    */ public class CmsFile extends BaseCmsFile
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsFile()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsFile(String filePath)
/*    */   {
/* 19 */     super(filePath);
/*    */   }
/*    */ 
/*    */   public CmsFile(String filePath, boolean fileIsvalid)
/*    */   {
/* 31 */     super(filePath, 
/* 31 */       fileIsvalid);
/*    */   }
/*    */ 
/*    */   public Boolean getFileIsvalid() {
/* 35 */     return Boolean.valueOf(super.isFileIsvalid());
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsFile
 * JD-Core Version:    0.6.0
 */