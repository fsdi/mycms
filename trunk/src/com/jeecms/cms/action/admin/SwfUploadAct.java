/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class SwfUploadAct extends AbstractUpload
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
/*     */   public static final String ERROR = "error";
/*     */ 
/*     */   @RequestMapping(value={"/common/o_swfPicsUpload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfPicsUpload(String root, Integer uploadNum, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws Exception
/*     */   {
/*  52 */     WebErrors errors = validate(file.getOriginalFilename(), file, request);
/*  53 */     if (errors.hasErrors()) {
/*  54 */       model.addAttribute("error", errors.getErrors().get(0));
/*     */     }
/*  56 */     CmsSite site = CmsUtils.getSite(request);
/*  57 */     String ctx = request.getContextPath();
/*  58 */     MarkConfig conf = site.getConfig().getMarkConfig();
/*  59 */     Boolean mark = conf.getOn();
/*  60 */     String origName = file.getOriginalFilename();
/*  61 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/*  62 */       Locale.ENGLISH);
/*     */ String fileUrl = null;
/*  64 */     if (site.getUploadFtp() != null) {
/*  65 */       Ftp ftp = site.getUploadFtp();
/*  66 */       String ftpUrl = ftp.getUrl();
/*  67 */       if (mark.booleanValue()) {
/*  68 */         File tempFile = mark(file, conf);
/*  69 */         fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/*  70 */           new FileInputStream(tempFile));
/*  71 */         tempFile.delete();
/*     */       } else {
/*  73 */         fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/*  74 */           file.getInputStream());
/*     */       }
/*     */ 
/*  77 */       fileUrl = ftpUrl + fileUrl;
/*     */     }
/*  79 */     else if (mark.booleanValue()) {
/*  80 */       File tempFile = mark(file, conf);
/*  81 */       fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), ext, 
/*  82 */         tempFile);
/*  83 */       tempFile.delete();
/*     */     } else {
/*  85 */       fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), ext, 
/*  86 */         file);
/*     */     }
/*     */ 
/*  91 */     fileUrl = ctx + fileUrl;
/*  92 */     log.info("file upload seccess: {}, size:{}.", 
/*  93 */       file.getOriginalFilename(), Long.valueOf(file.getSize()));
/*  94 */     ResponseUtils.renderText(response, fileUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/common/o_swfAttachsUpload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void swfAttachsUpload(String root, Integer uploadNum, @RequestParam(value="Filedata", required=false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws Exception
/*     */   {
/* 103 */     System.out.println("111111111111111111111");
/* 104 */     WebErrors errors = validate(file.getOriginalFilename(), file, request);
/* 105 */     if (errors.hasErrors()) {
/* 106 */       System.out.println("22222222222222222");
/* 107 */       model.addAttribute("error", errors.getErrors().get(0));
/*     */     }
/* 109 */     System.out.println("333333333333333333333333");
/* 110 */     CmsSite site = CmsUtils.getSite(request);
/* 111 */     String ctx = request.getContextPath();
/* 112 */     String origName = file.getOriginalFilename();
/* 113 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/* 114 */       Locale.ENGLISH);
/*     */ 
/* 116 */     String fileUrl = "";
/*     */     try {
/* 118 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/* 119 */         String dbFilePath = site.getConfig().getDbFileUri();
/* 120 */         fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 121 */           file.getInputStream());
/*     */ 
/* 123 */         fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/* 124 */       } else if (site.getUploadFtp() != null) {
/* 125 */         Ftp ftp = site.getUploadFtp();
/* 126 */         String ftpUrl = ftp.getUrl();
/* 127 */         fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 128 */           file.getInputStream());
/*     */ 
/* 130 */         fileUrl = ftpUrl + fileUrl;
/*     */       } else {
/* 132 */         fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), ext, 
/* 133 */           file);
/*     */ 
/* 135 */         System.out.println("fileUrl=" + fileUrl);
/* 136 */         fileUrl = ctx + fileUrl;
/*     */       }
/* 138 */       this.fileMng.saveFileByPath(fileUrl, origName, Boolean.valueOf(false));
/* 139 */       model.addAttribute("attachmentPath", fileUrl);
/*     */     } catch (IllegalStateException e) {
/* 141 */       model.addAttribute("error", e.getMessage());
/* 142 */       e.printStackTrace();
/* 143 */       log.error("upload file error!", e);
/*     */     } catch (IOException e) {
/* 145 */       e.printStackTrace();
/* 146 */       model.addAttribute("error", e.getMessage());
/* 147 */       log.error("upload file error!", e);
/*     */     }
/* 149 */     JSONObject data = new JSONObject();
/* 150 */     data.put("attachUrl", fileUrl);
/* 151 */     data.put("attachName", origName);
/* 152 */     ResponseUtils.renderJson(response, data.toString());
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.SwfUploadAct
 * JD-Core Version:    0.6.0
 */