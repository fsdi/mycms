/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import com.jeecms.common.util.MyBeanUtils;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.EntityMode;
/*     */ import org.hibernate.LockMode;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.criterion.Criterion;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.hibernate.metadata.ClassMetadata;
/*     */ import org.springframework.util.Assert;
/*     */ 
/*     */ public abstract class HibernateBaseDao<T, ID extends Serializable> extends HibernateSimpleDao
/*     */ {
/*     */   protected T get(ID id)
/*     */   {
/*  35 */     return get(id, false);
/*     */   }
/*     */ 
/*     */   protected T get(ID id, boolean lock)
/*     */   {
/*     */     T entity;
/*  49 */     if (lock)
/*  50 */       entity = (T)getSession().get(getEntityClass(), id, 
/*  51 */         LockMode.UPGRADE);
/*     */     else {
/*  53 */       entity = (T)getSession().get(getEntityClass(), id);
/*     */     }
/*  55 */     return entity;
/*     */   }
/*     */ 
/*     */   protected List<T> findByProperty(String property, Object value)
/*     */   {
/*  63 */     Assert.hasText(property);
/*  64 */     return createCriteria(new Criterion[] { Restrictions.eq(property, value) }).list();
/*     */   }
/*     */ 
/*     */   protected T findUniqueByProperty(String property, Object value)
/*     */   {
/*  72 */     Assert.hasText(property);
/*  73 */     Assert.notNull(value);
/*  74 */     return (T)createCriteria(new Criterion[] { Restrictions.eq(property, value) }).uniqueResult();
/*     */   }
/*     */ 
/*     */   protected int countByProperty(String property, Object value)
/*     */   {
/*  86 */     Assert.hasText(property);
/*  87 */     Assert.notNull(value);
/*  88 */     return ((Number)createCriteria(new Criterion[] { Restrictions.eq(property, value) })
/*  89 */       .setProjection(Projections.rowCount()).uniqueResult())
/*  90 */       .intValue();
/*     */   }
/*     */ 
/*     */   protected List findByCriteria(Criterion[] criterion)
/*     */   {
/* 101 */     return createCriteria(criterion).list();
/*     */   }
/*     */ 
/*     */   public T updateByUpdater(Updater<T> updater)
/*     */   {
/* 112 */     ClassMetadata cm = this.sessionFactory.getClassMetadata(getEntityClass());
/* 113 */     Object bean = updater.getBean();
/* 114 */     T po = (T)getSession().get(getEntityClass(),cm.getIdentifier(bean, EntityMode.POJO));
/* 116 */     updaterCopyToPersistentObject(updater, po, cm);
/* 117 */     return po;
/*     */   }
/*     */ 
/*     */   private void updaterCopyToPersistentObject(Updater<T> updater, T po, ClassMetadata cm)
/*     */   {
/* 128 */     String[] propNames = cm.getPropertyNames();
/* 129 */     String identifierName = cm.getIdentifierPropertyName();
/* 130 */     Object bean = updater.getBean();
/*     */ 
/* 132 */     for (String propName : propNames) {
/* 133 */       if (propName.equals(identifierName))
/*     */         continue;
/*     */       try
/*     */       {
/* 137 */         Object value = MyBeanUtils.getSimpleProperty(bean, propName);
/* 138 */         if (!updater.isUpdate(propName, value)) {
/*     */           continue;
/*     */         }
/* 141 */         cm.setPropertyValue(po, propName, value, EntityMode.POJO);
/*     */       } catch (Exception e) {
/* 143 */         throw new RuntimeException(
/* 144 */           "copy property to persistent object failed: '" + 
/* 145 */           propName + "'", e);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected Criteria createCriteria(Criterion[] criterions)
/*     */   {
/* 154 */     Criteria criteria = getSession().createCriteria(getEntityClass());
/* 155 */     for (Criterion c : criterions) {
/* 156 */       criteria.add(c);
/*     */     }
/* 158 */     return criteria;
/*     */   }
/*     */ 
/*     */   protected abstract Class<T> getEntityClass();
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.HibernateBaseDao
 * JD-Core Version:    0.6.0
 */