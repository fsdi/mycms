/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import java.util.Date;
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
/*     */ public class CmsCommentAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(CmsCommentAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsCommentMng manager;
/*     */ 
/*  36 */   @RequestMapping({"/comment/v_list.do"})
/*     */   public String list(Integer queryContentId, Boolean queryChecked, Boolean queryRecommend, Integer pageNo, HttpServletRequest request, ModelMap model) { if (queryRecommend == null) {
/*  37 */       queryRecommend = Boolean.valueOf(false);
/*     */     }
/*  39 */     CmsSite site = CmsUtils.getSite(request);
/*  40 */     Pagination pagination = this.manager.getPage(site.getId(), queryContentId, 
/*  41 */       null, queryChecked, queryRecommend.booleanValue(), true, SimplePage.cpn(pageNo), 
/*  42 */       CookieUtils.getPageSize(request));
/*  43 */     model.addAttribute("pagination", pagination);
/*  44 */     return "comment/list"; }
/*     */ 
/*     */   @RequestMapping({"/comment/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  49 */     return "comment/add";
/*     */   }
/*     */   @RequestMapping({"/comment/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  54 */     WebErrors errors = validateEdit(id, request);
/*  55 */     if (errors.hasErrors()) {
/*  56 */       return errors.showErrorPage(model);
/*     */     }
/*  58 */     model.addAttribute("cmsComment", this.manager.findById(id));
/*  59 */     return "comment/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/comment/o_update.do"})
/*     */   public String update(Integer queryContentId, Boolean queryChecked, Boolean queryRecommend, String reply, CmsComment bean, CmsCommentExt ext, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  66 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  67 */     if (errors.hasErrors()) {
/*  68 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/*  71 */     if ((StringUtils.isNotBlank(ext.getReply())) && (!reply.equals(ext.getReply()))) {
/*  72 */       bean.setReplayTime(new Date());
/*     */     }
/*  74 */     bean = this.manager.update(bean, ext);
/*  75 */     log.info("update CmsComment id={}.", bean.getId());
/*  76 */     this.cmsLogMng.operating(request, "cmsComment.log.update", "id=" + 
/*  77 */       bean.getId());
/*  78 */     return list(queryContentId, queryChecked, queryRecommend, pageNo, 
/*  79 */       request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/comment/o_delete.do"})
/*     */   public String delete(Integer queryContentId, Boolean queryChecked, Boolean queryRecommend, Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  86 */     WebErrors errors = validateDelete(ids, request);
/*  87 */     if (errors.hasErrors()) {
/*  88 */       return errors.showErrorPage(model);
/*     */     }
/*  90 */     CmsComment[] beans = this.manager.deleteByIds(ids);
/*  91 */     for (CmsComment bean : beans) {
/*  92 */       log.info("delete CmsComment id={}", bean.getId());
/*  93 */       this.cmsLogMng.operating(request, "cmsComment.log.delete", "id=" + 
/*  94 */         bean.getId());
/*     */     }
/*  96 */     return list(queryContentId, queryChecked, queryRecommend, pageNo, 
/*  97 */       request, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 101 */     WebErrors errors = WebErrors.create(request);
/* 102 */     CmsSite site = CmsUtils.getSite(request);
/* 103 */     if (vldExist(id, site.getId(), errors)) {
/* 104 */       return errors;
/*     */     }
/* 106 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 110 */     WebErrors errors = WebErrors.create(request);
/* 111 */     CmsSite site = CmsUtils.getSite(request);
/* 112 */     if (vldExist(id, site.getId(), errors)) {
/* 113 */       return errors;
/*     */     }
/* 115 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 119 */     WebErrors errors = WebErrors.create(request);
/* 120 */     CmsSite site = CmsUtils.getSite(request);
/* 121 */     if (errors.ifEmpty(ids, "ids")) {
/* 122 */       return errors;
/*     */     }
/* 124 */     for (Integer id : ids) {
/* 125 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 127 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 131 */     if (errors.ifNull(id, "id")) {
/* 132 */       return true;
/*     */     }
/* 134 */     CmsComment entity = this.manager.findById(id);
/* 135 */     if (errors.ifNotExist(entity, CmsComment.class, id)) {
/* 136 */       return true;
/*     */     }
/* 138 */     if (!entity.getSite().getId().equals(siteId)) {
/* 139 */       errors.notInSite(CmsComment.class, id);
/* 140 */       return true;
/*     */     }
/* 142 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsCommentAct
 * JD-Core Version:    0.6.0
 */