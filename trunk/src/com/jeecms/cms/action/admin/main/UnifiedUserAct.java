/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.core.entity.UnifiedUser;
/*     */ import com.jeecms.core.manager.UnifiedUserMng;
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
/*     */ public class UnifiedUserAct
/*     */ {
/*  26 */   private static final Logger log = LoggerFactory.getLogger(UnifiedUserAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private UnifiedUserMng manager;
/*     */ 
/*  31 */   @RequestMapping({"/unified_user/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), 
/*  32 */       CookieUtils.getPageSize(request));
/*  33 */     model.addAttribute("pagination", pagination);
/*  34 */     return "unified_user/list"; }
/*     */ 
/*     */   @RequestMapping({"/unified_user/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  39 */     return "unified_user/add";
/*     */   }
/*     */   @RequestMapping({"/unified_user/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  44 */     WebErrors errors = validateEdit(id, request);
/*  45 */     if (errors.hasErrors()) {
/*  46 */       return errors.showErrorPage(model);
/*     */     }
/*  48 */     model.addAttribute("user", this.manager.findById(id));
/*  49 */     return "unified_user/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/unified_user/o_save.do"})
/*     */   public String save(String username, String email, String password, HttpServletRequest request, ModelMap model) {
/*  55 */     WebErrors errors = validateSave(username, email, password, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return errors.showErrorPage(model);
/*     */     }
/*  59 */     UnifiedUser user = this.manager.save(username, email, password, 
/*  60 */       request.getRemoteAddr());
/*  61 */     log.info("save UnifiedUser id={}, username={}", user.getId(), 
/*  62 */       user.getUsername());
/*  63 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/unified_user/o_update.do"})
/*     */   public String update(Integer id, String email, String password, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  69 */     WebErrors errors = validateUpdate(id, email, password, request);
/*  70 */     if (errors.hasErrors()) {
/*  71 */       return errors.showErrorPage(model);
/*     */     }
/*  73 */     UnifiedUser user = this.manager.update(id, password, email);
/*  74 */     log.info("update UnifiedUser id={}.", user.getId());
/*  75 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/unified_user/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  81 */     WebErrors errors = validateDelete(ids, request);
/*  82 */     if (errors.hasErrors()) {
/*  83 */       return errors.showErrorPage(model);
/*     */     }
/*  85 */     UnifiedUser[] beans = this.manager.deleteByIds(ids);
/*  86 */     for (UnifiedUser bean : beans) {
/*  87 */       log.info("delete UnifiedUser id={}", bean.getId());
/*     */     }
/*  89 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/unified_user/v_check_username.do"})
/*     */   public String checkUsername(String username, HttpServletRequest request, HttpServletResponse response) {
/*  95 */     if ((StringUtils.isBlank(username)) || (this.manager.usernameExist(username)))
/*  96 */       ResponseUtils.renderJson(response, "false");
/*     */     else {
/*  98 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 100 */     return null;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/unified_user/v_check_email.do"})
/*     */   public String checkEmail(String email, HttpServletRequest request, HttpServletResponse response) {
/* 106 */     if ((StringUtils.isBlank(email)) || (this.manager.emailExist(email)))
/* 107 */       ResponseUtils.renderJson(response, "false");
/*     */     else {
/* 109 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(String username, String email, String password, HttpServletRequest request)
/*     */   {
/* 116 */     WebErrors errors = WebErrors.create(request);
/* 117 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 121 */     WebErrors errors = WebErrors.create(request);
/* 122 */     if (vldExist(id, errors)) {
/* 123 */       return errors;
/*     */     }
/* 125 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, String email, String password, HttpServletRequest request)
/*     */   {
/* 130 */     WebErrors errors = WebErrors.create(request);
/* 131 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 135 */     WebErrors errors = WebErrors.create(request);
/* 136 */     if (errors.ifEmpty(ids, "ids")) {
/* 137 */       return errors;
/*     */     }
/* 139 */     for (Integer id : ids) {
/* 140 */       if (vldExist(id, errors)) {
/* 141 */         return errors;
/*     */       }
/*     */     }
/*     */ 
/* 145 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 149 */     if (errors.ifNull(id, "id")) {
/* 150 */       return true;
/*     */     }
/* 152 */     UnifiedUser entity = this.manager.findById(id);
/*     */ 
/* 154 */     return errors.ifNotExist(entity, UnifiedUser.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.UnifiedUserAct
 * JD-Core Version:    0.6.0
 */