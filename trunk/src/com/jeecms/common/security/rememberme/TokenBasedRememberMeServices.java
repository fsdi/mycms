/*     */ package com.jeecms.common.security.rememberme;
/*     */ 
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.security.userdetails.UserDetails;
/*     */ import com.jeecms.common.security.userdetails.UserDetailsService;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.codec.digest.DigestUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.springframework.dao.DataAccessException;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public class TokenBasedRememberMeServices extends AbstractRememberMeServices
/*     */ {
/*     */   protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response)
/*     */     throws DataAccessException, UsernameNotFoundException, InvalidCookieException
/*     */   {
/*  81 */     if (cookieTokens.length != 4) {
/*  82 */       throw new InvalidCookieException("Cookie token did not contain 4 tokens, but contained '" + 
/*  84 */         Arrays.asList(cookieTokens) + "'");
/*     */     }
/*     */ 	  long tokenExpiryTime;
/*     */     try
/*     */     {
/*  90 */       tokenExpiryTime = new Long(cookieTokens[1]).longValue();
/*     */     }
/*     */     catch (NumberFormatException nfe)
/*     */     {
/*  92 */       throw new InvalidCookieException(
/*  93 */         "Cookie token[1] did not contain a valid number (contained '" + 
/*  94 */         cookieTokens[1] + "')");
/*     */     }
/*  97 */     if (isTokenExpired(tokenExpiryTime))
/*  98 */       throw new InvalidCookieException(
/*  99 */         "Cookie token[1] has expired (expired on '" + 
/* 100 */         new Date(tokenExpiryTime) + 
/* 101 */         "'; current time is '" + new Date() + "')");
			  Long userId;
/*     */     try
/*     */     {
/* 104 */       userId = new Long(cookieTokens[3]);
/*     */     }
/*     */     catch (NumberFormatException nfe)
/*     */     {
/* 106 */       throw new InvalidCookieException(
/* 107 */         "Cookie token[3] did not contain a valid number (contained '" + 
/* 108 */         cookieTokens[3] + "')");
/*     */     }
/* 114 */     UserDetails user = getUserDetailsService().loadUser(userId, 
/* 115 */       cookieTokens[0]);
/*     */ 
/* 125 */     String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, 
/* 126 */       user.getUsername(), user.getPassword(), user.getId());
/*     */ 
/* 128 */     if (!expectedTokenSignature.equals(cookieTokens[2])) {
/* 129 */       throw new InvalidCookieException(
/* 130 */         "Cookie token[2] contained signature '" + cookieTokens[2] + 
/* 131 */         "' but expected '" + expectedTokenSignature + "'");
/*     */     }
/* 133 */     return user;
/*     */   }
/*     */ 
/*     */   protected String makeTokenSignature(long tokenExpiryTime, String username, String password, Long id)
/*     */   {
/* 142 */     return DigestUtils.md5Hex(username + ":" + tokenExpiryTime + ":" + 
/* 143 */       password + ":" + getKey() + ":" + id);
/*     */   }
/*     */ 
/*     */   protected boolean isTokenExpired(long tokenExpiryTime) {
/* 147 */     return tokenExpiryTime < System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public boolean onLoginSuccess(HttpServletRequest request, HttpServletResponse response, UserDetails user)
/*     */   {
/* 153 */     String username = user.getUsername();
/* 154 */     String password = user.getPassword();
/*     */ 
/* 159 */     if ((!StringUtils.hasLength(username)) || 
/* 160 */       (!StringUtils.hasLength(password))) {
/* 161 */       return false;
/*     */     }
/*     */ 
/* 164 */     int tokenLifetime = calculateLoginLifetime(request, user);
/* 165 */     long expiryTime = System.currentTimeMillis();
/*     */ 
/* 167 */     expiryTime += 1000L * (tokenLifetime < 0 ? 1209600 : tokenLifetime);
/*     */ 
/* 169 */     String signatureValue = makeTokenSignature(expiryTime, username, 
/* 170 */       password, user.getId());
/*     */ 
/* 172 */     setCookie(new String[] { username, Long.toString(expiryTime), 
/* 173 */       signatureValue, user.getId().toString() }, tokenLifetime, 
/* 174 */       request, response);
/*     */ 
/* 176 */     if (this.logger.isDebugEnabled()) {
/* 177 */       this.logger.debug("Added remember-me cookie for user '" + username + 
/* 178 */         "', expiry: '" + new Date(expiryTime) + "'");
/*     */     }
/* 180 */     return true;
/*     */   }
/*     */ 
/*     */   protected int calculateLoginLifetime(HttpServletRequest request, UserDetails user)
/*     */   {
/* 204 */     return getTokenValiditySeconds();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.rememberme.TokenBasedRememberMeServices
 * JD-Core Version:    0.6.0
 */