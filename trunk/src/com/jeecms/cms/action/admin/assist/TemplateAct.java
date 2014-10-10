/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.util.Zipper;
import com.jeecms.common.util.Zipper.FileEntry;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.core.tpl.Tpl;
/*     */ import com.jeecms.core.tpl.TplManager;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class TemplateAct
/*     */ {
/*     */   public static final String TEXT_AREA = "textarea";
/*     */   public static final String EDITOR = "editor";
/*     */   private static final String INVALID_PARAM = "template.invalidParams";
/*  48 */   private static final Logger log = LoggerFactory.getLogger(TemplateAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */   private TplManager tplManager;
/*     */   private CmsResourceMng resourceMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */ 
/*  52 */   @RequestMapping({"/template/v_left.do"})
/*     */   public String left(String path, HttpServletRequest request, ModelMap model) { return "template/left"; }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_tree.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String tree(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  58 */     CmsSite site = CmsUtils.getSite(request);
/*  59 */     String root = RequestUtils.getQueryParam(request, "root");
/*  60 */     log.debug("tree path={}", root);
/*     */ 
/*  62 */     if ((StringUtils.isBlank(root)) || ("source".equals(root))) {
/*  63 */       root = site.getTplPath();
/*  64 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*     */     } else {
/*  66 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  68 */     WebErrors errors = validateTree(root, request);
/*  69 */     if (errors.hasErrors()) {
/*  70 */       log.error((String)errors.getErrors().get(0));
/*  71 */       ResponseUtils.renderJson(response, "[]");
/*  72 */       return null;
/*     */     }
/*  74 */     List tplList = this.tplManager.getChild(root);
/*  75 */     model.addAttribute("tplList", tplList);
/*  76 */     response.setHeader("Cache-Control", "no-cache");
/*  77 */     response.setContentType("text/json;charset=UTF-8");
/*  78 */     return "template/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/v_list.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String list(HttpServletRequest request, ModelMap model) {
/*  84 */     CmsSite site = CmsUtils.getSite(request);
/*  85 */     String root = (String)model.get("root");
/*  86 */     if (root == null) {
/*  87 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/*  89 */     log.debug("list Template root: {}", root);
/*  90 */     if (StringUtils.isBlank(root)) {
/*  91 */       root = site.getTplPath();
/*     */     }
/*  93 */     WebErrors errors = validateList(root, site.getTplPath(), request);
/*  94 */     if (errors.hasErrors()) {
/*  95 */       return errors.showErrorPage(model);
/*     */     }
/*  97 */     String rel = root.substring(site.getTplPath().length());
/*  98 */     if (rel.length() == 0) {
/*  99 */       rel = "/";
/*     */     }
/* 101 */     model.addAttribute("root", root);
/* 102 */     model.addAttribute("rel", rel);
/* 103 */     model.addAttribute("list", this.tplManager.getChild(root));
/* 104 */     return "template/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_create_dir.do"})
/*     */   public String createDir(String root, String dirName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 111 */     this.tplManager.save(root + "/" + dirName, null, true);
/* 112 */     model.addAttribute("root", root);
/* 113 */     return list(request, model);
/*     */   }
/*     */   @RequestMapping(value={"/template/v_add.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/* 118 */     CmsSite site = CmsUtils.getSite(request);
/* 119 */     String root = RequestUtils.getQueryParam(request, "root");
/* 120 */     WebErrors errors = validateAdd(root, site.getTplPath(), request);
/* 121 */     if (errors.hasErrors()) {
/* 122 */       return errors.showErrorPage(model);
/*     */     }
/* 124 */     String style = handerStyle(RequestUtils.getQueryParam(request, "style"));
/* 125 */     model.addAttribute("root", root);
/* 126 */     return "template/add_" + style;
/*     */   }
/*     */   @RequestMapping({"/template/v_edit.do"})
/*     */   public String edit(HttpServletRequest request, ModelMap model) {
/* 131 */     CmsSite site = CmsUtils.getSite(request);
/* 132 */     String root = RequestUtils.getQueryParam(request, "root");
/* 133 */     String name = RequestUtils.getQueryParam(request, "name");
/* 134 */     String style = handerStyle(RequestUtils.getQueryParam(request, "style"));
/* 135 */     WebErrors errors = validateEdit(root, name, site.getTplPath(), request);
/* 136 */     if (errors.hasErrors()) {
/* 137 */       return errors.showErrorPage(model);
/*     */     }
/* 139 */     model.addAttribute("template", this.tplManager.get(name));
/* 140 */     model.addAttribute("root", root);
/* 141 */     model.addAttribute("name", name);
/* 142 */     return "template/edit_" + style;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_save.do"})
/*     */   public String save(String root, String filename, String source, HttpServletRequest request, ModelMap model) {
/* 148 */     WebErrors errors = validateSave(filename, source, request);
/* 149 */     if (errors.hasErrors()) {
/* 150 */       return errors.showErrorPage(model);
/*     */     }
/* 152 */     String name = root + "/" + filename + ".html";
/* 153 */     this.tplManager.save(name, source, false);
/* 154 */     model.addAttribute("root", root);
/* 155 */     log.info("save Template name={}", filename);
/* 156 */     this.cmsLogMng.operating(request, "template.log.save", "filename=" + 
/* 157 */       filename);
/* 158 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_ajaxUpdate.do"})
/*     */   public void ajaxUpdate(String root, String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 166 */     CmsSite site = CmsUtils.getSite(request);
/* 167 */     WebErrors errors = validateUpdate(root, name, site.getTplPath(), source, request);
/* 168 */     if (errors.hasErrors()) {
/* 169 */       ResponseUtils.renderJson(response, "{success:false,msg:'" + 
/* 170 */         (String)errors.getErrors().get(0) + "'}");
/*     */     }
/* 172 */     this.tplManager.update(name, source);
/* 173 */     log.info("update Template name={}.", name);
/* 174 */     this.cmsLogMng.operating(request, "template.log.update", "filename=" + name);
/* 175 */     model.addAttribute("root", root);
/* 176 */     ResponseUtils.renderJson(response, "{success:true}");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_update.do"})
/*     */   public String update(String root, String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 183 */     CmsSite site = CmsUtils.getSite(request);
/* 184 */     WebErrors errors = validateUpdate(root, name, site.getTplPath(), source, request);
/* 185 */     if (errors.hasErrors()) {
/* 186 */       return errors.showErrorPage(model);
/*     */     }
/*     */ 
/* 189 */     source = source.replaceAll("&quot;", "\"");
/* 190 */     source = source.replaceAll("&amp;", "&");
/* 191 */     source = source.replaceAll("&lt;", "<");
/* 192 */     source = source.replaceAll("&gt;", ">");
/* 193 */     this.tplManager.update(name, source);
/* 194 */     log.info("update Template name={}.", name);
/* 195 */     this.cmsLogMng.operating(request, "template.log.update", "filename=" + name);
/* 196 */     model.addAttribute("template", this.tplManager.get(name));
/* 197 */     model.addAttribute("root", root);
/* 198 */     return "template/edit_editor";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_delete.do"})
/*     */   public String delete(String root, String[] names, HttpServletRequest request, ModelMap model) {
/* 204 */     CmsSite site = CmsUtils.getSite(request);
/* 205 */     WebErrors errors = validateDelete(names, site.getTplPath(), request);
/* 206 */     if (errors.hasErrors()) {
/* 207 */       return errors.showErrorPage(model);
/*     */     }
/* 209 */     int count = this.tplManager.delete(names);
/* 210 */     log.info("delete Template count: {}", Integer.valueOf(count));
/* 211 */     for (String name : names) {
/* 212 */       log.info("delete Template name={}", name);
/* 213 */       this.cmsLogMng.operating(request, "template.log.delete", "filename=" + 
/* 214 */         name);
/*     */     }
/* 216 */     model.addAttribute("root", root);
/* 217 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, ModelMap model) {
/* 223 */     String root = RequestUtils.getQueryParam(request, "root");
/* 224 */     String name = RequestUtils.getQueryParam(request, "name");
/* 225 */     CmsSite site = CmsUtils.getSite(request);
/* 226 */     WebErrors errors = validateDelete(new String[] { name }, 
/* 227 */       site.getTplPath(), request);
/* 228 */     if (errors.hasErrors()) {
/* 229 */       return errors.showErrorPage(model);
/*     */     }
/* 231 */     int count = this.tplManager.delete(new String[] { name });
/* 232 */     log.info("delete Template {}, count {}", name, Integer.valueOf(count));
/* 233 */     this.cmsLogMng.operating(request, "template.log.delete", "filename=" + name);
/* 234 */     model.addAttribute("root", root);
/* 235 */     return list(request, model);
/*     */   }
/*     */   @RequestMapping(value={"/template/v_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String renameInput(HttpServletRequest request, ModelMap model) {
/* 240 */     CmsSite site = CmsUtils.getSite(request);
/* 241 */     String root = RequestUtils.getQueryParam(request, "root");
/* 242 */     String name = RequestUtils.getQueryParam(request, "name");
/* 243 */     String origName = name.substring(site.getTplPath().length());
/* 244 */     model.addAttribute("origName", origName);
/* 245 */     model.addAttribute("root", root);
/* 246 */     return "template/rename";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/o_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String renameSubmit(String root, String origName, String distName, HttpServletRequest request, ModelMap model) {
/* 252 */     CmsSite site = CmsUtils.getSite(request);
/* 253 */     String orig = site.getTplPath() + origName;
/* 254 */     String dist = site.getTplPath() + distName;
/* 255 */     this.tplManager.rename(orig, dist);
/* 256 */     log.info("name Template from {} to {}", orig, dist);
/* 257 */     model.addAttribute("root", root);
/* 258 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/template/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOException
/*     */   {
/* 267 */     this.tplManager.save(root, file);
/* 268 */     model.addAttribute("root", root);
/* 269 */     log.info("file upload seccess: {}, size:{}.", 
/* 270 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/* 271 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/v_setting.do"})
/*     */   public String setting(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 277 */     CmsSite site = CmsUtils.getSite(request);
/* 278 */     String[] solutions = this.resourceMng.getSolutions(site.getTplPath());
/* 279 */     model.addAttribute("solutions", solutions);
/* 280 */     model.addAttribute("defSolution", site.getTplSolution());
/* 281 */     return "template/setting";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_def_template.do"})
/*     */   public void defTempate(String solution, HttpServletRequest request, HttpServletResponse response) {
/* 287 */     CmsSite site = CmsUtils.getSite(request);
/* 288 */     this.cmsSiteMng.updateTplSolution(site.getId(), solution);
/* 289 */     ResponseUtils.renderJson(response, "{'success':true}");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_export.do"})
/*     */   public void exportSubmit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
/* 295 */     String solution = RequestUtils.getQueryParam(request, "solution");
/* 296 */     CmsSite site = CmsUtils.getSite(request);
/* 297 */     List<FileEntry> fileEntrys = this.resourceMng.export(site, solution);
/* 298 */     response.setContentType("application/x-download;charset=UTF-8");
/* 299 */     response.addHeader("Content-disposition", "filename=template-" + 
/* 300 */       solution + ".zip");
/*     */     try
/*     */     {
/* 303 */       Zipper.zip(response.getOutputStream(), fileEntrys, "GBK");
/*     */     } catch (IOException e) {
/* 305 */       log.error("export template error!", e);
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/template/o_import.do"})
/*     */   public String importSubmit(@RequestParam(value="tplZip", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 314 */     CmsSite site = CmsUtils.getSite(request);
/* 315 */     File tempFile = File.createTempFile("tplZip", "temp");
/* 316 */     file.transferTo(tempFile);
/* 317 */     this.resourceMng.imoport(tempFile, site);
/* 318 */     tempFile.delete();
/* 319 */     return setting(request, response, model);
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 323 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 327 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateList(String name, String tplPath, HttpServletRequest request)
/*     */   {
/* 332 */     WebErrors errors = WebErrors.create(request);
/* 333 */     if (vldExist(name, errors)) {
/* 334 */       return errors;
/*     */     }
/* 336 */     if (isUnValidName(name, name, tplPath, errors)) {
/* 337 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 339 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateAdd(String name, String tplPath, HttpServletRequest request)
/*     */   {
/* 344 */     WebErrors errors = WebErrors.create(request);
/* 345 */     if (vldExist(name, errors)) {
/* 346 */       return errors;
/*     */     }
/* 348 */     if (isUnValidName(name, name, tplPath, errors)) {
/* 349 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 351 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(String name, String source, HttpServletRequest request)
/*     */   {
/* 356 */     WebErrors errors = WebErrors.create(request);
/* 357 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(String path, String name, String tplPath, HttpServletRequest request)
/*     */   {
/* 362 */     WebErrors errors = WebErrors.create(request);
/* 363 */     if (vldExist(name, errors)) {
/* 364 */       return errors;
/*     */     }
/* 366 */     if (!name.startsWith(tplPath)) {
/* 367 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 369 */     if (isUnValidName(path, name, tplPath, errors)) {
/* 370 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 372 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(String root, String name, String tplPath, String source, HttpServletRequest request)
/*     */   {
/* 377 */     WebErrors errors = WebErrors.create(request);
/* 378 */     if (vldExist(name, errors)) {
/* 379 */       return errors;
/*     */     }
/* 381 */     if (isUnValidName(root, name, tplPath, errors)) {
/* 382 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 384 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(String[] names, String tplPath, HttpServletRequest request)
/*     */   {
/* 389 */     WebErrors errors = WebErrors.create(request);
/* 390 */     errors.ifEmpty(names, "names");
/* 391 */     for (String id : names) {
/* 392 */       if (vldExist(id, errors)) {
/* 393 */         return errors;
/*     */       }
/* 395 */       if (isUnValidName(id, id, tplPath, errors)) {
/* 396 */         errors.addErrorCode("template.invalidParams");
/* 397 */         return errors;
/*     */       }
/*     */     }
/* 400 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(String name, WebErrors errors) {
/* 404 */     if (errors.ifNull(name, "name")) {
/* 405 */       return true;
/*     */     }
/* 407 */     Tpl entity = this.tplManager.get(name);
/*     */ 
/* 409 */     return errors.ifNotExist(entity, Tpl.class, name);
/*     */   }
/*     */ 
/*     */   private boolean isUnValidName(String path, String name, String tplPath, WebErrors errors)
/*     */   {
/* 416 */     return (!path.startsWith(tplPath)) || (path.contains("../")) || (path.contains("..\\")) || (name.contains("..\\")) || (name.contains("../"));
/*     */   }
/*     */ 
/*     */   private String handerStyle(String style)
/*     */   {
/* 423 */     if (("textarea".equals(style)) || ("editor".equals(style))) {
/* 424 */       return style;
/*     */     }
/* 426 */     return "textarea";
/*     */   }
/*     */ 
/*     */   public void setTplManager(TplManager tplManager)
/*     */   {
/* 436 */     this.tplManager = tplManager;
/*     */   }
/*     */   @Autowired
/*     */   public void setResourceMng(CmsResourceMng resourceMng) {
/* 441 */     this.resourceMng = resourceMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 446 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.TemplateAct
 * JD-Core Version:    0.6.0
 */