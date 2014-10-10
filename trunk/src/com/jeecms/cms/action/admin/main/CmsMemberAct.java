/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.util.List;
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
/*     */ public class CmsMemberAct
/*     */ {
/*  33 */   private static final Logger log = LoggerFactory.getLogger(CmsMemberAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsGroupMng cmsGroupMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng manager;
/*     */ 
/*  39 */   @RequestMapping({"/member/v_list.do"})
/*     */   public String list(String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(queryUsername, queryEmail, 
/*  40 */       null, queryGroupId, queryDisabled, Boolean.valueOf(false), null, SimplePage.cpn(pageNo), 
/*  41 */       CookieUtils.getPageSize(request));
/*  42 */     model.addAttribute("pagination", pagination);
/*     */ 
/*  44 */     model.addAttribute("queryUsername", queryUsername);
/*  45 */     model.addAttribute("queryEmail", queryEmail);
/*  46 */     model.addAttribute("queryGroupId", queryGroupId);
/*  47 */     model.addAttribute("queryDisabled", queryDisabled);
/*     */ 
/*  49 */     return "member/list"; }
/*     */ 
/*     */   @RequestMapping({"/member/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  54 */     List groupList = this.cmsGroupMng.getList();
/*  55 */     model.addAttribute("groupList", groupList);
/*  56 */     return "member/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/v_edit.do"})
/*     */   public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled, HttpServletRequest request, ModelMap model) {
/*  62 */     String queryUsername = RequestUtils.getQueryParam(request, 
/*  63 */       "queryUsername");
/*  64 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/*  65 */     WebErrors errors = validateEdit(id, request);
/*  66 */     if (errors.hasErrors()) {
/*  67 */       return errors.showErrorPage(model);
/*     */     }
/*  69 */     List groupList = this.cmsGroupMng.getList();
/*  70 */     model.addAttribute("queryUsername", queryUsername);
/*  71 */     model.addAttribute("queryEmail", queryEmail);
/*  72 */     model.addAttribute("queryGroupId", queryGroupId);
/*  73 */     model.addAttribute("queryDisabled", queryDisabled);
/*  74 */     model.addAttribute("groupList", groupList);
/*  75 */     model.addAttribute("cmsMember", this.manager.findById(id));
/*  76 */     return "member/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_save.do"})
/*     */   public String save(CmsUser bean, CmsUserExt ext, String username, String email, String password, Integer groupId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  83 */     WebErrors errors = validateSave(bean, request);
/*  84 */     if (errors.hasErrors()) {
/*  85 */       return errors.showErrorPage(model);
/*     */     }
/*  87 */     String ip = RequestUtils.getIpAddr(request);
/*  88 */     bean = this.manager.registerMember(username, email, password, ip, groupId, 
/*  89 */       ext);
/*  90 */     log.info("save CmsMember id={}", bean.getId());
/*  91 */     this.cmsLogMng.operating(request, "cmsMember.log.save", "id=" + bean.getId() + 
/*  92 */       ";username=" + bean.getUsername());
/*  93 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_update.do"})
/*     */   public String update(Integer id, String email, String password, Boolean disabled, CmsUserExt ext, Integer groupId, String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 102 */     WebErrors errors = validateUpdate(id, request);
/* 103 */     if (errors.hasErrors()) {
/* 104 */       return errors.showErrorPage(model);
/*     */     }
/* 106 */     CmsUser bean = this.manager.updateMember(id, email, password, disabled, ext, 
/* 107 */       groupId);
/* 108 */     log.info("update CmsMember id={}.", bean.getId());
/* 109 */     this.cmsLogMng.operating(request, "cmsMember.log.update", "id=" + 
/* 110 */       bean.getId() + ";username=" + bean.getUsername());
/*     */ 
/* 112 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 113 */       pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 120 */     String queryUsername = RequestUtils.getQueryParam(request, 
/* 121 */       "queryUsername");
/* 122 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/* 123 */     WebErrors errors = validateDelete(ids, request);
/* 124 */     if (errors.hasErrors()) {
/* 125 */       return errors.showErrorPage(model);
/*     */     }
/* 127 */     CmsUser[] beans = this.manager.deleteByIds(ids);
/* 128 */     for (CmsUser bean : beans) {
/* 129 */       log.info("delete CmsMember id={}", bean.getId());
/* 130 */       this.cmsLogMng.operating(request, "cmsMember.log.delete", "id=" + 
/* 131 */         bean.getId() + ";username=" + bean.getUsername());
/*     */     }
/* 133 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 134 */       pageNo, request, model);
/*     */   }
/*     */   @RequestMapping({"/member/v_check_username.do"})
/*     */   public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
/* 139 */     String username = RequestUtils.getQueryParam(request, "username");
/*     */     String pass;
/* 141 */     if (StringUtils.isBlank(username))
/* 142 */       pass = "false";
/*     */     else {
/* 144 */       pass = this.manager.usernameNotExist(username) ? "true" : "false";
/*     */     }
/* 146 */     ResponseUtils.renderJson(response, pass);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsUser bean, HttpServletRequest request) {
/* 150 */     WebErrors errors = WebErrors.create(request);
/* 151 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 155 */     WebErrors errors = WebErrors.create(request);
/* 156 */     if (vldExist(id, errors)) {
/* 157 */       return errors;
/*     */     }
/* 159 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 163 */     WebErrors errors = WebErrors.create(request);
/* 164 */     if (vldExist(id, errors)) {
/* 165 */       return errors;
/*     */     }
/*     */ 
/* 168 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 172 */     WebErrors errors = WebErrors.create(request);
/* 173 */     errors.ifEmpty(ids, "ids");
/* 174 */     for (Integer id : ids) {
/* 175 */       vldExist(id, errors);
/*     */     }
/* 177 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 181 */     if (errors.ifNull(id, "id")) {
/* 182 */       return true;
/*     */     }
/* 184 */     CmsUser entity = this.manager.findById(id);
/*     */ 
/* 186 */     return errors.ifNotExist(entity, CmsUser.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsMemberAct
 * JD-Core Version:    0.6.0
 */