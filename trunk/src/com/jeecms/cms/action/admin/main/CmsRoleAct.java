/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsRole;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsRoleMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ public class CmsRoleAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(CmsRoleAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsRoleMng manager;
/*     */ 
/*  28 */   @RequestMapping({"/role/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getList();
/*  29 */     model.addAttribute("list", list);
/*  30 */     return "role/list"; }
/*     */ 
/*     */   @RequestMapping({"/role/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  35 */     return "role/add";
/*     */   }
/*     */   @RequestMapping({"/role/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  40 */     WebErrors errors = validateEdit(id, request);
/*  41 */     if (errors.hasErrors()) {
/*  42 */       return errors.showErrorPage(model);
/*     */     }
/*  44 */     model.addAttribute("cmsRole", this.manager.findById(id));
/*  45 */     return "role/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/role/o_save.do"})
/*     */   public String save(CmsRole bean, String[] perms, HttpServletRequest request, ModelMap model) {
/*  51 */     WebErrors errors = validateSave(bean, request);
/*  52 */     if (errors.hasErrors()) {
/*  53 */       return errors.showErrorPage(model);
/*     */     }
/*  55 */     bean = this.manager.save(bean, splitPerms(perms));
/*  56 */     log.info("save CmsRole id={}", bean.getId());
/*  57 */     this.cmsLogMng.operating(request, "cmsRole.log.save", "id=" + bean.getId() + 
/*  58 */       ";name=" + bean.getName());
/*  59 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/role/o_update.do"})
/*     */   public String update(CmsRole bean, String[] perms, HttpServletRequest request, ModelMap model) {
/*  65 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  66 */     if (errors.hasErrors()) {
/*  67 */       return errors.showErrorPage(model);
/*     */     }
/*  69 */     bean = this.manager.update(bean, splitPerms(perms));
/*  70 */     log.info("update CmsRole id={}.", bean.getId());
/*  71 */     this.cmsLogMng.operating(request, "cmsRole.log.update", "id=" + bean.getId() + 
/*  72 */       ";name=" + bean.getName());
/*  73 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/role/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  79 */     WebErrors errors = validateDelete(ids, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     CmsRole[] beans = this.manager.deleteByIds(ids);
/*  84 */     for (CmsRole bean : beans) {
/*  85 */       log.info("delete CmsRole id={}", bean.getId());
/*  86 */       this.cmsLogMng.operating(request, "cmsRole.log.delete", "id=" + 
/*  87 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  89 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsRole bean, HttpServletRequest request) {
/*  93 */     WebErrors errors = WebErrors.create(request);
/*  94 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/*  98 */     WebErrors errors = WebErrors.create(request);
/*  99 */     if (vldExist(id, errors)) {
/* 100 */       return errors;
/*     */     }
/* 102 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 106 */     WebErrors errors = WebErrors.create(request);
/* 107 */     if (vldExist(id, errors)) {
/* 108 */       return errors;
/*     */     }
/* 110 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 114 */     WebErrors errors = WebErrors.create(request);
/* 115 */     if (errors.ifEmpty(ids, "ids")) {
/* 116 */       return errors;
/*     */     }
/* 118 */     for (Integer id : ids) {
/* 119 */       vldExist(id, errors);
/*     */     }
/* 121 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 125 */     if (errors.ifNull(id, "id")) {
/* 126 */       return true;
/*     */     }
/* 128 */     CmsRole entity = this.manager.findById(id);
/*     */ 
/* 130 */     return errors.ifNotExist(entity, CmsRole.class, id);
/*     */   }
/*     */ 
/*     */   private Set<String> splitPerms(String[] perms)
/*     */   {
/* 136 */     Set set = new HashSet();
/* 137 */     if (perms != null) {
/* 138 */       for (String perm : perms) {
/* 139 */         for (String p : StringUtils.split(perm, ',')) {
/* 140 */           if (!StringUtils.isBlank(p)) {
/* 141 */             set.add(p);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 146 */     return set;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsRoleAct
 * JD-Core Version:    0.6.0
 */