/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import com.jeecms.core.dao.AuthenticationDao;
/*    */ import com.jeecms.core.entity.Authentication;
/*    */ import java.util.Date;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class AuthenticationDaoImpl extends HibernateBaseDao<Authentication, String>
/*    */   implements AuthenticationDao
/*    */ {
/*    */   public int deleteExpire(Date d)
/*    */   {
/* 17 */     String hql = "delete Authentication bean where bean.updateTime <= :d";
/* 18 */     return getSession().createQuery(hql).setTimestamp("d", d)
/* 19 */       .executeUpdate();
/*    */   }
/*    */ 
/*    */   public Authentication getByUserId(Long userId) {
/* 23 */     String hql = "from Authentication bean where bean.uid=?";
/* 24 */     return (Authentication)findUnique(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public Pagination getPage(int pageNo, int pageSize) {
/* 28 */     Criteria crit = createCriteria(new Criterion[0]);
/* 29 */     Pagination page = findByCriteria(crit, pageNo, pageSize);
/* 30 */     return page;
/*    */   }
/*    */ 
/*    */   public Authentication findById(String id) {
/* 34 */     Authentication entity = (Authentication)get(id);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public Authentication save(Authentication bean) {
/* 39 */     getSession().save(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public Authentication deleteById(String id) {
/* 44 */     Authentication entity = (Authentication)super.get(id);
/* 45 */     if (entity != null) {
/* 46 */       getSession().delete(entity);
/*    */     }
/* 48 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<Authentication> getEntityClass()
/*    */   {
/* 53 */     return Authentication.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.AuthenticationDaoImpl
 * JD-Core Version:    0.6.0
 */