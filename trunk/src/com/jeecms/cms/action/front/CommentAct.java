/*     */ package com.jeecms.cms.action.front;
/*     */ 
/*     */ import com.jeecms.cms.entity.main.Channel;
/*     */ import com.jeecms.cms.entity.main.CmsGroup;
/*     */ import com.jeecms.cms.entity.main.CmsSite;
/*     */ import com.jeecms.cms.entity.main.CmsUser;
/*     */ import com.jeecms.cms.entity.main.Content;
/*     */ import com.jeecms.cms.manager.assist.CmsCommentMng;
/*     */ import com.jeecms.cms.manager.main.ContentMng;
/*     */ import com.jeecms.cms.web.CmsUtils;
/*     */ import com.jeecms.cms.web.FrontUtils;
/*     */ import com.jeecms.common.web.RequestUtils;
/*     */ import com.jeecms.common.web.ResponseUtils;
/*     */ import com.jeecms.common.web.session.SessionProvider;
/*     */ import com.octo.captcha.service.CaptchaServiceException;
/*     */ import com.octo.captcha.service.image.ImageCaptchaService;
/*     */ import java.util.List;
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
/*     */ public class CommentAct
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(CommentAct.class);
/*     */   public static final String COMMENT_PAGE = "tpl.commentPage";
/*     */   public static final String COMMENT_LIST = "tpl.commentList";
/*     */ 
/*     */   @Autowired
/*     */   private CmsCommentMng cmsCommentMng;
/*     */ 
/*     */   @Autowired
/*     */   private ContentMng contentMng;
/*     */ 
/*     */   @Autowired
/*     */   private SessionProvider session;
/*     */ 
/*     */   @Autowired
/*     */   private ImageCaptchaService imageCaptchaService;
/*     */ 
/*     */   @RequestMapping(value={"/comment*.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String page(Integer contentId, Integer pageNo, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  48 */     CmsSite site = CmsUtils.getSite(request);
/*  49 */     Content content = this.contentMng.findById(contentId);
/*  50 */     if (content == null) {
/*  51 */       return FrontUtils.showMessage(request, model, 
/*  52 */         "comment.contentNotFound", new String[0]);
/*     */     }
/*     */ 
/*  54 */     if (content.getChannel().getCommentControl().intValue() == 2) {
/*  55 */       return FrontUtils.showMessage(request, model, "comment.closed", new String[0]);
/*     */     }
/*     */ 
/*  58 */     model.putAll(RequestUtils.getQueryParams(request));
/*  59 */     FrontUtils.frontData(request, model, site);
/*  60 */     FrontUtils.frontPageData(request, model);
/*  61 */     model.addAttribute("content", content);
/*  62 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  63 */       "special", "tpl.commentPage");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/comment_list.jspx"})
/*     */   public String list(Integer siteId, Integer contentId, Integer greatTo, Integer recommend, Integer checked, Integer orderBy, Integer count, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  71 */     if ((count == null) || (count.intValue() <= 0) || (count.intValue() > 200))
/*  72 */       count = Integer.valueOf(200);
/*     */     boolean desc;
/*  75 */     if ((orderBy == null) || (orderBy.intValue() == 0))
/*  76 */       desc = true;
/*     */     else
/*  78 */       desc = false;
/*     */     boolean rec;
/*  80 */     if ((recommend == null) || (recommend.intValue() == 0))
/*  81 */       rec = false;
/*     */     else
/*  83 */       rec = true;
/*     */     Boolean chk;
/*  86 */     if (checked != null)
/*  87 */       chk = Boolean.valueOf(checked.intValue() != 0);
/*     */     else {
/*  89 */       chk = null;
/*     */     }
/*  91 */     List list = this.cmsCommentMng.getListForTag(siteId, contentId, 
/*  92 */       greatTo, chk, rec, desc, count.intValue());
/*     */ 
/*  94 */     model.putAll(RequestUtils.getQueryParams(request));
/*  95 */     model.addAttribute("list", list);
/*  96 */     CmsSite site = CmsUtils.getSite(request);
/*  97 */     FrontUtils.frontData(request, model, site);
/*  98 */     return FrontUtils.getTplPath(request, site.getSolutionPath(), 
/*  99 */       "csi", "tpl.commentList");
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"/comment.jspx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public void submit(Integer contentId, String text, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException
/*     */   {
/* 106 */     CmsSite site = CmsUtils.getSite(request);
/* 107 */     CmsUser user = CmsUtils.getUser(request);
/* 108 */     JSONObject json = new JSONObject();
/* 109 */     if (contentId == null) {
/* 110 */       json.put("success", false);
/* 111 */       json.put("status", 100);
/* 112 */       ResponseUtils.renderJson(response, json.toString());
/* 113 */       return;
/*     */     }
/* 115 */     if (StringUtils.isBlank(text)) {
/* 116 */       json.put("success", false);
/* 117 */       json.put("status", 101);
/* 118 */       ResponseUtils.renderJson(response, json.toString());
/* 119 */       return;
/*     */     }
/* 121 */     if ((user == null) || (user.getGroup().getNeedCaptcha().booleanValue())) {
/*     */       try
/*     */       {
/* 124 */         if (!this.imageCaptchaService.validateResponseForID(
/* 125 */           this.session.getSessionId(request, response), captcha).booleanValue())
/*     */         {
/* 126 */           json.put("success", false);
/* 127 */           json.put("status", 1);
/* 128 */           ResponseUtils.renderJson(response, json.toString());
/* 129 */           return;
/*     */         }
/*     */       } catch (CaptchaServiceException e) {
/* 132 */         json.put("success", false);
/* 133 */         json.put("status", 1);
/* 134 */         log.warn("", e);
/* 135 */         ResponseUtils.renderJson(response, json.toString());
/* 136 */         return;
/*     */       }
/*     */     }
/* 139 */     Content content = this.contentMng.findById(contentId);
/* 140 */     if (content == null)
/*     */     {
/* 142 */       json.put("success", false);
/* 143 */       json.put("status", 2);
/* 144 */     } else if (content.getChannel().getCommentControl().intValue() == 2)
/*     */     {
/* 146 */       json.put("success", false);
/* 147 */       json.put("status", 3);
/* 148 */     } else if ((content.getChannel().getCommentControl().intValue() == 1) && 
/* 149 */       (user == null))
/*     */     {
/* 151 */       json.put("success", false);
/* 152 */       json.put("status", 4);
/*     */     } else {
/* 154 */       boolean checked = false;
/* 155 */       Integer userId = null;
/* 156 */       if (user != null) {
/* 157 */         checked = !user.getGroup().getNeedCheck().booleanValue();
/* 158 */         userId = user.getId();
/*     */       }
/* 160 */       this.cmsCommentMng.comment(text, RequestUtils.getIpAddr(request), 
/* 161 */         contentId, site.getId(), userId, checked, false);
/* 162 */       json.put("success", true);
/* 163 */       json.put("status", 0);
/*     */     }
/* 165 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/comment_up.jspx"})
/*     */   public void up(Integer contentId, HttpServletRequest request, HttpServletResponse response) {
/* 171 */     if (exist(contentId)) {
/* 172 */       this.cmsCommentMng.ups(contentId);
/* 173 */       ResponseUtils.renderJson(response, "true");
/*     */     } else {
/* 175 */       ResponseUtils.renderJson(response, "false");
/*     */     }
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/comment_down.jspx"})
/*     */   public void down(Integer contentId, HttpServletRequest request, HttpServletResponse response) {
/* 182 */     if (exist(contentId)) {
/* 183 */       this.cmsCommentMng.downs(contentId);
/* 184 */       ResponseUtils.renderJson(response, "true");
/*     */     } else {
/* 186 */       ResponseUtils.renderJson(response, "false");
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean exist(Integer id) {
/* 191 */     if (id == null) {
/* 192 */       return false;
/*     */     }
/* 194 */     Content content = this.contentMng.findById(id);
/* 195 */     return content != null;
/*     */   }
/*     */ }

/* Location:           D:\tools\tomcat-6.0.36\webapps\ROOT\WEB-INF\classes\
 * Qualified Name:     com.jeecms.cms.action.front.CommentAct
 * JD-Core Version:    0.6.0
 */