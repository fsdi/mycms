/*     */ package com.jeecms.common.web;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import net.sf.ehcache.CacheException;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.ObjectExistsException;
/*     */ import net.sf.ehcache.config.Configuration;
/*     */ import net.sf.ehcache.config.ConfigurationFactory;
/*     */ import net.sf.ehcache.config.DiskStoreConfiguration;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.FactoryBean;
/*     */ import org.springframework.beans.factory.InitializingBean;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public class WebEhCacheManagerFacotryBean
/*     */   implements FactoryBean<CacheManager>, InitializingBean, DisposableBean
/*     */ {
/*  26 */   private final Logger log = LoggerFactory.getLogger(WebEhCacheManagerFacotryBean.class);
/*     */   private Resource configLocation;
/*     */   private Resource diskStoreLocation;
/*     */   private String cacheManagerName;
/*     */   private CacheManager cacheManager;
/*     */ 
/*     */   public void setConfigLocation(Resource configLocation)
/*     */   {
/*  47 */     this.configLocation = configLocation;
/*     */   }
/*     */ 
/*     */   public void setdiskStoreLocation(Resource diskStoreLocation)
/*     */   {
/*  58 */     this.diskStoreLocation = diskStoreLocation;
/*     */   }
/*     */ 
/*     */   public void setCacheManagerName(String cacheManagerName)
/*     */   {
/*  67 */     this.cacheManagerName = cacheManagerName;
/*     */   }
/*     */ 
/*     */   public void afterPropertiesSet() throws IOException, CacheException {
/*  71 */     this.log.info("Initializing EHCache CacheManager");
/*  72 */     Configuration config = null;
/*  73 */     if (this.configLocation != null) {
/*  74 */       config = 
/*  75 */         ConfigurationFactory.parseConfiguration(this.configLocation.getInputStream());
/*  76 */       if (this.diskStoreLocation != null) {
/*  77 */         DiskStoreConfiguration dc = new DiskStoreConfiguration();
/*  78 */         dc.setPath(this.diskStoreLocation.getFile().getAbsolutePath());
/*     */         try {
/*  80 */           config.addDiskStore(dc);
/*     */         } catch (ObjectExistsException e) {
/*  82 */           this.log.warn("if you want to config distStore in spring, please remove diskStore in config file!", 
/*  83 */             e);
/*     */         }
/*     */       }
/*     */     }
/*  87 */     if (config != null)
/*  88 */       this.cacheManager = new CacheManager(config);
/*     */     else {
/*  90 */       this.cacheManager = new CacheManager();
/*     */     }
/*  92 */     if (this.cacheManagerName != null)
/*  93 */       this.cacheManager.setName(this.cacheManagerName);
/*     */   }
/*     */ 
/*     */   public CacheManager getObject()
/*     */   {
/*  98 */     return this.cacheManager;
/*     */   }
/*     */ 
/*     */   public Class<? extends CacheManager> getObjectType() {
/* 102 */     return this.cacheManager != null ? this.cacheManager.getClass() : 
/* 103 */       CacheManager.class;
/*     */   }
/*     */ 
/*     */   public boolean isSingleton() {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   public void destroy() {
/* 111 */     this.log.info("Shutting down EHCache CacheManager");
/* 112 */     this.cacheManager.shutdown();
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.web.WebEhCacheManagerFacotryBean
 * JD-Core Version:    0.6.0
 */