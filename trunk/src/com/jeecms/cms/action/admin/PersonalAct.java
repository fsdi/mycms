/*    */ package com.jeecms.cms.action.admin;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.entity.main.CmsUserExt;
/*    */ import com.jeecms.cms.manager.main.CmsUserExtMng;
/*    */ import com.jeecms.cms.manager.main.CmsUserMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.WebErrors;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class PersonalAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsUserMng cmsUserMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsUserExtMng cmsUserExtMng;
/*    */ 
/*    */   @RequestMapping({"/personal/v_profile.do"})
/*    */   public String profileEdit(HttpServletRequest request, ModelMap model)
/*    */   {
/* 23 */     CmsUser user = CmsUtils.getUser(request);
/* 24 */     model.addAttribute("user", user);
/* 25 */     return "personal/profile";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/personal/o_profile.do"})
/*    */   public String profileUpdate(String origPwd, String newPwd, String email, String realname, HttpServletRequest request, ModelMap model) {
/* 31 */     CmsUser user = CmsUtils.getUser(request);
/* 32 */     WebErrors errors = validatePasswordSubmit(user.getId(), origPwd, 
/* 33 */       newPwd, email, realname, request);
/* 34 */     if (errors.hasErrors()) {
/* 35 */       return errors.showErrorPage(model);
/*    */     }
/* 37 */     CmsUserExt ext = user.getUserExt();
/* 38 */     if (ext == null) {
/* 39 */       ext = new CmsUserExt();
/*    */     }
/* 41 */     ext.setRealname(realname);
/* 42 */     this.cmsUserExtMng.update(ext, user);
/* 43 */     this.cmsUserMng.updatePwdEmail(user.getId(), newPwd, email);
/* 44 */     model.addAttribute("message", "global.success");
/* 45 */     return profileEdit(request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/personal/v_checkPwd.do"})
/*    */   public void checkPwd(String origPwd, HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 59 */     CmsUser user = CmsUtils.getUser(request);
/* 60 */     boolean pass = this.cmsUserMng.isPasswordValid(user.getId(), origPwd);
/* 61 */     ResponseUtils.renderJson(response, pass ? "true" : "false");
/*    */   }
/*    */ 
/*    */   private WebErrors validatePasswordSubmit(Integer id, String origPwd, String newPwd, String email, String realname, HttpServletRequest request)
/*    */   {
/* 67 */     WebErrors errors = WebErrors.create(request);
/* 68 */     if (errors.ifBlank(origPwd, "origPwd", 32)) {
/* 69 */       return errors;
/*    */     }
/* 71 */     if (errors.ifMaxLength(newPwd, "newPwd", 32)) {
/* 72 */       return errors;
/*    */     }
/* 74 */     if (errors.ifMaxLength(email, "email", 100)) {
/* 75 */       return errors;
/*    */     }
/* 77 */     if (errors.ifMaxLength(realname, "realname", 100)) {
/* 78 */       return errors;
/*    */     }
/* 80 */     if (!this.cmsUserMng.isPasswordValid(id, origPwd)) {
/* 81 */       errors.addErrorCode("member.origPwdInvalid");
/* 82 */       return errors;
/*    */     }
/* 84 */     return errors;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.PersonalAct
 * JD-Core Version:    0.6.0
 */