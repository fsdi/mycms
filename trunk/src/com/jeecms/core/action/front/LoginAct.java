/*     */ package com.jeecms.core.action.front;
/*     */ 
/*     */ import com.jeecms.common.security.BadCredentialsException;
/*     */ import com.jeecms.common.security.UsernameNotFoundException;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.entity.Authentication;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class LoginAct
/*     */ {
/*     */   public static final String PROCESS_URL = "processUrl";
/*     */   public static final String RETURN_URL = "returnUrl";
/*     */   public static final String MESSAGE = "message";
/*     */   public static final String LOGIN_INPUT = "/WEB-INF/t/jeecore/login.html";
/*     */   public static final String LOGIN_SUCCESS = "/WEB-INF/t/jeecore/login_success.html";
/*     */ 
/*     */   @Autowired
/*     */   private AuthenticationMng authMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @RequestMapping(value={"/login.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, ModelMap model)
/*     */   {
/*  67 */     String processUrl = RequestUtils.getQueryParam(request, "processUrl");
/*  68 */     String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
/*  69 */     String message = RequestUtils.getQueryParam(request, "message");
/*  70 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/*  71 */     if (authId != null)
/*     */     {
/*  73 */       Authentication auth = this.authMng.retrieve(authId);
/*     */ 
/*  75 */       if (auth != null) {
/*  76 */         String view = getView(processUrl, returnUrl, auth.getId());
/*  77 */         if (view != null) {
/*  78 */           return view;
/*     */         }
/*  80 */         model.addAttribute("auth", auth);
/*  81 */         return "/WEB-INF/t/jeecore/login_success.html";
/*     */       }
/*     */     }
/*     */ 
/*  85 */     if (!StringUtils.isBlank(processUrl)) {
/*  86 */       model.addAttribute("processUrl", processUrl);
/*     */     }
/*  88 */     if (!StringUtils.isBlank(returnUrl)) {
/*  89 */       model.addAttribute("returnUrl", returnUrl);
/*     */     }
/*  91 */     if (!StringUtils.isBlank(message)) {
/*  92 */       model.addAttribute("message", message);
/*     */     }
/*  94 */     return "/WEB-INF/t/jeecore/login.html";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/login.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String password, String processUrl, String returnUrl, String message, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 101 */     WebErrors errors = validateSubmit(username, password, request);
/* 102 */     if (!errors.hasErrors()) {
/*     */       try {
/* 104 */         Authentication auth = this.authMng.login(username, password, 
/* 105 */           RequestUtils.getIpAddr(request), request, response, 
/* 106 */           this.session);
/* 107 */         String view = getView(processUrl, returnUrl, auth.getId());
/* 108 */         if (view != null) {
/* 109 */           return view;
/*     */         }
/* 111 */         model.addAttribute("auth", auth);
/* 112 */         return "/WEB-INF/t/jeecore/login_success.html";
/*     */       }
/*     */       catch (UsernameNotFoundException e) {
/* 115 */         errors.addErrorString(e.getMessage());
/*     */       } catch (BadCredentialsException e) {
/* 117 */         errors.addErrorString(e.getMessage());
/*     */       }
/*     */     }
/* 120 */     errors.toModel(model);
/* 121 */     if (!StringUtils.isBlank(processUrl)) {
/* 122 */       model.addAttribute("processUrl", processUrl);
/*     */     }
/* 124 */     if (!StringUtils.isBlank(returnUrl)) {
/* 125 */       model.addAttribute("returnUrl", returnUrl);
/*     */     }
/* 127 */     if (!StringUtils.isBlank(message)) {
/* 128 */       model.addAttribute("message", message);
/*     */     }
/* 130 */     return "/WEB-INF/t/jeecore/login.html";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logout.jspx"})
/*     */   public String logout(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 137 */     String authId = (String)this.session.getAttribute(request, "auth_key");
/* 138 */     if (authId != null) {
/* 139 */       this.authMng.deleteById(authId);
/* 140 */       this.session.logout(request, response);
/*     */     }
/* 142 */     String processUrl = RequestUtils.getQueryParam(request, "processUrl");
/* 143 */     String returnUrl = RequestUtils.getQueryParam(request, "returnUrl");
/* 144 */     String view = getView(processUrl, returnUrl, authId);
/* 145 */     if (view != null) {
/* 146 */       return view;
/*     */     }
/* 148 */     return "redirect:login.jspx";
/*     */   }
/*     */ 
/*     */   private String getView(String processUrl, String returnUrl, String authId)
/*     */   {
/* 161 */     if (!StringUtils.isBlank(processUrl)) {
/* 162 */       StringBuilder sb = new StringBuilder("redirect:");
/* 163 */       sb.append(processUrl).append("?").append("auth_key").append("=")
/* 164 */         .append(authId);
/* 165 */       if (!StringUtils.isBlank(returnUrl)) {
/* 166 */         sb.append("&").append("returnUrl").append("=").append(returnUrl);
/*     */       }
/* 168 */       return sb.toString();
/* 169 */     }if (!StringUtils.isBlank(returnUrl)) {
/* 170 */       StringBuilder sb = new StringBuilder("redirect:");
/* 171 */       sb.append(returnUrl);
/* 172 */       if (!StringUtils.isBlank(authId)) {
/* 173 */         sb.append("?").append("auth_key").append("=").append(authId);
/*     */       }
/* 175 */       return sb.toString();
/*     */     }
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSubmit(String username, String password, HttpServletRequest request)
/*     */   {
/* 183 */     WebErrors errors = WebErrors.create(request);
/* 184 */     if (errors.ifOutOfLength(username, "username", 3, 100)) {
/* 185 */       return errors;
/*     */     }
/* 187 */     if (errors.ifOutOfLength(password, "password", 3, 32)) {
/* 188 */       return errors;
/*     */     }
/* 190 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.action.front.LoginAct
 * JD-Core Version:    0.6.0
 */