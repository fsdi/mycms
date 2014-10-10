/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseMarkConfig;
/*    */ 
/*    */ public class MarkConfig extends BaseMarkConfig
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public MarkConfig()
/*    */   {
/*    */   }
/*    */ 
/*    */   public MarkConfig(Boolean on, Integer minWidth, Integer minHeight, String content, Integer size, String color, Integer alpha, Integer pos, Integer offsetX, Integer offsetY)
/*    */   {
/* 40 */     super(on, 
/* 32 */       minWidth, 
/* 33 */       minHeight, 
/* 34 */       content, 
/* 35 */       size, 
/* 36 */       color, 
/* 37 */       alpha, 
/* 38 */       pos, 
/* 39 */       offsetX, 
/* 40 */       offsetY);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.MarkConfig
 * JD-Core Version:    0.6.0
 */