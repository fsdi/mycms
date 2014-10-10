/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsUserExtDao;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.entity.main.CmsUserExt;
/*    */ import com.jeecms.cms.manager.main.CmsUserExtMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsUserExtMngImpl
/*    */   implements CmsUserExtMng
/*    */ {
/*    */   private CmsUserExtDao dao;
/*    */ 
/*    */   public CmsUserExt save(CmsUserExt ext, CmsUser user)
/*    */   {
/* 17 */     ext.blankToNull();
/* 18 */     ext.setUser(user);
/* 19 */     this.dao.save(ext);
/* 20 */     return ext;
/*    */   }
/*    */ 
/*    */   public CmsUserExt update(CmsUserExt ext, CmsUser user) {
/* 24 */     CmsUserExt entity = this.dao.findById(user.getId());
/* 25 */     if (entity == null) {
/* 26 */       entity = save(ext, user);
/* 27 */       user.getUserExtSet().add(entity);
/* 28 */       return entity;
/*    */     }
/* 30 */     Updater updater = new Updater(ext);
/* 31 */     updater.include("gender");
/* 32 */     updater.include("birthday");
/* 33 */     ext = this.dao.updateByUpdater(updater);
/* 34 */     ext.blankToNull();
/* 35 */     return ext;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsUserExtDao dao)
/*    */   {
/* 43 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsUserExtMngImpl
 * JD-Core Version:    0.6.0
 */