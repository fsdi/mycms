/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsConfigDao;
/*    */ import com.jeecms.cms.entity.main.CmsConfig;
/*    */ import com.jeecms.cms.entity.main.MarkConfig;
/*    */ import com.jeecms.cms.entity.main.MemberConfig;
/*    */ import com.jeecms.cms.manager.main.CmsConfigMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.Date;
/*    */ import java.util.Map;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsConfigMngImpl
/*    */   implements CmsConfigMng
/*    */ {
/*    */   private CmsConfigDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public CmsConfig get()
/*    */   {
/* 21 */     CmsConfig entity = this.dao.findById(Integer.valueOf(1));
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public void updateCountCopyTime(Date d) {
/* 26 */     this.dao.findById(Integer.valueOf(1)).setCountCopyTime(d);
/*    */   }
/*    */ 
/*    */   public void updateCountClearTime(Date d) {
/* 30 */     this.dao.findById(Integer.valueOf(1)).setCountClearTime(d);
/*    */   }
/*    */ 
/*    */   public CmsConfig update(CmsConfig bean) {
/* 34 */     Updater updater = new Updater(bean);
/* 35 */     CmsConfig entity = this.dao.updateByUpdater(updater);
/* 36 */     entity.blankToNull();
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   public MarkConfig updateMarkConfig(MarkConfig mark) {
/* 41 */     get().setMarkConfig(mark);
/* 42 */     return mark;
/*    */   }
/*    */ 
/*    */   public void updateMemberConfig(MemberConfig memberConfig) {
/* 46 */     get().getAttr().putAll(memberConfig.getAttr());
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsConfigDao dao)
/*    */   {
/* 53 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsConfigMngImpl
 * JD-Core Version:    0.6.0
 */