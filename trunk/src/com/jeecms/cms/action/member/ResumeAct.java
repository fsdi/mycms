/*     */ package com.jeecms.cms.action.member;
/*     */ 
/*     */ import com.jeecms.cms.entity.assist.CmsJobApply;
/*     */ import com.jeecms.cms.entity.main.CmsConfig;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.CmsUserExt;
/*     */ import com.jeecms.cms.entity.main.CmsUserResume;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.entity.main.MemberConfig;
/*     */ import com.jeecms.cms.manager.assist.CmsJobApplyMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserExtMng;
/*     */ import com.jeecms.cms.manager.main.CmsUserResumeMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.cms.web.WebErrors;
/*     */ import com.jeecms.common.page.Pagination;
/*     */ import com.jeecms.common.page.SimplePage;
/*     */ import com.jeecms.common.web.CookieUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ResumeAct
/*     */ {
/*  46 */   private static final Logger log = LoggerFactory.getLogger(ResumeAct.class);
/*     */   public static final String MEMBER_RESUME = "tpl.resumeEdit";
/*     */   public static final String MEMBER_APPLYS = "tpl.jobApplys";
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserExtMng cmsUserExtMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsUserResumeMng cmsUserResumeMng;
/*     */ 
/*     */   @Autowired
/*     */   private CmsJobApplyMng jobApplyMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @RequestMapping(value={"/member/resume.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String resumeInput(HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  63 */     CmsSite site = CmsUtils.getSite(request);
/*  64 */     CmsUser user = CmsUtils.getUser(request);
/*  65 */     FrontUtils.frontData(request, model, site);
/*  66 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  68 */     if (!mcfg.isMemberOn()) {
/*  69 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/*  71 */     if (user == null) {
/*  72 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/*  74 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  75 */       "member", "tpl.resumeEdit");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/member/resume.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String resumeSubmit(CmsUserResume resume, CmsUserExt ext, String nextUrl, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */     throws IOException
/*     */   {
/*  92 */     CmsSite site = CmsUtils.getSite(request);
/*  93 */     CmsUser user = CmsUtils.getUser(request);
/*  94 */     FrontUtils.frontData(request, model, site);
/*  95 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/*  97 */     if (!mcfg.isMemberOn()) {
/*  98 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 100 */     if (user == null) {
/* 101 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 103 */     ext.setId(user.getId());
/* 104 */     this.cmsUserExtMng.update(ext, user);
/* 105 */     resume.setId(user.getId());
/* 106 */     this.cmsUserResumeMng.update(resume, user);
/* 107 */     log.info("update CmsUserExt success. id={}", user.getId());
/* 108 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */   @RequestMapping(value={"/member/myapplys.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String myapplys(Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
/* 113 */     CmsSite site = CmsUtils.getSite(request);
/* 114 */     CmsUser user = CmsUtils.getUser(request);
/* 115 */     FrontUtils.frontData(request, model, site);
/* 116 */     FrontUtils.frontPageData(request, model);
/* 117 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 119 */     if (!mcfg.isMemberOn()) {
/* 120 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 122 */     if (user == null) {
/* 123 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 125 */     Pagination pagination = this.jobApplyMng.getPage(user.getId(), null, null, true, SimplePage.cpn(pageNo), CookieUtils.getPageSize(request));
/* 126 */     model.addAttribute("pagination", pagination);
/* 127 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/* 128 */       "member", "tpl.jobApplys");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/jobapply_delete.jspx"})
/*     */   public String delete(Integer[] ids, HttpServletRequest request, String nextUrl, HttpServletResponse response, ModelMap model) {
/* 134 */     CmsSite site = CmsUtils.getSite(request);
/* 135 */     CmsUser user = CmsUtils.getUser(request);
/* 136 */     FrontUtils.frontData(request, model, site);
/* 137 */     MemberConfig mcfg = site.getConfig().getMemberConfig();
/*     */ 
/* 139 */     if (!mcfg.isMemberOn()) {
/* 140 */       return FrontUtils.showMessage(request, model, "member.memberClose", new String[0]);
/*     */     }
/* 142 */     if (user == null) {
/* 143 */       return FrontUtils.showLogin(request, model, site);
/*     */     }
/* 145 */     WebErrors errors = validateDelete(ids, site, user, request);
/* 146 */     if (errors.hasErrors()) {
/* 147 */       return FrontUtils.showError(request, response, model, errors);
/*     */     }
/* 149 */     CmsJobApply[] arr = this.jobApplyMng.deleteByIds(ids);
/* 150 */     log.info("member contribute delete Content success. ids={}", 
/* 151 */       StringUtils.join(arr, ","));
/* 152 */     return FrontUtils.showSuccess(request, model, nextUrl);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/jobapply.jspx"})
/*     */   public void jobapply(Integer cId, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
/* 158 */     CmsUser user = CmsUtils.getUser(request);
/* 159 */     JSONObject object = new JSONObject();
/* 160 */     if (user == null) {
/* 161 */       object.put("result", -1);
/* 162 */     } else if (cId == null) {
/* 163 */       object.put("result", -2);
/*     */     } else {
/* 165 */       Content c = this.contentMng.findById(cId);
/* 166 */       if (c == null) {
/* 167 */         object.put("result", -3);
/* 168 */       } else if (user.getUserResume() == null) {
/* 169 */         object.put("result", -4);
/* 170 */       } else if (user.hasApplyToday(cId)) {
/* 171 */         object.put("result", 0);
/*     */       } else {
/* 173 */         CmsJobApply jobApply = new CmsJobApply();
/* 174 */         jobApply.setApplyTime(Calendar.getInstance().getTime());
/* 175 */         jobApply.setContent(c);
/* 176 */         jobApply.setUser(user);
/* 177 */         this.jobApplyMng.save(jobApply);
/* 178 */         object.put("result", 1);
/*     */       }
/*     */     }
/* 181 */     ResponseUtils.renderJson(response, object.toString());
/*     */   }
/*     */ 
/*     */   private WebErrors validateDelete(Integer[] ids, CmsSite site, CmsUser user, HttpServletRequest request)
/*     */   {
/* 186 */     WebErrors errors = WebErrors.create(request);
/* 187 */     if (vldOpt(errors, site, user, ids)) {
/* 188 */       return errors;
/*     */     }
/* 190 */     return errors;
/*     */   }
/*     */ 
/*     */   private boolean vldOpt(WebErrors errors, CmsSite site, CmsUser user, Integer[] ids)
/*     */   {
/* 195 */     for (Integer id : ids) {
/* 196 */       if (errors.ifNull(id, "id")) {
/* 197 */         return true;
/*     */       }
/* 199 */       CmsJobApply jobapply = this.jobApplyMng.findById(id);
/*     */ 
/* 201 */       if (errors.ifNotExist(jobapply, CmsJobApply.class, id)) {
/* 202 */         return true;
/*     */       }
/*     */ 
/* 205 */       if (!jobapply.getUser().getId().equals(user.getId())) {
/* 206 */         errors.noPermission(Content.class, id);
/* 207 */         return true;
/*     */       }
/*     */     }
/* 210 */     return false;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.member.ResumeAct
 * JD-Core Version:    0.6.0
 */