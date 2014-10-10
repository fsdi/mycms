/*     */ package com.jeecms.cms.manager.main.impl;
/*     */ 
/*     */ import com.jeecms.cms.dao.main.CmsLogDao;
/*     */ import com.jeecms.cms.entity.main.CmsLog;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.main.CmsLogMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.springmvc.MessageResolver;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.web.util.UrlPathHelper;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class CmsLogMngImpl
/*     */   implements CmsLogMng
/*     */ {
/*     */   private CmsUserMng cmsUserMng;
/*     */   private CmsLogDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Pagination getPage(Integer category, Integer siteId, String username, String title, String ip, int pageNo, int pageSize)
/*     */   {
/*     */     Pagination page;
/*  33 */     if (StringUtils.isBlank(username)) {
/*  34 */       page = this.dao.getPage(category, siteId, null, title, ip, pageNo, 
/*  35 */         pageSize);
/*     */     } else {
/*  37 */       CmsUser user = this.cmsUserMng.findByUsername(username);
/*  38 */       if (user != null)
/*  39 */         page = this.dao.getPage(category, siteId, user.getId(), title, ip, 
/*  40 */           pageNo, pageSize);
/*     */       else {
/*  42 */         page = new Pagination(1, pageSize, 0, new ArrayList(0));
/*     */       }
/*     */     }
/*  45 */     return page;
/*     */   }
/*     */   @Transactional(readOnly=true)
/*     */   public CmsLog findById(Integer id) {
/*  50 */     CmsLog entity = this.dao.findById(id);
/*  51 */     return entity;
/*     */   }
/*     */ 
/*     */   public CmsLog save(Integer category, CmsSite site, CmsUser user, String url, String ip, Date date, String title, String content)
/*     */   {
/*  56 */     CmsLog log = new CmsLog();
/*  57 */     log.setSite(site);
/*  58 */     log.setUser(user);
/*  59 */     log.setCategory(category);
/*  60 */     log.setIp(ip);
/*  61 */     log.setTime(date);
/*  62 */     log.setUrl(url);
/*  63 */     log.setTitle(title);
/*  64 */     log.setContent(content);
/*  65 */     save(log);
/*  66 */     return log;
/*     */   }
/*     */ 
/*     */   public CmsLog loginSuccess(HttpServletRequest request, CmsUser user, String title)
/*     */   {
/*  71 */     String ip = RequestUtils.getIpAddr(request);
/*  72 */     UrlPathHelper helper = new UrlPathHelper();
/*  73 */     String uri = helper.getOriginatingRequestUri(request);
/*  74 */     Date date = new Date();
/*  75 */     CmsLog log = save(Integer.valueOf(1), null, user, uri, ip, date, 
/*  76 */       MessageResolver.getMessage(request, title, new Object[0]), null);
/*  77 */     return log;
/*     */   }
/*     */ 
/*     */   public CmsLog loginFailure(HttpServletRequest request, String title, String content)
/*     */   {
/*  82 */     String ip = RequestUtils.getIpAddr(request);
/*  83 */     UrlPathHelper helper = new UrlPathHelper();
/*  84 */     String uri = helper.getOriginatingRequestUri(request);
/*  85 */     Date date = new Date();
/*  86 */     CmsLog log = save(Integer.valueOf(2), null, null, uri, ip, date, 
/*  87 */       MessageResolver.getMessage(request, title, new Object[0]), content);
/*  88 */     return log;
/*     */   }
/*     */ 
/*     */   public CmsLog operating(HttpServletRequest request, String title, String content)
/*     */   {
/*  93 */     CmsSite site = CmsUtils.getSite(request);
/*  94 */     CmsUser user = CmsUtils.getUser(request);
/*  95 */     String ip = RequestUtils.getIpAddr(request);
/*  96 */     UrlPathHelper helper = new UrlPathHelper();
/*  97 */     String uri = helper.getOriginatingRequestUri(request);
/*  98 */     Date date = new Date();
/*  99 */     CmsLog log = save(Integer.valueOf(3), site, user, uri, ip, date, 
/* 100 */       MessageResolver.getMessage(request, title, new Object[0]), content);
/* 101 */     return log;
/*     */   }
/*     */ 
/*     */   public CmsLog save(CmsLog bean) {
/* 105 */     this.dao.save(bean);
/* 106 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deleteBatch(Integer category, Integer siteId, Integer days) {
/* 110 */     Date date = null;
/* 111 */     if ((days != null) && (days.intValue() > 0)) {
/* 112 */       Calendar cal = Calendar.getInstance();
/* 113 */       cal.add(5, -days.intValue());
/* 114 */       date = cal.getTime();
/*     */     }
/* 116 */     return this.dao.deleteBatch(category, siteId, date);
/*     */   }
/*     */ 
/*     */   public CmsLog deleteById(Integer id) {
/* 120 */     CmsLog bean = this.dao.deleteById(id);
/* 121 */     return bean;
/*     */   }
/*     */ 
/*     */   public CmsLog[] deleteByIds(Integer[] ids) {
/* 125 */     CmsLog[] beans = new CmsLog[ids.length];
/* 126 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 127 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 129 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCmsUserMng(CmsUserMng cmsUserMng)
/*     */   {
/* 137 */     this.cmsUserMng = cmsUserMng;
/*     */   }
/*     */   @Autowired
/*     */   public void setDao(CmsLogDao dao) {
/* 142 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.main.impl.CmsLogMngImpl
 * JD-Core Version:    0.6.0
 */