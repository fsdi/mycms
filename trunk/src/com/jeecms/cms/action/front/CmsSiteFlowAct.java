/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.service.CmsSiteFlowCache;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import com.jeecms.common.web.ResponseUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CmsSiteFlowAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteFlowCache cmsSiteFlowCache;
/*    */ 
/*    */   @RequestMapping({"/flow_statistic.jspx"})
/*    */   public void flowStatistic(HttpServletRequest request, HttpServletResponse response, String page, String referer)
/*    */   {
/* 22 */     if (!StringUtils.isBlank(page)) {
/* 23 */       String ip = RequestUtils.getIpAddr(request);
/* 24 */       CmsSite site = CmsUtils.getSite(request);
/* 25 */       String sessionId = RequestUtils.getRequestedSessionId(request);
/* 26 */       this.cmsSiteFlowCache.flow(site, ip, sessionId, page, referer);
/* 27 */       ResponseUtils.renderJson(response, "true");
/*    */     } else {
/* 29 */       ResponseUtils.renderJson(response, "false");
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.CmsSiteFlowAct
 * JD-Core Version:    0.6.0
 */