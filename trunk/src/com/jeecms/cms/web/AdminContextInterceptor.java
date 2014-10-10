/*     */ package com.jeecms.cms.web;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.common.web.springmvc.MessageResolver;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*     */ import org.springframework.web.util.UrlPathHelper;
/*     */ 
/*     */ public class AdminContextInterceptor extends HandlerInterceptorAdapter
/*     */ {
/*  37 */   private static final Logger log = Logger.getLogger(AdminContextInterceptor.class);
/*     */   public static final String SITE_PARAM = "_site_id_param";
/*     */   public static final String SITE_COOKIE = "_site_id_cookie";
/*     */   public static final String PERMISSION_MODEL = "_permission_key";
/*     */   private SessionProvider session;
/*     */   private AuthenticationMng authMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private Integer adminId;
/* 308 */   private boolean auth = true;
/*     */   private String[] excludeUrls;
/*     */   private String loginUrl;
/*     */   private String processUrl;
/*     */   private String returnUrl;
/*     */ 
/*     */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*     */     throws Exception
/*     */   {
/*  46 */     CmsSite site = getSite(request, response);
/*  47 */     CmsUtils.setSite(request, site);
/*     */ 
/*  49 */     CmsThreadVariable.setSite(site);
/*     */ 
/*  52 */     CmsUser user = null;
/*  53 */     if (this.adminId != null)
/*     */     {
/*  55 */       user = this.cmsUserMng.findById(this.adminId);
/*  56 */       if (user == null)
/*  57 */         throw new IllegalStateException("User ID=" + this.adminId + 
/*  58 */           " not found!");
/*     */     }
/*     */     else
/*     */     {
/*  62 */       Integer userId = this.authMng
/*  63 */         .retrieveUserIdFromSession(this.session, request);
/*  64 */       if (userId != null) {
/*  65 */         user = this.cmsUserMng.findById(userId);
/*     */       }
/*     */     }
/*     */ 
/*  69 */     CmsUtils.setUser(request, user);
/*     */ 
/*  71 */     CmsThreadVariable.setUser(user);
/*     */ 
/*  73 */     String uri = getURI(request);
/*     */ 
/*  75 */     if (exclude(uri)) {
/*  76 */       return true;
/*     */     }
/*     */ 
/*  79 */     if (user == null) {
/*  80 */       response.sendRedirect(getLoginUrl(request));
/*  81 */       return false;
/*     */     }
/*     */ 
/*  84 */     if (!user.getAdmin().booleanValue()) {
/*  85 */       request.setAttribute("message", 
/*  86 */         MessageResolver.getMessage(request, 
/*  86 */         "login.notAdmin", new Object[0]));
/*  87 */       response.sendError(403);
/*  88 */       return false;
/*     */     }
/*     */ 
/*  91 */     if (!user.getSites().contains(site)) {
/*  92 */       request.setAttribute("message", 
/*  93 */         MessageResolver.getMessage(request, 
/*  93 */         "login.notInSite", new Object[0]));
/*  94 */       response.sendError(403);
/*  95 */       return false;
/*     */     }
/*  97 */     boolean viewonly = user.getViewonlyAdmin().booleanValue();
/*     */ 
/*  99 */     if ((this.auth) && (!user.isSuper()) && 
/* 100 */       (!permistionPass(uri, user.getPerms(), viewonly))) {
/* 101 */       request.setAttribute("message", 
/* 102 */         MessageResolver.getMessage(request, 
/* 102 */         "login.notPermission", new Object[0]));
/* 103 */       response.sendError(403);
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
/*     */     throws Exception
/*     */   {
/* 113 */     CmsUser user = CmsUtils.getUser(request);
/*     */ 
/* 115 */     if ((this.auth) && (user != null) && (!user.isSuper()) && (mav != null) && 
/* 116 */       (mav.getModelMap() != null) && (mav.getViewName() != null) && 
/* 117 */       (!mav.getViewName().startsWith("redirect:")))
/* 118 */       mav.getModelMap().addAttribute("_permission_key", user.getPerms());
/*     */   }
/*     */ 
/*     */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
/*     */     throws Exception
/*     */   {
/* 127 */     CmsThreadVariable.removeUser();
/* 128 */     CmsThreadVariable.removeSite();
/*     */   }
/*     */ 
/*     */   private String getLoginUrl(HttpServletRequest request) {
/* 132 */     StringBuilder buff = new StringBuilder();
/* 133 */     if (this.loginUrl.startsWith("/")) {
/* 134 */       String ctx = request.getContextPath();
/* 135 */       if (!StringUtils.isBlank(ctx)) {
/* 136 */         buff.append(ctx);
/*     */       }
/*     */     }
/* 139 */     buff.append(this.loginUrl).append("?");
/* 140 */     buff.append("returnUrl").append("=").append(this.returnUrl);
/* 141 */     if (!StringUtils.isBlank(this.processUrl)) {
/* 142 */       buff.append("&").append("processUrl").append("=").append(
/* 143 */         getProcessUrl(request));
/*     */     }
/* 145 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   private String getProcessUrl(HttpServletRequest request) {
/* 149 */     StringBuilder buff = new StringBuilder();
/* 150 */     if (this.loginUrl.startsWith("/")) {
/* 151 */       String ctx = request.getContextPath();
/* 152 */       if (!StringUtils.isBlank(ctx)) {
/* 153 */         buff.append(ctx);
/*     */       }
/*     */     }
/* 156 */     buff.append(this.processUrl);
/* 157 */     return buff.toString();
/*     */   }
/*     */ 
/*     */   private CmsSite getSite(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 168 */     CmsSite site = getByParams(request, response);
/* 169 */     if (site == null) {
/* 170 */       site = getByCookie(request);
/*     */     }
/* 172 */     if (site == null) {
/* 173 */       site = getByDomain(request);
/*     */     }
/* 175 */     if (site == null) {
/* 176 */       site = getByDefault();
/*     */     }
/* 178 */     if (site == null) {
/* 179 */       throw new RuntimeException("cannot get site!");
/*     */     }
/* 181 */     return site;
/*     */   }
/*     */ 
/*     */   private CmsSite getByParams(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 187 */     String p = request.getParameter("_site_id_param");
/* 188 */     if (!StringUtils.isBlank(p)) {
/*     */       try {
/* 190 */         Integer siteId = Integer.valueOf(Integer.parseInt(p));
/* 191 */         CmsSite site = this.cmsSiteMng.findById(siteId);
/* 192 */         if (site != null)
/*     */         {
/* 194 */           CookieUtils.addCookie(request, response, "_site_id_cookie", 
/* 195 */             site.getId().toString(), null, null);
/* 196 */           return site;
/*     */         }
/*     */       } catch (NumberFormatException e) {
/* 199 */         log.warn("param site id format exception", e);
/*     */       }
/*     */     }
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */   private CmsSite getByCookie(HttpServletRequest request) {
/* 206 */     Cookie cookie = CookieUtils.getCookie(request, "_site_id_cookie");
/* 207 */     if (cookie != null) {
/* 208 */       String v = cookie.getValue();
/* 209 */       if (!StringUtils.isBlank(v)) {
/*     */         try {
/* 211 */           Integer siteId = Integer.valueOf(Integer.parseInt(v));
/* 212 */           return this.cmsSiteMng.findById(siteId);
/*     */         } catch (NumberFormatException e) {
/* 214 */           log.warn("cookie site id format exception", e);
/*     */         }
/*     */       }
/*     */     }
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */   private CmsSite getByDomain(HttpServletRequest request) {
/* 222 */     String domain = request.getServerName();
/* 223 */     if (!StringUtils.isBlank(domain)) {
/* 224 */       return this.cmsSiteMng.findByDomain(domain, true);
/*     */     }
/* 226 */     return null;
/*     */   }
/*     */ 
/*     */   private CmsSite getByDefault() {
/* 230 */     List list = this.cmsSiteMng.getListFromCache();
/* 231 */     if (list.size() > 0) {
/* 232 */       return (CmsSite)list.get(0);
/*     */     }
/* 234 */     return null;
/*     */   }
/*     */ 
/*     */   private boolean exclude(String uri)
/*     */   {
/* 239 */     if (this.excludeUrls != null) {
/* 240 */       for (String exc : this.excludeUrls) {
/* 241 */         if (exc.equals(uri)) {
/* 242 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 246 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean permistionPass(String uri, Set<String> perms, boolean viewOnly)
/*     */   {
/* 251 */     String u = null;
/*     */ 
/* 253 */     for (String perm : perms) {
/* 254 */       if (!uri.startsWith(perm))
/*     */         continue;
/* 256 */       if (viewOnly)
/*     */       {
/* 258 */         int i = uri.lastIndexOf("/");
/* 259 */         if (i == -1) {
/* 260 */           throw new RuntimeException("uri must start width '/':" + 
/* 261 */             uri);
/*     */         }
/* 263 */         u = uri.substring(i + 1);
/*     */ 
/* 265 */         if (u.startsWith("o_")) {
/* 266 */           return false;
/*     */         }
/*     */       }
/* 269 */       return true;
/*     */     }
/*     */ 
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */   private static String getURI(HttpServletRequest request)
/*     */     throws IllegalStateException
/*     */   {
/* 284 */     UrlPathHelper helper = new UrlPathHelper();
/* 285 */     String uri = helper.getOriginatingRequestUri(request);
/* 286 */     String ctxPath = helper.getOriginatingContextPath(request);
/* 287 */     int start = 0; int i = 0; int count = 2;
/* 288 */     if (!StringUtils.isBlank(ctxPath)) {
/* 289 */       count++;
/*     */     }
/* 291 */     while ((i < count) && (start != -1)) {
/* 292 */       start = uri.indexOf('/', start + 1);
/* 293 */       i++;
/*     */     }
/* 295 */     if (start <= 0) {
/* 296 */       throw new IllegalStateException(
/* 297 */         "admin access path not like '/jeeadmin/jspgou/...' pattern: " + 
/* 298 */         uri);
/*     */     }
/* 300 */     return uri.substring(start);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSession(SessionProvider session)
/*     */   {
/* 317 */     this.session = session;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 322 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 327 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setAuthMng(AuthenticationMng authMng) {
/* 332 */     this.authMng = authMng;
/*     */   }
/*     */ 
/*     */   public void setAuth(boolean auth) {
/* 336 */     this.auth = auth;
/*     */   }
/*     */ 
/*     */   public void setExcludeUrls(String[] excludeUrls) {
/* 340 */     this.excludeUrls = excludeUrls;
/*     */   }
/*     */ 
/*     */   public void setAdminId(Integer adminId) {
/* 344 */     this.adminId = adminId;
/*     */   }
/*     */ 
/*     */   public void setLoginUrl(String loginUrl) {
/* 348 */     this.loginUrl = loginUrl;
/*     */   }
/*     */ 
/*     */   public void setProcessUrl(String processUrl) {
/* 352 */     this.processUrl = processUrl;
/*     */   }
/*     */ 
/*     */   public void setReturnUrl(String returnUrl) {
/* 356 */     this.returnUrl = returnUrl;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.web.AdminContextInterceptor
 * JD-Core Version:    0.6.0
 */