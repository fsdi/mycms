/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsRoleMng;
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
/*     */ public class CmsAdminLocalAct extends CmsAdminAbstract
/*     */ {
/*  35 */   private static final Logger log = LoggerFactory.getLogger(CmsAdminLocalAct.class);
/*     */ 
/*     */   @RequestMapping({"/admin_local/v_list.do"})
/*     */   public String list(String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  41 */     CmsSite site = CmsUtils.getSite(request);
/*  42 */     CmsUser currUser = CmsUtils.getUser(request);
/*  43 */     Pagination pagination = this.manager.getPage(queryUsername, queryEmail, 
/*  44 */       site.getId(), queryGroupId, queryDisabled, Boolean.valueOf(true), 
/*  45 */       currUser.getRank(), SimplePage.cpn(pageNo), 
/*  46 */       CookieUtils.getPageSize(request));
/*  47 */     model.addAttribute("pagination", pagination);
/*     */ 
/*  49 */     model.addAttribute("queryUsername", queryUsername);
/*  50 */     model.addAttribute("queryEmail", queryEmail);
/*  51 */     model.addAttribute("queryGroupId", queryGroupId);
/*  52 */     model.addAttribute("queryDisabled", queryDisabled);
/*     */ 
/*  54 */     return "admin/local/list";
/*     */   }
/*     */   @RequestMapping({"/admin_local/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  59 */     CmsSite site = CmsUtils.getSite(request);
/*  60 */     CmsUser currUser = CmsUtils.getUser(request);
/*  61 */     List groupList = this.cmsGroupMng.getList();
/*  62 */     List roleList = this.cmsRoleMng.getList();
/*  63 */     model.addAttribute("site", site);
/*  64 */     model.addAttribute("groupList", groupList);
/*  65 */     model.addAttribute("roleList", roleList);
/*  66 */     model.addAttribute("currRank", currUser.getRank());
/*  67 */     return "admin/local/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/v_edit.do"})
/*     */   public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled, HttpServletRequest request, ModelMap model) {
/*  73 */     CmsSite site = CmsUtils.getSite(request);
/*  74 */     String queryUsername = RequestUtils.getQueryParam(request, 
/*  75 */       "queryUsername");
/*  76 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/*  77 */     CmsUser currUser = CmsUtils.getUser(request);
/*  78 */     WebErrors errors = validateEdit(id, request);
/*  79 */     if (errors.hasErrors()) {
/*  80 */       return errors.showErrorPage(model);
/*     */     }
/*  82 */     CmsUser admin = this.manager.findById(id);
/*  83 */     CmsUserSite userSite = admin.getUserSite(site.getId());
/*     */ 
/*  85 */     List groupList = this.cmsGroupMng.getList();
/*  86 */     List roleList = this.cmsRoleMng.getList();
/*     */ 
/*  88 */     model.addAttribute("cmsAdmin", admin);
/*  89 */     model.addAttribute("site", site);
/*  90 */     model.addAttribute("userSite", userSite);
/*  91 */     model.addAttribute("roleIds", admin.getRoleIds());
/*  92 */     model.addAttribute("groupList", groupList);
/*  93 */     model.addAttribute("roleList", roleList);
/*  94 */     model.addAttribute("currRank", currUser.getRank());
/*     */ 
/*  96 */     model.addAttribute("queryUsername", queryUsername);
/*  97 */     model.addAttribute("queryEmail", queryEmail);
/*  98 */     model.addAttribute("queryGroupId", queryGroupId);
/*  99 */     model.addAttribute("queryDisabled", queryDisabled);
/* 100 */     return "admin/local/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/o_save.do"})
/*     */   public String save(CmsUser bean, CmsUserExt ext, String username, String email, String password, Boolean viewonlyAdmin, Boolean selfAdmin, Integer rank, Integer groupId, Integer[] roleIds, Integer[] channelIds, Byte[] steps, Boolean[] allChannels, HttpServletRequest request, ModelMap model)
/*     */   {
/* 109 */     CmsSite site = CmsUtils.getSite(request);
/* 110 */     WebErrors errors = validateSave(bean, request);
/* 111 */     if (errors.hasErrors()) {
/* 112 */       return errors.showErrorPage(model);
/*     */     }
/* 114 */     Integer[] siteIds = { site.getId() };
/* 115 */     String ip = RequestUtils.getIpAddr(request);
/* 116 */     bean = this.manager.saveAdmin(username, email, password, ip, viewonlyAdmin.booleanValue(), 
/* 117 */       selfAdmin.booleanValue(), rank.intValue(), groupId, roleIds, channelIds, siteIds, steps, 
/* 118 */       allChannels, ext);
/* 119 */     log.info("save CmsAdmin id={}", bean.getId());
/* 120 */     this.cmsLogMng.operating(request, "cmsUser.log.save", "id=" + bean.getId() + 
/* 121 */       ";username=" + bean.getUsername());
/* 122 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/o_update.do"})
/*     */   public String update(CmsUser bean, CmsUserExt ext, String password, Integer groupId, Integer[] roleIds, Integer[] channelIds, Byte step, Boolean allChannel, String queryUsername, String queryEmail, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 131 */     CmsSite site = CmsUtils.getSite(request);
/* 132 */     WebErrors errors = validateUpdate(bean.getId(), bean.getRank(), request);
/* 133 */     if (errors.hasErrors()) {
/* 134 */       return errors.showErrorPage(model);
/*     */     }
/* 136 */     bean = this.manager.updateAdmin(bean, ext, password, groupId, roleIds, 
/* 137 */       channelIds, site.getId(), step, allChannel);
/* 138 */     log.info("update CmsAdmin id={}.", bean.getId());
/* 139 */     this.cmsLogMng.operating(request, "cmsUser.log.update", "id=" + bean.getId() + 
/* 140 */       ";username=" + bean.getUsername());
/* 141 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 142 */       pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryGroupId, Boolean queryDisabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 149 */     String queryUsername = RequestUtils.getQueryParam(request, 
/* 150 */       "queryUsername");
/* 151 */     String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
/* 152 */     WebErrors errors = validateDelete(ids, request);
/* 153 */     if (errors.hasErrors()) {
/* 154 */       return errors.showErrorPage(model);
/*     */     }
/* 156 */     CmsUser[] beans = this.manager.deleteByIds(ids);
/* 157 */     for (CmsUser bean : beans) {
/* 158 */       log.info("delete CmsAdmin id={}", bean.getId());
/* 159 */       this.cmsLogMng.operating(request, "cmsUser.log.delete", "id=" + 
/* 160 */         bean.getId() + ";username=" + bean.getUsername());
/*     */     }
/* 162 */     return list(queryUsername, queryEmail, queryGroupId, queryDisabled, 
/* 163 */       pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/v_channels_add.do"})
/*     */   public String channelsAdd(Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 169 */     return channelsAddJson(siteId, request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin_local/v_channels_edit.do"})
/*     */   public String channelsEdit(Integer userId, Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 176 */     return channelsEditJson(userId, siteId, request, response, model);
/*     */   }
/*     */   @RequestMapping({"/admin_local/v_check_username.do"})
/*     */   public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
/* 181 */     checkUserJson(request, response);
/*     */   }
/*     */   @RequestMapping({"/admin_local/v_check_email.do"})
/*     */   public void checkEmail(String email, HttpServletResponse response) {
/* 186 */     checkEmailJson(email, response);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsUser bean, HttpServletRequest request) {
/* 190 */     WebErrors errors = WebErrors.create(request);
/* 191 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 195 */     WebErrors errors = WebErrors.create(request);
/* 196 */     if (vldExist(id, errors)) {
/* 197 */       return errors;
/*     */     }
/* 199 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, Integer rank, HttpServletRequest request) {
/* 203 */     WebErrors errors = WebErrors.create(request);
/* 204 */     if (vldExist(id, errors)) {
/* 205 */       return errors;
/*     */     }
/* 207 */     if (vldParams(id, rank, request, errors)) {
/* 208 */       return errors;
/*     */     }
/* 210 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 214 */     WebErrors errors = WebErrors.create(request);
/* 215 */     errors.ifEmpty(ids, "ids");
/* 216 */     for (Integer id : ids) {
/* 217 */       vldExist(id, errors);
/*     */     }
/* 219 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 223 */     if (errors.ifNull(id, "id")) {
/* 224 */       return true;
/*     */     }
/* 226 */     CmsUser entity = this.manager.findById(id);
/*     */ 
/* 228 */     return errors.ifNotExist(entity, CmsUser.class, id);
/*     */   }
/*     */ 
/*     */   private boolean vldParams(Integer id, Integer rank, HttpServletRequest request, WebErrors errors)
/*     */   {
/* 235 */     CmsUser user = CmsUtils.getUser(request);
/* 236 */     CmsUser entity = this.manager.findById(id);
/*     */ 
/* 238 */     if (rank.intValue() > user.getRank().intValue()) {
/* 239 */       errors.addErrorCode("error.noPermissionToRaiseRank", new Object[] { id });
/* 240 */       return true;
/*     */     }
/*     */ 
/* 243 */     if (entity.getRank().intValue() > user.getRank().intValue()) {
/* 244 */       errors.addErrorCode("error.noPermission", new Object[] { CmsUser.class, id });
/* 245 */       return true;
/*     */     }
/* 247 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsAdminLocalAct
 * JD-Core Version:    0.6.0
 */