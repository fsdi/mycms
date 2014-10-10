/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsTopic;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.manager.main.ChannelMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsTopicMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.springmvc.MessageResolver;
/*     */ import com.jeecms.core.tpl.TplManager;
/*     */ import com.jeecms.core.web.CoreUtils;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONArray;
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
/*     */ public class CmsTopicAct
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(CmsTopicAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private TplManager tplManager;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelMng channelMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsTopicMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private CmsFileMng fileMng;
/*     */ 
/*  48 */   @RequestMapping({"/topic/v_list.do"})
/*     */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Pagination pagination = this.manager.getPage(SimplePage.cpn(pageNo), 
/*  49 */       CookieUtils.getPageSize(request));
/*  50 */     model.addAttribute("pagination", pagination);
/*  51 */     return "topic/list"; }
/*     */ 
/*     */   @RequestMapping({"/topic/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  56 */     CmsSite site = CmsUtils.getSite(request);
/*     */ 
/*  58 */     List tplList = getTplList(request, site, null);
/*     */ 
/*  60 */     List topList = this.channelMng.getTopList(site.getId(), true);
/*  61 */     List channelList = Channel.getListForSelect(topList, null, 
/*  62 */       true);
/*  63 */     model.addAttribute("tplList", tplList);
/*  64 */     model.addAttribute("channelList", channelList);
/*  65 */     return "topic/add";
/*     */   }
/*     */   @RequestMapping({"/topic/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  70 */     WebErrors errors = validateEdit(id, request);
/*  71 */     if (errors.hasErrors()) {
/*  72 */       return errors.showErrorPage(model);
/*     */     }
/*  74 */     CmsSite site = CmsUtils.getSite(request);
/*  75 */     CmsTopic topic = this.manager.findById(id);
/*     */ 
/*  77 */     List tplList = getTplList(request, site, topic.getTplContent());
/*     */ 
/*  80 */     Channel channel = topic.getChannel();
/*     */     Integer siteId;
/*  81 */     if (channel != null)
/*  82 */       siteId = channel.getSite().getId();
/*     */     else {
/*  84 */       siteId = site.getId();
/*     */     }
/*  86 */     List topList = this.channelMng.getTopList(siteId, true);
/*  87 */     List channelList = Channel.getListForSelect(topList, null, 
/*  88 */       true);
/*  89 */     model.addAttribute("tplList", tplList);
/*  90 */     model.addAttribute("channelList", channelList);
/*  91 */     model.addAttribute("cmsTopic", topic);
/*  92 */     return "topic/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/topic/o_save.do"})
/*     */   public String save(CmsTopic bean, Integer channelId, HttpServletRequest request, ModelMap model) {
/*  98 */     WebErrors errors = validateSave(bean, request);
/*  99 */     if (errors.hasErrors()) {
/* 100 */       return errors.showErrorPage(model);
/*     */     }
/* 102 */     CmsSite site = CmsUtils.getSite(request);
/* 103 */     if (!StringUtils.isBlank(bean.getTplContent())) {
/* 104 */       bean.setTplContent(site.getTplPath() + bean.getTplContent());
/*     */     }
/* 106 */     bean = this.manager.save(bean, channelId);
/* 107 */     this.fileMng.updateFileByPath(bean.getContentImg(), Boolean.valueOf(true), null);
/* 108 */     this.fileMng.updateFileByPath(bean.getTitleImg(), Boolean.valueOf(true), null);
/* 109 */     log.info("save CmsTopic id={}", bean.getId());
/* 110 */     this.cmsLogMng.operating(request, "cmsTopic.log.save", "id=" + bean.getId() + 
/* 111 */       ";name=" + bean.getName());
/* 112 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/topic/o_update.do"})
/*     */   public String update(CmsTopic bean, Integer channelId, String oldTitleImg, String oldContentImg, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 119 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 120 */     if (errors.hasErrors()) {
/* 121 */       return errors.showErrorPage(model);
/*     */     }
/* 123 */     CmsSite site = CmsUtils.getSite(request);
/* 124 */     if (!StringUtils.isBlank(bean.getTplContent())) {
/* 125 */       bean.setTplContent(site.getTplPath() + bean.getTplContent());
/*     */     }
/* 127 */     bean = this.manager.update(bean, channelId);
/*     */ 
/* 129 */     this.fileMng.updateFileByPath(oldTitleImg, Boolean.valueOf(false), null);
/*     */ 
/* 131 */     this.fileMng.updateFileByPath(oldContentImg, Boolean.valueOf(false), null);
/* 132 */     this.fileMng.updateFileByPath(bean.getContentImg(), Boolean.valueOf(true), null);
/* 133 */     this.fileMng.updateFileByPath(bean.getTitleImg(), Boolean.valueOf(true), null);
/* 134 */     log.info("update CmsTopic id={}.", bean.getId());
/* 135 */     this.cmsLogMng.operating(request, "cmsTopic.log.update", "id=" + 
/* 136 */       bean.getId() + ";name=" + bean.getName());
/* 137 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/topic/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 143 */     WebErrors errors = validateDelete(ids, request);
/* 144 */     if (errors.hasErrors()) {
/* 145 */       return errors.showErrorPage(model);
/*     */     }
/* 147 */     CmsTopic[] beans = this.manager.deleteByIds(ids);
/* 148 */     for (CmsTopic bean : beans) {
/* 149 */       this.fileMng.updateFileByPath(bean.getContentImg(), Boolean.valueOf(false), null);
/* 150 */       this.fileMng.updateFileByPath(bean.getTitleImg(), Boolean.valueOf(false), null);
/* 151 */       log.info("delete CmsTopic id={}", bean.getId());
/* 152 */       this.cmsLogMng.operating(request, "cmsTopic.log.delete", "id=" + 
/* 153 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 155 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/topic/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 161 */     WebErrors errors = validatePriority(wids, priority, request);
/* 162 */     if (errors.hasErrors()) {
/* 163 */       return errors.showErrorPage(model);
/*     */     }
/* 165 */     this.manager.updatePriority(wids, priority);
/* 166 */     model.addAttribute("message", "global.success");
/* 167 */     return list(pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/topic/by_channel.do"})
/*     */   public void topicsByChannel(Integer channelId, HttpServletResponse response) throws JSONException {
/* 173 */     JSONArray arr = new JSONArray();
/* 174 */     if (channelId != null) {
/* 175 */       List<CmsTopic> list = this.manager.getListByChannel(channelId);
/*     */ 
/* 177 */       for (CmsTopic t : list) {
/* 178 */         JSONObject o = new JSONObject();
/* 179 */         o.put("id", t.getId());
/* 180 */         o.put("name", t.getName());
/* 181 */         arr.put(o);
/*     */       }
/*     */     }
/* 184 */     ResponseUtils.renderJson(response, arr.toString());
/*     */   }
/*     */ 
/*     */   private List<String> getTplList(HttpServletRequest request, CmsSite site, String tpl)
/*     */   {
/* 189 */     List tplList = this.tplManager.getNameListByPrefix(
/* 190 */       site.getSolutionPath() + 
/* 191 */       "/" + "topic" + "/");
/* 192 */     String tplIndex = MessageResolver.getMessage(request, "tpl.topicIndex", new Object[0]);
/* 193 */     String tplDefault = MessageResolver.getMessage(request, "tpl.topicDefault", new Object[0]);
/* 194 */     tplList = CoreUtils.tplTrim(tplList, site.getTplPath(), tpl, new String[] { tplIndex, 
/* 195 */       tplDefault });
/* 196 */     return tplList;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsTopic bean, HttpServletRequest request) {
/* 200 */     WebErrors errors = WebErrors.create(request);
/* 201 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 205 */     WebErrors errors = WebErrors.create(request);
/* 206 */     if (vldExist(id, errors)) {
/* 207 */       return errors;
/*     */     }
/* 209 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 213 */     WebErrors errors = WebErrors.create(request);
/* 214 */     if (vldExist(id, errors)) {
/* 215 */       return errors;
/*     */     }
/* 217 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 221 */     WebErrors errors = WebErrors.create(request);
/* 222 */     errors.ifEmpty(ids, "ids");
/* 223 */     for (Integer id : ids) {
/* 224 */       vldExist(id, errors);
/*     */     }
/* 226 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validatePriority(Integer[] wids, Integer[] priority, HttpServletRequest request)
/*     */   {
/* 231 */     WebErrors errors = WebErrors.create(request);
/* 232 */     if (errors.ifEmpty(wids, "wids")) {
/* 233 */       return errors;
/*     */     }
/* 235 */     if (errors.ifEmpty(priority, "priority")) {
/* 236 */       return errors;
/*     */     }
/* 238 */     if (wids.length != priority.length) {
/* 239 */       errors.addErrorString("wids length not equals priority length");
/* 240 */       return errors;
/*     */     }
/* 242 */     int i = 0; for (int len = wids.length; i < len; i++) {
/* 243 */       if (vldExist(wids[i], errors)) {
/* 244 */         return errors;
/*     */       }
/* 246 */       if (priority[i] == null) {
/* 247 */         priority[i] = Integer.valueOf(0);
/*     */       }
/*     */     }
/* 250 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 254 */     if (errors.ifNull(id, "id")) {
/* 255 */       return true;
/*     */     }
/* 257 */     CmsTopic entity = this.manager.findById(id);
/*     */ 
/* 259 */     return errors.ifNotExist(entity, CmsTopic.class, id);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsTopicAct
 * JD-Core Version:    0.6.0
 */