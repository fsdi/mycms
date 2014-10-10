/*     */ package com.jeecms.common.web.session;
/*     */ 
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.session.cache.SessionCache;
/*     */ import com.jeecms.common.web.session.id.SessionIdGenerator;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public class CacheSessionProvider
/*     */   implements SessionProvider, InitializingBean
/*     */ {
/*     */   public static final String CURRENT_SESSION = "_current_session";
/*     */   public static final String CURRENT_SESSION_ID = "_current_session_id";
/*     */   private SessionCache sessionCache;
/*     */   private SessionIdGenerator sessionIdGenerator;
/* 134 */   private int sessionTimeout = 30;
/*     */ 
/*     */   public Serializable getAttribute(HttpServletRequest request, String name)
/*     */   {
/*  30 */     Map session = (Map)request
/*  31 */       .getAttribute("_current_session");
/*  32 */     if (session != null) {
/*  33 */       return (Serializable)session.get(name);
/*     */     }
/*     */ 
/*  36 */     String root = (String)request.getAttribute("_current_session_id");
/*  37 */     if (root == null) {
/*  38 */       root = RequestUtils.getRequestedSessionId(request);
/*     */     }
/*  40 */     if (StringUtils.isBlank(root)) {
/*  41 */       request.setAttribute("_current_session", 
/*  42 */         new HashMap());
/*  43 */       return null;
/*     */     }
/*  45 */     session = this.sessionCache.getSession(root);
/*  46 */     if (session != null) {
/*  47 */       request.setAttribute("_current_session_id", root);
/*  48 */       request.setAttribute("_current_session", session);
/*  49 */       return (Serializable)session.get(name);
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   public void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value)
/*     */   {
/*  58 */     Map session = (Map)request.getAttribute("_current_session");
/*     */     String root;
/*  61 */     if (session == null) {
/*  62 */       root = RequestUtils.getRequestedSessionId(request);
/*  63 */       if ((root != null) && (root.length() == 32)) {
/*  64 */         session = this.sessionCache.getSession(root);
/*     */       }
/*  66 */       if (session == null) {
/*  67 */         session = new HashMap();
/*     */         do
/*  69 */           root = this.sessionIdGenerator.get();
/*  68 */         while (
/*  70 */           this.sessionCache.exist(root));
/*  71 */         response.addCookie(createCookie(request, root));
/*     */       }
/*  73 */       request.setAttribute("_current_session", session);
/*  74 */       request.setAttribute("_current_session_id", root);
/*     */     } else {
/*  76 */       root = (String)request.getAttribute("_current_session_id");
/*  77 */       if (root == null) {
/*     */         do
/*  79 */           root = this.sessionIdGenerator.get();
/*  78 */         while (
/*  80 */           this.sessionCache.exist(root));
/*  81 */         response.addCookie(createCookie(request, root));
/*  82 */         request.setAttribute("_current_session_id", root);
/*     */       }
/*     */     }
/*  85 */     session.put(name, value);
/*  86 */     this.sessionCache.setSession(root, session, this.sessionTimeout);
/*     */   }
/*     */ 
/*     */   public String getSessionId(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  91 */     String root = (String)request.getAttribute("_current_session_id");
/*  92 */     if (root != null) {
/*  93 */       return root;
/*     */     }
/*  95 */     root = RequestUtils.getRequestedSessionId(request);
/*  96 */     if ((root == null) || (root.length() != 32) || (!this.sessionCache.exist(root))) {
/*     */       do
/*  98 */         root = this.sessionIdGenerator.get();
/*  97 */       while (
/*  99 */         this.sessionCache.exist(root));
/* 100 */       this.sessionCache.setSession(root, new HashMap(), 
/* 101 */         this.sessionTimeout);
/* 102 */       response.addCookie(createCookie(request, root));
/*     */     }
/* 104 */     request.setAttribute("_current_session_id", root);
/* 105 */     return root;
/*     */   }
/*     */ 
/*     */   public void logout(HttpServletRequest request, HttpServletResponse response) {
/* 109 */     request.removeAttribute("_current_session");
/* 110 */     request.removeAttribute("_current_session_id");
/* 111 */     String root = RequestUtils.getRequestedSessionId(request);
/* 112 */     if (!StringUtils.isBlank(root)) {
/* 113 */       this.sessionCache.clear(root);
/* 114 */       Cookie cookie = createCookie(request, null);
/* 115 */       cookie.setMaxAge(0);
/* 116 */       response.addCookie(cookie);
/*     */     }
/*     */   }
/*     */ 
/*     */   private Cookie createCookie(HttpServletRequest request, String value) {
/* 121 */     Cookie cookie = new Cookie("JSESSIONID", value);
/* 122 */     String ctx = request.getContextPath();
/* 123 */     cookie.setPath(StringUtils.isBlank(ctx) ? "/" : ctx);
/* 124 */     return cookie;
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws Exception {
/* 128 */     Assert.notNull(this.sessionCache);
/* 129 */     Assert.notNull(this.sessionIdGenerator);
/*     */   }
/*     */ 
/*     */   public void setSessionCache(SessionCache sessionCache)
/*     */   {
/* 137 */     this.sessionCache = sessionCache;
/*     */   }
/*     */ 
/*     */   public void setSessionTimeout(int sessionTimeout)
/*     */   {
/* 147 */     Assert.isTrue(sessionTimeout > 0);
/* 148 */     this.sessionTimeout = sessionTimeout;
/*     */   }
/*     */ 
/*     */   public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
/* 152 */     this.sessionIdGenerator = sessionIdGenerator;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.session.CacheSessionProvider
 * JD-Core Version:    0.6.0
 */