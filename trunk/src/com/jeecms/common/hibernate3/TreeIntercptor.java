/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.EmptyInterceptor;
/*     */ import org.hibernate.FlushMode;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.SessionFactory;
/*     */ import org.hibernate.type.Type;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.BeansException;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.context.ApplicationContextAware;
/*     */ 
/*     */ public class TreeIntercptor extends EmptyInterceptor
/*     */   implements ApplicationContextAware
/*     */ {
/*  21 */   private static final Logger log = LoggerFactory.getLogger(TreeIntercptor.class);
/*     */   private ApplicationContext appCtx;
/*     */   private SessionFactory sessionFactory;
/*     */   public static final String SESSION_FACTORY = "sessionFactory";
/*     */ 
/*     */   public void setApplicationContext(ApplicationContext appCtx)
/*     */     throws BeansException
/*     */   {
/*  28 */     this.appCtx = appCtx;
/*     */   }
/*     */ 
/*     */   protected SessionFactory getSessionFactory() {
/*  32 */     if (this.sessionFactory == null) {
/*  33 */       this.sessionFactory = 
/*  34 */         ((SessionFactory)this.appCtx
/*  34 */         .getBean("sessionFactory", 
/*  34 */         SessionFactory.class));
/*  35 */       if (this.sessionFactory == null) {
/*  36 */         throw new IllegalStateException("not found bean named 'sessionFactory',please check you spring config file.");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  41 */     return this.sessionFactory;
/*     */   }
/*     */ 
/*     */   protected Session getSession() {
/*  45 */     return getSessionFactory().getCurrentSession();
/*     */   }
/*     */ 
/*     */   public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
/*     */   {
/*  51 */     if ((entity instanceof HibernateTree)) {
/*  52 */       HibernateTree tree = (HibernateTree)entity;
/*  53 */       Number parentId = tree.getParentId();
/*  54 */       String beanName = tree.getClass().getName();
/*  55 */       Session session = getSession();
/*  56 */       FlushMode model = session.getFlushMode();
/*  57 */       session.setFlushMode(FlushMode.MANUAL);
/*     */       Integer myPosition;
/*  59 */       if (parentId != null)
/*     */       {
/*  61 */         String hql = "select bean." + tree.getRgtName() + " from " + 
/*  62 */           beanName + " bean where bean.id=:pid";
/*  63 */         myPosition = 
/*  64 */           Integer.valueOf(((Number)session.createQuery(hql).setParameter(
/*  64 */           "pid", parentId).uniqueResult()).intValue());
/*  65 */         String hql1 = "update " + beanName + " bean set bean." + 
/*  66 */           tree.getRgtName() + " = bean." + tree.getRgtName() + 
/*  67 */           " + 2 WHERE bean." + tree.getRgtName() + 
/*  68 */           " >= :myPosition";
/*  69 */         String hql2 = "update " + beanName + " bean set bean." + 
/*  70 */           tree.getLftName() + " = bean." + tree.getLftName() + 
/*  71 */           " + 2 WHERE bean." + tree.getLftName() + 
/*  72 */           " >= :myPosition";
/*  73 */         if (!StringUtils.isBlank(tree.getTreeCondition())) {
/*  74 */           hql1 = hql1 + " and (" + tree.getTreeCondition() + ")";
/*  75 */           hql2 = hql2 + " and (" + tree.getTreeCondition() + ")";
/*     */         }
/*  77 */         session.createQuery(hql1)
/*  78 */           .setParameter("myPosition", myPosition).executeUpdate();
/*  79 */         session.createQuery(hql2)
/*  80 */           .setParameter("myPosition", myPosition).executeUpdate();
/*     */       }
/*     */       else {
/*  83 */         String hql = "select max(bean." + tree.getRgtName() + ") from " + 
/*  84 */           beanName + " bean";
/*  85 */         if (!StringUtils.isBlank(tree.getTreeCondition())) {
/*  86 */           hql = hql + " where " + tree.getTreeCondition();
/*     */         }
/*  88 */         Number myPositionNumber = (Number)session.createQuery(hql)
/*  89 */           .uniqueResult();
/*  91 */         if (myPositionNumber == null)
/*  92 */           myPosition = Integer.valueOf(1);
/*     */         else {
/*  94 */           myPosition = Integer.valueOf(myPositionNumber.intValue() + 1);
/*     */         }
/*     */       }
/*  97 */       session.setFlushMode(model);
/*  98 */       for (int i = 0; i < propertyNames.length; i++) {
/*  99 */         if (propertyNames[i].equals(tree.getLftName())) {
/* 100 */           state[i] = myPosition;
/*     */         }
/* 102 */         if (propertyNames[i].equals(tree.getRgtName())) {
/* 103 */           state[i] = Integer.valueOf(myPosition.intValue() + 1);
/*     */         }
/*     */       }
/* 106 */       return true;
/*     */     }
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types)
/*     */   {
/* 115 */     if (!(entity instanceof HibernateTree)) {
/* 116 */       return false;
/*     */     }
/* 118 */     HibernateTree tree = (HibernateTree)entity;
/* 119 */     for (int i = 0; i < propertyNames.length; i++) {
/* 120 */       if (propertyNames[i].equals(tree.getParentName())) {
/* 121 */         HibernateTree preParent = (HibernateTree)previousState[i];
/* 122 */         HibernateTree currParent = (HibernateTree)currentState[i];
/* 123 */         return updateParent(tree, preParent, currParent);
/*     */       }
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean updateParent(HibernateTree<?> tree, HibernateTree<?> preParent, HibernateTree<?> currParent)
/*     */   {
/* 132 */     if (((preParent == null) && (currParent == null)) || (
/* 133 */       (preParent != null) && (currParent != null) && 
/* 134 */       (preParent.getId().equals(currParent.getId())))) {
/* 135 */       return false;
/*     */     }
/* 137 */     String beanName = tree.getClass().getName();
/* 138 */     if (log.isDebugEnabled()) {
/* 139 */       log.debug("update Tree {}, id={}, pre-parent id={}, curr-parent id={}", 
/* 140 */         new Object[] { 
/* 141 */         beanName, tree.getId(), 
/* 142 */         preParent == null ? null : preParent.getId(), 
/* 143 */         currParent == null ? null : currParent.getId() });
/*     */     }
/* 145 */     Session session = getSession();
/*     */ 
/* 147 */     FlushMode model = session.getFlushMode();
/* 148 */     session.setFlushMode(FlushMode.MANUAL);
/*     */     Integer currParentRgt;
/* 151 */     if (currParent != null)
/*     */     {
/* 153 */       String hql = "select bean." + tree.getLftName() + ",bean." + 
/* 154 */         tree.getRgtName() + " from " + beanName + 
/* 155 */         " bean where bean.id=:id";
/* 156 */       Object[] position = (Object[])session.createQuery(hql)
/* 157 */         .setParameter("id", tree.getId()).uniqueResult();
/* 158 */       int nodeLft = ((Number)position[0]).intValue();
/* 159 */       int nodeRgt = ((Number)position[1]).intValue();
/* 160 */       int span = nodeRgt - nodeLft + 1;
/* 161 */       log.debug("current node span={}", Integer.valueOf(span));
/*     */ 
/* 164 */       Object[] currPosition = (Object[])session.createQuery(hql)
/* 165 */         .setParameter("id", currParent.getId()).uniqueResult();
/* 166 */       int currParentLft = ((Number)currPosition[0]).intValue();
/* 167 */       currParentRgt = Integer.valueOf(((Number)currPosition[1]).intValue());
/* 168 */       log.debug("current parent lft={} rgt={}", Integer.valueOf(currParentLft), 
/* 169 */         currParentRgt);
/*     */ 
/* 172 */       String hql1 = "update " + beanName + " bean set bean." + 
/* 173 */         tree.getRgtName() + " = bean." + tree.getRgtName() + 
/* 174 */         " + " + span + " WHERE bean." + tree.getRgtName() + 
/* 175 */         " >= :parentRgt";
/* 176 */       String hql2 = "update " + beanName + " bean set bean." + 
/* 177 */         tree.getLftName() + " = bean." + tree.getLftName() + 
/* 178 */         " + " + span + " WHERE bean." + tree.getLftName() + 
/* 179 */         " >= :parentRgt";
/* 180 */       if (!StringUtils.isBlank(tree.getTreeCondition())) {
/* 181 */         hql1 = hql1 + " and (" + tree.getTreeCondition() + ")";
/* 182 */         hql2 = hql2 + " and (" + tree.getTreeCondition() + ")";
/*     */       }
/* 184 */       session.createQuery(hql1).setInteger("parentRgt", currParentRgt.intValue())
/* 185 */         .executeUpdate();
/* 186 */       session.createQuery(hql2).setInteger("parentRgt", currParentRgt.intValue())
/* 187 */         .executeUpdate();
/* 188 */       log.debug("vacated span hql: {}, {}, parentRgt={}", new Object[] { 
/* 189 */         hql1, hql2, currParentRgt });
/*     */     }
/*     */     else {
/* 192 */       String hql = "select max(bean." + tree.getRgtName() + ") from " + 
/* 193 */         beanName + " bean";
/* 194 */       if (!StringUtils.isBlank(tree.getTreeCondition())) {
/* 195 */         hql = hql + " where " + tree.getTreeCondition();
/*     */       }
/* 197 */       currParentRgt = 
/* 198 */         Integer.valueOf(((Number)session.createQuery(hql).uniqueResult())
/* 198 */         .intValue());
/* 199 */       currParentRgt = Integer.valueOf(currParentRgt.intValue() + 1);
/* 200 */       log.debug("max node left={}", currParentRgt);
/*     */     }
/*     */ 
/* 204 */     String hql = "select bean." + tree.getLftName() + ",bean." + 
/* 205 */       tree.getRgtName() + " from " + beanName + 
/* 206 */       " bean where bean.id=:id";
/* 207 */     Object[] position = (Object[])session.createQuery(hql).setParameter(
/* 208 */       "id", tree.getId()).uniqueResult();
/* 209 */     int nodeLft = ((Number)position[0]).intValue();
/* 210 */     int nodeRgt = ((Number)position[1]).intValue();
/* 211 */     int span = nodeRgt - nodeLft + 1;
/* 212 */     if (log.isDebugEnabled()) {
/* 213 */       log.debug("before adjust self left={} right={} span={}", 
/* 214 */         new Object[] { Integer.valueOf(nodeLft), Integer.valueOf(nodeRgt), Integer.valueOf(span) });
/*     */     }
/* 216 */     int offset = currParentRgt.intValue() - nodeLft;
/* 217 */     hql = "update " + beanName + " bean set bean." + tree.getLftName() + 
/* 218 */       "=bean." + tree.getLftName() + "+:offset, bean." + 
/* 219 */       tree.getRgtName() + "=bean." + tree.getRgtName() + 
/* 220 */       "+:offset WHERE bean." + tree.getLftName() + 
/* 221 */       " between :nodeLft and :nodeRgt";
/* 222 */     if (!StringUtils.isBlank(tree.getTreeCondition())) {
/* 223 */       hql = hql + " and (" + tree.getTreeCondition() + ")";
/*     */     }
/* 225 */     session.createQuery(hql).setParameter("offset", Integer.valueOf(offset)).setParameter(
/* 226 */       "nodeLft", Integer.valueOf(nodeLft)).setParameter("nodeRgt", Integer.valueOf(nodeRgt))
/* 227 */       .executeUpdate();
/* 228 */     if (log.isDebugEnabled()) {
/* 229 */       log.debug("adjust self hql: {}, offset={}, nodeLft={}, nodeRgt={}", 
/* 230 */         new Object[] { hql, Integer.valueOf(offset), Integer.valueOf(nodeLft), Integer.valueOf(nodeRgt) });
/*     */     }
/*     */ 
/* 234 */     String hql1 = "update " + beanName + " bean set bean." + 
/* 235 */       tree.getRgtName() + " = bean." + tree.getRgtName() + " - " + 
/* 236 */       span + " WHERE bean." + tree.getRgtName() + " > :nodeRgt";
/* 237 */     String hql2 = "update " + beanName + " bean set bean." + 
/* 238 */       tree.getLftName() + " = bean." + tree.getLftName() + " - " + 
/* 239 */       span + " WHERE bean." + tree.getLftName() + " > :nodeRgt";
/* 240 */     if (!StringUtils.isBlank(tree.getTreeCondition())) {
/* 241 */       hql1 = hql1 + " and (" + tree.getTreeCondition() + ")";
/* 242 */       hql2 = hql2 + " and (" + tree.getTreeCondition() + ")";
/*     */     }
/* 244 */     session.createQuery(hql1).setParameter("nodeRgt", Integer.valueOf(nodeRgt))
/* 245 */       .executeUpdate();
/* 246 */     session.createQuery(hql2).setParameter("nodeRgt", Integer.valueOf(nodeRgt))
/* 247 */       .executeUpdate();
/* 248 */     if (log.isDebugEnabled()) {
/* 249 */       log.debug("clear span hql1:{}, hql2:{}, nodeRgt:{}", new Object[] { 
/* 250 */         hql1, hql2, Integer.valueOf(nodeRgt) });
/*     */     }
/* 252 */     session.setFlushMode(model);
/* 253 */     return true;
/*     */   }
/*     */ 
/*     */   public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
/*     */   {
/* 259 */     if ((entity instanceof HibernateTree)) {
/* 260 */       HibernateTree tree = (HibernateTree)entity;
/* 261 */       String beanName = tree.getClass().getName();
/* 262 */       Session session = getSession();
/* 263 */       FlushMode model = session.getFlushMode();
/* 264 */       session.setFlushMode(FlushMode.MANUAL);
/* 265 */       String hql = "select bean." + tree.getLftName() + " from " + 
/* 266 */         beanName + " bean where bean.id=:id";
/* 267 */       Integer myPosition = 
/* 269 */         Integer.valueOf(((Number)session.createQuery(hql)
/* 268 */         .setParameter("id", tree.getId()).uniqueResult())
/* 269 */         .intValue());
/* 270 */       String hql1 = "update " + beanName + " bean set bean." + 
/* 271 */         tree.getRgtName() + " = bean." + tree.getRgtName() + 
/* 272 */         " - 2 WHERE bean." + tree.getRgtName() + " > :myPosition";
/* 273 */       String hql2 = "update " + beanName + " bean set bean." + 
/* 274 */         tree.getLftName() + " = bean." + tree.getLftName() + 
/* 275 */         " - 2 WHERE bean." + tree.getLftName() + " > :myPosition";
/* 276 */       if (!StringUtils.isBlank(tree.getTreeCondition())) {
/* 277 */         hql1 = hql1 + " and (" + tree.getTreeCondition() + ")";
/* 278 */         hql2 = hql2 + " and (" + tree.getTreeCondition() + ")";
/*     */       }
/* 280 */       session.createQuery(hql1).setInteger("myPosition", myPosition.intValue())
/* 281 */         .executeUpdate();
/* 282 */       session.createQuery(hql2).setInteger("myPosition", myPosition.intValue())
/* 283 */         .executeUpdate();
/* 284 */       session.setFlushMode(model);
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.TreeIntercptor
 * JD-Core Version:    0.6.0
 */