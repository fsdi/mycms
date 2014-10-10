/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class ImageUploadAct extends AbstractUpload
/*     */ {
/*  29 */   private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
/*     */   private static final String RESULT_PAGE = "/common/iframe_upload";
/*     */   public static final String ERROR = "error";
/*     */ 
/*     */   @RequestMapping({"/common/o_upload_image.do"})
/*     */   public String execute(String filename, Integer uploadNum, Boolean mark, @RequestParam(value="uploadFile", required=false) MultipartFile file, HttpServletRequest request, ModelMap model)
/*     */   {
/*  46 */     WebErrors errors = validate(filename, file, request);
/*  47 */     if (errors.hasErrors()) {
/*  48 */       model.addAttribute("error", errors.getErrors().get(0));
/*  49 */       return "/common/iframe_upload";
/*     */     }
/*  51 */     CmsSite site = CmsUtils.getSite(request);
/*  52 */     MarkConfig conf = site.getConfig().getMarkConfig();
/*  53 */     if (mark == null) {
/*  54 */       mark = conf.getOn();
/*     */     }
/*     */ 
/*  57 */     String origName = file.getOriginalFilename();
/*  58 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/*  59 */       Locale.ENGLISH);
/*     */     try
/*     */     {
/*     */       String fileUrl;
/*  62 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/*  63 */         String dbFilePath = site.getConfig().getDbFileUri();
/*  64 */         if (!StringUtils.isBlank(filename)) {
/*  65 */           filename = filename.substring(dbFilePath.length());
/*  66 */           if (mark.booleanValue()) {
/*  67 */             File tempFile = mark(file, conf);
/*  68 */             fileUrl = this.dbFileMng.storeByFilename(filename, 
/*  69 */               new FileInputStream(tempFile));
/*  70 */             tempFile.delete();
/*     */           } else {
/*  72 */             fileUrl = this.dbFileMng.storeByFilename(filename, 
/*  73 */               file.getInputStream());
/*     */           }
/*     */         } else {
/*  76 */           if (mark.booleanValue()) {
/*  77 */             File tempFile = mark(file, conf);
/*  78 */             fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), 
/*  79 */               ext, new FileInputStream(tempFile));
/*  80 */             tempFile.delete();
/*     */           } else {
/*  82 */             fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), 
/*  83 */               ext, file.getInputStream());
/*     */           }
/*     */ 
/*  86 */           fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/*     */         }
/*  88 */       } else if (site.getUploadFtp() != null) {
/*  89 */         Ftp ftp = site.getUploadFtp();
/*  90 */         String ftpUrl = ftp.getUrl();
/*  91 */         if (!StringUtils.isBlank(filename)) {
/*  92 */           filename = filename.substring(ftpUrl.length());
/*  93 */           if (mark.booleanValue()) {
/*  94 */             File tempFile = mark(file, conf);
/*  95 */             fileUrl = ftp.storeByFilename(filename, 
/*  96 */               new FileInputStream(tempFile));
/*  97 */             tempFile.delete();
/*     */           } else {
/*  99 */             fileUrl = ftp.storeByFilename(filename, 
/* 100 */               file.getInputStream());
/*     */           }
/*     */         } else {
/* 103 */           if (mark.booleanValue()) {
/* 104 */             File tempFile = mark(file, conf);
/* 105 */             fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 106 */               new FileInputStream(tempFile));
/* 107 */             tempFile.delete();
/*     */           } else {
/* 109 */             fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 110 */               file.getInputStream());
/*     */           }
/*     */ 
/* 113 */           fileUrl = ftpUrl + fileUrl;
/*     */         }
/*     */       } else {
/* 116 */         String ctx = request.getContextPath();
/* 117 */         if (!StringUtils.isBlank(filename)) {
/* 118 */           filename = filename.substring(ctx.length());
/* 119 */           if (mark.booleanValue()) {
/* 120 */             File tempFile = mark(file, conf);
/* 121 */             fileUrl = this.fileRepository.storeByFilename(filename, 
/* 122 */               tempFile);
/* 123 */             tempFile.delete();
/*     */           } else {
/* 125 */             fileUrl = this.fileRepository
/* 126 */               .storeByFilename(filename, file);
/*     */           }
/*     */         } else {
/* 129 */           if (mark.booleanValue()) {
/* 130 */             File tempFile = mark(file, conf);
/* 131 */             fileUrl = this.fileRepository.storeByExt(
/* 132 */               site.getUploadPath(), ext, tempFile);
/* 133 */             tempFile.delete();
/*     */           } else {
/* 135 */             fileUrl = this.fileRepository.storeByExt(
/* 136 */               site.getUploadPath(), ext, file);
/*     */           }
/*     */ 
/* 139 */           fileUrl = ctx + fileUrl;
/*     */         }
/*     */       }
/* 142 */       this.fileMng.saveFileByPath(fileUrl, origName, Boolean.valueOf(false));
/* 143 */       model.addAttribute("uploadPath", fileUrl);
/* 144 */       model.addAttribute("uploadNum", uploadNum);
/*     */     } catch (IllegalStateException e) {
/* 146 */       model.addAttribute("error", e.getMessage());
/* 147 */       log.error("upload file error!", e);
/*     */     } catch (IOException e) {
/* 149 */       model.addAttribute("error", e.getMessage());
/* 150 */       log.error("upload file error!", e);
/*     */     } catch (Exception e) {
/* 152 */       model.addAttribute("error", e.getMessage());
/* 153 */       log.error("upload file error!", e);
/*     */     }
/* 155 */     return "/common/iframe_upload";
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.ImageUploadAct
 * JD-Core Version:    0.6.0
 */