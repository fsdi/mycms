/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.ContentCountDao;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.ContentCount;
/*     */ import com.jeecms.cms.manager.main.CmsConfigMng;
/*     */ import com.jeecms.cms.manager.main.ContentCountMng;
/*     */ import com.jeecms.common.hibernate3.Updater;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import net.sf.ehcache.Ehcache;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ContentCountMngImpl
/*     */   implements ContentCountMng
/*     */ {
/* 120 */   private int interval = 3600000;
/*     */   private CmsConfigMng cmsConfigMng;
/*     */   private ContentCountDao dao;
/*     */ 
/*     */   public int contentUp(Integer id)
/*     */   {
/*  24 */     ContentCount c = this.dao.findById(id);
/*  25 */     if (c == null) {
/*  26 */       return 0;
/*     */     }
/*  28 */     int count = c.getUps().intValue() + 1;
/*  29 */     c.setUps(Integer.valueOf(count));
/*  30 */     c.setUpsMonth(Integer.valueOf(c.getUpsMonth().intValue() + 1));
/*  31 */     c.setUpsWeek(Short.valueOf((short)(c.getUpsWeek().shortValue() + 1)));
/*  32 */     c.setUpsDay(Short.valueOf((short)(c.getUpsDay().shortValue() + 1)));
/*  33 */     return count;
/*     */   }
/*     */ 
/*     */   public int contentDown(Integer id) {
/*  37 */     ContentCount c = this.dao.findById(id);
/*  38 */     if (c == null) {
/*  39 */       return 0;
/*     */     }
/*  41 */     int count = c.getDowns().intValue() + 1;
/*  42 */     c.setDowns(Integer.valueOf(count));
/*  43 */     return count;
/*     */   }
/*     */ 
/*     */   public void downloadCount(Integer contentId) {
/*  47 */     ContentCount c = findById(contentId);
/*  48 */     c.setDownloads(Integer.valueOf(c.getDownloads().intValue() + 1));
/*  49 */     c.setDownloadsMonth(Integer.valueOf(c.getDownloadsMonth().intValue() + 1));
/*  50 */     c.setDownloadsWeek(Short.valueOf((short)(c.getCommentsWeek().shortValue() + 1)));
/*  51 */     c.setDownloadsDay(Short.valueOf((short)(c.getDownloadsDay().shortValue() + 1)));
/*     */   }
/*     */ 
/*     */   public void commentCount(Integer contentId) {
/*  55 */     ContentCount c = findById(contentId);
/*  56 */     c.setComments(Integer.valueOf(c.getComments().intValue() + 1));
/*  57 */     c.setCommentsMonth(Integer.valueOf(c.getCommentsMonth().intValue() + 1));
/*  58 */     c.setCommentsWeek(Short.valueOf((short)(c.getCommentsWeek().shortValue() + 1)));
/*  59 */     c.setCommentsDay(Short.valueOf((short)(c.getCommentsDay().shortValue() + 1)));
/*     */   }
/*     */ 
/*     */   public int freshCacheToDB(Ehcache cache) {
/*  63 */     CmsConfig config = this.cmsConfigMng.get();
/*  64 */     clearCount(config);
/*  65 */     int count = this.dao.freshCacheToDB(cache);
/*  66 */     copyCount(config);
/*  67 */     return count;
/*     */   }
/*     */ 
/*     */   private int clearCount(CmsConfig config) {
/*  71 */     Calendar curr = Calendar.getInstance();
/*  72 */     Calendar last = Calendar.getInstance();
/*  73 */     last.setTime(config.getCountClearTime());
/*  74 */     int currDay = curr.get(6);
/*  75 */     int lastDay = last.get(6);
/*  76 */     if (currDay != lastDay) {
/*  77 */       int currWeek = curr.get(3);
/*  78 */       int lastWeek = last.get(3);
/*  79 */       int currMonth = curr.get(2);
/*  80 */       int lastMonth = last.get(2);
/*  81 */       this.cmsConfigMng.updateCountClearTime(curr.getTime());
/*  82 */       return this.dao.clearCount(currWeek != lastWeek, currMonth != lastMonth);
/*     */     }
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */   private int copyCount(CmsConfig config)
/*     */   {
/*  89 */     long curr = System.currentTimeMillis();
/*  90 */     long last = config.getCountCopyTime().getTime();
/*  91 */     if (curr > this.interval + last) {
/*  92 */       this.cmsConfigMng.updateCountCopyTime(new Date(curr));
/*  93 */       return this.dao.copyCount();
/*     */     }
/*  95 */     return 0;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public ContentCount findById(Integer id) {
/* 101 */     ContentCount entity = this.dao.findById(id);
/* 102 */     return entity;
/*     */   }
/*     */ 
/*     */   public ContentCount save(ContentCount count, Content content) {
/* 106 */     count.setContent(content);
/* 107 */     count.init();
/* 108 */     this.dao.save(count);
/* 109 */     content.setContentCount(count);
/* 110 */     return count;
/*     */   }
/*     */ 
/*     */   public ContentCount update(ContentCount bean) {
/* 114 */     Updater updater = new Updater(bean);
/* 115 */     ContentCount entity = this.dao.updateByUpdater(updater);
/* 116 */     return entity;
/*     */   }
/*     */ 
/*     */   public void setInterval(int interval)
/*     */   {
/* 131 */     this.interval = (interval * 60 * 1000);
/*     */   }
/*     */   @Autowired
/*     */   public void setCmsConfigMng(CmsConfigMng cmsConfigMng) {
/* 136 */     this.cmsConfigMng = cmsConfigMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(ContentCountDao dao) {
/* 141 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.ContentCountMngImpl
 * JD-Core Version:    0.6.0
 */