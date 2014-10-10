/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
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
/*  29 */   private static final Logger log = LoggerFactory.getLogger(ImageCutAct.class);
/*     */   public static final String IMAGE_SELECT_RESULT = "tpl.image_area_select";
/*     */   public static final String IMAGE_CUTED = "tpl.image_cuted";
/*     */   public static final String ERROR = "error";
/*     */   private ImageScale imageScale;
/*     */   private FileRepository fileRepository;
/*     */   private DbFileMng dbFileMng;
/*     */ 
/*     */   @RequestMapping({"/member/v_image_area_select.jspx"})
/*     */   public String imageAreaSelect(String uploadBase, String imgSrcPath, Integer zoomWidth, Integer zoomHeight, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*     */   {
/*  47 */     model.addAttribute("uploadBase", uploadBase);
/*  48 */     model.addAttribute("imgSrcPath", imgSrcPath);
/*  49 */     model.addAttribute("zoomWidth", zoomWidth);
/*  50 */     model.addAttribute("zoomHeight", zoomHeight);
/*  51 */     model.addAttribute("uploadNum", uploadNum);
/*  52 */     CmsSite site = CmsUtils.getSite(request);
/*  53 */     CmsUser user = CmsUtils.getUser(request);
/*  54 */     FrontUtils.frontData(request, model, site);
/*  55 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*  56 */     if (!mcfg.isMemberOn()) {
/*  57 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  59 */     if (user == null) {
/*  60 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  62 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  63 */       "member", "tpl.image_area_select");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_image_cut.jspx"})
/*     */   public String imageCut(String imgSrcPath, Integer imgTop, Integer imgLeft, Integer imgWidth, Integer imgHeight, Integer reMinWidth, Integer reMinHeight, Float imgScale, Integer uploadNum, HttpServletRequest request, ModelMap model)
/*     */   {
/*  71 */     CmsSite site = CmsUtils.getSite(request);
/*     */     try {
/*  73 */       if (imgWidth.intValue() > 0) {
/*  74 */         if (site.getConfig().getUploadToDb().booleanValue()) {
/*  75 */           String dbFilePath = site.getConfig().getDbFileUri();
/*  76 */           imgSrcPath = imgSrcPath.substring(dbFilePath.length());
/*  77 */           File file = this.dbFileMng.retrieve(imgSrcPath);
/*  78 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  79 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/*  80 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/*  81 */             getLen(imgWidth.intValue(), 
/*  81 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*  82 */           this.dbFileMng.restore(imgSrcPath, file);
/*  83 */         } else if (site.getUploadFtp() != null) {
/*  84 */           Ftp ftp = site.getUploadFtp();
/*  85 */           String ftpUrl = ftp.getUrl();
/*  86 */           imgSrcPath = imgSrcPath.substring(ftpUrl.length());
/*  87 */           String fileName = imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
/*  88 */           File file = ftp.retrieve(imgSrcPath, fileName);
/*  89 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  90 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/*  91 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/*  92 */             getLen(imgWidth.intValue(), 
/*  92 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*  93 */           ftp.restore(imgSrcPath, file);
/*     */         } else {
/*  95 */           String ctx = request.getContextPath();
/*  96 */           imgSrcPath = imgSrcPath.substring(ctx.length());
/*  97 */           File file = this.fileRepository.retrieve(imgSrcPath);
/*  98 */           this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue(), 
/*  99 */             getLen(imgTop.intValue(), imgScale.floatValue()), 
/* 100 */             getLen(imgLeft.intValue(), imgScale.floatValue()), 
/* 101 */             getLen(imgWidth.intValue(), 
/* 101 */             imgScale.floatValue()), getLen(imgHeight.intValue(), imgScale.floatValue()));
/*     */         }
/*     */       }
/* 104 */       else if (site.getConfig().getUploadToDb().booleanValue()) {
/* 105 */         String dbFilePath = site.getConfig().getDbFileUri();
/* 106 */         imgSrcPath = imgSrcPath.substring(dbFilePath.length());
/* 107 */         File file = this.dbFileMng.retrieve(imgSrcPath);
/* 108 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/* 109 */         this.dbFileMng.restore(imgSrcPath, file);
/* 110 */       } else if (site.getUploadFtp() != null) {
/* 111 */         Ftp ftp = site.getUploadFtp();
/* 112 */         String ftpUrl = ftp.getUrl();
/* 113 */         imgSrcPath = imgSrcPath.substring(ftpUrl.length());
/* 114 */         String fileName = imgSrcPath.substring(imgSrcPath.lastIndexOf("/"));
/* 115 */         File file = ftp.retrieve(imgSrcPath, fileName);
/* 116 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/* 117 */         ftp.restore(imgSrcPath, file);
/*     */       } else {
/* 119 */         String ctx = request.getContextPath();
/* 120 */         imgSrcPath = imgSrcPath.substring(ctx.length());
/* 121 */         File file = this.fileRepository.retrieve(imgSrcPath);
/* 122 */         this.imageScale.resizeFix(file, file, reMinWidth.intValue(), reMinHeight.intValue());
/*     */       }
/*     */ 
/* 125 */       model.addAttribute("uploadNum", uploadNum);
/*     */     } catch (Exception e) {
/* 127 */       log.error("cut image error", e);
/* 128 */       model.addAttribute("error", e.getMessage());
/*     */     }
/* 130 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 131 */       "member", "tpl.image_cuted");
/*     */   }
/*     */ 
/*     */   private int getLen(int len, float imgScale) {
/* 135 */     return Math.round(len / imgScale);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setImageScale(ImageScale imageScale)
/*     */   {
/* 145 */     this.imageScale = imageScale;
/*     */   }
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository) {
/* 150 */     this.fileRepository = fileRepository;
/*     */   }
/*     */   @Autowired
/*     */   public void setDbFileMng(DbFileMng dbFileMng) {
/* 155 */     this.dbFileMng = dbFileMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.ImageCutAct
 * JD-Core Version:    0.6.0
 */