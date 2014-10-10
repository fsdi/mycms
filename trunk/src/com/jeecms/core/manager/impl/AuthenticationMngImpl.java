/*     */ package com.jeecms.core.manager.impl;
/*     */ 
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.security.BadCredentialsException;
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.dao.AuthenticationDao;
/*     */ import com.jeecms.core.entity.Authentication;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class AuthenticationMngImpl
/*     */   implements AuthenticationMng
/*     */ {
/*  30 */   private Logger log = LoggerFactory.getLogger(AuthenticationMngImpl.class);
/*     */ 
/* 130 */   private int timeout = 1800000;
/*     */ 
/* 133 */   private int interval = 14400000;
/*     */ 
/* 137 */   private long refreshTime = getNextRefreshTime(System.currentTimeMillis(), 
/* 137 */     this.interval);
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */   private AuthenticationDao dao;
/*     */ 
/*     */   public Authentication login(String username, String password, String ip, HttpServletRequest request, HttpServletResponse response, SessionProvider session)
/*     */     throws UsernameNotFoundException, BadCredentialsException
/*     */   {
/*  36 */     UnifiedUser user = this.unifiedUserMng.login(username, password, ip);
/*  37 */     Authentication auth = new Authentication();
/*  38 */     auth.setUid(user.getId());
/*  39 */     auth.setUsername(user.getUsername());
/*  40 */     auth.setEmail(user.getEmail());
/*  41 */     auth.setLoginIp(ip);
/*  42 */     save(auth);
/*  43 */     session.setAttribute(request, response, "auth_key", auth.getId());
/*  44 */     return auth;
/*     */   }
/*     */ 
/*     */   public Authentication activeLogin(UnifiedUser user, String ip, HttpServletRequest request, HttpServletResponse response, SessionProvider session)
/*     */   {
/*  50 */     this.unifiedUserMng.activeLogin(user, ip);
/*  51 */     Authentication auth = new Authentication();
/*  52 */     auth.setUid(user.getId());
/*  53 */     auth.setUsername(user.getUsername());
/*  54 */     auth.setEmail(user.getEmail());
/*  55 */     auth.setLoginIp(ip);
/*  56 */     save(auth);
/*  57 */     session.setAttribute(request, response, "auth_key", auth.getId());
/*  58 */     return auth;
/*     */   }
/*     */ 
/*     */   public Authentication retrieve(String authId) {
/*  62 */     long current = System.currentTimeMillis();
/*     */ 
/*  64 */     if (this.refreshTime < current) {
/*  65 */       this.refreshTime = getNextRefreshTime(current, this.interval);
/*  66 */       int count = this.dao.deleteExpire(new Date(current - this.timeout));
/*  67 */       this.log.info("refresh Authentication, delete count: {}", Integer.valueOf(count));
/*     */     }
/*  69 */     Authentication auth = findById(authId);
/*  70 */     if ((auth != null) && (auth.getUpdateTime().getTime() + this.timeout > current)) {
/*  71 */       auth.setUpdateTime(new Timestamp(current));
/*  72 */       return auth;
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   public Integer retrieveUserIdFromSession(SessionProvider session, HttpServletRequest request)
/*     */   {
/*  80 */     String authId = (String)session.getAttribute(request, "auth_key");
/*  81 */     if (authId == null) {
/*  82 */       return null;
/*     */     }
/*  84 */     Authentication auth = retrieve(authId);
/*  85 */     if (auth == null) {
/*  86 */       return null;
/*     */     }
/*  88 */     return auth.getUid();
/*     */   }
/*     */ 
/*     */   public void storeAuthIdToSession(SessionProvider session, HttpServletRequest request, HttpServletResponse response, String authId)
/*     */   {
/*  94 */     session.setAttribute(request, response, "auth_key", authId);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(int pageNo, int pageSize) {
/*  99 */     Pagination page = this.dao.getPage(pageNo, pageSize);
/* 100 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public Authentication findById(String id) {
/* 105 */     Authentication entity = this.dao.findById(id);
/* 106 */     return entity;
/*     */   }
/*     */ 
/*     */   public Authentication save(Authentication bean) {
/* 110 */     bean.setId(StringUtils.remove(UUID.randomUUID().toString(), '-'));
/* 111 */     bean.init();
/* 112 */     this.dao.save(bean);
/* 113 */     return bean;
/*     */   }
/*     */ 
/*     */   public Authentication deleteById(String id) {
/* 117 */     Authentication bean = this.dao.deleteById(id);
/* 118 */     return bean;
/*     */   }
/*     */ 
/*     */   public Authentication[] deleteByIds(String[] ids) {
/* 122 */     Authentication[] beans = new Authentication[ids.length];
/* 123 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 124 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 126 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(AuthenticationDao dao)
/*     */   {
/* 144 */     this.dao = dao;
/*     */   }
/*     */   @Autowired
/*     */   public void setUserMng(UnifiedUserMng unifiedUserMng) {
/* 149 */     this.unifiedUserMng = unifiedUserMng;
/*     */   }
/*     */ 
/*     */   public void setTimeout(int timeout)
/*     */   {
/* 159 */     this.timeout = (timeout * 60 * 1000);
/*     */   }
/*     */ 
/*     */   public void setInterval(int interval)
/*     */   {
/* 169 */     this.interval = (interval * 60 * 1000);
/* 170 */     this.refreshTime = 
/* 171 */       getNextRefreshTime(System.currentTimeMillis(), 
/* 171 */       this.interval);
/*     */   }
/*     */ 
/*     */   private long getNextRefreshTime(long current, int interval)
/*     */   {
/* 184 */     return current + interval;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.manager.impl.AuthenticationMngImpl
 * JD-Core Version:    0.6.0
 */