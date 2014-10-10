/*    */ package com.jeecms.core.dao.impl;
/*    */ 
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import com.jeecms.core.dao.UnifiedUserDao;
/*    */ import com.jeecms.core.entity.UnifiedUser;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class UnifiedUserDaoImpl extends HibernateBaseDao<UnifiedUser, Integer>
/*    */   implements UnifiedUserDao
/*    */ {
/*    */   public UnifiedUser getByUsername(String username)
/*    */   {
/* 18 */     return (UnifiedUser)findUniqueByProperty("username", username);
/*    */   }
/*    */ 
/*    */   public List<UnifiedUser> getByEmail(String email) {
/* 22 */     return findByProperty("email", email);
/*    */   }
/*    */ 
/*    */   public int countByEmail(String email) {
/* 26 */     String hql = "select count(*) from UnifiedUser bean where bean.email=:email";
/* 27 */     Query query = getSession().createQuery(hql);
/* 28 */     query.setParameter("email", email);
/* 29 */     return ((Number)query.iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   public Pagination getPage(int pageNo, int pageSize) {
/* 33 */     Criteria crit = createCriteria(new Criterion[0]);
/* 34 */     Pagination page = findByCriteria(crit, pageNo, pageSize);
/* 35 */     return page;
/*    */   }
/*    */ 
/*    */   public UnifiedUser findById(Integer id) {
/* 39 */     UnifiedUser entity = (UnifiedUser)get(id);
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   public UnifiedUser save(UnifiedUser bean) {
/* 44 */     getSession().save(bean);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public UnifiedUser deleteById(Integer id) {
/* 49 */     UnifiedUser entity = (UnifiedUser)super.get(id);
/* 50 */     if (entity != null) {
/* 51 */       getSession().delete(entity);
/*    */     }
/* 53 */     return entity;
/*    */   }
/*    */ 
/*    */   protected Class<UnifiedUser> getEntityClass()
/*    */   {
/* 58 */     return UnifiedUser.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.core.dao.impl.UnifiedUserDaoImpl
 * JD-Core Version:    0.6.0
 */