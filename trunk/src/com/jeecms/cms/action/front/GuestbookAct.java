/*     */ package com.jeecms.cms.action.front;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbookCtg;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookCtgMng;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class GuestbookAct
/*     */ {
/*  36 */   private static final Logger log = LoggerFactory.getLogger(GuestbookAct.class);
/*     */   public static final String GUESTBOOK_INDEX = "tpl.guestbookIndex";
/*     */   public static final String GUESTBOOK_CTG = "tpl.guestbookCtg";
/*     */   public static final String GUESTBOOK_DETAIL = "tpl.guestbookDetail";
/*     */ 
/*     */   @Autowired
/*     */   private CmsGuestbookCtgMng cmsGuestbookCtgMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsGuestbookMng cmsGuestbookMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @RequestMapping(value={"/guestbook*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String index(Integer ctgId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  55 */     CmsSite site = CmsUtils.getSite(request);
/*  56 */     FrontUtils.frontData(request, model, site);
/*  57 */     FrontUtils.frontPageData(request, model);
/*  58 */     CmsGuestbookCtg ctg = null;
/*  59 */     if (ctgId != null) {
/*  60 */       ctg = this.cmsGuestbookCtgMng.findById(ctgId);
/*     */     }
/*  62 */     if (ctg == null)
/*     */     {
/*  64 */       return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  65 */         "special", "tpl.guestbookIndex");
/*     */     }
/*     */ 
/*  68 */     model.addAttribute("ctg", ctg);
/*  69 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  70 */       "special", "tpl.guestbookCtg");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/guestbook/{id}.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String detail(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  77 */     CmsSite site = CmsUtils.getSite(request);
/*  78 */     CmsGuestbook guestbook = null;
/*  79 */     if (id != null) {
/*  80 */       guestbook = this.cmsGuestbookMng.findById(id);
/*     */     }
/*  82 */     model.addAttribute("guestbook", guestbook);
/*  83 */     FrontUtils.frontData(request, model, site);
/*  84 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  85 */       "special", "tpl.guestbookDetail");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/guestbook.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void submit(Integer siteId, Integer ctgId, String title, String content, String email, String phone, String qq, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws JSONException
/*     */   {
/* 104 */     CmsSite site = CmsUtils.getSite(request);
/* 105 */     CmsUser member = CmsUtils.getUser(request);
/* 106 */     if (siteId == null) {
/* 107 */       siteId = site.getId();
/*     */     }
/* 109 */     JSONObject json = new JSONObject();
/*     */     try {
/* 111 */       if (!this.imageCaptchaService.validateResponseForID(
/* 112 */         this.session.getSessionId(request, response), captcha).booleanValue())
/*     */       {
/* 113 */         json.put("success", false);
/* 114 */         json.put("status", 1);
/* 115 */         ResponseUtils.renderJson(response, json.toString());
/* 116 */         return;
/*     */       }
/*     */     } catch (CaptchaServiceException e) {
/* 119 */       json.put("success", false);
/* 120 */       json.put("status", 1);
/* 121 */       ResponseUtils.renderJson(response, json.toString());
/* 122 */       log.warn("", e);
/* 123 */       return;
/*     */     }
/* 125 */     String ip = RequestUtils.getIpAddr(request);
/* 126 */     this.cmsGuestbookMng.save(member, siteId, ctgId, ip, title, content, email, 
/* 127 */       phone, qq);
/* 128 */     json.put("success", true);
/* 129 */     json.put("status", 0);
/* 130 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.GuestbookAct
 * JD-Core Version:    0.6.0
 */