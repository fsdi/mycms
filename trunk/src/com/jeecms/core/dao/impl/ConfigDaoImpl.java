/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.core.dao.ConfigDao;
/*    */ import com.jeecms.core.entity.Config;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ConfigDaoImpl extends HibernateBaseDao<Config, String>
/*    */   implements ConfigDao
/*    */ {
/*    */   public List<Config> getList()
/*    */   {
/* 16 */     String hql = "from Config";
/* 17 */     return find(hql, new Object[0]);
/*    */   }
/*    */ 
/*    */   public Config findById(String id) {
/* 21 */     Config entity = (Config)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public Config save(Config bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public Config deleteById(String id) {
/* 31 */     Config entity = (Config)super.get(id);
/* 32 */     if (entity != null) {
/* 33 */       getSession().delete(entity);
/*    */     }
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<Config> getEntityClass()
/*    */   {
/* 40 */     return Config.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.ConfigDaoImpl
 * JD-Core Version:    0.6.0
 */