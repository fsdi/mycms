/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.ContentTxtDao;
/*    */ import com.jeecms.cms.entity.main.Content;
/*    */ import com.jeecms.cms.entity.main.ContentTxt;
/*    */ import com.jeecms.cms.manager.main.ContentTxtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ContentTxtMngImpl
/*    */   implements ContentTxtMng
/*    */ {
/*    */   private ContentTxtDao dao;
/*    */ 
/*    */   public ContentTxt save(ContentTxt txt, Content content)
/*    */   {
/* 17 */     if (txt.isAllBlank()) {
/* 18 */       return null;
/*    */     }
/* 20 */     txt.setContent(content);
/* 21 */     txt.init();
/* 22 */     this.dao.save(txt);
/* 23 */     content.setContentTxt(txt);
/* 24 */     return txt;
/*    */   }
/*    */ 
/*    */   public ContentTxt update(ContentTxt txt, Content content)
/*    */   {
/* 29 */     ContentTxt entity = this.dao.findById(content.getId());
/* 30 */     if (entity == null) {
/* 31 */       entity = save(txt, content);
/* 32 */       content.getContentTxtSet().add(entity);
/* 33 */       return entity;
/*    */     }
/* 35 */     if (txt.isAllBlank()) {
/* 36 */       content.getContentTxtSet().clear();
/* 37 */       return null;
/*    */     }
/* 39 */     Updater updater = new Updater(txt);
/* 40 */     entity = this.dao.updateByUpdater(updater);
/* 41 */     entity.blankToNull();
/* 42 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ContentTxtDao dao)
/*    */   {
/* 51 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentTxtMngImpl
 * JD-Core Version:    0.6.0
 */