/*    */ package com.jeecms.cms.manager.assist.impl;
/*    */ 
/*    */ import com.jeecms.cms.dao.assist.CmsSiteFlowDao;
/*    */ import com.jeecms.cms.entity.assist.CmsSiteFlow;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsSiteFlowMng;
/*    */ import com.jeecms.common.util.DateFormatUtils;
/*    */ import java.sql.Timestamp;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import net.sf.ehcache.Ehcache;
/*    */ import net.sf.ehcache.Element;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CmsSiteFlowMngImpl
/*    */   implements CmsSiteFlowMng
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteFlowDao dao;
/*    */ 
/*    */   public CmsSiteFlow save(CmsSite site, String ip, String page, String sessionId)
/*    */   {
/* 27 */     CmsSiteFlow cmsSiteFlow = new CmsSiteFlow();
/* 28 */     Date now = new Timestamp(System.currentTimeMillis());
/* 29 */     cmsSiteFlow.setSite(site);
/* 30 */     cmsSiteFlow.setAccessIp(ip);
/* 31 */     cmsSiteFlow.setAccessPage(page);
/* 32 */     cmsSiteFlow.setAccessTime(now);
/* 33 */     cmsSiteFlow.setAccessDate(DateFormatUtils.formatDate(now));
/* 34 */     cmsSiteFlow.setSessionId(sessionId);
/* 35 */     return this.dao.save(cmsSiteFlow);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public CmsSiteFlow findUniqueByProperties(Integer siteId, String accessDate, String sessionId, String page) {
/* 41 */     return this.dao.findUniqueByProperties(siteId, accessDate, sessionId, page);
/*    */   }
/*    */ 
/*    */   public int freshCacheToDB(Ehcache cache)
/*    */   {
/* 46 */     int count = 0;
/* 47 */     List<String> list = cache.getKeys();
/* 48 */     for (String uuid : list) {
/* 49 */       Element element = cache.get(uuid);
/* 50 */       if (element == null) {
/* 51 */         return count;
/*    */       }
/* 53 */       CmsSiteFlow cmsSiteFlow = (CmsSiteFlow)element.getValue();
/* 54 */       if ((cmsSiteFlow.getId() != null) || 
/* 55 */         (cmsSiteFlow.getSessionId() == null)) continue;
/* 56 */       this.dao.save(cmsSiteFlow);
/*    */     }
/*    */ 
/* 59 */     return count;
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.manager.assist.impl.CmsSiteFlowMngImpl
 * JD-Core Version:    0.6.0
 */