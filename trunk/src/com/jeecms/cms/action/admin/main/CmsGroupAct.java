/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsGroupAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(CmsGroupAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsGroupMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   protected CmsSiteMng cmsSiteMng;
/*     */ 
/*  31 */   @RequestMapping({"/group/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { List list = this.manager.getList();
/*  32 */     model.addAttribute("list", list);
/*  33 */     return "group/list"; }
/*     */ 
/*     */   @RequestMapping({"/group/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  38 */     List siteList = this.cmsSiteMng.getList();
/*  39 */     model.addAttribute("siteList", siteList);
/*  40 */     return "group/add";
/*     */   }
/*     */   @RequestMapping({"/group/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  45 */     WebErrors errors = validateEdit(id, request);
/*  46 */     if (errors.hasErrors()) {
/*  47 */       return errors.showErrorPage(model);
/*     */     }
/*  49 */     List siteList = this.cmsSiteMng.getList();
/*  50 */     model.addAttribute("siteList", siteList);
/*  51 */     model.addAttribute("cmsGroup", this.manager.findById(id));
/*  52 */     return "group/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_save.do"})
/*     */   public String save(CmsGroup bean, Integer[] viewGroupIds, Integer[] contriGroupIds, HttpServletRequest request, ModelMap model) {
/*  58 */     WebErrors errors = validateSave(bean, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*     */     }
/*  62 */     bean = this.manager.save(bean, viewGroupIds, contriGroupIds);
/*  63 */     log.info("save CmsGroup id={}", bean.getId());
/*  64 */     this.cmsLogMng.operating(request, "cmsGroup.log.save", "id=" + bean.getId() + 
/*  65 */       ";name=" + bean.getName());
/*  66 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_update.do"})
/*     */   public String update(CmsGroup bean, Integer[] viewGroupIds, Integer[] contriGroupIds, HttpServletRequest request, ModelMap model) {
/*  72 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  73 */     if (errors.hasErrors()) {
/*  74 */       return errors.showErrorPage(model);
/*     */     }
/*  76 */     bean = this.manager.update(bean, viewGroupIds, contriGroupIds);
/*  77 */     log.info("update CmsGroup id={}.", bean.getId());
/*  78 */     this.cmsLogMng.operating(request, "cmsGroup.log.update", "id=" + 
/*  79 */       bean.getId() + ";name=" + bean.getName());
/*  80 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  86 */     WebErrors errors = validateDelete(ids, request);
/*  87 */     if (errors.hasErrors()) {
/*  88 */       return errors.showErrorPage(model);
/*     */     }
/*  90 */     CmsGroup[] beans = this.manager.deleteByIds(ids);
/*  91 */     for (CmsGroup bean : beans) {
/*  92 */       log.info("delete CmsGroup id={}", bean.getId());
/*  93 */       this.cmsLogMng.operating(request, "cmsGroup.log.delete", "id=" + 
/*  94 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  96 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Integer regDefId, HttpServletRequest request, ModelMap model) {
/* 102 */     WebErrors errors = validatePriority(wids, priority, request);
/* 103 */     if (errors.hasErrors()) {
/* 104 */       return errors.showErrorPage(model);
/*     */     }
/* 106 */     this.manager.updatePriority(wids, priority);
/* 107 */     this.manager.updateRegDef(regDefId);
/* 108 */     model.addAttribute("message", "global.success");
/* 109 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/v_channels_add.do"})
/*     */   public String channelsAdd(Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 115 */     return channelsAddJson(siteId, request, response, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/group/v_channels_edit.do"})
/*     */   public String channelsEdit(Integer groupId, Integer siteId, Integer type, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 122 */     return channelsEditJson(groupId, siteId, type, request, response, model);
/*     */   }
/*     */ 
/*     */   private String channelsAddJson(Integer siteId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 127 */     List channelList = this.channelMng.getTopList(siteId, false);
/* 128 */     model.addAttribute("channelList", channelList);
/* 129 */     response.setHeader("Cache-Control", "no-cache");
/* 130 */     response.setContentType("text/json;charset=UTF-8");
/* 131 */     return "group/channels_add";
/*     */   }
/*     */ 
/*     */   private String channelsEditJson(Integer groupId, Integer siteId, Integer type, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 137 */     List channelList = this.channelMng.getTopList(siteId, false);
/* 138 */     CmsGroup group = this.manager.findById(groupId);
/* 139 */     model.addAttribute("channelList", channelList);
/* 140 */     if (type.equals(Integer.valueOf(1)))
/* 141 */       model.addAttribute("channelIds", group.getViewChannelIds(siteId));
/*     */     else {
/* 143 */       model.addAttribute("channelIds", group.getContriChannelIds(siteId));
/*     */     }
/* 145 */     response.setHeader("Cache-Control", "no-cache");
/* 146 */     response.setContentType("text/json;charset=UTF-8");
/* 147 */     return "group/channels_edit";
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsGroup bean, HttpServletRequest request) {
/* 151 */     WebErrors errors = WebErrors.create(request);
/* 152 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 156 */     WebErrors errors = WebErrors.create(request);
/* 157 */     if (vldExist(id, errors)) {
/* 158 */       return errors;
/*     */     }
/* 160 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 164 */     WebErrors errors = WebErrors.create(request);
/* 165 */     if (vldExist(id, errors)) {
/* 166 */       return errors;
/*     */     }
/* 168 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 172 */     WebErrors errors = WebErrors.create(request);
/* 173 */     if (errors.ifEmpty(ids, "ids")) {
/* 174 */       return errors;
/*     */     }
/* 176 */     for (Integer id : ids) {
/* 177 */       vldExist(id, errors);
/*     */     }
/* 179 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Integer[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 184 */     WebErrors errors = WebErrors.create(request);
/* 185 */     if (errors.ifEmpty(wids, "wids")) {
/* 186 */       return errors;
/*     */     }
/* 188 */     if (errors.ifEmpty(priority, "priority")) {
/* 189 */       return errors;
/*     */     }
/* 191 */     if (wids.length != priority.length) {
/* 192 */       errors.addErrorString("wids length not equals priority length");
/* 193 */       return errors;
/*     */     }
/* 195 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 196 */       if (vldExist(wids[i], errors)) {
/* 197 */         return errors;
/*     */       }
/* 199 */       if (priority[i] == null) {
/* 200 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 203 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 207 */     if (errors.ifNull(id, "id")) {
/* 208 */       return true;
/*     */     }
/* 210 */     CmsGroup entity = this.manager.findById(id);
/*     */ 
/* 212 */     return errors.ifNotExist(entity, CmsGroup.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsGroupAct
 * JD-Core Version:    0.6.0
 */