/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
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
/*     */ public class CmsFileAct
/*     */ {
/*  43 */   private static final Logger log = LoggerFactory.getLogger(CmsFileAct.class);
/*     */   private static final String INVALID_PARAM = "template.invalidParams";
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsFileMng fileMng;
/*     */   private CmsResourceMng resourceMng;
/*     */ 
/*     */   @Autowired
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @RequestMapping({"/file/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model)
/*     */   {
/*  50 */     CmsSite site = CmsUtils.getSite(request);
/*  51 */     String root = (String)model.get("root");
/*  52 */     if (root == null) {
/*  53 */       root = RequestUtils.getQueryParam(request, "root");
/*     */     }
/*  55 */     String valid = RequestUtils.getQueryParam(request, "valid");
/*  56 */     Boolean validB = null;
/*  57 */     if (StringUtils.isNotBlank(valid)) {
/*  58 */       if (valid.equals("1"))
/*  59 */         validB = Boolean.valueOf(true);
/*     */       else {
/*  61 */         validB = Boolean.valueOf(false);
/*     */       }
/*     */     }
/*  64 */     log.debug("list Resource root: {}", root);
/*  65 */     if (StringUtils.isBlank(root)) {
/*  66 */       root = site.getUploadPath();
/*     */     }
/*  68 */     String uploadPath = root.substring(site.getUploadPath().length());
/*  69 */     if (uploadPath.length() == 0) {
/*  70 */       uploadPath = "/";
/*     */     }
/*  72 */     WebErrors errors = validateList(root, site.getUploadPath(), request);
/*  73 */     if (errors.hasErrors()) {
/*  74 */       return errors.showErrorPage(model);
/*     */     }
/*  76 */     model.addAttribute("root", root);
/*  77 */     model.addAttribute("rel", uploadPath);
/*  78 */     model.addAttribute("valid", validB);
/*  79 */     model.addAttribute("list", this.resourceMng.queryFiles(root, validB));
/*  80 */     return "file/list";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/file/o_delfreefiles.do"})
/*     */   public String deleteUnValid(String root, HttpServletRequest request, ModelMap model) {
/*  86 */     List fileList = this.fileMng.getList(Boolean.valueOf(false));
/*  87 */     CmsSite site = CmsUtils.getSite(request);
/*  88 */     String contextPath = site.getContextPath();
/*  89 */     String[] names = new String[fileList.size()];
/*     */ 
/*  91 */     for (int i = 0; i < names.length; i++) {
/*  92 */       String filePath = ((CmsFile)fileList.get(i)).getFilePath();
/*     */ 
/*  94 */       if (filePath.indexOf(".") != -1) {
/*  95 */         if (StringUtils.isNotBlank(contextPath)) {
/*  96 */           if (filePath.contains(contextPath))
/*  97 */             names[i] = filePath.substring(filePath.indexOf(contextPath) + contextPath.length());
/*     */         }
/*     */         else {
/* 100 */           names[i] = filePath;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 105 */     List nameList = new ArrayList();
/* 106 */     for (String name : names) {
/* 107 */       if (StringUtils.isNotBlank(name)) {
/* 108 */         nameList.add(name);
/*     */       }
/*     */     }
/* 111 */     names = (String[])nameList.toArray(new String[nameList.size()]);
/* 112 */     WebErrors errors = validateDeleteFreeFile(root, site.getUploadPath(), names, request);
/* 113 */     if (errors.hasErrors()) {
/* 114 */       return errors.showErrorPage(model);
/*     */     }
/* 116 */     if ((names != null) && (names.length > 0)) {
/* 117 */       int count = this.resourceMng.delete(names);
/* 118 */       log.info("delete Resource count: {}", Integer.valueOf(count));
/* 119 */       for (String name : names) {
/* 120 */         this.fileMng.deleteByPath(name);
/* 121 */         log.info("delete Resource name={}", name);
/* 122 */         this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + 
/* 123 */           name);
/*     */       }
/*     */     }
/* 126 */     model.addAttribute("root", root);
/* 127 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/file/o_delete.do"})
/*     */   public String delete(String root, String[] names, HttpServletRequest request, ModelMap model) {
/* 133 */     CmsSite site = CmsUtils.getSite(request);
/* 134 */     WebErrors errors = validateDelete(root, site.getUploadPath(), names, request);
/* 135 */     if (errors.hasErrors()) {
/* 136 */       return errors.showErrorPage(model);
/*     */     }
/* 138 */     int count = this.resourceMng.delete(names);
/* 139 */     log.info("delete Resource count: {}", Integer.valueOf(count));
/* 140 */     for (String name : names) {
/* 141 */       this.fileMng.deleteByPath(name);
/* 142 */       log.info("delete Resource name={}", name);
/* 143 */       this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + 
/* 144 */         name);
/*     */     }
/* 146 */     model.addAttribute("root", root);
/* 147 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/file/o_delete_single.do"})
/*     */   public String deleteSingle(HttpServletRequest request, ModelMap model) {
/* 153 */     String root = RequestUtils.getQueryParam(request, "root");
/* 154 */     String name = RequestUtils.getQueryParam(request, "name");
/* 155 */     int count = this.resourceMng.delete(new String[] { name });
/* 156 */     this.fileMng.deleteByPath(name);
/* 157 */     log.info("delete Resource {}, count {}", name, Integer.valueOf(count));
/* 158 */     this.cmsLogMng.operating(request, "resource.log.delete", "filename=" + name);
/* 159 */     model.addAttribute("root", root);
/* 160 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/file/v_upload.do"})
/*     */   public String uploadInput(HttpServletRequest request, ModelMap model) {
/* 166 */     String root = RequestUtils.getQueryParam(request, "root");
/* 167 */     model.addAttribute("root", root);
/* 168 */     return "file/upload";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/file/o_upload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String uploadSubmit(String root, HttpServletRequest request, ModelMap model) {
/* 174 */     model.addAttribute("root", root);
/* 175 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/file/o_swfupload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfUpload(String root, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IllegalStateException, IOException
/*     */   {
/* 184 */     this.resourceMng.saveFile(root, file);
/* 185 */     this.fileMng.saveFileByPath(root + "//" + file.getOriginalFilename(), file.getOriginalFilename(), Boolean.valueOf(false));
/* 186 */     model.addAttribute("root", root);
/* 187 */     log.info("file upload seccess: {}, size:{}.", 
/* 188 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/* 189 */     ResponseUtils.renderText(response, "");
/*     */   }
/* 193 */   @RequestMapping({"/file/o_flag_files.do"})
/*     */   public String flagOldFilesValid(HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/* 194 */     String root = site.getUploadPath();
/* 195 */     String uploadPath = root.substring(site.getUploadPath().length());
/* 196 */     if (uploadPath.length() == 0) {
/* 197 */       uploadPath = "/";
/*     */     }
/* 199 */     model.addAttribute("root", root);
/* 200 */     model.addAttribute("rel", uploadPath);
/* 201 */     model.addAttribute("list", this.resourceMng.queryFiles(root, Boolean.valueOf(false)));
/* 202 */     saveFileFlags(this.realPathResolver.get(root), root);
/* 203 */     return list(request, model); }
/*     */ 
/*     */   private void saveFileFlags(String realpath, String path)
/*     */   {
/* 207 */     File file = new File(realpath);
/* 208 */     File[] array = file.listFiles();
/*     */ 
/* 210 */     for (int i = 0; i < array.length; i++) {
/* 211 */       String filePath = path + "/" + array[i].getName();
/* 212 */       if (array[i].isFile()) {
/* 213 */         if (this.fileMng.findByPath(filePath) == null)
/* 214 */           this.fileMng.saveFileByPath(filePath, array[i].getName(), Boolean.valueOf(true));
/*     */       }
/* 216 */       else if (array[i].isDirectory()) {
/* 217 */         if (this.fileMng.findByPath(filePath) == null) {
/* 218 */           this.fileMng.saveFileByPath(filePath, array[i].getName(), Boolean.valueOf(true));
/*     */         }
/* 220 */         saveFileFlags(array[i].getPath(), path);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private WebErrors validateList(String name, String path, HttpServletRequest request)
/*     */   {
/* 227 */     WebErrors errors = WebErrors.create(request);
/* 228 */     if (vldExist(name, errors)) {
/* 229 */       return errors;
/*     */     }
/* 231 */     if (isUnValidName(name, name, path, errors)) {
/* 232 */       errors.addErrorCode("template.invalidParams");
/*     */     }
/* 234 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(String root, String path, String[] names, HttpServletRequest request)
/*     */   {
/* 239 */     WebErrors errors = WebErrors.create(request);
/* 240 */     errors.ifEmpty(names, "names");
/* 241 */     for (String id : names) {
/* 242 */       vldExist(id, errors);
/*     */     }
/* 244 */     for (String name : names) {
/* 245 */       if (isUnValidName(root, name, path, errors)) {
/* 246 */         errors.addErrorCode("template.invalidParams");
/* 247 */         return errors;
/*     */       }
/*     */     }
/* 250 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDeleteFreeFile(String root, String path, String[] names, HttpServletRequest request)
/*     */   {
/* 255 */     WebErrors errors = WebErrors.create(request);
/* 256 */     if ((names == null) || (names.length <= 0)) {
/* 257 */       errors.addErrorCode("error.findnofreefile");
/*     */     }
/* 259 */     for (String name : names) {
/* 260 */       if (isUnValidName(root, name, path, errors)) {
/* 261 */         errors.addErrorCode("template.invalidParams");
/* 262 */         return errors;
/*     */       }
/*     */     }
/* 265 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(String name, WebErrors errors)
/*     */   {
/* 270 */     return errors.ifNull(name, "name");
/*     */   }
/*     */ 
/*     */   private boolean isUnValidName(String path, String name, String resPath, WebErrors errors)
/*     */   {
/* 277 */     return (!path.startsWith(resPath)) || (path.contains("../")) || (path.contains("..\\")) || (name.contains("..\\")) || (name.contains("../"));
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceMng(CmsResourceMng resourceMng)
/*     */   {
/* 293 */     this.resourceMng = resourceMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsFileAct
 * JD-Core Version:    0.6.0
 */