/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.common.security.BadCredentialsException;
/*     */ import com.jeecms.common.security.DisabledException;
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.entity.Authentication;
/*     */ import com.jeecms.core.entity.Config.ConfigLogin;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsLoginAct
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(CmsLoginAct.class);
/*     */   public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private ConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private AuthenticationMng authMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @RequestMapping(value={"/login.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  50 */     String message = RequestUtils.getQueryParam(request, "message");
/*  51 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/*  52 */     if (authId != null)
/*     */     {
/*  54 */       Authentication auth = this.authMng.retrieve(authId);
/*     */ 
/*  56 */       if (auth != null) {
/*  57 */         return "index";
/*     */       }
/*     */     }
/*  60 */     writeCookieErrorRemaining(null, request, response, model);
/*  61 */     if (!StringUtils.isBlank(message)) {
/*  62 */       model.addAttribute("message", message);
/*     */     }
/*  64 */     return "login";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/login.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String password, String captcha, String message, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  71 */     Integer errorRemaining = this.unifiedUserMng.errorRemaining(username);
/*  72 */     WebErrors errors = validateSubmit(username, password, captcha, 
/*  73 */       errorRemaining, request, response);
/*  74 */     if (!errors.hasErrors()) {
/*     */       try {
/*  76 */         String ip = RequestUtils.getIpAddr(request);
/*  77 */         Authentication auth = this.authMng.login(username, password, ip, 
/*  78 */           request, response, this.session);
/*     */ 
/*  80 */         this.cmsUserMng.updateLoginInfo(auth.getUid(), ip);
/*  81 */         CmsUser user = this.cmsUserMng.findById(auth.getUid());
/*  82 */         if (user.getDisabled().booleanValue())
/*     */         {
/*  84 */           this.authMng.deleteById(auth.getId());
/*  85 */           this.session.logout(request, response);
/*  86 */           throw new DisabledException("user disabled");
/*     */         }
/*  88 */         this.cmsLogMng.loginSuccess(request, user, "login.log.loginSuccess");
/*  89 */         removeCookieErrorRemaining(request, response);
/*  90 */         if (user != null)
/*     */         {
/*  92 */           return "redirect:index.do";
/*     */         }
/*  94 */         return "redirect:login.do";
/*     */       }
/*     */       catch (UsernameNotFoundException e) {
/*  97 */         errors.addErrorString(e.getMessage());
/*  98 */         this.cmsLogMng.loginFailure(request, "login.log.loginFailure", 
/*  99 */           "username=" + username);
/*     */       } catch (BadCredentialsException e) {
/* 101 */         errors.addErrorString(e.getMessage());
/* 102 */         this.cmsLogMng.loginFailure(request, "login.log.loginFailure", 
/* 103 */           "username=" + username);
/*     */       } catch (DisabledException e) {
/* 105 */         errors.addErrorString(e.getMessage());
/* 106 */         this.cmsLogMng.loginFailure(request, "login.log.loginFailure", 
/* 107 */           "username=" + username);
/*     */       }
/*     */     }
/*     */ 
/* 111 */     writeCookieErrorRemaining(errorRemaining, request, response, model);
/* 112 */     errors.toModel(model);
/* 113 */     if (!StringUtils.isBlank(message)) {
/* 114 */       model.addAttribute("message", message);
/*     */     }
/* 116 */     return "login";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logout.do"})
/*     */   public String logout(HttpServletRequest request, HttpServletResponse response) {
/* 122 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/* 123 */     if (authId != null) {
/* 124 */       this.authMng.deleteById(authId);
/* 125 */       this.session.logout(request, response);
/*     */     }
/* 127 */     String processUrl = RequestUtils.getQueryParam(request, "processUrl");
/* 128 */     String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
/* 129 */     String view = getView(processUrl, returnUrl, authId);
/* 130 */     if (view != null) {
/* 131 */       return view;
/*     */     }
/* 133 */     return "redirect:login.do";
/*     */   }
/*     */ 
/*     */   private WebErrors validateSubmit(String username, String password, String captcha, Integer errorRemaining, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 140 */     WebErrors errors = WebErrors.create(request);
/* 141 */     if (errors.ifOutOfLength(username, "username", 1, 100)) {
/* 142 */       return errors;
/*     */     }
/* 144 */     if (errors.ifOutOfLength(password, "password", 1, 32)) {
/* 145 */       return errors;
/*     */     }
/*     */ 
/* 148 */     if ((!StringUtils.isBlank(captcha)) || (
/* 149 */       (errorRemaining != null) && (errorRemaining.intValue() < 0))) {
/* 150 */       if (errors.ifBlank(captcha, "captcha", 100))
/* 151 */         return errors;
/*     */       try
/*     */       {
/* 154 */         if (!this.imageCaptchaService.validateResponseForID(
/* 155 */           this.session.getSessionId(request, response), captcha).booleanValue())
/*     */         {
/* 156 */           errors.addErrorCode("error.invalidCaptcha");
/* 157 */           return errors;
/*     */         }
/*     */       } catch (CaptchaServiceException e) {
/* 160 */         errors.addErrorCode("error.exceptionCaptcha");
/* 161 */         log.warn("", e);
/* 162 */         return errors;
/*     */       }
/*     */     }
/* 165 */     return errors;
/*     */   }
/*     */ 
/*     */   private String getView(String processUrl, String returnUrl, String authId)
/*     */   {
/* 178 */     if (!StringUtils.isBlank(processUrl)) {
/* 179 */       StringBuilder sb = new StringBuilder("redirect:");
/* 180 */       sb.append(processUrl).append("?").append("auth_key").append("=")
/* 181 */         .append(authId);
/* 182 */       if (!StringUtils.isBlank(returnUrl)) {
/* 183 */         sb.append("&").append("returnUrl").append("=").append(returnUrl);
/*     */       }
/* 185 */       return sb.toString();
/* 186 */     }if (!StringUtils.isBlank(returnUrl)) {
/* 187 */       StringBuilder sb = new StringBuilder("redirect:");
/* 188 */       sb.append(returnUrl);
/* 189 */       return sb.toString();
/*     */     }
/* 191 */     return null;
/*     */   }
/*     */ 
/*     */   private void writeCookieErrorRemaining(Integer userErrorRemaining, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 199 */     Integer errorRemaining = getCookieErrorRemaining(request, response);
/* 200 */     ConfigLogin configLogin = this.configMng.getConfigLogin();
/* 201 */     Integer errorInterval = configLogin.getErrorInterval();
/* 202 */     if ((userErrorRemaining != null) && (
/* 203 */       (errorRemaining == null) || (userErrorRemaining.intValue() < errorRemaining.intValue()))) {
/* 204 */       errorRemaining = userErrorRemaining;
/*     */     }
/* 206 */     int maxErrorTimes = configLogin.getErrorTimes().intValue();
/* 207 */     if ((errorRemaining == null) || (errorRemaining.intValue() > maxErrorTimes))
/* 208 */       errorRemaining = Integer.valueOf(maxErrorTimes);
/* 209 */     else if (errorRemaining.intValue() <= 0)
/* 210 */       errorRemaining = Integer.valueOf(0);
/*     */     else {
/* 212 */       errorRemaining = Integer.valueOf(errorRemaining.intValue() - 1);
/*     */     }
/* 214 */     model.addAttribute("errorRemaining", errorRemaining);
/* 215 */     CookieUtils.addCookie(request, response, "_error_remaining", 
/* 216 */       errorRemaining.toString(), Integer.valueOf(errorInterval.intValue() * 60), null);
/*     */   }
/*     */ 
/*     */   private void removeCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 221 */     CookieUtils.cancleCookie(request, response, "_error_remaining", 
/* 222 */       null);
/*     */   }
/*     */ 
/*     */   private Integer getCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 227 */     Cookie cookie = CookieUtils.getCookie(request, "_error_remaining");
/* 228 */     if (cookie != null) {
/* 229 */       String value = cookie.getValue();
/* 230 */       if (NumberUtils.isDigits(value)) {
/* 231 */         return Integer.valueOf(Integer.parseInt(value));
/*     */       }
/*     */     }
/* 234 */     return null;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.CmsLoginAct
 * JD-Core Version:    0.6.0
 */