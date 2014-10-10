/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.main.CmsConfigMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.email.EmailSender;
/*     */ import com.jeecms.core.entity.Config.ConfigEmailSender;
/*     */ import com.jeecms.core.entity.Config.ConfigLogin;
/*     */ import com.jeecms.core.entity.Config.ConfigMessageTemplate;
/*     */ import com.jeecms.core.manager.ConfigMng;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsConfigAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(CmsConfigAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsConfigMng manager;
/*     */ 
/*  31 */   @RequestMapping({"/config/v_system_edit.do"})
/*     */   public String systemEdit(HttpServletRequest request, ModelMap model) { model.addAttribute("cmsConfig", this.manager.get());
/*  32 */     return "config/system_edit"; }
/*     */ 
/*     */   @RequestMapping({"/config/o_system_update.do"})
/*     */   public String systemUpdate(CmsConfig bean, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  38 */     WebErrors errors = validateSystemUpdate(bean, request);
/*  39 */     if (errors.hasErrors()) {
/*  40 */       return errors.showErrorPage(model);
/*     */     }
/*  42 */     bean = this.manager.update(bean);
/*  43 */     model.addAttribute("message", "global.success");
/*  44 */     log.info("update systemConfig of CmsConfig.");
/*  45 */     this.cmsLogMng.operating(request, "cmsConfig.log.systemUpdate", null);
/*  46 */     return systemEdit(request, model);
/*     */   }
/*     */   @RequestMapping({"/config/v_mark_edit.do"})
/*     */   public String markEdit(HttpServletRequest request, ModelMap model) {
/*  51 */     model.addAttribute("markConfig", this.manager.get().getMarkConfig());
/*  52 */     return "config/mark_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_mark_update.do"})
/*     */   public String markUpdate(MarkConfig bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  58 */     WebErrors errors = validateMarkUpdate(bean, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*     */     }
/*  62 */     bean = this.manager.updateMarkConfig(bean);
/*  63 */     model.addAttribute("message", "global.success");
/*  64 */     log.info("update markConfig of CmsConfig.");
/*  65 */     this.cmsLogMng.operating(request, "cmsConfig.log.markUpdate", null);
/*  66 */     return markEdit(request, model);
/*     */   }
/*     */   @RequestMapping({"/config/v_member_edit.do"})
/*     */   public String memberEdit(HttpServletRequest request, ModelMap model) {
/*  71 */     model.addAttribute("memberConfig", this.manager.get().getMemberConfig());
/*  72 */     return "config/member_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_member_update.do"})
/*     */   public String memberUpdate(MemberConfig bean, ConfigLogin configLogin, ConfigEmailSender emailSender, ConfigMessageTemplate msgTpl, HttpServletRequest request, ModelMap model)
/*     */   {
/*  79 */     WebErrors errors = validateMemberUpdate(bean, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     this.manager.updateMemberConfig(bean);
/*  84 */     model.addAttribute("message", "global.success");
/*  85 */     log.info("update memberConfig of CmsConfig.");
/*  86 */     this.cmsLogMng.operating(request, "cmsConfig.log.memberUpdate", null);
/*  87 */     return memberEdit(request, model);
/*     */   }
/*     */   @RequestMapping({"/config/v_login_edit.do"})
/*     */   public String loginEdit(HttpServletRequest request, ModelMap model) {
/*  92 */     model.addAttribute("configLogin", this.configMng.getConfigLogin());
/*  93 */     model.addAttribute("emailSender", this.configMng.getEmailSender());
/*  94 */     model.addAttribute("forgotPasswordTemplate", this.configMng.getForgotPasswordMessageTemplate());
/*  95 */     model.addAttribute("registerTemplate", this.configMng.getRegisterMessageTemplate());
/*  96 */     return "config/login_edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/config/o_login_update.do"})
/*     */   public String loginUpdate(ConfigLogin configLogin, ConfigEmailSender emailSender, ConfigMessageTemplate msgTpl, HttpServletRequest request, ModelMap model)
/*     */   {
/* 104 */     if (StringUtils.isBlank(emailSender.getPassword())) {
/* 105 */       emailSender.setPassword(this.configMng.getEmailSender().getPassword());
/*     */     }
/* 107 */     this.configMng.updateOrSave(configLogin.getAttr());
/* 108 */     this.configMng.updateOrSave(emailSender.getAttr());
/* 109 */     this.configMng.updateOrSave(msgTpl.getAttr());
/* 110 */     model.addAttribute("message", "global.success");
/* 111 */     log.info("update loginCoinfig of Config.");
/* 112 */     this.cmsLogMng.operating(request, "cmsConfig.log.loginUpdate", null);
/* 113 */     return loginEdit(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSystemUpdate(CmsConfig bean, HttpServletRequest request)
/*     */   {
/* 118 */     WebErrors errors = WebErrors.create(request);
/* 119 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateMarkUpdate(MarkConfig bean, HttpServletRequest request)
/*     */   {
/* 124 */     WebErrors errors = WebErrors.create(request);
/* 125 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateMemberUpdate(MemberConfig bean, HttpServletRequest request)
/*     */   {
/* 130 */     WebErrors errors = WebErrors.create(request);
/* 131 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsConfigAct
 * JD-Core Version:    0.6.0
 */