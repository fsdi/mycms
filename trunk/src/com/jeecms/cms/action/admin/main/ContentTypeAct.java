/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.ContentType;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.ContentTypeMng;
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
/*     */ public class ContentTypeAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(ContentTypeAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentTypeMng manager;
/*     */ 
/*  29 */   @RequestMapping({"/type/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { List list = this.manager.getList(true);
/*  30 */     model.addAttribute("list", list);
/*  31 */     return "type/list"; }
/*     */ 
/*     */   @RequestMapping({"/type/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  36 */     return "type/add";
/*     */   }
/*     */   @RequestMapping({"/type/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  41 */     WebErrors errors = validateEdit(id, request);
/*  42 */     if (errors.hasErrors()) {
/*  43 */       return errors.showErrorPage(model);
/*     */     }
/*  45 */     model.addAttribute("contentType", this.manager.findById(id));
/*  46 */     return "type/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/type/o_save.do"})
/*     */   public String save(ContentType bean, HttpServletRequest request, ModelMap model) {
/*  52 */     WebErrors errors = validateSave(bean, request);
/*  53 */     if (errors.hasErrors()) {
/*  54 */       return errors.showErrorPage(model);
/*     */     }
/*  56 */     bean = this.manager.save(bean);
/*  57 */     log.info("save ContentType id={}", bean.getId());
/*  58 */     this.cmsLogMng.operating(request, "contentType.log.save", "id=" + 
/*  59 */       bean.getId() + ";name=" + bean.getName());
/*  60 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/type/o_update.do"})
/*     */   public String update(ContentType bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  66 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  67 */     if (errors.hasErrors()) {
/*  68 */       return errors.showErrorPage(model);
/*     */     }
/*  70 */     bean = this.manager.update(bean);
/*  71 */     log.info("update ContentType id={}.", bean.getId());
/*  72 */     this.cmsLogMng.operating(request, "contentType.log.update", "id=" + 
/*  73 */       bean.getId() + ";name=" + bean.getName());
/*  74 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/type/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  80 */     WebErrors errors = validateDelete(ids, request);
/*  81 */     if (errors.hasErrors()) {
/*  82 */       return errors.showErrorPage(model);
/*     */     }
/*  84 */     ContentType[] beans = this.manager.deleteByIds(ids);
/*  85 */     for (ContentType bean : beans) {
/*  86 */       log.info("delete ContentType id={}", bean.getId());
/*  87 */       this.cmsLogMng.operating(request, "contentType.log.delete", "id=" + 
/*  88 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  90 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(ContentType bean, HttpServletRequest request) {
/*  94 */     WebErrors errors = WebErrors.create(request);
/*  95 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/*  99 */     WebErrors errors = WebErrors.create(request);
/* 100 */     CmsSite site = CmsUtils.getSite(request);
/* 101 */     if (vldExist(id, site.getId(), errors)) {
/* 102 */       return errors;
/*     */     }
/* 104 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 108 */     WebErrors errors = WebErrors.create(request);
/* 109 */     CmsSite site = CmsUtils.getSite(request);
/* 110 */     if (vldExist(id, site.getId(), errors)) {
/* 111 */       return errors;
/*     */     }
/* 113 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 117 */     WebErrors errors = WebErrors.create(request);
/* 118 */     CmsSite site = CmsUtils.getSite(request);
/* 119 */     errors.ifEmpty(ids, "ids");
/* 120 */     for (Integer id : ids) {
/* 121 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 123 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 127 */     if (errors.ifNull(id, "id")) {
/* 128 */       return true;
/*     */     }
/* 130 */     ContentType entity = this.manager.findById(id);
/*     */ 
/* 132 */     return errors.ifNotExist(entity, ContentType.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.ContentTypeAct
 * JD-Core Version:    0.6.0
 */