/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsSensitivity;
/*    */ 
/*    */ public class CmsSensitivity extends BaseCmsSensitivity
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsSensitivity()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsSensitivity(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsSensitivity(Integer id, String search, String replacement)
/*    */   {
/* 33 */     super(id, 
/* 32 */       search, 
/* 33 */       replacement);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsSensitivity
 * JD-Core Version:    0.6.0
 */