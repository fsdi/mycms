/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.util.MyBeanUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.criterion.CriteriaSpecification;
/*     */ import org.hibernate.criterion.Projection;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.impl.CriteriaImpl;
/*     */ import org.hibernate.transform.ResultTransformer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public abstract class HibernateSimpleDao
/*     */ {
/*  32 */   protected Logger log = LoggerFactory.getLogger(getClass());
/*     */   protected static final String ORDER_ENTRIES = "orderEntries";
/*     */   protected SessionFactory sessionFactory;
/*     */ 
/*     */   protected List find(String hql, Object[] values)
/*     */   {
/*  48 */     return createQuery(hql, values).list();
/*     */   }
/*     */ 
/*     */   protected Object findUnique(String hql, Object[] values)
/*     */   {
/*  55 */     return createQuery(hql, values).uniqueResult();
/*     */   }
/*     */ 
/*     */   protected Pagination find(Finder finder, int pageNo, int pageSize)
/*     */   {
/*  71 */     int totalCount = countQueryResult(finder);
/*  72 */     Pagination p = new Pagination(pageNo, pageSize, totalCount);
/*  73 */     if (totalCount < 1) {
/*  74 */       p.setList(new ArrayList());
/*  75 */       return p;
/*     */     }
/*  77 */     Query query = getSession().createQuery(finder.getOrigHql());
/*  78 */     finder.setParamsToQuery(query);
/*  79 */     query.setFirstResult(p.getFirstResult());
/*  80 */     query.setMaxResults(p.getPageSize());
/*  81 */     if (finder.isCacheable()) {
/*  82 */       query.setCacheable(true);
/*     */     }
/*  84 */     List list = query.list();
/*  85 */     p.setList(list);
/*  86 */     return p;
/*     */   }
/*     */ 
/*     */   protected List find(Finder finder)
/*     */   {
/*  97 */     Query query = finder.createQuery(getSession());
/*  98 */     List list = query.list();
/*  99 */     return list;
/*     */   }
/*     */ 
/*     */   protected Query createQuery(String queryString, Object[] values)
/*     */   {
/* 106 */     Assert.hasText(queryString);
/* 107 */     Query queryObject = getSession().createQuery(queryString);
/* 108 */     if (values != null) {
/* 109 */       for (int i = 0; i < values.length; i++) {
/* 110 */         queryObject.setParameter(i, values[i]);
/*     */       }
/*     */     }
/* 113 */     return queryObject;
/*     */   }
/*     */ 
/*     */   protected Pagination findByCriteria(Criteria crit, int pageNo, int pageSize)
/*     */   {
/* 128 */     CriteriaImpl impl = (CriteriaImpl)crit;
/*     */ 
/* 130 */     Projection projection = impl.getProjection();
/* 131 */     ResultTransformer transformer = impl.getResultTransformer();
/*     */     List orderEntries=null;
/*     */     try
/*     */     {
/* 134 */       orderEntries = (List)
/* 135 */         MyBeanUtils.getFieldValue(impl, "orderEntries");
/* 136 */       MyBeanUtils.setFieldValue(impl, "orderEntries", new ArrayList());
/*     */     } catch (Exception e) {
/* 138 */       throw new RuntimeException(
/* 139 */         "cannot read/write 'orderEntries' from CriteriaImpl", e);
/*     */     }
/* 142 */     int totalCount = ((Number)crit.setProjection(Projections.rowCount())
/* 143 */       .uniqueResult()).intValue();
/* 144 */     Pagination p = new Pagination(pageNo, pageSize, totalCount);
/* 145 */     if (totalCount < 1) {
/* 146 */       p.setList(new ArrayList());
/* 147 */       return p;
/*     */     }
/* 151 */     crit.setProjection(projection);
/* 152 */     if (projection == null) {
/* 153 */       crit.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
/*     */     }
/* 155 */     if (transformer != null)
/* 156 */       crit.setResultTransformer(transformer);
/*     */     try
/*     */     {
/* 159 */       MyBeanUtils.setFieldValue(impl, "orderEntries", orderEntries);
/*     */     } catch (Exception e) {
/* 161 */       throw new RuntimeException(
/* 162 */         "set 'orderEntries' to CriteriaImpl faild", e);
/*     */     }
/* 164 */     crit.setFirstResult(p.getFirstResult());
/* 165 */     crit.setMaxResults(p.getPageSize());
/* 166 */     p.setList(crit.list());
/* 167 */     return p;
/*     */   }
/*     */ 
/*     */   protected int countQueryResult(Finder finder)
/*     */   {
/* 177 */     Query query = getSession().createQuery(finder.getRowCountHql());
/* 178 */     finder.setParamsToQuery(query);
/* 179 */     if (finder.isCacheable()) {
/* 180 */       query.setCacheable(true);
/*     */     }
				Iterator it = query.iterate();
				if(it.hasNext()){
					Number n = (Number)query.iterate().next();
					if(n!=null){
						return n.intValue();
					}
				}
				return 0;
///* 182 */     return ((Number)query.iterate().next()).intValue();
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSessionFactory(SessionFactory sessionFactory)
/*     */   {
/* 189 */     this.sessionFactory = sessionFactory;
/*     */   }
/*     */ 
/*     */   protected Session getSession() {
/* 193 */     return this.sessionFactory.getCurrentSession();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.HibernateSimpleDao
 * JD-Core Version:    0.6.0
 */