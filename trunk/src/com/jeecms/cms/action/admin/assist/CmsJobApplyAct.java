/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserResume;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.assist.CmsJobApplyMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsJobApplyAct
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(CmsJobApplyAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsJobApplyMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng userMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*  33 */   @RequestMapping({"/jobapply/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(null, null, CmsUtils.getSiteId(request), true, SimplePage.cpn(pageNo), 
/*  34 */       CookieUtils.getPageSize(request));
/*  35 */     model.addAttribute("pagination", pagination);
/*  36 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  37 */     return "jobapply/list"; }
/*     */ 
/*     */   @RequestMapping({"/jobapply/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  43 */     WebErrors errors = validateDelete(ids, request);
/*  44 */     if (errors.hasErrors()) {
/*  45 */       return errors.showErrorPage(model);
/*     */     }
/*  47 */     CmsJobApply[] beans = this.manager.deleteByIds(ids);
/*  48 */     for (CmsJobApply bean : beans) {
/*  49 */       log.info("delete CmsJobApply id={}", bean.getId());
/*     */     }
/*  51 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/jobapply/v_view.do"})
/*     */   public String viewResume(Integer userId, Integer contentId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  58 */     WebErrors errors = validateViewResume(userId, contentId, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*     */     }
/*  62 */     CmsUserResume resume = this.userMng.findById(userId).getUserResume();
/*  63 */     model.addAttribute("resume", resume);
/*  64 */     return "jobapply/viewresume";
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/*  68 */     WebErrors errors = WebErrors.create(request);
/*  69 */     CmsSite site = CmsUtils.getSite(request);
/*  70 */     if (errors.ifEmpty(ids, "ids")) {
/*  71 */       return errors;
/*     */     }
/*  73 */     for (Integer id : ids) {
/*  74 */       vldExist(id, site.getId(), errors);
/*     */     }
/*  76 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateViewResume(Integer userId, Integer contentId, HttpServletRequest request) {
/*  80 */     WebErrors errors = WebErrors.create(request);
/*  81 */     CmsSite site = CmsUtils.getSite(request);
/*  82 */     if (errors.ifNull(userId, "user")) {
/*  83 */       return errors;
/*     */     }
/*  85 */     if (errors.ifNull(contentId, "contentId")) {
/*  86 */       return errors;
/*     */     }
/*  88 */     CmsUser u = this.userMng.findById(userId);
/*  89 */     if (u == null) {
/*  90 */       errors.addErrorCode("cmsJobApply.nouser");
/*  91 */       return errors;
/*     */     }
/*  93 */     Content c = this.contentMng.findById(contentId);
/*  94 */     if (c == null) {
/*  95 */       errors.addErrorCode("cmsJobApply.nocontent");
/*  96 */       return errors;
/*     */     }
/*  98 */     if (!c.getSite().equals(site)) {
/*  99 */       errors.addErrorCode("cmsJobApply.notinsite", new Object[] { u.getUsername() });
/* 100 */       return errors;
/*     */     }
/* 102 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 106 */     if (errors.ifNull(id, "id")) {
/* 107 */       return true;
/*     */     }
/* 109 */     CmsJobApply entity = this.manager.findById(id);
/*     */ 
/* 111 */     return errors.ifNotExist(entity, CmsJobApply.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsJobApplyAct
 * JD-Core Version:    0.6.0
 */