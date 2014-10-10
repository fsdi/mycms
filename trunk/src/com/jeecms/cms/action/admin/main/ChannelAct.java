/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.ChannelExt;
/*     */ import com.jeecms.cms.entity.main.ChannelTxt;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsGroupMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelItemMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.core.tpl.TplManager;
/*     */ import com.jeecms.core.web.CoreUtils;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ChannelAct
/*     */ {
/*  42 */   private static final Logger log = LoggerFactory.getLogger(ChannelAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelMng cmsModelMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelItemMng cmsModelItemMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsGroupMng cmsGroupMng;
/*     */ 
/*     */   @Autowired
/*     */   private TplManager tplManager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng manager;
/*     */ 
/*  46 */   @RequestMapping({"/channel/v_left.do"})
/*     */   public String left() { return "channel/left"; }
/*     */ 
/*     */   @RequestMapping({"/channel/v_tree.do"})
/*     */   public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  52 */     log.debug("tree path={}", root);
/*     */     boolean isRoot;
/*  55 */     if ((StringUtils.isBlank(root)) || ("source".equals(root)))
/*  56 */       isRoot = true;
/*     */     else {
/*  58 */       isRoot = false;
/*     */     }
/*  60 */     model.addAttribute("isRoot", Boolean.valueOf(isRoot));
/*  61 */     WebErrors errors = validateTree(root, request);
/*  62 */     if (errors.hasErrors()) {
/*  63 */       log.error((String)errors.getErrors().get(0));
/*  64 */       ResponseUtils.renderJson(response, "[]");
/*  65 */       return null;
/*     */     }
/*     */     List list;
/*  68 */     if (isRoot) {
/*  69 */       CmsSite site = CmsUtils.getSite(request);
/*  70 */       list = this.manager.getTopList(site.getId(), false);
/*     */     } else {
/*  72 */       Integer rootId = Integer.valueOf(root);
/*  73 */       list = this.manager.getChildList(rootId, false);
/*     */     }
/*  75 */     model.addAttribute("list", list);
/*  76 */     response.setHeader("Cache-Control", "no-cache");
/*  77 */     response.setContentType("text/json;charset=UTF-8");
/*  78 */     return "channel/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_list.do"})
/*     */   public String list(Integer root, HttpServletRequest request, ModelMap model)
/*     */   {
/*     */     List list;
/*  84 */     if (root == null)
/*  85 */       list = this.manager.getTopList(CmsUtils.getSiteId(request), false);
/*     */     else {
/*  87 */       list = this.manager.getChildList(root, false);
/*     */     }
/*  89 */     model.addAttribute("modelList", this.cmsModelMng.getList(false, null));
/*  90 */     model.addAttribute("root", root);
/*  91 */     model.addAttribute("list", list);
/*  92 */     return "channel/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_add.do"})
/*     */   public String add(Integer root, Integer modelId, HttpServletRequest request, ModelMap model) {
/*  98 */     CmsSite site = CmsUtils.getSite(request);
/*  99 */     Channel parent = null;
/* 100 */     if (root != null) {
/* 101 */       parent = this.manager.findById(root);
/* 102 */       model.addAttribute("parent", parent);
/* 103 */       model.addAttribute("root", root);
/*     */     }
/*     */ 
/* 106 */     CmsModel m = this.cmsModelMng.findById(modelId);
/*     */ 
/* 108 */     List channelTplList = getTplChannel(site, m, null);
/*     */ 
/* 110 */     List contentTplList = getTplContent(site, m, null);
/*     */ 
/* 112 */     List<CmsModel> models = this.cmsModelMng.getList(false, Boolean.valueOf(true));
/* 113 */     Map modelTplMap = new HashMap();
/* 114 */     for (CmsModel tempModel : models) {
/* 115 */       List modelTplList = getTplContent(site, tempModel, null);
/* 116 */       modelTplMap.put(tempModel.getId().toString(), modelTplList);
/*     */     }
/*     */ 
/* 119 */     List itemList = this.cmsModelItemMng.getList(modelId, true, 
/* 120 */       false);
/* 121 */     List groupList = this.cmsGroupMng.getList();
/*     */ 
/* 123 */     List viewGroups = groupList;
/*     */     Collection contriGroups;
/* 126 */     if (parent != null)
/* 127 */       contriGroups = parent.getContriGroups();
/*     */     else
/* 129 */       contriGroups = groupList;
/*     */     Collection users;
/* 133 */     if (parent != null)
/* 134 */       users = parent.getUsers();
/*     */     else {
/* 136 */       users = this.cmsUserMng.getAdminList(site.getId(), Boolean.valueOf(false), Boolean.valueOf(false), null);
/*     */     }
/* 138 */     model.addAttribute("channelTplList", channelTplList);
/* 139 */     model.addAttribute("contentTplList", contentTplList);
/* 140 */     model.addAttribute("itemList", itemList);
/* 141 */     model.addAttribute("viewGroups", viewGroups);
/* 142 */     model.addAttribute("contriGroups", contriGroups);
/* 143 */     model.addAttribute("contriGroupIds", CmsGroup.fetchIds(contriGroups));
/* 144 */     model.addAttribute("users", users);
/* 145 */     model.addAttribute("userIds", CmsUser.fetchIds(users));
/* 146 */     model.addAttribute("model", m);
/* 147 */     model.addAttribute("models", models);
/* 148 */     model.addAttribute("modelTplMap", modelTplMap);
/* 149 */     return "channel/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/v_edit.do"})
/*     */   public String edit(Integer id, Integer root, HttpServletRequest request, ModelMap model) {
/* 155 */     CmsSite site = CmsUtils.getSite(request);
/* 156 */     WebErrors errors = validateEdit(id, request);
/* 157 */     if (errors.hasErrors()) {
/* 158 */       return errors.showErrorPage(model);
/*     */     }
/* 160 */     if (root != null) {
/* 161 */       model.addAttribute("root", root);
/*     */     }
/*     */ 
/* 164 */     Channel channel = this.manager.findById(id);
/*     */ 
/* 166 */     int tplPathLength = site.getTplPath().length();
/* 167 */     String tplChannel = channel.getTplChannel();
/* 168 */     if (!StringUtils.isBlank(tplChannel)) {
/* 169 */       tplChannel = tplChannel.substring(tplPathLength);
/*     */     }
/* 171 */     String tplContent = channel.getTplContent();
/* 172 */     if (!StringUtils.isBlank(tplContent)) {
/* 173 */       tplContent = tplContent.substring(tplPathLength);
/*     */     }
/*     */ 
/* 176 */     Channel parent = channel.getParent();
/*     */ 
/* 178 */     CmsModel m = channel.getModel();
/*     */ 
/* 180 */     List topList = this.manager.getTopList(site.getId(), false);
/* 181 */     List channelList = Channel.getListForSelect(topList, null, 
/* 182 */       channel, false);
/*     */ 
/* 185 */     List channelTplList = getTplChannel(site, m, 
/* 186 */       channel.getTplChannel());
/*     */ 
/* 188 */     List contentTplList = getTplContent(site, m, 
/* 189 */       channel.getTplContent());
/*     */ 
/* 191 */     List<CmsModel> models = this.cmsModelMng.getList(false, Boolean.valueOf(true));
/* 192 */     Map modelTplMap = new HashMap();
/* 193 */     for (CmsModel tempModel : models) {
/* 194 */       List modelTplList = getTplContent(site, tempModel, null);
/* 195 */       modelTplMap.put(tempModel.getId().toString(), modelTplList);
/*     */     }
/* 197 */     List groupList = this.cmsGroupMng.getList();
/*     */ 
/* 199 */     List itemList = this.cmsModelItemMng.getList(m.getId(), true, 
/* 200 */       false);
/*     */ 
/* 202 */     List viewGroups = groupList;
/* 203 */     Integer[] viewGroupIds = CmsGroup.fetchIds(channel.getViewGroups());
/*     */     Collection contriGroups;
/* 206 */     if (parent != null)
/* 207 */       contriGroups = parent.getContriGroups();
/*     */     else {
/* 209 */       contriGroups = groupList;
/*     */     }
/*     */ 
/* 212 */     Integer[] contriGroupIds = CmsGroup.fetchIds(channel.getContriGroups());
/*     */     Collection users;
/* 215 */     if (parent != null)
/* 216 */       users = parent.getUsers();
/*     */     else {
/* 218 */       users = this.cmsUserMng.getAdminList(site.getId(), Boolean.valueOf(false), Boolean.valueOf(false), null);
/*     */     }
/*     */ 
/* 221 */     Integer[] userIds = channel.getUserIds();
/* 222 */     model.addAttribute("channelList", channelList);
/* 223 */     model.addAttribute("modelList", this.cmsModelMng.getList(false, null));
/* 224 */     model.addAttribute("tplChannel", tplChannel);
/* 225 */     model.addAttribute("tplContent", tplContent);
/* 226 */     model.addAttribute("channelTplList", channelTplList);
/* 227 */     model.addAttribute("contentTplList", contentTplList);
/* 228 */     model.addAttribute("itemList", itemList);
/* 229 */     model.addAttribute("viewGroups", viewGroups);
/* 230 */     model.addAttribute("viewGroupIds", viewGroupIds);
/* 231 */     model.addAttribute("contriGroups", contriGroups);
/* 232 */     model.addAttribute("contriGroupIds", contriGroupIds);
/* 233 */     model.addAttribute("users", users);
/* 234 */     model.addAttribute("userIds", userIds);
/* 235 */     model.addAttribute("channel", channel);
/* 236 */     model.addAttribute("model", m);
/* 237 */     model.addAttribute("models", models);
/* 238 */     model.addAttribute("modelTplMap", modelTplMap);
/* 239 */     return "channel/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_save.do"})
/*     */   public String save(Integer root, Channel bean, ChannelExt ext, ChannelTxt txt, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer[] userIds, Integer modelId, Integer[] modelIds, String[] tpls, HttpServletRequest request, ModelMap model)
/*     */   {
/* 247 */     WebErrors errors = validateSave(bean, request);
/* 248 */     if (errors.hasErrors()) {
/* 249 */       return errors.showErrorPage(model);
/*     */     }
/* 251 */     CmsSite site = CmsUtils.getSite(request);
/*     */ 
/* 253 */     String tplPath = site.getTplPath();
/* 254 */     if (!StringUtils.isBlank(ext.getTplChannel())) {
/* 255 */       ext.setTplChannel(tplPath + ext.getTplChannel());
/*     */     }
/* 257 */     if (!StringUtils.isBlank(ext.getTplContent())) {
/* 258 */       ext.setTplContent(tplPath + ext.getTplContent());
/*     */     }
/* 260 */     if ((tpls != null) && (tpls.length > 0)) {
/* 261 */       for (int t = 0; t < tpls.length; t++) {
/* 262 */         if (!StringUtils.isBlank(tpls[t])) {
/* 263 */           tpls[t] = (tplPath + tpls[t]);
/*     */         }
/*     */       }
/*     */     }
/* 267 */     bean.setAttr(RequestUtils.getRequestMap(request, "attr_"));
/* 268 */     bean = this.manager.save(bean, ext, txt, viewGroupIds, contriGroupIds, 
/* 269 */       userIds, CmsUtils.getSiteId(request), root, modelId, modelIds, tpls);
/* 270 */     log.info("save Channel id={}, name={}", bean.getId(), bean.getName());
/* 271 */     this.cmsLogMng.operating(request, "channel.log.save", "id=" + bean.getId() + 
/* 272 */       ";title=" + bean.getTitle());
/* 273 */     model.addAttribute("root", root);
/* 274 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_update.do"})
/*     */   public String update(Integer root, Channel bean, ChannelExt ext, ChannelTxt txt, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer[] userIds, Integer parentId, Integer[] modelIds, String[] tpls, HttpServletRequest request, ModelMap model)
/*     */   {
/* 282 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 283 */     if (errors.hasErrors()) {
/* 284 */       return errors.showErrorPage(model);
/*     */     }
/* 286 */     CmsSite site = CmsUtils.getSite(request);
/*     */ 
/* 288 */     String tplPath = site.getTplPath();
/* 289 */     if (!StringUtils.isBlank(ext.getTplChannel())) {
/* 290 */       ext.setTplChannel(tplPath + ext.getTplChannel());
/*     */     }
/* 292 */     if (!StringUtils.isBlank(ext.getTplContent())) {
/* 293 */       ext.setTplContent(tplPath + ext.getTplContent());
/*     */     }
/* 295 */     if ((tpls != null) && (tpls.length > 0)) {
/* 296 */       for (int t = 0; t < tpls.length; t++) {
/* 297 */         if (!StringUtils.isBlank(tpls[t])) {
/* 298 */           tpls[t] = (tplPath + tpls[t]);
/*     */         }
/*     */       }
/*     */     }
/* 302 */     Map attr = RequestUtils.getRequestMap(request, "attr_");
/* 303 */     bean = this.manager.update(bean, ext, txt, viewGroupIds, contriGroupIds, 
/* 304 */       userIds, parentId, attr, modelIds, tpls);
/* 305 */     log.info("update Channel id={}.", bean.getId());
/* 306 */     this.cmsLogMng.operating(request, "channel.log.update", "id=" + bean.getId() + 
/* 307 */       ";name=" + bean.getName());
/* 308 */     return list(root, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_delete.do"})
/*     */   public String delete(Integer root, Integer[] ids, HttpServletRequest request, ModelMap model) {
/* 314 */     WebErrors errors = validateDelete(ids, request);
/* 315 */     if (errors.hasErrors()) {
/* 316 */       return errors.showErrorPage(model);
/*     */     }
/* 318 */     Channel[] beans = this.manager.deleteByIds(ids);
/* 319 */     for (Channel bean : beans) {
/* 320 */       log.info("delete Channel id={}", bean.getId());
/* 321 */       this.cmsLogMng.operating(request, "channel.log.delete", "id=" + 
/* 322 */         bean.getId() + ";title=" + bean.getTitle());
/*     */     }
/* 324 */     return list(root, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/channel/o_priority.do"})
/*     */   public String priority(Integer root, Integer[] wids, Integer[] priority, HttpServletRequest request, ModelMap model) {
/* 330 */     WebErrors errors = validatePriority(wids, priority, request);
/* 331 */     if (errors.hasErrors()) {
/* 332 */       return errors.showErrorPage(model);
/*     */     }
/* 334 */     this.manager.updatePriority(wids, priority);
/* 335 */     model.addAttribute("message", "global.success");
/* 336 */     return list(root, request, model);
/*     */   }
/*     */ 
/*     */   private List<String> getTplChannel(CmsSite site, CmsModel model, String tpl) {
/* 340 */     String sol = site.getSolutionPath();
/* 341 */     List tplList = this.tplManager.getNameListByPrefix(
/* 342 */       model.getTplChannel(sol, false));
/* 343 */     return CoreUtils.tplTrim(tplList, site.getTplPath(), tpl, new String[0]);
/*     */   }
/*     */ 
/*     */   private List<String> getTplContent(CmsSite site, CmsModel model, String tpl) {
/* 347 */     String sol = site.getSolutionPath();
/* 348 */     List tplList = this.tplManager.getNameListByPrefix(
/* 349 */       model.getTplContent(sol, false));
/* 350 */     return CoreUtils.tplTrim(tplList, site.getTplPath(), tpl, new String[0]);
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 354 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 358 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(Channel bean, HttpServletRequest request) {
/* 362 */     WebErrors errors = WebErrors.create(request);
/* 363 */     CmsSite site = CmsUtils.getSite(request);
/* 364 */     bean.setSite(site);
/* 365 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 369 */     WebErrors errors = WebErrors.create(request);
/* 370 */     CmsSite site = CmsUtils.getSite(request);
/* 371 */     if (vldExist(id, site.getId(), errors)) {
/* 372 */       return errors;
/*     */     }
/* 374 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 378 */     WebErrors errors = WebErrors.create(request);
/* 379 */     CmsSite site = CmsUtils.getSite(request);
/* 380 */     if (vldExist(id, site.getId(), errors)) {
/* 381 */       return errors;
/*     */     }
/* 383 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 387 */     WebErrors errors = WebErrors.create(request);
/* 388 */     CmsSite site = CmsUtils.getSite(request);
/* 389 */     errors.ifEmpty(ids, "ids");
/* 390 */     for (Integer id : ids) {
/* 391 */       if (vldExist(id, site.getId(), errors)) {
/* 392 */         return errors;
/*     */       }
/*     */ 
/* 395 */       String code = this.manager.checkDelete(id);
/* 396 */       if (code != null) {
/* 397 */         errors.addErrorCode(code);
/* 398 */         return errors;
/*     */       }
/*     */     }
/* 401 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 405 */     if (errors.ifNull(id, "id")) {
/* 406 */       return true;
/*     */     }
/* 408 */     Channel entity = this.manager.findById(id);
/* 409 */     if (errors.ifNotExist(entity, Channel.class, id)) {
/* 410 */       return true;
/*     */     }
/* 412 */     if (!entity.getSite().getId().equals(siteId)) {
/* 413 */       errors.notInSite(Channel.class, id);
/* 414 */       return true;
/*     */     }
/* 416 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Integer[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 421 */     CmsSite site = CmsUtils.getSite(request);
/* 422 */     WebErrors errors = WebErrors.create(request);
/* 423 */     if (errors.ifEmpty(wids, "wids")) {
/* 424 */       return errors;
/*     */     }
/* 426 */     if (errors.ifEmpty(priority, "priority")) {
/* 427 */       return errors;
/*     */     }
/* 429 */     if (wids.length != priority.length) {
/* 430 */       errors.addErrorString("wids length not equals priority length");
/* 431 */       return errors;
/*     */     }
/* 433 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 434 */       if (vldExist(wids[i], site.getId(), errors)) {
/* 435 */         return errors;
/*     */       }
/* 437 */       if (priority[i] == null) {
/* 438 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 441 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.ChannelAct
 * JD-Core Version:    0.6.0
 */