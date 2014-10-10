/*    */ package com.jeecms.cms.action.admin;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.MarkConfig;
/*    */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*    */ import com.jeecms.common.image.ImageScale;
/*    */ import com.jeecms.common.image.ImageUtils;
/*    */ import com.jeecms.common.upload.FileRepository;
/*    */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*    */ import com.jeecms.core.manager.DbFileMng;
/*    */ import com.jeecms.core.web.WebErrors;
/*    */ import java.awt.Color;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.commons.io.FilenameUtils;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ public class AbstractUpload
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   protected FileRepository fileRepository;
/*    */ 
/*    */   @Autowired
/*    */   protected DbFileMng dbFileMng;
/*    */ 
/*    */   @Autowired
/*    */   protected ImageScale imageScale;
/*    */ 
/*    */   @Autowired
/*    */   protected RealPathResolver realPathResolver;
/*    */ 
/*    */   @Autowired
/*    */   protected CmsFileMng fileMng;
/*    */ 
/*    */   protected WebErrors validate(String filename, MultipartFile file, HttpServletRequest request)
/*    */   {
/* 29 */     WebErrors errors = WebErrors.create(request);
/* 30 */     if (file == null) {
/* 31 */       errors.addErrorCode("imageupload.error.noFileToUpload");
/* 32 */       return errors;
/*    */     }
/* 34 */     if (StringUtils.isBlank(filename)) {
/* 35 */       filename = file.getOriginalFilename();
/*    */     }
/* 37 */     String ext = FilenameUtils.getExtension(filename);
/* 38 */     if (!ImageUtils.isValidImageExt(ext)) {
/* 39 */       errors.addErrorCode("imageupload.error.notSupportExt", new Object[] { ext });
/* 40 */       return errors;
/*    */     }
/*    */     try {
/* 43 */       if (!ImageUtils.isImage(file.getInputStream())) {
/* 44 */         errors.addErrorCode("imageupload.error.notImage", new Object[] { ext });
/* 45 */         return errors;
/*    */       }
/*    */     } catch (IOException e) {
/* 48 */       errors.addErrorCode("imageupload.error.ioError", new Object[] { ext });
/* 49 */       return errors;
/*    */     }
/* 51 */     return errors;
/*    */   }
/*    */ 
/*    */   protected File mark(MultipartFile file, MarkConfig conf) throws Exception {
/* 55 */     String path = System.getProperty("java.io.tmpdir");
/* 56 */     File tempFile = new File(path, String.valueOf(
/* 57 */       System.currentTimeMillis()));
/* 58 */     file.transferTo(tempFile);
/* 59 */     boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
/* 60 */     if (imgMark) {
/* 61 */       File markImg = new File(this.realPathResolver.get(conf.getImagePath()));
/* 62 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 63 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 64 */         conf.getOffsetY().intValue(), markImg);
/*    */     } else {
/* 66 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 67 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 68 */         conf.getOffsetY().intValue(), conf.getContent(), Color.decode(
/* 69 */         conf.getColor()), conf.getSize().intValue(), conf.getAlpha().intValue());
/*    */     }
/* 71 */     return tempFile;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.AbstractUpload
 * JD-Core Version:    0.6.0
 */