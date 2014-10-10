/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsSensitivity;
/*     */ import com.jeecms.cms.manager.assist.CmsSensitivityMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
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
/*     */ public class CmsSensitivityAct
/*     */ {
/*  22 */   private static final Logger log = LoggerFactory.getLogger(CmsSensitivityAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSensitivityMng manager;
/*     */ 
/*  26 */   @RequestMapping({"/sensitivity/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getList(false);
/*  27 */     model.addAttribute("list", list);
/*  28 */     return "sensitivity/list"; }
/*     */ 
/*     */   @RequestMapping({"/sensitivity/o_save.do"})
/*     */   public String save(CmsSensitivity bean, HttpServletRequest request, ModelMap model)
/*     */   {
/*  34 */     WebErrors errors = validateSave(bean, request);
/*  35 */     if (errors.hasErrors()) {
/*  36 */       return errors.showErrorPage(model);
/*     */     }
/*  38 */     bean = this.manager.save(bean);
/*  39 */     model.addAttribute("message", "global.success");
/*  40 */     log.info("save CmsSensitivity id={}", bean.getId());
/*  41 */     this.cmsLogMng.operating(request, "cmsSensitivity.log.save", "id=" + 
/*  42 */       bean.getId() + ";name=" + bean.getSearch());
/*  43 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/sensitivity/o_update.do"})
/*     */   public String update(Integer[] id, String[] search, String[] replacement, HttpServletRequest request, ModelMap model) {
/*  49 */     WebErrors errors = validateUpdate(id, search, replacement, request);
/*  50 */     if (errors.hasErrors()) {
/*  51 */       return errors.showErrorPage(model);
/*     */     }
/*  53 */     this.manager.updateEnsitivity(id, search, replacement);
/*  54 */     model.addAttribute("message", "global.success");
/*  55 */     log.info("update CmsSensitivity.");
/*  56 */     this.cmsLogMng.operating(request, "cmsSensitivity.log.save", null);
/*  57 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/sensitivity/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  63 */     WebErrors errors = validateDelete(ids, request);
/*  64 */     if (errors.hasErrors()) {
/*  65 */       return errors.showErrorPage(model);
/*     */     }
/*  67 */     CmsSensitivity[] beans = this.manager.deleteByIds(ids);
/*  68 */     for (CmsSensitivity bean : beans) {
/*  69 */       log.info("delete CmsSensitivity id={}", bean.getId());
/*  70 */       this.cmsLogMng.operating(request, "cmsSensitivity.log.delete", "id=" + 
/*  71 */         bean.getId() + ";name=" + bean.getSearch());
/*     */     }
/*  73 */     model.addAttribute("message", "global.success");
/*  74 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsSensitivity bean, HttpServletRequest request)
/*     */   {
/*  79 */     WebErrors errors = WebErrors.create(request);
/*  80 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer[] ids, String[] searchs, String[] replacements, HttpServletRequest request)
/*     */   {
/*  85 */     WebErrors errors = WebErrors.create(request);
/*  86 */     if (errors.ifEmpty(ids, "id")) {
/*  87 */       return errors;
/*     */     }
/*  89 */     if (errors.ifEmpty(searchs, "name")) {
/*  90 */       return errors;
/*     */     }
/*  92 */     if (errors.ifEmpty(replacements, "url")) {
/*  93 */       return errors;
/*     */     }
/*  95 */     if ((ids.length != searchs.length) || (ids.length != replacements.length)) {
/*  96 */       errors.addErrorString("id, searchs, replacements length not equals");
/*     */ 
/*  98 */       return errors;
/*     */     }
/* 100 */     Integer[] arrayOfInteger;
/* 100 */     if ((arrayOfInteger = ids).length != 0) { Integer id = arrayOfInteger[0];
/* 101 */       vldExist(id, errors);
/* 102 */       return errors;
/*     */     }
/* 104 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 108 */     WebErrors errors = WebErrors.create(request);
/* 109 */     if (errors.ifEmpty(ids, "ids")) {
/* 110 */       return errors;
/*     */     }
/* 112 */     for (Integer id : ids) {
/* 113 */       vldExist(id, errors);
/*     */     }
/* 115 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 119 */     if (errors.ifNull(id, "id")) {
/* 120 */       return true;
/*     */     }
/* 122 */     CmsSensitivity entity = this.manager.findById(id);
/*     */ 
/* 124 */     return errors.ifNotExist(entity, CmsSensitivity.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsSensitivityAct
 * JD-Core Version:    0.6.0
 */