/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsCommentExtDao;
/*    */ import com.jeecms.cms.entity.assist.CmsComment;
/*    */ import com.jeecms.cms.entity.assist.CmsCommentExt;
/*    */ import com.jeecms.cms.manager.assist.CmsCommentExtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsCommentExtMngImpl
/*    */   implements CmsCommentExtMng
/*    */ {
/*    */   private CmsCommentExtDao dao;
/*    */ 
/*    */   public CmsCommentExt save(String ip, String text, CmsComment comment)
/*    */   {
/* 17 */     CmsCommentExt ext = new CmsCommentExt();
/* 18 */     ext.setText(text);
/* 19 */     ext.setIp(ip);
/* 20 */     ext.setComment(comment);
/* 21 */     comment.setCommentExt(ext);
/* 22 */     this.dao.save(ext);
/* 23 */     return ext;
/*    */   }
/*    */ 
/*    */   public CmsCommentExt update(CmsCommentExt bean) {
/* 27 */     Updater updater = new Updater(bean);
/* 28 */     bean = this.dao.updateByUpdater(updater);
/* 29 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteByContentId(Integer contentId) {
/* 33 */     return this.dao.deleteByContentId(contentId);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsCommentExtDao dao)
/*    */   {
/* 40 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsCommentExtMngImpl
 * JD-Core Version:    0.6.0
 */