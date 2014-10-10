/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsLog;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
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
/*     */ public class CmsLogAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(CmsLogAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng manager;
/*     */ 
/*  30 */   @RequestMapping({"/log/v_list_operating.do"})
/*     */   public String listOperating(String queryUsername, String queryTitle, String queryIp, Integer pageNo, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  31 */     Pagination pagination = this.manager.getPage(Integer.valueOf(3), site.getId(), 
/*  32 */       queryUsername, queryTitle, queryIp, SimplePage.cpn(pageNo), 
/*  33 */       CookieUtils.getPageSize(request));
/*  34 */     model.addAttribute("pagination", pagination);
/*  35 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  36 */     model.addAttribute("queryUsername", queryUsername);
/*  37 */     model.addAttribute("queryTitle", queryTitle);
/*  38 */     model.addAttribute("queryIp", queryIp);
/*  39 */     return "log/list_operating";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/v_list_login_success.do"})
/*     */   public String listLoginSuccess(String queryUsername, String queryTitle, String queryIp, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  46 */     Pagination pagination = this.manager.getPage(Integer.valueOf(1), null, 
/*  47 */       queryUsername, queryTitle, queryIp, SimplePage.cpn(pageNo), 
/*  48 */       CookieUtils.getPageSize(request));
/*  49 */     model.addAttribute("pagination", pagination);
/*  50 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  51 */     model.addAttribute("queryUsername", queryUsername);
/*  52 */     model.addAttribute("queryTitle", queryTitle);
/*  53 */     model.addAttribute("queryIp", queryIp);
/*  54 */     return "log/list_login_success";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/v_list_login_failure.do"})
/*     */   public String listLoginFailure(String queryTitle, String queryIp, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  60 */     Pagination pagination = this.manager.getPage(Integer.valueOf(2), null, 
/*  61 */       null, queryTitle, queryIp, SimplePage.cpn(pageNo), 
/*  62 */       CookieUtils.getPageSize(request));
/*  63 */     model.addAttribute("pagination", pagination);
/*  64 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  65 */     model.addAttribute("queryTitle", queryTitle);
/*  66 */     model.addAttribute("queryIp", queryIp);
/*  67 */     return "log/list_login_failure";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_operating.do"})
/*     */   public String deleteOperating(String queryUsername, String queryTitle, String queryIp, Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  74 */     WebErrors errors = validateDelete(ids, request);
/*  75 */     if (errors.hasErrors()) {
/*  76 */       return errors.showErrorPage(model);
/*     */     }
/*  78 */     CmsLog[] beans = this.manager.deleteByIds(ids);
/*  79 */     for (CmsLog bean : beans) {
/*  80 */       log.info("delete CmsLog id={}", bean.getId());
/*     */     }
/*  82 */     return listOperating(queryUsername, queryTitle, queryIp, pageNo, 
/*  83 */       request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_operating_batch.do"})
/*     */   public String deleteOperatingBatch(Integer days, HttpServletRequest request, ModelMap model) {
/*  89 */     CmsSite site = CmsUtils.getSite(request);
/*  90 */     this.manager.deleteBatch(Integer.valueOf(3), site.getId(), days);
/*  91 */     model.addAttribute("message", "global.success");
/*  92 */     return listOperating(null, null, null, Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_login_success.do"})
/*     */   public String deleteLoginSuccess(String queryUsername, String queryTitle, String queryIp, Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  99 */     WebErrors errors = validateDelete(ids, request);
/* 100 */     if (errors.hasErrors()) {
/* 101 */       return errors.showErrorPage(model);
/*     */     }
/* 103 */     CmsLog[] beans = this.manager.deleteByIds(ids);
/* 104 */     for (CmsLog bean : beans) {
/* 105 */       log.info("delete CmsLog id={}", bean.getId());
/*     */     }
/* 107 */     return listLoginSuccess(queryUsername, queryTitle, queryIp, pageNo, 
/* 108 */       request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_login_success_batch.do"})
/*     */   public String deleteLoginSuccessBatch(Integer days, HttpServletRequest request, ModelMap model) {
/* 114 */     if (days == null) {
/* 115 */       days = Integer.valueOf(0);
/*     */     }
/* 117 */     this.manager.deleteBatch(Integer.valueOf(1), null, days);
/* 118 */     model.addAttribute("message", "global.success");
/* 119 */     return listLoginSuccess(null, null, null, Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_login_failure.do"})
/*     */   public String deleteLoginFailure(String queryTitle, String queryIp, Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 126 */     WebErrors errors = validateDelete(ids, request);
/* 127 */     if (errors.hasErrors()) {
/* 128 */       return errors.showErrorPage(model);
/*     */     }
/* 130 */     CmsLog[] beans = this.manager.deleteByIds(ids);
/* 131 */     for (CmsLog bean : beans) {
/* 132 */       log.info("delete CmsLog id={}", bean.getId());
/*     */     }
/* 134 */     return listLoginFailure(queryTitle, queryIp, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/log/o_delete_login_failure_batch.do"})
/*     */   public String deleteLoginFailureBatch(Integer days, HttpServletRequest request, ModelMap model) {
/* 140 */     if (days == null) {
/* 141 */       days = Integer.valueOf(0);
/*     */     }
/* 143 */     this.manager.deleteBatch(Integer.valueOf(2), null, days);
/* 144 */     model.addAttribute("message", "global.success");
/* 145 */     return listLoginFailure(null, null, Integer.valueOf(1), request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 149 */     WebErrors errors = WebErrors.create(request);
/* 150 */     CmsSite site = CmsUtils.getSite(request);
/* 151 */     if (errors.ifEmpty(ids, "ids")) {
/* 152 */       return errors;
/*     */     }
/* 154 */     for (Integer id : ids) {
/* 155 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 157 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 161 */     if (errors.ifNull(id, "id")) {
/* 162 */       return true;
/*     */     }
/* 164 */     CmsLog entity = this.manager.findById(id);
/*     */ 
/* 166 */     return errors.ifNotExist(entity, CmsLog.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsLogAct
 * JD-Core Version:    0.6.0
 */