/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.io.IOException;
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
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class ResourceAct
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(ResourceAct.class);
/*     */   private static final String INVALID_PARAM = "template.invalidParams";
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */   private CmsResourceMng resourceMng;
/*     */ 
/*     */   @RequestMapping({"/resource/v_left.do"})
/*     */   public String left(String path, HttpServletRequest request, ModelMap model)
/*     */   {
/*  41 */     return "resource/left";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_tree.do"})
/*     */   public String tree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/*  47 */     CmsSite site = CmsUtils.getSite(request);
/*  48 */     String root = RequestUtils.getQueryParam(request, "root");
/*  49 */     log.debug("tree path={}", root);
/*     */ 
/*  51 */     if ((StringUtils.isBlank(root)) || ("source".equals(root))) {
/*  52 */       root = site.getResPath();
/*  53 */       model.addAttribute("isRoot", Boolean.valueOf(true));
/*     */     } else {
/*  55 */       model.addAttribute("isRoot", Boolean.valueOf(false));
/*     */     }
/*  57 */     WebErrors errors = validateTree(root, request);
/*  58 */     if (errors.hasErrors()) {
/*  59 */       log.error((String)errors.getErrors().get(0));
/*  60 */       ResponseUtils.renderJson(response, "[]");
/*  61 */       return null;
/*     */     }
/*  63 */     List resList = this.resourceMng.listFile(root, true);
/*  64 */     model.addAttribute("resList", resList);
/*  65 */     response.setHeader("Cache-Control", "no-cache");
/*  66 */     response.setContentType("text/json;charset=UTF-8");
/*  67 */     return "resource/tree";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) {
/*  73 */     CmsSite site = CmsUtils.getSite(request);
/*  74 */     String root = (String)model.get("root");
/*  75 */     if (root == null) {
/*  76 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/*  78 */     log.debug("list Resource root: {}", root);
/*  79 */     if (StringUtils.isBlank(root)) {
/*  80 */       root = site.getResPath();
/*     */     }
/*  82 */     WebErrors errors = validateList(root, site.getResPath(), request);
/*  83 */     if (errors.hasErrors()) {
/*  84 */       return errors.showErrorPage(model);
/*     */     }
/*  86 */     String rel = root.substring(site.getResPath().length());
/*  87 */     if (rel.length() == 0) {
/*  88 */       rel = "/";
/*     */     }
/*  90 */     model.addAttribute("root", root);
/*  91 */     model.addAttribute("rel", rel);
/*  92 */     model.addAttribute("list", this.resourceMng.listFile(root, false));
/*  93 */     return "resource/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_create_dir.do"})
/*     */   public String createDir(String root, String dirName, HttpServletRequest request, ModelMap model)
/*     */   {
/* 100 */     this.resourceMng.createDir(root, dirName);
/* 101 */     model.addAttribute("root", root);
/* 102 */     return list(request, model);
/*     */   }
/*     */   @RequestMapping({"/resource/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/* 107 */     String root = RequestUtils.getQueryParam(request, "root");
/* 108 */     model.addAttribute("root", root);
/* 109 */     return "resource/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/v_edit.do"})
/*     */   public String edit(HttpServletRequest request, ModelMap model) throws IOException {
/* 115 */     CmsSite site = CmsUtils.getSite(request);
/* 116 */     String root = RequestUtils.getQueryParam(request, "root");
/* 117 */     String name = RequestUtils.getQueryParam(request, "name");
/* 118 */     WebErrors errors = validateEdit(root, site.getResPath(), request);
/* 119 */     if (errors.hasErrors()) {
/* 120 */       return errors.showErrorPage(model);
/*     */     }
/* 122 */     model.addAttribute("source", this.resourceMng.readFile(name));
/* 123 */     model.addAttribute("root", root);
/* 124 */     model.addAttribute("name", name);
/* 125 */     model.addAttribute("filename", 
/* 126 */       name.substring(name.lastIndexOf('/') + 1));
/* 127 */     return "resource/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_save.do"})
/*     */   public String save(String root, String filename, String source, HttpServletRequest request, ModelMap model) throws IOException {
/* 133 */     WebErrors errors = validateSave(filename, source, request);
/* 134 */     if (errors.hasErrors()) {
/* 135 */       return errors.showErrorPage(model);
/*     */     }
/* 137 */     this.resourceMng.createFile(root, filename, source);
/* 138 */     model.addAttribute("root", root);
/* 139 */     log.info("save Resource name={}", filename);
/* 140 */     this.cmsLogMng.operating(request, "resource.log.save", "filename=" + 
/* 141 */       filename);
/* 142 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_update.do"})
/*     */   public void update(String root, String name, String source, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/* 150 */     CmsSite site = CmsUtils.getSite(request);
/* 151 */     WebErrors errors = validateUpdate(root, name, site.getResPath(), source, request);
/* 152 */     if (errors.hasErrors()) {
/* 153 */       ResponseUtils.renderJson(response, "{success:false,msg:'" + 
/* 154 */         (String)errors.getErrors().get(0) + "'}");
/*     */     }
/* 156 */     this.resourceMng.updateFile(name, source);
/* 157 */     log.info("update Resource name={}.", name);
/* 158 */     this.cmsLogMng.operating(request, "resource.log.update", "filename=" + name);
/* 159 */     model.addAttribute("root", root);
/* 160 */     ResponseUtils.renderJson(response, "{success:true}");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_delete.do"})
/*     */   public String delete(String root, String[] names, HttpServletRequest request, ModelMap model) {
/* 166 */     CmsSite site = CmsUtils.getSite(request);
/* 167 */     WebErrors errors = validateDelete(root, names, site.getResPath(), request);
/* 168 */     if (errors.hasErrors()) {
/* 169 */       return errors.showErrorPage(model);
/*     */     }
/* 171 */     int count = this.resourceMng.delete(names);
/* 172 */     log.info("delete Resource count: {}", Integer.valueOf(count));
/* 173 */     for (String name : names) {
/* 174 */       log.info("delete Resource name={}", name);
/* 175 */       this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + 
/* 176 */         name);
/*     */     }
/* 178 */     model.addAttribute("root", root);
/* 179 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/resource/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, ModelMap model) {
/* 185 */     String root = RequestUtils.getQueryParam(request, "root");
/* 186 */     String name = RequestUtils.getQueryParam(request, "name");
/* 187 */     int count = this.resourceMng.delete(new String[] { name });
/* 188 */     log.info("delete Resource {}, count {}", name, Integer.valueOf(count));
/* 189 */     this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + name);
/* 190 */     model.addAttribute("root", root);
/* 191 */     return list(request, model);
/*     */   }
/*     */   @RequestMapping({"/resource/v_rename.do"})
/*     */   public String renameInput(HttpServletRequest request, ModelMap model) {
/* 196 */     CmsSite site = CmsUtils.getSite(request);
/* 197 */     String root = RequestUtils.getQueryParam(request, "root");
/* 198 */     String name = RequestUtils.getQueryParam(request, "name");
/* 199 */     String origName = name.substring(site.getResPath().length());
/* 200 */     model.addAttribute("origName", origName);
/* 201 */     model.addAttribute("root", root);
/* 202 */     return "resource/rename";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/resource/o_rename.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String renameSubmit(String root, String origName, String distName, HttpServletRequest request, ModelMap model) {
/* 208 */     CmsSite site = CmsUtils.getSite(request);
/* 209 */     String orig = site.getResPath() + origName;
/* 210 */     String dist = site.getResPath() + distName;
/* 211 */     this.resourceMng.rename(orig, dist);
/* 212 */     log.info("name Resource from {} to {}", orig, dist);
/* 213 */     model.addAttribute("root", root);
/* 214 */     return list(request, model);
/*     */   }
/*     */   @RequestMapping({"/resource/v_upload.do"})
/*     */   public String uploadInput(HttpServletRequest request, ModelMap model) {
/* 219 */     String root = RequestUtils.getQueryParam(request, "root");
/* 220 */     model.addAttribute("root", root);
/* 221 */     return "resource/upload";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/resource/o_upload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String uploadSubmit(String root, HttpServletRequest request, ModelMap model) {
/* 227 */     model.addAttribute("root", root);
/* 228 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/resource/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOException
/*     */   {
/* 237 */     this.resourceMng.saveFile(root, file);
/* 238 */     model.addAttribute("root", root);
/* 239 */     log.info("file upload seccess: {}, size:{}.", 
/* 240 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/* 241 */     ResponseUtils.renderText(response, "");
/*     */   }
/*     */ 
/*     */   private WebErrors validateTree(String path, HttpServletRequest request) {
/* 245 */     WebErrors errors = WebErrors.create(request);
/*     */ 
/* 249 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateList(String name, String resPath, HttpServletRequest request)
/*     */   {
/* 254 */     WebErrors errors = WebErrors.create(request);
/* 255 */     if (vldExist(name, errors)) {
/* 256 */       return errors;
/*     */     }
/* 258 */     if (isUnValidName(name, name, resPath, errors)) {
/* 259 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 261 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(String name, String source, HttpServletRequest request)
/*     */   {
/* 266 */     WebErrors errors = WebErrors.create(request);
/* 267 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(String id, String resPath, HttpServletRequest request) {
/* 271 */     WebErrors errors = WebErrors.create(request);
/* 272 */     if (vldExist(id, errors)) {
/* 273 */       return errors;
/*     */     }
/* 275 */     if (isUnValidName(id, id, resPath, errors)) {
/* 276 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 278 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(String root, String name, String resPath, String source, HttpServletRequest request)
/*     */   {
/* 283 */     WebErrors errors = WebErrors.create(request);
/* 284 */     if (vldExist(name, errors)) {
/* 285 */       return errors;
/*     */     }
/* 287 */     if (isUnValidName(root, name, resPath, errors)) {
/* 288 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 290 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(String root, String[] names, String resPath, HttpServletRequest request)
/*     */   {
/* 295 */     WebErrors errors = WebErrors.create(request);
/* 296 */     errors.ifEmpty(names, "names");
/* 297 */     for (String id : names) {
/* 298 */       vldExist(id, errors);
/* 299 */       if (isUnValidName(id, id, resPath, errors)) {
/* 300 */         errors.addErrorCode("template.invalidParams");
/* 301 */         return errors;
/*     */       }
/*     */     }
/* 304 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(String name, WebErrors errors)
/*     */   {
/* 309 */     return errors.ifNull(name, "name");
/*     */   }
/*     */ 
/*     */   private boolean isUnValidName(String path, String name, String resPath, WebErrors errors)
/*     */   {
/* 320 */     return (!path.startsWith(resPath)) || (path.contains("../")) || (path.contains("..\\")) || (name.contains("..\\")) || (name.contains("../"));
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceMng(CmsResourceMng resourceMng)
/*     */   {
/* 332 */     this.resourceMng = resourceMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.ResourceAct
 * JD-Core Version:    0.6.0
 */