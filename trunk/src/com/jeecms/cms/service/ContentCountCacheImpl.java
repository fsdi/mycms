/*    */ package com.jeecms.cms.service;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.ContentCount;
/*    */ import com.jeecms.cms.manager.main.ContentCountMng;
/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.DisposableBean;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.beans.factory.annotation.Qualifier;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service
/*    */ public class ContentCountCacheImpl
/*    */   implements ContentCountCache, DisposableBean
/*    */ {
/* 21 */   private Logger log = LoggerFactory.getLogger(ContentCountCacheImpl.class);
/*    */ 
/* 64 */   private int interval = 600000;
/*    */ 
/* 66 */   private long refreshTime = System.currentTimeMillis();
/*    */   private ContentCountMng contentCountMng;
/*    */   private Ehcache cache;
/*    */ 
/*    */   public int[] viewAndGet(Integer id)
/*    */   {
/* 27 */     ContentCount count = this.contentCountMng.findById(id);
/* 28 */     if (count == null) {
/* 29 */       return null;
/*    */     }
/* 31 */     Element e = this.cache.get(id);
/*    */     Integer views;
/* 33 */     if (e != null)
/* 34 */       views = Integer.valueOf(((Integer)e.getValue()).intValue() + 1);
/*    */     else {
/* 36 */       views = Integer.valueOf(1);
/*    */     }
/* 38 */     this.cache.put(new Element(id, views));
/* 39 */     refreshToDB();
/* 40 */     return new int[] { views.intValue() + count.getViews().intValue(), count.getComments().intValue(), 
/* 41 */       count.getDownloads().intValue(), count.getUps().intValue(), count.getDowns().intValue() };
/*    */   }
/*    */ 
/*    */   private void refreshToDB() {
/* 45 */     long time = System.currentTimeMillis();
/* 46 */     if (time > this.refreshTime + this.interval) {
/* 47 */       this.refreshTime = time;
/* 48 */       int count = this.contentCountMng.freshCacheToDB(this.cache);
/*    */ 
/* 50 */       this.cache.removeAll();
/* 51 */       this.log.info("refresh cache views to DB: {}", Integer.valueOf(count));
/*    */     }
/*    */   }
/*    */ 
/*    */   public void destroy()
/*    */     throws Exception
/*    */   {
/* 59 */     int count = this.contentCountMng.freshCacheToDB(this.cache);
/* 60 */     this.log.info("Bean destroy.refresh cache views to DB: {}", Integer.valueOf(count));
/*    */   }
/*    */ 
/*    */   public void setInterval(int interval)
/*    */   {
/* 79 */     this.interval = (interval * 60 * 1000);
/*    */   }
/*    */   @Autowired
/*    */   public void setContentCountMng(ContentCountMng contentCountMng) {
/* 84 */     this.contentCountMng = contentCountMng;
/*    */   }
/*    */   @Autowired
/*    */   public void setCache(@Qualifier("contentCount") Ehcache cache) {
/* 89 */     this.cache = cache;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.service.ContentCountCacheImpl
 * JD-Core Version:    0.6.0
 */