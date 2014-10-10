/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.main.CmsUserExtMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.io.IOException;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class MemberAct
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(MemberAct.class);
/*     */   public static final String MEMBER_CENTER = "tpl.memberCenter";
/*     */   public static final String MEMBER_PROFILE = "tpl.memberProfile";
/*     */   public static final String MEMBER_PORTRAIT = "tpl.memberPortrait";
/*     */   public static final String MEMBER_PASSWORD = "tpl.memberPassword";
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserExtMng cmsUserExtMng;
/*     */ 
/*     */   @RequestMapping(value={"/member/index.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  54 */     CmsSite site = CmsUtils.getSite(request);
/*  55 */     CmsUser user = CmsUtils.getUser(request);
/*  56 */     FrontUtils.frontData(request, model, site);
/*  57 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  59 */     if (!mcfg.isMemberOn()) {
/*  60 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  62 */     if (user == null) {
/*  63 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  65 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  66 */       "member", "tpl.memberCenter");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/profile.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String profileInput(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  80 */     CmsSite site = CmsUtils.getSite(request);
/*  81 */     CmsUser user = CmsUtils.getUser(request);
/*  82 */     FrontUtils.frontData(request, model, site);
/*  83 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  85 */     if (!mcfg.isMemberOn()) {
/*  86 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  88 */     if (user == null) {
/*  89 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  91 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  92 */       "member", "tpl.memberProfile");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/portrait.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String portrait(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 104 */     CmsSite site = CmsUtils.getSite(request);
/* 105 */     CmsUser user = CmsUtils.getUser(request);
/* 106 */     FrontUtils.frontData(request, model, site);
/* 107 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 109 */     if (!mcfg.isMemberOn()) {
/* 110 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 112 */     if (user == null) {
/* 113 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 115 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 116 */       "member", "tpl.memberPortrait");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/profile.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String profileSubmit(CmsUserExt ext, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 132 */     CmsSite site = CmsUtils.getSite(request);
/* 133 */     CmsUser user = CmsUtils.getUser(request);
/* 134 */     FrontUtils.frontData(request, model, site);
/* 135 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 137 */     if (!mcfg.isMemberOn()) {
/* 138 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 140 */     if (user == null) {
/* 141 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 143 */     ext.setId(user.getId());
/* 144 */     this.cmsUserExtMng.update(ext, user);
/* 145 */     log.info("update CmsUserExt success. id={}", user.getId());
/* 146 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/pwd.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String passwordInput(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 160 */     CmsSite site = CmsUtils.getSite(request);
/* 161 */     CmsUser user = CmsUtils.getUser(request);
/* 162 */     FrontUtils.frontData(request, model, site);
/* 163 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 165 */     if (!mcfg.isMemberOn()) {
/* 166 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 168 */     if (user == null) {
/* 169 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 171 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 172 */       "member", "tpl.memberPassword");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/pwd.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String passwordSubmit(String origPwd, String newPwd, String email, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 196 */     CmsSite site = CmsUtils.getSite(request);
/* 197 */     CmsUser user = CmsUtils.getUser(request);
/* 198 */     FrontUtils.frontData(request, model, site);
/* 199 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 201 */     if (!mcfg.isMemberOn()) {
/* 202 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 204 */     if (user == null) {
/* 205 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 207 */     WebErrors errors = validatePasswordSubmit(user.getId(), origPwd, 
/* 208 */       newPwd, email, request);
/* 209 */     if (errors.hasErrors()) {
/* 210 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 212 */     this.cmsUserMng.updatePwdEmail(user.getId(), newPwd, email);
/* 213 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/checkPwd.jspx"})
/*     */   public void checkPwd(String origPwd, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 227 */     CmsUser user = CmsUtils.getUser(request);
/* 228 */     boolean pass = this.cmsUserMng.isPasswordValid(user.getId(), origPwd);
/* 229 */     ResponseUtils.renderJson(response, pass ? "true" : "false");
/*     */   }
/*     */ 
/*     */   private WebErrors validatePasswordSubmit(Integer id, String origPwd, String newPwd, String email, HttpServletRequest request)
/*     */   {
/* 234 */     WebErrors errors = WebErrors.create(request);
/* 235 */     if (errors.ifBlank(origPwd, "origPwd", 100)) {
/* 236 */       return errors;
/*     */     }
/* 238 */     if (errors.ifMaxLength(newPwd, "newPwd", 100)) {
/* 239 */       return errors;
/*     */     }
/* 241 */     if (errors.ifNotEmail(email, "email", 100)) {
/* 242 */       return errors;
/*     */     }
/* 244 */     if (!this.cmsUserMng.isPasswordValid(id, origPwd)) {
/* 245 */       errors.addErrorCode("member.origPwdInvalid");
/* 246 */       return errors;
/*     */     }
/* 248 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.MemberAct
 * JD-Core Version:    0.6.0
 */