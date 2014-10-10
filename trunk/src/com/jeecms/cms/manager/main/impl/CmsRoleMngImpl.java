/*    */ package com.jeecms.cms.manager.main.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.main.CmsRoleDao;
/*    */ import com.jeecms.cms.entity.main.CmsRole;
/*    */ import com.jeecms.cms.manager.main.CmsRoleMng;
/*    */ import com.jeecms.common.hibernate3.Updater;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsRoleMngImpl
/*    */   implements CmsRoleMng
/*    */ {
/*    */   private CmsRoleDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<CmsRole> getList()
/*    */   {
/* 20 */     return this.dao.getList();
/*    */   }
/*    */   @Transactional(readOnly=true)
/*    */   public CmsRole findById(Integer id) {
/* 25 */     CmsRole entity = this.dao.findById(id);
/* 26 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsRole save(CmsRole bean, Set<String> perms) {
/* 30 */     bean.setPerms(perms);
/* 31 */     this.dao.save(bean);
/* 32 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsRole update(CmsRole bean, Set<String> perms) {
/* 36 */     Updater updater = new Updater(bean);
/* 37 */     bean = this.dao.updateByUpdater(updater);
/* 38 */     bean.setPerms(perms);
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsRole deleteById(Integer id) {
/* 43 */     CmsRole bean = this.dao.deleteById(id);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsRole[] deleteByIds(Integer[] ids) {
/* 48 */     CmsRole[] beans = new CmsRole[ids.length];
/* 49 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 50 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 52 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CmsRoleDao dao)
/*    */   {
/* 59 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsRoleMngImpl
 * JD-Core Version:    0.6.0
 */