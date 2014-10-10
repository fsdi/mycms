/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsGuestbookExtDao;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*    */ import com.jeecms.cms.entity.assist.CmsGuestbookExt;
/*    */ import com.jeecms.cms.manager.assist.CmsGuestbookExtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsGuestbookExtMngImpl
/*    */   implements CmsGuestbookExtMng
/*    */ {
/*    */   private CmsGuestbookExtDao dao;
/*    */ 
/*    */   public CmsGuestbookExt save(CmsGuestbookExt ext, CmsGuestbook guestbook)
/*    */   {
/* 17 */     guestbook.setExt(ext);
/* 18 */     ext.setGuestbook(guestbook);
/* 19 */     ext.init();
/* 20 */     this.dao.save(ext);
/* 21 */     return ext;
/*    */   }
/*    */ 
/*    */   public CmsGuestbookExt update(CmsGuestbookExt ext) {
/* 25 */     Updater updater = new Updater(ext);
/* 26 */     CmsGuestbookExt entity = this.dao.updateByUpdater(updater);
/* 27 */     entity.blankToNull();
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsGuestbookExtDao dao)
/*    */   {
/* 35 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsGuestbookExtMngImpl
 * JD-Core Version:    0.6.0
 */