/*     */ package com.jeecms.common.hibernate3;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Ehcache;
/*     */ import net.sf.ehcache.ObjectExistsException;
/*     */ import net.sf.ehcache.config.Configuration;
/*     */ import net.sf.ehcache.config.ConfigurationFactory;
/*     */ import net.sf.ehcache.config.DiskStoreConfiguration;
/*     */ import net.sf.ehcache.hibernate.EhCache;
/*     */ import org.hibernate.cache.Cache;
/*     */ import org.hibernate.cache.CacheProvider;
/*     */ import org.hibernate.cache.Timestamper;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.core.io.Resource;
/*     */ 
/*     */ public final class SpringEhCacheProvider
/*     */   implements CacheProvider
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(SpringEhCacheProvider.class);
/*     */   private Resource configLocation;
/*     */   private Resource diskStoreLocation;
/*     */   private CacheManager manager;
/*     */ 
/*     */   public void setConfigLocation(Resource configLocation)
/*     */   {
/*  35 */     this.configLocation = configLocation;
/*     */   }
/*     */ 
/*     */   public void setDiskStoreLocation(Resource diskStoreLocation) {
/*  39 */     this.diskStoreLocation = diskStoreLocation;
/*     */   }
/*     */ 
/*     */   public final Cache buildCache(String name, Properties properties) throws org.hibernate.cache.CacheException
/*     */   {
/*     */     try {
/*  45 */       Ehcache cache = this.manager.getEhcache(name);
/*  46 */       if (cache == null) {
/*  47 */         String s = "Could not find a specific ehcache configuration for cache named [{}]; using defaults.";
/*  48 */         log.warn(s, name);
/*  49 */         this.manager.addCache(name);
/*  50 */         cache = this.manager.getEhcache(name);
/*  51 */         log.debug("started EHCache region: " + name);
/*     */       }
/*  53 */       return new EhCache(cache); } catch (net.sf.ehcache.CacheException e) {
				throw new org.hibernate.cache.CacheException(e);
/*     */     }
/*     */   }
/*     */ 
/*     */   public final long nextTimestamp()
/*     */   {
/*  63 */     return Timestamper.next();
/*     */   }
/*     */ 
/*     */   public final void start(Properties properties)
/*     */     throws org.hibernate.cache.CacheException
/*     */   {
/*  75 */     if (this.manager != null) {
/*  76 */       String s = "Attempt to restart an already started EhCacheProvider. Use sessionFactory.close()  between repeated calls to buildSessionFactory. Using previously created EhCacheProvider. If this behaviour is required, consider using SingletonEhCacheProvider.";
/*     */ 
/*  79 */       log.warn(s);
/*  80 */       return;
/*     */     }
/*  82 */     Configuration config = null;
/*     */     try {
/*  84 */       if (this.configLocation != null) {
/*  85 */         config = ConfigurationFactory.parseConfiguration(this.configLocation
/*  86 */           .getInputStream());
/*  87 */         if (this.diskStoreLocation != null) {
/*  88 */           DiskStoreConfiguration dc = new DiskStoreConfiguration();
/*  89 */           dc.setPath(
/*  90 */             this.diskStoreLocation.getFile().getAbsolutePath());
/*     */           try {
/*  92 */             config.addDiskStore(dc);
/*     */           } catch (ObjectExistsException e) {
/*  94 */             String s = "if you want to config distStore in spring, please remove diskStore in config file!";
/*     */ 
/*  96 */             log.warn(s, e);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/* 101 */       log.warn("create ehcache config failed!", e);
/*     */     }
/* 103 */     if (config != null)
/* 104 */       this.manager = new CacheManager(config);
/*     */     else
/* 106 */       this.manager = new CacheManager();
/*     */   }
/*     */ 
/*     */   public final void stop()
/*     */   {
/* 115 */     if (this.manager != null) {
/* 116 */       this.manager.shutdown();
/* 117 */       this.manager = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   public final boolean isMinimalPutsEnabledByDefault()
/*     */   {
/* 127 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.common.hibernate3.SpringEhCacheProvider
 * JD-Core Version:    0.6.0
 */