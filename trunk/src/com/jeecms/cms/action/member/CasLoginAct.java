/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
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
/*     */ public class CasLoginAct
/*     */ {
/*  47 */   private static final Logger log = LoggerFactory.getLogger(CasLoginAct.class);
/*     */   public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
/*     */   public static final String LOGIN_INPUT = "tpl.loginInput";
/*     */   public static final String LOGIN_STATUS = "tpl.loginStatus";
/*     */   public static final String TPL_INDEX = "tpl.index";
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private ConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private AuthenticationMng authMng;
/*     */ 
/*     */   @Autowired
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @RequestMapping(value={"/login.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, ModelMap model)
/*     */   {
/*  56 */     CmsSite site = CmsUtils.getSite(request);
/*  57 */     String sol = site.getSolutionPath();
/*  58 */     String message = RequestUtils.getQueryParam(request, "message");
/*  59 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/*  60 */     if (authId != null)
/*     */     {
/*  62 */       Authentication auth = this.authMng.retrieve(authId);
/*     */ 
/*  64 */       if (auth != null) {
/*  65 */         return "redirect:/";
/*     */       }
/*     */     }
/*  68 */     FrontUtils.frontData(request, model, site);
/*  69 */     if (!StringUtils.isBlank(message)) {
/*  70 */       model.addAttribute("message", message);
/*     */     }
/*  72 */     return FrontUtils.getTplPath(request, sol, "member", "tpl.loginInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/login.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String password, String captcha, String message, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  79 */     Integer errorRemaining = this.unifiedUserMng.errorRemaining(username);
/*  80 */     CmsSite site = CmsUtils.getSite(request);
/*  81 */     String sol = site.getSolutionPath();
/*  82 */     WebErrors errors = validateSubmit(username, password, captcha, 
/*  83 */       errorRemaining, request, response);
/*  84 */     if (!errors.hasErrors()) {
/*     */       try {
/*  86 */         String ip = RequestUtils.getIpAddr(request);
/*  87 */         Authentication auth = this.authMng.login(username, password, ip, 
/*  88 */           request, response, this.session);
/*     */ 
/*  90 */         this.cmsUserMng.updateLoginInfo(auth.getUid(), ip);
/*  91 */         CmsUser user = this.cmsUserMng.findById(auth.getUid());
/*  92 */         if (user.getDisabled().booleanValue())
/*     */         {
/*  94 */           this.authMng.deleteById(auth.getId());
/*  95 */           this.session.logout(request, response);
/*  96 */           throw new DisabledException("user disabled");
/*     */         }
/*  98 */         removeCookieErrorRemaining(request, response);
/*  99 */         FrontUtils.frontData(request, model, site);
/* 100 */         if (user != null) {
/* 101 */           return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 102 */             "index", "tpl.index");
/*     */         }
/* 104 */         return "redirect:login.jspx";
/*     */       }
/*     */       catch (UsernameNotFoundException e) {
/* 107 */         errors.addErrorString(e.getMessage());
/*     */       } catch (BadCredentialsException e) {
/* 109 */         errors.addErrorString(e.getMessage());
/*     */       } catch (DisabledException e) {
/* 111 */         errors.addErrorString(e.getMessage());
/*     */       }
/*     */     }
/*     */ 
/* 115 */     writeCookieErrorRemaining(errorRemaining, request, response, model);
/* 116 */     errors.toModel(model);
/* 117 */     FrontUtils.frontData(request, model, site);
/* 118 */     if (!StringUtils.isBlank(message)) {
/* 119 */       model.addAttribute("message", message);
/*     */     }
/* 121 */     return FrontUtils.getTplPath(request, sol, "member", "tpl.loginInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logout.jspx"})
/*     */   public String logout(HttpServletRequest request, HttpServletResponse response) {
/* 127 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/* 128 */     if (authId != null) {
/* 129 */       this.authMng.deleteById(authId);
/* 130 */       this.session.logout(request, response);
/*     */     }
/* 132 */     String processUrl = RequestUtils.getQueryParam(request, "processUrl");
/* 133 */     String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
/* 134 */     String view = getView(processUrl, returnUrl, authId);
/* 135 */     if (view != null) {
/* 136 */       return view;
/*     */     }
/* 138 */     return "redirect:login.jspx";
/*     */   }
/*     */ 
/*     */   private WebErrors validateSubmit(String username, String password, String captcha, Integer errorRemaining, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 145 */     WebErrors errors = WebErrors.create(request);
/* 146 */     if (errors.ifOutOfLength(username, "username", 1, 100)) {
/* 147 */       return errors;
/*     */     }
/* 149 */     if (errors.ifOutOfLength(password, "password", 1, 32)) {
/* 150 */       return errors;
/*     */     }
/*     */ 
/* 153 */     if ((!StringUtils.isBlank(captcha)) || (
/* 154 */       (errorRemaining != null) && (errorRemaining.intValue() < 0))) {
/* 155 */       if (errors.ifBlank(captcha, "captcha", 100))
/* 156 */         return errors;
/*     */       try
/*     */       {
/* 159 */         if (!this.imageCaptchaService.validateResponseForID(
/* 160 */           this.session.getSessionId(request, response), captcha).booleanValue())
/*     */         {
/* 161 */           errors.addErrorCode("error.invalidCaptcha");
/* 162 */           return errors;
/*     */         }
/*     */       } catch (CaptchaServiceException e) {
/* 165 */         errors.addErrorCode("error.exceptionCaptcha");
/* 166 */         log.warn("", e);
/* 167 */         return errors;
/*     */       }
/*     */     }
/* 170 */     return errors;
/*     */   }
/*     */ 
/*     */   private String getView(String processUrl, String returnUrl, String authId)
/*     */   {
/* 183 */     if (!StringUtils.isBlank(processUrl)) {
/* 184 */       StringBuilder sb = new StringBuilder("redirect:");
/* 185 */       sb.append(processUrl).append("?").append("auth_key").append("=")
/* 186 */         .append(authId);
/* 187 */       if (!StringUtils.isBlank(returnUrl)) {
/* 188 */         sb.append("&").append("returnUrl").append("=").append(returnUrl);
/*     */       }
/* 190 */       return sb.toString();
/* 191 */     }if (!StringUtils.isBlank(returnUrl)) {
/* 192 */       StringBuilder sb = new StringBuilder("redirect:");
/* 193 */       sb.append(returnUrl);
/* 194 */       return sb.toString();
/*     */     }
/* 196 */     return null;
/*     */   }
/*     */ 
/*     */   private void writeCookieErrorRemaining(Integer userErrorRemaining, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 204 */     Integer errorRemaining = getCookieErrorRemaining(request, response);
/* 205 */     ConfigLogin configLogin = this.configMng.getConfigLogin();
/* 206 */     Integer errorInterval = configLogin.getErrorInterval();
/* 207 */     if ((userErrorRemaining != null) && (
/* 208 */       (errorRemaining == null) || (userErrorRemaining.intValue() < errorRemaining.intValue()))) {
/* 209 */       errorRemaining = userErrorRemaining;
/*     */     }
/* 211 */     int maxErrorTimes = configLogin.getErrorTimes().intValue();
/* 212 */     if ((errorRemaining == null) || (errorRemaining.intValue() > maxErrorTimes))
/* 213 */       errorRemaining = Integer.valueOf(maxErrorTimes);
/* 214 */     else if (errorRemaining.intValue() <= 0)
/* 215 */       errorRemaining = Integer.valueOf(0);
/*     */     else {
/* 217 */       errorRemaining = Integer.valueOf(errorRemaining.intValue() - 1);
/*     */     }
/* 219 */     model.addAttribute("errorRemaining", errorRemaining);
/* 220 */     CookieUtils.addCookie(request, response, "_error_remaining", 
/* 221 */       errorRemaining.toString(), Integer.valueOf(errorInterval.intValue() * 60), null);
/*     */   }
/*     */ 
/*     */   private Integer getCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 226 */     Cookie cookie = CookieUtils.getCookie(request, "_error_remaining");
/* 227 */     if (cookie != null) {
/* 228 */       String value = cookie.getValue();
/* 229 */       if (NumberUtils.isDigits(value)) {
/* 230 */         return Integer.valueOf(Integer.parseInt(value));
/*     */       }
/*     */     }
/* 233 */     return null;
/*     */   }
/*     */ 
/*     */   private void removeCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 238 */     CookieUtils.cancleCookie(request, response, "_error_remaining", 
/* 239 */       null);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.CasLoginAct
 * JD-Core Version:    0.6.0
 */