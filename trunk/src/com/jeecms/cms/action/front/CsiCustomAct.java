/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import com.jeecms.common.web.RequestUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CsiCustomAct
/*    */ {
/* 25 */   private static final Logger log = LoggerFactory.getLogger(CsiCustomAct.class);
/*    */ 
/*    */   @RequestMapping({"/csi_custom.jspx"})
/*    */   public String custom(String tpl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 40 */     log.debug("visit csi custom template: {}", tpl);
/* 41 */     CmsSite site = CmsUtils.getSite(request);
/*    */ 
/* 43 */     model.putAll(RequestUtils.getQueryParams(request));
/* 44 */     FrontUtils.frontData(request, model, site);
/* 45 */     return FrontUtils.getTplPath(site.getSolutionPath(), "csi_custom", 
/* 46 */       tpl);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.CsiCustomAct
 * JD-Core Version:    0.6.0
 */