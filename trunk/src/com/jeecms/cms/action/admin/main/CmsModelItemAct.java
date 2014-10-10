/*     */ package com.jeecms.cms.action.admin.main;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsModel;
/*     */ import com.jeecms.cms.entity.main.CmsModelItem;
/*     */ import com.jeecms.cms.manager.main.CmsModelItemMng;
/*     */ import com.jeecms.cms.manager.main.CmsModelMng;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.web.springmvc.MessageResolver;
/*     */ import java.util.ArrayList;
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
/*     */ public class CmsModelItemAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(CmsModelItemAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelMng cmsModelMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsModelItemMng manager;
/*     */ 
/*  33 */   @RequestMapping({"/item/v_list.do"})
/*     */   public String list(Integer modelId, Boolean isChannel, HttpServletRequest request, ModelMap model) { CmsModel m = this.cmsModelMng.findById(modelId);
/*  34 */     List list = this.manager.getList(modelId, isChannel.booleanValue(), true);
/*  35 */     model.addAttribute("model", m);
/*  36 */     model.addAttribute("fieldList", getFieldList(list));
/*  37 */     model.addAttribute("modelId", modelId);
/*  38 */     model.addAttribute("isChannel", isChannel);
/*  39 */     model.addAttribute("list", list);
/*  40 */     if (isChannel.booleanValue()) {
/*  41 */       return "item/list_channel";
/*     */     }
/*  43 */     return "item/list_content"; }
/*     */ 
/*     */   @RequestMapping({"/item/v_add.do"})
/*     */   public String add(Integer modelId, Boolean isChannel, ModelMap model)
/*     */   {
/*  49 */     CmsModel m = this.cmsModelMng.findById(modelId);
/*  50 */     model.addAttribute("model", m);
/*  51 */     model.addAttribute("modelId", modelId);
/*  52 */     model.addAttribute("isChannel", isChannel);
/*  53 */     return "item/add";
/*     */   }
/*     */   @RequestMapping({"/item/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  58 */     WebErrors errors = validateEdit(id, request);
/*  59 */     if (errors.hasErrors()) {
/*  60 */       return errors.showErrorPage(model);
/*     */     }
/*  62 */     CmsModelItem item = this.manager.findById(id);
/*  63 */     model.addAttribute("cmsModelItem", item);
/*  64 */     return "item/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/item/o_priority.do"})
/*     */   public String priority(Integer[] wids, Integer[] priority, String[] label, Boolean[] single, Boolean[] display, Integer modelId, Boolean isChannel, HttpServletRequest request, ModelMap model)
/*     */   {
/*  71 */     if ((wids != null) && (wids.length > 0)) {
/*  72 */       this.manager.updatePriority(wids, priority, label, single, display);
/*     */     }
/*  74 */     model.addAttribute("message", "global.success");
/*  75 */     return list(modelId, isChannel, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/item/o_save_list.do"})
/*     */   public String saveList(Integer modelId, Boolean isChannel, String[] fields, String[] labels, Integer[] dataTypes, Integer[] prioritys, Boolean[] singles, Boolean[] displays, HttpServletRequest request, ModelMap model)
/*     */   {
/*  83 */     CmsModel m = this.cmsModelMng.findById(modelId);
/*  84 */     List itemList = getItems(m, isChannel.booleanValue(), fields, labels, 
/*  85 */       dataTypes, prioritys, singles, displays);
/*  86 */     this.manager.saveList(itemList);
/*  87 */     log.info("save CmsModelItem count={}", Integer.valueOf(itemList.size()));
/*  88 */     model.addAttribute("modelId", modelId);
/*  89 */     model.addAttribute("isChannel", isChannel);
/*  90 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/item/o_save.do"})
/*     */   public String save(CmsModelItem bean, Integer modelId, Boolean isChannel, HttpServletRequest request, ModelMap model) {
/*  96 */     WebErrors errors = validateSave(bean, modelId, request);
/*  97 */     if (errors.hasErrors()) {
/*  98 */       return errors.showErrorPage(model);
/*     */     }
/* 100 */     bean = this.manager.save(bean, modelId);
/* 101 */     log.info("update CmsModelItem id={}.", bean.getId());
/* 102 */     model.addAttribute("modelId", bean.getModel().getId());
/* 103 */     model.addAttribute("isChannel", bean.getChannel());
/* 104 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/item/o_update.do"})
/*     */   public String update(CmsModelItem bean, HttpServletRequest request, ModelMap model) {
/* 110 */     WebErrors errors = validateUpdate(bean.getId(), bean, request);
/* 111 */     if (errors.hasErrors()) {
/* 112 */       return errors.showErrorPage(model);
/*     */     }
/* 114 */     bean = this.manager.update(bean);
/* 115 */     log.info("update CmsModelItem id={}.", bean.getId());
/* 116 */     model.addAttribute("modelId", bean.getModel().getId());
/* 117 */     model.addAttribute("isChannel", bean.getChannel());
/* 118 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/item/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer modelId, Boolean isChannel, HttpServletRequest request, ModelMap model) {
/* 124 */     WebErrors errors = validateDelete(ids, request);
/* 125 */     if (errors.hasErrors()) {
/* 126 */       return errors.showErrorPage(model);
/*     */     }
/* 128 */     CmsModelItem[] beans = this.manager.deleteByIds(ids);
/* 129 */     for (CmsModelItem bean : beans) {
/* 130 */       log.info("delete CmsModelItem id={}", bean.getId());
/*     */     }
/* 132 */     model.addAttribute("modelId", modelId);
/* 133 */     model.addAttribute("isChannel", isChannel);
/* 134 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   private List<String> getFieldList(List<CmsModelItem> items) {
/* 138 */     List list = new ArrayList(items.size());
/* 139 */     for (CmsModelItem item : items) {
/* 140 */       list.add(item.getField());
/*     */     }
/* 142 */     return list;
/*     */   }
/*     */ 
/*     */   private List<CmsModelItem> getItems(CmsModel model, boolean isChannel, String[] fields, String[] labels, Integer[] dataTypes, Integer[] prioritys, Boolean[] singles, Boolean[] displays)
/*     */   {
/* 148 */     List list = new ArrayList();
/*     */ 
/* 150 */     int i = 0; for (int len = fields.length; i < len; i++) {
/* 151 */       if (!StringUtils.isBlank(fields[i])) {
/* 152 */         CmsModelItem item = new CmsModelItem();
/* 153 */         item.setCustom(Boolean.valueOf(false));
/* 154 */         item.setModel(model);
/* 155 */         item.setChannel(Boolean.valueOf(isChannel));
/*     */ 
/* 157 */         item.setField(fields[i]);
/* 158 */         item.setLabel(labels[i]);
/* 159 */         item.setPriority(prioritys[i]);
/* 160 */         item.setDataType(dataTypes[i]);
/* 161 */         item.setSingle(singles[i]);
/* 162 */         item.setDisplay(displays[i]);
/*     */ 
/* 164 */         list.add(item);
/*     */       }
/*     */     }
/* 167 */     return list;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsModelItem bean, Integer modelId, HttpServletRequest request)
/*     */   {
/* 172 */     WebErrors errors = WebErrors.create(request);
/* 173 */     if (!StringUtils.isBlank(bean.getOptValue())) {
/* 174 */       bean.setOptValue(replaceLocaleSplit(bean.getOptValue(), request));
/*     */     }
/* 176 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 180 */     WebErrors errors = WebErrors.create(request);
/* 181 */     if (vldExist(id, errors)) {
/* 182 */       return errors;
/*     */     }
/* 184 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, CmsModelItem bean, HttpServletRequest request)
/*     */   {
/* 189 */     WebErrors errors = WebErrors.create(request);
/* 190 */     if (!StringUtils.isBlank(bean.getOptValue())) {
/* 191 */       bean.setOptValue(replaceLocaleSplit(bean.getOptValue(), request));
/*     */     }
/* 193 */     if (vldExist(id, errors)) {
/* 194 */       return errors;
/*     */     }
/* 196 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 200 */     WebErrors errors = WebErrors.create(request);
/* 201 */     errors.ifEmpty(ids, "ids");
/* 202 */     for (Integer id : ids) {
/* 203 */       vldExist(id, errors);
/*     */     }
/* 205 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, WebErrors errors) {
/* 209 */     if (errors.ifNull(id, "id")) {
/* 210 */       return true;
/*     */     }
/* 212 */     CmsModelItem entity = this.manager.findById(id);
/*     */ 
/* 214 */     return errors.ifNotExist(entity, CmsModelItem.class, id);
/*     */   }
/*     */ 
/*     */   private String replaceLocaleSplit(String s, HttpServletRequest request)
/*     */   {
/* 220 */     String split = MessageResolver.getMessage(request, 
/* 221 */       "cmsModelItem.optValue.split", new Object[0]);
/*     */ 
/* 222 */     return StringUtils.replace(s, split, ",");
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.main.CmsModelItemAct
 * JD-Core Version:    0.6.0
 */