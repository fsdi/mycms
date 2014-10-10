/*    */ package com.jeecms.core.entity;
/*    */ 
/*    */ import com.jeecms.core.entity.base.BaseUnifiedUser;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class UnifiedUser extends BaseUnifiedUser
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public UnifiedUser()
/*    */   {
/*    */   }
/*    */ 
/*    */   public UnifiedUser(Integer id)
/*    */   {
/* 18 */     super(id);
/*    */   }
/*    */ 
/*    */   public UnifiedUser(Integer id, String username, String password, Date registerTime, String registerIp, Integer loginCount, Integer errorCount, Boolean activation)
/*    */   {
/* 42 */     super(id, 
/* 36 */       username, 
/* 37 */       password, 
/* 38 */       registerTime, 
/* 39 */       registerIp, 
/* 40 */       loginCount, 
/* 41 */       errorCount, 
/* 42 */       activation);
/*    */   }
/*    */ 
/*    */   public void init()
/*    */   {
/* 48 */     if (getLoginCount() == null) {
/* 49 */       setLoginCount(Integer.valueOf(0));
/*    */     }
/* 51 */     if (getErrorCount() == null)
/* 52 */       setErrorCount(Integer.valueOf(0));
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.UnifiedUser
 * JD-Core Version:    0.6.0
 */