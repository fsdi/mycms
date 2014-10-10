/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsSiteCompanyDao;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsSiteCompany;
/*    */ import com.jeecms.cms.manager.main.CmsSiteCompanyMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsSiteCompanyMngImpl
/*    */   implements CmsSiteCompanyMng
/*    */ {
/*    */   private CmsSiteCompanyDao dao;
/*    */ 
/*    */   public CmsSiteCompany save(CmsSite site, CmsSiteCompany bean)
/*    */   {
/* 17 */     site.setSiteCompany(bean);
/* 18 */     bean.setSite(site);
/* 19 */     this.dao.save(bean);
/* 20 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsSiteCompany update(CmsSiteCompany bean) {
/* 24 */     Updater updater = new Updater(bean);
/* 25 */     bean = this.dao.updateByUpdater(updater);
/* 26 */     return bean;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsSiteCompanyDao dao)
/*    */   {
/* 33 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsSiteCompanyMngImpl
 * JD-Core Version:    0.6.0
 */