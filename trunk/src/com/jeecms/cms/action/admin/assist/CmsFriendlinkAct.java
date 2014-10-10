/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFriendlink;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.manager.assist.CmsFriendlinkCtgMng;
/*     */ import com.jeecms.cms.manager.assist.CmsFriendlinkMng;
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
/*     */ public class CmsFriendlinkAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(CmsFriendlinkAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsFriendlinkCtgMng cmsFriendlinkCtgMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsFriendlinkMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsFileMng fileMng;
/*     */ 
/*  32 */   @RequestMapping({"/friendlink/v_list.do"})
/*     */   public String list(Integer queryCtgId, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  33 */     List list = this.manager.getList(site.getId(), queryCtgId, 
/*  34 */       null);
/*  35 */     List ctgList = this.cmsFriendlinkCtgMng.getList(
/*  36 */       site.getId());
/*  37 */     model.addAttribute("list", list);
/*  38 */     model.addAttribute("ctgList", ctgList);
/*  39 */     if (queryCtgId != null) {
/*  40 */       model.addAttribute("queryCtgId", queryCtgId);
/*     */     }
/*  42 */     return "friendlink/list"; }
/*     */ 
/*     */   @RequestMapping({"/friendlink/v_add.do"})
/*     */   public String add(ModelMap model, HttpServletRequest request) {
/*  47 */     CmsSite site = CmsUtils.getSite(request);
/*  48 */     WebErrors errors = validateAdd(request);
/*  49 */     if (errors.hasErrors()) {
/*  50 */       return errors.showErrorPage(model);
/*     */     }
/*  52 */     List ctgList = this.cmsFriendlinkCtgMng.getList(
/*  53 */       site.getId());
/*  54 */     model.addAttribute("ctgList", ctgList);
/*  55 */     return "friendlink/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink/v_edit.do"})
/*     */   public String edit(Integer id, Integer queryCtgId, HttpServletRequest request, ModelMap model) {
/*  61 */     CmsSite site = CmsUtils.getSite(request);
/*  62 */     WebErrors errors = validateEdit(id, request);
/*  63 */     if (errors.hasErrors()) {
/*  64 */       return errors.showErrorPage(model);
/*     */     }
/*  66 */     model.addAttribute("cmsFriendlink", this.manager.findById(id));
/*  67 */     List ctgList = this.cmsFriendlinkCtgMng.getList(
/*  68 */       site.getId());
/*  69 */     model.addAttribute("ctgList", ctgList);
/*  70 */     if (queryCtgId != null) {
/*  71 */       model.addAttribute("queryCtgId", queryCtgId);
/*     */     }
/*  73 */     return "friendlink/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink/o_save.do"})
/*     */   public String save(CmsFriendlink bean, Integer ctgId, HttpServletRequest request, ModelMap model) {
/*  79 */     WebErrors errors = validateSave(bean, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     bean = this.manager.save(bean, ctgId);
/*  84 */     this.fileMng.updateFileByPath(bean.getLogo(), Boolean.valueOf(true), null);
/*  85 */     log.info("save CmsFriendlink id={}", bean.getId());
/*  86 */     this.cmsLogMng.operating(request, "cmsFriendlink.log.save", "id=" + 
/*  87 */       bean.getId() + ";name=" + bean.getName());
/*  88 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink/o_update.do"})
/*     */   public String update(CmsFriendlink bean, Integer ctgId, Integer queryCtgId, String oldLog, HttpServletRequest request, ModelMap model)
/*     */   {
/*  95 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  96 */     if (errors.hasErrors()) {
/*  97 */       return errors.showErrorPage(model);
/*     */     }
/*  99 */     bean = this.manager.update(bean, ctgId);
/* 100 */     this.fileMng.updateFileByPath(oldLog, Boolean.valueOf(false), null);
/* 101 */     this.fileMng.updateFileByPath(bean.getLogo(), Boolean.valueOf(true), null);
/* 102 */     log.info("update CmsFriendlink id={}.", bean.getId());
/* 103 */     this.cmsLogMng.operating(request, "cmsFriendlink.log.update", "id=" + 
/* 104 */       bean.getId() + ";name=" + bean.getName());
/* 105 */     return list(queryCtgId, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Integer queryCtgId, HttpServletRequest request, ModelMap model) {
/* 111 */     WebErrors errors = validatePriority(wids, priority, request);
/* 112 */     if (errors.hasErrors()) {
/* 113 */       return errors.showErrorPage(model);
/*     */     }
/* 115 */     this.manager.updatePriority(wids, priority);
/* 116 */     log.info("update CmsFriendlink priority.");
/* 117 */     return list(queryCtgId, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryCtgId, HttpServletRequest request, ModelMap model) {
/* 123 */     WebErrors errors = validateDelete(ids, request);
/* 124 */     if (errors.hasErrors()) {
/* 125 */       return errors.showErrorPage(model);
/*     */     }
/* 127 */     CmsFriendlink[] beans = this.manager.deleteByIds(ids);
/* 128 */     for (CmsFriendlink bean : beans) {
/* 129 */       this.fileMng.updateFileByPath(bean.getLogo(), Boolean.valueOf(false), null);
/* 130 */       log.info("delete CmsFriendlink id={}", bean.getId());
/* 131 */       this.cmsLogMng.operating(request, "cmsFriendlink.log.delete", "id=" + 
/* 132 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 134 */     return list(queryCtgId, request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateAdd(HttpServletRequest request) {
/* 138 */     WebErrors errors = WebErrors.create(request);
/* 139 */     CmsSite site = CmsUtils.getSite(request);
/* 140 */     if (this.cmsFriendlinkCtgMng.countBySiteId(site.getId()) <= 0) {
/* 141 */       errors.addErrorCode("cmsFriendlink.error.addFriendlinkCtgFirst");
/* 142 */       return errors;
/*     */     }
/* 144 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsFriendlink bean, HttpServletRequest request)
/*     */   {
/* 149 */     WebErrors errors = WebErrors.create(request);
/* 150 */     CmsSite site = CmsUtils.getSite(request);
/* 151 */     bean.setSite(site);
/* 152 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 156 */     WebErrors errors = WebErrors.create(request);
/* 157 */     CmsSite site = CmsUtils.getSite(request);
/* 158 */     if (vldExist(id, site.getId(), errors)) {
/* 159 */       return errors;
/*     */     }
/* 161 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 165 */     WebErrors errors = WebErrors.create(request);
/* 166 */     CmsSite site = CmsUtils.getSite(request);
/* 167 */     if (vldExist(id, site.getId(), errors)) {
/* 168 */       return errors;
/*     */     }
/* 170 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Integer[] ids, Integer[] priorities, HttpServletRequest request)
/*     */   {
/* 175 */     WebErrors errors = WebErrors.create(request);
/* 176 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 180 */     WebErrors errors = WebErrors.create(request);
/* 181 */     CmsSite site = CmsUtils.getSite(request);
/* 182 */     if (errors.ifEmpty(ids, "ids")) {
/* 183 */       return errors;
/*     */     }
/* 185 */     for (Integer id : ids) {
/* 186 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 188 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 192 */     if (errors.ifNull(id, "id")) {
/* 193 */       return true;
/*     */     }
/* 195 */     CmsFriendlink entity = this.manager.findById(id);
/* 196 */     if (errors.ifNotExist(entity, CmsFriendlink.class, id)) {
/* 197 */       return true;
/*     */     }
/* 199 */     if (!entity.getSite().getId().equals(siteId)) {
/* 200 */       errors.notInSite(CmsFriendlink.class, id);
/* 201 */       return true;
/*     */     }
/* 203 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsFriendlinkAct
 * JD-Core Version:    0.6.0
 */