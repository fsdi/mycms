/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng;
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
/*     */ public class CmsAdvertisingSpaceAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(CmsAdvertisingSpaceAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAdvertisingSpaceMng manager;
/*     */ 
/*  29 */   @RequestMapping({"/advertising_space/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  30 */     List list = this.manager.getList(site.getId());
/*  31 */     model.addAttribute("list", list);
/*  32 */     return "advertising_space/list"; }
/*     */ 
/*     */   @RequestMapping({"/advertising_space/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  37 */     return "advertising_space/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising_space/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  43 */     WebErrors errors = validateEdit(id, request);
/*  44 */     if (errors.hasErrors()) {
/*  45 */       return errors.showErrorPage(model);
/*     */     }
/*  47 */     model.addAttribute("cmsAdvertisingSpace", this.manager.findById(id));
/*  48 */     model.addAttribute("pageNo", pageNo);
/*  49 */     return "advertising_space/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising_space/o_save.do"})
/*     */   public String save(CmsAdvertisingSpace bean, HttpServletRequest request, ModelMap model) {
/*  55 */     WebErrors errors = validateSave(bean, request);
/*  56 */     if (errors.hasErrors()) {
/*  57 */       return errors.showErrorPage(model);
/*     */     }
/*  59 */     bean = this.manager.save(bean);
/*  60 */     log.info("save CmsAdvertisingSpace id={}", bean.getId());
/*  61 */     this.cmsLogMng.operating(request, "cmsAdvertisingSpace.log.save", "id=" + 
/*  62 */       bean.getId() + ";name=" + bean.getName());
/*  63 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising_space/o_update.do"})
/*     */   public String update(CmsAdvertisingSpace bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  69 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  70 */     if (errors.hasErrors()) {
/*  71 */       return errors.showErrorPage(model);
/*     */     }
/*  73 */     bean = this.manager.update(bean);
/*  74 */     log.info("update CmsAdvertisingSpace id={}.", bean.getId());
/*  75 */     this.cmsLogMng.operating(request, "cmsAdvertisingSpace.log.update", "id=" + 
/*  76 */       bean.getId() + ";name=" + bean.getName());
/*  77 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising_space/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  83 */     WebErrors errors = validateDelete(ids, request);
/*  84 */     if (errors.hasErrors()) {
/*  85 */       return errors.showErrorPage(model);
/*     */     }
/*  87 */     CmsAdvertisingSpace[] beans = this.manager.deleteByIds(ids);
/*  88 */     for (CmsAdvertisingSpace bean : beans) {
/*  89 */       log.info("delete CmsAdvertisingSpace id={}", bean.getId());
/*  90 */       this.cmsLogMng.operating(request, "cmsAdvertisingSpace.log.delete", 
/*  91 */         "id=" + bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  93 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsAdvertisingSpace bean, HttpServletRequest request)
/*     */   {
/*  98 */     WebErrors errors = WebErrors.create(request);
/*  99 */     CmsSite site = CmsUtils.getSite(request);
/* 100 */     bean.setSite(site);
/* 101 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 105 */     WebErrors errors = WebErrors.create(request);
/* 106 */     CmsSite site = CmsUtils.getSite(request);
/* 107 */     if (vldExist(id, site.getId(), errors)) {
/* 108 */       return errors;
/*     */     }
/* 110 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 114 */     WebErrors errors = WebErrors.create(request);
/* 115 */     CmsSite site = CmsUtils.getSite(request);
/* 116 */     if (vldExist(id, site.getId(), errors)) {
/* 117 */       return errors;
/*     */     }
/* 119 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 123 */     WebErrors errors = WebErrors.create(request);
/* 124 */     CmsSite site = CmsUtils.getSite(request);
/* 125 */     if (errors.ifEmpty(ids, "ids")) {
/* 126 */       return errors;
/*     */     }
/* 128 */     for (Integer id : ids) {
/* 129 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 131 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 135 */     if (errors.ifNull(id, "id")) {
/* 136 */       return true;
/*     */     }
/* 138 */     CmsAdvertisingSpace entity = this.manager.findById(id);
/* 139 */     if (errors.ifNotExist(entity, CmsAdvertisingSpace.class, id)) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (!entity.getSite().getId().equals(siteId)) {
/* 143 */       errors.notInSite(CmsAdvertisingSpace.class, id);
/* 144 */       return true;
/*     */     }
/* 146 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsAdvertisingSpaceAct
 * JD-Core Version:    0.6.0
 */