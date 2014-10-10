/*    */ package com.jeecms.cms.action.admin;
/*    */ 
/*    */ import com.jeecms.cms.entity.main.CmsConfig;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.entity.main.CmsUser;
/*    */ import com.jeecms.cms.manager.main.CmsSiteMng;
/*    */ import com.jeecms.cms.statistic.CmsStatisticSvc;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import java.util.List;
/*    */ import java.util.Properties;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class WelcomeAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteMng cmsSiteMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsStatisticSvc cmsStatisticSvc;
/*    */ 
/*    */   @RequestMapping({"/index.do"})
/*    */   public String index()
/*    */   {
/* 24 */     return "index";
/*    */   }
/*    */   @RequestMapping({"/map.do"})
/*    */   public String map() {
/* 29 */     return "map";
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/top.do"})
/*    */   public String top(HttpServletRequest request, ModelMap model) {
/* 35 */     List siteList = this.cmsSiteMng.getList();
/* 36 */     CmsSite site = CmsUtils.getSite(request);
/* 37 */     CmsUser user = CmsUtils.getUser(request);
/* 38 */     model.addAttribute("siteList", siteList);
/* 39 */     model.addAttribute("site", site);
/* 40 */     model.addAttribute("siteParam", "_site_id_param");
/* 41 */     model.addAttribute("user", user);
/* 42 */     return "top";
/*    */   }
/*    */   @RequestMapping({"/main.do"})
/*    */   public String main() {
/* 47 */     return "main";
/*    */   }
/*    */   @RequestMapping({"/left.do"})
/*    */   public String left() {
/* 52 */     return "left";
/*    */   }
/*    */   @RequestMapping({"/right.do"})
/*    */   public String right(HttpServletRequest request, ModelMap model) {
/* 57 */     CmsSite site = CmsUtils.getSite(request);
/* 58 */     CmsUser user = CmsUtils.getUser(request);
/* 59 */     String version = site.getConfig().getVersion();
/* 60 */     Properties props = System.getProperties();
/* 61 */     Runtime runtime = Runtime.getRuntime();
/* 62 */     long freeMemoery = runtime.freeMemory();
/* 63 */     long totalMemory = runtime.totalMemory();
/* 64 */     long usedMemory = totalMemory - freeMemoery;
/* 65 */     long maxMemory = runtime.maxMemory();
/* 66 */     long useableMemory = maxMemory - totalMemory + freeMemoery;
/* 67 */     model.addAttribute("props", props);
/* 68 */     model.addAttribute("freeMemoery", Long.valueOf(freeMemoery));
/* 69 */     model.addAttribute("totalMemory", Long.valueOf(totalMemory));
/* 70 */     model.addAttribute("usedMemory", Long.valueOf(usedMemory));
/* 71 */     model.addAttribute("maxMemory", Long.valueOf(maxMemory));
/* 72 */     model.addAttribute("useableMemory", Long.valueOf(useableMemory));
/* 73 */     model.addAttribute("version", version);
/* 74 */     model.addAttribute("user", user);
/* 75 */     model.addAttribute("flowMap", this.cmsStatisticSvc.getWelcomeSiteFlowData(site.getId()));
/* 76 */     return "right";
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.admin.WelcomeAct
 * JD-Core Version:    0.6.0
 */