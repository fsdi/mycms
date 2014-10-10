/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsRoleMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsAdminGlobalAct extends CmsAdminAbstract
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(CmsAdminGlobalAct.class);
/*     */ 
/*     */   @RequestMapping({"/admin_global/v_list.do"})
/*     */   public String list(String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  40 */     CmsUser currUser = CmsUtils.getUser(request);
/*  41 */     Pagination pagination = this.manager.getPage(queryUsername, queryEmail, 
/*  42 */       null, queryGroupId, queryDisabled, Boolean.valueOf(true), currUser.getRank(), 
/*  43 */       SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  44 */     model.addAttribute("pagination", pagination);
/*     */ 
/*  46 */     model.addAttribute("queryUsername", queryUsername);
/*  47 */     model.addAttribute("queryEmail", queryEmail);
/*  48 */     model.addAttribute("queryGroupId", queryGroupId);
/*  49 */     model.addAttribute("queryDisabled", queryDisabled);
/*     */ 
/*  51 */     return "admin/global/list";
/*     */   }
/*     */   @RequestMapping({"/admin_global/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  56 */     CmsUser currUser = CmsUtils.getUser(request);
/*  57 */     List groupList = this.cmsGroupMng.getList();
/*  58 */     List siteList = this.cmsSiteMng.getList();
/*  59 */     List roleList = this.cmsRoleMng.getList();
/*  60 */     model.addAttribute("groupList", groupList);
/*  61 */     model.addAttribute("siteList", siteList);
/*  62 */     model.addAttribute("roleList", roleList);
/*  63 */     model.addAttribute("currRank", currUser.getRank());
/*  64 */     return "admin/global/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/v_edit.do"})
/*     */   public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled, HttpServletRequest request, ModelMap model) {
/*  70 */     String queryUsername = RequestUtils.getQueryParam(request, 
/*  71 */       "queryUsername");
/*  72 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/*  73 */     CmsUser currUser = CmsUtils.getUser(request);
/*  74 */     WebErrors errors = validateEdit(id, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*  78 */     CmsUser admin = this.manager.findById(id);
/*     */ 
/*  80 */     List groupList = this.cmsGroupMng.getList();
/*  81 */     List siteList = this.cmsSiteMng.getList();
/*  82 */     List roleList = this.cmsRoleMng.getList();
/*     */ 
/*  84 */     model.addAttribute("cmsAdmin", admin);
/*  85 */     model.addAttribute("siteIds", admin.getSiteIds());
/*  86 */     model.addAttribute("roleIds", admin.getRoleIds());
/*  87 */     model.addAttribute("groupList", groupList);
/*  88 */     model.addAttribute("siteList", siteList);
/*  89 */     model.addAttribute("roleList", roleList);
/*  90 */     model.addAttribute("currRank", currUser.getRank());
/*     */ 
/*  92 */     model.addAttribute("queryUsername", queryUsername);
/*  93 */     model.addAttribute("queryEmail", queryEmail);
/*  94 */     model.addAttribute("queryGroupId", queryGroupId);
/*  95 */     model.addAttribute("queryDisabled", queryDisabled);
/*  96 */     return "admin/global/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/o_save.do"})
/*     */   public String save(CmsUser bean, CmsUserExt ext, String username, String email, String password, Boolean viewonlyAdmin, Boolean selfAdmin, Integer rank, Integer groupId, Integer[] roleIds, Integer[] channelIds, Integer[] siteIds, Byte[] steps, Boolean[] allChannels, HttpServletRequest request, ModelMap model)
/*     */   {
/* 106 */     WebErrors errors = validateSave(bean, siteIds, steps, allChannels, 
/* 107 */       request);
/* 108 */     if (errors.hasErrors()) {
/* 109 */       return errors.showErrorPage(model);
/*     */     }
/* 111 */     String ip = RequestUtils.getIpAddr(request);
/* 112 */     bean = this.manager.saveAdmin(username, email, password, ip, viewonlyAdmin.booleanValue(), 
/* 113 */       selfAdmin.booleanValue(), rank.intValue(), groupId, roleIds, channelIds, siteIds, steps, 
/* 114 */       allChannels, ext);
/* 115 */     log.info("save CmsAdmin id={}", bean.getId());
/* 116 */     this.cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + 
/* 117 */       ";username=" + bean.getUsername());
/* 118 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/o_update.do"})
/*     */   public String update(CmsUser bean, CmsUserExt ext, String password, Integer groupId, Integer[] roleIds, Integer[] channelIds, Integer[] siteIds, Byte[] steps, Boolean[] allChannels, String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 128 */     WebErrors errors = validateUpdate(bean.getId(), bean.getRank(), request);
/* 129 */     if (errors.hasErrors()) {
/* 130 */       return errors.showErrorPage(model);
/*     */     }
/* 132 */     bean = this.manager.updateAdmin(bean, ext, password, groupId, roleIds, 
/* 133 */       channelIds, siteIds, steps, allChannels);
/* 134 */     log.info("update CmsAdmin id={}.", bean.getId());
/* 135 */     this.cmsLogMng.operating(request, "cmsUser.log.update", "id=" + bean.getId() + 
/* 136 */       ";username=" + bean.getUsername());
/* 137 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 138 */       pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 145 */     String queryUsername = RequestUtils.getQueryParam(request, 
/* 146 */       "queryUsername");
/* 147 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/* 148 */     WebErrors errors = validateDelete(ids, request);
/* 149 */     if (errors.hasErrors()) {
/* 150 */       return errors.showErrorPage(model);
/*     */     }
/* 152 */     CmsUser[] beans = this.manager.deleteByIds(ids);
/* 153 */     for (CmsUser bean : beans) {
/* 154 */       log.info("delete CmsAdmin id={}", bean.getId());
/* 155 */       this.cmsLogMng.operating(request, "cmsUser.log.delete", "id=" + 
/* 156 */         bean.getId() + ";username=" + bean.getUsername());
/*     */     }
/* 158 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 159 */       pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/v_channels_add.do"})
/*     */   public String channelsAdd(Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 165 */     return channelsAddJson(siteId, request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_global/v_channels_edit.do"})
/*     */   public String channelsEdit(Integer userId, Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 172 */     return channelsEditJson(userId, siteId, request, response, model);
/*     */   }
/*     */   @RequestMapping({"/admin_global/v_check_username.do"})
/*     */   public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
/* 177 */     checkUserJson(request, response);
/*     */   }
/*     */   @RequestMapping({"/admin_global/v_check_email.do"})
/*     */   public void checkEmail(String email, HttpServletResponse response) {
/* 182 */     checkEmailJson(email, response);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsUser bean, Integer[] siteIds, Byte[] steps, Boolean[] allChannels, HttpServletRequest request)
/*     */   {
/* 187 */     WebErrors errors = WebErrors.create(request);
/* 188 */     if (siteIds != null) {
/* 189 */       if (steps == null) {
/* 190 */         errors.addError("steps cannot be null");
/* 191 */         return errors;
/*     */       }
/* 193 */       if (allChannels == null) {
/* 194 */         errors.addError("allChannels cannot be null");
/* 195 */         return errors;
/*     */       }
/* 197 */       if ((siteIds.length != steps.length) || 
/* 198 */         (siteIds.length != allChannels.length)) {
/* 199 */         errors.addError("siteIds length, steps length, allChannels length not equals");
/*     */ 
/* 201 */         return errors;
/*     */       }
/*     */     }
/* 204 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 208 */     WebErrors errors = WebErrors.create(request);
/* 209 */     if (vldExist(id, errors)) {
/* 210 */       return errors;
/*     */     }
/*     */ 
/* 213 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, Integer rank, HttpServletRequest request) {
/* 217 */     WebErrors errors = WebErrors.create(request);
/* 218 */     if (vldExist(id, errors)) {
/* 219 */       return errors;
/*     */     }
/*     */ 
/* 222 */     if (vldParams(id, rank, request, errors)) {
/* 223 */       return errors;
/*     */     }
/* 225 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 229 */     WebErrors errors = WebErrors.create(request);
/* 230 */     errors.ifEmpty(ids, "ids");
/* 231 */     for (Integer id : ids) {
/* 232 */       vldExist(id, errors);
/*     */     }
/* 234 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 238 */     if (errors.ifNull(id, "id")) {
/* 239 */       return true;
/*     */     }
/* 241 */     CmsUser entity = this.manager.findById(id);
/*     */ 
/* 243 */     return errors.ifNotExist(entity, CmsUser.class, id);
/*     */   }
/*     */ 
/*     */   private boolean vldParams(Integer id, Integer rank, HttpServletRequest request, WebErrors errors)
/*     */   {
/* 250 */     CmsUser user = CmsUtils.getUser(request);
/* 251 */     CmsUser entity = this.manager.findById(id);
/*     */ 
/* 253 */     if (rank.intValue() > user.getRank().intValue()) {
/* 254 */       errors.addErrorCode("error.noPermissionToRaiseRank", new Object[] { id });
/* 255 */       return true;
/*     */     }
/*     */ 
/* 258 */     if (entity.getRank().intValue() > user.getRank().intValue()) {
/* 259 */       errors.addErrorCode("error.noPermission", new Object[] { CmsUser.class, id });
/* 260 */       return true;
/*     */     }
/* 262 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsAdminGlobalAct
 * JD-Core Version:    0.6.0
 */