/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class RssAct
/*    */ {
/*    */   public static final String RSS_TPL = "tpl.rss";
/*    */ 
/*    */   @RequestMapping(value={"/rss.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*    */   public String rss(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 24 */     response.setContentType("text/xml;charset=UTF-8");
/* 25 */     CmsSite site = CmsUtils.getSite(request);
/* 26 */     FrontUtils.frontData(request, model, site);
/* 27 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 28 */       "special", "tpl.rss");
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.RssAct
 * JD-Core Version:    0.6.0
 */