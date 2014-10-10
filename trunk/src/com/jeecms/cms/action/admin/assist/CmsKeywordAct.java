/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsKeyword;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsKeywordMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsKeywordAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(CmsKeywordAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsKeywordMng manager;
/*     */ 
/*  28 */   @RequestMapping({"/keyword/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  29 */     List list = this.manager.getListBySiteId(site.getId(), false, 
/*  30 */       false);
/*  31 */     model.addAttribute("list", list);
/*  32 */     return "keyword/list"; }
/*     */ 
/*     */   @RequestMapping({"/keyword/o_save.do"})
/*     */   public String save(CmsKeyword bean, HttpServletRequest request, ModelMap model)
/*     */   {
/*  38 */     WebErrors errors = validateSave(bean, request);
/*  39 */     if (errors.hasErrors()) {
/*  40 */       return errors.showErrorPage(model);
/*     */     }
/*  42 */     bean = this.manager.save(bean);
/*  43 */     model.addAttribute("message", "global.success");
/*  44 */     log.info("save CmsKeyword id={}", bean.getId());
/*  45 */     this.cmsLogMng.operating(request, "cmsKeyword.log.save", "id=" + 
/*  46 */       bean.getId() + ";name=" + bean.getName());
/*  47 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/keyword/o_update.do"})
/*     */   public String update(Integer[] id, String[] name, String[] url, Boolean[] disabled, HttpServletRequest request, ModelMap model) {
/*  53 */     WebErrors errors = validateUpdate(id, name, url, disabled, request);
/*  54 */     if (errors.hasErrors()) {
/*  55 */       return errors.showErrorPage(model);
/*     */     }
/*  57 */     if ((id != null) && (id.length > 0)) {
/*  58 */       this.manager.updateKeywords(id, name, url, disabled);
/*     */     }
/*  60 */     log.info("update CmsKeyword");
/*  61 */     model.addAttribute("message", "global.success");
/*  62 */     this.cmsLogMng.operating(request, "cmsKeyword.log.update", null);
/*  63 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/keyword/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  69 */     WebErrors errors = validateDelete(ids, request);
/*  70 */     if (errors.hasErrors()) {
/*  71 */       return errors.showErrorPage(model);
/*     */     }
/*  73 */     CmsKeyword[] beans = this.manager.deleteByIds(ids);
/*  74 */     for (CmsKeyword bean : beans) {
/*  75 */       log.info("delete CmsKeyword id={}", bean.getId());
/*  76 */       this.cmsLogMng.operating(request, "cmsKeyword.log.delete", "id=" + 
/*  77 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  79 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsKeyword bean, HttpServletRequest request) {
/*  83 */     WebErrors errors = WebErrors.create(request);
/*  84 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer[] ids, String[] names, String[] urls, Boolean[] disalbeds, HttpServletRequest request)
/*     */   {
/*  89 */     WebErrors errors = WebErrors.create(request);
/*  90 */     CmsSite site = CmsUtils.getSite(request);
/*  91 */     if (errors.ifEmpty(ids, "id")) {
/*  92 */       return errors;
/*     */     }
/*  94 */     if (errors.ifEmpty(names, "name")) {
/*  95 */       return errors;
/*     */     }
/*  97 */     if (errors.ifEmpty(urls, "url")) {
/*  98 */       return errors;
/*     */     }
/* 100 */     if ((ids.length != names.length) || (ids.length != urls.length)) {
/* 101 */       errors.addErrorString("id, name, url length not equals");
/* 102 */       return errors;
/*     */     }
/* 104 */     Integer[] arrayOfInteger;
/* 104 */     if ((arrayOfInteger = ids).length != 0) { Integer id = arrayOfInteger[0];
/* 105 */       vldExist(id, site.getId(), errors);
/* 106 */       return errors;
/*     */     }
/* 108 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 112 */     WebErrors errors = WebErrors.create(request);
/* 113 */     CmsSite site = CmsUtils.getSite(request);
/* 114 */     if (errors.ifEmpty(ids, "ids")) {
/* 115 */       return errors;
/*     */     }
/* 117 */     for (Integer id : ids) {
/* 118 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 120 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 124 */     if (errors.ifNull(id, "id")) {
/* 125 */       return true;
/*     */     }
/* 127 */     CmsKeyword entity = this.manager.findById(id);
/*     */ 
/* 129 */     return errors.ifNotExist(entity, CmsKeyword.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsKeywordAct
 * JD-Core Version:    0.6.0
 */