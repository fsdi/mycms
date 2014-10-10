/*     */ package com.jeecms.cms.action.admin;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.fck.Command;
/*     */ import com.jeecms.common.fck.ResourceType;
/*     */ import com.jeecms.common.fck.UploadResponse;
/*     */ import com.jeecms.common.fck.Utils;
/*     */ import com.jeecms.common.image.ImageScale;
/*     */ import com.jeecms.common.image.ImageUtils;
/*     */ import com.jeecms.common.upload.FileRepository;
/*     */ import com.jeecms.common.upload.UploadUtils;
/*     */ import com.jeecms.common.web.springmvc.RealPathResolver;
/*     */ import com.jeecms.core.entity.Ftp;
/*     */ import com.jeecms.core.manager.DbFileMng;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.io.FilenameUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.multipart.MultipartHttpServletRequest;
/*     */ 
/*     */ @Controller
/*     */ public class FckAct
/*     */ {
/*  48 */   private static final Logger log = LoggerFactory.getLogger(FckAct.class);
/*     */   private FileRepository fileRepository;
/*     */   private DbFileMng dbFileMng;
/*     */   private ImageScale imageScale;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @RequestMapping(value={"/fck/upload.do"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void upload(@RequestParam(value="Command", required=false) String commandStr, @RequestParam(value="Type", required=false) String typeStr, @RequestParam(value="CurrentFolder", required=false) String currentFolderStr, @RequestParam(value="mark", required=false) Boolean mark, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  64 */     log.debug("Entering Dispatcher#doPost");
/*  65 */     responseInit(response);
/*  66 */     if ((Utils.isEmpty(commandStr)) && (Utils.isEmpty(currentFolderStr))) {
/*  67 */       commandStr = "QuickUpload";
/*  68 */       currentFolderStr = "/";
/*  69 */       if (Utils.isEmpty(typeStr)) {
/*  70 */         typeStr = "File";
/*     */       }
/*     */     }
/*  73 */     if ((currentFolderStr != null) && (!currentFolderStr.startsWith("/"))) {
/*  74 */       currentFolderStr = "/".concat(currentFolderStr);
/*     */     }
/*  76 */     log.debug("Parameter Command: {}", commandStr);
/*  77 */     log.debug("Parameter Type: {}", typeStr);
/*  78 */     log.debug("Parameter CurrentFolder: {}", currentFolderStr);
/*  79 */     UploadResponse ur = validateUpload(request, commandStr, typeStr, 
/*  80 */       currentFolderStr);
/*  81 */     if (ur == null) {
/*  82 */       ur = doUpload(request, typeStr, currentFolderStr, mark);
/*     */     }
/*  84 */     PrintWriter out = response.getWriter();
/*  85 */     out.print(ur);
/*  86 */     out.flush();
/*  87 */     out.close();
/*     */   }
/*     */ 
/*     */   private UploadResponse doUpload(HttpServletRequest request, String typeStr, String currentFolderStr, Boolean mark) throws Exception
/*     */   {
/*  92 */     ResourceType type = ResourceType.getDefaultResourceType(typeStr);
/*     */     try {
/*  94 */       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
/*     */ 
/*  96 */       MultipartFile uplFile = 
/*  97 */         (MultipartFile)((Map.Entry)multipartRequest.getFileMap().entrySet()
/*  97 */         .iterator().next()).getValue();
/*     */ 
/* 100 */       String filename = FilenameUtils.getName(uplFile
/* 101 */         .getOriginalFilename());
/* 102 */       log.debug("Parameter NewFile: {}", filename);
/* 103 */       String ext = FilenameUtils.getExtension(filename);
/* 104 */       if (type.isDeniedExtension(ext)) {
/* 105 */         return UploadResponse.getInvalidFileTypeError(request);
/*     */       }
/* 107 */       if ((type.equals(ResourceType.IMAGE)) && 
/* 108 */         (!ImageUtils.isImage(uplFile.getInputStream()))) {
/* 109 */         return UploadResponse.getInvalidFileTypeError(request);
/*     */       }
/*     */ 
/* 112 */       CmsSite site = CmsUtils.getSite(request);
/* 113 */       MarkConfig conf = site.getConfig().getMarkConfig();
/* 114 */       if (mark == null) {
/* 115 */         mark = conf.getOn();
/*     */       }
/* 117 */       boolean isImg = type.equals(ResourceType.IMAGE);
/*     */       String fileUrl;
/* 118 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/* 119 */         if ((mark.booleanValue()) && (isImg)) {
/* 120 */           File tempFile = mark(uplFile, conf);
/* 121 */           fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 122 */             new FileInputStream(tempFile));
/* 123 */           tempFile.delete();
/*     */         } else {
/* 125 */           fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 126 */             uplFile.getInputStream());
/*     */         }
/*     */ 
/* 129 */         String dbFilePath = site.getConfig().getDbFileUri();
/* 130 */         fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/* 131 */       } else if (site.getUploadFtp() != null) {
/* 132 */         Ftp ftp = site.getUploadFtp();
/* 133 */         if ((mark.booleanValue()) && (isImg)) {
/* 134 */           File tempFile = mark(uplFile, conf);
/* 135 */           fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 136 */             new FileInputStream(tempFile));
/* 137 */           tempFile.delete();
/*     */         } else {
/* 139 */           fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 140 */             uplFile.getInputStream());
/*     */         }
/*     */ 
/* 143 */         fileUrl = ftp.getUrl() + fileUrl;
/*     */       } else {
/* 145 */         if ((mark.booleanValue()) && (isImg)) {
/* 146 */           File tempFile = mark(uplFile, conf);
/* 147 */           fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), 
/* 148 */             ext, tempFile);
/* 149 */           tempFile.delete();
/*     */         } else {
/* 151 */           fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), 
/* 152 */             ext, uplFile);
/*     */         }
/*     */ 
/* 155 */         fileUrl = request.getContextPath() + fileUrl;
/*     */       }
/* 157 */       return UploadResponse.getOK(request, fileUrl); } catch (IOException e) {
/*     */     }
/* 159 */     return UploadResponse.getFileUploadWriteError(request);
/*     */   }
/*     */ 
/*     */   private void responseInit(HttpServletResponse response)
/*     */   {
/* 164 */     response.setCharacterEncoding("UTF-8");
/* 165 */     response.setContentType("text/html");
/* 166 */     response.setHeader("Cache-Control", "no-cache");
/*     */   }
/*     */ 
/*     */   private UploadResponse validateUpload(HttpServletRequest request, String commandStr, String typeStr, String currentFolderStr)
/*     */   {
/* 172 */     if (!Command.isValidForPost(commandStr)) {
/* 173 */       return UploadResponse.getInvalidCommandError(request);
/*     */     }
/* 175 */     if (!ResourceType.isValidType(typeStr)) {
/* 176 */       return UploadResponse.getInvalidResourceTypeError(request);
/*     */     }
/* 178 */     if (!UploadUtils.isValidPath(currentFolderStr)) {
/* 179 */       return UploadResponse.getInvalidCurrentFolderError(request);
/*     */     }
/* 181 */     return null;
/*     */   }
/*     */ 
/*     */   private File mark(MultipartFile file, MarkConfig conf) throws Exception {
/* 185 */     String path = System.getProperty("java.io.tmpdir");
/* 186 */     File tempFile = new File(path, String.valueOf(
/* 187 */       System.currentTimeMillis()));
/* 188 */     file.transferTo(tempFile);
/* 189 */     boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
/* 190 */     if (imgMark) {
/* 191 */       File markImg = new File(this.realPathResolver.get(conf.getImagePath()));
/* 192 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 193 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 194 */         conf.getOffsetY().intValue(), markImg);
/*     */     } else {
/* 196 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 197 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 198 */         conf.getOffsetY().intValue(), conf.getContent(), Color.decode(
/* 199 */         conf.getColor()), conf.getSize().intValue(), conf.getAlpha().intValue());
/*     */     }
/* 201 */     return tempFile;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository)
/*     */   {
/* 211 */     this.fileRepository = fileRepository;
/*     */   }
/*     */   @Autowired
/*     */   public void setDbFileMng(DbFileMng dbFileMng) {
/* 216 */     this.dbFileMng = dbFileMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setImageScale(ImageScale imageScale) {
/* 221 */     this.imageScale = imageScale;
/*     */   }
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver) {
/* 226 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.FckAct
 * JD-Core Version:    0.6.0
 */