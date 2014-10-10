/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentCheckDao;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.entity.main.ContentCheck;
/*    */ import com.jeecms.cms.manager.main.ContentCheckMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ContentCheckMngImpl
/*    */   implements ContentCheckMng
/*    */ {
/*    */   private ContentCheckDao dao;
/*    */ 
/*    */   public ContentCheck save(ContentCheck check, Content content)
/*    */   {
/* 17 */     check.setContent(content);
/* 18 */     check.init();
/* 19 */     this.dao.save(check);
/* 20 */     content.setContentCheck(check);
/* 21 */     return check;
/*    */   }
/*    */ 
/*    */   public ContentCheck update(ContentCheck bean) {
/* 25 */     Updater updater = new Updater(bean);
/* 26 */     bean = this.dao.updateByUpdater(updater);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ContentCheckDao dao)
/*    */   {
/* 34 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentCheckMngImpl
 * JD-Core Version:    0.6.0
 */