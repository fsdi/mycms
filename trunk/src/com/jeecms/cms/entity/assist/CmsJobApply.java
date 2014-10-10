/*    */ package com.jeecms.cms.entity.assist;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.base.BaseCmsJobApply;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CmsJobApply extends BaseCmsJobApply
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public CmsJobApply()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsJobApply(Integer id)
/*    */   {
/* 19 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsJobApply(Integer id, Content content, CmsUser user, Date applyTime)
/*    */   {
/* 35 */     super(id, 
/* 33 */       content, 
/* 34 */       user, 
/* 35 */       applyTime);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.assist.CmsJobApply
 * JD-Core Version:    0.6.0
 */