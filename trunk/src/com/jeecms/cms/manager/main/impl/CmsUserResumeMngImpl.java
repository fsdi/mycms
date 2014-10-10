/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsUserResumeDao;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.entity.main.CmsUserResume;
/*    */ import com.jeecms.cms.manager.main.CmsUserResumeMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsUserResumeMngImpl
/*    */   implements CmsUserResumeMng
/*    */ {
/*    */   private CmsUserResumeDao dao;
/*    */ 
/*    */   public CmsUserResume save(CmsUserResume resume, CmsUser user)
/*    */   {
/* 17 */     resume.setUser(user);
/* 18 */     this.dao.save(resume);
/* 19 */     return resume;
/*    */   }
/*    */ 
/*    */   public CmsUserResume update(CmsUserResume ext, CmsUser user) {
/* 23 */     CmsUserResume entity = this.dao.findById(user.getId());
/* 24 */     if (entity == null) {
/* 25 */       entity = save(ext, user);
/* 26 */       user.getUserResumeSet().add(entity);
/* 27 */       return entity;
/*    */     }
/* 29 */     Updater updater = new Updater(ext);
/* 30 */     ext = this.dao.updateByUpdater(updater);
/* 31 */     return ext;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsUserResumeDao dao)
/*    */   {
/* 39 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsUserResumeMngImpl
 * JD-Core Version:    0.6.0
 */