/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.common.email.MessageTemplate;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
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
/*     */ public class ForgotPasswordAct
/*     */ {
/*  40 */   private static Logger log = LoggerFactory.getLogger(ForgotPasswordAct.class);
/*     */   public static final String FORGOT_PASSWORD_INPUT = "tpl.forgotPasswordInput";
/*     */   public static final String FORGOT_PASSWORD_RESULT = "tpl.forgotPasswordResult";
/*     */   public static final String PASSWORD_RESET = "tpl.passwordReset";
/*     */ 
/*     */   @Autowired
/*     */   private UnifiedUserMng unifiedUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private ConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @RequestMapping(value={"/member/forgot_password.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String forgotPasswordInput(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  57 */     CmsSite site = CmsUtils.getSite(request);
/*  58 */     FrontUtils.frontData(request, model, site);
/*  59 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  60 */       "member", "tpl.forgotPasswordInput");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/forgot_password.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String forgotPasswordSubmit(String username, String email, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  77 */     CmsSite site = CmsUtils.getSite(request);
/*  78 */     WebErrors errors = validateForgotPasswordSubmit(username, email, 
/*  79 */       captcha, request, response);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/*  83 */     UnifiedUser user = this.unifiedUserMng.getByUsername(username);
/*  84 */     EmailSender sender = this.configMng.getEmailSender();
/*  85 */     MessageTemplate msgTpl = this.configMng.getForgotPasswordMessageTemplate();
/*  86 */     model.addAttribute("user", user);
/*  87 */     FrontUtils.frontData(request, model, site);
/*  88 */     if (user == null)
/*     */     {
/*  90 */       model.addAttribute("status", Integer.valueOf(1));
/*  91 */     } else if (StringUtils.isBlank(user.getEmail()))
/*     */     {
/*  93 */       model.addAttribute("status", Integer.valueOf(2));
/*  94 */     } else if (!user.getEmail().equals(email))
/*     */     {
/*  96 */       model.addAttribute("status", Integer.valueOf(3));
/*  97 */     } else if (sender == null)
/*     */     {
/*  99 */       model.addAttribute("status", Integer.valueOf(4));
/* 100 */     } else if (msgTpl == null)
/*     */     {
/* 102 */       model.addAttribute("status", Integer.valueOf(5));
/*     */     }
/*     */     else try {
/* 105 */         this.unifiedUserMng.passwordForgotten(user.getId(), sender, msgTpl);
/* 106 */         model.addAttribute("status", Integer.valueOf(0));
/*     */       }
/*     */       catch (Exception e) {
/* 109 */         model.addAttribute("status", Integer.valueOf(100));
/* 110 */         model.addAttribute("message", e.getMessage());
/* 111 */         log.error("send email exception.", e);
/*     */       }
/*     */ 
/* 114 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 115 */       "member", "tpl.forgotPasswordResult");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/password_reset.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String passwordReset(Integer uid, String key, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 122 */     CmsSite site = CmsUtils.getSite(request);
/* 123 */     WebErrors errors = validatePasswordReset(uid, key, request);
/* 124 */     if (errors.hasErrors()) {
/* 125 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 127 */     UnifiedUser user = this.unifiedUserMng.findById(uid);
/* 128 */     if (user == null)
/*     */     {
/* 130 */       model.addAttribute("status", Integer.valueOf(1));
/* 131 */     } else if (StringUtils.isBlank(user.getResetKey()))
/*     */     {
/* 133 */       model.addAttribute("status", Integer.valueOf(2));
/* 134 */     } else if (!user.getResetKey().equals(key))
/*     */     {
/* 136 */       model.addAttribute("status", Integer.valueOf(3));
/*     */     } else {
/* 138 */       this.unifiedUserMng.resetPassword(uid);
/* 139 */       model.addAttribute("status", Integer.valueOf(0));
/*     */     }
/* 141 */     FrontUtils.frontData(request, model, site);
/* 142 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 143 */       "member", "tpl.passwordReset");
/*     */   }
/*     */ 
/*     */   private WebErrors validateForgotPasswordSubmit(String username, String email, String captcha, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 149 */     WebErrors errors = WebErrors.create(request);
/* 150 */     if (errors.ifBlank(username, "username", 100)) {
/* 151 */       return errors;
/*     */     }
/* 153 */     if (errors.ifBlank(email, "email", 100)) {
/* 154 */       return errors;
/*     */     }
/* 156 */     if (errors.ifBlank(captcha, "captcha", 20))
/* 157 */       return errors;
/*     */     try
/*     */     {
/* 160 */       if (!this.imageCaptchaService.validateResponseForID(
/* 161 */         this.session.getSessionId(request, response), captcha).booleanValue())
/*     */       {
/* 162 */         errors.addErrorCode("error.invalidCaptcha");
/* 163 */         return errors;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 166 */       errors.addErrorCode("error.exceptionCaptcha");
/* 167 */       log.warn("", e);
/* 168 */       return errors;
/*     */     }
/* 170 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePasswordReset(Integer uid, String key, HttpServletRequest request)
/*     */   {
/* 175 */     WebErrors errors = WebErrors.create(request);
/* 176 */     if (errors.ifNull(uid, "uid")) {
/* 177 */       return errors;
/*     */     }
/* 179 */     if (errors.ifBlank(key, "key", 50)) {
/* 180 */       return errors;
/*     */     }
/* 182 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.ForgotPasswordAct
 * JD-Core Version:    0.6.0
 */