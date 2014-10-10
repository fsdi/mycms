/*     */ package com.jeecms.common.security.rememberme;
/*     */ 
/*     */ import com.jeecms.common.security.AccountStatusException;
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.security.userdetails.AccountStatusUserDetailsChecker;
/*     */ import com.jeecms.common.security.userdetails.UserDetails;
/*     */ import com.jeecms.common.security.userdetails.UserDetailsChecker;
/*     */ import com.jeecms.common.security.userdetails.UserDetailsService;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.codec.binary.Base64;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.util.Assert;
/*     */ import org.springframework.util.StringUtils;
/*     */ 
/*     */ public abstract class AbstractRememberMeServices
/*     */   implements RememberMeService, InitializingBean
/*     */ {
/*     */   public static final String REMEMBER_ME_COOKIE_KEY = "remember_me_cookie";
/*     */   private static final String DELIMITER = ":";
/*     */   public static final String DEFAULT_PARAMETER = "remember_me";
/*     */   public static final int TWO_WEEKS_S = 1209600;
/*  27 */   private String cookieName = "remember_me_cookie";
/*  28 */   private String parameter = "remember_me";
/*  29 */   private int tokenValiditySeconds = 1209600;
/*     */   private boolean alwaysRemember;
/*     */   private boolean alwaysRememberCookie;
/*     */   private String key;
/*  33 */   private UserDetailsChecker userDetailsChecker = new AccountStatusUserDetailsChecker();
/*     */   private UserDetailsService userDetailsService;
/*  36 */   protected final Logger logger = LoggerFactory.getLogger(getClass());
/*     */ 
/*     */   public final UserDetails autoLogin(HttpServletRequest request, HttpServletResponse response) throws CookieTheftException
/*     */   {
/*  40 */     String rememberMeCookie = extractRememberMeCookie(request);
/*     */ 
/*  42 */     if (rememberMeCookie == null) {
/*  43 */       return null;
/*     */     }
/*     */ 
/*  46 */     this.logger.debug("Remember-me cookie detected");
/*     */ 
/*  48 */     UserDetails user = null;
/*     */     try
/*     */     {
/*  52 */       String[] cookieTokens = decodeCookie(rememberMeCookie);
/*  53 */       user = processAutoLoginCookie(cookieTokens, request, response);
/*  54 */       this.userDetailsChecker.check(user);
/*     */     } catch (CookieTheftException cte) {
/*  56 */       cancelCookie(request, response);
/*  57 */       throw cte;
/*     */     } catch (UsernameNotFoundException noUser) {
/*  59 */       cancelCookie(request, response);
/*  60 */       this.logger.debug("Remember-me login was valid but corresponding user not found.", 
/*  61 */         noUser);
/*  62 */       return null;
/*     */     } catch (InvalidCookieException invalidCookie) {
/*  64 */       cancelCookie(request, response);
/*  65 */       this.logger.debug("Invalid remember-me cookie: " + 
/*  66 */         invalidCookie.getMessage());
/*  67 */       return null;
/*     */     } catch (AccountStatusException statusInvalid) {
/*  69 */       cancelCookie(request, response);
/*  70 */       this.logger.debug("Invalid UserDetails: " + statusInvalid.getMessage());
/*  71 */       return null;
/*     */     } catch (RememberMeAuthenticationException e) {
/*  73 */       cancelCookie(request, response);
/*  74 */       this.logger.debug(e.getMessage());
/*  75 */       return null;
/*     */     }
/*     */     String[] cookieTokens;
/*  77 */     this.logger.debug("Remember-me cookie accepted");
/*  78 */     return user;
/*     */   }
/*     */ 
/*     */   public final boolean loginSuccess(HttpServletRequest request, HttpServletResponse response, UserDetails user)
/*     */   {
/*  89 */     if (!rememberMeRequested(request, this.parameter)) {
/*  90 */       this.logger.debug("Remember-me login not requested.");
/*  91 */       return false;
/*     */     }
/*     */ 
/*  94 */     return onLoginSuccess(request, response, user);
/*     */   }
/*     */ 
/*     */   public final void loginFail(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  99 */     this.logger.debug("Interactive login attempt was unsuccessful.");
/* 100 */     cancelCookie(request, response);
/* 101 */     onLoginFail(request, response);
/*     */   }
/*     */ 
/*     */   public void logout(HttpServletRequest request, HttpServletResponse response) {
/* 105 */     this.logger.debug("Remember-me logout.");
/* 106 */     cancelCookie(request, response);
/* 107 */     onLogout(request, response);
/*     */   }
/*     */ 
/*     */   protected String extractRememberMeCookie(HttpServletRequest request)
/*     */   {
/* 119 */     Cookie[] cookies = request.getCookies();
/*     */ 
/* 121 */     if ((cookies == null) || (cookies.length == 0)) {
/* 122 */       return null;
/*     */     }
/*     */ 
/* 125 */     for (int i = 0; i < cookies.length; i++) {
/* 126 */       if (this.cookieName.equals(cookies[i].getName())) {
/* 127 */         return cookies[i].getValue();
/*     */       }
/*     */     }
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */   protected boolean rememberMeRequested(HttpServletRequest request, String parameter)
/*     */   {
/* 151 */     if (this.alwaysRemember) {
/* 152 */       return true;
/*     */     }
/*     */ 
/* 155 */     String paramValue = request.getParameter(parameter);
/*     */ 
/* 157 */     if ((paramValue != null) && (
/* 158 */       (paramValue.equalsIgnoreCase("true")) || 
/* 159 */       (paramValue.equalsIgnoreCase("on")) || 
/* 160 */       (paramValue.equalsIgnoreCase("yes")) || 
/* 161 */       (paramValue.equals("1")))) {
/* 162 */       return true;
/*     */     }
/*     */ 
/* 166 */     if (this.logger.isDebugEnabled()) {
/* 167 */       this.logger.debug("Did not send remember-me cookie (principal did not set parameter '" + 
/* 168 */         parameter + "')");
/*     */     }
/*     */ 
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */   protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 188 */     String cookieValue = encodeCookie(tokens);
/* 189 */     Cookie cookie = new Cookie(this.cookieName, cookieValue);
/* 190 */     String ctx = request.getContextPath();
/* 191 */     cookie.setPath(StringUtils.hasText(ctx) ? ctx : "/");
/* 192 */     cookie.setMaxAge(maxAge);
/* 193 */     response.addCookie(cookie);
/*     */   }
/*     */ 
/*     */   protected void cancelCookie(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 205 */     this.logger.debug("Cancelling cookie");
/* 206 */     Cookie cookie = new Cookie(this.cookieName, null);
/* 207 */     String ctx = request.getContextPath();
/* 208 */     cookie.setPath(StringUtils.hasText(ctx) ? ctx : "/");
/* 209 */     cookie.setMaxAge(0);
/* 210 */     response.addCookie(cookie);
/*     */   }
/*     */ 
/*     */   protected String[] decodeCookie(String cookieValue)
/*     */     throws InvalidCookieException
/*     */   {
/* 225 */     StringBuilder sb = new StringBuilder(cookieValue.length() + 3)
/* 226 */       .append(cookieValue);
/* 227 */     for (int j = 0; j < sb.length() % 4; j++) {
/* 228 */       sb.append("=");
/*     */     }
/* 230 */     cookieValue = sb.toString();
/* 231 */     if (!Base64.isArrayByteBase64(cookieValue.getBytes())) {
/* 232 */       throw new InvalidCookieException(
/* 233 */         "Cookie token was not Base64 encoded; value was '" + 
/* 234 */         cookieValue + "'");
/*     */     }
/*     */ 
/* 237 */     String cookieAsPlainText = new String(Base64.decodeBase64(
/* 238 */       cookieValue.getBytes()));
/*     */ 
/* 240 */     return StringUtils.delimitedListToStringArray(cookieAsPlainText, 
/* 241 */       ":");
/*     */   }
/*     */ 
/*     */   protected String encodeCookie(String[] cookieTokens)
/*     */   {
/* 253 */     StringBuilder sb = new StringBuilder();
/* 254 */     for (int i = 0; i < cookieTokens.length; i++) {
/* 255 */       sb.append(cookieTokens[i]);
/*     */ 
/* 257 */       if (i < cookieTokens.length - 1) {
/* 258 */         sb.append(":");
/*     */       }
/*     */     }
/*     */ 
/* 262 */     String value = sb.toString();
/*     */ 
/* 264 */     sb = new StringBuilder(
/* 265 */       new String(Base64.encodeBase64(value.getBytes())));
/*     */ 
/* 267 */     while (sb.charAt(sb.length() - 1) == '=') {
/* 268 */       sb.deleteCharAt(sb.length() - 1);
/*     */     }
/*     */ 
/* 271 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   protected abstract UserDetails processAutoLoginCookie(String[] paramArrayOfString, HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
/*     */     throws RememberMeAuthenticationException, UsernameNotFoundException;
/*     */ 
/*     */   protected abstract boolean onLoginSuccess(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, UserDetails paramUserDetails);
/*     */ 
/*     */   protected void onLoginFail(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void onLogout(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet()
/*     */     throws Exception
/*     */   {
/* 317 */     Assert.hasLength(this.key);
/* 318 */     Assert.hasLength(this.parameter);
/* 319 */     Assert.hasLength(this.cookieName);
/* 320 */     Assert.notNull(this.userDetailsService);
/*     */   }
/*     */ 
/*     */   protected String getCookieName() {
/* 324 */     return this.cookieName;
/*     */   }
/*     */ 
/*     */   public void setCookieName(String cookieName) {
/* 328 */     this.cookieName = cookieName;
/*     */   }
/*     */ 
/*     */   public boolean isAlwaysRemember() {
/* 332 */     return this.alwaysRemember;
/*     */   }
/*     */ 
/*     */   public void setAlwaysRemember(boolean alwaysRemember) {
/* 336 */     this.alwaysRemember = alwaysRemember;
/*     */   }
/*     */ 
/*     */   public String getParameter() {
/* 340 */     return this.parameter;
/*     */   }
/*     */ 
/*     */   public void setParameter(String parameter) {
/* 344 */     this.parameter = parameter;
/*     */   }
/*     */ 
/*     */   public UserDetailsService getUserDetailsService() {
/* 348 */     return this.userDetailsService;
/*     */   }
/*     */ 
/*     */   public void setUserDetailsService(UserDetailsService userDetailsService) {
/* 352 */     this.userDetailsService = userDetailsService;
/*     */   }
/*     */ 
/*     */   public String getKey() {
/* 356 */     return this.key;
/*     */   }
/*     */ 
/*     */   public void setKey(String key) {
/* 360 */     this.key = key;
/*     */   }
/*     */ 
/*     */   protected int getTokenValiditySeconds() {
/* 364 */     return this.tokenValiditySeconds;
/*     */   }
/*     */ 
/*     */   public void setTokenValiditySeconds(int tokenValiditySeconds) {
/* 368 */     this.tokenValiditySeconds = tokenValiditySeconds;
/*     */   }
/*     */ 
/*     */   public boolean isAlwaysRememberCookie() {
/* 372 */     return this.alwaysRememberCookie;
/*     */   }
/*     */ 
/*     */   public void setAlwaysRememberCookie(boolean alwaysRememberCookie) {
/* 376 */     this.alwaysRememberCookie = alwaysRememberCookie;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.security.rememberme.AbstractRememberMeServices
 * JD-Core Version:    0.6.0
 */