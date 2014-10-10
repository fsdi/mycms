/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.AuthenticationMng;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import javax.mail.MessagingException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class RegisterAct
/*     */ {
/*  47 */   private static final Logger log = LoggerFactory.getLogger(RegisterAct.class);
/*     */   public static final String REGISTER = "tpl.register";
/*     */   public static final String REGISTER_RESULT = "tpl.registerResult";
/*     */   public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
/*     */   public static final String LOGIN_INPUT = "tpl.loginInput";
/*     */ 
/*     */   @Autowired
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @Autowired
/*     */   private ConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private AuthenticationMng authMng;
/*     */ 
/*     */   @RequestMapping(value={"/register.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  57 */     CmsSite site = CmsUtils.getSite(request);
/*  58 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  60 */     if (!mcfg.isMemberOn()) {
/*  61 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*     */ 
/*  64 */     if (!mcfg.isRegisterOn()) {
/*  65 */       return FrontUtils.showMessage(request, model, 
/*  66 */         "member.registerClose", new String[0]);
/*     */     }
/*     */ 
/*  68 */     FrontUtils.frontData(request, model, site);
/*  69 */     model.addAttribute("mcfg", mcfg);
/*  70 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  71 */       "member", "tpl.register");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/register.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String submit(String username, String email, String password, CmsUserExt userExt, String captcha, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/*  79 */     CmsSite site = CmsUtils.getSite(request);
/*  80 */     CmsConfig config = site.getConfig();
/*  81 */     WebErrors errors = validateSubmit(username, email, password, captcha, 
/*  82 */       site, request, response);
/*  83 */     if (errors.hasErrors()) {
/*  84 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/*  86 */     String ip = RequestUtils.getIpAddr(request);
/*  87 */     if (config.getEmailValidate().booleanValue()) {
/*  88 */       EmailSender sender = this.configMng.getEmailSender();
/*  89 */       MessageTemplate msgTpl = this.configMng.getRegisterMessageTemplate();
/*  90 */       if (sender == null)
/*     */       {
/*  92 */         model.addAttribute("status", Integer.valueOf(4));
/*  93 */       } else if (msgTpl == null)
/*     */       {
/*  95 */         model.addAttribute("status", Integer.valueOf(5));
/*     */       }
/*     */       else try {
/*  98 */           this.cmsUserMng.registerMember(username, email, password, ip, null, userExt, 
/*  99 */             Boolean.valueOf(false), sender, msgTpl);
/* 100 */           model.addAttribute("status", Integer.valueOf(0));
/*     */         }
/*     */         catch (UnsupportedEncodingException e) {
/* 103 */           model.addAttribute("status", Integer.valueOf(100));
/* 104 */           model.addAttribute("message", e.getMessage());
/* 105 */           log.error("send email exception.", e);
/*     */         }
/*     */         catch (MessagingException e) {
/* 108 */           model.addAttribute("status", Integer.valueOf(101));
/* 109 */           model.addAttribute("message", e.getMessage());
/* 110 */           log.error("send email exception.", e);
/*     */         }
/*     */ 
/* 113 */       log.info("member register success. username={}", username);
/* 114 */       FrontUtils.frontData(request, model, site);
/* 115 */       if (!StringUtils.isBlank(nextUrl)) {
/* 116 */         response.sendRedirect(nextUrl);
/* 117 */         return null;
/*     */       }
/* 119 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 120 */         "member", "tpl.registerResult");
/*     */     }
/*     */ 
/* 123 */     this.cmsUserMng.registerMember(username, email, password, ip, null, userExt);
/* 124 */     log.info("member register success. username={}", username);
/* 125 */     FrontUtils.frontData(request, model, site);
/* 126 */     FrontUtils.frontPageData(request, model);
/* 127 */     model.addAttribute("success", Boolean.valueOf(true));
/* 128 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 129 */       "member", "tpl.loginInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/active.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String active(String username, String key, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 137 */     CmsSite site = CmsUtils.getSite(request);
/* 138 */     WebErrors errors = validateActive(username, key, request, response);
/* 139 */     if (errors.hasErrors()) {
/* 140 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 142 */     UnifiedUser user = this.unifiedUserMng.active(username, key);
/* 143 */     String ip = RequestUtils.getIpAddr(request);
/* 144 */     this.authMng.activeLogin(user, ip, request, response, this.session);
/* 145 */     FrontUtils.frontData(request, model, site);
/* 146 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 147 */       "member", "tpl.registerActiveSuccess");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/username_unique.jspx"})
/*     */   public void usernameUnique(HttpServletRequest request, HttpServletResponse response) {
/* 153 */     String username = RequestUtils.getQueryParam(request, "username");
/*     */ 
/* 155 */     if (StringUtils.isBlank(username)) {
/* 156 */       ResponseUtils.renderJson(response, "false");
/* 157 */       return;
/*     */     }
/* 159 */     CmsSite site = CmsUtils.getSite(request);
/* 160 */     CmsConfig config = site.getConfig();
/*     */ 
/* 162 */     if (!config.getMemberConfig().checkUsernameReserved(username)) {
/* 163 */       ResponseUtils.renderJson(response, "false");
/* 164 */       return;
/*     */     }
/*     */ 
/* 167 */     if (this.unifiedUserMng.usernameExist(username)) {
/* 168 */       ResponseUtils.renderJson(response, "false");
/* 169 */       return;
/*     */     }
/* 171 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/email_unique.jspx"})
/*     */   public void emailUnique(HttpServletRequest request, HttpServletResponse response) {
/* 177 */     String email = RequestUtils.getQueryParam(request, "email");
/*     */ 
/* 179 */     if (StringUtils.isBlank(email)) {
/* 180 */       ResponseUtils.renderJson(response, "false");
/* 181 */       return;
/*     */     }
/*     */ 
/* 184 */     if (this.unifiedUserMng.emailExist(email)) {
/* 185 */       ResponseUtils.renderJson(response, "false");
/* 186 */       return;
/*     */     }
/* 188 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ 
/*     */   private WebErrors validateSubmit(String username, String email, String password, String captcha, CmsSite site, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 194 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/* 195 */     WebErrors errors = WebErrors.create(request);
/*     */     try {
/* 197 */       if (!this.imageCaptchaService.validateResponseForID(
/* 198 */         this.session.getSessionId(request, response), captcha).booleanValue())
/*     */       {
/* 199 */         errors.addErrorCode("error.invalidCaptcha");
/* 200 */         return errors;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 203 */       errors.addErrorCode("error.exceptionCaptcha");
/* 204 */       log.warn("", e);
/* 205 */       return errors;
/*     */     }
/* 207 */     if (errors.ifOutOfLength(username, "username", 
/* 208 */       mcfg.getUsernameMinLen(), 100)) {
/* 209 */       return errors;
/*     */     }
/* 211 */     if (errors.ifNotUsername(username, "username", 
/* 212 */       mcfg.getUsernameMinLen(), 100)) {
/* 213 */       return errors;
/*     */     }
/* 215 */     if (errors.ifOutOfLength(password, "password", 
/* 216 */       mcfg.getPasswordMinLen(), 100)) {
/* 217 */       return errors;
/*     */     }
/* 219 */     if (errors.ifNotEmail(email, "email", 100)) {
/* 220 */       return errors;
/*     */     }
/*     */ 
/* 223 */     if (!mcfg.checkUsernameReserved(username)) {
/* 224 */       errors.addErrorCode("error.usernameReserved");
/* 225 */       return errors;
/*     */     }
/*     */ 
/* 228 */     if (this.unifiedUserMng.usernameExist(username)) {
/* 229 */       errors.addErrorCode("error.usernameExist");
/* 230 */       return errors;
/*     */     }
/* 232 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateActive(String username, String activationCode, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 237 */     WebErrors errors = WebErrors.create(request);
/* 238 */     if ((StringUtils.isBlank(username)) || 
/* 239 */       (StringUtils.isBlank(activationCode))) {
/* 240 */       errors.addErrorCode("error.exceptionParams");
/* 241 */       return errors;
/*     */     }
/* 243 */     UnifiedUser user = this.unifiedUserMng.getByUsername(username);
/* 244 */     if (user == null) {
/* 245 */       errors.addErrorCode("error.usernameNotExist");
/* 246 */       return errors;
/*     */     }
/* 248 */     if ((user.getActivation().booleanValue()) || 
/* 249 */       (StringUtils.isBlank(user.getActivationCode()))) {
/* 250 */       errors.addErrorCode("error.usernameActivated");
/* 251 */       return errors;
/*     */     }
/* 253 */     if (!user.getActivationCode().equals(activationCode)) {
/* 254 */       errors.addErrorCode("error.exceptionActivationCode");
/* 255 */       return errors;
/*     */     }
/* 257 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.RegisterAct
 * JD-Core Version:    0.6.0
 */