/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsCommentDao;
/*     */ import com.jeecms.cms.entity.assist.CmsComment;
/*     */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentExtMng;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*     */ import com.jeecms.cms.manager.assist.CmsSensitivityMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.manager.main.ContentCountMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsCommentMngImpl
/*     */   implements CmsCommentMng
/*     */ {
/*     */   private CmsSensitivityMng cmsSensitivityMng;
/*     */   private CmsUserMng cmsUserMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private ContentMng contentMng;
/*     */   private ContentCountMng contentCountMng;
/*     */   private CmsCommentExtMng cmsCommentExtMng;
/*     */   private CmsCommentDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(Integer siteId, Integer contentId, Integer greaterThen, Boolean checked, boolean recommend, boolean desc, int pageNo, int pageSize)
/*     */   {
/*  29 */     Pagination page = this.dao.getPage(siteId, contentId, greaterThen, checked, 
/*  30 */       recommend, desc, pageNo, pageSize, false);
/*  31 */     return page;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPageForTag(Integer siteId, Integer contentId, Integer greaterThen, Boolean checked, boolean recommend, boolean desc, int pageNo, int pageSize)
/*     */   {
/*  38 */     Pagination page = this.dao.getPage(siteId, contentId, greaterThen, checked, 
/*  39 */       recommend, desc, pageNo, pageSize, true);
/*  40 */     return page;
/*     */   }
/*     */ 
/*     */   public Pagination getPageForMember(Integer siteId, Integer contentId, Integer toUserId, Integer fromUserId, Integer greaterThen, Boolean checked, Boolean recommend, boolean desc, int pageNo, int pageSize)
/*     */   {
/*  45 */     Pagination page = this.dao.getPageForMember(siteId, contentId, toUserId, fromUserId, greaterThen, checked, 
/*  46 */       recommend, desc, pageNo, pageSize, false);
/*  47 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsComment> getListForDel(Integer siteId, Integer userId, Integer commentUserId, String ip) {
/*  52 */     return this.dao.getListForDel(siteId, userId, commentUserId, ip);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsComment> getListForTag(Integer siteId, Integer contentId, Integer greaterThen, Boolean checked, boolean recommend, boolean desc, int count)
/*     */   {
/*  59 */     return this.dao.getList(siteId, contentId, greaterThen, checked, recommend, 
/*  60 */       desc, count, true);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsComment findById(Integer id) {
/*  65 */     CmsComment entity = this.dao.findById(id);
/*  66 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsComment comment(String text, String ip, Integer contentId, Integer siteId, Integer userId, boolean checked, boolean recommend)
/*     */   {
/*  71 */     CmsComment comment = new CmsComment();
/*  72 */     comment.setContent(this.contentMng.findById(contentId));
/*  73 */     comment.setSite(this.cmsSiteMng.findById(siteId));
/*  74 */     if (userId != null) {
/*  75 */       comment.setCommentUser(this.cmsUserMng.findById(userId));
/*     */     }
/*  77 */     comment.setChecked(Boolean.valueOf(checked));
/*  78 */     comment.setRecommend(Boolean.valueOf(recommend));
/*  79 */     comment.init();
/*  80 */     this.dao.save(comment);
/*  81 */     text = this.cmsSensitivityMng.replaceSensitivity(text);
/*  82 */     this.cmsCommentExtMng.save(ip, text, comment);
/*  83 */     this.contentCountMng.commentCount(contentId);
/*  84 */     return comment;
/*     */   }
/*     */ 
/*     */   public CmsComment update(CmsComment bean, CmsCommentExt ext) {
/*  88 */     Updater updater = new Updater(bean);
/*  89 */     bean = this.dao.updateByUpdater(updater);
/*  90 */     this.cmsCommentExtMng.update(ext);
/*  91 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deleteByContentId(Integer contentId) {
/*  95 */     this.cmsCommentExtMng.deleteByContentId(contentId);
/*  96 */     return this.dao.deleteByContentId(contentId);
/*     */   }
/*     */ 
/*     */   public CmsComment deleteById(Integer id) {
/* 100 */     CmsComment bean = this.dao.deleteById(id);
/* 101 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsComment[] deleteByIds(Integer[] ids) {
/* 105 */     CmsComment[] beans = new CmsComment[ids.length];
/* 106 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 107 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 109 */     return beans;
/*     */   }
/*     */ 
/*     */   public void ups(Integer id) {
/* 113 */     CmsComment comment = findById(id);
/* 114 */     comment.setUps(Short.valueOf((short)(comment.getUps().shortValue() + 1)));
/*     */   }
/*     */ 
/*     */   public void downs(Integer id) {
/* 118 */     CmsComment comment = findById(id);
/* 119 */     comment.setDowns(Short.valueOf((short)(comment.getDowns().shortValue() + 1)));
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsSensitivityMng(CmsSensitivityMng cmsSensitivityMng)
/*     */   {
/* 132 */     this.cmsSensitivityMng = cmsSensitivityMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng) {
/* 137 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 142 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentMng(ContentMng contentMng) {
/* 147 */     this.contentMng = contentMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsCommentExtMng(CmsCommentExtMng cmsCommentExtMng) {
/* 152 */     this.cmsCommentExtMng = cmsCommentExtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setContentCountMng(ContentCountMng contentCountMng) {
/* 157 */     this.contentCountMng = contentCountMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsCommentDao dao) {
/* 162 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsCommentMngImpl
 * JD-Core Version:    0.6.0
 */