/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
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
/*     */ public class CmsModelAct
/*     */ {
/*  22 */   private static final Logger log = LoggerFactory.getLogger(CmsModelAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelMng manager;
/*     */ 
/*  26 */   @RequestMapping({"/model/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getList(true, null);
/*  27 */     model.addAttribute("list", list);
/*  28 */     return "model/list"; }
/*     */ 
/*     */   @RequestMapping({"/model/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  33 */     return "model/add";
/*     */   }
/*     */   @RequestMapping({"/model/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  38 */     WebErrors errors = validateEdit(id, request);
/*  39 */     if (errors.hasErrors()) {
/*  40 */       return errors.showErrorPage(model);
/*     */     }
/*  42 */     model.addAttribute("cmsModel", this.manager.findById(id));
/*  43 */     return "model/edit";
/*     */   }
/*     */   @RequestMapping({"/model/o_save.do"})
/*     */   public String save(CmsModel bean, HttpServletRequest request, ModelMap model) {
/*  48 */     WebErrors errors = validateSave(bean, request);
/*  49 */     if (errors.hasErrors()) {
/*  50 */       return errors.showErrorPage(model);
/*     */     }
/*  52 */     bean = this.manager.save(bean);
/*  53 */     log.info("save CmsModel id={}", bean.getId());
/*  54 */     this.cmsLogMng.operating(request, "cmsModel.log.save", "id=" + bean.getId() + 
/*  55 */       ";name=" + bean.getName());
/*  56 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/model/o_update.do"})
/*     */   public String update(CmsModel bean, HttpServletRequest request, ModelMap model) {
/*  62 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  63 */     if (errors.hasErrors()) {
/*  64 */       return errors.showErrorPage(model);
/*     */     }
/*  66 */     bean = this.manager.update(bean);
/*  67 */     log.info("update CmsModel id={}.", bean.getId());
/*  68 */     this.cmsLogMng.operating(request, "cmsModel.log.update", "id=" + 
/*  69 */       bean.getId() + ";name=" + bean.getName());
/*  70 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/model/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  76 */     WebErrors errors = validateDelete(ids, request);
/*  77 */     if (errors.hasErrors()) {
/*  78 */       return errors.showErrorPage(model);
/*     */     }
/*  80 */     CmsModel[] beans = this.manager.deleteByIds(ids);
/*  81 */     for (CmsModel bean : beans) {
/*  82 */       log.info("delete CmsModel id={}", bean.getId());
/*  83 */       this.cmsLogMng.operating(request, "cmsModel.log.delete", "id=" + 
/*  84 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  86 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/model/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Boolean[] disabled, Integer defId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  93 */     WebErrors errors = validatePriority(wids, priority, disabled, defId, 
/*  94 */       request);
/*  95 */     if (errors.hasErrors()) {
/*  96 */       return errors.showErrorPage(model);
/*     */     }
/*  98 */     this.manager.updatePriority(wids, priority, disabled, defId);
/*  99 */     model.addAttribute("message", "global.success");
/* 100 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsModel bean, HttpServletRequest request) {
/* 104 */     WebErrors errors = WebErrors.create(request);
/* 105 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 109 */     WebErrors errors = WebErrors.create(request);
/* 110 */     if (vldExist(id, errors)) {
/* 111 */       return errors;
/*     */     }
/* 113 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 117 */     WebErrors errors = WebErrors.create(request);
/* 118 */     if (vldExist(id, errors)) {
/* 119 */       return errors;
/*     */     }
/* 121 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 125 */     WebErrors errors = WebErrors.create(request);
/* 126 */     errors.ifEmpty(ids, "ids");
/* 127 */     for (Integer id : ids) {
/* 128 */       vldExist(id, errors);
/*     */     }
/* 130 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Integer[] wids, Integer[] priority, Boolean[] disabled, Integer defId, HttpServletRequest request)
/*     */   {
/* 135 */     WebErrors errors = WebErrors.create(request);
/* 136 */     if (errors.ifEmpty(wids, "wids")) {
/* 137 */       return errors;
/*     */     }
/* 139 */     if (errors.ifEmpty(priority, "priority")) {
/* 140 */       return errors;
/*     */     }
/* 142 */     if ((wids.length != priority.length) || (wids.length != disabled.length)) {
/* 143 */       String s = "wids length not equals priority length or disabled length";
/* 144 */       errors.addErrorString(s);
/* 145 */       return errors;
/*     */     }
/* 147 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 148 */       if (vldExist(wids[i], errors)) {
/* 149 */         return errors;
/*     */       }
/* 151 */       if (priority[i] == null) {
/* 152 */         priority[i] = Integer.valueOf(0);
/*     */       }
/* 154 */       if (disabled[i] == null) {
/* 155 */         disabled[i] = Boolean.valueOf(false);
/*     */       }
/*     */     }
/* 158 */     if (vldExist(defId, errors)) {
/* 159 */       return errors;
/*     */     }
/* 161 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 165 */     if (errors.ifNull(id, "id")) {
/* 166 */       return true;
/*     */     }
/* 168 */     CmsModel entity = this.manager.findById(id);
/*     */ 
/* 170 */     return errors.ifNotExist(entity, CmsModel.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsModelAct
 * JD-Core Version:    0.6.0
 */