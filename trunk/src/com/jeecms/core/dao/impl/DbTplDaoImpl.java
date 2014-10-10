/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.core.dao.DbTplDao;
/*    */ import com.jeecms.core.entity.DbTpl;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DbTplDaoImpl extends HibernateBaseDao<DbTpl, String>
/*    */   implements DbTplDao
/*    */ {
/*    */   public List<DbTpl> getStartWith(String prefix)
/*    */   {
/* 17 */     StringUtils.replace(prefix, "_", "\\_");
/* 18 */     prefix = prefix + "%";
/* 19 */     String hql = "from DbTpl bean where bean.id like ? order by bean.id";
/* 20 */     return find(hql, new Object[] { prefix });
/*    */   }
/*    */ 
/*    */   public List<DbTpl> getChild(String path, boolean isDirectory)
/*    */   {
/* 25 */     StringUtils.replace(path, "_", "\\_");
/* 26 */     path = path + "/%";
/* 27 */     String notLike = path + "/%";
/* 28 */     String hql = "from DbTpl bean where bean.id like ? and bean.id not like ? and bean.directory=? order by bean.id";
/*    */ 
/* 31 */     return find(hql, new Object[] { path, notLike, Boolean.valueOf(isDirectory) });
/*    */   }
/*    */ 
/*    */   public DbTpl findById(String id) {
/* 35 */     DbTpl entity = (DbTpl)get(id);
/* 36 */     return entity;
/*    */   }
/*    */ 
/*    */   public DbTpl save(DbTpl bean) {
/* 40 */     getSession().save(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public DbTpl deleteById(String id) {
/* 45 */     DbTpl entity = (DbTpl)super.get(id);
/* 46 */     if (entity != null) {
/* 47 */       getSession().delete(entity);
/*    */     }
/* 49 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<DbTpl> getEntityClass()
/*    */   {
/* 54 */     return DbTpl.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.DbTplDaoImpl
 * JD-Core Version:    0.6.0
 */