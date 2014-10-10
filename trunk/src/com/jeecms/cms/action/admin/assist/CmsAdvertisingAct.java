/*     */ package com.jeecms.cms.action.admin.assist;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsAdvertisingMng;
/*     */ import com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import java.io.IOException;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.io.FilenameUtils;
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
/*     */ public class CmsAdvertisingAct
/*     */ {
/*  44 */   private static final Logger log = LoggerFactory.getLogger(CmsAdvertisingAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsAdvertisingSpaceMng cmsAdvertisingSpaceMng;
/*     */ 
/*     */   @Autowired
/*     */   private FileRepository fileRepository;
/*     */ 
/*     */   @Autowired
/*     */   private DbFileMng dbFileMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsLogMng cmsLogMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsAdvertisingMng manager;
/*     */ 
/*  49 */   @RequestMapping({"/advertising/v_list.do"})
/*     */   public String list(Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model) { CmsSite site = CmsUtils.getSite(request);
/*  50 */     Pagination pagination = this.manager.getPage(site.getId(), queryAdspaceId, 
/*  51 */       queryEnabled, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/*  52 */     List adspaceList = this.cmsAdvertisingSpaceMng
/*  53 */       .getList(site.getId());
/*  54 */     model.addAttribute("pagination", pagination);
/*  55 */     model.addAttribute("adspaceList", adspaceList);
/*  56 */     model.addAttribute("pageNo", Integer.valueOf(pagination.getPageNo()));
/*  57 */     if (queryAdspaceId != null) {
/*  58 */       model.addAttribute("queryAdspaceId", queryAdspaceId);
/*     */     }
/*  60 */     if (queryEnabled != null) {
/*  61 */       model.addAttribute("queryEnabled", queryEnabled);
/*     */     }
/*  63 */     return "advertising/list"; }
/*     */ 
/*     */   @RequestMapping({"/advertising/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  68 */     CmsSite site = CmsUtils.getSite(request);
/*  69 */     List adspaceList = this.cmsAdvertisingSpaceMng
/*  70 */       .getList(site.getId());
/*  71 */     model.addAttribute("adspaceList", adspaceList);
/*  72 */     return "advertising/add";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising/v_edit.do"})
/*     */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  78 */     CmsSite site = CmsUtils.getSite(request);
/*  79 */     WebErrors errors = validateEdit(id, request);
/*  80 */     if (errors.hasErrors()) {
/*  81 */       return errors.showErrorPage(model);
/*     */     }
/*  83 */     CmsAdvertising cmsAdvertising = this.manager.findById(id);
/*  84 */     model.addAttribute("cmsAdvertising", cmsAdvertising);
/*  85 */     model.addAttribute("attr", cmsAdvertising.getAttr());
/*  86 */     model.addAttribute("adspaceList", this.cmsAdvertisingSpaceMng
/*  87 */       .getList(site.getId()));
/*  88 */     model.addAttribute("pageNo", pageNo);
/*  89 */     return "advertising/edit";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising/o_save.do"})
/*     */   public String save(CmsAdvertising bean, Integer adspaceId, HttpServletRequest request, ModelMap model) {
/*  95 */     WebErrors errors = validateSave(bean, request);
/*  96 */     if (errors.hasErrors()) {
/*  97 */       return errors.showErrorPage(model);
/*     */     }
/*  99 */     Map<String, String> attr = RequestUtils.getRequestMap(request, "attr_");
/*     */ 
/* 101 */     Set<String> toRemove = new HashSet();
/* 102 */     for (Map.Entry entry : attr.entrySet()) {
/* 103 */       if (StringUtils.isBlank((String)entry.getValue())) {
/* 104 */         toRemove.add((String)entry.getKey());
/*     */       }
/*     */     }
/* 107 */     for (String key : toRemove) {
/* 108 */       attr.remove(key);
/*     */     }
/* 110 */     bean = this.manager.save(bean, adspaceId, attr);
/* 111 */     log.info("save CmsAdvertising id={}", bean.getId());
/* 112 */     this.cmsLogMng.operating(request, "cmsAdvertising.log.save", "id=" + 
/* 113 */       bean.getId() + ";name=" + bean.getName());
/* 114 */     return "redirect:v_list.do";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising/o_update.do"})
/*     */   public String update(Integer queryAdspaceId, Boolean queryEnabled, CmsAdvertising bean, Integer adspaceId, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 121 */     WebErrors errors = validateUpdate(bean.getId(), request);
/* 122 */     if (errors.hasErrors()) {
/* 123 */       return errors.showErrorPage(model);
/*     */     }
/* 125 */     Map<String, String> attr = RequestUtils.getRequestMap(request, "attr_");
/*     */ 
/* 127 */     Set<String> toRemove = new HashSet();
/* 128 */     for (Map.Entry entry : attr.entrySet()) {
/* 129 */       if (StringUtils.isBlank((String)entry.getValue())) {
/* 130 */         toRemove.add((String)entry.getKey());
/*     */       }
/*     */     }
/* 133 */     for (String key : toRemove) {
/* 134 */       attr.remove(key);
/*     */     }
/* 136 */     bean = this.manager.update(bean, adspaceId, attr);
/* 137 */     log.info("update CmsAdvertising id={}.", bean.getId());
/* 138 */     this.cmsLogMng.operating(request, "cmsAdvertising.log.update", "id=" + 
/* 139 */       bean.getId() + ";name=" + bean.getName());
/* 140 */     return list(queryAdspaceId, queryEnabled, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising/o_delete.do"})
/*     */   public String delete(Integer[] ids, Integer queryAdspaceId, Boolean queryEnabled, Integer pageNo, HttpServletRequest request, ModelMap model)
/*     */   {
/* 147 */     WebErrors errors = validateDelete(ids, request);
/* 148 */     if (errors.hasErrors()) {
/* 149 */       return errors.showErrorPage(model);
/*     */     }
/* 151 */     CmsAdvertising[] beans = this.manager.deleteByIds(ids);
/* 152 */     for (CmsAdvertising bean : beans) {
/* 153 */       log.info("delete CmsAdvertising id={}", bean.getId());
/* 154 */       this.cmsLogMng.operating(request, "cmsAdvertising.log.delete", "id=" + 
/* 155 */         bean.getId() + ";name=" + bean.getName());
/*     */     }
/* 157 */     return list(queryAdspaceId, queryEnabled, pageNo, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/advertising/o_upload_flash.do"})
/*     */   public String uploadFlash(@RequestParam(value="flashFile", required=false) MultipartFile file, String flashNum, HttpServletRequest request, ModelMap model)
/*     */   {
/* 164 */     WebErrors errors = validateUpload(file, request);
/* 165 */     if (errors.hasErrors()) {
/* 166 */       model.addAttribute("error", errors.getErrors().get(0));
/* 167 */       return "advertising/flash_iframe";
/*     */     }
/* 169 */     CmsSite site = CmsUtils.getSite(request);
/* 170 */     String origName = file.getOriginalFilename();
/* 171 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/* 172 */       Locale.ENGLISH);
/*     */     try
/*     */     {
/*     */       String fileUrl;
/* 176 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/* 177 */         String dbFilePath = site.getConfig().getDbFileUri();
/* 178 */         fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 179 */           file.getInputStream());
/*     */ 
/* 181 */         fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/* 182 */       } else if (site.getUploadFtp() != null) {
/* 183 */         Ftp ftp = site.getUploadFtp();
/* 184 */         String ftpUrl = ftp.getUrl();
/* 185 */         fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 186 */           file.getInputStream());
/*     */ 
/* 188 */         fileUrl = ftpUrl + fileUrl;
/*     */       } else {
/* 190 */         String ctx = request.getContextPath();
/* 191 */         fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), ext, 
/* 192 */           file);
/*     */ 
/* 194 */         fileUrl = ctx + fileUrl;
/*     */       }
/* 196 */       model.addAttribute("flashPath", fileUrl);
/* 197 */       model.addAttribute("flashName", origName);
/* 198 */       model.addAttribute("flashNum", flashNum);
/*     */     } catch (IllegalStateException e) {
/* 200 */       model.addAttribute("error", e.getMessage());
/* 201 */       log.error("upload file error!", e);
/*     */     } catch (IOException e) {
/* 203 */       model.addAttribute("error", e.getMessage());
/* 204 */       log.error("upload file error!", e);
/*     */     }
/* 206 */     return "advertising/flash_iframe";
/*     */   }
/*     */ 
/*     */   private WebErrors validateSave(CmsAdvertising bean, HttpServletRequest request)
/*     */   {
/* 211 */     WebErrors errors = WebErrors.create(request);
/* 212 */     CmsSite site = CmsUtils.getSite(request);
/* 213 */     bean.setSite(site);
/* 214 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateEdit(Integer id, HttpServletRequest request) {
/* 218 */     WebErrors errors = WebErrors.create(request);
/* 219 */     CmsSite site = CmsUtils.getSite(request);
/* 220 */     if (vldExist(id, site.getId(), errors)) {
/* 221 */       return errors;
/*     */     }
/* 223 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
/* 227 */     WebErrors errors = WebErrors.create(request);
/* 228 */     CmsSite site = CmsUtils.getSite(request);
/* 229 */     if (vldExist(id, site.getId(), errors)) {
/* 230 */       return errors;
/*     */     }
/* 232 */     return errors;
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
/* 236 */     WebErrors errors = WebErrors.create(request);
/* 237 */     CmsSite site = CmsUtils.getSite(request);
/* 238 */     if (errors.ifEmpty(ids, "ids")) {
/* 239 */       return errors;
/*     */     }
/* 241 */     for (Integer id : ids) {
/* 242 */       vldExist(id, site.getId(), errors);
/*     */     }
/* 244 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
/* 248 */     if (errors.ifNull(id, "id")) {
/* 249 */       return true;
/*     */     }
/* 251 */     CmsAdvertising entity = this.manager.findById(id);
/* 252 */     if (errors.ifNotExist(entity, CmsAdvertising.class, id)) {
/* 253 */       return true;
/*     */     }
/* 255 */     if (!entity.getSite().getId().equals(siteId)) {
/* 256 */       errors.notInSite(CmsAdvertising.class, id);
/* 257 */       return true;
/*     */     }
/* 259 */     return false;
/*     */   }
/*     */ 
/*     */   private WebErrors validateUpload(MultipartFile file, HttpServletRequest request)
/*     */   {
/* 264 */     WebErrors errors = WebErrors.create(request);
/* 265 */     if (errors.ifNull(file, "file")) {
/* 266 */       return errors;
/*     */     }
/* 268 */     return errors;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.assist.CmsAdvertisingAct
 * JD-Core Version:    0.6.0
 */