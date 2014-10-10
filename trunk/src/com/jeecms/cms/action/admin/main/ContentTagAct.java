/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.ContentTag;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.ContentTagMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
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
/*     */ public class ContentTagAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(ContentTagAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentTagMng manager;
/*     */ 
/*  33 */   @RequestMapping({"/tag/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { String queryName = RequestUtils.getQueryParam(request, "queryName");
/*  34 */     Pagination pagination = this.manager.getPage(queryName, SimplePage.cpn(pageNo), 
/*  35 */       CookieUtils.getPageSize(request));
/*  36 */     model.addAttribute("pagination", pagination);
/*  37 */     if (!StringUtils.isBlank(queryName)) {
/*  38 */       model.addAttribute("queryName", queryName);
/*     */     }
/*  40 */     return "tag/list"; }
/*     */ 
/*     */   @RequestMapping({"/tag/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  45 */     return "tag/add";
/*     */   }
/*     */   @RequestMapping({"/tag/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  50 */     WebErrors errors = validateEdit(id, request);
/*  51 */     if (errors.hasErrors()) {
/*  52 */       return errors.showErrorPage(model);
/*     */     }
/*  54 */     model.addAttribute("contentTag", this.manager.findById(id));
/*  55 */     String queryName = RequestUtils.getQueryParam(request, "queryName");
/*  56 */     if (!StringUtils.isBlank(queryName)) {
/*  57 */       model.addAttribute("queryName", queryName);
/*     */     }
/*  59 */     return "tag/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/tag/o_save.do"})
/*     */   public String save(ContentTag bean, HttpServletRequest request, ModelMap model) {
/*  65 */     WebErrors errors = validateSave(bean, request);
/*  66 */     if (errors.hasErrors()) {
/*  67 */       return errors.showErrorPage(model);
/*     */     }
/*  69 */     bean = this.manager.save(bean);
/*  70 */     log.info("save ContentTag id={}", bean.getId());
/*  71 */     this.cmsLogMng.operating(request, "contentTag.log.save", "id=" + 
/*  72 */       bean.getId() + ";name=" + bean.getName());
/*  73 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/tag/o_update.do"})
/*     */   public String update(ContentTag bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  79 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     bean = this.manager.update(bean);
/*  84 */     log.info("update ContentTag id={}.", bean.getId());
/*  85 */     this.cmsLogMng.operating(request, "contentTag.log.update", "id=" + 
/*  86 */       bean.getId() + ";name=" + bean.getName());
/*  87 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/tag/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  93 */     WebErrors errors = validateDelete(ids, request);
/*  94 */     if (errors.hasErrors()) {
/*  95 */       return errors.showErrorPage(model);
/*     */     }
/*  97 */     ContentTag[] beans = this.manager.deleteByIds(ids);
/*  98 */     for (ContentTag bean : beans) {
/*  99 */       log.info("delete ContentTag id={}", bean.getId());
/* 100 */       this.cmsLogMng.operating(request, "contentTag.log.delete", "id=" + 
/* 101 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 103 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ContentTag bean, HttpServletRequest request) {
/* 107 */     WebErrors errors = WebErrors.create(request);
/* 108 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/* 113 */     CmsSite site = CmsUtils.getSite(request);
/* 114 */     if (vldExist(id, site.getId(), errors)) {
/* 115 */       return errors;
/*     */     }
/* 117 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 121 */     WebErrors errors = WebErrors.create(request);
/* 122 */     CmsSite site = CmsUtils.getSite(request);
/* 123 */     if (vldExist(id, site.getId(), errors)) {
/* 124 */       return errors;
/*     */     }
/* 126 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 130 */     WebErrors errors = WebErrors.create(request);
/* 131 */     CmsSite site = CmsUtils.getSite(request);
/* 132 */     errors.ifEmpty(ids, "ids");
/* 133 */     for (Integer id : ids) {
/* 134 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 136 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 140 */     if (errors.ifNull(id, "id")) {
/* 141 */       return true;
/*     */     }
/* 143 */     ContentTag entity = this.manager.findById(id);
/*     */ 
/* 145 */     return errors.ifNotExist(entity, ContentTag.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.ContentTagAct
 * JD-Core Version:    0.6.0
 */