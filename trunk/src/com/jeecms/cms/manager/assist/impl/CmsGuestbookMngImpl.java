/*     */ package com.jeecms.cms.manager.assist.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.assist.CmsGuestbookDao;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookExtMng;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsGuestbookMngImpl
/*     */   implements CmsGuestbookMng
/*     */ {
/*     */   private CmsGuestbookCtgMng cmsGuestbookCtgMng;
/*     */   private CmsGuestbookExtMng cmsGuestbookExtMng;
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsGuestbookDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(Integer siteId, Integer ctgId, Integer userId, Boolean recommend, Boolean checked, boolean desc, boolean cacheable, int pageNo, int pageSize)
/*     */   {
/*  28 */     return this.dao.getPage(siteId, ctgId, userId, recommend, checked, desc, cacheable, 
/*  29 */       pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<CmsGuestbook> getList(Integer siteId, Integer ctgId, Boolean recommend, Boolean checked, boolean desc, boolean cacheable, int first, int max)
/*     */   {
/*  36 */     return this.dao.getList(siteId, ctgId, recommend, checked, desc, cacheable, 
/*  37 */       first, max);
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsGuestbook findById(Integer id) {
/*  42 */     CmsGuestbook entity = this.dao.findById(id);
/*  43 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsGuestbook save(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId, String ip)
/*     */   {
/*  48 */     bean.setCtg(this.cmsGuestbookCtgMng.findById(ctgId));
/*  49 */     bean.setIp(ip);
/*  50 */     bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
/*  51 */     bean.init();
/*  52 */     this.dao.save(bean);
/*  53 */     this.cmsGuestbookExtMng.save(ext, bean);
/*  54 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGuestbook save(CmsUser member, Integer siteId, Integer ctgId, String ip, String title, String content, String email, String phone, String qq)
/*     */   {
/*  60 */     CmsGuestbook guestbook = new CmsGuestbook();
/*  61 */     guestbook.setMember(member);
/*  62 */     guestbook.setSite(this.cmsSiteMng.findById(siteId));
/*  63 */     guestbook.setIp(ip);
/*  64 */     CmsGuestbookExt ext = new CmsGuestbookExt();
/*  65 */     ext.setTitle(title);
/*  66 */     ext.setContent(content);
/*  67 */     ext.setEmail(email);
/*  68 */     ext.setPhone(phone);
/*  69 */     ext.setQq(qq);
/*  70 */     return save(guestbook, ext, ctgId, ip);
/*     */   }
/*     */ 
/*     */   public CmsGuestbook update(CmsGuestbook bean, CmsGuestbookExt ext, Integer ctgId)
/*     */   {
/*  75 */     Updater updater = new Updater(bean);
/*  76 */     bean = this.dao.updateByUpdater(updater);
/*  77 */     bean.setCtg(this.cmsGuestbookCtgMng.findById(ctgId));
/*  78 */     this.cmsGuestbookExtMng.update(ext);
/*  79 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGuestbook deleteById(Integer id) {
/*  83 */     CmsGuestbook bean = this.dao.deleteById(id);
/*  84 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsGuestbook[] deleteByIds(Integer[] ids) {
/*  88 */     CmsGuestbook[] beans = new CmsGuestbook[ids.length];
/*  89 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  90 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  92 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(CmsGuestbookDao dao)
/*     */   {
/* 102 */     this.dao = dao;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsGuestbookExtMng(CmsGuestbookExtMng cmsGuestbookExtMng) {
/* 107 */     this.cmsGuestbookExtMng = cmsGuestbookExtMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsGuestbookCtgMng(CmsGuestbookCtgMng cmsGuestbookCtgMng) {
/* 112 */     this.cmsGuestbookCtgMng = cmsGuestbookCtgMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng) {
/* 117 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsGuestbookMngImpl
 * JD-Core Version:    0.6.0
 */