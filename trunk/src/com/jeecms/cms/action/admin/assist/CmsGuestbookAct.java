/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import java.util.Date;
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
/*     */ public class CmsGuestbookAct
/*     */ {
/*  34 */   private static final Logger log = LoggerFactory.getLogger(CmsGuestbookAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsGuestbookCtgMng cmsGuestbookCtgMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsGuestbookMng manager;
/*     */ 
/*  40 */   @RequestMapping({"/guestbook/v_list.do"})
/*     */   public String list(Integer queryCtgId, Boolean queryRecommend, Boolean queryChecked, Integer pageNo, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  41 */     Pagination pagination = this.manager.getPage(site.getId(), queryCtgId, null, 
/*  42 */       queryRecommend, queryChecked, true, false, SimplePage.cpn(pageNo), 
/*  43 */       CookieUtils.getPageSize(request));
/*  44 */     model.addAttribute("pagination", pagination);
/*  45 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  46 */     return "guestbook/list"; }
/*     */ 
/*     */   @RequestMapping({"/guestbook/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  51 */     CmsSite site = CmsUtils.getSite(request);
/*  52 */     List ctgList = this.cmsGuestbookCtgMng
/*  53 */       .getList(site.getId());
/*  54 */     model.addAttribute("ctgList", ctgList);
/*  55 */     return "guestbook/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/guestbook/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  61 */     CmsSite site = CmsUtils.getSite(request);
/*  62 */     WebErrors errors = validateEdit(id, request);
/*  63 */     if (errors.hasErrors()) {
/*  64 */       return errors.showErrorPage(model);
/*     */     }
/*  66 */     CmsGuestbook cmsGuestbook = this.manager.findById(id);
/*  67 */     List ctgList = this.cmsGuestbookCtgMng
/*  68 */       .getList(site.getId());
/*     */ 
/*  70 */     model.addAttribute("cmsGuestbook", cmsGuestbook);
/*  71 */     model.addAttribute("ctgList", ctgList);
/*  72 */     model.addAttribute("pageNo", pageNo);
/*  73 */     return "guestbook/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/guestbook/o_save.do"})
/*     */   public String save(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId, HttpServletRequest request, ModelMap model) {
/*  79 */     WebErrors errors = validateSave(bean, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     String ip = RequestUtils.getIpAddr(request);
/*  84 */     bean = this.manager.save(bean, ext, ctgId, ip);
/*  85 */     log.info("save CmsGuestbook id={}", bean.getId());
/*  86 */     this.cmsLogMng.operating(request, "cmsGuestbook.log.save", "id=" + 
/*  87 */       bean.getId() + ";title=" + bean.getTitle());
/*  88 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/guestbook/o_update.do"})
/*     */   public String update(Integer queryCtgId, Boolean queryRecommend, Boolean queryChecked, String oldreply, CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/*  96 */     WebErrors errors = validateUpdate(bean.getId(), request);
/*  97 */     if (errors.hasErrors()) {
/*  98 */       return errors.showErrorPage(model);
/*     */     }
/* 100 */     Date now = new Date();
/* 101 */     if ((StringUtils.isNotBlank(ext.getReply())) && (!oldreply.equals(ext.getReply()))) {
/* 102 */       bean.setReplayTime(now);
/* 103 */       if (bean.getAdmin() != null) {
/* 104 */         if (!bean.getAdmin().equals(CmsUtils.getUser(request)))
/* 105 */           bean.setAdmin(CmsUtils.getUser(request));
/*     */       }
/*     */       else {
/* 108 */         bean.setAdmin(CmsUtils.getUser(request));
/*     */       }
/*     */     }
/* 111 */     bean = this.manager.update(bean, ext, ctgId);
/* 112 */     log.info("update CmsGuestbook id={}.", bean.getId());
/* 113 */     this.cmsLogMng.operating(request, "cmsGuestbook.log.update", "id=" + 
/* 114 */       bean.getId() + ";title=" + bean.getTitle());
/* 115 */     return list(queryCtgId, queryRecommend, queryChecked, pageNo, request, 
/* 116 */       model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/guestbook/o_delete.do"})
/*     */   public String delete(Integer queryCtgId, Boolean queryRecommend, Boolean queryChecked, Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 123 */     WebErrors errors = validateDelete(ids, request);
/* 124 */     if (errors.hasErrors()) {
/* 125 */       return errors.showErrorPage(model);
/*     */     }
/* 127 */     CmsGuestbook[] beans = this.manager.deleteByIds(ids);
/* 128 */     for (CmsGuestbook bean : beans) {
/* 129 */       log.info("delete CmsGuestbook id={}", bean.getId());
/* 130 */       this.cmsLogMng.operating(request, "cmsGuestbook.log.delete", "id=" + 
/* 131 */         bean.getId() + ";title=" + bean.getTitle());
/*     */     }
/* 133 */     return list(queryCtgId, queryRecommend, queryChecked, pageNo, request, 
/* 134 */       model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsGuestbook bean, HttpServletRequest request) {
/* 138 */     WebErrors errors = WebErrors.create(request);
/* 139 */     CmsSite site = CmsUtils.getSite(request);
/* 140 */     bean.setSite(site);
/* 141 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 145 */     WebErrors errors = WebErrors.create(request);
/* 146 */     CmsSite site = CmsUtils.getSite(request);
/* 147 */     if (vldExist(id, site.getId(), errors)) {
/* 148 */       return errors;
/*     */     }
/* 150 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 154 */     WebErrors errors = WebErrors.create(request);
/* 155 */     CmsSite site = CmsUtils.getSite(request);
/* 156 */     if (vldExist(id, site.getId(), errors)) {
/* 157 */       return errors;
/*     */     }
/* 159 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 163 */     WebErrors errors = WebErrors.create(request);
/* 164 */     CmsSite site = CmsUtils.getSite(request);
/* 165 */     if (errors.ifEmpty(ids, "ids")) {
/* 166 */       return errors;
/*     */     }
/* 168 */     for (Integer id : ids) {
/* 169 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 171 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 175 */     if (errors.ifNull(id, "id")) {
/* 176 */       return true;
/*     */     }
/* 178 */     CmsGuestbook entity = this.manager.findById(id);
/* 179 */     if (errors.ifNotExist(entity, CmsGuestbook.class, id)) {
/* 180 */       return true;
/*     */     }
/* 182 */     if (!entity.getSite().getId().equals(siteId)) {
/* 183 */       errors.notInSite(CmsGuestbook.class, id);
/* 184 */       return true;
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsGuestbookAct
 * JD-Core Version:    0.6.0
 */