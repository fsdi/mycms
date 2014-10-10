/*    */ package com.jeecms.cms.entity.main;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.base.BaseCmsLog;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class CmsLog extends BaseCmsLog
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   public static final int LOGIN_SUCCESS = 1;
/*    */   public static final int LOGIN_FAILURE = 2;
/*    */   public static final int OPERATING = 3;
/*    */ 
/*    */   public CmsLog()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CmsLog(Integer id)
/*    */   {
/* 20 */     super(id);
/*    */   }
/*    */ 
/*    */   public CmsLog(Integer id, Integer category, Date time)
/*    */   {
/* 34 */     super(id, 
/* 33 */       category, 
/* 34 */       time);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.entity.main.CmsLog
 * JD-Core Version:    0.6.0
 */