/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsFileDao;
/*     */ import com.jeecms.cms.entity.assist.CmsFile;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.assist.CmsFileMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsFileMngImpl
/*     */   implements CmsFileMng
/*     */ {
/*     */   private CmsFileDao dao;
/*     */ 
/*     */   public CmsFile deleteById(Integer id)
/*     */   {
/*  21 */     return this.dao.deleteById(id);
/*     */   }
/*     */ 
/*     */   public CmsFile deleteByPath(String path) {
/*  25 */     return this.dao.deleteByPath(path);
/*     */   }
/*     */ 
/*     */   public void deleteByContentId(Integer contentId) {
/*  29 */     this.dao.deleteByContentId(contentId);
/*     */   }
/*     */ 
/*     */   public CmsFile findById(Integer id) {
/*  33 */     return this.dao.findById(id);
/*     */   }
/*     */ 
/*     */   public CmsFile findByPath(String path) {
/*  37 */     return this.dao.findByPath(path);
/*     */   }
/*     */ 
/*     */   public List<CmsFile> getList(Boolean valid) {
/*  41 */     return this.dao.getList(valid);
/*     */   }
/*     */ 
/*     */   public CmsFile save(CmsFile bean) {
/*  45 */     return this.dao.save(bean);
/*     */   }
/*     */ 
/*     */   public void saveFileByPath(String filepath, String name, Boolean valid) {
/*  49 */     CmsFile attFile = new CmsFile();
/*  50 */     attFile.setFilePath(filepath);
/*  51 */     attFile.setFileName(name);
/*  52 */     attFile.setFileIsvalid(valid.booleanValue());
/*  53 */     save(attFile);
/*     */   }
/*     */ 
/*     */   public void updateFileByPath(String path, Boolean valid, Content c)
/*     */   {
/*  58 */     CmsFile file = findByPath(path);
/*  59 */     if (file != null) {
/*  60 */       file.setContent(c);
/*  61 */       file.setFileIsvalid(valid.booleanValue());
/*  62 */       update(file);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateFileByPaths(String[] attachmentPaths, String[] picPaths, String mediaPath, String titleImg, String typeImg, String contentImg, Boolean valid, Content c)
/*     */   {
/*  68 */     if (attachmentPaths != null) {
/*  69 */       for (String att : attachmentPaths) {
/*  70 */         updateFileByPath(att, valid, c);
/*     */       }
/*     */     }
/*     */ 
/*  74 */     if (picPaths != null) {
/*  75 */       for (String pic : picPaths) {
/*  76 */         updateFileByPath(pic, valid, c);
/*     */       }
/*     */     }
/*     */ 
/*  80 */     if (StringUtils.isNotBlank(mediaPath)) {
/*  81 */       updateFileByPath(mediaPath, valid, c);
/*     */     }
/*     */ 
/*  84 */     if (StringUtils.isNotBlank(titleImg)) {
/*  85 */       updateFileByPath(titleImg, valid, c);
/*     */     }
/*     */ 
/*  88 */     if (StringUtils.isNotBlank(typeImg)) {
/*  89 */       updateFileByPath(typeImg, valid, c);
/*     */     }
/*     */ 
/*  92 */     if (StringUtils.isNotBlank(contentImg))
/*  93 */       updateFileByPath(contentImg, valid, c);
/*     */   }
/*     */ 
/*     */   public CmsFile update(CmsFile bean)
/*     */   {
/*  98 */     Updater updater = new Updater(bean);
/*  99 */     bean = this.dao.updateByUpdater(updater);
/* 100 */     return bean;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsFileDao dao)
/*     */   {
/* 108 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsFileMngImpl
 * JD-Core Version:    0.6.0
 */