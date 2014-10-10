/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.type.Type;
/*     */ 
/*     */ public class Finder
/*     */ {
/*     */   private StringBuilder hqlBuilder;
/*     */   private List<String> params;
/*     */   private List<Object> values;
/*     */   private List<Type> types;
/*     */   private List<String> paramsList;
/*     */   private List<Collection<Object>> valuesList;
/*     */   private List<Type> typesList;
/*     */   private List<String> paramsArray;
/*     */   private List<Object[]> valuesArray;
/*     */   private List<Type> typesArray;
/* 341 */   private int firstResult = 0;
/*     */ 
/* 343 */   private int maxResults = 0;
/*     */ 
/* 345 */   private boolean cacheable = false;
/*     */   public static final String ROW_COUNT = "select count(*) ";
/*     */   public static final String FROM = "from";
/*     */   public static final String DISTINCT = "distinct";
/*     */   public static final String HQL_FETCH = "fetch";
/*     */   public static final String ORDER_BY = "order";
/*     */ 
/*     */   protected Finder()
/*     */   {
/*  17 */     this.hqlBuilder = new StringBuilder();
/*     */   }
/*     */ 
/*     */   protected Finder(String hql) {
/*  21 */     this.hqlBuilder = new StringBuilder(hql);
/*     */   }
/*     */ 
/*     */   public static Finder create() {
/*  25 */     return new Finder();
/*     */   }
/*     */ 
/*     */   public static Finder create(String hql) {
/*  29 */     return new Finder(hql);
/*     */   }
/*     */ 
/*     */   public Finder append(String hql) {
/*  33 */     this.hqlBuilder.append(hql);
/*  34 */     return this;
/*     */   }
/*     */ 
/*     */   public String getOrigHql()
/*     */   {
/*  43 */     return this.hqlBuilder.toString();
/*     */   }
/*     */ 
/*     */   public String getRowCountHql()
/*     */   {
/*  52 */     String hql = this.hqlBuilder.toString();
/*     */ 
/*  54 */     int fromIndex = hql.toLowerCase().indexOf("from");
/*  55 */     String projectionHql = hql.substring(0, fromIndex);
/*     */ 
/*  57 */     hql = hql.substring(fromIndex);
/*  58 */     String rowCountHql = hql.replace("fetch", "");
/*     */ 
/*  60 */     int index = rowCountHql.indexOf("order");
/*  61 */     if (index > 0) {
/*  62 */       rowCountHql = rowCountHql.substring(0, index);
/*     */     }
/*  64 */     return wrapProjection(projectionHql) + rowCountHql;
/*     */   }
/*     */ 
/*     */   public int getFirstResult() {
/*  68 */     return this.firstResult;
/*     */   }
/*     */ 
/*     */   public void setFirstResult(int firstResult) {
/*  72 */     this.firstResult = firstResult;
/*     */   }
/*     */ 
/*     */   public int getMaxResults() {
/*  76 */     return this.maxResults;
/*     */   }
/*     */ 
/*     */   public void setMaxResults(int maxResults) {
/*  80 */     this.maxResults = maxResults;
/*     */   }
/*     */ 
/*     */   public boolean isCacheable()
/*     */   {
/*  89 */     return this.cacheable;
/*     */   }
/*     */ 
/*     */   public void setCacheable(boolean cacheable)
/*     */   {
/*  99 */     this.cacheable = cacheable;
/*     */   }
/*     */ 
/*     */   public Finder setParam(String param, Object value)
/*     */   {
/* 111 */     return setParam(param, value, null);
/*     */   }
/*     */ 
/*     */   public Finder setParam(String param, Object value, Type type)
/*     */   {
/* 124 */     getParams().add(param);
/* 125 */     getValues().add(value);
/* 126 */     getTypes().add(type);
/* 127 */     return this;
/*     */   }
/*     */ 
/*     */   public Finder setParams(Map<String, Object> paramMap)
/*     */   {
/* 138 */     for (Map.Entry entry : paramMap.entrySet()) {
/* 139 */       setParam((String)entry.getKey(), entry.getValue());
/*     */     }
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   public Finder setParamList(String name, Collection<Object> vals, Type type)
/*     */   {
/* 154 */     getParamsList().add(name);
/* 155 */     getValuesList().add(vals);
/* 156 */     getTypesList().add(type);
/* 157 */     return this;
/*     */   }
/*     */ 
/*     */   public Finder setParamList(String name, Collection<Object> vals)
/*     */   {
/* 169 */     return setParamList(name, vals, null);
/*     */   }
/*     */ 
/*     */   public Finder setParamList(String name, Object[] vals, Type type)
/*     */   {
/* 182 */     getParamsArray().add(name);
/* 183 */     getValuesArray().add(vals);
/* 184 */     getTypesArray().add(type);
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */   public Finder setParamList(String name, Object[] vals)
/*     */   {
/* 197 */     return setParamList(name, vals, null);
/*     */   }
/*     */ 
/*     */   public Query setParamsToQuery(Query query)
/*     */   {
/* 206 */     if (this.params != null) {
/* 207 */       for (int i = 0; i < this.params.size(); i++) {
/* 208 */         if (this.types.get(i) == null)
/* 209 */           query.setParameter((String)this.params.get(i), this.values.get(i));
/*     */         else {
/* 211 */           query.setParameter((String)this.params.get(i), this.values.get(i), 
/* 212 */             (Type)this.types.get(i));
/*     */         }
/*     */       }
/*     */     }
/* 216 */     if (this.paramsList != null) {
/* 217 */       for (int i = 0; i < this.paramsList.size(); i++) {
/* 218 */         if (this.typesList.get(i) == null)
/* 219 */           query
/* 220 */             .setParameterList((String)this.paramsList.get(i), 
/* 221 */             (Collection)this.valuesList.get(i));
/*     */         else {
/* 223 */           query.setParameterList((String)this.paramsList.get(i), 
/* 224 */             (Collection)this.valuesList.get(i), (Type)this.typesList.get(i));
/*     */         }
/*     */       }
/*     */     }
/* 228 */     if (this.paramsArray != null) {
/* 229 */       for (int i = 0; i < this.paramsArray.size(); i++) {
/* 230 */         if (this.typesArray.get(i) == null)
/* 231 */           query.setParameterList((String)this.paramsArray.get(i), 
/* 232 */             (Object[])this.valuesArray.get(i));
/*     */         else {
/* 234 */           query.setParameterList((String)this.paramsArray.get(i), 
/* 235 */             (Object[])this.valuesArray.get(i), (Type)this.typesArray.get(i));
/*     */         }
/*     */       }
/*     */     }
/* 239 */     return query;
/*     */   }
/*     */ 
/*     */   public Query createQuery(Session s) {
/* 243 */     Query query = setParamsToQuery(s.createQuery(getOrigHql()));
/* 244 */     if (getFirstResult() > 0) {
/* 245 */       query.setFirstResult(getFirstResult());
/*     */     }
/* 247 */     if (getMaxResults() > 0) {
/* 248 */       query.setMaxResults(getMaxResults());
/*     */     }
/* 250 */     if (isCacheable()) {
/* 251 */       query.setCacheable(true);
/*     */     }
/* 253 */     return query;
/*     */   }
/*     */ 
/*     */   private String wrapProjection(String projection) {
/* 257 */     if (projection.indexOf("select") == -1) {
/* 258 */       return "select count(*) ";
/*     */     }
/* 260 */     return projection.replace("select", "select count(") + ") ";
/*     */   }
/*     */ 
/*     */   private List<String> getParams()
/*     */   {
/* 265 */     if (this.params == null) {
/* 266 */       this.params = new ArrayList();
/*     */     }
/* 268 */     return this.params;
/*     */   }
/*     */ 
/*     */   private List<Object> getValues() {
/* 272 */     if (this.values == null) {
/* 273 */       this.values = new ArrayList();
/*     */     }
/* 275 */     return this.values;
/*     */   }
/*     */ 
/*     */   private List<Type> getTypes() {
/* 279 */     if (this.types == null) {
/* 280 */       this.types = new ArrayList();
/*     */     }
/* 282 */     return this.types;
/*     */   }
/*     */ 
/*     */   private List<String> getParamsList() {
/* 286 */     if (this.paramsList == null) {
/* 287 */       this.paramsList = new ArrayList();
/*     */     }
/* 289 */     return this.paramsList;
/*     */   }
/*     */ 
/*     */   private List<Collection<Object>> getValuesList() {
/* 293 */     if (this.valuesList == null) {
/* 294 */       this.valuesList = new ArrayList();
/*     */     }
/* 296 */     return this.valuesList;
/*     */   }
/*     */ 
/*     */   private List<Type> getTypesList() {
/* 300 */     if (this.typesList == null) {
/* 301 */       this.typesList = new ArrayList();
/*     */     }
/* 303 */     return this.typesList;
/*     */   }
/*     */ 
/*     */   private List<String> getParamsArray() {
/* 307 */     if (this.paramsArray == null) {
/* 308 */       this.paramsArray = new ArrayList();
/*     */     }
/* 310 */     return this.paramsArray;
/*     */   }
/*     */ 
/*     */   private List<Object[]> getValuesArray() {
/* 314 */     if (this.valuesArray == null) {
/* 315 */       this.valuesArray = new ArrayList();
/*     */     }
/* 317 */     return this.valuesArray;
/*     */   }
/*     */ 
/*     */   private List<Type> getTypesArray() {
/* 321 */     if (this.typesArray == null) {
/* 322 */       this.typesArray = new ArrayList();
/*     */     }
/* 324 */     return this.typesArray;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 354 */     Finder find = 
/* 355 */       create("select distinct p FROM BookType join fetch p");
/* 356 */     System.out.println(find.getRowCountHql());
/* 357 */     System.out.println(find.getOrigHql());
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.Finder
 * JD-Core Version:    0.6.0
 */