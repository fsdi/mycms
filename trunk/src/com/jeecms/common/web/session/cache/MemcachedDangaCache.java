/*     */ package com.jeecms.common.web.session.cache;
/*     */ 
/*     */ import com.danga.MemCached.MemCachedClient;
/*     */ import com.danga.MemCached.SockIOPool;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ 
/*     */ public class MemcachedDangaCache
/*     */   implements SessionCache, InitializingBean
/*     */ {
/*     */   private MemCachedClient client;
/*     */   private String[] servers;
/*     */   private Integer[] weights;
/*     */ 
/*     */   public HashMap<String, Serializable> getSession(String root)
/*     */   {
/*  20 */     return (HashMap)this.client.get(root);
/*     */   }
/*     */ 
/*     */   public void setSession(String root, Map<String, Serializable> session, int exp)
/*     */   {
/*  25 */     this.client.set(root, session, 
/*  26 */       new Date(System.currentTimeMillis() + exp * 
/*  26 */       60 * 1000));
/*     */   }
/*     */ 
/*     */   public Serializable getAttribute(String root, String name) {
/*  30 */     HashMap session = getSession(root);
/*  31 */     return session != null ? (Serializable)session.get(name) : null;
/*     */   }
/*     */ 
/*     */   public void setAttribute(String root, String name, Serializable value, int exp)
/*     */   {
/*  36 */     HashMap session = getSession(root);
/*  37 */     if (session == null) {
/*  38 */       session = new HashMap();
/*     */     }
/*  40 */     session.put(name, value);
/*  41 */     Date expDate = new Date(System.currentTimeMillis() + exp * 60 * 1000);
/*  42 */     this.client.set(root, session, expDate);
/*     */   }
/*     */ 
/*     */   public void clear(String root) {
/*  46 */     this.client.delete(root);
/*     */   }
/*     */ 
/*     */   public boolean exist(String root) {
/*  50 */     return this.client.keyExists(root);
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws Exception {
/*  54 */     this.client = new MemCachedClient();
/*     */ 
/*  56 */     SockIOPool pool = SockIOPool.getInstance();
/*     */ 
/*  59 */     pool.setServers(this.servers);
/*  60 */     pool.setWeights(this.weights);
/*     */ 
/*  66 */     pool.setInitConn(5);
/*  67 */     pool.setMinConn(5);
/*  68 */     pool.setMaxConn(250);
/*  69 */     pool.setMaxIdle(21600000L);
/*     */ 
/*  74 */     pool.setMaintSleep(30L);
/*     */ 
/*  80 */     pool.setNagle(false);
/*  81 */     pool.setSocketTO(3000);
/*  82 */     pool.setSocketConnectTO(0);
/*     */ 
/*  85 */     pool.initialize();
/*     */ 
/*  89 */     this.client.setCompressEnable(true);
/*  90 */     this.client.setCompressThreshold(65536L);
/*     */   }
/*     */ 
/*     */   public String[] getServers() {
/*  94 */     return this.servers;
/*     */   }
/*     */ 
/*     */   public void setServers(String[] servers) {
/*  98 */     this.servers = servers;
/*     */   }
/*     */ 
/*     */   public Integer[] getWeights() {
/* 102 */     return this.weights;
/*     */   }
/*     */ 
/*     */   public void setWeights(Integer[] weights) {
/* 106 */     this.weights = weights;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.session.cache.MemcachedDangaCache
 * JD-Core Version:    0.6.0
 */