/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisition;
/*     */ import com.jeecms.cms.entity.assist.CmsAcquisitionHistory;
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionHistoryMng;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionMng;
/*     */ import com.jeecms.cms.manager.assist.CmsAcquisitionTempMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.ContentTypeMng;
/*     */ import com.jeecms.cms.service.AcquisitionSvc;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
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
/*     */ public class CmsAcquisitionAct
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(CmsAcquisitionAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ContentTypeMng contentTypeMng;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private AcquisitionSvc acquisitionSvc;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAcquisitionMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAcquisitionHistoryMng cmsAcquisitionHistoryMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAcquisitionTempMng cmsAcquisitionTempMng;
/*     */ 
/*  46 */   @RequestMapping({"/acquisition/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  47 */     List list = this.manager.getList(site.getId());
/*  48 */     model.addAttribute("list", list);
/*  49 */     return "acquisition/list"; }
/*     */ 
/*     */   @RequestMapping({"/acquisition/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  54 */     CmsSite site = CmsUtils.getSite(request);
/*     */ 
/*  56 */     List typeList = this.contentTypeMng.getList(false);
/*     */ 
/*  58 */     List topList = this.channelMng.getTopList(site.getId(), true);
/*  59 */     List channelList = Channel.getListForSelect(topList, null, 
/*  60 */       true);
/*  61 */     model.addAttribute("channelList", channelList);
/*  62 */     model.addAttribute("typeList", typeList);
/*  63 */     return "acquisition/add";
/*     */   }
/*     */   @RequestMapping({"/acquisition/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  68 */     WebErrors errors = validateEdit(id, request);
/*  69 */     if (errors.hasErrors()) {
/*  70 */       return errors.showErrorPage(model);
/*     */     }
/*  72 */     CmsSite site = CmsUtils.getSite(request);
/*     */ 
/*  74 */     List typeList = this.contentTypeMng.getList(false);
/*     */ 
/*  76 */     List topList = this.channelMng.getTopList(site.getId(), true);
/*  77 */     List channelList = Channel.getListForSelect(topList, null, 
/*  78 */       true);
/*  79 */     model.addAttribute("channelList", channelList);
/*  80 */     model.addAttribute("typeList", typeList);
/*  81 */     model.addAttribute("cmsAcquisition", this.manager.findById(id));
/*  82 */     return "acquisition/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_save.do"})
/*     */   public String save(CmsAcquisition bean, Integer channelId, Integer typeId, HttpServletRequest request, ModelMap model) {
/*  88 */     WebErrors errors = validateSave(bean, request);
/*  89 */     if (errors.hasErrors()) {
/*  90 */       return errors.showErrorPage(model);
/*     */     }
/*  92 */     Integer siteId = CmsUtils.getSiteId(request);
/*  93 */     Integer userId = CmsUtils.getUserId(request);
/*  94 */     bean = this.manager.save(bean, channelId, typeId, userId, siteId);
/*  95 */     log.info("save CmsAcquisition id={}", bean.getId());
/*  96 */     this.cmsLogMng.operating(request, "cmsAcquisition.log.save", "id=" + 
/*  97 */       bean.getId() + ";name=" + bean.getName());
/*  98 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_update.do"})
/*     */   public String update(CmsAcquisition bean, Integer channelId, Integer typeId, HttpServletRequest request, ModelMap model) {
/* 104 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 105 */     if (errors.hasErrors()) {
/* 106 */       return errors.showErrorPage(model);
/*     */     }
/* 108 */     bean = this.manager.update(bean, channelId, typeId);
/* 109 */     log.info("update CmsAcquisition id={}.", bean.getId());
/* 110 */     this.cmsLogMng.operating(request, "cmsAcquisition.log.update", "id=" + 
/* 111 */       bean.getId() + ";name=" + bean.getName());
/* 112 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_delete.do"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, ModelMap model) {
/* 118 */     WebErrors errors = validateDelete(ids, request);
/* 119 */     if (errors.hasErrors()) {
/* 120 */       return errors.showErrorPage(model);
/*     */     }
/* 122 */     CmsAcquisition[] beans = this.manager.deleteByIds(ids);
/* 123 */     for (CmsAcquisition bean : beans) {
/* 124 */       log.info("delete CmsAcquisition id={}", bean.getId());
/* 125 */       this.cmsLogMng.operating(request, "cmsAcquisition.log.delete", "id=" + 
/* 126 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 128 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_start.do"})
/*     */   public String start(Integer[] ids, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 134 */     Integer siteId = CmsUtils.getSiteId(request);
/* 135 */     WebErrors errors = validateStart(ids, request);
/* 136 */     if (errors.hasErrors()) {
/* 137 */       return errors.showErrorPage(model);
/*     */     }
/* 139 */     Integer queueNum = this.manager.hasStarted(siteId);
/* 140 */     if (queueNum.intValue() == 0) {
/* 141 */       this.acquisitionSvc.start(ids[0]);
/*     */     }
/* 143 */     this.manager.addToQueue(ids, queueNum);
/* 144 */     log.info("start CmsAcquisition ids={}", Arrays.toString(ids));
/* 145 */     return "acquisition/progress";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_end.do"})
/*     */   public String end(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 151 */     WebErrors errors = WebErrors.create(request);
/* 152 */     Integer siteId = CmsUtils.getSiteId(request);
/* 153 */     if (vldExist(id, siteId, errors)) {
/* 154 */       return errors.showErrorPage(model);
/*     */     }
/* 156 */     this.manager.end(id);
/* 157 */     CmsAcquisition acqu = this.manager.popAcquFromQueue(siteId);
/* 158 */     if (acqu != null) {
/* 159 */       Integer acquId = acqu.getId();
/* 160 */       this.acquisitionSvc.start(acquId);
/*     */     }
/* 162 */     log.info("end CmsAcquisition id={}", id);
/* 163 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_pause.do"})
/*     */   public String pause(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 169 */     WebErrors errors = WebErrors.create(request);
/* 170 */     Integer siteId = CmsUtils.getSiteId(request);
/* 171 */     if (vldExist(id, siteId, errors)) {
/* 172 */       return errors.showErrorPage(model);
/*     */     }
/* 174 */     this.manager.pause(id);
/* 175 */     log.info("pause CmsAcquisition id={}", id);
/* 176 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_cancel.do"})
/*     */   public String cancel(Integer id, Integer sortId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 183 */     WebErrors errors = WebErrors.create(request);
/* 184 */     Integer siteId = CmsUtils.getSiteId(request);
/* 185 */     if (vldExist(id, siteId, errors)) {
/* 186 */       return errors.showErrorPage(model);
/*     */     }
/* 188 */     this.manager.cancel(siteId, id);
/* 189 */     log.info("cancel CmsAcquisition id={}", id);
/* 190 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/v_check_complete.do"})
/*     */   public void checkComplete(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 196 */     JSONObject json = new JSONObject();
/* 197 */     CmsSite site = CmsUtils.getSite(request);
/* 198 */     Integer siteId = site.getId();
/* 199 */     CmsAcquisition acqu = this.manager.getStarted(siteId);
/* 200 */     json.put("completed", acqu == null);
/* 201 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/v_progress_data.do"})
/*     */   public String progressData(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 207 */     CmsSite site = CmsUtils.getSite(request);
/* 208 */     Integer siteId = site.getId();
/* 209 */     CmsAcquisition acqu = this.manager.getStarted(siteId);
/* 210 */     List list = this.cmsAcquisitionTempMng.getList(siteId);
/* 211 */     model.put("percent", this.cmsAcquisitionTempMng.getPercent(siteId));
/* 212 */     model.put("acqu", acqu);
/* 213 */     model.put("list", list);
/* 214 */     return "acquisition/progress_data";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/v_progress.do"})
/*     */   public String progress(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 220 */     CmsSite site = CmsUtils.getSite(request);
/* 221 */     Integer siteId = site.getId();
/* 222 */     CmsAcquisition acqu = this.manager.getStarted(siteId);
/* 223 */     if (acqu == null) {
/* 224 */       this.cmsAcquisitionTempMng.clear(siteId);
/*     */     }
/* 226 */     return "acquisition/progress";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/v_history.do"})
/*     */   public String history(Integer acquId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 233 */     CmsSite site = CmsUtils.getSite(request);
/* 234 */     Integer siteId = site.getId();
/* 235 */     Pagination pagination = this.cmsAcquisitionHistoryMng.getPage(siteId, 
/* 236 */       acquId, Integer.valueOf(SimplePage.cpn(pageNo)), Integer.valueOf(CookieUtils.getPageSize(request)));
/* 237 */     model.addAttribute("pagination", pagination);
/* 238 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/* 239 */     return "acquisition/history";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/acquisition/o_delete_history.do"})
/*     */   public String deleteHistory(Integer[] ids, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 246 */     WebErrors errors = validateHistoryDelete(ids, request);
/* 247 */     if (errors.hasErrors()) {
/* 248 */       return errors.showErrorPage(model);
/*     */     }
/* 250 */     CmsAcquisitionHistory[] beans = this.cmsAcquisitionHistoryMng
/* 251 */       .deleteByIds(ids);
/* 252 */     for (CmsAcquisitionHistory bean : beans) {
/* 253 */       log.info("delete CmsAcquisitionHistory id={}", bean.getId());
/* 254 */       this.cmsLogMng.operating(request, "cmsAcquisitionHistory.log.delete", 
/* 255 */         "id=" + bean.getId() + ";name=" + bean.getTitle());
/*     */     }
/* 257 */     return history(null, pageNo, request, response, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsAcquisition bean, HttpServletRequest request)
/*     */   {
/* 262 */     WebErrors errors = WebErrors.create(request);
/* 263 */     CmsSite site = CmsUtils.getSite(request);
/* 264 */     bean.setSite(site);
/* 265 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 269 */     WebErrors errors = WebErrors.create(request);
/* 270 */     CmsSite site = CmsUtils.getSite(request);
/* 271 */     if (vldExist(id, site.getId(), errors)) {
/* 272 */       return errors;
/*     */     }
/* 274 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 278 */     WebErrors errors = WebErrors.create(request);
/* 279 */     CmsSite site = CmsUtils.getSite(request);
/* 280 */     if (vldExist(id, site.getId(), errors)) {
/* 281 */       return errors;
/*     */     }
/* 283 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 287 */     WebErrors errors = WebErrors.create(request);
/* 288 */     CmsSite site = CmsUtils.getSite(request);
/* 289 */     if (errors.ifEmpty(ids, "ids")) {
/* 290 */       return errors;
/*     */     }
/* 292 */     for (Integer id : ids) {
/* 293 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 295 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateStart(Integer[] ids, HttpServletRequest request) {
/* 299 */     WebErrors errors = WebErrors.create(request);
/* 300 */     CmsSite site = CmsUtils.getSite(request);
/* 301 */     if (errors.ifEmpty(ids, "ids")) {
/* 302 */       return errors;
/*     */     }
/* 304 */     for (Integer id : ids) {
/* 305 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 307 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors)
/*     */   {
/* 313 */     if (errors.ifNull(id, "id")) {
/* 314 */       return true;
/*     */     }
/* 316 */     CmsAcquisition entity = this.manager.findById(id);
/* 317 */     if (errors.ifNotExist(entity, CmsAcquisition.class, id)) {
/* 318 */       return true;
/*     */     }
/* 320 */     if (!entity.getSite().getId().equals(siteId)) {
/* 321 */       errors.notInSite(CmsAcquisition.class, id);
/* 322 */       return true;
/*     */     }
/* 324 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validateHistoryDelete(Integer[] ids, HttpServletRequest request)
/*     */   {
/* 329 */     WebErrors errors = WebErrors.create(request);
/* 330 */     CmsSite site = CmsUtils.getSite(request);
/* 331 */     if (errors.ifEmpty(ids, "ids")) {
/* 332 */       return errors;
/*     */     }
/* 334 */     for (Integer id : ids) {
/* 335 */       vldHistoryExist(id, site.getId(), errors);
/*     */     }
/* 337 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldHistoryExist(Integer id, Integer siteId, WebErrors errors) {
/* 341 */     if (errors.ifNull(id, "id")) {
/* 342 */       return true;
/*     */     }
/* 344 */     CmsAcquisitionHistory entity = this.cmsAcquisitionHistoryMng.findById(id);
/* 345 */     if (errors.ifNotExist(entity, CmsAcquisitionHistory.class, id)) {
/* 346 */       return true;
/*     */     }
/* 348 */     if (!entity.getAcquisition().getSite().getId().equals(siteId)) {
/* 349 */       errors.notInSite(CmsAcquisitionHistory.class, id);
/* 350 */       return true;
/*     */     }
/* 352 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsAcquisitionAct
 * JD-Core Version:    0.6.0
 */