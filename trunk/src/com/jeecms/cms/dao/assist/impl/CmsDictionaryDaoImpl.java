/*    */ package com.jeecms.cms.dao.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsDictionaryDao;
/*    */ import com.jeecms.cms.entity.assist.CmsDictionary;
/*    */ import com.jeecms.common.hibernate3.Finder;
/*    */ import com.jeecms.common.hibernate3.HibernateBaseDao;
/*    */ import com.jeecms.common.page.Pagination;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.Query;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Criterion;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class CmsDictionaryDaoImpl extends HibernateBaseDao<CmsDictionary, Integer>
/*    */   implements CmsDictionaryDao
/*    */ {
/*    */   public Pagination getPage(String queryType, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria(new Criterion[0]);
/* 22 */     if (StringUtils.isNotBlank(queryType)) {
/* 23 */       Criterion cron = Restrictions.like("type", queryType);
/* 24 */       crit.add(cron);
/*    */     }
/* 26 */     Pagination page = findByCriteria(crit, pageNo, pageSize);
/* 27 */     return page;
/*    */   }
/*    */ 
/*    */   public List<CmsDictionary> getList(String type)
/*    */   {
/* 32 */     Criterion cron = Restrictions.like("type", type);
/* 33 */     return findByCriteria(new Criterion[] { cron });
/*    */   }
/*    */ 
/*    */   public List<String> getTypeList()
/*    */   {
/* 38 */     Finder f = Finder.create("select  type from CmsDictionary dic group by type ");
/* 39 */     return find(f);
/*    */   }
/*    */ 
/*    */   public CmsDictionary findById(Integer id) {
/* 43 */     CmsDictionary entity = (CmsDictionary)get(id);
/* 44 */     return entity;
/*    */   }
/*    */ 
/*    */   public CmsDictionary findByValue(String type, String value)
/*    */   {
/* 49 */     Criterion cron_type = null; Criterion cron_value = null;
/* 50 */     if (StringUtils.isNotBlank(type)) {
/* 51 */       cron_type = Restrictions.like("type", type);
/*    */     }
/* 53 */     if (StringUtils.isNotBlank(value)) {
/* 54 */       cron_value = Restrictions.like("value", value);
/*    */     }
/* 56 */     List li = findByCriteria(new Criterion[] { cron_type, cron_value });
/* 57 */     if ((li != null) && (li.size() > 0)) {
/* 58 */       return (CmsDictionary)li.get(0);
/*    */     }
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   public CmsDictionary save(CmsDictionary bean)
/*    */   {
/* 65 */     getSession().save(bean);
/* 66 */     return bean;
/*    */   }
/*    */ 
/*    */   public CmsDictionary deleteById(Integer id) {
/* 70 */     CmsDictionary entity = (CmsDictionary)super.get(id);
/* 71 */     if (entity != null) {
/* 72 */       getSession().delete(entity);
/*    */     }
/* 74 */     return entity;
/*    */   }
/*    */ 
/*    */   public int countByValue(String value, String type) {
/* 78 */     String hql = "select count(*) from CmsDictionary bean where bean.value=:value and bean.type=:type";
/* 79 */     Query query = getSession().createQuery(hql);
/* 80 */     query.setParameter("value", value);
/* 81 */     query.setParameter("type", type);
/* 82 */     return ((Number)query.iterate().next()).intValue();
/*    */   }
/*    */ 
/*    */   protected Class<CmsDictionary> getEntityClass()
/*    */   {
/* 87 */     return CmsDictionary.class;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.dao.assist.impl.CmsDictionaryDaoImpl
 * JD-Core Version:    0.6.0
 */