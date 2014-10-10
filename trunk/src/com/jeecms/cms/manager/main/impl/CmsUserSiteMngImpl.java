/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsUserSiteDao;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserSite;
/*     */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserSiteMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsUserSiteMngImpl
/*     */   implements CmsUserSiteMng
/*     */ {
/*     */   private CmsSiteMng cmsSiteMng;
/*     */   private CmsUserSiteDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public CmsUserSite findById(Integer id)
/*     */   {
/*  24 */     CmsUserSite entity = this.dao.findById(id);
/*  25 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsUserSite save(CmsSite site, CmsUser user, Byte step, Boolean allChannel)
/*     */   {
/*  30 */     CmsUserSite bean = new CmsUserSite();
/*  31 */     bean.setSite(site);
/*  32 */     bean.setUser(user);
/*  33 */     bean.setCheckStep(step);
/*  34 */     bean.setAllChannel(allChannel);
/*  35 */     this.dao.save(bean);
/*  36 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsUserSite update(CmsUserSite bean) {
/*  40 */     Updater updater = new Updater(bean);
/*  41 */     bean = this.dao.updateByUpdater(updater);
/*  42 */     return bean;
/*     */   }
/*     */ 
/*     */   public void updateByUser(CmsUser user, Integer siteId, Byte step, Boolean allChannel)
/*     */   {
/*  47 */     Set<CmsUserSite> uss = user.getUserSites();
/*  48 */     if ((siteId == null) || (step == null) || (allChannel == null)) {
/*  49 */       return;
/*     */     }
/*     */ 
/*  52 */     for (CmsUserSite us : uss)
/*  53 */       if (siteId.equals(us.getSite().getId())) {
/*  54 */         us.setCheckStep(step);
/*  55 */         us.setAllChannel(allChannel);
/*     */       }
/*     */   }
/*     */ 
/*     */   public void updateByUser(CmsUser user, Integer[] siteIds, Byte[] steps, Boolean[] allChannels)
/*     */   {
/*  62 */     Set<CmsUserSite> uss = user.getUserSites();
/*     */ 
/*  64 */     if (siteIds == null) {
/*  65 */       user.getUserSites().clear();
/*  66 */       for (CmsUserSite us : uss) {
/*  67 */         this.dao.delete(us);
/*     */       }
/*  69 */       return;
/*     */     }
/*     */ 
/*  72 */     Set toDel = new HashSet();
/*     */ 
/*  75 */     for (CmsUserSite us : uss) {
/*  76 */       boolean contains = false;
/*  77 */       for (int i = 0; i < siteIds.length; i++) {
/*  78 */         if (siteIds[i].equals(us.getSite().getId())) {
/*  79 */           contains = true;
/*  83 */       if (contains) {
/*  84 */         us.setCheckStep(steps[i]);
/*  85 */         us.setAllChannel(allChannels[i]);
/*     */       } else {
/*  87 */         toDel.add(us);
/*     */       }
/*  80 */           break;
/*     */         }
/*     */       }
/*     */     }
/*  90 */     delete(toDel, uss);
/*     */ 
/*  92 */     int i = 0;
/*  93 */     Set toSave = new HashSet();
/*  94 */     for (Integer sid : siteIds) {
/*  95 */       boolean contains = false;
/*  96 */       for (CmsUserSite us : uss) {
/*  97 */         if (us.getSite().getId().equals(sid)) {
/*  98 */           contains = true;
/*  99 */           break;
/*     */         }
/*     */       }
/* 102 */       if (!contains) {
/* 103 */         toSave.add(
/* 104 */           save(this.cmsSiteMng.findById(sid), user, steps[i], 
/* 104 */           allChannels[i]));
/*     */       }
/* 106 */       i++;
/*     */     }
/* 108 */     uss.addAll(toSave);
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/* 112 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   private void delete(Collection<CmsUserSite> coll, Set<CmsUserSite> set) {
/* 116 */     if (coll == null) {
/* 117 */       return;
/*     */     }
/* 119 */     for (CmsUserSite us : coll) {
/* 120 */       this.dao.delete(us);
/* 121 */       set.remove(us);
/*     */     }
/*     */   }
/*     */ 
/*     */   public CmsUserSite deleteById(Integer id) {
/* 126 */     CmsUserSite bean = this.dao.deleteById(id);
/* 127 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsUserSite[] deleteByIds(Integer[] ids) {
/* 131 */     CmsUserSite[] beans = new CmsUserSite[ids.length];
/* 132 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 133 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 135 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsSiteMng(CmsSiteMng cmsSiteMng)
/*     */   {
/* 143 */     this.cmsSiteMng = cmsSiteMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsUserSiteDao dao) {
/* 148 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsUserSiteMngImpl
 * JD-Core Version:    0.6.0
 */