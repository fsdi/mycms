/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsConfigMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.FtpMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsSiteAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(CmsSiteAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsConfigMng configMng;
/*     */ 
/*     */   @Autowired
/*     */   private FtpMng ftpMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteMng manager;
/*     */ 
/*  35 */   @RequestMapping({"/site/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { List list = this.manager.getList();
/*  36 */     model.addAttribute("list", list);
/*  37 */     return "site/list"; }
/*     */ 
/*     */   @RequestMapping({"/site/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  42 */     List ftpList = this.ftpMng.getList();
/*  43 */     model.addAttribute("ftpList", ftpList);
/*  44 */     return "site/add";
/*     */   }
/*     */   @RequestMapping({"/site/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  49 */     WebErrors errors = validateEdit(id, request);
/*  50 */     if (errors.hasErrors()) {
/*  51 */       return errors.showErrorPage(model);
/*     */     }
/*  53 */     List ftpList = this.ftpMng.getList();
/*  54 */     model.addAttribute("ftpList", ftpList);
/*  55 */     model.addAttribute("cmsSite", this.manager.findById(id));
/*  56 */     return "site/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/site/o_save.do"})
/*     */   public String save(CmsSite bean, Integer uploadFtpId, HttpServletRequest request, ModelMap model) throws IOException {
/*  62 */     CmsSite site = CmsUtils.getSite(request);
/*  63 */     CmsUser user = CmsUtils.getUser(request);
/*  64 */     WebErrors errors = validateSave(bean, uploadFtpId, request);
/*  65 */     if (errors.hasErrors()) {
/*  66 */       return errors.showErrorPage(model);
/*     */     }
/*  68 */     bean = this.manager.save(site, user, bean, uploadFtpId);
/*  69 */     log.info("save CmsSite id={}", bean.getId());
/*  70 */     this.cmsLogMng.operating(request, "cmsSite.log.save", "id=" + bean.getId() + 
/*  71 */       ";name=" + bean.getName());
/*  72 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/site/o_update.do"})
/*     */   public String update(CmsSite bean, Integer uploadFtpId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  78 */     WebErrors errors = validateUpdate(bean.getId(), uploadFtpId, request);
/*  79 */     if (errors.hasErrors()) {
/*  80 */       return errors.showErrorPage(model);
/*     */     }
/*  82 */     bean = this.manager.update(bean, uploadFtpId);
/*  83 */     log.info("update CmsSite id={}.", bean.getId());
/*  84 */     this.cmsLogMng.operating(request, "cmsSite.log.update", "id=" + bean.getId() + 
/*  85 */       ";name=" + bean.getName());
/*  86 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/site/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  92 */     WebErrors errors = validateDelete(ids, request);
/*  93 */     if (errors.hasErrors()) {
/*  94 */       return errors.showErrorPage(model);
/*     */     }
/*  96 */     CmsSite[] beans = this.manager.deleteByIds(ids);
/*  97 */     for (CmsSite bean : beans) {
/*  98 */       log.info("delete CmsSite id={}", bean.getId());
/*  99 */       this.cmsLogMng.operating(request, "cmsSite.log.delete", "id=" + 
/* 100 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 102 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/site/v_checkDomain.do"})
/*     */   public void checkUserJson(String domain, HttpServletResponse response)
/*     */   {
/*     */     String pass;
/* 108 */     if (StringUtils.isBlank(domain))
/* 109 */       pass = "false";
/*     */     else {
/* 111 */       pass = this.manager.findByDomain(domain, false) == null ? "true" : 
/* 112 */         "false";
/*     */     }
/* 114 */     ResponseUtils.renderJson(response, pass);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsSite bean, Integer uploadFtpId, HttpServletRequest request)
/*     */   {
/* 119 */     WebErrors errors = WebErrors.create(request);
/* 120 */     if (vldFtpExist(uploadFtpId, errors)) {
/* 121 */       return errors;
/*     */     }
/*     */ 
/* 124 */     bean.setConfig(this.configMng.get());
/* 125 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 129 */     WebErrors errors = WebErrors.create(request);
/* 130 */     if (vldExist(id, errors)) {
/* 131 */       return errors;
/*     */     }
/* 133 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, Integer uploadFtpId, HttpServletRequest request)
/*     */   {
/* 138 */     WebErrors errors = WebErrors.create(request);
/* 139 */     if (vldExist(id, errors)) {
/* 140 */       return errors;
/*     */     }
/* 142 */     if (vldFtpExist(uploadFtpId, errors)) {
/* 143 */       return errors;
/*     */     }
/* 145 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 149 */     WebErrors errors = WebErrors.create(request);
/* 150 */     errors.ifEmpty(ids, "ids");
/* 151 */     for (Integer id : ids) {
/* 152 */       vldExist(id, errors);
/*     */     }
/* 154 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldFtpExist(Integer id, WebErrors errors) {
/* 158 */     if (id == null) {
/* 159 */       return false;
/*     */     }
/* 161 */     Ftp entity = this.ftpMng.findById(id);
/* 162 */     return errors.ifNotExist(entity, Ftp.class, id);
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 166 */     if (errors.ifNull(id, "id")) {
/* 167 */       return true;
/*     */     }
/* 169 */     CmsSite entity = this.manager.findById(id);
/*     */ 
/* 171 */     return errors.ifNotExist(entity, CmsSite.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsSiteAct
 * JD-Core Version:    0.6.0
 */