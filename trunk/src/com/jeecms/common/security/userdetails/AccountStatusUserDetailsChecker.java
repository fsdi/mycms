/*    */ package com.jeecms.common.security.userdetails;
/*    */ 
/*    */ import com.jeecms.common.security.AccountExpiredException;
/*    */ import com.jeecms.common.security.AccountStatusException;
/*    */ import com.jeecms.common.security.CredentialsExpiredException;
/*    */ import com.jeecms.common.security.DisabledException;
/*    */ import com.jeecms.common.security.LockedException;
/*    */ 
/*    */ public class AccountStatusUserDetailsChecker
/*    */   implements UserDetailsChecker
/*    */ {
/*    */   public void check(UserDetails user)
/*    */     throws AccountStatusException
/*    */   {
/* 16 */     if (!user.isAccountNonLocked()) {
/* 17 */       throw new LockedException();
/*    */     }
/*    */ 
/* 20 */     if (!user.isEnabled()) {
/* 21 */       throw new DisabledException("User is disabled", user);
/*    */     }
/*    */ 
/* 24 */     if (!user.isAccountNonExpired()) {
/* 25 */       throw new AccountExpiredException("User account has expired", user);
/*    */     }
/*    */ 
/* 28 */     if (!user.isCredentialsNonExpired())
/* 29 */       throw new CredentialsExpiredException(
/* 30 */         "User credentials have expired", user);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.userdetails.AccountStatusUserDetailsChecker
 * JD-Core Version:    0.6.0
 */