/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentExtDao;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.entity.main.ContentExt;
/*    */ import com.jeecms.cms.manager.main.ContentExtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ContentExtMngImpl
/*    */   implements ContentExtMng
/*    */ {
/*    */   private ContentExtDao dao;
/*    */ 
/*    */   public ContentExt save(ContentExt ext, Content content)
/*    */   {
/* 17 */     content.setContentExt(ext);
/* 18 */     ext.setContent(content);
/* 19 */     if (ext.getReleaseDate() == null) {
/* 20 */       ext.setReleaseDate(content.getSortDate());
/*    */     }
/* 22 */     ext.init();
/* 23 */     this.dao.save(ext);
/* 24 */     content.setContentExt(ext);
/* 25 */     return ext;
/*    */   }
/*    */ 
/*    */   public ContentExt update(ContentExt bean) {
/* 29 */     Updater updater = new Updater(bean);
/* 30 */     bean = this.dao.updateByUpdater(updater);
/* 31 */     bean.blankToNull();
/*    */ 
/* 33 */     bean.setNeedRegenerate(Boolean.valueOf(true));
/* 34 */     return bean;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ContentExtDao dao)
/*    */   {
/* 41 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentExtMngImpl
 * JD-Core Version:    0.6.0
 */