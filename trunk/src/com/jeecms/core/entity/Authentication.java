/*    */ package com.jeecms.core.entity;
/*    */ 
/*    */ import com.jeecms.core.entity.base.BaseAuthentication;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Authentication extends BaseAuthentication
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */ 
/*    */   public void init()
/*    */   {
/* 12 */     Date now = new Timestamp(System.currentTimeMillis());
/* 13 */     setLoginTime(now);
/* 14 */     setUpdateTime(now);
/*    */   }
/*    */ 
/*    */   public Authentication()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Authentication(String id)
/*    */   {
/* 26 */     super(id);
/*    */   }
/*    */ 
/*    */   public Authentication(String id, Integer uid, String username, Date loginTime, String loginIp, Date updateTime)
/*    */   {
/* 46 */     super(id, 
/* 42 */       uid, 
/* 43 */       username, 
/* 44 */       loginTime, 
/* 45 */       loginIp, 
/* 46 */       updateTime);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.entity.Authentication
 * JD-Core Version:    0.6.0
 */