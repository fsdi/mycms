/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsGuestbook;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsGuestbookMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class GuestbookMemberAct
/*     */ {
/*     */   public static final String GUESTBOOK_LIST = "tpl.guestBookLists";
/*     */   public static final String GUESTBOOK_DETAIL = "tpl.guestBookDetail";
/*     */   public static final String GUESTBOOK_REPLAY = "tpl.guestBookReplay";
/*     */ 
/*     */   @Autowired
/*     */   private CmsGuestbookMng guestbookMng;
/*     */ 
/*     */   @RequestMapping({"/member/myguestbook.jspx"})
/*     */   public String myguestbook(Integer pageNo, Integer ctgId, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  52 */     CmsSite site = CmsUtils.getSite(request);
/*  53 */     CmsUser user = CmsUtils.getUser(request);
/*  54 */     FrontUtils.frontData(request, model, site);
/*  55 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  57 */     if (!mcfg.isMemberOn()) {
/*  58 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  60 */     if (user == null) {
/*  61 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  63 */     Pagination pagination = this.guestbookMng.getPage(site.getId(), ctgId, 
/*  64 */       user.getId(), null, null, true, false, SimplePage.cpn(pageNo), 
/*  65 */       CookieUtils.getPageSize(request));
/*  66 */     model.addAttribute("pagination", pagination);
/*  67 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  68 */       "guestbook", "tpl.guestBookLists");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/guestbook_detail.jspx"})
/*     */   public String guestbook_detail(Integer id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  78 */     CmsSite site = CmsUtils.getSite(request);
/*  79 */     CmsUser user = CmsUtils.getUser(request);
/*  80 */     FrontUtils.frontData(request, model, site);
/*  81 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  83 */     if (!mcfg.isMemberOn()) {
/*  84 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  86 */     if (user == null) {
/*  87 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  89 */     CmsGuestbook guestbook = this.guestbookMng.findById(id);
/*  90 */     model.addAttribute("guestbook", guestbook);
/*  91 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  92 */       "guestbook", "tpl.guestBookDetail");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/guestbook_replay.jspx"})
/*     */   public String guestbook_replay(Integer id, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 101 */     CmsSite site = CmsUtils.getSite(request);
/* 102 */     CmsUser user = CmsUtils.getUser(request);
/* 103 */     FrontUtils.frontData(request, model, site);
/* 104 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 106 */     if (!mcfg.isMemberOn()) {
/* 107 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 109 */     if (user == null) {
/* 110 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 112 */     CmsGuestbook guestbook = this.guestbookMng.findById(id);
/* 113 */     if (!guestbook.getMember().equals(user)) {
/* 114 */       WebErrors errors = WebErrors.create(request);
/* 115 */       errors.addErrorCode("error.noPermissionsView");
/* 116 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 118 */     model.addAttribute("guestbook", guestbook);
/* 119 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 120 */       "guestbook", "tpl.guestBookReplay");
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.GuestbookMemberAct
 * JD-Core Version:    0.6.0
 */