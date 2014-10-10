/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.core.dao.FtpDao;
/*    */ import com.jeecms.core.entity.Ftp;
/*    */ import java.util.List;
/*    */ import org.hibernate.Session;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class FtpDaoImpl extends HibernateBaseDao<Ftp, Integer>
/*    */   implements FtpDao
/*    */ {
/*    */   public List<Ftp> getList()
/*    */   {
/* 16 */     String hql = "from Ftp bean";
/* 17 */     return find(hql, new Object[0]);
/*    */   }
/*    */ 
/*    */   public Ftp findById(Integer id) {
/* 21 */     Ftp entity = (Ftp)get(id);
/* 22 */     return entity;
/*    */   }
/*    */ 
/*    */   public Ftp save(Ftp bean) {
/* 26 */     getSession().save(bean);
/* 27 */     return bean;
/*    */   }
/*    */ 
/*    */   public Ftp deleteById(Integer id) {
/* 31 */     Ftp entity = (Ftp)super.get(id);
/* 32 */     if (entity != null) {
/* 33 */       getSession().delete(entity);
/*    */     }
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<Ftp> getEntityClass()
/*    */   {
/* 40 */     return Ftp.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.FtpDaoImpl
 * JD-Core Version:    0.6.0
 */