/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
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
/*     */ public class CmsFriendlinkCtgAct
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(CmsFriendlinkCtgAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsFriendlinkMng cmsFriendlinkMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsFriendlinkCtgMng manager;
/*     */ 
/*  29 */   @RequestMapping({"/friendlink_ctg/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  30 */     List list = this.manager.getList(site.getId());
/*  31 */     model.addAttribute("list", list);
/*  32 */     return "friendlink_ctg/list"; }
/*     */ 
/*     */   @RequestMapping({"/friendlink_ctg/o_save.do"})
/*     */   public String save(CmsFriendlinkCtg bean, HttpServletRequest request, ModelMap model)
/*     */   {
/*  38 */     WebErrors errors = validateSave(bean, request);
/*  39 */     if (errors.hasErrors()) {
/*  40 */       return errors.showErrorPage(model);
/*     */     }
/*  42 */     bean = this.manager.save(bean);
/*  43 */     log.info("save CmsFriendlinkCtg id={}", bean.getId());
/*  44 */     this.cmsLogMng.operating(request, "cmsFriendlinkCtg.log.save", "id=" + 
/*  45 */       bean.getId() + ";name=" + bean.getName());
/*  46 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink_ctg/o_update.do"})
/*     */   public String update(Integer[] wids, String[] name, Integer[] priority, HttpServletRequest request, ModelMap model) {
/*  52 */     WebErrors errors = validateUpdate(wids, name, priority, request);
/*  53 */     if (errors.hasErrors()) {
/*  54 */       return errors.showErrorPage(model);
/*     */     }
/*  56 */     this.manager.updateFriendlinkCtgs(wids, name, priority);
/*  57 */     log.info("update CmsFriendlinkCtg.");
/*  58 */     this.cmsLogMng.operating(request, "cmsFriendlinkCtg.log.update", null);
/*  59 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/friendlink_ctg/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/*  65 */     WebErrors errors = validateDelete(ids, request);
/*  66 */     if (errors.hasErrors()) {
/*  67 */       return errors.showErrorPage(model);
/*     */     }
/*  69 */     CmsFriendlinkCtg[] beans = this.manager.deleteByIds(ids);
/*  70 */     for (CmsFriendlinkCtg bean : beans) {
/*  71 */       log.info("delete CmsFriendlinkCtg id={}", bean.getId());
/*  72 */       this.cmsLogMng.operating(request, "cmsFriendlinkCtg.log.delete", "id=" + 
/*  73 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/*  75 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsFriendlinkCtg bean, HttpServletRequest request)
/*     */   {
/*  80 */     WebErrors errors = WebErrors.create(request);
/*  81 */     CmsSite site = CmsUtils.getSite(request);
/*  82 */     bean.setSite(site);
/*  83 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer[] ids, String[] names, Integer[] priorities, HttpServletRequest request)
/*     */   {
/*  88 */     WebErrors errors = WebErrors.create(request);
/*  89 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/*  93 */     WebErrors errors = WebErrors.create(request);
/*  94 */     CmsSite site = CmsUtils.getSite(request);
/*  95 */     if (errors.ifEmpty(ids, "ids")) {
/*  96 */       return errors;
/*     */     }
/*  98 */     for (Integer id : ids) {
/*  99 */       vldExist(id, site.getId(), errors);
/* 100 */       if (this.cmsFriendlinkMng.countByCtgId(id) > 0) {
/* 101 */         String code = "cmsFriendlinkCtg.error.delFriendlinkFirst";
/* 102 */         errors.addErrorCode(code);
/* 103 */         return errors;
/*     */       }
/*     */     }
/* 106 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 110 */     if (errors.ifNull(id, "id")) {
/* 111 */       return true;
/*     */     }
/* 113 */     CmsFriendlinkCtg entity = this.manager.findById(id);
/* 114 */     if (errors.ifNotExist(entity, CmsFriendlinkCtg.class, id)) {
/* 115 */       return true;
/*     */     }
/* 117 */     if (!entity.getSite().getId().equals(siteId)) {
/* 118 */       errors.notInSite(CmsFriendlinkCtg.class, id);
/* 119 */       return true;
/*     */     }
/* 121 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsFriendlinkCtgAct
 * JD-Core Version:    0.6.0
 */