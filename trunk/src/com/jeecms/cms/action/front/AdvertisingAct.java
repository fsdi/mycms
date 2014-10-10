/*    */ package com.jeecms.cms.action.front;
/*    */ 
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertising;
/*    */ import com.jeecms.cms.entity.assist.CmsAdvertisingSpace;
/*    */ import com.jeecms.cms.entity.main.CmsSite;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingMng;
/*    */ import com.jeecms.cms.manager.assist.CmsAdvertisingSpaceMng;
/*    */ import com.jeecms.cms.web.CmsUtils;
/*    */ import com.jeecms.cms.web.FrontUtils;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class AdvertisingAct
/*    */ {
/*    */   public static final String TPL_AD = "tpl.advertising";
/*    */   public static final String TPL_ADSPACE = "tpl.adspace";
/*    */ 
/*    */   @Autowired
/*    */   private CmsAdvertisingMng cmsAdvertisingMng;
/*    */ 
/*    */   @Autowired
/*    */   private CmsAdvertisingSpaceMng cmsAdvertisingSpaceMng;
/*    */ 
/*    */   @RequestMapping({"/ad.jspx"})
/*    */   public String ad(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 37 */     CmsSite site = CmsUtils.getSite(request);
/* 38 */     if (id != null) {
/* 39 */       CmsAdvertising ad = this.cmsAdvertisingMng.findById(id);
/* 40 */       model.addAttribute("ad", ad);
/*    */     }
/* 42 */     FrontUtils.frontData(request, model, site);
/* 43 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 44 */       "csi", "tpl.advertising");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/adspace.jspx"})
/*    */   public String adspace(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 50 */     CmsSite site = CmsUtils.getSite(request);
/* 51 */     if (id != null) {
/* 52 */       CmsAdvertisingSpace adspace = this.cmsAdvertisingSpaceMng.findById(id);
/* 53 */       List adList = this.cmsAdvertisingMng.getList(id, Boolean.valueOf(true));
/* 54 */       model.addAttribute("adspace", adspace);
/* 55 */       model.addAttribute("adList", adList);
/*    */     }
/* 57 */     FrontUtils.frontData(request, model, site);
/* 58 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 59 */       "csi", "tpl.adspace");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/ad_display.jspx"})
/*    */   public void display(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 65 */     if (id != null) {
/* 66 */       this.cmsAdvertisingMng.display(id);
/*    */     }
/* 68 */     response.setHeader("Pragma", "No-cache");
/* 69 */     response.setHeader("Cache-Control", "no-cache");
/* 70 */     response.setDateHeader("Expires", 0L);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/ad_click.jspx"})
/*    */   public void click(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 76 */     if (id != null) {
/* 77 */       this.cmsAdvertisingMng.click(id);
/*    */     }
/* 79 */     response.setHeader("Pragma", "No-cache");
/* 80 */     response.setHeader("Cache-Control", "no-cache");
/* 81 */     response.setDateHeader("Expires", 0L);
/*    */   }
/*    */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.AdvertisingAct
 * JD-Core Version:    0.6.0
 */