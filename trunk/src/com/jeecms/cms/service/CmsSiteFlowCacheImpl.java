/*     */ package com.jeecms.cms.service;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsSiteFlow;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.manager.assist.CmsSiteFlowMng;
/*     */ import com.jeecms.common.ipseek.IPLocation;
/*     */ import com.jeecms.common.ipseek.IPSeeker;
/*     */ import com.jeecms.common.util.DateFormatUtils;
/*     */ import com.jeecms.common.util.ParseURLKeyword;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import net.sf.ehcache.Ehcache;
/*     */ import net.sf.ehcache.Element;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service
/*     */ public class CmsSiteFlowCacheImpl
/*     */   implements CmsSiteFlowCache, DisposableBean
/*     */ {
/*  29 */   private Logger log = LoggerFactory.getLogger(CmsSiteFlowCacheImpl.class);
/*     */ 
/*  94 */   private int interval = 30000;
/*     */ 
/*  96 */   private long refreshTime = System.currentTimeMillis();
/*     */ 
/*     */   @Autowired
/*     */   private CmsSiteFlowMng manager;
/*     */ 
/*     */   @Autowired
/*     */   private IPSeeker ipSeeker;
/*     */ 
/*     */   @Autowired
/*     */   @Qualifier("cmsSiteFlow")
/*     */   private Ehcache cache;
/*     */ 
/*     */   public void flow(CmsSite site, String ip, String sessionId, String page, String referer)
/*     */   {
/*  32 */     CmsSiteFlow cmsSiteFlow = create(site, ip, sessionId, page, referer);
/*     */ 
/*  34 */     String uuid = UUID.randomUUID().toString();
/*  35 */     this.cache.put(new Element(uuid, cmsSiteFlow));
/*  36 */     refreshToDB();
/*     */   }
/*     */ 
/*     */   private CmsSiteFlow create(CmsSite site, String ip, String sessionId, String page, String referer)
/*     */   {
/*  41 */     CmsSiteFlow bean = new CmsSiteFlow();
/*  42 */     Date now = new Timestamp(System.currentTimeMillis());
/*  43 */     bean.setSite(site);
/*  44 */     bean.setAccessIp(ip);
/*  45 */     bean.setAccessPage(page);
/*  46 */     bean.setAccessTime(now);
/*  47 */     bean.setAccessDate(DateFormatUtils.formatDate(now));
/*  48 */     bean.setSessionId(sessionId);
/*  49 */     bean.setRefererPage(referer);
/*  50 */     bean.setRefererWebSite(getRefererWebSite(referer));
/*  51 */     bean.setArea(this.ipSeeker.getIPLocation(ip).getCountry());
/*  52 */     bean.setRefererKeyword(ParseURLKeyword.getKeyword(referer));
/*  53 */     return bean;
/*     */   }
/*     */ 
/*     */   private void refreshToDB() {
/*  57 */     long time = System.currentTimeMillis();
/*  58 */     if (time > this.refreshTime + this.interval) {
/*  59 */       this.refreshTime = time;
/*  60 */       int count = this.manager.freshCacheToDB(this.cache);
/*     */ 
/*  62 */       this.cache.removeAll();
/*  63 */       this.log.info("refresh cache flows to DB: {}", Integer.valueOf(count));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void destroy()
/*     */     throws Exception
/*     */   {
/*  71 */     int count = this.manager.freshCacheToDB(this.cache);
/*  72 */     this.log.info("Bean destroy.refresh cache flows to DB: {}", Integer.valueOf(count));
/*     */   }
/*     */ 
/*     */   private static String getRefererWebSite(String referer)
/*     */   {
/*  77 */     if (StringUtils.isBlank(referer)) {
/*  78 */       return "";
/*     */     }
/*  80 */     int start = 0; int i = 0; int count = 3;
/*  81 */     while ((i < count) && (start != -1)) {
/*  82 */       start = referer.indexOf('/', start + 1);
/*  83 */       i++;
/*     */     }
/*  85 */     if (start <= 0) {
/*  86 */       throw new IllegalStateException(
/*  87 */         "referer website uri not like 'http://.../...' pattern: " + 
/*  88 */         referer);
/*     */     }
/*  90 */     return referer.substring(0, start);
/*     */   }
/*     */ 
/*     */   public void setInterval(int interval)
/*     */   {
/* 105 */     this.interval = (interval * 1000);
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.service.CmsSiteFlowCacheImpl
 * JD-Core Version:    0.6.0
 */