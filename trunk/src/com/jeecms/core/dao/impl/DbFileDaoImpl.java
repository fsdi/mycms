/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.core.dao.DbFileDao;
/*    */ import com.jeecms.core.entity.DbFile;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DbFileDaoImpl extends HibernateBaseDao<DbFile, String>
/*    */   implements DbFileDao
/*    */ {
/*    */   public DbFile findById(String id)
/*    */   {
/* 13 */     DbFile entity = (DbFile)get(id);
/* 14 */     return entity;
/*    */   }
/*    */ 
/*    */   public DbFile save(DbFile bean) {
/* 18 */     getSession().save(bean);
/* 19 */     return bean;
/*    */   }
/*    */ 
/*    */   public DbFile deleteById(String id) {
/* 23 */     DbFile entity = (DbFile)super.get(id);
/* 24 */     if (entity != null) {
/* 25 */       getSession().delete(entity);
/*    */     }
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<DbFile> getEntityClass()
/*    */   {
/* 32 */     return DbFile.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.DbFileDaoImpl
 * JD-Core Version:    0.6.0
 */