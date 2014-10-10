/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsFriendlinkDao;
/*    */ import com.jeecms.cms.entity.assist.CmsFriendlink;
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkCtgMng;
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsFriendlinkMngImpl
/*    */   implements CmsFriendlinkMng
/*    */ {
/*    */   private CmsFriendlinkDao dao;
/*    */   private CmsFriendlinkCtgMng cmsFriendlinkCtgMng;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsFriendlink> getList(Integer siteId, Integer ctgId, Boolean enabled)
/*    */   {
/* 21 */     List list = this.dao.getList(siteId, ctgId, enabled);
/* 22 */     return list;
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public int countByCtgId(Integer ctgId) {
/* 27 */     return this.dao.countByCtgId(ctgId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsFriendlink findById(Integer id) {
/* 32 */     CmsFriendlink entity = this.dao.findById(id);
/* 33 */     return entity;
/*    */   }
/*    */ 
/*    */   public int updateViews(Integer id) {
/* 37 */     CmsFriendlink link = findById(id);
/* 38 */     if (link != null) {
/* 39 */       link.setViews(Integer.valueOf(link.getViews().intValue() + 1));
/*    */     }
/* 41 */     return link.getViews().intValue();
/*    */   }
/*    */ 
/*    */   public CmsFriendlink save(CmsFriendlink bean, Integer ctgId) {
/* 45 */     bean.setCategory(this.cmsFriendlinkCtgMng.findById(ctgId));
/* 46 */     bean.init();
/* 47 */     this.dao.save(bean);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlink update(CmsFriendlink bean, Integer ctgId) {
/* 52 */     Updater updater = new Updater(bean);
/* 53 */     bean = this.dao.updateByUpdater(updater);
/* 54 */     if (ctgId != null) {
/* 55 */       bean.setCategory(this.cmsFriendlinkCtgMng.findById(ctgId));
/*    */     }
/* 57 */     bean.blankToNull();
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public void updatePriority(Integer[] ids, Integer[] priorities) {
/* 62 */     if ((ids == null) || (priorities == null) || (ids.length <= 0) || 
/* 63 */       (ids.length != priorities.length)) {
/* 64 */       return;
/*    */     }
/*    */ 
/* 67 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 68 */       CmsFriendlink link = findById(ids[i]);
/* 69 */       link.setPriority(priorities[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */   public CmsFriendlink deleteById(Integer id)
/*    */   {
/* 75 */     CmsFriendlink bean = this.dao.deleteById(id);
/* 76 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlink[] deleteByIds(Integer[] ids) {
/* 80 */     CmsFriendlink[] beans = new CmsFriendlink[ids.length];
/* 81 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 82 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 84 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsFriendlinkDao dao)
/*    */   {
/* 92 */     this.dao = dao;
/*    */   }
/*    */   @Autowired
/*    */   public void setCmsFriendlinkCtgMng(CmsFriendlinkCtgMng cmsFriendlinkCtgMng) {
/* 97 */     this.cmsFriendlinkCtgMng = cmsFriendlinkCtgMng;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsFriendlinkMngImpl
 * JD-Core Version:    0.6.0
 */