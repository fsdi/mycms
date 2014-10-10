/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsFriendlinkCtgDao;
/*    */ import com.jeecms.cms.entity.assist.CmsFriendlinkCtg;
/*    */ import com.jeecms.cms.manager.assist.CmsFriendlinkCtgMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsFriendlinkCtgMngImpl
/*    */   implements CmsFriendlinkCtgMng
/*    */ {
/*    */   private CmsFriendlinkCtgDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsFriendlinkCtg> getList(Integer siteId)
/*    */   {
/* 19 */     return this.dao.getList(siteId);
/*    */   }
/*    */ 
/*    */   public int countBySiteId(Integer siteId) {
/* 23 */     return this.dao.countBySiteId(siteId);
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsFriendlinkCtg findById(Integer id) {
/* 28 */     CmsFriendlinkCtg entity = this.dao.findById(id);
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg save(CmsFriendlinkCtg bean) {
/* 33 */     this.dao.save(bean);
/* 34 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg update(CmsFriendlinkCtg bean) {
/* 38 */     Updater updater = new Updater(bean);
/* 39 */     bean = this.dao.updateByUpdater(updater);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public void updateFriendlinkCtgs(Integer[] ids, String[] names, Integer[] priorities)
/*    */   {
/* 45 */     if ((ids == null) || (ids.length == 0)) {
/* 46 */       return;
/*    */     }
/*    */ 
/* 49 */     for (int i = 0; i < ids.length; i++) {
/* 50 */       CmsFriendlinkCtg ctg = this.dao.findById(ids[i]);
/* 51 */       ctg.setName(names[i]);
/* 52 */       ctg.setPriority(priorities[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg deleteById(Integer id) {
/* 57 */     CmsFriendlinkCtg bean = this.dao.deleteById(id);
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsFriendlinkCtg[] deleteByIds(Integer[] ids) {
/* 62 */     CmsFriendlinkCtg[] beans = new CmsFriendlinkCtg[ids.length];
/* 63 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 64 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 66 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsFriendlinkCtgDao dao)
/*    */   {
/* 73 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsFriendlinkCtgMngImpl
 * JD-Core Version:    0.6.0
 */