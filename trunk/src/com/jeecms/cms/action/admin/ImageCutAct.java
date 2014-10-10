/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.image.ImageScale;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import java.io.File;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ImageCutAct
/*     */ {
/*  24 */   private static final Logger log = LoggerFactory.getLogger(ImageCutAct.class);
/*     */   public static final String IMAGE_SELECT_RESULT = "/common/image_area_select";
/*     */   public static final String IMAGE_CUTED = "/common/image_cuted";
/*     */   public static final String ERROR = "error";
/*     */   private ImageScale imageScale;
/*     */   private FileRepository fileRepository;
/*     */   private DbFileMng dbFileMng;
/*     */ 
/*     */   @RequestMapping({"/common/v_image_area_select.do"})
/*     */   public String imageAreaSelect(String uploadBase, String imgSrcPath, Integer zoomWidth, Integer zoomHeight, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*     */   {
/*  42 */     model.addAttribute("uploadBase", uploadBase);
/*  43 */     model.addAttribute("imgSrcPath", imgSrcPath);
/*  44 */     model.addAttribute("zoomWidth", zoomWidth);
/*  45 */     model.addAttribute("zoomHeight", zoomHeight);
/*  46 */     model.addAttribute("uploadNum", uploadNum);
/*  47 */     return "/common/image_area_select";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/common/o_image_cut.do"})
/*     */   public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft, Integer imgWidth, Integer imgHeight, Integer reMinWidth, Integer reMinHeight, Float imgScale, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*     */   {
/*  55 */     CmsSite site = CmsUtils.getSite(request);
/*     */     try {
/*  57 */       if (imgWidth.intValue() > 0) {
/*  58 */         if (site.getConfig().getUploadToDb().booleanValue()) {
/*  59 */           String dbFilePath = site.getConfig().getDbFileUri();
/*  60 */           imgSrcPath = imgSrcPath.substring(dbFilePath.length());
/*  61 */           File file = this.dbFileMng.retrieve(imgSrcPath);
/*  62 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  63 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/*  64 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/*  65 */             getLen(imgWidth.intValue(), 
/*  65 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*  66 */           this.dbFileMng.restore(imgSrcPath, file);
/*  67 */         } else if (site.getUploadFtp() != null) {
/*  68 */           Ftp ftp = site.getUploadFtp();
/*  69 */           String ftpUrl = ftp.getUrl();
/*  70 */           imgSrcPath = imgSrcPath.substring(ftpUrl.length());
/*  71 */           String fileName = imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
/*  72 */           File file = ftp.retrieve(imgSrcPath, fileName);
/*  73 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  74 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/*  75 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/*  76 */             getLen(imgWidth.intValue(), 
/*  76 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*  77 */           ftp.restore(imgSrcPath, file);
/*     */         } else {
/*  79 */           String ctx = request.getContextPath();
/*  80 */           imgSrcPath = imgSrcPath.substring(ctx.length());
/*  81 */           File file = this.fileRepository.retrieve(imgSrcPath);
/*  82 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  83 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/*  84 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/*  85 */             getLen(imgWidth.intValue(), 
/*  85 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*     */         }
/*     */       }
/*  88 */       else if (site.getConfig().getUploadToDb().booleanValue()) {
/*  89 */         String dbFilePath = site.getConfig().getDbFileUri();
/*  90 */         imgSrcPath = imgSrcPath.substring(dbFilePath.length());
/*  91 */         File file = this.dbFileMng.retrieve(imgSrcPath);
/*  92 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/*  93 */         this.dbFileMng.restore(imgSrcPath, file);
/*  94 */       } else if (site.getUploadFtp() != null) {
/*  95 */         Ftp ftp = site.getUploadFtp();
/*  96 */         String ftpUrl = ftp.getUrl();
/*  97 */         imgSrcPath = imgSrcPath.substring(ftpUrl.length());
/*  98 */         String fileName = imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
/*  99 */         File file = ftp.retrieve(imgSrcPath, fileName);
/* 100 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/* 101 */         ftp.restore(imgSrcPath, file);
/*     */       } else {
/* 103 */         String ctx = request.getContextPath();
/* 104 */         imgSrcPath = imgSrcPath.substring(ctx.length());
/* 105 */         File file = this.fileRepository.retrieve(imgSrcPath);
/* 106 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/*     */       }
/*     */ 
/* 109 */       model.addAttribute("uploadNum", uploadNum);
/*     */     } catch (Exception e) {
/* 111 */       log.error("cut image error", e);
/* 112 */       model.addAttribute("error", e.getMessage());
/*     */     }
/* 114 */     return "/common/image_cuted";
/*     */   }
/*     */ 
/*     */   private int getLen(int len, float imgScale) {
/* 118 */     return Math.round(len / imgScale);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setImageScale(ImageScale imageScale)
/*     */   {
/* 128 */     this.imageScale = imageScale;
/*     */   }
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository) {
/* 133 */     this.fileRepository = fileRepository;
/*     */   }
/*     */   @Autowired
/*     */   public void setDbFileMng(DbFileMng dbFileMng) {
/* 138 */     this.dbFileMng = dbFileMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.ImageCutAct
 * JD-Core Version:    0.6.0
 */