/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsSiteDao;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsResourceMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteCompanyMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserSiteMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.core.manager.FtpMng;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsSiteMngImpl
/*     */   implements CmsSiteMng
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(CmsSiteMngImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserMng cmsUserMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserSiteMng cmsUserSiteMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsResourceMng cmsResourceMng;
/*     */ 
/*     */   @Autowired
/*     */   private FtpMng ftpMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteCompanyMng siteCompanyMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteDao dao;
/*     */ 
/*  32 */   @Transactional(readOnly=true)
/*     */   public List<CmsSite> getList() { return this.dao.getList(false); }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsSite> getListFromCache() {
/*  37 */     return this.dao.getList(true);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsSite findByDomain(String domain, boolean cacheable) {
/*  42 */     return this.dao.findByDomain(domain, cacheable);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsSite findById(Integer id) {
/*  47 */     CmsSite entity = this.dao.findById(id);
/*  48 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsSite save(CmsSite currSite, CmsUser currUser, CmsSite bean, Integer uploadFtpId) throws IOException
/*     */   {
/*  53 */     if (uploadFtpId != null) {
/*  54 */       bean.setUploadFtp(this.ftpMng.findById(uploadFtpId));
/*     */     }
/*  56 */     bean.init();
/*  57 */     this.dao.save(bean);
/*     */ 
/*  59 */     this.cmsResourceMng.copyTplAndRes(currSite, bean);
/*     */ 
/*  61 */     this.cmsUserMng.addSiteToUser(currUser, bean, bean.getFinalStep());
/*     */ 
/*  63 */     CmsSiteCompany company = new CmsSiteCompany();
/*  64 */     company.setName(bean.getName());
/*  65 */     this.siteCompanyMng.save(bean, company);
/*  66 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsSite update(CmsSite bean, Integer uploadFtpId) {
/*  70 */     CmsSite entity = findById(bean.getId());
/*  71 */     if (uploadFtpId != null)
/*  72 */       entity.setUploadFtp(this.ftpMng.findById(uploadFtpId));
/*     */     else {
/*  74 */       entity.setUploadFtp(null);
/*     */     }
/*  76 */     Updater updater = new Updater(bean);
/*  77 */     entity = this.dao.updateByUpdater(updater);
/*  78 */     return entity;
/*     */   }
/*     */ 
/*     */   public void updateTplSolution(Integer siteId, String solution) {
/*  82 */     CmsSite site = findById(siteId);
/*  83 */     site.setTplSolution(solution);
/*     */   }
/*     */ 
/*     */   public CmsSite deleteById(Integer id)
/*     */   {
/*  88 */     this.cmsUserSiteMng.deleteBySiteId(id);
/*  89 */     CmsSite bean = this.dao.deleteById(id);
/*     */     try
/*     */     {
/*  92 */       this.cmsResourceMng.delTplAndRes(bean);
/*     */     } catch (IOException e) {
/*  94 */       log.error("delete Template and Resource fail!", e);
/*     */     }
/*  96 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsSite[] deleteByIds(Integer[] ids) {
/* 100 */     CmsSite[] beans = new CmsSite[ids.length];
/* 101 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 102 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 104 */     return beans;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsSiteMngImpl
 * JD-Core Version:    0.6.0
 */