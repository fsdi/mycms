/*     */ package com.jeecms.cms.web;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*     */ 
/*     */ public class FrontContextInterceptor extends HandlerInterceptorAdapter
/*     */ {
/*     */   private SessionProvider session;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private AuthenticationMng authMng;
/*     */ 
/*     */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*     */     throws ServletException
/*     */   {
/*  31 */     CmsSite site = null;
/*  32 */     List<CmsSite> list = this.cmsSiteMng.getListFromCache();
/*  33 */     int size = list.size();
/*  34 */     if (size == 0)
/*  35 */       throw new RuntimeException("no site record in database!");
/*  36 */     if (size == 1) {
/*  37 */       site = (CmsSite)list.get(0);
/*     */     } else {
/*  39 */       String server = request.getServerName();
/*     */ 
/*  41 */       for (CmsSite s : list)
/*     */       {
/*  43 */         if (s.getDomain().equals(server)) {
/*  44 */           site = s;
/*  45 */           break;
/*     */         }
/*     */ 
/*  48 */         String alias = s.getDomainAlias();
/*  49 */         if (!StringUtils.isBlank(alias)) {
/*  50 */           for (String a : StringUtils.split(alias, ',')) {
/*  51 */             if (a.equals(server)) {
/*  52 */               site = s;
/*  53 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/*  58 */         String redirect = s.getDomainRedirect();
/*  59 */         if (!StringUtils.isBlank(redirect)) {
/*  60 */           for (String r : StringUtils.split(redirect, ',')) {
/*  61 */             if (!r.equals(server)) continue;
/*     */             try {
/*  63 */               response.sendRedirect(s.getUrl());
/*     */             } catch (IOException e) {
/*  65 */               throw new RuntimeException(e);
/*     */             }
/*  67 */             return false;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*  72 */       if (site == null) {
/*  73 */         throw new SiteNotFoundException(server);
/*     */       }
/*     */     }
/*     */ 
/*  77 */     CmsUtils.setSite(request, site);
/*     */ 
/*  79 */     CmsUser user = null;
/*  80 */     Integer userId = this.authMng.retrieveUserIdFromSession(this.session, request);
/*  81 */     if (userId != null) {
/*  82 */       user = this.cmsUserMng.findById(userId);
/*     */     }
/*     */ 
/*  85 */     if (user != null) {
/*  86 */       CmsUtils.setUser(request, user);
/*     */     }
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSession(SessionProvider session)
/*     */   {
/*  98 */     this.session = session;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 103 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 108 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setAuthMng(AuthenticationMng authMng) {
/* 113 */     this.authMng = authMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.FrontContextInterceptor
 * JD-Core Version:    0.6.0
 */