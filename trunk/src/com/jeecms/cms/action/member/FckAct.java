/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MarkConfig;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
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
/*  49 */   private static final Logger log = LoggerFactory.getLogger(FckAct.class);
/*     */   private CmsUserMng cmsUserMng;
/*     */   private FileRepository fileRepository;
/*     */   private DbFileMng dbFileMng;
/*     */   private ImageScale imageScale;
/*     */   private RealPathResolver realPathResolver;
/*     */ 
/*     */   @RequestMapping(value={"/fck/upload.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void upload(@RequestParam(value="Command", required=false) String commandStr, @RequestParam(value="Type", required=false) String typeStr, @RequestParam(value="CurrentFolder", required=false) String currentFolderStr, @RequestParam(value="mark", required=false) Boolean mark, HttpServletRequest request, HttpServletResponse response)
/*     */     throws Exception
/*     */   {
/*  59 */     log.debug("Entering Dispatcher#doPost");
/*  60 */     responseInit(response);
/*  61 */     if ((Utils.isEmpty(commandStr)) && (Utils.isEmpty(currentFolderStr))) {
/*  62 */       commandStr = "QuickUpload";
/*  63 */       currentFolderStr = "/";
/*  64 */       if (Utils.isEmpty(typeStr)) {
/*  65 */         typeStr = "File";
/*     */       }
/*     */     }
/*  68 */     if ((currentFolderStr != null) && (!currentFolderStr.startsWith("/"))) {
/*  69 */       currentFolderStr = "/".concat(currentFolderStr);
/*     */     }
/*  71 */     log.debug("Parameter Command: {}", commandStr);
/*  72 */     log.debug("Parameter Type: {}", typeStr);
/*  73 */     log.debug("Parameter CurrentFolder: {}", currentFolderStr);
/*  74 */     UploadResponse ur = validateUpload(request, commandStr, typeStr, 
/*  75 */       currentFolderStr);
/*  76 */     if (ur == null) {
/*  77 */       ur = doUpload(request, typeStr, currentFolderStr, mark);
/*     */     }
/*  79 */     PrintWriter out = response.getWriter();
/*  80 */     out.print(ur);
/*  81 */     out.flush();
/*  82 */     out.close();
/*     */   }
/*     */ 
/*     */   private UploadResponse doUpload(HttpServletRequest request, String typeStr, String currentFolderStr, Boolean mark) throws Exception
/*     */   {
/*  87 */     ResourceType type = ResourceType.getDefaultResourceType(typeStr);
/*     */     try {
/*  89 */       MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
/*     */ 
/*  91 */       MultipartFile uplFile = 
/*  92 */         (MultipartFile)((Map.Entry)multipartRequest.getFileMap().entrySet()
/*  92 */         .iterator().next()).getValue();
/*  93 */       CmsUser user = CmsUtils.getUser(request);
/*  94 */       int fileSize = (int)(uplFile.getSize() / 1024L);
/*     */ 
/*  96 */       if (!user.isAllowMaxFile(fileSize)) {
/*  97 */         log.warn("member fck upload warn: not allow max file: {}", 
/*  98 */           Integer.valueOf(fileSize));
/*  99 */         return UploadResponse.getFileUploadDisabledError(request);
/*     */       }
/*     */ 
/* 102 */       if (!user.isAllowPerDay(fileSize)) {
/* 103 */         log.warn("member fck upload warn: not allow per day: {}", 
/* 104 */           Integer.valueOf(fileSize));
/* 105 */         return UploadResponse.getFileUploadDisabledError(request);
/*     */       }
/*     */ 
/* 109 */       String filename = FilenameUtils.getName(uplFile
/* 110 */         .getOriginalFilename());
/* 111 */       log.debug("Parameter NewFile: {}", filename);
/* 112 */       String ext = FilenameUtils.getExtension(filename);
/*     */ 
/* 114 */       if (!user.isAllowSuffix(ext)) {
/* 115 */         log.warn("member fck upload warn: not allow file extension: {}", 
/* 116 */           ext);
/* 117 */         return UploadResponse.getFileUploadDisabledError(request);
/*     */       }
/* 119 */       if (type.isDeniedExtension(ext)) {
/* 120 */         return UploadResponse.getInvalidFileTypeError(request);
/*     */       }
/* 122 */       if ((type.equals(ResourceType.IMAGE)) && 
/* 123 */         (!ImageUtils.isImage(uplFile.getInputStream()))) {
/* 124 */         return UploadResponse.getInvalidFileTypeError(request);
/*     */       }
/*     */ 
/* 127 */       CmsSite site = CmsUtils.getSite(request);
/* 128 */       MarkConfig conf = site.getConfig().getMarkConfig();
/* 129 */       if (mark == null) {
/* 130 */         mark = conf.getOn();
/*     */       }
/* 132 */       boolean isImg = type.equals(ResourceType.IMAGE);
/*     */       String fileUrl;
/* 133 */       if (site.getConfig().getUploadToDb().booleanValue()) {
/* 134 */         if ((mark.booleanValue()) && (isImg)) {
/* 135 */           File tempFile = mark(uplFile, conf);
/* 136 */           fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 137 */             new FileInputStream(tempFile));
/* 138 */           tempFile.delete();
/*     */         } else {
/* 140 */           fileUrl = this.dbFileMng.storeByExt(site.getUploadPath(), ext, 
/* 141 */             uplFile.getInputStream());
/*     */         }
/*     */ 
/* 144 */         String dbFilePath = site.getConfig().getDbFileUri();
/* 145 */         fileUrl = request.getContextPath() + dbFilePath + fileUrl;
/* 146 */       } else if (site.getUploadFtp() != null) {
/* 147 */         Ftp ftp = site.getUploadFtp();
/* 148 */         if ((mark.booleanValue()) && (isImg)) {
/* 149 */           File tempFile = mark(uplFile, conf);
/* 150 */           fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 151 */             new FileInputStream(tempFile));
/* 152 */           tempFile.delete();
/*     */         } else {
/* 154 */           fileUrl = ftp.storeByExt(site.getUploadPath(), ext, 
/* 155 */             uplFile.getInputStream());
/*     */         }
/*     */ 
/* 158 */         fileUrl = ftp.getUrl() + fileUrl;
/*     */       } else {
/* 160 */         if ((mark.booleanValue()) && (isImg)) {
/* 161 */           File tempFile = mark(uplFile, conf);
/* 162 */           fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), 
/* 163 */             ext, tempFile);
/* 164 */           tempFile.delete();
/*     */         } else {
/* 166 */           fileUrl = this.fileRepository.storeByExt(site.getUploadPath(), 
/* 167 */             ext, uplFile);
/*     */         }
/*     */ 
/* 170 */         fileUrl = request.getContextPath() + fileUrl;
/*     */       }
/* 172 */       this.cmsUserMng.updateUploadSize(user.getId(), Integer.valueOf(fileSize));
/* 173 */       return UploadResponse.getOK(request, fileUrl); } catch (IOException e) {
/*     */     }
/* 175 */     return UploadResponse.getFileUploadWriteError(request);
/*     */   }
/*     */ 
/*     */   private void responseInit(HttpServletResponse response)
/*     */   {
/* 180 */     response.setCharacterEncoding("UTF-8");
/* 181 */     response.setContentType("text/html");
/* 182 */     response.setHeader("Cache-Control", "no-cache");
/*     */   }
/*     */ 
/*     */   private UploadResponse validateUpload(HttpServletRequest request, String commandStr, String typeStr, String currentFolderStr)
/*     */   {
/* 188 */     if (!Command.isValidForPost(commandStr)) {
/* 189 */       return UploadResponse.getInvalidCommandError(request);
/*     */     }
/* 191 */     if (!ResourceType.isValidType(typeStr)) {
/* 192 */       return UploadResponse.getInvalidResourceTypeError(request);
/*     */     }
/* 194 */     if (!UploadUtils.isValidPath(currentFolderStr)) {
/* 195 */       return UploadResponse.getInvalidCurrentFolderError(request);
/*     */     }
/* 197 */     CmsUser user = CmsUtils.getUser(request);
/*     */ 
/* 199 */     if (user == null) {
/* 200 */       log.warn("member fck upload warn: not logged in.");
/* 201 */       return UploadResponse.getFileUploadDisabledError(request);
/*     */     }
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */   private File mark(MultipartFile file, MarkConfig conf) throws Exception {
/* 207 */     String path = System.getProperty("java.io.tmpdir");
/* 208 */     File tempFile = new File(path, String.valueOf(
/* 209 */       System.currentTimeMillis()));
/* 210 */     file.transferTo(tempFile);
/* 211 */     boolean imgMark = !StringUtils.isBlank(conf.getImagePath());
/* 212 */     if (imgMark) {
/* 213 */       File markImg = new File(this.realPathResolver.get(conf.getImagePath()));
/* 214 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 215 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 216 */         conf.getOffsetY().intValue(), markImg);
/*     */     } else {
/* 218 */       this.imageScale.imageMark(tempFile, tempFile, conf.getMinWidth().intValue(), 
/* 219 */         conf.getMinHeight().intValue(), conf.getPos().intValue(), conf.getOffsetX().intValue(), 
/* 220 */         conf.getOffsetY().intValue(), conf.getContent(), Color.decode(
/* 221 */         conf.getColor()), conf.getSize().intValue(), conf.getAlpha().intValue());
/*     */     }
/* 223 */     return tempFile;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng)
/*     */   {
/* 234 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setFileRepository(FileRepository fileRepository) {
/* 239 */     this.fileRepository = fileRepository;
/*     */   }
/*     */   @Autowired
/*     */   public void setDbFileMng(DbFileMng dbFileMng) {
/* 244 */     this.dbFileMng = dbFileMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setImageScale(ImageScale imageScale) {
/* 249 */     this.imageScale = imageScale;
/*     */   }
/*     */   @Autowired
/*     */   public void setRealPathResolver(RealPathResolver realPathResolver) {
/* 254 */     this.realPathResolver = realPathResolver;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.FckAct
 * JD-Core Version:    0.6.0
 */