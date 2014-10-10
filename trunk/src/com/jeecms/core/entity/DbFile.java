/*    */ package com.jeecms.core.entity;
/*    */ 
/*    */ import com.jeecms.core.entity.base.BaseDbFile;
/*    */ 
/*    */ public class DbFile extends BaseDbFile
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public DbFile()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DbFile(String id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public DbFile(String id, Integer length, Long lastModified, byte[] content)
/*    */   {
/* 35 */     super(id, 
/* 33 */       length, 
/* 34 */       lastModified, 
/* 35 */       content);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.DbFile
 * JD-Core Version:    0.6.0
 */