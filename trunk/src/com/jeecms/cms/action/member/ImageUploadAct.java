/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.image.ImageScale;
/*     */ import com.jeecms.common.image.ImageUtils;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import com.jeecms.core.web.WebErrors;
/*     */ import java.awt.Color;
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
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ 
/*     */ @Controller
/*     */ public class ImageUploadAct
/*     */ {
/*  41 */   private static final Logger log = LoggerFactory.getLogger(ImageUploadAct.class);
/*     */   private static final String USER_IMG_PATH = "/user/images";
/*     */   private static final String RESULT_PAGE = "tpl.iframe_upload";
/*     */   public static final String ERROR = "error";
/*     */   private FileRepository fileRepository;
/*     */   private DbFileMng dbFileMng;
/*     */   private ImageScale imageScale;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @RequestMapping({"/member/o_upload_image.jspx"})
/*     */   public String execute(String filename, Integer uploadNum, Boolean mark, @RequestParam(value="uploadFile", required=false) MultipartFile file, HttpServletRequest request, ModelMap model)
/*     */   {
/*  62 */     WebErrors errors = validate(filename, file, request);
/*  63 */     CmsSite site = CmsUtils.getSite(request);
/*  64 */     CmsUser user = CmsUtils.getUser(request);
/*  65 */     FrontUtils.frontData(request, model, site);
/*  66 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*  67 */     if (!mcfg.isMemberOn()) {
/*  68 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  70 */     if (user == null) {
/*  71 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  73 */     if (errors.hasErrors()) {
/*  74 */       model.addAttribute("error", errors.getErrors().get(0));
/*  75 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  76 */         "member", "tpl.iframe_upload");
/*     */     }
/*  78 */     MarkConfig conf = site.getConfig().getMarkConfig();
/*  79 */     if (mark == null) {
/*  80 */       mark = conf.getOn();
/*     */     }
/*     */ 
/*  83 */     String origName = file.getOriginalFilename();
/*  84 */     String ext = FilenameUtils.getExtension(origName).toLowerCase(
/*  85 */       Locale.ENGLISH);
/*     */     try
/*     */     {
/*     */       String fileUrl;
/*  88 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/*  89 */         String dbFilePath = site.getConfig().getDbFileUri();
/*  90 */         if (!StringUtils.isBlank(filename)) {
/*  91 */           filename = filename.substring(dbFilePath.length());
/*  92 */           if (mark.booleanValue()) {
/*  93 */             File tempFile = mark(file, conf);
/*  94 */             fileUrl = this.dbFileMng.storeByFilename(filename, 
/*  95 */               new FileInputStream(tempFile));
/*  96 */             tempFile.delete();
/*     */           } else {
/*  98 */             fileUrl = this.dbFileMng.storeByFilename(filename, 
/*  99 */               file.getInputStream());
/*     */           }
/*     */         } else {
/* 102 */           if (mark.booleanValue()) {
/* 103 */             File tempFile = mark(file, conf);
/* 104 */             fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), 
/* 105 */               ext, new FileInputStream(tempFile));
/* 106 */             tempFile.delete();
/*     */           } else {
/* 108 */             fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), 
/* 109 */               ext, file.getInputStream());
/*     */           }
/*     */ 
/* 112 */           fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/*     */         }
/* 114 */       } else if (site.getUploadFtp() != null) {
/* 115 */         Ftp ftp = site.getUploadFtp();
/* 116 */         String ftpUrl = ftp.getUrl();
/* 117 */         if (!StringUtils.isBlank(filename)) {
/* 118 */           filename = filename.substring(ftpUrl.length());
/* 119 */           if (mark.booleanValue()) {
/* 120 */             File tempFile = mark(file, conf);
/* 121 */             fileUrl = ftp.storeByFilename(filename, 
/* 122 */               new FileInputStream(tempFile));
/* 123 */             tempFile.delete();
/*     */           } else {
/* 125 */             fileUrl = ftp.storeByFilename(filename, 
/* 126 */               file.getInputStream());
/*     */           }
/*     */         } else {
/* 129 */           if (mark.booleanValue()) {
/* 130 */             File tempFile = mark(file, conf);
/* 131 */             fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 132 */               new FileInputStream(tempFile));
/* 133 */             tempFile.delete();
/*     */           } else {
/* 135 */             fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 136 */               file.getInputStream());
/*     */           }
/*     */ 
/* 139 */           fileUrl = ftpUrl + fileUrl;
/*     */         }
/*     */       } else {
/* 142 */         String ctx = request.getContextPath();
/* 143 */         if (!StringUtils.isBlank(filename)) {
/* 144 */           filename = filename.substring(ctx.length());
/* 145 */           if (mark.booleanValue()) {
/* 146 */             File tempFile = mark(file, conf);
/* 147 */             fileUrl = this.fileRepository.storeByFilename(filename, 
/* 148 */               tempFile);
/* 149 */             tempFile.delete();
/*     */           } else {
/* 151 */             fileUrl = this.fileRepository.storeByFilename(filename, file);
/*     */           }
/*     */         } else {
/* 155 */           if (mark.booleanValue()) {
/* 156 */             File tempFile = mark(file, conf);
/* 157 */             fileUrl = this.fileRepository.storeByExt("/user/images", ext, tempFile);
/* 158 */             tempFile.delete();
/*     */           } else {
/* 160 */             fileUrl = this.fileRepository.storeByExt("/user/images", ext, file);
/*     */           }
/*     */ 
/* 163 */           fileUrl = ctx + fileUrl;
/*     */         }
/*     */       }
/* 166 */       model.addAttribute("uploadPath", fileUrl);
/* 167 */       model.addAttribute("uploadNum", uploadNum);
/*     */     } catch (IllegalStateException e) {
/* 169 */       model.addAttribute("error", e.getMessage());
/* 170 */       log.error("upload file error!", e);
/*     */     } catch (IOException e) {
/* 172 */       model.addAttribute("error", e.getMessage());
/* 173 */       log.error("upload file error!", e);
/*     */     } catch (Exception e) {
/* 175 */       model.addAttribute("error", e.getMessage());
/* 176 */       log.error("upload file error!", e);
/*     */     }
/* 178 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 179 */       "member", "tpl.iframe_upload");
/*     */   }
/*     */ 
/*     */   private WebErrors validate(String filename, MultipartFile file, HttpServletRequest request)
/*     */   {
/* 184 */     WebErrors errors = WebErrors.create(request);
/* 185 */     if (file == null) {
/* 186 */       errors.addErrorCode("imageupload.error.noFileToUpload");
/* 187 */       return errors;
/*     */     }
/* 189 */     if (StringUtils.isBlank(filename)) {
/* 190 */       filename = file.getOriginalFilename();
/*     */     }
/* 192 */     String ext = FilenameUtils.getExtension(filename);
/* 193 */     if (!ImageUtils.isValidImageExt(ext)) {
/* 194 */       errors.addErrorCode("imageupload.error.notSupportExt", new Object[] { ext });
/* 195 */       return errors;
/*     */     }
/*     */     try {
/* 198 */       if (!ImageUtils.isImage(file.getInputStream())) {
/* 199 */         errors.addErrorCode("imageupload.error.notImage", new Object[] { ext });
/* 200 */         return errors;
/*     */       }
/*     */     } catch (IOException e) {
/* 203 */       log.error("image upload error", e);
/* 204 */       errors.addErrorCode("imageupload.error.ioError", new Object[] { ext });
/* 205 */       return errors;
/*     */     }
/* 207 */     return errors;
/*     */   }
/*     */ 
/*     */   private File mark(MultipartFile file, MarkConfig conf) throws Exception {
/* 211 */     String path = System.getProperty("java.io.tmpdir");
/* 212 */     File tempFile = new File(path, String.valueOf(
/* 213 */       System.currentTimeMillis()));
/* 214 */     file.transferTo(tempFile);
/* 215 */     boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
/* 216 */     if (imgMark) {
/* 217 */       File markImg = new File(this.realPathResolver.get(conf.getImagePath()));
/* 218 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 219 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 220 */         conf.getOffsetY().intValue(), markImg);
/*     */     } else {
/* 222 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 223 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 224 */         conf.getOffsetY().intValue(), conf.getContent(), Color.decode(
/* 225 */         conf.getColor()), conf.getSize().intValue(), conf.getAlpha().intValue());
/*     */     }
/* 227 */     return tempFile;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository)
/*     */   {
/* 237 */     this.fileRepository = fileRepository;
/*     */   }
/*     */   @Autowired
/*     */   public void setDbFileMng(DbFileMng dbFileMng) {
/* 242 */     this.dbFileMng = dbFileMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setImageScale(ImageScale imageScale) {
/* 247 */     this.imageScale = imageScale;
/*     */   }
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver) {
/* 252 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.ImageUploadAct
 * JD-Core Version:    0.6.0
 */