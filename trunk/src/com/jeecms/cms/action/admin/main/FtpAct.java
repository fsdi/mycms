/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.FtpMng;
/*     */ import java.util.List;
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
/*     */ public class FtpAct
/*     */ {
/*  22 */   private static final Logger log = LoggerFactory.getLogger(FtpAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private FtpMng manager;
/*     */ 
/*  27 */   @RequestMapping({"/ftp/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { List list = this.manager.getList();
/*  28 */     model.addAttribute("list", list);
/*  29 */     return "ftp/list"; }
/*     */ 
/*     */   @RequestMapping({"/ftp/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  34 */     return "ftp/add";
/*     */   }
/*     */   @RequestMapping({"/ftp/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  39 */     WebErrors errors = validateEdit(id, request);
/*  40 */     if (errors.hasErrors()) {
/*  41 */       return errors.showErrorPage(model);
/*     */     }
/*  43 */     model.addAttribute("ftp", this.manager.findById(id));
/*  44 */     return "ftp/edit";
/*     */   }
/*     */   @RequestMapping({"/ftp/o_save.do"})
/*     */   public String save(Ftp bean, HttpServletRequest request, ModelMap model) {
/*  49 */     WebErrors errors = validateSave(bean, request);
/*  50 */     if (errors.hasErrors()) {
/*  51 */       return errors.showErrorPage(model);
/*     */     }
/*  53 */     bean = this.manager.save(bean);
/*  54 */     log.info("save Ftp id={}", bean.getId());
/*  55 */     this.cmsLogMng.operating(request, "ftp.log.save", "id=" + bean.getId() + 
/*  56 */       ";name=" + bean.getName());
/*  57 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/ftp/o_update.do"})
/*     */   public String update(Ftp bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  63 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  64 */     if (errors.hasErrors()) {
/*  65 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/*  68 */     if (StringUtils.isBlank(bean.getPassword())) {
/*  69 */       bean.setPassword(this.manager.findById(bean.getId()).getPassword());
/*     */     }
/*  71 */     bean = this.manager.update(bean);
/*  72 */     log.info("update Ftp id={}.", bean.getId());
/*  73 */     this.cmsLogMng.operating(request, "ftp.log.update", "id=" + bean.getId() + 
/*  74 */       ";name=" + bean.getName());
/*  75 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/ftp/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  81 */     WebErrors errors = validateDelete(ids, request);
/*  82 */     if (errors.hasErrors()) {
/*  83 */       return errors.showErrorPage(model);
/*     */     }
/*  85 */     Ftp[] beans = this.manager.deleteByIds(ids);
/*  86 */     for (Ftp bean : beans) {
/*  87 */       log.info("delete Ftp id={}", bean.getId());
/*  88 */       this.cmsLogMng.operating(request, "ftp.log.delete", "id=" + bean.getId() + 
/*  89 */         ";name=" + bean.getName());
/*     */     }
/*  91 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Ftp bean, HttpServletRequest request) {
/*  95 */     WebErrors errors = WebErrors.create(request);
/*  96 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 100 */     WebErrors errors = WebErrors.create(request);
/* 101 */     if (vldExist(id, errors)) {
/* 102 */       return errors;
/*     */     }
/* 104 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 108 */     WebErrors errors = WebErrors.create(request);
/* 109 */     if (vldExist(id, errors)) {
/* 110 */       return errors;
/*     */     }
/* 112 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 116 */     WebErrors errors = WebErrors.create(request);
/* 117 */     errors.ifEmpty(ids, "ids");
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
/* 128 */     Ftp entity = this.manager.findById(id);
/*     */ 
/* 130 */     return errors.ifNotExist(entity, Ftp.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.FtpAct
 * JD-Core Version:    0.6.0
 */