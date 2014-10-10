/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsDictionary;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsDictionaryMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class CmsDictionaryAct
/*     */ {
/*  32 */   private static final Logger log = LoggerFactory.getLogger(CmsDictionaryAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsDictionaryMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*  36 */   @RequestMapping({"/dictionary/v_list.do"})
/*     */   public String list(String queryType, Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(queryType, SimplePage.cpn(pageNo), 
/*  37 */       CookieUtils.getPageSize(request));
/*  38 */     List typeList = this.manager.getTypeList();
/*  39 */     model.addAttribute("pagination", pagination);
/*  40 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  41 */     model.addAttribute("typeList", typeList);
/*  42 */     model.addAttribute("queryType", queryType);
/*  43 */     return "dictionary/list"; }
/*     */ 
/*     */   @RequestMapping({"/dictionary/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  48 */     return "dictionary/add";
/*     */   }
/*     */   @RequestMapping({"/dictionary/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  53 */     WebErrors errors = validateEdit(id, request);
/*  54 */     if (errors.hasErrors()) {
/*  55 */       return errors.showErrorPage(model);
/*     */     }
/*  57 */     model.addAttribute("cmsDictionary", this.manager.findById(id));
/*  58 */     model.addAttribute("pageNo", pageNo);
/*  59 */     return "dictionary/edit";
/*     */   }
/*     */   @RequestMapping({"/dictionary/o_save.do"})
/*     */   public String save(CmsDictionary bean, HttpServletRequest request, ModelMap model) {
/*  64 */     WebErrors errors = validateSave(bean, request);
/*  65 */     if (errors.hasErrors()) {
/*  66 */       return errors.showErrorPage(model);
/*     */     }
/*  68 */     bean = this.manager.save(bean);
/*  69 */     log.info("save CmsDictionary id={}", bean.getId());
/*  70 */     this.cmsLogMng.operating(request, "CmsDictionary.log.save", "id=" + 
/*  71 */       bean.getId() + ";name=" + bean.getName());
/*  72 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/dictionary/o_update.do"})
/*     */   public String update(CmsDictionary bean, String queryType, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  78 */     WebErrors errors = validateUpdate(bean, request);
/*  79 */     if (errors.hasErrors()) {
/*  80 */       return errors.showErrorPage(model);
/*     */     }
/*  82 */     bean = this.manager.update(bean);
/*  83 */     log.info("update CmsDictionary id={}.", bean.getId());
/*  84 */     this.cmsLogMng.operating(request, "CmsDictionary.log.update", "id=" + 
/*  85 */       bean.getId() + ";name=" + bean.getName());
/*  86 */     return list(queryType, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/dictionary/o_delete.do"})
/*     */   public String delete(Integer[] ids, String queryType, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  92 */     WebErrors errors = validateDelete(ids, request);
/*  93 */     if (errors.hasErrors()) {
/*  94 */       return errors.showErrorPage(model);
/*     */     }
/*  96 */     CmsDictionary[] beans = this.manager.deleteByIds(ids);
/*  97 */     for (CmsDictionary bean : beans) {
/*  98 */       log.info("delete CmsDictionary id={}", bean.getId());
/*  99 */       this.cmsLogMng.operating(request, "CmsDictionary.log.delete", "id=" + 
/* 100 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 102 */     return list(queryType, pageNo, request, model);
/*     */   }
/*     */   @RequestMapping({"/dictionary/v_check_value.do"})
/*     */   public void checkDateValue(String value, String type, HttpServletResponse response) throws JSONException {
/* 107 */     JSONObject json = new JSONObject();
/*     */     String pass;
/* 109 */     if (StringUtils.isBlank(value))
/* 110 */       pass = "false";
/*     */     else {
/* 112 */       pass = this.manager.dicDeplicateValue(value, type) ? "true" : "false";
/*     */     }
/* 114 */     json.put("pass", pass);
/* 115 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsDictionary bean, HttpServletRequest request) {
/* 119 */     WebErrors errors = WebErrors.create(request);
/* 120 */     if (this.manager.dicDeplicateValue(bean.getValue(), bean.getType())) {
/* 121 */       errors.addErrorCode("cmsDictionary.value.deplicate", new Object[] { bean.getType(), bean.getValue() });
/*     */     }
/* 123 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 127 */     WebErrors errors = WebErrors.create(request);
/* 128 */     CmsSite site = CmsUtils.getSite(request);
/* 129 */     if (vldExist(id, site.getId(), errors)) {
/* 130 */       return errors;
/*     */     }
/* 132 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(CmsDictionary bean, HttpServletRequest request) {
/* 136 */     WebErrors errors = WebErrors.create(request);
/* 137 */     CmsSite site = CmsUtils.getSite(request);
/* 138 */     CmsDictionary entity = this.manager.findById(bean.getId());
/* 139 */     if (vldExist(bean.getId(), site.getId(), errors)) {
/* 140 */       return errors;
/*     */     }
/* 142 */     if ((!entity.getValue().equals(bean.getValue())) && (!entity.getType().equals(bean.getType())) && 
/* 143 */       (this.manager.dicDeplicateValue(bean.getValue(), bean.getType()))) {
/* 144 */       errors.addErrorCode("cmsDictionary.value.deplicate", new Object[] { bean.getType(), bean.getValue() });
/*     */     }
/*     */ 
/* 147 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 151 */     WebErrors errors = WebErrors.create(request);
/* 152 */     CmsSite site = CmsUtils.getSite(request);
/* 153 */     if (errors.ifEmpty(ids, "ids")) {
/* 154 */       return errors;
/*     */     }
/* 156 */     for (Integer id : ids) {
/* 157 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 159 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 163 */     if (errors.ifNull(id, "id")) {
/* 164 */       return true;
/*     */     }
/* 166 */     CmsDictionary entity = this.manager.findById(id);
/*     */ 
/* 168 */     return errors.ifNotExist(entity, CmsDictionary.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsDictionaryAct
 * JD-Core Version:    0.6.0
 */